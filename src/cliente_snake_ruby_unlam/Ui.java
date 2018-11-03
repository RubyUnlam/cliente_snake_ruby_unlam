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
    private List<List<Ubicacion>> ubicacionesSerpientes = new ArrayList<>();
    private List<Ubicacion> ubicacionesComestibles= new ArrayList<>();

    Ui(Jugador jugador) {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(jugador);
    }
    
    @Override
	public void notificarUbicaciones(UbicacionesDTO ubicaciones) {
    	ubicacionesSerpientes.addAll(ubicaciones.getSerpientes());
        ubicacionesComestibles.addAll(ubicaciones.getComestibles());
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
        for (List<Ubicacion> serpiente : ubicacionesSerpientes){
        	g.setColor(Color.BLUE);
        	g.fillOval(serpiente.get(0).getX(), serpiente.get(0).getY(), 20, 20);
        	for (int i = 1; i < serpiente.size(); i++) {
        		g.setColor(Color.BLUE.darker());
        		g.fillOval(serpiente.get(i).getX(), serpiente.get(i).getY(), 20, 20);
        	}
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
