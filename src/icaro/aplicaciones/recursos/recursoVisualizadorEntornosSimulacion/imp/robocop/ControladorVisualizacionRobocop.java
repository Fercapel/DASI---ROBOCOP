package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

import org.openide.util.Exceptions;

public class ControladorVisualizacionRobocop {

	private VisorMovimientoRobocop visorMovimientoEscen;

	public void peticionMostrarEscenarioMovimiento() {
        try {          
            if (visorMovimientoEscen == null)
            	visorMovimientoEscen = new VisorMovimientoRobocop();
            
            visorMovimientoEscen.visualizarEscenario();
                        
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
	}
	
	public VisorMovimientoRobocop getVisorMovimientoRobocop(){
		return visorMovimientoEscen;
	}
}
