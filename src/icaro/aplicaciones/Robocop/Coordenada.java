package icaro.aplicaciones.Robocop;


/*
 * Clase que representa una coordenada en el mapa
 * */
public class Coordenada {

	private int x;
	private int y;
	
	public Coordenada(int var_x, int var_y) {
		this.x = var_x;
		this.y = var_y;
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
