package cliente_snake_ruby_unlam;

import manejadores.ManejadorActualizacionSala;
import manejadores.ManejadorSalas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class SalasCreadas extends JDialog {

	private static final long serialVersionUID = -2515309292855432466L;

	private Menu ventanaMenu;
	private List<Sala> listaSalas = new ArrayList<>();
	private ManejadorSalas manejadorSalas;
	private ManejadorActualizacionSala manejadorActualizacionSala;

	private DefaultListModel<String> listModel = new DefaultListModel<>();

	private JLabel lblValorCantidadMaximaJugadores;
	private JLabel lblValorCantidadIA;
	private JLabel lblDificultadIA;
	private JLabel lblValorCreador;
	private JLabel lblValorEstadoSala;
	private JLabel lblEstadoSala;
	private JLabel lblCantidadMaxJugadores;
	private JLabel lblDificultad;
	private JLabel lblCantidadIA;
	private JLabel lblCreador;
	private JLabel lblPasswordSala;
	private JLabel lblInformativo;
	private JPanel pnlDetallesSala;
	private JList<String> lstSalas;
	private JPasswordField pswSala;
	private JButton btnConectar;

	public SalasCreadas(Menu menu, ManejadorSalas manejadorSalas, ManejadorActualizacionSala manejadorActualizacionSala) {
		ventanaMenu = menu;
		this.manejadorSalas = manejadorSalas;
		this.manejadorActualizacionSala = manejadorActualizacionSala;
		setBounds(100, 100, 450, 315);
		setLocationRelativeTo(menu);
		
		JLabel lblSalasCreadas = new JLabel("Salas creadas");
		lblSalasCreadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalasCreadas.setBounds(6, 5, 400, 16);

		JPanel pnlSalas = new JPanel();
		pnlSalas.setLayout(null);
		pnlSalas.setForeground(Color.WHITE);
		pnlSalas.setBounds(6, 40, 200, 190);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 200, 190);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pnlSalas.add(scrollPane);

		lstSalas = new JList<String>();
		lstSalas.setBounds(0, 0, 200, 149);
		lstSalas.setModel(listModel);
		lstSalas.setEnabled(false);
		lstSalas.setBackground(new Color(255, 240, 245));
		scrollPane.setViewportView(lstSalas);
		lstSalas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarDetalleSala();
			}
		});

		pnlDetallesSala = new JPanel();
		pnlDetallesSala.setBackground(new Color(255, 240, 245));
		pnlDetallesSala.setLayout(null);
		pnlDetallesSala.setBounds(206, 40, 200, 190);

		lblCantidadMaxJugadores = new JLabel("Cantidad maxima de jugadores");
		lblCantidadMaxJugadores.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		lblCantidadMaxJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadMaxJugadores.setBounds(6, 0, 188, 24);
		lblCantidadMaxJugadores.setVisible(false);
		pnlDetallesSala.add(lblCantidadMaxJugadores);

		lblCantidadIA = new JLabel("Cantidad de bots");
		lblCantidadIA.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadIA.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		lblCantidadIA.setBounds(6, 36, 188, 24);
		lblCantidadIA.setVisible(false);
		pnlDetallesSala.add(lblCantidadIA);

		lblCreador = new JLabel("Creador");
		lblCreador.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreador.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		lblCreador.setBounds(6, 103, 188, 24);
		lblCreador.setVisible(false);
		pnlDetallesSala.add(lblCreador);

		lblDificultad = new JLabel("Dificultad Bots");
		lblDificultad.setHorizontalAlignment(SwingConstants.CENTER);
		lblDificultad.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		lblDificultad.setBounds(6, 73, 188, 24);
		lblDificultad.setVisible(false);
		pnlDetallesSala.add(lblDificultad);

		lblValorCantidadMaximaJugadores = new JLabel("");
		lblValorCantidadMaximaJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorCantidadMaximaJugadores.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblValorCantidadMaximaJugadores.setBounds(6, 17, 188, 24);
		pnlDetallesSala.add(lblValorCantidadMaximaJugadores);

		lblValorCantidadIA = new JLabel("");
		lblValorCantidadIA.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorCantidadIA.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblValorCantidadIA.setBounds(6, 53, 188, 24);
		pnlDetallesSala.add(lblValorCantidadIA);

		lblDificultadIA = new JLabel("");
		lblDificultadIA.setHorizontalAlignment(SwingConstants.CENTER);
		lblDificultadIA.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblDificultadIA.setBounds(6, 85, 188, 24);
		pnlDetallesSala.add(lblDificultadIA);

		lblValorCreador = new JLabel("");
		lblValorCreador.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorCreador.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblValorCreador.setBounds(6, 121, 188, 24);
		pnlDetallesSala.add(lblValorCreador);

		lblValorEstadoSala = new JLabel("");
		lblValorEstadoSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorEstadoSala.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblValorEstadoSala.setBounds(6, 158, 188, 24);
		pnlDetallesSala.add(lblValorEstadoSala);

		lblEstadoSala = new JLabel("Estado de la sala");
		lblEstadoSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoSala.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		lblEstadoSala.setBounds(6, 140, 188, 24);
		lblEstadoSala.setVisible(false);
		pnlDetallesSala.add(lblEstadoSala);

		btnConectar = new JButton("Conectar");
		btnConectar.setEnabled(false);
		btnConectar.setBounds(289, 236, 117, 29);
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conectarASala();
			}
		});
		
		pswSala = new JPasswordField();
		pswSala.setEnabled(false);
		pswSala.setBounds(138, 236, 142, 26);

		lblPasswordSala = new JLabel("Ingrese Password");
		lblPasswordSala.setEnabled(false);
		lblPasswordSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordSala.setBounds(6, 240, 129, 16);

		lblInformativo = new JLabel("");
		lblInformativo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformativo.setBounds(6, 21, 400, 16);

		getContentPane().add(pswSala);
		getContentPane().add(lblPasswordSala);
		getContentPane().add(lblInformativo);
		getContentPane().add(pnlDetallesSala);
		getContentPane().add(btnConectar);
		getContentPane().setLayout(null);
		getContentPane().add(lblSalasCreadas);
		getContentPane().add(pnlSalas);
		
		actualizarSalas();

		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				menu.setVisible(true);
				e.getWindow().dispose();
			}
		});

		setVisible(true);
	}

	private void actualizarSalas() {
		listaSalas.clear();

		RespuestaAccionConSala respuesta = manejadorSalas.pedirSalas();
		if(respuesta.esAccionValida()){
			listaSalas.addAll(respuesta.getListaSalas());
			if (listaSalas.size() > 0) {
				for (Sala sala : listaSalas) {
					listModel.addElement(sala.getNombreSala());
				}
				lstSalas.setEnabled(true);
				btnConectar.setEnabled(true);
			}
		} else {
			mostrarMensajeInformativo(respuesta.getMensaje());
		}

	}

	private void conectarASala() {
		if (!lstSalas.isSelectionEmpty()) {
			String nombreSala = lstSalas.getSelectedValue();
			RespuestaAccionConSala respuesta = manejadorSalas.unirseASala(new Sala(nombreSala, String.valueOf(pswSala.getPassword())));
			if(respuesta.esAccionValida()){
				for (Sala sala : respuesta.getListaSalas()) {
					if (sala.getNombreSala().equals(nombreSala)) {
						ventanaMenu.setVisible(true);
						ventanaMenu.conectadoASala(sala);
						manejadorActualizacionSala.start();
						ventanaMenu.habilitarInteraccionSalas(false);
						dispose();
					}
				}
			} else {
				mostrarMensajeInformativo(respuesta.getMensaje());
			}
		}
	}

	private void pedirPassword() {
		lblPasswordSala.setEnabled(true);
		pswSala.setEnabled(true);
	}

	private void mostrarDetalleSala() {
		if (lstSalas.isEnabled()) {
			Sala sala = listaSalas.get(lstSalas.getSelectedIndex());

			String estadoSala = sala.isSalaInactiva() ? "Inactiva"  : "Activa";

			lblPasswordSala.setEnabled(false);
			pswSala.setEnabled(false);
			pswSala.setText("");
			mostrarMensajeInformativo("");
			
			lblDificultadIA.setText(String.valueOf(sala.getDificultadIA()));
			lblValorCantidadMaximaJugadores.setText(String.valueOf(sala.getCantidadJugadores()));
			lblValorCantidadIA.setText(String.valueOf(sala.getCantidadIA()));
			lblValorCreador.setText(ventanaMenu.getUsuarioActual());
			lblDificultad.setVisible(true);
			lblCantidadMaxJugadores.setVisible(true);
			lblCantidadIA.setVisible(true);
			lblCreador.setVisible(true);
			lblEstadoSala.setVisible(true);
			lblValorEstadoSala.setText(estadoSala);
			
			if (!sala.getContrasenia().isEmpty()) {
				pedirPassword();
			}
		}
	}

	/**
	 * Muestra mensaje de error o informativo.
	 * 
	 * @param mensaje
	 */
	public void mostrarMensajeInformativo(String mensaje) {
		lblInformativo.setForeground(Color.RED);
		lblInformativo.setText(mensaje);
		lblInformativo.setVisible(true);
	}
}
