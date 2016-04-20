package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VisorMovimientoRobocop extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  VisorMovimientoRobocop() throws Exception {
		initComponentes();
    }
	
	private void initComponentes() {
		JFrame.setDefaultLookAndFeelDecorated(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLayout(new GridLayout(3, 2));
	    
	    for(int i = 0; i < 6; i++){
	    	this.add(new JButton("Button "+i));
	    }
	    this.pack();
	    this.setVisible(false);
		
	}

	public void visualizarEscenario() {
		this.setVisible(true);
	}

}
