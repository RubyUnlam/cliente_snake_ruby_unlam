package cliente_snake_ruby_unlam;

public class Usuario {

    private String nombreUsuario;
    private String contrasenia;
    private String email;

    public Usuario(String nombreUsuario, String contrasenia, String email) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.email = email;
    }

    public Usuario(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

}
