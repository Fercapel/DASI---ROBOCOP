package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.Robocop.EstadoComisaria;
import icaro.aplicaciones.Robocop.InfoEquipo;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class FormarEquipo extends TareaSincrona{

	private ArrayList <String> agentesEquipo = new ArrayList<String>();
	private ArrayList <String> ladronesSueltos = new ArrayList<String>();
	
	@Override
	public void ejecutar(Object... params) {
		
		EstadoComisaria eComisaria = (EstadoComisaria) params[0];
		
		ItfUsoRecursoPersistenciaEntornoSimulacion itfCompPer;
		try {
			itfCompPer =(ItfUsoRecursoPersistenciaEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoPersistenciaEntornoSimulacionRobocop");
			agentesEquipo = itfCompPer.obtenerMapa().getPolicias();
			ladronesSueltos = itfCompPer.obtenerMapa().getLadrones();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		eComisaria.setMisAgentes(agentesEquipo);
		this.getEnvioHechos().actualizarHechoWithoutFireRules(eComisaria);

		InfoEquipo equipoInfo = new InfoEquipo("Comisaria", "EquipoPolicias", agentesEquipo);
		
		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Numero de agentes de los que espero respuesta:" + agentesEquipo.size(), InfoTraza.NivelTraza.info));     
        
        System.out.println("FormandoEquipo -- Numero de agentes de los que espero respuesta:" + equipoInfo.getTeamRobotIds().size());
        
		this.getComunicator().informaraGrupoAgentes(equipoInfo, equipoInfo.getTeamRobotIds());
		this.getComunicator().informaraGrupoAgentes(eComisaria, ladronesSueltos);
		
        equipoInfo.setInicioContactoConEquipo(true);
	}

}
