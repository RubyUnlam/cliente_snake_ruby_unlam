package manejadores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ManejadorMovimientos {

	private DataOutputStream salida;

	public ManejadorMovimientos(Socket socket) throws IOException {
		this.salida = new DataOutputStream(socket.getOutputStream());
	}

	public void enviarMovimiento(String direccion) {
		try {
			salida.writeUTF(direccion);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
