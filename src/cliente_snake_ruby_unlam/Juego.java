package cliente_snake_ruby_unlam;

import manejadores.ManejadorDeJuego;

import javax.swing.JFrame;

import java.util.concurrent.CountDownLatch;

import static utilidades.Constantes.ALTURA_VENTANA;
import static utilidades.Constantes.ANCHO_VENTANA;

public class Juego {

	public static void iniciar(Cliente cliente, Menu menu, CountDownLatch countDownLatch) {

		JFrame ventana = new JFrame("Snake");
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setBounds(0, 0, ANCHO_VENTANA , ALTURA_VENTANA);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);

		Ui ui = new Ui(cliente.obtenerControlador(), ventana, menu);

		ManejadorDeJuego manejadorDeJuego = cliente.obtenerManejadorDeJuego();
		manejadorDeJuego.comunicarInicioDeJuego();
		manejadorDeJuego.agregarObservadorDibujables(ui);

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		manejadorDeJuego.start();
		menu.setVisible(false);
		ventana.setContentPane(ui);
		ventana.setVisible(true);
	}
}
