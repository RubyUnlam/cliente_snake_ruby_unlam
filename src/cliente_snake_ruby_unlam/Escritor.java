package cliente_snake_ruby_unlam;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Escritor {
	
	private DataOutputStream salida;

	Escritor(Socket socket) throws IOException {
		this.salida = new DataOutputStream(socket.getOutputStream());
	}

	public void enviarAlServidor(String direccion) {
		try {
			salida.writeUTF(direccion);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
