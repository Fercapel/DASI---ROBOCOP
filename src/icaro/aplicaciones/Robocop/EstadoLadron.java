package icaro.aplicaciones.Robocop;

import java.util.ArrayList;
import java.util.List;

public class EstadoLadron extends EstadoAgente{

	private ArrayList<PropuestaDeRobo> propuestasRobo;
	
	private ArrayList<String> compañeros;
	
	private List<Coordenada> camino;
	
	private Coordenada coordenadasDelRobo;
	
	private boolean propuestaRealizada;
	private boolean moverseAlRobo;
	
	public EstadoLadron(String id) {
		super(id);
		this.propuestasRobo = new ArrayList<PropuestaDeRobo>();
		this.compañeros = new ArrayList<String>();
		this.camino = new ArrayList<Coordenada>();
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
	
	public void setCamino(List<Coordenada> camino) {
		this.camino = camino;
	}
	
	public List<Coordenada> getCamino() {
		return camino;
	}
	
	public Coordenada getSiguientePaso(){
		if(this.getCamino().isEmpty()){
			return null;
		}
		
		Coordenada c = this.camino.get(0);
		
		if(this.camino.size()==1){
			this.camino.clear();
		} else {
			this.camino = this.camino.subList(1, this.camino.size()-1);
		}
		return c;
	}
}
