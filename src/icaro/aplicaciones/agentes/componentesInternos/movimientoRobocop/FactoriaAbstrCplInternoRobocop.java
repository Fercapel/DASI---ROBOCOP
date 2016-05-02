package icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop;

import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.InfoCompMovimiento;
import icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop.imp.FactoriaRIntMovimientoCtrlRobocop;
import icaro.infraestructura.entidadesBasicas.excepciones.ExcepcionEnComponente;
import icaro.infraestructura.patronAgenteCognitivo.procesadorObjetivos.factoriaEInterfacesPrObj.ItfProcesadorObjetivos;

public abstract class FactoriaAbstrCplInternoRobocop {

	public abstract InfoCompMovimiento crearComponenteInterno(String identClaseQueImplementaInterfaz,
			ItfProcesadorObjetivos procObj);

	public abstract boolean verificarExisteInterfazUsoComponente(String identClaseQueImplementaInterfaz);

	public static FactoriaAbstrCplInternoRobocop instance;

	public static FactoriaAbstrCplInternoRobocop instance() throws ExcepcionEnComponente {
		try {
			if (instance == null)
				instance = new FactoriaRIntMovimientoCtrlRobocop();
			return instance;
		} catch (Exception e) {
			throw new ExcepcionEnComponente(
					"\n\nError al comprobar los comportamientos de los gestores, agentes y recursos descritos en el fichero de descripcion del XML ");
		}
	}

}
