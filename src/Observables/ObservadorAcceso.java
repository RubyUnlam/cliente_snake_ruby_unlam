package Observables;

import cliente_snake_ruby_unlam.RegistroUsuario;

public interface ObservadorAcceso {

	boolean notificarRegistro(RegistroUsuario respuesta);
}
