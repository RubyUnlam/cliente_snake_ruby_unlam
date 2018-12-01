package manejadores;

import cliente_snake_ruby_unlam.RegistroUsuario;
import cliente_snake_ruby_unlam.Usuario;
import com.google.gson.Gson;

import java.io.IOException;

public class ManejadorLogin {

    private ManejadorES manejadorES;

    public ManejadorLogin(ManejadorES manejadorES) {
        this.manejadorES = manejadorES;
    }

    public RegistroUsuario enviarUsuario(Usuario usuario, String mensaje) {
        try {
            manejadorES.enviar(mensaje);
            manejadorES.enviar(usuario);
            return manejadorES.escuchar(RegistroUsuario.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new RegistroUsuario("Ha ocurrido un error. Intente nuevamente", false);
        }
    }
}
