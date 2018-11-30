package manejadores;


import java.io.File;
import java.io.IOException;

import cliente_snake_ruby_unlam.ActualizacionDelJuego;
import observables.ObservadoLectura;
import observables.ObservadorDibujables;

import javax.sound.sampled.*;


public class ManejadorDeJuego extends Thread implements ObservadoLectura {

	private final String JUGAR = "jugar";
	private ObservadorDibujables observadorDibujable;
	private ManejadorES manejadorES;

	private String archivoMusicaDeJuego = "src\\sonidos\\musicaDeFondo.wav";

	public ManejadorDeJuego(ManejadorES manejadorES) {
		this.manejadorES = manejadorES;
	}

	public void comunicarInicioDeJuego() {
		try {
			manejadorES.enviarString(JUGAR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			boolean finDelJuego = false;

			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(archivoMusicaDeJuego));
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);

			Clip musicaDelJuego = (Clip)AudioSystem.getLine(info);
			musicaDelJuego.open(audioInputStream);

			FloatControl gainControl = (FloatControl) musicaDelJuego.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f); // Reduce el volumen 20 db

			musicaDelJuego.start();

			while (!finDelJuego) {
				ActualizacionDelJuego actualizacion = manejadorES.escuchar(ActualizacionDelJuego.class);
				finDelJuego = actualizacion.terminoElJuego();
				enviarADibujar(actualizacion);
			}

			musicaDelJuego.stop();

		} catch (IOException  e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	private void enviarADibujar(ActualizacionDelJuego actualizacion) {
		new Thread() {
			@Override
			public void run() {
				observadorDibujable.notificarUbicaciones(actualizacion);
			}
		}.start();
	}


	@Override
	public void agregarObservadorDibujables(ObservadorDibujables observador) {
		this.observadorDibujable = observador;
	}

}
