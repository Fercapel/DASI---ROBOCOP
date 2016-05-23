package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import icaro.aplicaciones.Robocop.EstadoComisaria;
import icaro.aplicaciones.Robocop.RoboEnProceso;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class RegistrarRobo extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		EstadoComisaria eComisaria = (EstadoComisaria) params[0];
		RoboEnProceso robo = (RoboEnProceso) params[1];
		Objetivo obj = (Objetivo) params[2];
		
		if(robo.isSofocado()){
			eComisaria.añadirRoboSofocados(robo.getCoordenadaRobo());
			System.out.println("AÑADIR SOFOCADOS  -V- "+eComisaria.getRobosASofocar().toString());

			obj.setPending();
		} else if(!robo.isNecesitoRefuerzos()){
			if(eComisaria.añadirRoboASofocar(robo.getCoordenadaRobo())){
				obj.setSolved();
			} else {
				obj.setPending();
			}
			System.out.println("AÑADIR A SOFOCAR  -V- "+eComisaria.getRobosASofocar().toString());

		} else {
			if(eComisaria.añadirRoboAEnviarRefuerzos(robo)){
				obj.setSolved();
			} else {
				obj.setPending();
			}
			System.out.println("ENVIAR REFUERZOS  -V- "+eComisaria.getRobosASofocar().toString());

		}
		
		this.getEnvioHechos().eliminarHechoWithoutFireRules(robo);
		this.getEnvioHechos().actualizarHechoWithoutFireRules(eComisaria);
		this.getEnvioHechos().actualizarHecho(obj);
		
		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Hay un robo en "+robo.getCoordenadaRobo().toString(), InfoTraza.NivelTraza.info));     
        
        System.out.println("Hay un robo en "+robo.getCoordenadaRobo().toString());

	}
		
}

