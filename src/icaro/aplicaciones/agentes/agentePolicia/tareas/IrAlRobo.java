package icaro.aplicaciones.agentes.agentePolicia.tareas;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.EstadoPolicia;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class IrAlRobo extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		EstadoPolicia ePolicia = (EstadoPolicia) params[0];
		Objetivo obj = (Objetivo) params[1];
		
		ItfUsoRecursoVisualizadorEntornoSimulacion itfCompVis;
		try {
			itfCompVis =(ItfUsoRecursoVisualizadorEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");
			
			//El Policia se mueve un paso
			Coordenada nuevaCoordenada = ePolicia.getSiguientePaso();
			itfCompVis.mostrarPosicionRobot(this.getIdentAgente(), nuevaCoordenada);
			ePolicia.setCoordenadas(nuevaCoordenada);
			this.getEnvioHechos().actualizarHechoWithoutFireRules(ePolicia);
			
			//Si he lelgado al lugar del robo he cumplido la tarea
			if(ePolicia.getCamino().isEmpty()){
				obj.setSolved();
			} else {
				obj.setRefined();
			}	

			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Me he movido a " + nuevaCoordenada.toString() + "Me falta recorrer "+ePolicia.getCamino().toString(), InfoTraza.NivelTraza.info));     
	        
	        System.out.println(this.identAgente+": Me he movido a " + nuevaCoordenada.toString() );
			
	        this.getEnvioHechos().actualizarHecho(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}