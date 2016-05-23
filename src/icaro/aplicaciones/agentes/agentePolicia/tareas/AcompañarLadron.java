package icaro.aplicaciones.agentes.agentePolicia.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.ParejaLadronPolicia;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class AcompañarLadron extends TareaSincrona{
	
    @Override
	public void ejecutar(Object... params) {
		ParejaLadronPolicia pareja = (ParejaLadronPolicia) params[0];
		Objetivo obj = (Objetivo) params[1];
		
		System.out.println(this.identAgente+": Acompañar Ladron "+pareja.getLadron() );
		    	
		ItfUsoRecursoVisualizadorEntornoSimulacion itfCompVis;
		try {
			itfCompVis =(ItfUsoRecursoVisualizadorEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");
			
			//El policia se mueve un paso
			Coordenada nuevaCoordenada = pareja.getSiguientePaso();
			System.out.println("Coordenadas hasta la comisaria"+pareja.getCaminoAComisaria());
			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Coordenadas hasta la comisaria"+pareja.getCaminoAComisaria(), InfoTraza.NivelTraza.debug));    
			
			itfCompVis.mostrarPosicionRobot(this.getIdentAgente(), nuevaCoordenada);
			if(!pareja.getCaminoAComisaria().isEmpty()){
				ParejaLadronPolicia newPareja = new ParejaLadronPolicia(pareja.getLadron(), pareja.getPolicia());
				newPareja.setCaminoAComisaria(pareja.getCaminoAComisaria());
				
				ArrayList<String> agente = new ArrayList<String>();
				agente.add(pareja.getLadron());
	        	this.getComunicator().informaraGrupoAgentes(newPareja, agente);
	        	
				trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Acompañar Ladron "+agente, InfoTraza.NivelTraza.info));     
		        
				this.getEnvioHechos().eliminarHecho(pareja);
				
		        System.out.println(this.identAgente+": Acompañar Ladron "+agente);
		        
		        obj.setRefined();
			} else {
				obj.setSolved();
			}
			
			this.getEnvioHechos().actualizarHecho(obj);

		} catch (Exception e){
			
		}
	}

}
