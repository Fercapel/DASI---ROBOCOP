package icaro.aplicaciones.Robocop.informacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipo implements Serializable{

	private String idEquipo;
	private ArrayList<String> equipo;
	private ArrayList<Boolean> enDestino;
	private boolean todosEnDestino;
	private boolean inicioContactoConEquipo = false;

	public Equipo(String idEquipo) {
		this.idEquipo = idEquipo;
		this.equipo = new ArrayList<String>();
		this.enDestino = new ArrayList<Boolean>();
		
		//for(int i = 0; i<this.enDestino.size(); i++){
		//	this.enDestino.set(i, false);	
		//}
		this.todosEnDestino = false;
	}

	public Equipo(String idEquipo, ArrayList<String> equipo) {
		this.idEquipo = idEquipo;
		this.equipo = equipo;
		
		for(int i = 0; i<this.enDestino.size(); i++){
			this.enDestino.set(i, false);	
		}
		this.todosEnDestino = false;
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
			this.enDestino.add(false);
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
	
	public boolean todosEnDestino(){
		return this.todosEnDestino;
	}
	
	public void llegadaADestino(int index){
		this.enDestino.set(index, true);
		
		boolean conjunto = true;
		int i = 0;
		while(i<this.enDestino.size() && conjunto){
			conjunto &= this.enDestino.get(i);
			i++;
		}
		this.todosEnDestino = conjunto;
	}
	
	public void cambioDestino(){
		for(int i = 0; i<this.enDestino.size(); i++){
			this.enDestino.set(i, false);	
		}

		this.todosEnDestino = false;
	}

} 
