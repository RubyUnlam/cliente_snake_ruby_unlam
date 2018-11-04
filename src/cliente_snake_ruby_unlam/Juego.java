package cliente_snake_ruby_unlam;

import javax.swing.JFrame;

public class Juego {

	public static void main(String[] args) {
		JFrame ventana = new JFrame("Snake");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setBounds(0, 0, 800, 600);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		
		Cliente cliente = new Cliente("localhost", 12000);
		
		Ui ui = new Ui(cliente.obtenerJugador());
		
		cliente.obtenerLector().agregarObservador(ui);
		
        ventana.setContentPane(ui);
		ventana.setVisible(true);
	}
}
