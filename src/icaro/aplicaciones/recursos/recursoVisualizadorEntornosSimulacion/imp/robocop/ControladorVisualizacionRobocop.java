package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

import icaro.aplicaciones.Robocop.InfoMapa;

public class ControladorVisualizacionRobocop {

	private VisorMovimientoRobocop visorMovimientoEscen;

	public ControladorVisualizacionRobocop(){
		try {
			visorMovimientoEscen = new VisorMovimientoRobocop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cargarMapa(InfoMapa mapa) {
        visorMovimientoEscen.cargarMapa(mapa);
	}
	
	public void peticionMostrarEscenarioMovimiento() {
        visorMovimientoEscen.visualizarEscenario();
	}
	
	public VisorMovimientoRobocop getVisorMovimientoRobocop(){
		return visorMovimientoEscen;
	}
}
