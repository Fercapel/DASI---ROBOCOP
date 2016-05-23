package icaro.aplicaciones.Robocop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EstadoComisaria extends EstadoAgente {

	private ArrayList<String> misAgentes;
	private ArrayList<String> agentesOcupados;
	
	private Map<String, Coordenada> coordenadasAgente;
	
	private ArrayList<RoboEnProceso> robosASofocar;
	private ArrayList<RoboEnProceso> robosAEnviarRefuerzos;
	private ArrayList<RoboEnProceso> robosSofocados;
	
	private boolean puedoEvaluarCaminos;
	
	public EstadoComisaria(String id) {
		super(id);
		puedoEvaluarCaminos = false;
		misAgentes = new ArrayList<String>();
		robosASofocar = new ArrayList<RoboEnProceso>();
		robosAEnviarRefuerzos = new ArrayList<RoboEnProceso>();
		robosSofocados = new ArrayList<RoboEnProceso>();
		coordenadasAgente = new HashMap<String, Coordenada>();
		agentesOcupados = new ArrayList<String>();
	}
	
	public ArrayList<String> getMisAgentes() {
		return misAgentes;
	}
	
	public void setMisAgentes(ArrayList<String> misAgentes) {
		this.misAgentes = misAgentes;
	}
	
	public void addAgente(String idAgente){
		misAgentes.add(idAgente);
	}
	
	public void setCoordenadaAgente(String idAgente, Coordenada c){
		coordenadasAgente.put(idAgente, c);
	}
	
	public Coordenada getCoordenadaAgente(String idAgente){
		return coordenadasAgente.get(idAgente);
	}
	
	public boolean estanTodasLasCoordenadas(){
		return misAgentes.size() == coordenadasAgente.size();
	}
	
	public void limpiarCoordenadas(){
		coordenadasAgente.clear();
	}
	
	public boolean isPuedoEvaluarCaminos() {
		return puedoEvaluarCaminos;
	}
	
	public void setPuedoEvaluarCaminos(boolean puedoEvaluarCaminos) {
		this.puedoEvaluarCaminos = puedoEvaluarCaminos;
	}
	
	public ArrayList<RoboEnProceso> getRobosASofocar() {
		return robosASofocar;
	}
	
	public ArrayList<String> getAgentesOcupados() {
		return agentesOcupados;
	}
	
	public void anadirAgenteOcupado(String agente){
		if(!agentesOcupados.contains(agente))
			agentesOcupados.add(agente);
	}
	
	public void quitarAgenteOcupado(String agente){
		agentesOcupados.remove(agente);
	}
	
	public boolean estaOcupado(String idAgente){
		return agentesOcupados.contains(idAgente);
	}
	
	/*
	 * LISTA DE ROBOS A SOFOCAR
	 */
	public boolean añadirRoboASofocar(RoboEnProceso r){
		for(RoboEnProceso robo : this.robosASofocar){
			if(robo.getCoordenadaRobo().getX()==r.getCoordenadaRobo().getX() && 
					robo.getCoordenadaRobo().getY() == r.getCoordenadaRobo().getY()){
				return false;
			}
		}
				
		this.robosASofocar.add(r);
		return true;
	}
	
	public ArrayList<RoboEnProceso> getRobosSofocados() {
		return robosSofocados;
	}
	
	/*
	 * LISTA DE ROBOS ATENDIDOS
	 */
	public void añadirRoboSofocados(RoboEnProceso r){	
		for(RoboEnProceso robo : this.robosASofocar){
			if(robo.getCoordenadaRobo().getX()==r.getCoordenadaRobo().getX() && robo.getCoordenadaRobo().getY() == r.getCoordenadaRobo().getY()){
				robosASofocar.remove(robo);
				break;
			}
		}
		for(RoboEnProceso robo : this.robosAEnviarRefuerzos){
			if(robo.getCoordenadaRobo().getX()==r.getCoordenadaRobo().getX() && robo.getCoordenadaRobo().getY() == r.getCoordenadaRobo().getY()){
				robosAEnviarRefuerzos.remove(robo);
				break;
			}
		}
		this.robosSofocados.add(r);
	}
	
	public ArrayList<RoboEnProceso> getRobosAEnviarRefuerzos() {
		return robosAEnviarRefuerzos;
	}
	
	/*
	 * LISTA DE ROBOS A ENVIAR REFUERZOS
	 */
	public boolean añadirRoboAEnviarRefuerzos(RoboEnProceso r){			
		for(RoboEnProceso robo : this.robosAEnviarRefuerzos){
			if(robo.getCoordenadaRobo().getX()==r.getCoordenadaRobo().getX() && 
					robo.getCoordenadaRobo().getY() == r.getCoordenadaRobo().getY()){
				return false;
			}
		}
		for(RoboEnProceso robo : this.robosASofocar){
			if(robo.getCoordenadaRobo().getX()==r.getCoordenadaRobo().getX() &&
					robo.getCoordenadaRobo().getY() == r.getCoordenadaRobo().getY()){
				robosASofocar.remove(robo);
				break;
			}
		}
		
		this.robosAEnviarRefuerzos.add(r);
		return true;
	}
}
