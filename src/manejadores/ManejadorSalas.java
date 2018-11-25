package manejadores;

import cliente_snake_ruby_unlam.RespuestaCreacionSala;
import cliente_snake_ruby_unlam.Sala;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ManejadorSalas {

    private DataOutputStream salida;
    private DataInputStream entrada;
    private Gson gson = new Gson();

    public ManejadorSalas(Socket socket) throws IOException {
        this.salida = new DataOutputStream(socket.getOutputStream());
        this.entrada = new DataInputStream(socket.getInputStream());
    }

    /**
     * Devuelve la lista de salas. Cuidado! está lista es inmutable,
     * no agregar ni remover elementos.
     * @return
     */
    public List<Sala> pedirSalas() {
        try {
            salida.writeUTF("ver_salas");
            List<Sala> salas = Arrays.asList(gson.fromJson(entrada.readUTF(), Sala[].class));
            return salas;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public RespuestaCreacionSala crearSala(Sala sala){
        try {
            salida.writeUTF("crear_sala");
            salida.writeUTF(gson.toJson(sala));
            RespuestaCreacionSala respuesta = gson.fromJson(entrada.readUTF(), RespuestaCreacionSala.class);
            return respuesta;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
