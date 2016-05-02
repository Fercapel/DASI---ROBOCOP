package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.Robocop.InfoEquipo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class FormarEquipo extends TareaSincrona{

	private ArrayList <String> agentesEquipo;
	
	@Override
	public void ejecutar(Object... params) {
		// TODO Auto-generated method stub
		
		agentesEquipo = new ArrayList<String>();
		
		
		agentesEquipo.add("Comisaria");
		agentesEquipo.add("Policia1");
		agentesEquipo.add("Policia2");
		
		
		InfoEquipo equipoInfo = new InfoEquipo("Comisaria", "MiEquipo", agentesEquipo);
		
		
		this.getComunicator().informaraGrupoAgentes("Formando Equipo", equipoInfo.getTeamRobotIds());
        equipoInfo.setInicioContactoConEquipo(true);
        
        trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Numero de agentes de los que espero respuesta:" + agentesEquipo.size(), InfoTraza.NivelTraza.info));     
        
        System.out.println("FormandoEquipo -- Numero de agentes de los que espero respuesta:" + equipoInfo.getTeamRobotIds().size());
	}

}
