package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import icaro.aplicaciones.Robocop.Mensaje;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/*
 * Clase que permite que la comisaria mande un mensaje al policia 
 * */

public class MandarMensajeAPolicia extends TareaSincrona{

	private String nombreAgenteEmisor;
	private String nombreAgenteReceptor;
	
	@Override
	public void ejecutar(Object... params) {
		
		String msg_texto = (String) params[0];
		
		// TODO Auto-generated method stub
		nombreAgenteEmisor = this.getAgente().getIdentAgente(); // Se obtiene el id del agente emisor
		nombreAgenteReceptor = (String)params[1];
		
		// Se crea el objeto mensaje
		Mensaje msg = new Mensaje(nombreAgenteEmisor, msg_texto);
		
		//msg.setMensaje(msg_texto);
		
		// Se accede a la clase que comunica dos agentes (la cual esta en la infraestructura de Icaro)
		this.getComunicator().enviarInfoAotroAgente(msg, nombreAgenteReceptor);
		
		String saludo = "Se ejecuta la tarea " + this.identTarea + " --- " 
				+ nombreAgenteEmisor + " manda un mensaje a " + nombreAgenteReceptor + " : " + msg_texto + "\n"; 
		trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, saludo);
		System.out.println("------------ Ejecutando saludo: "+ saludo );
	}

}
