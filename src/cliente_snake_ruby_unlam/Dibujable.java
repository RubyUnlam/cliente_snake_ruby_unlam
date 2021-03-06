package cliente_snake_ruby_unlam;

import java.awt.*;
import java.util.List;

/**
 * Clase que representa un elemento dibujable por el campo
 */
public class Dibujable {

    private Color color;
    private List<Ubicacion> zonaDeDibujo;
    private String nombreJugador;
    private Integer puntaje;

    public Color obtenerColor() {
        return color;
    }

    public List<Ubicacion> obtenerZonaDeDibujo() {
        return zonaDeDibujo;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

}
