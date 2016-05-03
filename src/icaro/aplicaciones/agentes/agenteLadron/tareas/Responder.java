package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.Robocop.informacion.RespuestaIdentificacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class Responder extends TareaSincrona{
	
	private String nombreAgenteEmisor;
	private String nombreAgenteReceptor;
	
	
	@Override
	public void ejecutar(Object... params) {
		try {
			String tipo = (String) params[0];

			nombreAgenteEmisor = this.getIdentAgente();
			nombreAgenteReceptor = (String) params[1];

			RespuestaIdentificacion respuesta = new RespuestaIdentificacion (nombreAgenteEmisor, tipo, nombreAgenteReceptor);
			this.getComunicator().enviarInfoAotroAgente(respuesta, nombreAgenteReceptor);
			
			
			trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, "Se ejecuta la tarea " + this.identTarea + " Emisor: "+ nombreAgenteEmisor+" Tipo: " +tipo + " Receptor: "+ nombreAgenteReceptor+"\n" );

			

			System.out.println("-----------------------------------------------------------Ejecutando petición de identificación: "+ nombreAgenteEmisor +" " + tipo + " " + nombreAgenteReceptor);                    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

/*
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

}*/