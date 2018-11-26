package manejadores;

import cliente_snake_ruby_unlam.RespuestaAccionConSala;
import cliente_snake_ruby_unlam.Sala;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static java.util.Objects.nonNull;

public class ManejadorSalas {

    private DataOutputStream salida;
    private DataInputStream entrada;
    private Gson gson = new Gson();

    public ManejadorSalas(Socket socket) throws IOException {
        this.salida = new DataOutputStream(socket.getOutputStream());
        this.entrada = new DataInputStream(socket.getInputStream());
    }

    public RespuestaAccionConSala pedirSalas() {
        return realizarAccion(null, "ver_salas");
    }

    public RespuestaAccionConSala crearSala(Sala sala){
        return realizarAccion(sala, "crear_sala");
    }

    public RespuestaAccionConSala unirseASala(Sala sala){
        return realizarAccion(sala, "unirse_a_sala");
    }

    private RespuestaAccionConSala realizarAccion(Sala sala, String accion) {
        try {
            salida.writeUTF(accion);
            if (nonNull(sala)) {
                salida.writeUTF(gson.toJson(sala));
            }
            return gson.fromJson(entrada.readUTF(), RespuestaAccionConSala.class);
        } catch (IOException e) {
            e.printStackTrace();
            return obtenerRespuestaDeError();
        }
    }

    private RespuestaAccionConSala obtenerRespuestaDeError() {
        return new RespuestaAccionConSala(false, "Ha ocurrido un error. Intente nuevamente");
    }
}
