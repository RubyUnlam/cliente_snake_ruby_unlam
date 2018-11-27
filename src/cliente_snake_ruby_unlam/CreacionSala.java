package cliente_snake_ruby_unlam;

import manejadores.ManejadorActualizacionSala;
import manejadores.ManejadorSalas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class CreacionSala extends JDialog {

    private static final long serialVersionUID = 3146453246362725770L;
    public static final Integer[] CANTIDAD_DE_JUGADORES = {1, 2, 3, 4};

    private Menu ventanaMenu;

    private JTextField txtNombreSala;
    private JTextField txtMapa;
    private JTextField txtTiempo;
    private JPasswordField txtContrasenia;
    private JLabel lblInformativo;
    private JComboBox<Integer> cmbIA;
    private JComboBox<Integer> cmbJugadores;
    private JSpinner spinner;
    private ManejadorSalas manejadorSalas;
    private ManejadorActualizacionSala manejadorActualizacionSala;

    /**
     * Create the dialog.
     */
    public CreacionSala(Menu menu, ManejadorSalas manejadorSalas, ManejadorActualizacionSala manejadorActualizacionSala) {
        this.manejadorSalas = manejadorSalas;
        this.manejadorActualizacionSala = manejadorActualizacionSala;
        ventanaMenu = menu;

        setBounds(100, 100, 380, 300);
        setLocationRelativeTo(menu);

        JLabel lblNombreSala = new JLabel("Nombre de la sala");
        lblNombreSala.setBounds(6, 33, 135, 16);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(6, 61, 61, 16);

        JLabel lblCantidadDeJugadores = new JLabel("Cantidad de Jugadores");
        lblCantidadDeJugadores.setBounds(6, 89, 150, 16);

        JLabel lblCantidadDeIa = new JLabel("Cantidad de IA");
        lblCantidadDeIa.setBounds(6, 117, 111, 16);

        JLabel lblTiempo = new JLabel("Tiempo");
        lblTiempo.setBounds(6, 145, 61, 16);

        JLabel lblMapa = new JLabel("Mapa");
        lblMapa.setBounds(6, 173, 61, 16);

        JLabel lblDificultad = new JLabel("Dificultad");
        lblDificultad.setBounds(233, 117, 69, 16);

        txtNombreSala = new JTextField();
        txtNombreSala.setBounds(168, 28, 182, 26);
        txtNombreSala.setColumns(10);

        txtMapa = new JTextField();
        txtMapa.setColumns(10);
        txtMapa.setBounds(168, 168, 182, 26);
        txtMapa.setEnabled(false);

        txtTiempo = new JTextField();
        txtTiempo.setColumns(10);
        txtTiempo.setBounds(168, 140, 182, 26);
        txtTiempo.setEnabled(false);

        JButton btnCrearSala = new JButton("Crear sala");
        btnCrearSala.setBounds(115, 202, 117, 30);
        btnCrearSala.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearSala();
            }
        });

        JLabel lblCreacionDeSala = new JLabel("Creacion de sala");
        lblCreacionDeSala.setHorizontalAlignment(SwingConstants.CENTER);
        lblCreacionDeSala.setBounds(6, 5, 344, 16);

        txtContrasenia = new JPasswordField();
        txtContrasenia.setBounds(168, 56, 182, 26);

        lblInformativo = new JLabel("");
        lblInformativo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInformativo.setBounds(6, 14, 344, 16);


        crearComboJugadores();
        crearComboIA();

        spinner = new JSpinner();
        spinner.setBounds(296, 112, 54, 26);
        SpinnerModel spinnerModel = new SpinnerNumberModel(50, 0, 100, 5);
        spinner.setModel(spinnerModel);

        getContentPane().setLayout(null);
        getContentPane().add(lblNombreSala);
        getContentPane().add(lblPassword);
        getContentPane().add(lblCantidadDeJugadores);
        getContentPane().add(lblCantidadDeIa);
        getContentPane().add(lblTiempo);
        getContentPane().add(lblMapa);
        getContentPane().add(btnCrearSala);
        getContentPane().add(lblCreacionDeSala);
        getContentPane().add(lblDificultad);
        getContentPane().add(txtNombreSala);
        getContentPane().add(txtMapa);
        getContentPane().add(txtTiempo);
        getContentPane().add(txtContrasenia);
        getContentPane().add(lblInformativo);
        getContentPane().add(cmbIA);
        getContentPane().add(cmbJugadores);
        getContentPane().add(spinner);

        setVisible(true);
    }


    private void crearComboJugadores() {
        cmbJugadores = new JComboBox<Integer>();
        cmbJugadores.setModel(new DefaultComboBoxModel<Integer>(CANTIDAD_DE_JUGADORES));
        cmbJugadores.setBounds(168, 85, 64, 27);

        //Reduce la cantidad de IA que se puede elegir al seleccionar cantidad de usuarios
//		cmbJugadores.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if ("comboBoxChanged".equals(e.getActionCommand())) {
//					Integer opcionSeleccionada = (Integer) cmbJugadores.getSelectedItem();
//					if (nonNull(opcionSeleccionada) && opcionSeleccionada != 4){
//                        cmbIA.setModel(new DefaultComboBoxModel<Integer>(Arrays.copyOfRange(CANTIDAD_DE_JUGADORES, 0, 4 - opcionSeleccionada)));
//                    } else {
//					    cmbIA.setModel(new DefaultComboBoxModel<Integer>(new Integer[]{0}));
//                    }
//
//				}
//			}
//		});
    }

    private void crearComboIA() {
        cmbIA = new JComboBox<Integer>();
        cmbIA.setModel(new DefaultComboBoxModel<Integer>(Arrays.copyOfRange(CANTIDAD_DE_JUGADORES, 0, 3)));
        cmbIA.setBounds(168, 113, 64, 27);
    }

    /**
     * Inicia la validacion del usuario y la contrasenia, y llama al proceso de
     * creacion de sala.
     */
    private void crearSala() {
        String password = String.valueOf(txtContrasenia.getPassword());
        Sala sala = new Sala(txtNombreSala.getText(), password,
                (int) cmbJugadores.getSelectedItem(), (int) cmbIA.getSelectedItem(),
                ventanaMenu.getUsuarioActual(), (int) spinner.getValue());
        if (!camposCreacionSalaVacios() && cantidadJugadoresValida() && crearSala(sala)) {
            RespuestaAccionConSala respuesta = manejadorSalas.crearSala(sala);

            if(respuesta.esAccionValida()){
                dispose();
                ventanaMenu.crearMiSala(respuesta.getListaSalas());
                manejadorActualizacionSala.start();
                ventanaMenu.habilitarInteraccionSalas(false);
            } else {
                mostrarMensajeInformativo(respuesta.getMensaje());
            }
        }
    }

    /**
     * Valida que los campos de nombre de sala, contrasenia, y cantidad de jugadores
     * no esten vacios.
     * @return
     */
    private boolean camposCreacionSalaVacios() {
        if (txtNombreSala.getText().isEmpty()) {
            mostrarMensajeInformativo("Nombre de sala es obligatorio");
            return true;
        }
        // TODO: validacion de tiempo y mapas cuando se agreguen.
        return false;
    }

    /**
     * Valida que la cantidad maxima de jugadres sea valida
     * @return
     */
    private boolean cantidadJugadoresValida() {
        int cantidadTotalJugadores = (int) cmbIA.getSelectedItem() + (int) cmbJugadores.getSelectedItem();
        if (cantidadTotalJugadores > 4) {
            mostrarMensajeInformativo("Cantidad de jugadores mayor a 4");
            return false;
        }
        return true;
    }

    public boolean crearSala(Sala sala) {
        if (!ventanaMenu.getListaSalas().contains(sala)) {
            return true;
        } else {
            mostrarMensajeInformativo("Error al crear sala. Nombre en uso");
            return false;
        }
    }

    /**
     * Muestra mensaje de error o informativo.
     * @param mensaje
     */
    public void mostrarMensajeInformativo(String mensaje) {
        lblInformativo.setForeground(Color.RED);
        lblInformativo.setText(mensaje);
        lblInformativo.setVisible(true);
    }
}