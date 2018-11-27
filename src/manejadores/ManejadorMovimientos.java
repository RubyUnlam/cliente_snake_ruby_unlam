package manejadores;

import java.io.IOException;

public class ManejadorMovimientos {

	private ManejadorES manejadorES;

	public ManejadorMovimientos(ManejadorES manejadorES) {
		this.manejadorES = manejadorES;
	}

	public void enviarMovimiento(String direccion) {
		try {
			System.out.println(direccion);
			manejadorES.getSalida().writeUTF(direccion);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
