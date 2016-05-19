package icaro.aplicaciones.agentes.agenteComisaria.tareas;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.EstadoComisaria;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class ObtenerCoordenadas extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		Coordenada c = (Coordenada) params[0];
		EstadoComisaria eComisaria = (EstadoComisaria) params[1];
		Objetivo obj = (Objetivo) params[2];

		eComisaria.setCoordenadaAgente(c.getIdAgente(), c);
		this.getEnvioHechos().eliminarHechoWithoutFireRules(c);
		
		if(eComisaria.estanTodasLasCoordenadas()){
			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Obtenidas todas las coordenadas", InfoTraza.NivelTraza.info));     
	        System.out.println("Obtenidas todas las coordenadas");
	        
	        eComisaria.setPuedoEvaluarCaminos(true);
	        this.getEnvioHechos().actualizarHechoWithoutFireRules(eComisaria);
		} else {			
			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Obtenida Coordenada: " + c.toString(), InfoTraza.NivelTraza.info));      
	        System.out.println("Obtenida Coordenada: " + c.toString());
	        
	        
		}	
		obj.setPending();
        this.getEnvioHechos().actualizarHecho(obj);
		
	}

}
