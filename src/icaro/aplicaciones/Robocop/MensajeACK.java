package icaro.aplicaciones.Robocop;

import icaro.aplicaciones.Rosace.informacion.PropuestaAgente;

import java.io.Serializable;


/*
 * Clase que representa un mensaje de confirmacion de otro mensaje
 * */
public class MensajeACK implements Serializable{
	
	public String identAgente; // Identificador del agente que emite la confirmacion de la propuesta
    public String msgAceptacion; // Mensaje para confirmar que ha llegado el mensaje del policia
    public Mensaje msgAConfirmar; // Objeto que guarda el mensaje mandado por la comisaria
	
    public MensajeACK(Mensaje var_msgAConfirmar) {
		// TODO Auto-generated constructor stub
    	this.msgAConfirmar = var_msgAConfirmar;
    	this.identAgente = "";
    	this.msgAceptacion = "";
	}
    
	public MensajeACK(String nombreAgenteEmisor, String mensajeAceptacion, Mensaje msgAConfirmar) {
		this.identAgente = nombreAgenteEmisor;
		this.msgAceptacion = mensajeAceptacion;
		this.msgAConfirmar = msgAConfirmar;
	}
	
	public String getIdentAgente() {
		return identAgente;
	}
	
	public String getMsgAceptacion() {
		return msgAceptacion;
	}
	
	public Mensaje getMsgAConfirmar() {
		return msgAConfirmar;
	}
	
	public void setIdentAgente(String identAgente) {
		this.identAgente = identAgente;
	}
	
	public void setMsgAceptacion(String msgAceptacion) {
		this.msgAceptacion = msgAceptacion;
	}
	
	public void setMsgAConfirmar(Mensaje msgAConfirmar) {
		this.msgAConfirmar = msgAConfirmar;
	}
}
