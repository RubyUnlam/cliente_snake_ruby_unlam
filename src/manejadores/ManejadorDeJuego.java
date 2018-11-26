package manejadores;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import cliente_snake_ruby_unlam.Dibujable;
import com.google.gson.Gson;
import observables.ObservadoLectura;
import observables.ObservadorDibujables;

public class ManejadorDeJuego extends Thread implements ObservadoLectura {
	
	private Socket socket;
	private ObservadorDibujables observadorDibujable;
	private Gson gson = new Gson();
	private DataInputStream entrada;
	private DataOutputStream salida;

	public ManejadorDeJuego(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			this.entrada = new DataInputStream(socket.getInputStream());
			this.salida = new DataOutputStream(socket.getOutputStream());
			salida.writeUTF("jugar");

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

}
