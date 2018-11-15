package cliente_snake_ruby_unlam;

import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static utilidades.Constantes.ALTURA_VENTANA;
import static utilidades.Constantes.ANCHO_VENTANA;

public class Juego {

//	public static void main(String[] args) {
//		JFrame ventana = new JFrame("Snake");
//		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		ventana.setBounds(0, 0, 800, 600);
//		ventana.setResizable(false);
//		ventana.setLocationRelativeTo(null);
//
//		Cliente cliente = new Cliente("192.168.0.8", 12000);
//
//		Ui ui = new Ui(cliente.obtenerJugador());
//
//		cliente.obtenerLector().agregarObservador(ui);
//
//        ventana.setContentPane(ui);
//		ventana.setVisible(true);
//	}

	public static void iniciar(Sala sala, Cliente cliente) {

		JFrame ventana = new JFrame("Snake");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setBounds(0, 0, ANCHO_VENTANA, ALTURA_VENTANA);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);

//		List<Serpiente> serpientes = new ArrayList<>();
//
//		for (int i = 0; i < sala.getCantidadJugadores(); i++) {
//			serpientes.add(new Serpiente(Color.BLUE));
//		}
//
//		List<SerpienteIA> serpientesIA = new ArrayList<>();
//		for (int i = 0; i < sala.getCantidadIA(); i++) {
//			serpientesIA.add(new SerpienteIA(sala.getDificultadIA(), Color.BLACK));
//		}

		Ui ui = new Ui(cliente.obtenerJugador());

//		Campo campo = new Campo(serpientes, serpientesIA);

//		campo.agregarObservador(ui);
		ventana.setContentPane(ui);
		ventana.setVisible(true);

	}
}
