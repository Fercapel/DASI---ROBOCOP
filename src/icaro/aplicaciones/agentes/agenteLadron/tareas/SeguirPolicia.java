package icaro.aplicaciones.agentes.agenteLadron.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.ParejaLadronPolicia;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class SeguirPolicia extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		ParejaLadronPolicia pareja = (ParejaLadronPolicia) params[0];
				
		ItfUsoRecursoVisualizadorEntornoSimulacion itfCompVis;
		try {
			itfCompVis =(ItfUsoRecursoVisualizadorEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");
			
			//El ladron se mueve un paso
			Coordenada nuevaCoordenada = pareja.getCaminoAComisaria().get(0);
			itfCompVis.mostrarPosicionRobot(this.getIdentAgente(), nuevaCoordenada);
			
			ParejaLadronPolicia newPareja = new ParejaLadronPolicia(pareja.getLadron(), pareja.getPolicia());
			newPareja.setCaminoAComisaria(pareja.getCaminoAComisaria());
	        
			ArrayList<String> agente = new ArrayList<String>();
			agente.add(pareja.getPolicia());
        	this.getComunicator().informaraGrupoAgentes(newPareja, agente);
			
			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Seguir Policia "+agente, InfoTraza.NivelTraza.info));     
	        
	        System.out.println(this.identAgente+": Seguir Policia "+agente);
	        
			this.getEnvioHechos().eliminarHecho(pareja);
						
		} catch (Exception e){
			
		}

	}
}

