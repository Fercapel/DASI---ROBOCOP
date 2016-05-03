package icaro.aplicaciones.agentes.agenteComisaria.objetivos;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

/*
 * Clase para definir el objetivo de formar equipo
 * */

public class DefinirMiEquipo extends Objetivo {

	public String id;
	public String misionId; // identificador de la mision del equipo

	/** Crea una nueva instancia del objetivo */
	public DefinirMiEquipo() {
		super.setgoalId("DefinirMiEquipo");
	}

	public DefinirMiEquipo(String misionId) {
		super.setgoalId("DefinirMiEquipo");
		this.misionId = misionId;
		super.setobjectReferenceId(misionId);
	}

	public void setmisionId(String misionId) {
		this.misionId = misionId;
		super.setobjectReferenceId(misionId);
	}

	public String getmisionId() {
		return misionId;
	}

}