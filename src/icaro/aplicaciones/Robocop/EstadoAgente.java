package icaro.aplicaciones.Robocop;

public class EstadoAgente {
	private String idAgente;
	private Coordenada coordenadas;
	
	public EstadoAgente(String id){
		this.idAgente = id;
	}

	public void setCoordenadas(Coordenada c){
		this.coordenadas = c;
	}
	
	public Coordenada getCoordenadas(){
		return this.coordenadas;
	}
	
	public String getIdAgente() {
		return idAgente;
	}
	
	public void setIdAgente(String idAgente) {
		this.idAgente = idAgente;
	}
}
