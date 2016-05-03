package icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop.imp;

import org.apache.log4j.Logger;
import org.openide.util.Exceptions;

import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Informe;
import icaro.infraestructura.patronAgenteCognitivo.procesadorObjetivos.factoriaEInterfacesPrObj.ItfProcesadorObjetivos;

public class HebraMonitorizacionRobocop extends Thread {
	/**
	 * Milisegundos que esperar antes de lanzar otra monitorizacin
	 * 
	 * @uml.property name="milis"
	 */
	protected long milis;

	/**
	 * Indica cundo debe dejar de monitorizar
	 * 
	 * @uml.property name="finalizar"
	 */
	protected volatile boolean finalizar = false;

	/**
	 * Agente reactivo al que se pasan los eventos de monitorizacin
	 * 
	 * @uml.property name="agente"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	// protected ItfUsoMovimientoCtrl controladorMovimiento;
	public MaquinaEstadosMovimientoRobocop controladorMovimiento;
	private Logger log = Logger.getLogger(this.getClass().getSimpleName());
	/**
	 * Evento a producir
	 * 
	 * @uml.property name="evento"
	 */
	private String identDestino, identRobot;
	private volatile int xActual;
	private volatile int yActual;

	private volatile int xDestino;
	private volatile int yDestino;

	private volatile boolean enDestino = false;

	private int dirX = 0, dirY = 0;
	private int intervaloEnvioInformesMs;
	private int distanciaRecorridaEnIntervaloInformes;
	private long tiempoParaAlcanzarDestino = 3000;
	public ItfUsoRecursoVisualizadorEntornoSimulacion itfusoRecVisSimulador;
	public ItfProcesadorObjetivos itfProcObjetivos;

	private double velocidadRobot;

	/**
	 * Constructor
	 *
	 * @param milis
	 *            Milisegundos a esperar entre cada monitorizacion
	 */
	public HebraMonitorizacionRobocop(String idRobot, MaquinaEstadosMovimientoRobocop contrMovimiento,
			ItfUsoRecursoVisualizadorEntornoSimulacion itfRecVisSimulador) {
		super("HebraMonitorizacion " + idRobot);
		controladorMovimiento = contrMovimiento;
		this.itfusoRecVisSimulador = itfRecVisSimulador;
		identRobot = idRobot;
		itfProcObjetivos = contrMovimiento.itfProcObjetivos;
	}

	public synchronized void inicializarDestino(String idDestino, int x, int y, int xDest, int yDest) {
		// this.finalizar= false;
		xActual = x;
		yActual = y;
		xDestino = xDest;
		yDestino = yDest;

		identDestino = idDestino;

		log.debug("Coord Robot " + identRobot + " iniciales -> (" + this.xActual + " , " + this.yActual + ")");
		log.debug("Coord Robot " + identRobot + " destino -> (" + this.xDestino + " , " + this.yDestino + ")");

		this.setDaemon(true);

		this.finalizar = false;
		this.enDestino = false;
		// distanciaDestino = this.distanciaEuclidC1toC2(coordActuales,
		// coordDestino);
		int incrX = (xActual - xDestino);
		int incrY = (yActual - yDestino);
		if (incrX > 0)
			dirX = 1;
		else
			dirX = -1;
		if (incrY > 0)
			dirY = 1;
		else
			dirY = -1;

		if (incrX == 0 && incrY == 0)
			finalizar = true;

		intervaloEnvioInformesMs = (int) velocidadRobot * 50;
		distanciaRecorridaEnIntervaloInformes = 1;

		// try {
		// this.itfusoRecVisSimulador.inicializarDestinoRobot(identRobot,
		// coordActuales, identDestino,coordDestino, velocidadRobot);
		// } catch (Exception ex) {
		// Exceptions.printStackTrace(ex);
		// }
	}

	/**
	 * Termina la monitorizacin
	 */
	public synchronized void finalizar() {
		this.finalizar = true;
		this.notifyAll();

	}

	public synchronized void setCoordRobot(int x, int y) {
		this.xActual = x;
		this.yActual = y;
	}

	public synchronized int[] getCoordRobot() {
		int[] coordenadas = { xActual, yActual };
		return coordenadas;
	}

	public synchronized void setCoordDestino(int xDest, int yDest) {
		try {
			this.xDestino = xDest;
			this.yDestino = yDest;
			if (itfusoRecVisSimulador != null)
				this.itfusoRecVisSimulador.inicializarDestinoRobot(identRobot, xActual, yActual, identDestino, xDestino,
						yDestino);
		} catch (Exception ex) {
			Exceptions.printStackTrace(ex);
		}
	}

	public synchronized int[] getCoordDestino() {
		int[] coordenadas = { xDestino, yDestino };
		return coordenadas;
	}

	public synchronized void setVelocidadRobot(double velRobot) {
		this.velocidadRobot = velRobot;
	}

	@Override
	public synchronized void run() {
		log.debug("Coord Robot " + identRobot + " iniciales -> (" + this.xActual + " , " + this.yActual + ")");
		log.debug("Coord Robot " + identRobot + " destino -> (" + this.xDestino + " , " + this.yDestino + ")");

		log.debug("Inicio ciclo de envio de coordenadas  " + identRobot + " en destino -> (" + this.xActual + " , "
				+ this.yActual + ")");
		while (!this.finalizar && (!enDestino)) {
			try {
				Thread.sleep(intervaloEnvioInformesMs);
				calcularNuevasCoordenadas(distanciaRecorridaEnIntervaloInformes);

				enDestino = ((xActual - xDestino) * dirX >= 0 && (yActual - yDestino) * dirY >= 0);
				finalizar = (xActual < 0.5 || yActual < 0.5); // CHECK THIS
				if (itfusoRecVisSimulador != null)
					this.controladorMovimiento.setCoordenadasActuales(xActual, yActual);
				this.itfusoRecVisSimulador.mostrarPosicionRobot(identRobot, xActual, yActual);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (enDestino) {
			finalizar = true;
			try {
				Thread.sleep(tiempoParaAlcanzarDestino);
				this.controladorMovimiento.estamosEnDestino(identDestino, xDestino, yDestino);
				log.debug("Coord Robot En thread  " + identRobot + " en destino -> (" + this.xActual
						+ " , " + this.yActual + ")");
				System.out.println("Coord Robot En thread  " + identRobot + " en destino -> (" + this.xDestino
						+ " , " + this.yDestino + ")");
				this.controladorMovimiento.setCoordenadasActuales(xDestino, yDestino);
				// se informa al control de que estamos en el destino. Se cambia
				// el estado a parar

				Informe informeLlegada = new Informe(this.identRobot, this.identDestino,
						VocabularioRosace.MsgeLlegadaDestino);

				itfProcObjetivos.insertarHecho(informeLlegada);
				this.notifyAll();
			} catch (Exception ex) {
				log.error(ex);
			}
		}
	}

	private void calcularNuevasCoordenadas(long incrementoDistancia) {
			// incremmento de x respecto a distancia recorrida
			this.yActual = (int) (yActual + incrementoDistancia * dirY);
			this.xActual = (int) (xActual + incrementoDistancia * dirX);
			
	}
}
