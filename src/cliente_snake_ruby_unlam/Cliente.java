package cliente_snake_ruby_unlam;

import manejadores.ManejadorMovimientos;
import manejadores.ManejadorLogin;
import manejadores.ManejadorSalas;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	private ManejadorMovimientos manejadorMovimientos;
    private ManejadorLogin manejadorLogin;
    private ManejadorSalas manejadorSalas;
    private Socket socket;

    public Cliente(String ip, int puerto) {
        try {
            this.socket = new Socket(ip, puerto);

            System.out.print("Connection accepted");

            this.manejadorMovimientos = new ManejadorMovimientos(this.socket);
            this.manejadorLogin = new ManejadorLogin(socket);
            this.manejadorSalas = new ManejadorSalas(socket);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Controlador obtenerControlador() {
    	return new Controlador(manejadorMovimientos);
    }

    public Socket obtenerJugador() {
        return socket;
    }

    public ManejadorMovimientos obtenerEscritor(){
        return this.manejadorMovimientos;
    }

    public ManejadorLogin getManejadorLogin() {
        return manejadorLogin;
    }

    public ManejadorSalas getManejadorSalas() {
        return manejadorSalas;
    }
}
