package icaro.aplicaciones.agentes.agenteLadron.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.Robocop.EstadoComisaria;
import icaro.aplicaciones.Robocop.EstadoLadron;
import icaro.aplicaciones.Robocop.RoboEnProceso;
import icaro.infraestructura.entidadesBasicas.comunicacion.MensajeSimple;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class IniciarRobo extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		EstadoLadron eLadron = (EstadoLadron) params[0];
		EstadoComisaria eComisaria = (EstadoComisaria) params[1];
		MensajeSimple msg = (MensajeSimple) params[2];
		Objetivo obj = (Objetivo) params[3];
		
		eLadron.a�adirCompa�eroPreparado((String)msg.getEmisor());
		this.getEnvioHechos().eliminarHechoWithoutFireRules(msg);
		
		if(eLadron.estanTodosPreparados()){
			ArrayList<String> comisaria = new ArrayList<String>();
			comisaria.add(eComisaria.getIdAgente());
			this.getComunicator().informaraGrupoAgentes(new RoboEnProceso(eLadron.getCoordenadasDelRobo()), comisaria);
			obj.setSolved();
			
			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Estamos todos: Vamos a iniciar el robo", InfoTraza.NivelTraza.info));     
	        
	        System.out.println(this.identAgente +": Estamos todos: Vamos a iniciar el robo");
		} else {
			obj.setPending();
			
			trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Esperando m�s compa�eros", InfoTraza.NivelTraza.info));     
	        
	        System.out.println("Esperando m�s compa�eros");
		}
		
		this.getEnvioHechos().actualizarHecho(obj);
	}
}