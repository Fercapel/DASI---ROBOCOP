package icaro.aplicaciones.agentes.agentePolicia.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.Robocop.EstadoPolicia;
import icaro.aplicaciones.Robocop.InfoEquipo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class InformarDePosicion extends TareaSincrona{
	
    @Override
	public void ejecutar(Object... params) {
		InfoEquipo info = (InfoEquipo) params[0];
		EstadoPolicia miEstado = (EstadoPolicia) params[1];
		
		ArrayList<String> jefe = new ArrayList<String>();
		jefe.add(info.getIdentAgenteJefeEquipo());

		String idAgente = this.getIdentAgente();
		Coordenada c = miEstado.getCoordenadas();
		c.setIdAgente(idAgente);
		
		this.getComunicator().informaraGrupoAgentes(c, jefe);
		
		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Mandando mis coordenadas:" + miEstado.getCoordenadas().toString(), InfoTraza.NivelTraza.info));     
        
        System.out.println("\n"+this.identAgente+" Mandando mis coordenadas:" + miEstado.getCoordenadas().toString()+"\n");
	}

}
