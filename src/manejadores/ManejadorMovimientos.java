package manejadores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ManejadorMovimientos {

	private Socket socket;

	public ManejadorMovimientos(Socket socket) throws IOException {
		this.socket = socket;
	}

	public void enviarMovimiento(String direccion) {
		DataOutputStream salida = null;
		try {
			salida = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(direccion);
			salida.writeUTF(direccion);
			System.out.println("enviado");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
