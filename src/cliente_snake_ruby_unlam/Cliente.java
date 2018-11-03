package cliente_snake_ruby_unlam;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	
	private Lector lector;
	private Escritor escritor;

    public Cliente(String ip, int puerto) {
        try {
            Socket coneccionServidor = new Socket(ip, puerto);

            System.out.print("Connection accepted");

            this.lector = new Lector(coneccionServidor);
            lector.start();

            this.escritor = new Escritor(coneccionServidor);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Jugador obtenerJugador() {
    	return new Jugador(1, escritor, lector);
    }
}
