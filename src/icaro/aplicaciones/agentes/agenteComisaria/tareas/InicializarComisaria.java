package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.EstadoComisaria;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class InicializarComisaria extends TareaSincrona {

	
	@Override
	public void ejecutar(Object... params) {
		try {
			this.getItfConfigMotorDeReglas().setDepuracionActivationRulesDebugging(false);
			this.getItfConfigMotorDeReglas().setfactHandlesMonitoring_afterActivationFired_DEBUGGING(false);
			this.getEnvioHechos().insertarHechoWithoutFireRules(new Focus());

			ItfUsoRecursoVisualizadorEntornoSimulacion itfCompMov = (ItfUsoRecursoVisualizadorEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");

			String idAgente = this.getIdentAgente();
			
			EstadoComisaria eComisaria = new EstadoComisaria(idAgente);
			Coordenada c = itfCompMov.getRobotOrigen(idAgente);
			c.setIdAgente(idAgente);
			eComisaria.setCoordenadas(c);
			this.getEnvioHechos().insertarHechoWithoutFireRules(eComisaria);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
