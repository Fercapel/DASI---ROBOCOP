package icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop;

import icaro.aplicaciones.agentes.componentesInternos.InfoCompInterno;
import icaro.infraestructura.patronAgenteCognitivo.procesadorObjetivos.factoriaEInterfacesPrObj.ItfProcesadorObjetivos;

public class InfoCompMovimientoRobocop extends InfoCompInterno {

	public ItfUsoMovimientoCtrlRopocop itfAccesoComponente;
	public ItfProcesadorObjetivos itfAccesoControlEntidad;
	private String identEstadoRobot;
	private String identDestino;

	public InfoCompMovimientoRobocop(String componenteId) {
		super(componenteId);
	}

	public void setitfAccesoComponente(ItfUsoMovimientoCtrlRopocop itfAccComponente) {
		itfAccesoComponente = itfAccComponente;
	}

	@Override
	public Object getitfAccesoComponente() {
		return itfAccesoComponente;
	}

	public void setitfAccesoControlEntidad(ItfProcesadorObjetivos itfAccEntidad) {
		itfAccesoControlEntidad = itfAccEntidad;
	}

	@Override
	public Object getitfAccesoControlEntidad() {
		return itfAccesoControlEntidad;
	}

	public String getidentEstadoRobot() {
		return identEstadoRobot;
	}

	public void setidentEstadoRobot(String identEstRobot) {
		identEstadoRobot = identEstRobot;

	}

	public String getidentDestino() {
		return identDestino;
	}

	public void setidentDestino(String destinoId) {
		identDestino = destinoId;

	}
}
