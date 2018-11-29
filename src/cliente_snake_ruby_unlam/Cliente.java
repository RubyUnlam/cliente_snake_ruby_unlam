package cliente_snake_ruby_unlam;

import manejadores.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	private ManejadorMovimientos manejadorMovimientos;
    private ManejadorLogin manejadorLogin;
    private ManejadorSalas manejadorSalas;
    private ManejadorDeJuego manejadorDeJuego;
    private ManejadorES manejadorES;
    private Socket socket;


    public Cliente(String ip, int puerto) {
        try {
            this.socket = new Socket(ip, puerto);

            this.manejadorES = new ManejadorES(socket);
            this.manejadorMovimientos = new ManejadorMovimientos(manejadorES);
            this.manejadorLogin = new ManejadorLogin(manejadorES);
            this.manejadorSalas = new ManejadorSalas(manejadorES);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Controlador obtenerControlador() {
    	return new Controlador(manejadorMovimientos);
    }

    public ManejadorDeJuego obtenerManejadorDeJuego() {
        return new ManejadorDeJuego(manejadorES);
    }

    public ManejadorLogin getManejadorLogin() {
        return manejadorLogin;
    }

    public ManejadorSalas getManejadorSalas() {
        return manejadorSalas;
    }

    public ManejadorActualizacionSala getManejadorActualizacionSala() {
        return new ManejadorActualizacionSala(manejadorES);
    }

    public ManejadorES getManejadorES() {
        return manejadorES;
    }
}
