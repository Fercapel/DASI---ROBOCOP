package icaro.aplicaciones.Robocop;

public class InfoAgente {

	private String nombreAgente;
	private Coordenada coord;
	
	public InfoAgente(String var_nombreAgente, Coordenada var_coord) {
		this.nombreAgente = var_nombreAgente;
		this.coord = var_coord;
	}

	public String getNombreAgente() {
		return nombreAgente;
	}

	public void setNombreAgente(String nombreAgente) {
		this.nombreAgente = nombreAgente;
	}

	public Coordenada getCoord() {
		return coord;
	}

	public void setCoord(Coordenada coord) {
		this.coord = coord;
	}
	
	
	
}
