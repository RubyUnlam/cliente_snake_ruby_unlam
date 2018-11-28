package cliente_snake_ruby_unlam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GanadorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
    private JButton btnIniciarSesion;
    private Menu menu;

	/**
	 * Cuadro de dialogo para el inicio de sesion o registro de usuarios.
	 * @param
	 */
	public GanadorDialog(ActualizacionDelJuego actualizacionDelJuego, Menu menu) {
	    this.menu = menu;
        armarVentanaLogin();
        JLabel lblNombreUsuario = new JLabel("El ganador es " + actualizacionDelJuego.obtenerGanador());
        lblNombreUsuario.setBounds(340 / 2, 340 / 4, 140, 29);
        armarBotonSalir();
        getContentPane().add(lblNombreUsuario);
        getContentPane().add(btnIniciarSesion);
	}

    /**
     * Propiedades del JDialog para el login.
     * @param
     */
    private void armarVentanaLogin() {
        getContentPane().setLayout(null);
        setBounds(0, 0, 340, 200);
        setTitle("Ganador");
        setVisible(true);
    }

    /**
     * Arma el boton de inicio de sesion
     */
    private void armarBotonSalir() {
        btnIniciarSesion = new JButton("Entendido");
        btnIniciarSesion.setBounds(340 / 2, 200 - (200 / 4), 140, 29);
        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.finalizarJuego();
                menu.salirSala();
                dispose();
            }
        });
    }
}
