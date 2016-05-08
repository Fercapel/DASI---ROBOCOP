package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

public class ControladorVisualizacionRobocop {

	private VisorMovimientoRobocop visorMovimientoEscen;

	public ControladorVisualizacionRobocop(){
		try {
			visorMovimientoEscen = new VisorMovimientoRobocop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void peticionMostrarEscenarioMovimiento() {
        visorMovimientoEscen.visualizarEscenario();
	}
	
	public VisorMovimientoRobocop getVisorMovimientoRobocop(){
		return visorMovimientoEscen;
	}
}
