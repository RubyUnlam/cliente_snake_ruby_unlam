package observables;

import cliente_snake_ruby_unlam.Dibujable;

import java.util.List;

public interface ObservadorDibujables {

	void notificarUbicaciones(List<Dibujable> dibujables);
}
