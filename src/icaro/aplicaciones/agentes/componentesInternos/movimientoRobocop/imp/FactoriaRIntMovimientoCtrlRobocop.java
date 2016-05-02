package icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop.imp;

import java.util.logging.Level;
import java.util.logging.Logger;

import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.InfoCompMovimiento;
import icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop.FactoriaAbstrCplInternoRobocop;
import icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop.ItfUsoMovimientoCtrlRopocop;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.patronAgenteCognitivo.procesadorObjetivos.factoriaEInterfacesPrObj.ItfProcesadorObjetivos;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.ItfUsoRepositorioInterfaces;

public class FactoriaRIntMovimientoCtrlRobocop extends FactoriaAbstrCplInternoRobocop {

	private ItfUsoRecursoVisualizadorEntornoSimulacion itfUsoRecVisEntornoSimul;

	@Override
	public InfoCompMovimiento crearComponenteInterno(String identClaseQueImplementaInterfaz,
			ItfProcesadorObjetivos procObj) {
		String identComponenteAcrear = procObj.getAgentId() + identClaseQueImplementaInterfaz;
		MaquinaEstadosMovimientoRobocop maquinaEstados = new MaquinaEstadosMovimientoRobocop();
		maquinaEstados.SetComponentId(identComponenteAcrear);
		maquinaEstados.SetInfoContexto(procObj);
		try {
			ItfUsoRepositorioInterfaces repoItfs = NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ;
			itfUsoRecVisEntornoSimul = (ItfUsoRecursoVisualizadorEntornoSimulacion) repoItfs
					.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");
			maquinaEstados.SetItfUsoRecursoVisualizadorEntornoSimulacion(itfUsoRecVisEntornoSimul);
		} catch (Exception ex) {
			Logger.getLogger(FactoriaRIntMovimientoCtrlRobocop.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		maquinaEstados.cambiarEstado(MaquinaEstadosMovimientoRobocop.EstadoMovimientoRobot.PARADO);
		ItfUsoMovimientoCtrlRopocop itfMov = (ItfUsoMovimientoCtrlRopocop) maquinaEstados
				.cambiarEstado(MaquinaEstadosMovimientoRobocop.EstadoMovimientoRobot.PARADO);

		InfoCompMovimiento infoCompCreado = new InfoCompMovimiento(identComponenteAcrear);
		infoCompCreado.setitfAccesoComponente(itfMov);

		return infoCompCreado;
	}

	@Override
	public boolean verificarExisteInterfazUsoComponente(String identClaseQueImplementaInterfaz) {
		return true;
	}

}
