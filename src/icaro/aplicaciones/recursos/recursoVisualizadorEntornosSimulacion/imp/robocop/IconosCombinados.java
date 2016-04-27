package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/*
 * Clase para mostrar en un Icon más de una imagen, de modo superpuesto
 * Las imagenes que se muestras son las relacionadas con los tipos de estructuras
 * que puede haber en el mapa mostrado por el RecursoVisualizador y los distintos 
 * tipos de agente
 */
public class IconosCombinados implements Icon{
		
	private Icon bg;
	private ArrayList<Icon> ladrones = new ArrayList<Icon>();
	private ArrayList<Icon> policias = new ArrayList<Icon>();
	private ArrayList<Icon> bombas = new ArrayList<Icon>();
	private ArrayList<Icon> ladrones_cap = new ArrayList<Icon>();
	private ArrayList<Icon> artf = new ArrayList<Icon>();

	/*
	 * La Constructora de la clase se inicializa con la imagen de fondo del botón
	 */
    public IconosCombinados(Image bg) {
		this.bg = new ImageIcon(bg);
    }

    public int getIconHeight() {
        return Math.max(bg.getIconHeight(), bg.getIconHeight());
    }

    public int getIconWidth() {
        return Math.max(bg.getIconWidth(), bg.getIconWidth());
    }
    
    public void añadirIcono(EnumAgentes agente) {
    	switch(agente){
			case POLICIA:
				policias.add(new ImageIcon(obtenerImagen(agente)));
				break;
			case LADRON:
				ladrones.add(new ImageIcon(obtenerImagen(agente)));
				break;
			case ARTIFICIERO:
				artf.add(new ImageIcon(obtenerImagen(agente)));
				break;
			case LADRON_CAPTURADO:
				ladrones_cap.add(new ImageIcon(obtenerImagen(agente)));
				break;
			case BOMBA:
				bombas.add(new ImageIcon(obtenerImagen(agente)));
				break;
		}
    }

    public void eliminarIcono(EnumAgentes agente) {
    	switch(agente){
			case POLICIA:
				policias.remove(policias.size()-1);
				break;
			case LADRON:
				ladrones.remove(ladrones.size()-1);
				break;
			case ARTIFICIERO:
				artf.remove(artf.size()-1);
				break;
			case LADRON_CAPTURADO:
				ladrones_cap.remove(ladrones_cap.size()-1);
				break;
			case BOMBA:
				bombas.remove(bombas.size()-1);
				break;
		}
    }
    
	@Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
		int offsetX=0;
		int offsetY=0;
		int n = 8;
		int m = 12;
		bg.paintIcon(c, g, x, y);
		int p=1;
		for(Icon i : this.ladrones){
    		i.paintIcon(c,g,x+offsetX,y+offsetY);
    		offsetX+=n;
    		if(p%4==0){
    			offsetY+=m;
    			offsetX=0;
    		}
    		p++;
    	}
		for(Icon i : this.ladrones_cap){
    		i.paintIcon(c,g,x+offsetX,y+offsetY);
    		offsetX+=n;
    		if(p%4==0){
    			offsetY+=m;
    			offsetX=0;
    		}
    		p++;
    	}
		offsetX=0;
		offsetY+=m;
		p=1;
		for(Icon i : this.policias){
    		i.paintIcon(c,g,x+offsetX,y+offsetY);
    		offsetX+=n;
    		if(p%4==0){
    			offsetY+=m;
    			offsetX=0;
    		}
    		p++;
    	}		
		for(Icon i : this.artf){
    		i.paintIcon(c,g,x+offsetX,y+offsetY);
    	}
		for(Icon i : this.bombas){
    		i.paintIcon(c,g,x+offsetX,y+offsetY);
    	}
    }
	
	/*
	 * Devuelva la imagen correspondiente al tipo de agente
	 */
	private Image obtenerImagen(EnumAgentes agente){
		String file = "";
		switch(agente){
			case POLICIA:
				file = "policia";
				break;
			case LADRON:
				file = "ladron";
				break;
			case ARTIFICIERO:
				file = "artf";
				break;
			case LADRON_CAPTURADO:
				file = "criminal";
				break;
			case BOMBA:
				file = "bomba";
				break;
		}
		
		try {
			return ImageIO.read(getClass().getResource("iconos/"+file+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
