package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.Robocop.CoordenadaLadron;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class CrearCoordenadaRobo extends TareaSincrona {
	private CoordenadaLadron coordRobo;
	
	@Override
	public void ejecutar(Object... params) {

		String nombreAgenteEmisor = this.getIdentAgente();
		int coord_x = (int)params[0];
		int coord_y = (int)params[1];

		
		//this.coordRobo = new CoordenadaLadron(coord_x, coord_y);

		// Insertar en la base de conocimiento
		
		for(int i = 1; i <= 3; i++){

			this.coordRobo = new CoordenadaLadron(coord_x, coord_y, "Ladron"+i);
			this.getEnvioHechos().actualizarHechoWithoutFireRules(this.coordRobo);
			
			if(!nombreAgenteEmisor.contains(Integer.toString(i))){
				
				this.getComunicator().enviarInfoAotroAgente(this.coordRobo, "Ladron"+i);

			}
			else this.getComunicator().enviarInfoAotroAgente(this.coordRobo, "Comisaria");
		}

		
		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente,
				"Mensaje con las coordenadasRobo enviado por ladron: " + nombreAgenteEmisor, InfoTraza.NivelTraza.info));
		System.out.println("Mensaje con las coordenadasRobo enviado por ladron: " + nombreAgenteEmisor+ " : (" + coord_x + ", " + coord_y + ")");
		System.out.println("Hola COMISARIA, soy " + nombreAgenteEmisor + " y mis compañeros y yo vamos a robar en"+ " : (" + coord_x + ", " + coord_y + ")");
	}
}
