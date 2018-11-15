package Observables;

import cliente_snake_ruby_unlam.Usuario;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Escritor {
	
	private DataOutputStream salida;
	private Gson gson = new Gson();

	public Escritor(Socket socket) throws IOException {
		this.salida = new DataOutputStream(socket.getOutputStream());
	}

	public void enviarRegistro(Usuario usuario) {
		try {
			salida.writeUTF(gson.toJson(usuario));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviarLogin(Usuario usuario) {
		try {
			salida.writeUTF(gson.toJson(usuario));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviarMovimiento(String direccion) {
		try {
			salida.writeUTF(direccion);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
