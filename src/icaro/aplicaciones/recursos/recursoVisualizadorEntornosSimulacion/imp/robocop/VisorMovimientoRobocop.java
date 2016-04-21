package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
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
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.getContentPane().setLayout(new GridLayout(3, 2));
	    
	    for(int i = 0; i < 6; i++){
	    	this.getContentPane().add(new JButton("Button "+i));
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

}