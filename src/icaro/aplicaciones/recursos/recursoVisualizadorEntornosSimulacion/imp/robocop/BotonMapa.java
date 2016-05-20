package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/*
 * JButton para representar la casilla de un mapa
 * mediante distintos iconos
 */
public class BotonMapa extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Construccion contr; //Tipo de casilla
	private IconosCombinados iconos; //Conjunto de im�genes que tienen que aparecer en la casilla
	
	public BotonMapa(Construccion c){
		super();
		this.contr = c;
		Image img = obtenerImagen();
		iconos = new IconosCombinados(img);
		this.setIcon(iconos);
		this.setBorder(null);
	}
	
	private Image obtenerImagen(){
		String file = "";
		switch(contr){
			case CALLE:
				file = "calle";
				break;
			case BANCO:
				file = "banco";
				break;
			case CASA:
				file = nombreConNumero("casa", 1, 3);
				break;
			case COMISARIA:
				file = "comisaria";
				break;
			case ALMACEN:
				file = "almacen";
		}
		
		try {
			return ImageIO.read(getClass().getResource("iconos/"+file+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/*
	 * Concatena un n�mero aleatorio entre nMin y nMax a un String
	 */
	private String nombreConNumero(String nombre, int nMin, int nMax) {
		int n = (int)(Math.random()*nMax + nMin);
		return nombre+n;
	}

	public void a�adirAgente(EnumAgentes agente){
		this.iconos.a�adirIcono(agente);
		this.repaint();
	}

	public void eliminarAgente(EnumAgentes agente){
		this.iconos.eliminarIcono(agente);
	}
	
	public int getNumeroLadrones(){
		return this.iconos.getIconosLadrones().size();
	}
	
	public Construccion getConstruccion(){
		return this.contr;
	}
}
