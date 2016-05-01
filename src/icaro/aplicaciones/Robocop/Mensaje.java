package icaro.aplicaciones.Robocop;

import java.io.Serializable;

public class Mensaje implements Serializable {


	public String identAgente;
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

	public void setMensaje(String msg) {
		mensaje = msg;
	}

	public String getMensaje() {
		return mensaje;
	}

	public String getIdentAgente() {
		return identAgente;
	}

}
