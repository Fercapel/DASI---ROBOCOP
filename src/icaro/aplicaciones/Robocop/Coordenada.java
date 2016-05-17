package icaro.aplicaciones.Robocop;



/*
 * Clase que representa una coordenada en el mapa
 * */
public class Coordenada {

	private int x;
	private int y;
	private String idAgente;
	
	public Coordenada(int var_x, int var_y) {
		this.x = var_x;
		this.y = var_y;
	}
	
	public Coordenada(int var_x, int var_y, String idAgente) {
		this.x = var_x;
		this.y = var_y;
		this.idAgente = idAgente;
	}
	
	public String getIdAgente() {
		return idAgente;
	}
	
	public void setIdAgente(String idAgente) {
		this.idAgente = idAgente;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
