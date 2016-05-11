package icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion;

import icaro.aplicaciones.Robocop.InfoMapa;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoRecursoPersistenciaEntornoSimulacion extends ItfUsoRecursoSimple {
	public InfoMapa obtenerMapa() throws Exception;
}