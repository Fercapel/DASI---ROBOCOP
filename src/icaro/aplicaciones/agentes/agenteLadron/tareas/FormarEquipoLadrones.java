package icaro.aplicaciones.agentes.agenteLadron.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.Robocop.EstadoLadron;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class FormarEquipoLadrones extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		EstadoLadron eLadron = (EstadoLadron) params[0];
		
		ItfUsoRecursoPersistenciaEntornoSimulacion itfCompPer;
		try {
			itfCompPer =(ItfUsoRecursoPersistenciaEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoPersistenciaEntornoSimulacionRobocop");
			String idAgente = this.getIdentAgente();
			
			ArrayList <String> agentesLadrones = itfCompPer.obtenerMapa().getLadrones();
			
			for(String l : agentesLadrones){		
				if(!l.equals(idAgente))
					eLadron.añadirCompañeros(l);
			}
			this.getEnvioHechos().actualizarHechoWithoutFireRules(eLadron);
			
			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Equipo de ladrones formado: "+eLadron.getCompañeros(), InfoTraza.NivelTraza.info));     
	        
	        System.out.println("Equipo de ladrones formado "+eLadron.getCompañeros());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}

