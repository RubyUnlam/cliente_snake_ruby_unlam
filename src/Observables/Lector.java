package Observables;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import cliente_snake_ruby_unlam.Dibujable;
import cliente_snake_ruby_unlam.RegistroUsuario;
import com.google.gson.Gson;

public class Lector extends Thread implements ObservadoLectura {
	
	private Socket socket;
	private ObservadorDibujables observadorDibujable;
	private ObservadorAcceso observadorAcceso;
	private Gson gson = new Gson();
	private DataInputStream entrada;

	public Lector(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			this.entrada = new DataInputStream(socket.getInputStream());

            boolean noLogueado = true;

            while (noLogueado) {
				String json = entrada.readUTF();
				RegistroUsuario respuesta = gson.fromJson(json, RegistroUsuario.class);
				noLogueado = observadorAcceso.notificarRegistro(respuesta);
			}

			while (true) {
				String json = entrada.readUTF();
				List<Dibujable> dibujables = Arrays.asList(gson.fromJson(json, Dibujable[].class));
				enviarADibujar(dibujables);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            try {
				entrada.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void enviarADibujar(List<Dibujable> dibujables) {
		new Thread() {
			@Override
			public void run() {
				observadorDibujable.notificarUbicaciones(dibujables);
			}
		}.start();
	}


	@Override
	public void agregarObservadorDibujables(ObservadorDibujables observador) {
		this.observadorDibujable = observador;
	}

	@Override
	public void agregarObservadorAcceso(ObservadorAcceso observador) {
		this.observadorAcceso = observador;
	}
}
