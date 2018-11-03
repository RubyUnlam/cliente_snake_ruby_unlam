package cliente_snake_ruby_unlam;

import java.util.List;

import javax.swing.JFrame;

public class Juego {

    public static long initTime;

	public static void main(String[] args) {
		JFrame ventana = new JFrame("Snake");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setBounds(0, 0, 800, 600);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		
		Jugador jugador = new Cliente("123", 1213).obtenerJugador();
		
		Ui ui = new Ui(jugador);
		
        ventana.setContentPane(ui); //TODO NO CREAR SERPIENTE A LO CABEZA
		ventana.setVisible(true);
	}
}
