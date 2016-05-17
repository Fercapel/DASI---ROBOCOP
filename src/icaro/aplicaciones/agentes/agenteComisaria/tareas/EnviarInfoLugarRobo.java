package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.InfoEquipo;
import icaro.infraestructura.entidadesBasicas.comunicacion.MensajeSimple;
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
		
		for (int i = 0; i < miEquipo.getTeamRobotIds().size(); i++){
			
			this.coordEdificio = new Coordenada(coord_x, coord_y, miEquipo.getTeamRobotIds().get(i));
			this.getComunicator().enviarInfoAotroAgente(this.coordEdificio, miEquipo.getTeamRobotIds().get(i));
			
			// Insertar en la base de conocimiento
			this.getEnvioHechos().actualizarHechoWithoutFireRules(this.coordEdificio);
			
			
			System.out.println("Mensaje coordenada enviado a " + miEquipo.getTeamRobotIds().get(i));
		}
		
		
		//MensajeSimple mensaje_coordendas = new MensajeSimple(this.coordEdificio, this.getIdentAgente(), miEquipo);
		
		
		
		
		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente,
				"Mensaje con las coordenadas del lugar del robo enviado", InfoTraza.NivelTraza.info));
		//System.out.println("Mensaje con las coordenadas del lugar del robo enviado: (" + coord_x + ", " + coord_y + ")");
	}

}