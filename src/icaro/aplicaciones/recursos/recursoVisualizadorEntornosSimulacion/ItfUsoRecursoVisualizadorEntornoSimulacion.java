package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.InfoMapa;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoRecursoVisualizadorEntornoSimulacion extends ItfUsoRecursoSimple {	

	public void cargarMapa(InfoMapa mapa)throws Exception;
	
	public void mostrarEscenarioMovimiento()throws Exception;

	public void inicializarDestinoRobot(String identRobot, int xActual, int yActual, String identDestino, int xDestino,
			int yDestino)throws Exception;
	
	public void inicializarDestinoRobot(String identRobot, Coordenada cActual, String identDestino, Coordenada cDestino)throws Exception;

	public void mostrarPosicionRobot(String identRobot, int xActual, int yActual)throws Exception;
	
	public void mostrarPosicionRobot(String identRobot, Coordenada c)throws Exception;
	
	public void mostrarRobotEnOrigen(String identRobot)throws Exception;
		 
}
