package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion;

import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoRecursoVisualizadorEntornoSimulacion extends ItfUsoRecursoSimple {	

	public void mostrarEscenarioMovimiento()throws Exception;

	public void inicializarDestinoRobot(String identRobot, int xActual, int yActual, String identDestino, int xDestino,
			int yDestino)throws Exception;

	public void mostrarPosicionRobot(String identRobot, int xActual, int yActual)throws Exception;;
		 
}
