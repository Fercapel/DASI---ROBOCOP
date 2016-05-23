package icaro.aplicaciones.Robocop;

public class RoboEnProceso {
	private Coordenada coordenadaRobo;
	
	private boolean necesitoRefuerzos;
	private int efectivos;
	
	private boolean sofocado;
	
	private String equipoDeRobo;
	
	public RoboEnProceso(Coordenada coordenadaRobo){
		this.necesitoRefuerzos = false;
		this.sofocado = false;
		this.coordenadaRobo = coordenadaRobo;
	}
	
	public RoboEnProceso(Coordenada coordenadaRobo, boolean sofocado){
		this.necesitoRefuerzos = false;
		this.sofocado = sofocado;
		this.coordenadaRobo = coordenadaRobo;
	}
	
	public RoboEnProceso(Coordenada coordenadaRobo, int efectivos){
		this.necesitoRefuerzos = true;
		this.sofocado = false;
		this.coordenadaRobo = coordenadaRobo;
		this.efectivos = efectivos;
	}
	
	public void setEquipoDeRobo(String equipoDeRobo) {
		this.equipoDeRobo = equipoDeRobo;
	}
	
	public String getEquipoDeRobo() {
		return equipoDeRobo;
	}
	
	public Coordenada getCoordenadaRobo() {
		return coordenadaRobo;
	}
	
	public void setEfectivos(int efectivos) {
		this.efectivos = efectivos;
	}
	
	public int getEfectivos() {
		return efectivos;
	}
	
	public boolean isNecesitoRefuerzos() {
		return necesitoRefuerzos;
	}
	
	public boolean isSofocado() {
		return sofocado;
	}
}
