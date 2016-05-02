
package icaro.aplicaciones.Robocop;

import java.util.ArrayList;



public class InfoEquipo {
	private String identEquipo; // Identificacion del equipo
	private String identAgenteJefeEquipo; // Identificacion del agente jefe
	private boolean inicioContactoConEquipo = false; // Si se ha creado el equipo o no
	private ArrayList<String> teamRobotIds; // Ids de los agentes del equipo

	public InfoEquipo(String agtePropietarioId, String identEquipo, ArrayList<String> miembros) {
		this.identEquipo = identEquipo;
		this.identAgenteJefeEquipo = agtePropietarioId;
		this.inicioContactoConEquipo = false;
		this.teamRobotIds = new ArrayList<String>();
		
		// Se incluyen los miembros del equipo
		for (int i = 0; i < miembros.size(); i++){
			this.teamRobotIds.add(miembros.get(i));
		}
	}

	public String getIdentEquipo() {
		return identEquipo;
	}

	public void setIdentEquipo(String identEquipo) {
		this.identEquipo = identEquipo;
	}

	public String getIdentAgenteJefeEquipo() {
		return identAgenteJefeEquipo;
	}

	public void setIdentAgenteJefeEquipo(String identAgenteJefeEquipo) {
		this.identAgenteJefeEquipo = identAgenteJefeEquipo;
	}

	public boolean isInicioContactoConEquipo() {
		return inicioContactoConEquipo;
	}

	public void setInicioContactoConEquipo(boolean inicioContactoConEquipo) {
		this.inicioContactoConEquipo = inicioContactoConEquipo;
	}

	public ArrayList<String> getTeamRobotIds() {
		return teamRobotIds;
	}

	public void setTeamRobotIds(ArrayList<String> teamRobotIds) {
		this.teamRobotIds = teamRobotIds;
	}

	
}
