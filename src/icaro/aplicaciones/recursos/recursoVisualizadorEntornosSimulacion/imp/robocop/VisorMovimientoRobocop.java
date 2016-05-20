package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.InfoMapa;

public class VisorMovimientoRobocop extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<BotonMapa> botonesMapa = new ArrayList<BotonMapa>();
	private Map<String, Integer> tablaEntidades = new HashMap<String, Integer>();
	
	private InfoMapa mapa;

	public VisorMovimientoRobocop() throws Exception {
		initialize();
	}

	/**
	 * Inicializar contenido de la ventana.
	 */
	private void initialize() {
		this.setBounds(950, 100, 384, 384);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JFrame.setDefaultLookAndFeelDecorated(true);
	}

	public synchronized void cargarMapa(InfoMapa mapa) {
		if(this.mapa == null){
			this.mapa = mapa;
			this.getContentPane().setLayout(new GridLayout(mapa.getNumeroFilas(), mapa.getNumeroColumnas()));
			for(Construccion c : mapa.getConstrucciones()){
				BotonMapa b = new BotonMapa(c);
				this.getContentPane().add(b);
				botonesMapa.add(b);
			}
			this.pack();
			this.setVisible(false);
		}
	}
	
	public synchronized void moverOrigenRobot(String idRobot) {
		Integer pos = tablaEntidades.get(idRobot);
		Coordenada c = mapa.getPosicionInicial(idRobot);
		int newPos = c.getPosArray(mapa.getNumeroColumnas());
		System.out.println("----OOOOO----"+idRobot+" "+c.toString());
		EnumAgentes agente = tipoAgente(idRobot);
		if (pos != null) {
			botonesMapa.get(pos).eliminarAgente(agente);
		} 
		
		tablaEntidades.put(idRobot, newPos);
		botonesMapa.get(newPos).añadirAgente(agente);
	}

	public synchronized void cambiarPosicionRobot(String idRobot, int x, int y) {
		System.out.println("----OOOOO----"+idRobot+" ("+x+", "+y+") ");
		Integer pos = tablaEntidades.get(idRobot);
		int newPos = y*mapa.getNumeroColumnas() + x;
		EnumAgentes agente = tipoAgente(idRobot);
		if (pos != null) {
			botonesMapa.get(pos).eliminarAgente(agente);
		} 
		
		tablaEntidades.put(idRobot, newPos);
		botonesMapa.get(newPos).añadirAgente(agente);
	}
	
	public synchronized void cambiarPosicionRobot(String idRobot, Coordenada c) {
		try {
			wait(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("----OOOOO----"+idRobot+" "+c.toString());
		Integer pos = tablaEntidades.get(idRobot);
		int newPos = c.getPosArray(mapa.getNumeroColumnas());
		EnumAgentes agente = tipoAgente(idRobot);
		if (pos != null) {
			botonesMapa.get(pos).eliminarAgente(agente);
		} 
		
		tablaEntidades.put(idRobot, newPos);
		botonesMapa.get(newPos).añadirAgente(agente);
		this.repaint();
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
	
	public void caminoMinimo(Coordenada cInicio, Coordenada cFinal){
		mapa.obtenerCaminoMinimo(cInicio, cFinal);
	}

	public Coordenada getCoordenadasOrigen(String identRobot) {
		return mapa.getPosicionInicial(identRobot);
	}

	public int getNumeroLadronesEn(Coordenada c){
		return this.botonesMapa.get(c.getPosArray(mapa.getNumeroColumnas())).getNumeroLadrones();
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