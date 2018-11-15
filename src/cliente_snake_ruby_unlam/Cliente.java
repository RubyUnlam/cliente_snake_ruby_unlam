package cliente_snake_ruby_unlam;

import Observables.Escritor;
import Observables.Lector;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	private Escritor escritor;
	private Socket socket;

    public Cliente(String ip, int puerto) {
        try {
            this.socket = new Socket(ip, puerto);

            System.out.print("Connection accepted");

            this.escritor = new Escritor(this.socket);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Jugador obtenerJugador() {
    	return new Jugador(1, escritor);
    }
    
    public Lector obtenerLector() {
        Lector lector = new Lector(this.socket);
        lector.start();
        return lector;
    }

    public Escritor obtenerEscritor(){
        return this.escritor;
    }
}
