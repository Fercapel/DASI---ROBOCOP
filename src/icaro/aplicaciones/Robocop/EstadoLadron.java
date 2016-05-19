package icaro.aplicaciones.Robocop;

import java.util.ArrayList;

public class EstadoLadron extends EstadoAgente{

	private ArrayList<PropuestaDeRobo> propuestasRobo;
	
	private ArrayList<String> compa�eros;
	
	private Coordenada coordenadasDelRobo;
	
	private boolean propuestaRealizada;
	private boolean moverseAlRobo;
	
	public EstadoLadron(String id) {
		super(id);
		this.propuestasRobo = new ArrayList<PropuestaDeRobo>();
		this.compa�eros = new ArrayList<String>();
		this.moverseAlRobo = false;
		this.propuestaRealizada = false;
	}
	
	public void a�adirCompa�eros(String id){
		this.compa�eros.add(id);
	}
	
	public void a�adirPropuestaRobo(PropuestaDeRobo robo){
		if(!this.propuestasRobo.contains(robo)){
			this.propuestasRobo.add(robo);
		}
	}
	
	public ArrayList<String> getCompa�eros() {
		return compa�eros;
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
