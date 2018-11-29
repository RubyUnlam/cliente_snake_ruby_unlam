package manejadores;

import cliente_snake_ruby_unlam.RespuestaAccionConSala;
import cliente_snake_ruby_unlam.Sala;
import com.google.gson.Gson;

import java.io.IOException;

import static java.util.Objects.nonNull;

public class ManejadorSalas {

    private ManejadorES manejadorES;
    private Gson gson = new Gson();

    public ManejadorSalas(ManejadorES manejadorES) {
        this.manejadorES = manejadorES;
    }

    public RespuestaAccionConSala pedirSalas() {
        return realizarAccion(null, "ver_salas");
    }

    public RespuestaAccionConSala crearSala(Sala sala) {
        return realizarAccion(sala, "crear_sala");
    }

    public RespuestaAccionConSala unirseASala(Sala sala) {
        return realizarAccion(sala, "unirse_a_sala");
    }

    public void salirDeSala() {
        try {
            manejadorES.getSalida().writeUTF("salir_de_sala");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RespuestaAccionConSala realizarAccion(Sala sala, String accion) {
        try {
            manejadorES.getSalida().writeUTF(accion);
            if (nonNull(sala)) {
                manejadorES.getSalida().writeUTF(gson.toJson(sala));
            }
            return gson.fromJson(manejadorES.getEntrada().readUTF(), RespuestaAccionConSala.class);
        } catch (IOException e) {
            e.printStackTrace();
            return obtenerRespuestaDeError();
        }
    }

    private RespuestaAccionConSala obtenerRespuestaDeError() {
        return new RespuestaAccionConSala(false, "Ha ocurrido un error. Intente nuevamente");
    }
}
