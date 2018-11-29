package manejadores;

import cliente_snake_ruby_unlam.Menu;
import cliente_snake_ruby_unlam.Sala;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static java.util.Objects.nonNull;

public class ManejadorActualizacionSala extends Thread {

    private ManejadorES manejadorES;
    private Menu menu;
    private CountDownLatch countDownLatch;

    public ManejadorActualizacionSala(ManejadorES manejadorES) {
        this.manejadorES = manejadorES;
    }

    @Override
    public void run() {
        boolean continuar = true;
        while (continuar) {
            try {
                Sala sala = manejadorES.escuchar(Sala.class);
                if (nonNull(sala)) { // Si la sala no es null hubo un cambio, si es null significa que esta por comenzar el juego y debo salir del manejador o que salí de la sala
                    menu.conectadoASala(sala);
                } else {
                    continuar = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Apaga el countDown que hace que inicie el juego
        countDownLatch.countDown();
    }

    public void agregarMenu(Menu menu) {
        this.menu = menu;
    }

    public void agregarCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

}
