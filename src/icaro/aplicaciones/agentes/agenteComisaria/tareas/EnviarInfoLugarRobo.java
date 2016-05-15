package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.InfoEquipo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

// Clase que va a enviar el lugar del robo a todos los policias del equipo

public class EnviarInfoLugarRobo extends TareaSincrona {

	private InfoEquipo miEquipo;
	private Coordenada coordEdificio;
	
	@Override
	public void ejecutar(Object... params) {
		// TODO Auto-generated method stub

		miEquipo = (InfoEquipo) params[0];
		int coord_x = (int)params[1];
		int coord_y = (int)params[2];
		
		this.coordEdificio = new Coordenada(coord_x, coord_y);
		
		this.getComunicator().informaraGrupoAgentes(this.coordEdificio,
				miEquipo.getTeamRobotIds());
		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente,
				"Mensaje con las coordenadas del lugar del robo enviado", InfoTraza.NivelTraza.info));
		System.out.println("Mensaje con las coordenadas del lugar del robo enviado: (" + coord_x + ", " + coord_y + ")");
	}

}