package icaro.aplicaciones.Robocop;


/*
 * Clase que representa una coordenada en el mapa
 * */
public class CoordenadaLadron {

	private int x;
	private int y;
	private String nombreLadron;
	
	public CoordenadaLadron(int var_x, int var_y, String nombreLadron) {
		this.x = var_x;
		this.y = var_y;
		this.nombreLadron = nombreLadron;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getNombreLadron() {
		return nombreLadron;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setNombreLadron(String nombreLadron) {
		this.nombreLadron = nombreLadron;
	}
}
