package cliente_snake_ruby_unlam;

import manejadores.ManejadorActualizacionSala;
import manejadores.ManejadorSalas;
import utilidades.NumerosTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class CreacionSala extends JDialog {

    private static final long serialVersionUID = 3146453246362725770L;

    private static final String PUNTAJE = "Puntaje";
    private static final String SUPERVIVENCIA = "Supervivencia";
    private static final String[] FORMAS_DE_VICTORIA = {PUNTAJE, SUPERVIVENCIA};

    private static final Integer[] CANTIDAD_DE_JUGADORES = {0, 1, 2, 3, 4};

    private JComboBox<Integer> cmbIA;
    private JComboBox<Integer> cmbJugadores;
    private JComboBox<String> cmbVictoria;
    private JSpinner spinner;
    private ManejadorSalas manejadorSalas;
    private ManejadorActualizacionSala manejadorActualizacionSala;
    private NumerosTextField txtPuntajeMax;
    private NumerosTextField txtTiempo;
    private JTextField txtNombreSala;
    private JPasswordField txtContrasenia;
    private JLabel lblInformativo;
    private Menu ventanaMenu;

    /**
     * Create the dialog.
     */
    public CreacionSala(Menu menu, ManejadorSalas manejadorSalas, ManejadorActualizacionSala manejadorActualizacionSala) {
        this.manejadorSalas = manejadorSalas;
        this.manejadorActualizacionSala = manejadorActualizacionSala;
        ventanaMenu = menu;

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 380, 400);
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
        lblTiempo.setBounds(6, 173, 70, 16);

        JLabel lblCondicionFinPartida = new JLabel("Victoria por");
        lblCondicionFinPartida.setBounds(6, 145, 70, 16);

        JLabel lblDificultad = new JLabel("Dificultad");
        lblDificultad.setBounds(233, 117, 69, 16);

        JLabel lblPuntaje = new JLabel("Puntaje a alcanzar");
        lblPuntaje.setBounds(6, 200, 120, 20);

        txtNombreSala = new JTextField();
        txtNombreSala.setBounds(168, 28, 182, 26);
        txtNombreSala.setColumns(10);

        txtTiempo = new NumerosTextField();
        txtTiempo.setColumns(10);
        txtTiempo.setBounds(168, 168, 182, 26);
        txtTiempo.setEnabled(false);

        txtPuntajeMax = new NumerosTextField();
        txtPuntajeMax.setColumns(10);
        txtPuntajeMax.setBounds(168, 200, 182, 26);
        txtPuntajeMax.setEnabled(true);

        JButton btnCrearSala = new JButton("Crear sala");
        btnCrearSala.setBounds(115, 250, 117, 30);
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
        crearComboFormaVictoria();

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
        getContentPane().add(lblCondicionFinPartida);
        getContentPane().add(btnCrearSala);
        getContentPane().add(lblCreacionDeSala);
        getContentPane().add(lblDificultad);
        getContentPane().add(lblPuntaje);
        getContentPane().add(txtNombreSala);
        getContentPane().add(cmbVictoria);
        getContentPane().add(txtTiempo);
        getContentPane().add(txtContrasenia);
        getContentPane().add(lblInformativo);
        getContentPane().add(cmbIA);
        getContentPane().add(cmbJugadores);
        getContentPane().add(spinner);
        getContentPane().add(txtPuntajeMax);

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                menu.setVisible(true);
                e.getWindow().dispose();
            }
        });

        setVisible(true);
    }

    /**
     * Crea y carga el comboBox con las opciones de victoria.
     */
    private void crearComboFormaVictoria() {
        cmbVictoria = new JComboBox<String>();
        cmbVictoria.setModel(new DefaultComboBoxModel<String>(FORMAS_DE_VICTORIA));
        cmbVictoria.setBounds(168, 140, 182, 26);
        cmbVictoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deshabilitarSegunOpcion();
            }
        });
    }

    /**
     * Deshabilita JText de puntaje o tiempo dependiendo del modo de juego elegido.
     */
    private void deshabilitarSegunOpcion() {
        if (cmbVictoria.getSelectedItem().equals(PUNTAJE)) {
            txtTiempo.setEnabled(false);
            txtPuntajeMax.setEnabled(true);
            txtTiempo.setText("");
        } else {
            txtTiempo.setEnabled(true);
            txtPuntajeMax.setEnabled(false);
            txtPuntajeMax.setText("");
        }
    }

    /**
     * Crea el comboBox para seleccionar la cantidad maxima de jugadores.
     */
    private void crearComboJugadores() {
        cmbJugadores = new JComboBox<Integer>();
        cmbJugadores.setModel(new DefaultComboBoxModel<Integer>(Arrays.copyOfRange(CANTIDAD_DE_JUGADORES, 1, 5)));
        cmbJugadores.setBounds(168, 85, 64, 27);
    }

    /**
     * Crea el comboBox para seleccionar la cantidad maxima de IAs.
     */
    private void crearComboIA() {
        cmbIA = new JComboBox<Integer>();
        cmbIA.setModel(new DefaultComboBoxModel<Integer>(Arrays.copyOfRange(CANTIDAD_DE_JUGADORES, 0, 4)));
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
                ventanaMenu.getUsuarioActual(), (int) spinner.getValue(), cmbVictoria.getSelectedItem().toString());

        if (!camposCreacionSalaVacios() && cantidadJugadoresValida() && crearSala(sala) && condicionesDeVictoriaValidas()) {

            agregarCondicionDeVictoria(sala);

            RespuestaAccionConSala respuesta = manejadorSalas.crearSala(sala);

            if (respuesta.esAccionValida()) {
                ventanaMenu.setVisible(true);
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
     * Dado un modo de juego (puntaje o supervivencia) verifica si la condición de fin
     * de ese modo de juego es correcta.
     * @return
     */
    private boolean condicionesDeVictoriaValidas(){
        return PUNTAJE.equals(cmbVictoria.getSelectedItem()) ? esPuntajeValido() : esTiempoValido();
    } //TODO CAMBIAR POR UN SWITCH SI AGREGAMOS MÁS MODOS DE JUEGO

    /**
     * Verfica que el puntaje no este vacio.
     * @return
     */
    private boolean esPuntajeValido() {
        if (txtPuntajeMax.getText().isEmpty()) {
            mostrarMensajeInformativo("El puntaje es obligatorio");
            return false;
        }
        return true;
    }

    /**
     * Verifica que el tiempo ingresado no este vacio.
     * @return
     */
    private boolean esTiempoValido() {
        if (txtTiempo.getText().isEmpty()) {
            mostrarMensajeInformativo("El tiempo es obligatorio");
            return false;
        }
        return true;
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

    /**
     * Verifica si existe ya una sala con el mismo nombre que la que se quiere crear.
     * @param sala
     * @return
     */
    public boolean crearSala(Sala sala) {
        if (!ventanaMenu.getListaSalas().contains(sala)) {
            return true;
        } else {
            mostrarMensajeInformativo("Error al crear sala. Nombre en uso");
            return false;
        }
    }

    /**
     * Agrega las condiciones de victoria dependiendo de cual fue elegida
     * @param sala
     */
    private void agregarCondicionDeVictoria(Sala sala) {
        if (cmbVictoria.getSelectedItem().equals(PUNTAJE)) {
            agregarPuntaje(sala);
        } else {
            agregarTiempo(sala);
        }
    }

    /**
     * Agrega el puntaje maximo a la sala.
     * @param sala
     */
    public void agregarPuntaje(Sala sala) {
        sala.setPuntajeAAlcanzar(Integer.valueOf(txtPuntajeMax.getText()));
    }

    /**
     * Agrega el tiempo a la sala.
     * @param sala
     */
    public void agregarTiempo(Sala sala) {
        sala.setTiempo(Integer.valueOf(txtTiempo.getText()));
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