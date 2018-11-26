package cliente_snake_ruby_unlam;

import manejadores.ManejadorDeJuego;

import javax.swing.JFrame;

import static utilidades.Constantes.ALTURA_VENTANA;
import static utilidades.Constantes.ANCHO_VENTANA;

public class Juego {

	public static void iniciar(Cliente cliente, ManejadorDeJuego manejadorDeJuego) {

		JFrame ventana = new JFrame("Snake");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setBounds(0, 0, ANCHO_VENTANA, ALTURA_VENTANA);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);

		Ui ui = new Ui(cliente.obtenerControlador());

		manejadorDeJuego.agregarObservadorDibujables(ui);
		manejadorDeJuego.start();

		ventana.setContentPane(ui);
		ventana.setVisible(true);

	}
}
