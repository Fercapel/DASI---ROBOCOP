package icaro.aplicaciones.agentes.agentePolicia.tareas;

import java.util.List;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.EstadoPolicia;
import icaro.aplicaciones.Robocop.InfoMapa;
import icaro.aplicaciones.Robocop.RoboEnProceso;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class ObtenerOrdenMoverme extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		EstadoPolicia ePolicia = (EstadoPolicia) params[0];
		RoboEnProceso robo = (RoboEnProceso) params[1];
		
		ItfUsoRecursoPersistenciaEntornoSimulacion itfCompPer;
		InfoMapa mapa = null;
		try {
			itfCompPer =(ItfUsoRecursoPersistenciaEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoPersistenciaEntornoSimulacionRobocop");
			mapa = itfCompPer.obtenerMapa();
			
			//Calculo mi camino y me lo guardo para moverme
			List<Coordenada> camino = mapa.obtenerCaminoMinimo(ePolicia.getCoordenadas(), robo.getCoordenadaRobo());
			camino.add(robo.getCoordenadaRobo());
			ePolicia.setCamino(camino);
			
			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "He Recibido Orden para moverme " + robo.getCoordenadaRobo().toString(), InfoTraza.NivelTraza.info));     
	        
	        System.out.println(this.identAgente+"He Recibido Orden para moverme " + robo.getCoordenadaRobo().toString() );
			
	        this.getEnvioHechos().actualizarHechoWithoutFireRules(ePolicia);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}