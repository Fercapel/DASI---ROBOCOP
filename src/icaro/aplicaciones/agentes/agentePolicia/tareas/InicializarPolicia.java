package icaro.aplicaciones.agentes.agentePolicia.tareas;

import icaro.aplicaciones.Robocop.EstadoPolicia;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class InicializarPolicia extends TareaSincrona {

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
			
			EstadoPolicia ePolicia = new EstadoPolicia(idAgente);
			ePolicia.setCoordenadas(itfCompMov.getRobotOrigen(idAgente));
			this.getEnvioHechos().insertarHechoWithoutFireRules(ePolicia);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
