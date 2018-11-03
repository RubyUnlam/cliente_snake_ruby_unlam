package cliente_snake_ruby_unlam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import com.google.gson.Gson;

public class Lector extends Thread implements ObservadoLectura {
	
	private Socket socket;
	private ObservadorLectura observador;
	private Gson gson = new Gson();

	public Lector(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

            boolean conectado = true;

            while (conectado) {
                List<Ubicacion> mensaje = (List<Ubicacion>) entrada.readObject();
                observador.notificarUbicaciones(mensaje);
            }

            entrada.close();

            socket.close();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void agregarObservador(ObservadorLectura observador) {
		this.observador = observador;
	}

}
