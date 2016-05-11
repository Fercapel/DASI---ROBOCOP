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
	private IconosCombinados iconos; //Conjunto de imágenes que tienen que aparecer en la casilla
	
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
	 * Concatena un número aleatorio entre nMin y nMax a un String
	 */
	private String nombreConNumero(String nombre, int nMin, int nMax) {
		int n = (int)(Math.random()*nMax + nMin);
		return nombre+n;
	}

	public void añadirAgente(EnumAgentes agente){
		this.iconos.añadirIcono(agente);
		this.repaint();
	}

	public void eliminarAgente(EnumAgentes agente){
		this.iconos.eliminarIcono(agente);
	}
	
	public Construccion getConstruccion(){
		return this.contr;
	}
}
