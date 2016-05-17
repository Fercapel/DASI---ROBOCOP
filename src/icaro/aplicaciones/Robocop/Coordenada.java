package icaro.aplicaciones.Robocop;

public class Coordenada {

	private int x;
	private int y;
	private String idAgente;

	public Coordenada() {
		this.x = -1;
		this.y = -1;
	}

	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordenada(int x, int y, String idAgente) {
		this.x = x;
		this.y = y;
		this.idAgente = idAgente;
	}

	public String getIdAgente() {
		return idAgente;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setIdAgente(String idAgente) {
		this.idAgente = idAgente;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getPosArray(int nCols) {
		return this.y * nCols + this.x;
	}

	public void setPosArray(int posArray, int nCols) {
		this.x = posArray % nCols;
		this.y = posArray / nCols;
	}
	
	public String toString(){
		return " ("+this.x+", "+this.y+") " ;
	}
}
