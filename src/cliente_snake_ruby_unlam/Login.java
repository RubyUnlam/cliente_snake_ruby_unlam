package cliente_snake_ruby_unlam;

import manejadores.ManejadorLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombreUsuario;
	private JPasswordField txtContrasenia;
	private JLabel lblErrorRegistro;
	private JButton btnIniciarSesion;
	private Menu ventanaMenu;
	private boolean loggeado = false;
	Login login = this;
	private RegistroUsuario respuesta = new RegistroUsuario("", false);;
	private ManejadorLogin manejador;

	/**
	 * Cuadro de dialogo para el inicio de sesion o registro de usuarios. 
	 * @param menu para poder acceder a los componentes del JFrame principal.
	 */
	public Login(Menu menu, ManejadorLogin manejador) {
		this.manejador = manejador;

        ventanaMenu = menu;
        armarVentanaLogin(menu);
        armarTextFieldNombreDeUsuario();
        armarJPasswordFieldContrasenia();
        JButton btnRegistrarse = armarBotonDeRegistro();
        armarBotonInicioDeSesion();
        JLabel lblNombreUsuario = armarLabels("Nombre de Usuario", 52, 140);
        JLabel lblContrasenia = armarLabels("Password", 79, 117);
        JLabel lblLogin = armarLabelLogin();
        armarLabelDeErrores();
        agregarComponentes(btnRegistrarse, lblNombreUsuario, lblContrasenia, lblLogin);

        iniciarSesionConEnter();
		
	}

    /**
     * Agregando componentes al panel.
     * @param btnRegistrarse
     * @param lblNombreUsuario
     * @param lblContrasenia
     * @param lblLogin
     */
    private void agregarComponentes(JButton btnRegistrarse, JLabel lblNombreUsuario, JLabel lblContrasenia, JLabel lblLogin) {
        getContentPane().add(txtNombreUsuario);
        getContentPane().add(txtContrasenia);
        getContentPane().add(lblErrorRegistro);
        getContentPane().add(btnIniciarSesion);
        getContentPane().add(btnRegistrarse);
        getContentPane().add(lblNombreUsuario);
        getContentPane().add(lblContrasenia);
        getContentPane().add(lblLogin);
    }

    /**
     * Label para mostrar errores al registrarse/iniciar sesion
     */
    private void armarLabelDeErrores() {
        lblErrorRegistro = new JLabel();
        lblErrorRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        lblErrorRegistro.setBounds(6, 32, 318, 16);
        lblErrorRegistro.setVisible(false);
    }

    /**
     * Arma el label del login
     * @return
     */
    private JLabel armarLabelLogin() {
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setBounds(6, 10, 318, 16);
        return lblLogin;
    }

    /**
     * Arma labels para nombre de usuario y contrasenia
     * @param s
     * @param y
     * @param alto
     * @return
     */
    private JLabel armarLabels(String s, int y, int alto) {
        JLabel lblNombreUsuario = new JLabel(s);
        lblNombreUsuario.setBounds(27, y, alto, 16);
        return lblNombreUsuario;
    }

    /**
     * Arma el boton de inicio de sesion
     */
    private void armarBotonInicioDeSesion() {
        // Boton de inicio de sesion.
        btnIniciarSesion = new JButton("Mandale Mecha");
        btnIniciarSesion.setBounds(168, 120, 140, 29);
        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionIniciarSesion();
            }
        });
    }

    /**
     * Arma el boton de registro
     * @return
     */
    private JButton armarBotonDeRegistro() {
        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(27, 120, 117, 29);
        btnRegistrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Registro(login, login.manejador);

            }
        });
        return btnRegistrarse;
    }

    /**
     * Arma el JPasswordField para la contrasenia
     */
    private void armarJPasswordFieldContrasenia() {
        txtContrasenia = new JPasswordField();
        txtContrasenia.setBounds(158, 74, 149, 26);
        txtContrasenia.setColumns(10);
    }

    /**
     * Arma el TextField para el nombre de usuario
     */
    private void armarTextFieldNombreDeUsuario() {
        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(158, 47, 149, 26);
        txtNombreUsuario.setColumns(10);
    }

    /**
     * Propiedades del JDialog para el login.
     * @param menu
     */
    private void armarVentanaLogin(Menu menu) {
        getContentPane().setLayout(null);
        setBounds(0, 0, 340, 200);
        setLocationRelativeTo(menu);
        setTitle("Login");
        setVisible(true);
    }

    /**
	 * Llama al proceso de inicio de sesion, y muestra un error en caso de fallar. 
	 * @param nombreUsuario
	 * @param contrasenia
	 * @return verdadero o falso segun el exito del inicio de sesion.
	 */
	public boolean iniciarSesion(String nombreUsuario, String contrasenia) {
        this.respuesta = manejador.enviarUsuario(new Usuario(nombreUsuario, contrasenia));
        if (!respuesta.esRegistroEfectivo()) {
            lblErrorRegistro.setText("Error al loggear. Verifique los datos");
            txtContrasenia.setText("");
            lblErrorRegistro.setForeground(Color.RED);
            lblErrorRegistro.setVisible(true);
        }
		return respuesta.esRegistroEfectivo();
	}
	
	/**
	 * Inicia la validacion del usuario y la contrasenia, y llama al proceso de inicio de sesion. Cierra el dialogo 
	 * al iniciar correctamente la sesion.
	 */
	private void actionIniciarSesion() {
		String contrasenia = String.valueOf(txtContrasenia.getPassword());
		if (!camposLoginVacios() && iniciarSesion(txtNombreUsuario.getText(), contrasenia)) {
			cerrarDialogo();
		}
	}
	
	/**
	 * Valida que los campos de nombre de usuario y contrasenia no esten vacios.
	 * @return
	 */
	private boolean camposLoginVacios() {
		if(txtNombreUsuario.getText().isEmpty() || txtContrasenia.getPassword().length == 0) {
			lblErrorRegistro.setText("Rellene todos los campos");
			lblErrorRegistro.setForeground(Color.RED);
			lblErrorRegistro.setVisible(true);
			return true;
		}
		return false;
	}
	
	/**
	 * Cierra la ventana del login y cambia el texto de bienvenida del menu principal.
	 */
	private void cerrarDialogo() {
		ventanaMenu.loggeado(txtNombreUsuario.getText());
		dispose();
	}
	
	/**
	 * Ejecuta el inicio de sesion al pulsar la tecla Enter en los JTextField.
	 */
	private void iniciarSesionConEnter() {
		txtContrasenia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIniciarSesion.doClick();					
				}
			}
		});
		txtNombreUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIniciarSesion.doClick();					
				}			
			}
		});
	}
	
	public void actualizarUsuario(String usuarioRegistrado) {
		lblErrorRegistro.setText("Usuario registrado. Ingrese su password");
		lblErrorRegistro.setForeground(Color.BLUE);
		lblErrorRegistro.setVisible(true);
		txtNombreUsuario.setText(usuarioRegistrado);
		txtContrasenia.setText("");
		txtContrasenia.requestFocus();
	}
}
