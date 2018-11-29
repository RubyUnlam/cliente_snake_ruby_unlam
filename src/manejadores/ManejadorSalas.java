package manejadores;

import cliente_snake_ruby_unlam.RespuestaAccionConSala;
import cliente_snake_ruby_unlam.Sala;

import java.io.IOException;

import static java.util.Objects.nonNull;

public class ManejadorSalas {

    private final String VER_SALAS = "ver_salas";
    private final String CREAR_SALA = "crear_sala";
    private final String UNIRSE_A_SALA = "unirse_a_sala";
    private final String SALIR_DE_SALA = "salir_de_sala";
    private ManejadorES manejadorES;

    public ManejadorSalas(ManejadorES manejadorES) {
        this.manejadorES = manejadorES;
    }

    public RespuestaAccionConSala pedirSalas() {
        return realizarAccion(null, VER_SALAS);
    }

    public RespuestaAccionConSala crearSala(Sala sala) {
        return realizarAccion(sala, CREAR_SALA);
    }

    public RespuestaAccionConSala unirseASala(Sala sala) {
        return realizarAccion(sala, UNIRSE_A_SALA);
    }

    public void salirDeSala() {
        try {
            manejadorES.enviarString(SALIR_DE_SALA);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RespuestaAccionConSala realizarAccion(Sala sala, String accion) {
        try {
            manejadorES.enviarString(accion);
            if (nonNull(sala)) {
                manejadorES.enviar(sala);
            }
            return manejadorES.escuchar(RespuestaAccionConSala.class);
        } catch (IOException e) {
            e.printStackTrace();
            return obtenerRespuestaDeError();
        }
    }

    private RespuestaAccionConSala obtenerRespuestaDeError() {
        return new RespuestaAccionConSala(false, "Ha ocusarrido un error. Intente nuevamente");
    }
}
