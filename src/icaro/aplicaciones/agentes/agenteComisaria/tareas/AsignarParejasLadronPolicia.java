package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.Robocop.EstadoComisaria;
import icaro.aplicaciones.Robocop.InfoMapa;
import icaro.aplicaciones.Robocop.ListaParejas;
import icaro.aplicaciones.Robocop.ParejaLadronPolicia;
import icaro.aplicaciones.Robocop.RoboEnProceso;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class AsignarParejasLadronPolicia extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		EstadoComisaria eComisaria = (EstadoComisaria) params[0];
		
		ItfUsoRecursoPersistenciaEntornoSimulacion itfCompPer;
		try {
			itfCompPer =(ItfUsoRecursoPersistenciaEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoPersistenciaEntornoSimulacionRobocop");
			InfoMapa mapa = itfCompPer.obtenerMapa();
			
			RoboEnProceso roboAResolver = eComisaria.getRoboAResolver();
			for(RoboEnProceso robo : eComisaria.getRobosExplorados()){
				if(robo.getCoordenadaRobo().getX()==roboAResolver.getCoordenadaRobo().getX() &&
						robo.getCoordenadaRobo().getY()==roboAResolver.getCoordenadaRobo().getY()){
					roboAResolver.setEquipoDeRobo(robo.getEquipoDeRobo());
					break;
				}
			}
						
			int pos = 0;
			ListaParejas lParejas = new ListaParejas();
			ArrayList<String> ladrones = mapa.getLadrones(eComisaria.getRoboAResolver().getEquipoDeRobo());
			ArrayList<String> policias = eComisaria.obtenerParejas(eComisaria.getRoboAResolver().getEquipoDeRobo());
			
			for(String a : policias){
				ParejaLadronPolicia pareja = new ParejaLadronPolicia(ladrones.get(pos), a);
				pareja.setCaminoAComisaria(mapa.obtenerCaminoMinimo(eComisaria.getRoboAResolver().getCoordenadaRobo(), eComisaria.getCoordenadas()));
				lParejas.addPareja(pareja);
				pos++;
			}		
			
			this.getComunicator().informaraGrupoAgentes(lParejas, policias);
			
			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Informar pareja ladron-policia "+agente.toString(), InfoTraza.NivelTraza.info));     
	        
	        System.out.println(this.identAgente+": Informar pareja ladron-policia"+agente.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
