package cliente_snake_ruby_unlam;

import manejadores.*;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    private ManejadorMovimientos manejadorMovimientos;
    private ManejadorLogin manejadorLogin;
    private ManejadorSalas manejadorSalas;
    private ManejadorES manejadorES;
    private Socket socket;
    private final String SALIR = "salir";


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

    public void cerrarConexion() {
        try {
            manejadorES.enviarString(SALIR);
            Thread.sleep(1000); //le doy tiempo al sv de que cierre el socket antes de yo cerrar el mio
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public ManejadorES getManejadorES() {
        return manejadorES;
    }
}
