package icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion;

import java.util.ArrayList;

import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop.Construccion;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoRecursoPersistenciaEntornoSimulacion extends ItfUsoRecursoSimple {
  public ArrayList<Construccion> obtenerMapa ()throws Exception;
}