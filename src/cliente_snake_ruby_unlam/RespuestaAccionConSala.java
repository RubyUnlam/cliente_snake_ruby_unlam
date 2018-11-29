package cliente_snake_ruby_unlam;

import java.util.List;

public class RespuestaAccionConSala {

    private boolean accionValida;
    private List<Sala> listaSalas;
    private String mensaje;

    public RespuestaAccionConSala(boolean accionValida, List<Sala> salas) {
        this.accionValida = accionValida;
        listaSalas = salas;
    }


    public RespuestaAccionConSala(boolean accionValida, String mensaje) {
        this.accionValida = accionValida;
        this.mensaje = mensaje;
    }

    public boolean esAccionValida() {
        return accionValida;
    }

    public List<Sala> getListaSalas() {
        return listaSalas;
    }

    public String getMensaje() {
        return mensaje;
    }
}
