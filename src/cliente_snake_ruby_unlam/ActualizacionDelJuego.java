package cliente_snake_ruby_unlam;

import java.util.List;

public class  ActualizacionDelJuego {

    private boolean terminado;
    private List<Dibujable> dibujables;
    private String ganador;

    public boolean terminoElJuego() {
        return terminado;
    }

    public List<Dibujable> obtenerDibujables() {
        return dibujables;
    }

    public String obtenerGanador() {
        return ganador;
    }
}
