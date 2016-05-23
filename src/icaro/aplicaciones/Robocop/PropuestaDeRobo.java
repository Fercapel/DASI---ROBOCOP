package icaro.aplicaciones.Robocop;

import java.util.ArrayList;

public class PropuestaDeRobo {

	private int coste;
	private ArrayList<String> idEvaluadores;
	private Coordenada coordenada;
	
	public PropuestaDeRobo(String idAgente, Coordenada c, int coste){
		this.coste = coste;
		this.idEvaluadores = new ArrayList<String>();
		this.idEvaluadores.add(idAgente);
		this.coordenada = c;
	}

	public void añadirEvaluacion(int coste, String id){
		if(!this.idEvaluadores.contains(id)){
			this.coste += coste;
			this.idEvaluadores.add(id);
		}
	}
	
	public int getCoste() {
		return coste;
	}
	
	public Coordenada getCoordenada() {
		return coordenada;
	}
	
	public ArrayList<String> getIdEvaluadores() {
		return idEvaluadores;
	}
}
