package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

public class VisorMovimientoRobocop extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<BotonMapa> botonesMapa = new ArrayList<BotonMapa>();
	private Map<String, Integer> tablaEntidades = new HashMap<String, Integer>();
	
	private int columnas=0;
	private int filas=0;

	public VisorMovimientoRobocop() throws Exception {
		filas=8;
		columnas=8;
		initialize();
	}

	/**
	 * Inicializar contenido de la ventana.
	 */
	private void initialize() {
		this.setBounds(500, 500, 384, 384);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JFrame.setDefaultLookAndFeelDecorated(true);
		this.getContentPane().setLayout(new GridLayout(filas, columnas));

		for (int i = 0; i < 64; i++) {
			// ----
			// Ejemplo (probando mapa)
			// TODO leer mapa de archivo
			Construccion c;
			if (i == 0) {
				c = Construccion.COMISARIA;
			} else if (i == 50 || i == 54) {
				c = Construccion.ALMACEN;
			} else if (i % 4 == 0 || (i > 8 * 1 && i < 8 * 2) || (i > 8 * 3 && i < 8 * 4) || (i > 8 * 5 && i < 8 * 6)
					|| (i > 8 * 7 && i < 8 * 8)) {
				c = Construccion.CALLE;
			} else {
				c = Construccion.CASA;
			}
			// -----
			BotonMapa b = new BotonMapa(c);
			this.getContentPane().add(b);
			botonesMapa.add(b);
		}

		// Ejemplo (posicionando policias y ladrones)
		//
		/*
		 * botonesMapa.get(0).añadirAgente(EnumAgentes.POLICIA);
		 * botonesMapa.get(14).añadirAgente(EnumAgentes.POLICIA);
		 * 
		 * botonesMapa.get(30).añadirAgente(EnumAgentes.POLICIA);
		 * botonesMapa.get(30).añadirAgente(EnumAgentes.POLICIA);
		 * botonesMapa.get(30).añadirAgente(EnumAgentes.POLICIA);
		 * botonesMapa.get(30).añadirAgente(EnumAgentes.POLICIA);
		 * botonesMapa.get(30).añadirAgente(EnumAgentes.BOMBA);
		 * botonesMapa.get(30).añadirAgente(EnumAgentes.LADRON);
		 * botonesMapa.get(30).añadirAgente(EnumAgentes.LADRON_CAPTURADO);
		 * 
		 * botonesMapa.get(56).añadirAgente(EnumAgentes.LADRON);
		 * botonesMapa.get(46).añadirAgente(EnumAgentes.LADRON);
		 */
		// --------------------------------------------------

		this.pack();
		this.setVisible(false);
	}

	public synchronized void cambiarPosicionRobot(String idRobot, int x, int y) {
		System.out.println("----OOOOO----"+idRobot+" "+x+" "+y);
		Integer pos = tablaEntidades.get(idRobot);
		int newPos = y*filas + x;
		EnumAgentes agente = tipoAgente(idRobot);
		if (pos != null) {
			botonesMapa.get(pos).eliminarAgente(agente);
		} else
			System.out.println("jlabel nulo");
		
		tablaEntidades.put(idRobot, newPos);
		botonesMapa.get(newPos).añadirAgente(agente);
	}

	private EnumAgentes tipoAgente(String idRobot) {
		if (idRobot.toLowerCase().contains("ladron")) {
			return EnumAgentes.LADRON;
		}
		if (idRobot.toLowerCase().contains("policia")) {
			return EnumAgentes.POLICIA;
		}
		return null;
	}

	public void visualizarEscenario() {
		if(!this.isVisible())
			this.setVisible(true);
	}

	/**
	 * Ejecutar ventana.
	 */
	/*
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
	}*/
}