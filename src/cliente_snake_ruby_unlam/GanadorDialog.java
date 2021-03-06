package cliente_snake_ruby_unlam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GanadorDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JButton btnConfirmar;
    private Menu menu;
    public JDialog dialog = this;

    /**
     * Cuadro de dialogo para el inicio de sesion o registro de usuarios.
     *
     * @param
     */
    public GanadorDialog(ActualizacionDelJuego actualizacionDelJuego, Menu menu) {
        this.menu = menu;
        setLocationRelativeTo(menu);
        armarVentanaLogin();
        JLabel lblNombreUsuario = new JLabel(actualizacionDelJuego.obtenerGanador());
        lblNombreUsuario.setBounds(50, 50, 200, 29);
        armarBotonSalir();
        getContentPane().add(lblNombreUsuario);
        getContentPane().add(btnConfirmar);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dialog.setLocationRelativeTo(menu);
            }
        });
    }

    /**
     * Propiedades del JDialog para el login.
     *
     * @param
     */
    private void armarVentanaLogin() {
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setBounds(0, 0, 260, 200);
        setTitle("Ganador");
        setVisible(true);
    }

    /**
     * Arma el boton de inicio de sesion
     */
    private void armarBotonSalir() {
        btnConfirmar = new JButton("Entendido");
        btnConfirmar.setBounds(50, 100, 140, 29);
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.finalizarJuego();
                menu.setVisible(true);
                menu.salirSala();
                dispose();
            }
        });
    }
}
