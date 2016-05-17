package icaro.aplicaciones.agentes.agentePolicia.tareas;

import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class InicializarPolicia extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			//System.out.println(params[0]+"----"+params[1]);
			// Objetivo objetivoEjecutantedeTarea = (Objetivo)params[0];
			// String identTarea = this.getIdentTarea();
			
			this.getItfConfigMotorDeReglas().setDepuracionActivationRulesDebugging(false);
			this.getItfConfigMotorDeReglas().setfactHandlesMonitoring_afterActivationFired_DEBUGGING(false);
			this.getEnvioHechos().insertarHechoWithoutFireRules(new Focus());
			//this.getEnvioHechos().insertarHechoWithoutFireRules(new MisObjetivos());
			//this.getEnvioHechos().insertarHechoWithoutFireRules(new VictimsToRescue());
			
			//Obtener recurso visualización
			ItfUsoRecursoVisualizadorEntornoSimulacion itfCompMov = (ItfUsoRecursoVisualizadorEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");
			/*
			 *  Prueba concepto -> Poner al policía en la posicion 0,0 del mapa
			 */
			String idAgente = this.getIdentAgente();
			itfCompMov.mostrarRobotEnOrigen(idAgente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
