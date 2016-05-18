package icaro.aplicaciones.agentes.agenteLadron.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.InfoMapa;
import icaro.aplicaciones.Robocop.informacion.Equipo;
import icaro.aplicaciones.Robocop.informacion.InfoLadron;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornoSimulacion;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class IrLugarRobo extends TareaSincrona{

	//private ItfUsoMovimientoCtrlRobocop itfcompMovRob;
	//private ItfUsoRecursoVisualizadorEntornoSimulacion itfVisualizacion;

	private String nombreAgenteEmisor;
	
	private InfoLadron infoLadron;
	private int coord_x;
	private int coord_y;
	private Coordenada coordRobo;	

	private Equipo equipo;


	@Override
	public void ejecutar(Object... params) {
	
		try {
			
			nombreAgenteEmisor = this.getIdentAgente();
			System.out.println(nombreAgenteEmisor);
			
			
			this.infoLadron = (InfoLadron)params[0];
			this.coordRobo = (Coordenada)params[1];
			
			Coordenada coordActual = infoLadron.getCoordenada();
			
			
			ItfUsoRecursoPersistenciaEntornoSimulacion itfUsoRecursoPersistenciaEntornoSimulacion = (ItfUsoRecursoPersistenciaEntornoSimulacion) this.repoInterfaces.obtenerInterfaz("Itf_Uso_RecursoPersistenciaEntornoSimulacionRobocop");
			InfoMapa mapa = itfUsoRecursoPersistenciaEntornoSimulacion.obtenerMapa();
			
			ItfUsoRecursoVisualizadorEntornoSimulacion itfVisualizacion = (ItfUsoRecursoVisualizadorEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");
			
			
			ArrayList<Coordenada> shortestPath = mapa.obtenerCaminoMinimo(coordActual, coordRobo);
			
			//for (int i = 1; i < shortestPath.size(); i++) {
			if(shortestPath.size() > 1){
				Thread.sleep(200);
				Coordenada step = shortestPath.get(1);
				infoLadron.setCoordenada(step);
				this.getEnvioHechos().actualizarHechoWithoutFireRules(infoLadron);
				System.out.println(nombreAgenteEmisor + " se mueve a " + coordActual.toString());
				itfVisualizacion.mostrarPosicionRobot(nombreAgenteEmisor, step.getX(), step.getY());	
			}
			//}
			else{
				//System.out.println(nombreAgenteEmisor + " ha llegado a destino " + coordRobo.toString());
				infoLadron.setEstadoActual("DESTINO");
				this.getEnvioHechos().actualizarHechoWithoutFireRules(infoLadron);
				
				equipo.llegadaADestino(Integer.parseInt(nombreAgenteEmisor.substring(nombreAgenteEmisor.length() - 1))); //marcar agente como en destino
				
				this.getComunicator().enviarInfoAotroAgente(coordRobo, "Comisaria");
				System.out.println("Hola COMISARIA, soy " + nombreAgenteEmisor + " y mis compañeros y yo vamos a robar en"+ " : (" + coordRobo.getX() + ", " + coordRobo.getY() + ")");
				this.getEnvioHechos().eliminarHechoWithoutFireRules(coordRobo);
			}
			//System.out.println("La coordenada X inicial de " + nombreAgenteEmisor + " es " + coordActual.getX());
			//System.out.println("La coordenada Y inicial de " + nombreAgenteEmisor + " es " + coordActual.getY());
			

			//this.coord_x = this.coordRobo.getX();System.out.println(coord_x);
			//this.coord_y = this.coordRobo.getY();System.out.println(coord_y);
			//String nombreLadron = this.coordRobo.getIdAgente();System.out.println(nombreLadron);
			//if(nombreAgenteEmisor.equals(nombreLadron)) System.out.println("Alguien ha pedido al agente " + nombreLadron + " que se mueva a (" + coord_x + ", " + coord_y + "). Como soy yo, voy a moverme");
			
			//infoLadron.setCoordenada(coordRobo);
			//this.getEnvioHechos().actualizarHechoWithoutFireRules(infoLadron);

			//ItfUsoRecursoVisualizadorEntornoSimulacion itfVisualizacion = (ItfUsoRecursoVisualizadorEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoVisualizadorEntornoSimulacionRobocop");
			
			//System.out.println("Soy:"+itfVisualizacion);
			//itfVisualizacion.mostrarPosicionRobot(nombreAgenteEmisor, coord_x, coord_y);

			//coordActual = infoLadron.getCoordenada();
			//System.out.println("La coordenada X final de " + nombreAgenteEmisor + " es " + coordActual.getX());
			//System.out.println("La coordenada Y final de " + nombreAgenteEmisor + " es " + coordActual.getY());
			
			this.getEnvioHechos().eliminarHechoWithoutFireRules(coordRobo);
			


			trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, "Se ejecuta la tarea " + this.identTarea + " Emisor: "+ nombreAgenteEmisor+"\n" );

			System.out.println("-----------------------------------------------------------Ejecutando desplazar ladron: "+ nombreAgenteEmisor + "a: " + coord_x + ", " +coord_y);  

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
