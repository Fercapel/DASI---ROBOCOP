package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.EstadoLadron;
import icaro.aplicaciones.Robocop.InfoMapa;
import icaro.aplicaciones.Robocop.PropuestaDeRobo;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class SeleccionarPropuestaRobo extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		EstadoLadron eLadron = (EstadoLadron) params[0];
		PropuestaDeRobo propuesta = (PropuestaDeRobo) params[1];
		Objetivo obj = (Objetivo) params[2];
		
		ItfUsoRecursoPersistenciaEntornoSimulacion itfCompPer;
		InfoMapa mapa = null;
		try {
			itfCompPer =(ItfUsoRecursoPersistenciaEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoPersistenciaEntornoSimulacionRobocop");
			mapa = itfCompPer.obtenerMapa();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		//Evaluo el coste de ir a las coordenadas de la propuesta
		String idAgente = this.getIdentAgente();
		int coste = mapa.obtenerCaminoMinimo(eLadron.getCoordenadas(), propuesta.getCoordenada()).size();
		
		propuesta.añadirEvaluacion(coste, idAgente);
		
		//Reenvio la propuesta con la nueva evaluacion
		this.getComunicator().informaraGrupoAgentes(propuesta, eLadron.getCompañeros());

		//Compruebo que la propuesta ha sido evaluada por todo el equipo, entonces la guardo como propuesta válida
		if(propuesta.getIdEvaluadores().size()==eLadron.getCompañeros().size()+1){
			eLadron.añadirPropuestaRobo(propuesta);
			
			//Si hay tantas propuestas como agentes en el equipo (los demás agentes +1 de el agente que ejecuta la regla) puedo evaluar a cual ir
			if(eLadron.getPropuestasRobo().size()==eLadron.getCompañeros().size()+1){
				PropuestaDeRobo pFinal = new PropuestaDeRobo("", new Coordenada(), Integer.MAX_VALUE); 
				for(PropuestaDeRobo p : eLadron.getPropuestasRobo()){
					if(p.getCoste() < pFinal.getCoste()){
						pFinal = p;
					}
				}
				
				//Me quedo con las coordenadas de la propuesta mejor (Lugar donde será el robo)
				eLadron.setCoordenadasDelRobo(pFinal.getCoordenada());
				obj.setSolved();
				
				trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Obtenidas todas las propuestas --> Robo en" + pFinal.getCoordenada().toString() + " con coste total " + pFinal.getCoste(), InfoTraza.NivelTraza.info));     
		        
		        System.out.println("Obtenidas todas las propuestas --> Robo en" + pFinal.getCoordenada().toString() + " con coste total " + pFinal.getCoste());
		        
		        this.getEnvioHechos().actualizarHechoWithoutFireRules(eLadron);
				this.getEnvioHechos().eliminarHechoWithoutFireRules(propuesta);
		        this.getEnvioHechos().actualizarHecho(obj);
		        
		        return;
			}
		}
				
		obj.setPending();
		
		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Obtenidas propuesta " + propuesta.getCoordenada().toString() + " con coste total " + propuesta.getCoste() + "(Faltan)", InfoTraza.NivelTraza.info));     
        
        System.out.println("Obtenidas propuesta " + propuesta.getCoordenada().toString() + " con coste total " + propuesta.getCoste() + "(Faltan)" );
		
        this.getEnvioHechos().actualizarHechoWithoutFireRules(eLadron);
		this.getEnvioHechos().eliminarHechoWithoutFireRules(propuesta);
        this.getEnvioHechos().actualizarHecho(obj);
	}
		
}
