package cliente_snake_ruby_unlam;

import excepciones.ErrorConfiguracionException;
import utilidades.Configuracion;

public class Main {

    /**
     * Se inicia el menu del juego y el cliente
     *
     */
    public static void main(String[] args) throws ErrorConfiguracionException {
        Configuracion configuracion = new Configuracion("configuracion");
        Cliente cliente = new Cliente(configuracion.getIp(), configuracion.getPuerto());
        new Menu(cliente).setVisible(true);
    } //IP DE MI PC "192.168.0.8"
}
