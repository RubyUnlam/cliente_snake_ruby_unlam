package cliente_snake_ruby_unlam;

import manejadores.ManejadorDeJuego;

import javax.swing.JFrame;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static utilidades.Constantes.ALTURA_VENTANA;
import static utilidades.Constantes.ANCHO_VENTANA;

public class Juego {

	public static void iniciar(Cliente cliente, Menu menu) {

		JFrame ventana = new JFrame("Snake");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setBounds(0, 0, ANCHO_VENTANA, ALTURA_VENTANA);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);

		Ui ui = new Ui(cliente.obtenerControlador(), ventana, menu);

		ManejadorDeJuego manejadorDeJuego = cliente.obtenerManejadorDeJuego();
		manejadorDeJuego.comunicarInicioDeJuego();
		manejadorDeJuego.agregarObservadorDibujables(ui);

		try {
			cliente.getManejadorES().getEntrada().readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}

		manejadorDeJuego.start();
		ventana.setContentPane(ui);
		ventana.setVisible(true);
	}
}
