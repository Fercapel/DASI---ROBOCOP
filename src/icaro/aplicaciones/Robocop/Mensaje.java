package icaro.aplicaciones.Robocop;

import java.io.Serializable;

public class Mensaje implements Serializable {


	public String identAgente;
	public String identAgenteReceptor;
	public String mensaje;
	
	public Mensaje() {

	}

	public Mensaje(String identAgenteEmisor) {
		identAgente = identAgenteEmisor;
		mensaje = "";
	}
	
	public Mensaje(String identAgenteEmisor, String var_mensaje) {
		identAgente = identAgenteEmisor;
		mensaje = var_mensaje;
	}

	public Mensaje(String identAgenteEmisor, String var_mensaje, String var_receptor) {
		identAgente = identAgenteEmisor;
		mensaje = var_mensaje;
		identAgenteReceptor = var_receptor;
	}
	
	
	
	public void setMensaje(String msg) {
		mensaje = msg;
	}

	public String getMensaje() {
		return mensaje;
	}

	public String getIdentAgente() {
		return identAgente;
	}
	
	public String getIdentAgenteReceptor() {
		return identAgenteReceptor;
	}
	
	public void setIdentAgenteReceptor(String identAgenteReceptor) {
		this.identAgenteReceptor = identAgenteReceptor;
	}
	
	public void setIdentAgente(String identAgente) {
		this.identAgente = identAgente;
	}
	
}
