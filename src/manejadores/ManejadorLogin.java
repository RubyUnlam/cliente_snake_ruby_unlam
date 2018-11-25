package manejadores;

import cliente_snake_ruby_unlam.RegistroUsuario;
import cliente_snake_ruby_unlam.Usuario;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ManejadorLogin {

    private DataOutputStream salida;
    private DataInputStream entrada;
    private Gson gson = new Gson();

    public ManejadorLogin(Socket socket) throws IOException {
        this.salida = new DataOutputStream(socket.getOutputStream());
        this.entrada = new DataInputStream(socket.getInputStream());
    }

    public RegistroUsuario enviarUsuario(Usuario usuario) {
        try {
            salida.writeUTF(gson.toJson(usuario));
            return gson.fromJson(entrada.readUTF(), RegistroUsuario.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new RegistroUsuario("Ha ocurrido un error. Intente nuevamente", false);
        }
    }
}
