package icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop.imp;

import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.imp.MaquinaEstadoMovimientoCtrl;
import icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop.ItfUsoMovimientoCtrlRobocop;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class RobotMoviendose extends EstadoAbstractoMovimientoRobocop implements ItfUsoMovimientoCtrlRobocop {

	public RobotMoviendose(MaquinaEstadosMovimientoRobocop maquinaEstados) {
		super(maquinaEstados, MaquinaEstadosMovimientoRobocop.EstadoMovimientoRobot.MOVIENDOSE);
	}

	@Override
	public synchronized void moverADestino(String identdest, int x, int y) {
		if (identdest.equals(identDestino)) {
			this.trazas.trazar(this.identComponente, " Se esta avanzando hacia el destino ",
					InfoTraza.NivelTraza.debug);
		} else { 
			maquinaEstados.cambiaDestino(identdest, x, y);
		}
	}

	@Override
	public synchronized void cambiaDestino(String identdest, int x, int y) {
		if (identdest.equals(identDestino))
			this.trazas.trazar(this.identComponente, " Se esta avanzando hacia el destino ",
					InfoTraza.NivelTraza.debug);
		else
			maquinaEstados.cambiaDestino(identdest, x, y);
	}

	@Override
	public void parar() {
		this.maquinaEstados.parar();
		this.maquinaEstados.cambiarEstado(MaquinaEstadosMovimientoRobocop.EstadoMovimientoRobot.PARADO);
	}

	@Override
	public void continuar() {
		this.trazas.trazar(this.identComponente, " ignoro la operacion porque estoy parado ",
				InfoTraza.NivelTraza.debug);
	}

	@Override
	public void bloquear() {
		if (monitorizacionLlegadaDestino != null)
			this.monitorizacionLlegadaDestino.finalizar();
		this.maquinaEstados.cambiarEstado(MaquinaEstadosMovimientoRobocop.EstadoMovimientoRobot.BLOQUEADO).parar();
	}

	@Override
	public int[] getCoordenadasActuales() {
		return this.monitorizacionLlegadaDestino.getCoordRobot();
	}

	@Override
	public String getIdentEstadoMovRobot() {
		return MaquinaEstadosMovimientoRobocop.EstadoMovimientoRobot.MOVIENDOSE.name();
	}

	@Override
	public boolean paradoEnDestino(String identDestino) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
