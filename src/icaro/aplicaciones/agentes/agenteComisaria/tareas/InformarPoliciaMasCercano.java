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
		Coordenada coordenajaObj = eComisaria.getRobosASofocar().get(0);
		//eComisaria.añadirRoboSofocados(coordenajaObj);
		
		this.getEnvioHechos().actualizarHechoWithoutFireRules(eComisaria);
		
		InfoMapa mapa;
		try {
			ItfUsoRecursoPersistenciaEntornoSimulacion itfCompPer =(ItfUsoRecursoPersistenciaEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoPersistenciaEntornoSimulacionRobocop");
			mapa = itfCompPer.obtenerMapa();
			String agenteCercano="";
			int pasosMinimo = Integer.MAX_VALUE;
			
			//Obtener policia más cercano
			for(String agente : eComisaria.getMisAgentes()){
				ArrayList<Coordenada> caminoAux = mapa.obtenerCaminoMinimo(eComisaria.getCoordenadaAgente(agente), coordenajaObj);
				int pasos = caminoAux.size();
				if(pasos < pasosMinimo){
					pasosMinimo = pasos;
					agenteCercano = agente;
				}
			}
			
			ArrayList<String> policiasQueVan = new ArrayList<String>();
			policiasQueVan.add(agenteCercano);
			
			this.getComunicator().informaraGrupoAgentes(new RoboEnProceso(coordenajaObj), policiasQueVan);
			
			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Agente Mas Cercado -> "+agenteCercano, InfoTraza.NivelTraza.info));     
	        System.out.println("Agente Mas Cercado -> "+agenteCercano);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
