package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.Robocop.informacion.Equipo;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class IrLugarRobo extends TareaSincrona{

	//private ItfUsoMovimientoCtrlRobocop itfcompMovRob;
	//private ItfUsoRecursoVisualizadorEntornoSimulacion itfVisualizacion;

	private String nombreAgenteEmisor;


	@Override
	public void ejecutar(Object... params) {
	
		try {
			
			nombreAgenteEmisor = this.getIdentAgente();
			System.out.println(nombreAgenteEmisor);
			
			ItfUsoRecursoVisualizadorEntornoSimulacion itfVisualizacion = (ItfUsoRecursoVisualizadorEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");
			
			int x = (int) params[0];System.out.println(x);
			int y = (int) params[1];System.out.println(y);
			System.out.println("Soy:"+itfVisualizacion);
			itfVisualizacion.mostrarPosicionRobot(nombreAgenteEmisor, x, y);




			trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, "Se ejecuta la tarea " + this.identTarea + " Emisor: "+ nombreAgenteEmisor+"\n" );

			System.out.println("-----------------------------------------------------------Ejecutando desplazar ladron: "+ nombreAgenteEmisor + "a: " + x + ", " +y);  

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
