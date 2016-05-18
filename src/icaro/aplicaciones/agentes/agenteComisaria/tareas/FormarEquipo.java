package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import icaro.aplicaciones.Robocop.InfoAgente;
import icaro.aplicaciones.Robocop.InfoEquipo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

import java.util.HashMap;

public class FormarEquipo extends TareaSincrona {

	private HashMap<String, InfoAgente> agentesEquipo;

	@Override
	public void ejecutar(Object... params) {
		// TODO Auto-generated method stub

		agentesEquipo = new HashMap<String, InfoAgente>();

		// agentesEquipo.add("Comisaria");
	
		
		/*agentesEquipo.add(new HashMap("Policia1", ));
		agentesEquipo.add("Policia2");*/

		InfoEquipo equipoInfo = new InfoEquipo("Comisaria", "MiEquipo",
				agentesEquipo);

		this.getComunicator().informaraGrupoAgentes(equipoInfo,
				equipoInfo.getTeamRobotIds());
		equipoInfo.setInicioContactoConEquipo(false);
		
		
		// Forma un equipo sin tener conocimiento sobre ello

		// Insertar en la base de conocimiento
		this.getEnvioHechos().actualizarHechoWithoutFireRules(equipoInfo);

		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente,
				"FormandoEquipo ---------"
						+ agentesEquipo.size(), InfoTraza.NivelTraza.info));

		System.out
				.println("FormandoEquipo ---------"
						+ equipoInfo.getTeamRobotIds().size());
	}

}
