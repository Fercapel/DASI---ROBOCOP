package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.Robocop.informacion.Equipo;
import icaro.aplicaciones.Robocop.informacion.RespuestaIdentificacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class CrearEquipo extends TareaSincrona{
	
	private String nombreAgenteEmisor;
	private String nombreAgenteReceptor;
	private Equipo equipo;
	
	
	@Override
	public void ejecutar(Object... params) {
		try {
			//String tipo = (String) params[0];

			nombreAgenteEmisor = this.getIdentAgente();
			equipo = new Equipo("Ladrones");

			equipo.incluirEnEquipo("Ladron1");
			equipo.incluirEnEquipo("Ladron2");
			
			for(int i = 1; i <= 2; i++){
				
				if(!nombreAgenteEmisor.contains(Integer.toString(i))){
					this.getComunicator().enviarInfoAotroAgente(equipo, "Ladron"+i);

					trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, "Se ejecuta la tarea " + this.identTarea + " Emisor: "+ nombreAgenteEmisor+" Equipo: " +equipo.toString() + " Receptor: "+ "Ladron"+i+"\n" );
					

					System.out.println("-----------------------------------------------------------Ejecutando petici�n de identificaci�n: "+ nombreAgenteEmisor +" " + equipo.toString() + " " + "Ladron"+i);  

				}
			}
			//nombreAgenteReceptor = (String) params[1];

			//RespuestaIdentificacion respuesta = new RespuestaIdentificacion (nombreAgenteEmisor, tipo, nombreAgenteReceptor);
			//this.getComunicator().enviarInfoAotroAgente(respuesta, nombreAgenteReceptor);
			
			
			
                  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
