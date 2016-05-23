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
		int cantidadAgentesNecesarios = 1;
		
		Coordenada coordenadaObj = null;
		if(!eComisaria.getRobosASofocar().isEmpty())
			coordenadaObj = eComisaria.getRobosASofocar().get(0);
		else if(!eComisaria.getRobosAEnviarRefuerzos().isEmpty()){
			coordenadaObj = eComisaria.getRobosAEnviarRefuerzos().get(0).getCoordenadaRobo();
			cantidadAgentesNecesarios = eComisaria.getRobosAEnviarRefuerzos().get(0).getEfectivos();
		}
		
		this.getEnvioHechos().actualizarHechoWithoutFireRules(eComisaria);
		
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
				this.getEnvioHechos().actualizarHechoWithoutFireRules(eComisaria);

				this.getComunicator().informaraGrupoAgentes(new RoboEnProceso(coordenadaObj), policiasQueVan);
				
				trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Agente Mas Cercado -> "+policiasQueVan.toString(), InfoTraza.NivelTraza.info));     
		        System.out.println("Agente Mas Cercado -> "+policiasQueVan.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
