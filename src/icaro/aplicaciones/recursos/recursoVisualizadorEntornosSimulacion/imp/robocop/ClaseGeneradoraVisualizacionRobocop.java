package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

import java.rmi.RemoteException;

import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class ClaseGeneradoraVisualizacionRobocop extends ImplRecursoSimple implements ItfUsoRecursoVisualizadorEntornoSimulacion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ControladorVisualizacionRobocop controladorIUSimulador;
	
	public ClaseGeneradoraVisualizacionRobocop(String idRecurso) throws RemoteException {
		super(idRecurso);
		
		//recursoId = idRecurso;
        try {
            trazas.aceptaNuevaTraza(new InfoTraza(idRecurso, "El constructor de la clase generadora del recurso " + idRecurso + " ha completado su ejecucion ....", InfoTraza.NivelTraza.debug));
            //notifEvt = new NotificadorInfoUsuarioSimulador(recursoId, identAgenteaReportar);

            controladorIUSimulador = new ControladorVisualizacionRobocop();
            //controladorIUSimulador = new ControladorVisualizacionRobocop(notifEvt);
            
        } catch (Exception e) {
            this.trazas.trazar(idRecurso, " Se ha producido un error en la creación del recurso : " + e.getMessage(), InfoTraza.NivelTraza.error);
            this.itfAutomata.transita("error");
            throw e;
        }
	}

	@Override
	public void mostrarEscenarioMovimiento() throws Exception {
		controladorIUSimulador.peticionMostrarEscenarioMovimiento();
		
	}

}
