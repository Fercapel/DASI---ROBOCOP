package icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop.imp;

import java.util.EnumMap;
import java.util.Map;

import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.patronAgenteCognitivo.procesadorObjetivos.factoriaEInterfacesPrObj.ItfProcesadorObjetivos;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.ItfUsoRecursoTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza.NivelTraza;

public class MaquinaEstadosMovimientoRobocop {

	private String identEstadoActual;
	private String identComponente;
	private String identDestino;

	public static enum EstadoMovimientoRobot {
		INDEFINIDO, PARADO, MOVIENDOSE, BLOQUEADO, IMPOSIBLE, DESTINO, ERROR
	}

	public EstadoAbstractoMovimientoRobocop estadoActual;
	public String identAgente;

	public ItfUsoRecursoTrazas trazas;
	private Map<EstadoMovimientoRobot, EstadoAbstractoMovimientoRobocop> estadosCreados;

	public volatile int xActual;
	public volatile int yActual;

	public volatile int xDestino;
	public volatile int yDestino;

	public int distanciaDestino;

	public ItfProcesadorObjetivos itfProcObjetivos;

	protected HebraMonitorizacionRobocop monitorizacionLlegadaDestino;

	ItfUsoRecursoVisualizadorEntornoSimulacion itfUsoRecVisEntornoSimul;
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getSimpleName());

	public MaquinaEstadosMovimientoRobocop() {
		estadosCreados = new EnumMap<EstadoMovimientoRobot, EstadoAbstractoMovimientoRobocop>(
				EstadoMovimientoRobot.class);

	}

	public void bloquear() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean estamosEnDestino(String identDestino) {
		return estadoActual.paradoEnDestino(identDestino);
	}

	public synchronized void inicializar(ItfProcesadorObjetivos itfProcObj,
			ItfUsoRecursoVisualizadorEntornoSimulacion itfVisSimul) {
		identAgente = itfProcObj.getAgentId();
		if (identComponente == null)
			identComponente = identAgente + "." + this.getClass().getSimpleName();

		itfProcObjetivos = itfProcObj;
		itfUsoRecVisEntornoSimul = itfVisSimul;
	}

	public EstadoAbstractoMovimientoRobocop getEstadoActual() {
		return estadoActual;
	}

	public void SetComponentId(String idComp) {
		identComponente = idComp;
	}

	public void SetItfUsoRecursoVisualizadorEntornoSimulacion(
			ItfUsoRecursoVisualizadorEntornoSimulacion itfVisualEntSim) {
		itfUsoRecVisEntornoSimul = itfVisualEntSim;
	}

	public void SetInfoContexto(ItfProcesadorObjetivos itfProcObj) {
		identAgente = itfProcObj.getAgentId();
		itfProcObjetivos = itfProcObj;

		if (identComponente == null)
			identComponente = identAgente + "." + this.getClass().getSimpleName();

		trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
	}

	public synchronized EstadoAbstractoMovimientoRobocop cambiarEstado(EstadoMovimientoRobot nuevoEstadoId) {
		if (!nuevoEstadoId.name().equals(identEstadoActual)) {
			trazas.trazar(identComponente,
					" se cambia el estado: " + identEstadoActual + " al estado : " + nuevoEstadoId, NivelTraza.debug);
			estadoActual = estadosCreados.get(nuevoEstadoId);

			if (estadoActual != null) {
			} else
				estadoActual = crearInstanciaEstado(nuevoEstadoId);
		}
		identEstadoActual = estadoActual.identEstadoActual;
		return estadoActual;
	}

	private EstadoAbstractoMovimientoRobocop crearInstanciaEstado(EstadoMovimientoRobot estadoId) {

		if (estadoId.equals(EstadoMovimientoRobot.BLOQUEADO))
			//estadoActual = new RobotBloqueado(this);
			estadoActual = new RobotMoviendose(this);
		else if (estadoId.equals(EstadoMovimientoRobot.PARADO))
			//estadoActual = new RobotParado(this);
			estadoActual = new RobotMoviendose(this);
		else
			estadoActual = new RobotMoviendose(this);

		estadoActual.inicializar(itfProcObjetivos, itfUsoRecVisEntornoSimul);
		identEstadoActual = estadoId.name();
		estadoActual.identComponente = identComponente;
		estadosCreados.put(estadoId, estadoActual);
		trazas.trazar(identComponente,
				" se crea el estado: " + identEstadoActual + " y se pone la maquina de estados  en este estado  ",
				NivelTraza.debug);

		return estadoActual;

	}

	public void inicializarInfoMovimiento(int x, int y) {
		xActual = x;
		yActual = y;
	}

	public synchronized void moverAdestino(String identdest, int x, int y) {
		// suponemos que la orden de moverse se da desde un estado parado
		// cuando se da desde un estado en movimiento se activa la orden cambiar
		// destino
		if (this.identEstadoActual.equals(EstadoMovimientoRobot.MOVIENDOSE.name()))
			if (identdest.equals(identDestino))
				this.trazas.trazar(this.identComponente, " Se esta avanzando hacia el destino ",
						InfoTraza.NivelTraza.debug);
			else {
				// cambiar destino
				if (monitorizacionLlegadaDestino != null)
					this.monitorizacionLlegadaDestino.finalizar();
				int c[] = monitorizacionLlegadaDestino.getCoordRobot();
				xActual = c[0];
				yActual = c[1];
				
				this.xDestino = x;
				this.yDestino = y;
				this.identDestino = identdest;

				this.monitorizacionLlegadaDestino = new HebraMonitorizacionRobocop(this.identAgente, this,
						this.itfUsoRecVisEntornoSimul);

				monitorizacionLlegadaDestino.inicializarDestino(identdest, xActual, yActual, xDestino, yDestino);
				monitorizacionLlegadaDestino.start();
			}
		
		if (this.identEstadoActual.equals(EstadoMovimientoRobot.PARADO.name()))
			if (identdest.equals(identDestino))
				this.trazas.trazar(this.identComponente, " Estamos parados en destino ", InfoTraza.NivelTraza.error);
			else {// se da la orden de avanzar al destino
				this.xDestino = x;
				this.yDestino = y;
				this.identDestino = identdest;
				
				log.debug("Se crea la hebra de  monitorizacion para destino " + identdest + "  posicion actual -> ("
						+ this.xActual + " , " + this.yActual + ")");
				if (monitorizacionLlegadaDestino == null) {
					monitorizacionLlegadaDestino = new HebraMonitorizacionRobocop(this.identAgente, this,
							this.itfUsoRecVisEntornoSimul);
				} 
				
				monitorizacionLlegadaDestino.inicializarDestino(identdest, xActual, yActual, xDestino, yDestino);
				monitorizacionLlegadaDestino.start();
			}
	}

	public synchronized void cambiaDestino(String identdest, int x, int y) {
		if (identdest.equals(identDestino))
			this.trazas.trazar(this.identComponente, " El nuevo destino coincide con el  destino actual ",
					InfoTraza.NivelTraza.debug);
		else {
			if (this.identEstadoActual.equals(EstadoMovimientoRobot.MOVIENDOSE)) {
				this.xDestino = x;
				this.yDestino = y;
				this.identDestino = identdest;
				if (monitorizacionLlegadaDestino != null)
					this.monitorizacionLlegadaDestino.finalizar();
				
				int[] c = monitorizacionLlegadaDestino.getCoordRobot();
				xActual = c[0];
				yActual = c[1];

				trazas.trazar(identAgente,
						"Se recibe una  orden de cambiar  a destino. El robot esta en el estado :" + identEstadoActual
								+ " CoordActuales =  " + this.xActual + ", " + this.yActual + " CoordDestino =  "
								+ xDestino + ", " + yDestino, InfoTraza.NivelTraza.debug);
			}
			moverAdestino(identDestino, x, y);
		}
	}

	public synchronized void parar() {
		if (monitorizacionLlegadaDestino != null)
			this.monitorizacionLlegadaDestino.finalizar();
		int[] c = monitorizacionLlegadaDestino.getCoordRobot();
		xActual = c[0];
		yActual = c[1];
				
		trazas.trazar(identAgente,
				"Se recibe una  orden de parada. El robot esta en el estado :" + identEstadoActual
						+ " CoordActuales =  " + this.xActual + ", " + yActual + " CoordDestino =  "
						+ xDestino + ", " + yDestino, InfoTraza.NivelTraza.debug);
	}

	public synchronized void estamosEnDestino(String identDest, int xDest, int yDest) {
		estadoActual = this.cambiarEstado(EstadoMovimientoRobot.PARADO);
		this.xActual = xDest;
		this.yActual = yDest;
		this.estadoActual.identDestino = identDest;
	}

	public synchronized void imposibleAvanzarADestino() {
		estadoActual = this.cambiarEstado(EstadoMovimientoRobot.BLOQUEADO);
	}

	public synchronized int[] getCoordenadasActuales() {
		int[] coordenadas = {xActual, yActual};
		return coordenadas;
	}

	public synchronized void setCoordenadasActuales(int x, int y) {
		if (x >= 0 && y>=0) {
			xActual = x;
			yActual = y;
		}
	}
	
	public String getIdentEstadoMovRobot() {
		return identEstadoActual;
	}
}
