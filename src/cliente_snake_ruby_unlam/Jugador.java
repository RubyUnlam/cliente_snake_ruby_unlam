package cliente_snake_ruby_unlam;

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
			escritor.enviarAlServidor("arriba");
		} else if (teclaPresionada == keyEventDOWN) {
			escritor.enviarAlServidor("abajo");
		} else if (teclaPresionada == keyEventRIGTH) {
			escritor.enviarAlServidor("derecha");
		} else if (teclaPresionada == keyEventLEFT) {
			escritor.enviarAlServidor("izquierda");
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
