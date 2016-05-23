package icaro.aplicaciones.agentes.agentePolicia.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.Robocop.ListaParejas;
import icaro.aplicaciones.Robocop.ParejaLadronPolicia;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class EsposarLadron extends TareaSincrona{
	
    @Override
	public void ejecutar(Object... params) {
		ListaParejas lParejas = (ListaParejas) params[0];
		
		ParejaLadronPolicia parejaLP = new ParejaLadronPolicia("", "");
		ArrayList<String> ladron = new ArrayList<String>();
		for(ParejaLadronPolicia pareja : lParejas.getParejas()){
			if(pareja.getPolicia().equals(this.getIdentAgente())){
				ladron.add(pareja.getLadron());
				parejaLP = pareja;
				break;
			}
		}
		
		this.getComunicator().informaraGrupoAgentes(parejaLP, ladron);
		this.getEnvioHechos().eliminarHechoWithoutFireRules(lParejas);
		
		trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Esposo al ladron" + ladron.toString(), InfoTraza.NivelTraza.info));     
        
        System.out.println("\n"+this.identAgente+": Esposo al ladron" + ladron.toString()+"\n");
	}

}

