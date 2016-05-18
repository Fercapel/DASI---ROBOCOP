package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.Robocop.InfoMapa;
import icaro.aplicaciones.Robocop.InfoVariables;
import icaro.aplicaciones.Robocop.informacion.InfoLadron;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornoSimulacion;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.ItfUsoRepositorioInterfaces;

public class InicializarLadron extends TareaSincrona {

	private Object itfUsoRepositorio;

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

			
			
			InfoLadron infoLadron = new InfoLadron();

			//ItfUsoRecursoPersistenciaEntornoSimulacion itfUsoRecursoPersistenciaEntornoSimulacion = (ItfUsoRecursoPersistenciaEntornoSimulacion) ((ItfUsoRepositorioInterfaces) this.itfUsoRepositorio).obtenerInterfaz(NombresPredefinidos.ITF_USO + InfoVariables.RECURSO_PERSISTENCIA);
			ItfUsoRecursoPersistenciaEntornoSimulacion itfUsoRecursoPersistenciaEntornoSimulacion = (ItfUsoRecursoPersistenciaEntornoSimulacion) this.repoInterfaces.obtenerInterfaz("Itf_Uso_RecursoPersistenciaEntornoSimulacionRobocop");
			InfoMapa mapa = itfUsoRecursoPersistenciaEntornoSimulacion.obtenerMapa();
			
			infoLadron.setNombreAgente(this.getIdentAgente());
			infoLadron.setCoordenada(mapa.getPosicionInicial(infoLadron.getNombreAgente()));
			
			this.getEnvioHechos().actualizarHechoWithoutFireRules(infoLadron);

			
			//String idAgente = this.getIdentAgente();
			System.out.println("--0---0--0--0--0--0--0"+infoLadron.getNombreAgente());
			//itfCompMov.mostrarPosicionRobot(idAgente, 2, 2);
			itfCompMov.mostrarRobotEnOrigen(infoLadron.getNombreAgente());


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
