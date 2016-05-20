package icaro.aplicaciones.Robocop;

import java.util.ArrayList;
import java.util.List;

public class EstadoLadron extends EstadoAgente{

	private ArrayList<PropuestaDeRobo> propuestasRobo;
	
	private ArrayList<String> compañeros;
	
	private List<Coordenada> camino;
	
	private Coordenada coordenadasDelRobo;
	
	private boolean propuestaRealizada;
	
	private boolean iniciarRobo;
	private boolean esperandoCompañeros;
	
	private ArrayList<String> compañerosPreparados;
	
	public EstadoLadron(String id) {
		super(id);
		this.propuestasRobo = new ArrayList<PropuestaDeRobo>();
		this.compañeros = new ArrayList<String>();
		this.compañerosPreparados = new ArrayList<String>();
		this.camino = new ArrayList<Coordenada>();
		this.iniciarRobo = false;
		this.esperandoCompañeros = false;
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
	
	public boolean isIniciarRobo() {
		return iniciarRobo;
	}
	
	public void setIniciarRobo(boolean iniciarRobo) {
		this.iniciarRobo = iniciarRobo;
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
	
	public void añadirCompañeroPreparado(String compi){
		if(!compañerosPreparados.contains(compi))
			compañerosPreparados.add(compi);
	}
	
	public boolean estanTodosPreparados(){
		return compañerosPreparados.size() == compañeros.size();
	}
	
	public void setEsperandoCompañeros(boolean esperandoCompañeros) {
		this.esperandoCompañeros = esperandoCompañeros;
	}
	
	public boolean isEsperandoCompañeros() {
		return esperandoCompañeros;
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
