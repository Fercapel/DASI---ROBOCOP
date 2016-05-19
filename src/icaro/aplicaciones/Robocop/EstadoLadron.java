package icaro.aplicaciones.Robocop;

import java.util.ArrayList;

public class EstadoLadron extends EstadoAgente{

	private ArrayList<PropuestaDeRobo> propuestasRobo;
	
	private ArrayList<String> compañeros;
	
	private Coordenada coordenadasDelRobo;
	
	private boolean propuestaRealizada;
	private boolean moverseAlRobo;
	
	public EstadoLadron(String id) {
		super(id);
		this.propuestasRobo = new ArrayList<PropuestaDeRobo>();
		this.compañeros = new ArrayList<String>();
		this.moverseAlRobo = false;
		this.propuestaRealizada = false;
	}
	
	public void añadirCompañeros(String id){
		this.compañeros.add(id);
	}
	
	public void añadirPropuestaRobo(PropuestaDeRobo robo){
		if(!this.propuestasRobo.contains(robo)){
			this.propuestasRobo.add(robo);
		}
	}
	
	public ArrayList<String> getCompañeros() {
		return compañeros;
	}
	
	public boolean isMoverseAlRobo() {
		return moverseAlRobo;
	}
	
	public void setMoverseAlRobo(boolean moverseAlRobo) {
		this.moverseAlRobo = moverseAlRobo;
	}
	
	public boolean isPropuestaRealizada() {
		return propuestaRealizada;
	}
	
	public void setPropuestaRealizada(boolean propuestaRealizada) {
		this.propuestaRealizada = propuestaRealizada;
	}
	
	public ArrayList<PropuestaDeRobo> getPropuestasRobo() {
		return propuestasRobo;
	}
	
	public void setCoordenadasDelRobo(Coordenada coordenadasDelRobo) {
		this.coordenadasDelRobo = coordenadasDelRobo;
	}
	
	public Coordenada getCoordenadasDelRobo() {
		return coordenadasDelRobo;
	}
}
