package cliente_snake_ruby_unlam;

import manejadores.ManejadorActualizacionSala;
import manejadores.ManejadorSalas;
import utilidades.NumerosTextField;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class CreacionSala extends JDialog {

    private static final long serialVersionUID = 3146453246362725770L;

    private static final String PUNTAJE = "Puntaje";
    private static final String SUPERVIVENCIA = "Supervivencia";
    private static final String CARNICERIA = "CarnicerIA";
    private static final String[] FORMAS_DE_VICTORIA = {PUNTAJE, SUPERVIVENCIA, CARNICERIA};
    private static final String[] MAPAS = {"Clasico", "Pradera", "Desierto", "Oceano"};
    private static final Integer[] CANTIDAD_DE_JUGADORES = {0, 1, 2, 3, 4};

    private JComboBox<Integer> cmbIA;
    private JComboBox<Integer> cmbJugadores;
    private JComboBox<String> cmbVictoria;
    private JComboBox<String> cmbMapas;
    private JSpinner spinner;
    private ManejadorSalas manejadorSalas;
    private ManejadorActualizacionSala manejadorActualizacionSala;
    private NumerosTextField txtPuntajeMax;
    private NumerosTextField txtTiempo;
    private JTextField txtNombreSala;
    private JPasswordField txtContrasenia;
    private JLabel lblInformativo;
    private Menu ventanaMenu;
    private JLabel lblNombreSala;
    private JLabel lblPassword;
    private JLabel lblCantidadDeJugadores;
    private JLabel lblCantidadDeIa;
    private JLabel lblTiempo;
    private JLabel lblCondicionFinPartida;
    private JLabel lblCreacionDeSala;
    private JLabel lblDificultad;
    private JLabel lblPuntaje;
    private JLabel lblMapa;
    private JButton btnCrearSala;

    /**
     * Creacion del JDialog de la creacion de salas.
     */
    public CreacionSala(Menu menu, ManejadorSalas manejadorSalas, ManejadorActualizacionSala manejadorActualizacionSala) {
        ventanaMenu = menu;
        this.manejadorSalas = manejadorSalas;
        this.manejadorActualizacionSala = manejadorActualizacionSala;
        inicializarVentanaCreacionSala(menu);
        crearDeLabels(6);
        crearTextFields(168);
        crearComboJugadores();
        crearComboIA();
        crearComboMapas();
        crearComboFormaVictoria();
        agregarDificultadIA();
        crearBotones();
        agregarComponentesAlPanel();
        setVisible(true);
    }

    /**
     * Creacion de los botones de la ventana.
     */
    private void crearBotones() {
        lblDificultad = new JLabel("Dificultad");
        lblDificultad.setBounds(233, 117, 69, 16);

        btnCrearSala = new JButton("Crear sala");
        btnCrearSala.setBounds(115, 260, 117, 30);
        btnCrearSala.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearSala();
            }
        });
    }

    /**
     * Creacion de la ventana de creacion de salas.
     */
    private void inicializarVentanaCreacionSala(Menu menu) {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 380, 400);
        setLocationRelativeTo(menu);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                menu.setVisible(true);
                e.getWindow().dispose();
            }
        });
    }

    /**
     * Creacion de todos los labels a la misma distancia del borde de la ventana.
     * @param x Distancia al borde de la ventana.
     */
    private void crearDeLabels(int x) {
        lblNombreSala = new JLabel("Nombre de la sala");
        lblNombreSala.setBounds(x, 33, 135, 16);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(x, 61, 61, 16);

        lblCantidadDeJugadores = new JLabel("Cantidad de Jugadores");
        lblCantidadDeJugadores.setBounds(x, 89, 150, 16);

        lblCantidadDeIa = new JLabel("Cantidad de IA");
        lblCantidadDeIa.setBounds(x, 117, 111, 16);

        lblTiempo = new JLabel("Tiempo (en segundos)");
        lblTiempo.setBounds(x, 173, 150, 16);

        lblMapa = new JLabel("Mapa");
        lblMapa.setBounds(x, 230, 150, 16);

        lblCondicionFinPartida = new JLabel("Victoria por");
        lblCondicionFinPartida.setBounds(x, 145, 70, 16);

        lblPuntaje = new JLabel("Puntaje a alcanzar");
        lblPuntaje.setBounds(x, 200, 120, 20);

        lblCreacionDeSala = new JLabel("Creacion de sala");
        lblCreacionDeSala.setHorizontalAlignment(SwingConstants.CENTER);
        lblCreacionDeSala.setBounds(x, 5, 344, 16);

        lblInformativo = new JLabel("");
        lblInformativo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInformativo.setBounds(x, 14, 344, 16);
    }

    /**
     * Crea TextFields a la misma distancia del borde de la ventana.
     * @param x Distancia al borde de la ventana.
     */
    private void crearTextFields(int x) {
        txtNombreSala = new JTextField();
        txtNombreSala.setBounds(x, 28, 182, 26);
        txtNombreSala.setColumns(10);

        txtContrasenia = new JPasswordField();
        txtContrasenia.setBounds(x, 56, 182, 26);

        txtTiempo = new NumerosTextField();
        txtTiempo.setColumns(10);
        txtTiempo.setBounds(x, 168, 182, 26);
        txtTiempo.setEnabled(false);

        txtPuntajeMax = new NumerosTextField();
        txtPuntajeMax.setColumns(10);
        txtPuntajeMax.setBounds(x, 200, 182, 26);
        txtPuntajeMax.setEnabled(true);
    }

    /**
     * Agrega spinner para la dificultad de la IA
     */
    private void agregarDificultadIA() {
        spinner = new JSpinner();
        spinner.setBounds(296, 112, 54, 26);
        SpinnerModel spinnerModel = new SpinnerNumberModel(50, 0, 100, 5);
        spinner.setModel(spinnerModel);
    }

    /**
     * Agrega al panel todos los componentes creados.
     */
    private void agregarComponentesAlPanel() {
        getContentPane().setLayout(null);
        getContentPane().add(lblNombreSala);
        getContentPane().add(lblPassword);
        getContentPane().add(lblCantidadDeJugadores);
        getContentPane().add(lblCantidadDeIa);
        getContentPane().add(lblTiempo);
        getContentPane().add(lblCondicionFinPartida);
        getContentPane().add(lblCreacionDeSala);
        getContentPane().add(lblDificultad);
        getContentPane().add(lblPuntaje);
        getContentPane().add(lblInformativo);
        getContentPane().add(btnCrearSala);
        getContentPane().add(txtNombreSala);
        getContentPane().add(txtTiempo);
        getContentPane().add(txtContrasenia);
        getContentPane().add(cmbVictoria);
        getContentPane().add(cmbIA);
        getContentPane().add(cmbJugadores);
        getContentPane().add(cmbMapas);
        getContentPane().add(lblMapa);
        getContentPane().add(txtPuntajeMax);
        getContentPane().add(spinner);
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
        Object tipoDeJuego = cmbVictoria.getSelectedItem();
        cambiarModelAlCombo(cmbJugadores, 1, 5);
        cambiarModelAlCombo(cmbIA, 0, 4);
        if (PUNTAJE.equals(tipoDeJuego)) {
            txtTiempo.setEnabled(false);
            txtPuntajeMax.setEnabled(true);
            txtTiempo.setText("");
        } else if (SUPERVIVENCIA.equals(tipoDeJuego)) {
            txtTiempo.setEnabled(true);
            txtPuntajeMax.setEnabled(false);
            txtPuntajeMax.setText("");
        } else if (CARNICERIA.equals(tipoDeJuego)){
            txtTiempo.setEnabled(false);
            txtPuntajeMax.setEnabled(false);
            txtPuntajeMax.setText("");
            txtTiempo.setText("");
            cambiarModelAlCombo(cmbJugadores, 0, 1);
            cambiarModelAlCombo(cmbIA, 4, 5);
        }
    }

    /**
     * Crea el comboBox para seleccionar la cantidad maxima de jugadores.
     */
    private void crearComboJugadores() {
        cmbJugadores = new JComboBox<Integer>();
        cmbJugadores.setBounds(168, 85, 64, 27);
        cambiarModelAlCombo(cmbJugadores, 1, 5);
    }

    /**
     * Crea el comboBox para seleccionar la cantidad maxima de jugadores.
     */
    private void crearComboMapas() {
        cmbMapas = new JComboBox<String>();
        cmbMapas.setBounds(168, 230, 182, 27);
        cmbMapas.setModel(new DefaultComboBoxModel<String>(MAPAS));
    }

    /**
     * Crea el comboBox para seleccionar la cantidad maxima de IAs.
     */
    private void crearComboIA() {
        cmbIA = new JComboBox<Integer>();
        cmbIA.setBounds(168, 113, 64, 27);
        cambiarModelAlCombo(cmbIA, 0, 4);
    }

    /**
     * Dado un combo le setea como modelo el rango especificado de la constante de jugadores
     * @param combo
     * @param inicio
     * @param fin
     */
    private void cambiarModelAlCombo(JComboBox<Integer> combo, int inicio, int fin) {
        combo.setModel(new DefaultComboBoxModel<Integer>(Arrays.copyOfRange(CANTIDAD_DE_JUGADORES, inicio, fin)));

    }

    /**
     * Inicia la validacion del usuario y la contrasenia, y llama al proceso de
     * creacion de sala.
     */
    private void crearSala() {
        String password = String.valueOf(txtContrasenia.getPassword());
        Sala sala = new Sala(txtNombreSala.getText(), password,
                (int) cmbJugadores.getSelectedItem(), (int) cmbIA.getSelectedItem(),
                ventanaMenu.getUsuarioActual(), (int) spinner.getValue(), cmbVictoria.getSelectedItem().toString(),
                (String) cmbMapas.getSelectedItem());

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
        Object selectedItem = cmbVictoria.getSelectedItem();
        if (CARNICERIA.equals(selectedItem)) {
            return true;
        }
        return PUNTAJE.equals(selectedItem) ? esPuntajeValido() : esTiempoValido();
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
        Object selectedItem = cmbVictoria.getSelectedItem();
        if (PUNTAJE.equals(selectedItem)) {
            agregarPuntaje(sala);
        } else if (SUPERVIVENCIA.equals(selectedItem) ){
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