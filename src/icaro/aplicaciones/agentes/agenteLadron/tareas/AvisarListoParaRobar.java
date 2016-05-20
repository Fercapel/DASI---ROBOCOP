package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.aplicaciones.Robocop.EstadoLadron;
import icaro.infraestructura.entidadesBasicas.comunicacion.MensajeSimple;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class AvisarListoParaRobar extends TareaSincrona{
	
	@Override
	public void ejecutar(Object... params) {
		
		EstadoLadron eLadron = (EstadoLadron) params[0];
		
		eLadron.setIniciarRobo(true);
		this.getEnvioHechos().actualizarHechoWithoutFireRules(eLadron);
		
		MensajeSimple msgListo = new MensajeSimple("EstoyListo", this.getIdentAgente(), "Compañeros");

		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Estoy listo para el robo", InfoTraza.NivelTraza.info));     
        
        System.out.println("Estoy listo para el robo");
        
		this.getComunicator().informaraGrupoAgentes(msgListo, eLadron.getCompañeros());
	}
		
}
