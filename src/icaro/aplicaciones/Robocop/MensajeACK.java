package icaro.aplicaciones.Robocop;

import icaro.infraestructura.entidadesBasicas.comunicacion.MensajeSimple;

import java.io.Serializable;


/*
 * Clase que representa un mensaje de confirmacion de otro mensaje
 * */

// BORRAR

public class MensajeACK implements Serializable{
	
	public String identAgente; // Identificador del agente que emite la confirmacion de la propuesta
    public String msgAceptacion; // Mensaje para confirmar que ha llegado el mensaje del policia
    public MensajeSimple msgAConfirmar; // Objeto que guarda el mensaje mandado por la comisaria
	
    public MensajeACK(MensajeSimple var_msgAConfirmar) {
		// TODO Auto-generated constructor stub
    	this.msgAConfirmar = var_msgAConfirmar;
    	this.identAgente = "";
    	this.msgAceptacion = "";
	}
    
    public MensajeACK(String nombreAgenteEmisor, String mensajeAceptacion) {
		this.identAgente = nombreAgenteEmisor;
		this.msgAceptacion = mensajeAceptacion;
		//this.msgAConfirmar = msgAConfirmar;
	}
    
	public MensajeACK(String nombreAgenteEmisor, String mensajeAceptacion, MensajeSimple msgAConfirmar) {
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
	
	public MensajeSimple getMsgAConfirmar() {
		return msgAConfirmar;
	}
	
	public void setIdentAgente(String identAgente) {
		this.identAgente = identAgente;
	}
	
	public void setMsgAceptacion(String msgAceptacion) {
		this.msgAceptacion = msgAceptacion;
	}
	
	public void setMsgAConfirmar(MensajeSimple msgAConfirmar) {
		this.msgAConfirmar = msgAConfirmar;
	}
}
