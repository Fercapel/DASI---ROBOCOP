package icaro.aplicaciones.Robocop;

import java.util.ArrayList;
import java.util.List;

public class EstadoLadron extends EstadoAgente{

	private ArrayList<PropuestaDeRobo> propuestasRobo;
	
	private ArrayList<String> compa�eros;
	
	private List<Coordenada> camino;
	
	private Coordenada coordenadasDelRobo;
	
	private boolean propuestaRealizada;
	
	private boolean iniciarRobo;
	private boolean esperandoCompa�eros;
	
	private ArrayList<String> compa�erosPreparados;
	
	private String estado; // "Libre", "Detenido", "Encarcelado"
	
	public EstadoLadron(String id) {
		super(id);
		this.propuestasRobo = new ArrayList<PropuestaDeRobo>();
		this.compa�eros = new ArrayList<String>();
		this.compa�erosPreparados = new ArrayList<String>();
		this.camino = new ArrayList<Coordenada>();
		this.iniciarRobo = false;
		this.esperandoCompa�eros = false;
		this.propuestaRealizada = false;
		this.estado = "Libre";
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
	
	public void a�adirCompa�eroPreparado(String compi){
		if(!compa�erosPreparados.contains(compi))
			compa�erosPreparados.add(compi);
	}
	
	public boolean estanTodosPreparados(){
		return compa�erosPreparados.size() == compa�eros.size();
	}
	
	public void setEsperandoCompa�eros(boolean esperandoCompa�eros) {
		this.esperandoCompa�eros = esperandoCompa�eros;
	}
	
	public boolean isEsperandoCompa�eros() {
		return esperandoCompa�eros;
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
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
}
