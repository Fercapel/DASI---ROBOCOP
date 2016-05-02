package icaro.aplicaciones.Robocop.informacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipo implements Serializable{

	public ArrayList<String> equipo;

	public Equipo() {
		this.equipo = new ArrayList<String>();
	}

	public Equipo(ArrayList<String> equipo) {
		this.equipo = equipo;
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
		String text = "Equipo: ";
		for(int i = 0; i < this.equipo.size(); i++){
			text = text + this.equipo.get(i) + ", ";
		}
		text.substring(0, text.length() - 2);
		return text;
	}

} 
