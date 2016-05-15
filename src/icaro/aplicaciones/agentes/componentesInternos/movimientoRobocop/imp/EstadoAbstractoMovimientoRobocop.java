package icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop.imp;

import icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop.ItfUsoMovimientoCtrlRobocop;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.patronAgenteCognitivo.procesadorObjetivos.factoriaEInterfacesPrObj.ItfProcesadorObjetivos;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.ItfUsoRecursoTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public abstract class EstadoAbstractoMovimientoRobocop implements ItfUsoMovimientoCtrlRobocop {

	public EstadoAbstractoMovimientoRobocop estadoActual;
	public MaquinaEstadosMovimientoRobocop maquinaEstados;
	public String identAgente;

	public volatile int xActual;
	public volatile int yActual;

	public volatile int xDestino;
	public volatile int yDestino;

	public double distanciaDestino;

	public ItfProcesadorObjetivos itfProcObjetivos;
	protected HebraMonitorizacionRobocop monitorizacionLlegadaDestino;
	public ItfUsoRecursoTrazas trazas;

	public String identComponente;
	public String identEstadoActual;
	public String identDestino;
	public ItfUsoRecursoVisualizadorEntornoSimulacion itfusoRecVisSimulador;

	public EstadoAbstractoMovimientoRobocop(MaquinaEstadosMovimientoRobocop maquinaEstds,
			MaquinaEstadosMovimientoRobocop.EstadoMovimientoRobot identEstadoAcrear) {
		if (maquinaEstds == null) {
			estadoActual = null;
			trazas.trazar(this.getClass().getSimpleName(),
					" Error al crear el estado  " + identEstadoAcrear + " La maquinaEstds es null   ",
					InfoTraza.NivelTraza.error);
		} else {
			maquinaEstados = maquinaEstds;
			identEstadoActual = identEstadoAcrear.name();
		}
	}

	public void inicializar(ItfProcesadorObjetivos itfProcObj) {
		identAgente = itfProcObj.getAgentId();
		itfProcObjetivos = itfProcObj;
		identComponente = identAgente + "." + this.getClass().getSimpleName();
		trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;

	}

	public void inicializar(ItfProcesadorObjetivos itfProcObj, ItfUsoRecursoVisualizadorEntornoSimulacion itfVisSimul) {
		identAgente = itfProcObj.getAgentId();
		itfProcObjetivos = itfProcObj;
		identComponente = identAgente + "." + this.getClass().getSimpleName();
		trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
		itfusoRecVisSimulador = itfVisSimul;
		maquinaEstados.inicializar(itfProcObj, itfVisSimul);

	}

	@Override
	public void inicializarInfoMovimiento(int x, int y) {
		maquinaEstados.inicializarInfoMovimiento(x, y);
	}

	@Override
	public void moverADestino(String identDest, int x, int y) {
		estadoActual.moverADestino(identDest, x, y);
		this.identDestino = identDest;
	}

	@Override
	public void cambiaDestino(String identDest, int x, int y) {
		maquinaEstados.getEstadoActual().cambiaDestino(identDest, x, y);
		this.identDestino = identDest;
	}

	@Override
	public int[] getCoordenadasActuales() {
		return maquinaEstados.getCoordenadasActuales();
	}

	@Override
	public void setCoordenadasActuales(int x, int y) {
		if (x >= 0 && y >= 0) {
			xActual = x;
			yActual = y;
			maquinaEstados.setCoordenadasActuales(x, y);
		}
	}

	@Override
	public String getIdentEstadoMovRobot() {
		return maquinaEstados.getIdentEstadoMovRobot();
	}

	@Override
	public void parar() {
		maquinaEstados.getEstadoActual().parar();
	}

	@Override
	public void bloquear() {
		maquinaEstados.getEstadoActual().bloquear();
	}

	@Override
	public void continuar() {
		maquinaEstados.getEstadoActual().continuar();
	}

	@Override
	public abstract boolean paradoEnDestino(String identDestino);

	@Override
	public void imposibleAvanzarADestino() {
		maquinaEstados.cambiarEstado(MaquinaEstadosMovimientoRobocop.EstadoMovimientoRobot.BLOQUEADO);
	}

	public EstadoAbstractoMovimientoRobocop getEstadoActual() {
		return maquinaEstados.getEstadoActual();
	}

}
