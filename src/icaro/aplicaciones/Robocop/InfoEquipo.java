
package icaro.aplicaciones.Robocop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;




public class InfoEquipo {
	private String identEquipo; // Identificacion del equipo
	private String identAgenteJefeEquipo; // Identificacion del agente jefe
	private boolean inicioContactoConEquipo = false; // Si se ha creado el equipo o no
	private Map<String, InfoAgente> teamRobot; // Ids de los agentes del equipo

	public InfoEquipo(String agtePropietarioId, String identEquipo, Map<String, InfoAgente> agentesEquipo) {
		this.identEquipo = identEquipo;
		this.identAgenteJefeEquipo = agtePropietarioId;
		this.inicioContactoConEquipo = false;
		this.teamRobot = new HashMap<String, InfoAgente>();
		
		
		System.out.println("Equipo formado");
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

	public Map<String, InfoAgente> getTeamRobot() {
		return teamRobot;
	}
	
	public void setTeamRobot(Map<String, InfoAgente> teamRobotIds) {
		this.teamRobot = teamRobotIds;
	}
	
	public ArrayList<String> getTeamRobotIds() {
		ArrayList<String> result = new ArrayList<String>();
		for ( String key : this.teamRobot.keySet() ) {
		    result.add(key);
		}
		
		return result;
	}
	
	
	
}
