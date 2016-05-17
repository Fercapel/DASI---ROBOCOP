package icaro.aplicaciones.agentes.agentePolicia.tareas;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class RecibirCoordenadasLugarRobo extends TareaSincrona{

	private Coordenada coordenadaLugarRobo;
	private int coord_x;
	private int coord_y;
	
	@Override
	public void ejecutar(Object... params) {
		// TODO Auto-generated method stub
		this.coordenadaLugarRobo = (Coordenada)params[0];
		
		this.coord_x = this.coordenadaLugarRobo.getX();
		this.coord_y = this.coordenadaLugarRobo.getY();
		
		// Lo elimino de la base del conocimiento
		this.getEnvioHechos().eliminarHechoWithoutFireRules(coordenadaLugarRobo);
		
		
		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente,
				"Mensaje con las coordenadas del lugar del robo recibido", InfoTraza.NivelTraza.info));
		System.out.println("Mensaje con las coordenadas del lugar del robo recibido: (" + coord_x + ", " + coord_y + ") -------- " + this.getIdentAgente());
	}

}
