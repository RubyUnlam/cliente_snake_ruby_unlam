package cliente_snake_ruby_unlam;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

public class Lector extends Thread implements ObservadoLectura {
	
	private Socket socket;
	private ObservadorLectura observador;
	private Gson gson = new Gson();
	private DataInputStream entrada;

	public Lector(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			this.entrada = new DataInputStream(socket.getInputStream());

            boolean conectado = true;

            while (conectado) {
				String json = entrada.readUTF();
				UbicacionesDTO ubicaciones = gson.fromJson(json, UbicacionesDTO.class);
                observador.notificarUbicaciones(ubicaciones);
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

	@Override
	public void agregarObservador(ObservadorLectura observador) {
		this.observador = observador;
	}

}
