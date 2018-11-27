package manejadores;

import cliente_snake_ruby_unlam.Menu;
import cliente_snake_ruby_unlam.Sala;
import com.google.gson.Gson;

import java.io.IOException;

import static java.util.Objects.nonNull;

public class ManejadorActualizacionSala extends Thread {

    private ManejadorES manejadorES;
    private Menu menu;
    private Gson gson = new Gson();

    public ManejadorActualizacionSala(ManejadorES manejadorES) {
        this.manejadorES = manejadorES;
    }

    @Override
    public void run() {
        boolean continuar = true;
        while (continuar) {
            try {
                Sala sala = gson.fromJson(manejadorES.getEntrada().readUTF(), Sala.class);
                if (nonNull(sala)){
                    menu.conectadoASala(sala);
                } else {
                    continuar = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void agregarMenu(Menu menu) {
        this.menu = menu;
    }

}
