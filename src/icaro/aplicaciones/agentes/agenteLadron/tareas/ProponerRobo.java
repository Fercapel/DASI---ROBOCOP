package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.EstadoLadron;
import icaro.aplicaciones.Robocop.InfoMapa;
import icaro.aplicaciones.Robocop.PropuestaDeRobo;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornoSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class ProponerRobo extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		EstadoLadron eLadron = (EstadoLadron) params[0];
		
		ItfUsoRecursoPersistenciaEntornoSimulacion itfCompPer;
		InfoMapa mapa = null;
		try {
			itfCompPer =(ItfUsoRecursoPersistenciaEntornoSimulacion) repoInterfaces.obtenerInterfazUso("RecursoPersistenciaEntornoSimulacionRobocop");
			mapa = itfCompPer.obtenerMapa();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		Coordenada c = mapa.obtenerCasaRandom();
		String idAgente = this.getIdentAgente();
		int coste = mapa.obtenerCaminoMinimo(eLadron.getCoordenadas(), c).size();
		
		PropuestaDeRobo propuesta = new PropuestaDeRobo(idAgente, c, coste);

		eLadron.setPropuestaRealizada(true);
		this.getEnvioHechos().actualizarHechoWithoutFireRules(eLadron);

		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Creando Propuesta de Robo:" + c.toString() + " con coste " + coste, InfoTraza.NivelTraza.info));     
        
        System.out.println("Creando Propuesta de Robo:" + c.toString() + " con coste " + coste + " " + eLadron.getCompañeros());
        
		this.getComunicator().informaraGrupoAgentes(propuesta, eLadron.getCompañeros());
	}

}
