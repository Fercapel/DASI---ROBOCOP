package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.Robocop.CoordenadaLadron;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class IrLugarRobo extends TareaSincrona{

	//private ItfUsoMovimientoCtrlRobocop itfcompMovRob;
	//private ItfUsoRecursoVisualizadorEntornoSimulacion itfVisualizacion;

	private String nombreAgenteEmisor;
	
	private int coord_x;
	private int coord_y;
	private CoordenadaLadron coordRobo;


	@Override
	public void ejecutar(Object... params) {
	
		try {
			
			nombreAgenteEmisor = this.getIdentAgente();
			System.out.println(nombreAgenteEmisor);
			
			this.coordRobo = (CoordenadaLadron)params[0];			

			this.coord_x = this.coordRobo.getX();System.out.println(coord_x);
			this.coord_y = this.coordRobo.getY();System.out.println(coord_y);
			String nombreLadron = this.coordRobo.getNombreLadron();System.out.println(nombreLadron);
			if(nombreAgenteEmisor.equals(nombreLadron)) System.out.println("Alguien ha pedido al agente " + nombreLadron + " que se mueva a (" + coord_x + ", " + coord_y + "). Como soy yo, voy a moverme");
			
			ItfUsoRecursoVisualizadorEntornoSimulacion itfVisualizacion = (ItfUsoRecursoVisualizadorEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");
			
			System.out.println("Soy:"+itfVisualizacion);
			itfVisualizacion.mostrarPosicionRobot(nombreAgenteEmisor, coord_x, coord_y);




			trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, "Se ejecuta la tarea " + this.identTarea + " Emisor: "+ nombreAgenteEmisor+"\n" );

			System.out.println("-----------------------------------------------------------Ejecutando desplazar ladron: "+ nombreAgenteEmisor + "a: " + coord_x + ", " +coord_y);  

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
