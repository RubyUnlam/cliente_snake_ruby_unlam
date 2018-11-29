package cliente_snake_ruby_unlam;

import java.util.ArrayList;
import java.util.List;

public class Sala {

	private String nombreSala;

	private String contrasenia;

	private int cantidadJugadores;

	private int cantidadIA;

	private String nombreCreador;

	private int dificultadIA;

	private int tiempo = 300; //tiempo default, 5 minutos

	private String modoDeJuego;

	private int puntajeAAlcanzar;

	private List<Jugador> jugadores = new ArrayList<>();

	public Sala(String nombreSala, String contrasenia) {
		this.nombreSala = nombreSala;
		this.contrasenia = contrasenia;
	}

	public Sala(String nombreSala, String contrasenia, int cantidadJugadores, int cantidadIA, String nombreCreador,
                int dificultadIA, String modoDeJuego) {
		this.nombreSala = nombreSala;
		this.contrasenia = contrasenia;
		this.cantidadJugadores = cantidadJugadores;
		this.cantidadIA = cantidadIA;
		this.nombreCreador = nombreCreador;
		this.dificultadIA = dificultadIA;
		this.modoDeJuego = modoDeJuego;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public int getCantidadJugadores() {
		return cantidadJugadores;
	}

	public int getCantidadIA() {
		return cantidadIA;
	}

	public String getNombreCreador() {
		return nombreCreador;
	}

	public int getDificultadIA() {
		return dificultadIA;
	}
	
	// Importante para poder comprobar que no se repitan los nombres de sala
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombreSala == null) ? 0 : nombreSala.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		if (nombreSala == null) {
			if (other.nombreSala != null)
				return false;
		} else if (!nombreSala.equals(other.nombreSala))
			return false;
		return true;
	}

	// TODO: Cuando haya distintos tipos de mapa y tiempo, agregar lo siguiente
	// private int mapa;
	//
	// private int tiempo;


	public List<Jugador> getJugadores() {
		return jugadores;
	}


	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getTiempo() {
		return tiempo;
	}

	public String getModoDeJuego() {
		return modoDeJuego;
	}

	public int getPuntajeAAlcanzar() {
		return puntajeAAlcanzar;
	}

	public void setPuntajeAAlcanzar(int puntajeAAlcanzar) {
		this.puntajeAAlcanzar = puntajeAAlcanzar;
	}


}
