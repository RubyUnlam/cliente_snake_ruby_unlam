package manejadores;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import cliente_snake_ruby_unlam.ActualizacionDelJuego;
import cliente_snake_ruby_unlam.Dibujable;
import com.google.gson.Gson;
import observables.ObservadoLectura;
import observables.ObservadorDibujables;

public class ManejadorDeJuego extends Thread implements ObservadoLectura {
	
	private ObservadorDibujables observadorDibujable;
	private Gson gson = new Gson();
	private ManejadorES manejadorES;

	public ManejadorDeJuego(ManejadorES manejadorES) {
		this.manejadorES = manejadorES;
	}

	public void comunicarInicioDeJuego() {
		try {
			manejadorES.getSalida().writeUTF("jugar");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			boolean finDelJuego = false;
			while (!finDelJuego) {
				String json = manejadorES.getEntrada().readUTF();
				ActualizacionDelJuego actualizacion = gson.fromJson(json, ActualizacionDelJuego.class);
				finDelJuego = actualizacion.terminoElJuego();
				enviarADibujar(actualizacion);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void enviarADibujar(ActualizacionDelJuego actualizacion) {
		new Thread() {
			@Override
			public void run() {
				observadorDibujable.notificarUbicaciones(actualizacion);
			}
		}.start();
	}


	@Override
	public void agregarObservadorDibujables(ObservadorDibujables observador) {
		this.observadorDibujable = observador;
	}

}
