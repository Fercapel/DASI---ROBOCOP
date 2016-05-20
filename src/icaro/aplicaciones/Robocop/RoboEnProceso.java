package icaro.aplicaciones.Robocop;

public class RoboEnProceso {
	private Coordenada coordenadaRobo;
	
	private boolean necesitoRefuerzos;
	private int efectivos;
	
	private boolean sofocado;
	
	public RoboEnProceso(Coordenada coordenadaRobo){
		this.necesitoRefuerzos = false;
		this.coordenadaRobo = coordenadaRobo;
	}
	
	public RoboEnProceso(Coordenada coordenadaRobo, boolean sofocado){
		this.necesitoRefuerzos = false;
		this.sofocado = sofocado;
		this.coordenadaRobo = coordenadaRobo;
	}
	
	public RoboEnProceso(Coordenada coordenadaRobo, int efectivos){
		this.necesitoRefuerzos = true;
		this.coordenadaRobo = coordenadaRobo;
		this.efectivos = efectivos;
	}
	
	public Coordenada getCoordenadaRobo() {
		return coordenadaRobo;
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
