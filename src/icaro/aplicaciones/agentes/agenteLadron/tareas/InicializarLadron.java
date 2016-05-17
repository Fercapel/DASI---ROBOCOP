package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class InicializarLadron extends TareaSincrona {

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

			String idAgente = this.getIdentAgente();
			System.out.println("--0---0--0--0--0--0--0"+idAgente);
			//itfCompMov.mostrarPosicionRobot(idAgente, 2, 2);
			itfCompMov.mostrarRobotEnOrigen(idAgente);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
