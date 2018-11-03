package cliente_snake_ruby_unlam;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Ui extends JPanel implements ObservadorLectura {

    private ImageIcon fondoDefault;
    private String fondoPath = "src/imagenes/fondo.png"; //TODO HACERLO VARIABLE
    private List<Ubicacion> ubicacionesSerpientes = new ArrayList<>();
    private List<Ubicacion> ubicacionesComestibles= new ArrayList<>();

    Ui(Jugador jugador) {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(jugador);
    }
    
    @Override
	public void notificarUbicaciones(List<Ubicacion> ubicaciones) {
	    
    	ubicacionesSerpientes.addAll(ubicaciones);
//        ubicacionesComestibles.addAll(ubicaciones);
        repaint();
	}

    public void paint(Graphics g){
        fondoDefault = new ImageIcon(fondoPath);
        fondoDefault.paintIcon(this, g, 0, 0);
        if (!ubicacionesSerpientes.isEmpty()) {
            pintarSerpientes(g);
        }
        if (!ubicacionesComestibles.isEmpty()) {
            pintarComestibles(g);
        }
        g.dispose();
    }

    private void pintarSerpientes(Graphics g) {
        g.setColor(Color.BLUE); //TODO VER SI USAMOS IMAGENES EN VEZ DE CIRCULOS
        for (Ubicacion serpiente : ubicacionesSerpientes){
            g.fillOval(serpiente.getX(), serpiente.getY(), 20, 20);
        }
        ubicacionesSerpientes.clear();
    }

    private void pintarComestibles(Graphics g) {
        g.setColor(Color.RED); //TODO VER SI USAMOS IMAGENES EN VEZ DE CIRCULOS
        for (Ubicacion comestible : ubicacionesComestibles) {
            g.fillOval(comestible.getX(), comestible.getY(), 20, 20);
        }
        ubicacionesComestibles.clear();
    }

}
