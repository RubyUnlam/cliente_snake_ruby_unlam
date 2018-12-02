package cliente_snake_ruby_unlam;

import manejadores.ManejadorDeJuego;

import javax.swing.*;

import java.awt.*;
import java.util.concurrent.CountDownLatch;

import static utilidades.Constantes.ALTURA_VENTANA;
import static utilidades.Constantes.ANCHO_VENTANA;
import static utilidades.Constantes.SEGUNDOS_PARA_INICIAR;


public class Juego {
public static Menu menu;
public static JFrame ventana;
public static ManejadorDeJuego manejadorDeJuego;
public static Ui ui;

	public static void iniciar(Cliente cliente, Menu menu, CountDownLatch countDownLatch) {

		Juego.menu = menu;

		ventana = new JFrame("Snake");
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setBounds(0, 0, ANCHO_VENTANA , ALTURA_VENTANA);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);

		ui = new Ui(cliente.obtenerControlador(), ventana, Juego.menu);

		manejadorDeJuego = cliente.obtenerManejadorDeJuego();
		manejadorDeJuego.comunicarInicioDeJuego();
		manejadorDeJuego.agregarObservadorDibujables(ui);

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Juego.menu.mandarMecha(SEGUNDOS_PARA_INICIAR);

	}

	public static void iniciarJuego() {
		manejadorDeJuego.start();
		Juego.menu.setVisible(false);
		ventana.setContentPane(ui);
		ventana.setVisible(true);
	}
}
