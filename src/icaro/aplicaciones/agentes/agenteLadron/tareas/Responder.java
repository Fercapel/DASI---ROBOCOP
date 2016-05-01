package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.Robocop.informacion.RespuestaIdentificacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class Responder extends TareaSincrona{
	@Override
	public void ejecutar(Object... params) {
		try {
			String tipo = (String) params[0];
			String destinatario = (String) params[1];

			String nombreAgenteEmisor = this.getIdentAgente();

			trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, "Se ejecuta la tarea " + this.identTarea + " Emisor: "+ nombreAgenteEmisor+" Tipo: " +tipo + " Receptor: "+ destinatario+"\n" );

			RespuestaIdentificacion respuesta = new RespuestaIdentificacion (nombreAgenteEmisor, nombreAgenteEmisor, nombreAgenteEmisor);


			System.out.println("-----------------------------------------------------------Ejecutando petición de identificación: "+ nombreAgenteEmisor +" " + tipo + " " + destinatario);                    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}