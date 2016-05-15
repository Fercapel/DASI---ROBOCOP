package icaro.aplicaciones.Robocop.informacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipo implements Serializable{

	private String idEquipo;
	private ArrayList<String> equipo;
	private boolean inicioContactoConEquipo = false;

	public Equipo(String idEquipo) {
		this.idEquipo = idEquipo;
		this.equipo = new ArrayList<String>();
	}

	public Equipo(String idEquipo, ArrayList<String> equipo) {
		this.idEquipo = idEquipo;
		this.equipo = equipo;
	}
	
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}
	
	public String getIdEquipo(){
		return this.idEquipo;
	}

	public void setEquipo(ArrayList<String> equipo) {
		this.equipo = equipo;
	}
	
	public ArrayList<String> getEquipo(){
		return this.equipo;
	}
	
	public void incluirEnEquipo(String actor){
		if (!this.equipo.contains(actor))
			this.equipo.add(actor);
	}
	
	public boolean estaEnEquipo(String actor){
		return this.equipo.contains(actor);
	}

	@Override
	public String toString() {
		String text = "Equipo " + this.idEquipo + ": ";
		for(int i = 0; i < this.equipo.size(); i++){
			text = text + this.equipo.get(i) + ", ";
		}
		text.substring(0, text.length() - 2);
		return text;
	}

	public boolean getInicioContactoConEquipo() {
		return inicioContactoConEquipo;
	}

	public void setInicioContactoConEquipo(boolean inicioContactoConEquipo) {
		this.inicioContactoConEquipo = inicioContactoConEquipo;
	}

} 
