package icaro.aplicaciones.agentes.agentePolicia.tareas;

import icaro.aplicaciones.Robocop.MensajeACK;
import icaro.infraestructura.entidadesBasicas.comunicacion.MensajeSimple;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/*
 * Clase que representa un mensaje de confirmacion a un mensaje enviado
 * */

public class MandarMensajeConfirmacion extends TareaSincrona {

	private String nombreAgenteEmisor; // El que emite la confirmacion
	private String nombreAgenteReceptor; // El que recibe la confirmacion
	private MensajeSimple msgAConfirmar;

	@Override
	public void ejecutar(Object... params) {
		// TODO Auto-generated method stub
		nombreAgenteEmisor = this.getAgente().getIdentAgente();
		msgAConfirmar = (MensajeSimple) params[0]; // Se mete el mensaje por el
												// parametro de la funcion
		nombreAgenteReceptor = (String) msgAConfirmar.getReceptor(); // Se obtiene el
																// receptor a
																// partir del
																// mensaje
																// recibido

		String mensajeDeConfirmacion = "Soy " + nombreAgenteEmisor
				+ " y he recibido un mensaje de " + nombreAgenteReceptor;

		// Creacion del mensaje de confirmacion
		MensajeACK ack = new MensajeACK(nombreAgenteEmisor,
				mensajeDeConfirmacion, msgAConfirmar);

		// Se accede a la clase que comunica dos agentes (la cual esta en la
		// infraestructura de Icaro)
		this.getComunicator().enviarInfoAotroAgente(ack, nombreAgenteReceptor);
		
		trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, mensajeDeConfirmacion);
		System.out.println("*************** Ejecutando ack: "+ mensajeDeConfirmacion );
	}

}
