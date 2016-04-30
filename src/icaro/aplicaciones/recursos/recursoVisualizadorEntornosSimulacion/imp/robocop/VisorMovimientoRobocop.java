package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class VisorMovimientoRobocop extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<BotonMapa> botonesMapa = new ArrayList<BotonMapa>();

	public  VisorMovimientoRobocop() throws Exception {
		initialize();
    }
	
	/**
	 * Inicializar contenido de la ventana.
	 */
	private void initialize() {
		this.setBounds(500, 500, 384, 384);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.getContentPane().setLayout(new GridLayout(8, 8));
	    
	    for(int i = 0; i < 64; i++){
	    	//----
	    	//Ejemplo (probando mapa)
	    	//TODO leer mapa de archivo
	    	Construccion c;
	    	if(i==0){
	    		c = Construccion.COMISARIA;
	    	} else if(i==50 || i==54){
		    		c = Construccion.ALMACEN;
	    	} else if(i%4==0 || (i>8*1&&i<8*2) || (i>8*3&&i<8*4) || (i>8*5&&i<8*6) || (i>8*7&&i<8*8)){
	    		c = Construccion.CALLE;
	    	} else {
	    		c = Construccion.CASA;
	    	}
	    	//-----
	    	BotonMapa b = new BotonMapa(c);
	    	this.getContentPane().add(b);
	    	botonesMapa.add(b);
	    }
	    
	    //Ejemplo (posicionando policias y ladrones)
    	//
	    botonesMapa.get(0).a�adirAgente(EnumAgentes.POLICIA);
	    botonesMapa.get(14).a�adirAgente(EnumAgentes.POLICIA);
	    
	    botonesMapa.get(30).a�adirAgente(EnumAgentes.POLICIA);
	    botonesMapa.get(30).a�adirAgente(EnumAgentes.POLICIA);
	    botonesMapa.get(30).a�adirAgente(EnumAgentes.POLICIA);
	    botonesMapa.get(30).a�adirAgente(EnumAgentes.POLICIA);
	    botonesMapa.get(30).a�adirAgente(EnumAgentes.BOMBA);
	    botonesMapa.get(30).a�adirAgente(EnumAgentes.LADRON);
	    botonesMapa.get(30).a�adirAgente(EnumAgentes.LADRON_CAPTURADO);
	    
	    botonesMapa.get(56).a�adirAgente(EnumAgentes.LADRON);
	    botonesMapa.get(46).a�adirAgente(EnumAgentes.LADRON);
	    //--------------------------------------------------
	    
	    this.pack();
	    this.setVisible(false);
	}

	public void visualizarEscenario() {
		this.setVisible(true);
	}
	
	/**
	 * Ejecutar ventana.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisorMovimientoRobocop window = new VisorMovimientoRobocop();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}