package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

import java.rmi.RemoteException;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.InfoMapa;
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
    		visorMovimiento = controladorIUSimulador.getVisorMovimientoRobocop();
            //controladorIUSimulador = new ControladorVisualizacionRobocop(notifEvt);
            
        } catch (Exception e) {
            this.trazas.trazar(idRecurso, " Se ha producido un error en la creación del recurso : " + e.getMessage(), InfoTraza.NivelTraza.error);
            this.itfAutomata.transita("error");
            throw e;
        }
	}

	@Override
	public void cargarMapa(InfoMapa mapa) throws Exception {
		controladorIUSimulador.cargarMapa(mapa);
	}
	
	@Override
	public void mostrarEscenarioMovimiento() throws Exception {
		controladorIUSimulador.peticionMostrarEscenarioMovimiento();
	}

	@Override
	public void inicializarDestinoRobot(String identRobot, int xActual, int yActual, String identDestino, int xDestino,
			int yDestino) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void mostrarPosicionRobot(String identRobot, int xActual, int yActual) {
		controladorIUSimulador.peticionMostrarEscenarioMovimiento();
		visorMovimiento.cambiarPosicionRobot(identRobot, xActual, yActual);
	}

	@Override
	public void mostrarRobotEnOrigen(String identRobot) throws Exception {
		visorMovimiento.moverOrigenRobot(identRobot);
	}

	@Override
	public void inicializarDestinoRobot(String identRobot, Coordenada cActual, String identDestino, Coordenada cDestino)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarPosicionRobot(String identRobot, Coordenada c) throws Exception {
		visorMovimiento.moverOrigenRobot(identRobot);
	}

}
