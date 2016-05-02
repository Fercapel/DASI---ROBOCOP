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
	private VisorMovimientoRobocop visorMovimiento;
	
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
		visorMovimiento = controladorIUSimulador.getVisorMovimientoRobocop();
	}

	@Override
	public void inicializarDestinoRobot(String identRobot, int xActual, int yActual, String identDestino, int xDestino,
			int yDestino) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void mostrarPosicionRobot(String identRobot, int xActual, int yActual) {
		if(visorMovimiento == null){
			controladorIUSimulador.peticionMostrarEscenarioMovimiento();
		}
		visorMovimiento.cambiarPosicionRobot(identRobot, xActual, yActual);
	}

}
