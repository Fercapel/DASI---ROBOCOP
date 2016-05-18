package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.informacion.Equipo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class CrearCoordenadaRobo extends TareaSincrona {
	private Coordenada coordRobo;
	
	@Override
	public void ejecutar(Object... params) {

		String nombreAgenteEmisor = this.getIdentAgente();
		int coord_x = (int)params[1];
		int coord_y = (int)params[2];

		Equipo eq = (Equipo)params[0];
		//this.coordRobo = new CoordenadaLadron(coord_x, coord_y);

		// Insertar en la base de conocimiento
		
		//for(int i = 1; i <= 3; i++){
		if(nombreAgenteEmisor.contains(Integer.toString(1))){

			this.coordRobo = new Coordenada(coord_x, coord_y, "Ladron"+Integer.toString(2));
			this.getComunicator().enviarInfoAotroAgente(this.coordRobo, "Ladron"+Integer.toString(2));
		}
		else{ if(nombreAgenteEmisor.contains(Integer.toString(2))){

			this.coordRobo = new Coordenada(coord_x, coord_y, "Ladron"+Integer.toString(3));
			this.getComunicator().enviarInfoAotroAgente(this.coordRobo, "Ladron"+Integer.toString(3));
		
			}
		
			
			else{ this.coordRobo = new Coordenada(coord_x, coord_y, "Ladron"+Integer.toString(1));
				this.getComunicator().enviarInfoAotroAgente(this.coordRobo, "Ladron"+Integer.toString(1));
			}
		}
		
			this.getEnvioHechos().actualizarHechoWithoutFireRules(this.coordRobo);
			
			//if(!nombreAgenteEmisor.contains(Integer.toString(i))){
				
				//this.getComunicator().enviarInfoAotroAgente(this.coordRobo, "Ladron"+i);

			//}
		//}

		
		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente,
				"Mensaje con las coordenadasRobo enviado por ladron: " + nombreAgenteEmisor, InfoTraza.NivelTraza.info));
		System.out.println("Mensaje con las coordenadasRobo enviado por ladron: " + nombreAgenteEmisor+ " : (" + coord_x + ", " + coord_y + ")");
	}
}
