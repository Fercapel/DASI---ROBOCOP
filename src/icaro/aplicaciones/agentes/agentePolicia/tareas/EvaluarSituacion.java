package icaro.aplicaciones.agentes.agentePolicia.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.Robocop.EstadoPolicia;
import icaro.aplicaciones.Robocop.InfoEquipo;
import icaro.aplicaciones.Robocop.RoboEnProceso;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class EvaluarSituacion extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		EstadoPolicia ePolicia = (EstadoPolicia) params[0];
		InfoEquipo info = (InfoEquipo) params[1];
		
		ItfUsoRecursoVisualizadorEntornoSimulacion itfCompVis;
		try {
			itfCompVis =(ItfUsoRecursoVisualizadorEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");
			int numLadrones = itfCompVis.getNumeroLadronesDibujadosEn(ePolicia.getCoordenadas());
			

			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Hay " + numLadrones + "Ladrones robando en "+ePolicia.getCoordenadas().toString(), InfoTraza.NivelTraza.info));     
	        
	        System.out.println(this.identAgente+": Hay " + numLadrones + "Ladrones robando en "+ePolicia.getCoordenadas().toString() );
			
	        ArrayList<String> jefe = new ArrayList<String>();
			jefe.add(info.getIdentAgenteJefeEquipo());
			
			//Comunicar a la comisaria cuantos refuerzos se necesitan
			this.getComunicator().informaraGrupoAgentes(new RoboEnProceso(ePolicia.getCoordenadas(), numLadrones-1), jefe);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}