package icaro.aplicaciones.Robocop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EstadoComisaria extends EstadoAgente {

	private ArrayList<String> misAgentes;
	private ArrayList<String> agentesOcupados;
	
	private Map<String, Coordenada> coordenadasAgente;
	
	private ArrayList<Coordenada> robosASofocar;
	private ArrayList<RoboEnProceso> robosAEnviarRefuerzos;
	private ArrayList<Coordenada> robosSofocados;
	private ArrayList<Coordenada> robosExplorados;
	
	private boolean puedoEvaluarCaminos;
	
	public EstadoComisaria(String id) {
		super(id);
		puedoEvaluarCaminos = false;
		misAgentes = new ArrayList<String>();
		
		robosASofocar = new ArrayList<Coordenada>();
		robosAEnviarRefuerzos = new ArrayList<RoboEnProceso>();
		robosSofocados = new ArrayList<Coordenada>();
		robosExplorados = new ArrayList<Coordenada>();
		
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
	
	public ArrayList<Coordenada> getRobosASofocar() {
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
	public boolean añadirRoboASofocar(Coordenada c){
		for(Coordenada cd : this.robosExplorados){
			if(cd.getX()==c.getX() && cd.getY() == c.getY()){
				return false;
			}
		}
		for(RoboEnProceso robo : this.robosAEnviarRefuerzos){
			if(robo.getCoordenadaRobo().getX()==c.getX() && robo.getCoordenadaRobo().getY() == c.getY()){
				return false;
			}
		}
		for(Coordenada cord : this.robosASofocar){
			if(cord.getX()==c.getX() && 
					cord.getY() == c.getY()){
				return false;
			}
		}
				
		this.robosASofocar.add(c);
		return true;
	}
	
	public ArrayList<Coordenada> getRobosSofocados() {
		return robosSofocados;
	}
	
	/*
	 * LISTA DE ROBOS EXPLORADOS
	 */
	public boolean añadirRoboExplorado(Coordenada c){
		for(Coordenada cd : this.robosExplorados){
			if(cd.getX()==c.getX() && cd.getY() == c.getY()){
				return false;
			}
		}
		for(Coordenada cord : this.robosASofocar){
			if(cord.getX()==c.getX() && 
					cord.getY() == c.getY()){
				robosASofocar.remove(c);
				break;
			}
		}
				
		this.robosExplorados.add(c);
		return true;
	}
	
	public ArrayList<Coordenada> getRobosExplorados() {
		return robosExplorados;
	}
	
	/*
	 * LISTA DE ROBOS ATENDIDOS
	 */
	public void añadirRoboSofocados(Coordenada c){	
		for(Coordenada cd : this.robosASofocar){
			if(cd.getX()==c.getX() && cd.getY() == c.getY()){
				robosASofocar.remove(cd);
				break;
			}
		}
		for(RoboEnProceso robo : this.robosAEnviarRefuerzos){
			if(robo.getCoordenadaRobo().getX()==c.getX() && robo.getCoordenadaRobo().getY() == c.getY()){
				robosAEnviarRefuerzos.remove(robo);
				break;
			}
		}
		this.robosSofocados.add(c);
	}
	
	public ArrayList<RoboEnProceso> getRobosAEnviarRefuerzos() {
		return robosAEnviarRefuerzos;
	}
	
	/*
	 * LISTA DE ROBOS A ENVIAR REFUERZOS
	 */
	public boolean añadirRoboAEnviarRefuerzos(RoboEnProceso robo){	
		for(Coordenada cd : this.robosSofocados){
			if(cd.getX()==robo.getCoordenadaRobo().getX() && cd.getY() == robo.getCoordenadaRobo().getY()){
				return false;
			}
		}
		for(RoboEnProceso roboExt : this.robosAEnviarRefuerzos){
			if(robo.getCoordenadaRobo().getX()==roboExt.getCoordenadaRobo().getX() && 
					robo.getCoordenadaRobo().getY() == roboExt.getCoordenadaRobo().getY()){
				return false;
			}
		}
		for(Coordenada cd : this.robosASofocar){
			if(cd.getX()==robo.getCoordenadaRobo().getX() && cd.getY() == robo.getCoordenadaRobo().getY()){
				robosASofocar.remove(cd);
				break;
			}
		}
		
		this.robosAEnviarRefuerzos.add(robo);
		return true;
	}
}
