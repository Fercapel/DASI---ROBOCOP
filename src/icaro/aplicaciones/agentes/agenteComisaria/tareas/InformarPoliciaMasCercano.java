package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.EstadoComisaria;
import icaro.aplicaciones.Robocop.InfoMapa;
import icaro.aplicaciones.Robocop.RoboEnProceso;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class InformarPoliciaMasCercano extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {

		EstadoComisaria eComisaria = (EstadoComisaria) params[0];
		int cantidadAgentesNecesarios = 0;
		
		Coordenada coordenadaObj = null;
		String equipoRobo = "default";
		if(!eComisaria.getRobosASofocar().isEmpty()){
			cantidadAgentesNecesarios=1;
			coordenadaObj = eComisaria.getRobosASofocar().get(0).getCoordenadaRobo();
			equipoRobo = eComisaria.getRobosASofocar().get(0).getEquipoDeRobo();
			eComisaria.añadirRoboExplorado(eComisaria.getRobosASofocar().get(0));
		}else if(!eComisaria.getRobosAEnviarRefuerzos().isEmpty()){
			coordenadaObj = eComisaria.getRobosAEnviarRefuerzos().get(0).getCoordenadaRobo();
			equipoRobo = eComisaria.getRobosAEnviarRefuerzos().get(0).getEquipoDeRobo();
			cantidadAgentesNecesarios = eComisaria.getRobosAEnviarRefuerzos().get(0).getEfectivos();
			eComisaria.añadirRoboSofocados(eComisaria.getRobosAEnviarRefuerzos().get(0));
		}
		
		if(coordenadaObj != null){
			InfoMapa mapa;
			
			try {
				ItfUsoRecursoPersistenciaEntornoSimulacion itfCompPer =(ItfUsoRecursoPersistenciaEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoPersistenciaEntornoSimulacionRobocop");
				mapa = itfCompPer.obtenerMapa();
				ArrayList<String> policiasQueVan = new ArrayList<String>();
				for(int i = 0; i < cantidadAgentesNecesarios; i++){
					String agenteCercano = "";
					int pasosMinimo = Integer.MAX_VALUE;
					//Obtener policia más cercano
					for(String agente : eComisaria.getMisAgentes()){
						if(!eComisaria.estaOcupado(agente)){
							ArrayList<Coordenada> caminoAux = mapa.obtenerCaminoMinimo(eComisaria.getCoordenadaAgente(agente), coordenadaObj);
							int pasos = caminoAux.size();
							if(pasos < pasosMinimo){
								agenteCercano = agente;
								pasosMinimo = pasos;
							}
						}
					}
					policiasQueVan.add(agenteCercano);
					eComisaria.anadirAgenteOcupado(agenteCercano);
				}
				
				RoboEnProceso robo = new RoboEnProceso(coordenadaObj);
				robo.setEquipoDeRobo(equipoRobo);
				
				eComisaria.añadirPareja(equipoRobo, policiasQueVan);
				this.getEnvioHechos().actualizarHechoWithoutFireRules(eComisaria);
				
				this.getComunicator().informaraGrupoAgentes(robo, policiasQueVan);
				
				trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Agente Mas Cercado -> "+policiasQueVan.toString(), InfoTraza.NivelTraza.info));     
		        System.out.println("Agente Mas Cercado -> "+policiasQueVan.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
