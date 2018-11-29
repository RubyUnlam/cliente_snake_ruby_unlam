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

	private final String JUGAR = "jugar";
	private ObservadorDibujables observadorDibujable;
	private ManejadorES manejadorES;

	public ManejadorDeJuego(ManejadorES manejadorES) {
		this.manejadorES = manejadorES;
	}

	public void comunicarInicioDeJuego() {
		try {
			manejadorES.enviarString(JUGAR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			boolean finDelJuego = false;
			while (!finDelJuego) {
				ActualizacionDelJuego actualizacion = manejadorES.escuchar(ActualizacionDelJuego.class);
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
