package cliente_snake_ruby_unlam;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * Clase que representa un elemento dibujable por el campo
 * @author gonrodriguez
 *
 */
public class Dibujable {
	
	private Color color;
	private List<Ubicacion> zonaDeDibujo;

	public Color obtenerColor() {
		return color;
	}

	public List<Ubicacion> obtenerZonaDeDibujo() {
		return zonaDeDibujo;
	}
	
	
}
