package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import icaro.aplicaciones.Robocop.InfoEquipo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

// Clase que va a enviar el lugar del robo a todos los policias del equipo

public class EnviarInfoLugarRobo extends TareaSincrona {

	private InfoEquipo miEquipo;

	@Override
	public void ejecutar(Object... params) {
		// TODO Auto-generated method stub

		miEquipo = (InfoEquipo) params[0];
		this.getComunicator().informaraGrupoAgentes("Hola",
				miEquipo.getTeamRobotIds());
		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente,
				"Mensaje Hola enviado", InfoTraza.NivelTraza.info));
		System.out.println("Mensaje Hola enviado");
	}

}