package cliente_snake_ruby_unlam;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

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
            	UbicacionesDTO ubicaciones = gson.fromJson(entrada.readUTF(), UbicacionesDTO.class);
                observador.notificarUbicaciones(ubicaciones);
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
				entrada.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void agregarObservador(ObservadorLectura observador) {
		this.observador = observador;
	}

}
