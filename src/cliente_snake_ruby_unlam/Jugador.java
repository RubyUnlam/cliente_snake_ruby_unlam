package cliente_snake_ruby_unlam;

import Observables.Escritor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Jugador implements KeyListener {

	private int id;
	private Escritor escritor;
	
	private int keyEventUP = KeyEvent.VK_UP;
	private int keyEventDOWN = KeyEvent.VK_DOWN;
	private int keyEventRIGTH = KeyEvent.VK_RIGHT;
	private int keyEventLEFT = KeyEvent.VK_LEFT;
	
	public Jugador(int id, Escritor escritor) {
		this.id = id;
		this.escritor = escritor;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int teclaPresionada = e.getKeyCode();
		if (teclaPresionada == keyEventUP) {
			escritor.enviarMovimiento("arriba");
		} else if (teclaPresionada == keyEventDOWN) {
			escritor.enviarMovimiento("abajo");
		} else if (teclaPresionada == keyEventRIGTH) {
			escritor.enviarMovimiento("derecha");
		} else if (teclaPresionada == keyEventLEFT) {
			escritor.enviarMovimiento("izquierda");
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
