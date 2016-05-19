package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.Robocop.EstadoLadron;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class InicializarLadron extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			
			this.getItfConfigMotorDeReglas().setDepuracionActivationRulesDebugging(false);
			this.getItfConfigMotorDeReglas().setfactHandlesMonitoring_afterActivationFired_DEBUGGING(false);
			this.getEnvioHechos().insertarHechoWithoutFireRules(new Focus());

			//Obtener recurso visualización
			ItfUsoRecursoVisualizadorEntornoSimulacion itfCompMov = (ItfUsoRecursoVisualizadorEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");

			String idAgente = this.getIdentAgente();
			itfCompMov.mostrarRobotEnOrigen(idAgente);
			
			EstadoLadron eLadron = new EstadoLadron(idAgente);
			eLadron.setCoordenadas(itfCompMov.getRobotOrigen(idAgente));
			this.getEnvioHechos().insertarHechoWithoutFireRules(eLadron);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
