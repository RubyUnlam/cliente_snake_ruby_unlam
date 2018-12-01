package cliente_snake_ruby_unlam;

import manejadores.ManejadorMovimientos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controlador implements KeyListener {

	private ManejadorMovimientos manejadorMovimientos;
	
	private int keyEventUP = KeyEvent.VK_UP;
	private int keyEventDOWN = KeyEvent.VK_DOWN;
	private int keyEventRIGTH = KeyEvent.VK_RIGHT;
	private int keyEventLEFT = KeyEvent.VK_LEFT;
	
	public Controlador(ManejadorMovimientos manejadorMovimientos) {
		this.manejadorMovimientos = manejadorMovimientos;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int teclaPresionada = e.getKeyCode();
		if (teclaPresionada == keyEventUP) {
			manejadorMovimientos.enviarMovimiento("arriba");
		} else if (teclaPresionada == keyEventDOWN) {
			manejadorMovimientos.enviarMovimiento("abajo");
		} else if (teclaPresionada == keyEventRIGTH) {
			manejadorMovimientos.enviarMovimiento("derecha");
		} else if (teclaPresionada == keyEventLEFT) {
			manejadorMovimientos.enviarMovimiento("izquierda");
		} else if (teclaPresionada == KeyEvent.VK_ESCAPE) {
			manejadorMovimientos.enviarMovimiento("finalizar");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
