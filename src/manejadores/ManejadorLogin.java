package manejadores;

import cliente_snake_ruby_unlam.RegistroUsuario;
import cliente_snake_ruby_unlam.Usuario;
import com.google.gson.Gson;

import java.io.IOException;

public class ManejadorLogin {

    private ManejadorES manejadorES;
    private Gson gson = new Gson();

    public ManejadorLogin(ManejadorES manejadorES) {
        this.manejadorES = manejadorES;
    }

    public RegistroUsuario enviarUsuario(Usuario usuario) {
        try {
            manejadorES.getSalida().writeUTF(gson.toJson(usuario));
            RegistroUsuario registroUsuario = gson.fromJson(manejadorES.getEntrada().readUTF(), RegistroUsuario.class);
            return registroUsuario;
        } catch (IOException e) {
            e.printStackTrace();
            return new RegistroUsuario("Ha ocurrido un error. Intente nuevamente", false);
        }
    }
}
