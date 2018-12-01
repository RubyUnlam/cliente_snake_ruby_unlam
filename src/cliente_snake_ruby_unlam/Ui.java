package cliente_snake_ruby_unlam;

import observables.ObservadorDibujables;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.util.Objects.nonNull;
import static utilidades.Constantes.*;

public class Ui extends JPanel implements ObservadorDibujables {

    private static final long serialVersionUID = 1L;
    public static final String CLASICO = "Clasico";
    private ImageIcon fondo;
    private Queue<Dibujable> aDibujar = new ConcurrentLinkedQueue<Dibujable>();
    private String ganador;
    private JFrame ventana;
    private boolean terminoElJuego;
    private ActualizacionDelJuego actualizacionDelJuego;
    private Menu menu;

    Ui(Controlador controlador, JFrame ventana, Menu menu, String mapa) {
        this.ventana = ventana;
        this.menu = menu;
        addKeyListener(controlador);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.fondo = inicializarFondo(mapa);
    }

    /**
     * Inicializa el fondo con el mapa recibido o el clasico.
     *
     * @param mapa
     * @return
     */
    private ImageIcon inicializarFondo(String mapa) {
        String mapaFinal = nonNull(mapa) ? mapa : CLASICO;
        return new ImageIcon(String.format(FONDO_PATH, mapaFinal));
    }

    @Override
    public void notificarUbicaciones(ActualizacionDelJuego actualizacion) {
        if (actualizacion.quiereSalir()) {
            ventana.setVisible(false);
            menu.salirSala();
            menu.setVisible(true);
        } else {
            this.actualizacionDelJuego = actualizacion;
            aDibujar.addAll(actualizacion.obtenerDibujables());
            ganador = actualizacion.obtenerGanador();
            repaint();
        }
    }

    public void paint(Graphics g) {
        fondo.paintIcon(this, g, 0, 0);
        if (!aDibujar.isEmpty()) {
            dibujar(g);
        }
        g.dispose();
        if (terminoElJuego) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ventana.setVisible(false);
            new GanadorDialog(actualizacionDelJuego, menu);
        }
    }

    /**
     * Dibuja un circulo de 20x20 por cada dibujable en la lista
     * aDibujar y luego limpia la lista
     */
    private void dibujar(Graphics g) {
        int alturaNombreN = ALTURA_INICIAL_TEXTO;
        for (Dibujable dibujable : aDibujar) {
            Color colorActual = dibujable.obtenerColor();
            dibujarPuntaje(g, alturaNombreN, dibujable, colorActual);
            dibujarUbicaciones(g, dibujable, colorActual);
            alturaNombreN += ALTURA_INICIAL_TEXTO + ALTURA_PUNTACION;
        }
        aDibujar.clear();

        if (!"".equals(ganador)) {
            terminoElJuego = true;
        }
    }

    /**
     * Dado un dibujable y su color dibuja todas las ubicaciones que tenga.
     *
     * @param g
     * @param dibujable
     * @param colorActual
     */
    private void dibujarUbicaciones(Graphics g, Dibujable dibujable, Color colorActual) {
        List<Ubicacion> ubicaciones = dibujable.obtenerZonaDeDibujo();
        if (!ubicaciones.isEmpty()) {
            g.setColor(colorActual);
            g.fillOval(ubicaciones.get(0).getX(), ubicaciones.get(0).getY(), 20, 20);
            for (int i = 1; i < ubicaciones.size(); i++) {
                g.setColor(colorActual.darker());
                g.fillOval(ubicaciones.get(i).getX(), ubicaciones.get(i).getY(), 20, 20);
            }
        }
    }

    /**
     * Dado un dibujable, si tiene un nombre dibuja ese nombre junto con su puntaje.
     * Chequea si tiene nombre porque el dibujable podría ser un comestible y este no tiene ni
     * nombre ni puntaje
     *
     * @param g
     * @param alturaNombreN
     * @param dibujable
     * @param colorActual
     */
    private void dibujarPuntaje(Graphics g, int alturaNombreN, Dibujable dibujable, Color colorActual) {
        String nombreJugador = dibujable.getNombreJugador();
        if (nonNull(nombreJugador) && !nombreJugador.isEmpty()) {
            g.setColor(colorActual);
            g.setFont(new Font(FUENTE, Font.BOLD, TAMANIO_FUENTE));
            g.drawString(nombreJugador, SANGRIA, alturaNombreN);
            g.setColor(colorActual.darker());
            g.drawString(dibujable.getPuntaje().toString(), SANGRIA, alturaNombreN + ALTURA_PUNTACION);
        }
    }

}
