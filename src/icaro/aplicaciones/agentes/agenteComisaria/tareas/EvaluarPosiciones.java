package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import icaro.aplicaciones.Robocop.InfoEquipo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class EvaluarPosiciones extends TareaSincrona{

	private InfoEquipo miEquipo;
	private ArrayList<>
	
	@Override
	public void ejecutar(Object... params) {
		// TODO Auto-generated method stub
		this.miEquipo = (InfoEquipo) params[0];
		
		
	}

}
