package icaro.aplicaciones.agentes.agenteLadron.tareas;

import java.util.List;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.EstadoLadron;
import icaro.aplicaciones.Robocop.InfoMapa;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornoSimulacion;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class IrHastaElRobo extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		EstadoLadron eLadron = (EstadoLadron) params[0];
		Objetivo obj = (Objetivo) params[1];
		
		ItfUsoRecursoPersistenciaEntornoSimulacion itfCompPer;
		ItfUsoRecursoVisualizadorEntornoSimulacion itfCompVis;
		InfoMapa mapa = null;
		try {
			itfCompPer =(ItfUsoRecursoPersistenciaEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoPersistenciaEntornoSimulacionRobocop");
			itfCompVis =(ItfUsoRecursoVisualizadorEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");
			mapa = itfCompPer.obtenerMapa();
			
			//Si no tengo camino por recorrer lo calculo
			if(eLadron.getCamino().isEmpty()){
				List<Coordenada> camino = mapa.obtenerCaminoMinimo(eLadron.getCoordenadas(), eLadron.getCoordenadasDelRobo());
				camino.add(eLadron.getCoordenadasDelRobo());
				eLadron.setCamino(camino);
			} 
			
			//El ladron se mueve un paso
			Coordenada nuevaCoordenada = eLadron.getSiguientePaso();
			itfCompVis.mostrarPosicionRobot(this.getIdentAgente(), nuevaCoordenada);
			eLadron.setCoordenadas(nuevaCoordenada);
			
			//Si he llegado al lugar del robo he cumplido la tarea
			if(nuevaCoordenada.getPosArray(mapa.getNumeroColumnas()) == eLadron.getCoordenadasDelRobo().getPosArray(mapa.getNumeroColumnas())){
				eLadron.setEsperandoCompañeros(true);
			} 
			
			obj.setPending();
			

			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Me he movido a " + nuevaCoordenada.toString(), InfoTraza.NivelTraza.info));     
	        
	        System.out.println(this.identAgente+": Me he movido a " + nuevaCoordenada.toString() );
			
	        this.getEnvioHechos().actualizarHechoWithoutFireRules(eLadron);
	        this.getEnvioHechos().actualizarHecho(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
