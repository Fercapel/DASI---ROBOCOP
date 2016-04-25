package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VisorMovimientoRobocop extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	    	String file = null;
	    	if(i==0){
	    		file = "comisaria";
	    	} else if(i<8 || i%4==0 || (i>8*2&&i<8*3) || (i>8*4&&i<8*5) || (i>8*6&&i<8*7) ){
	    		file = "calle";
	    	} else {
	    		file = "casa1";
	    	}
	    	//-----
	    	this.getContentPane().add(getImage(file));
	    }
	    
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
	
	private JButton getImage(String file){
		JButton button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("iconos/"+file+".jpg"));
			button.setIcon(new ImageIcon(img));
			button.setBorder(null);
		} catch (IOException ex) {
		}
  
		return button;
	}

}