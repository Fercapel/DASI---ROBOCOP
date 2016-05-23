package icaro.aplicaciones.Robocop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EstadoComisaria extends EstadoAgente {

	private ArrayList<String> misAgentes;
	private ArrayList<String> agentesOcupados;
	
	private Map<String, Coordenada> coordenadasAgente;
	
	private Map<String, ArrayList<String>> parejasAgenteLadron;
	
	private ArrayList<RoboEnProceso> robosASofocar;
	private ArrayList<RoboEnProceso> robosAEnviarRefuerzos;
	private ArrayList<RoboEnProceso> robosSofocados;
	private ArrayList<RoboEnProceso> robosExplorados;
	
	private boolean puedoEvaluarCaminos;
	
	private RoboEnProceso roboAResolver;
	
	public EstadoComisaria(String id) {
		super(id);
		puedoEvaluarCaminos = false;
		misAgentes = new ArrayList<String>();
		
		robosASofocar = new ArrayList<RoboEnProceso>();
		robosAEnviarRefuerzos = new ArrayList<RoboEnProceso>();
		robosSofocados = new ArrayList<RoboEnProceso>();
		robosExplorados = new ArrayList<RoboEnProceso>();
		
		parejasAgenteLadron = new HashMap<String, ArrayList<String>>();
		
		coordenadasAgente = new HashMap<String, Coordenada>();
		agentesOcupados = new ArrayList<String>();
	}
	
	public void añadirPareja(String equipo, String policia)	{
		parejasAgenteLadron.get(equipo).add(policia);
	}
	
	public void añadirPareja(String equipo, ArrayList<String> policias)	{
		if(!parejasAgenteLadron.containsKey(equipo)){
			parejasAgenteLadron.put(equipo, policias);
		} else {
			parejasAgenteLadron.get(equipo).addAll(policias);
		}
		
		System.out.println("----VV----"+parejasAgenteLadron.get(equipo).toString()+"-"+equipo);
	}
	
	public ArrayList<String> obtenerParejas(String equipo){
		return parejasAgenteLadron.get(equipo);
	}
	
	public void setRoboAResolver(RoboEnProceso roboAResolver) {
		this.roboAResolver = roboAResolver;
	}
	
	public RoboEnProceso getRoboAResolver() {
		return roboAResolver;
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
	public boolean añadirRoboASofocar(RoboEnProceso c){
		for(RoboEnProceso cd : this.robosExplorados){
			if(cd.getCoordenadaRobo().getX()==c.getCoordenadaRobo().getX() && 
					cd.getCoordenadaRobo().getY() == c.getCoordenadaRobo().getY()){
				return false;
			}
		}
		for(RoboEnProceso robo : this.robosAEnviarRefuerzos){
			if(robo.getCoordenadaRobo().getX()==c.getCoordenadaRobo().getX() && 
					robo.getCoordenadaRobo().getY() == c.getCoordenadaRobo().getY()){
				return false;
			}
		}
		for(RoboEnProceso cord : this.robosASofocar){
			if(cord.getCoordenadaRobo().getX()==c.getCoordenadaRobo().getX() && 
					cord.getCoordenadaRobo().getY() == c.getCoordenadaRobo().getY()){
				return false;
			}
		}
	
		this.robosASofocar.add(c);
		return true;
	}
	
	public ArrayList<RoboEnProceso> getRobosSofocados() {
		return robosSofocados;
	}
	
	/*
	 * LISTA DE ROBOS EXPLORADOS
	 */
	public boolean añadirRoboExplorado(RoboEnProceso c){
		for(RoboEnProceso cd : this.robosExplorados){
			if(cd.getCoordenadaRobo().getX()==c.getCoordenadaRobo().getX() && 
					cd.getCoordenadaRobo().getY() == c.getCoordenadaRobo().getY()){
				return false;
			}
		}
		for(RoboEnProceso cord : this.robosASofocar){
			if(cord.getCoordenadaRobo().getX()==c.getCoordenadaRobo().getX() && 
					cord.getCoordenadaRobo().getY() == c.getCoordenadaRobo().getY()){
				robosASofocar.remove(c);
				c.setEquipoDeRobo(cord.getEquipoDeRobo());
				break;
			}
		}
				
		this.robosExplorados.add(c);
		return true;
	}
	
	public ArrayList<RoboEnProceso> getRobosExplorados() {
		return robosExplorados;
	}
	
	/*
	 * LISTA DE ROBOS ATENDIDOS
	 */
	public void añadirRoboSofocados(RoboEnProceso c){	
		for(RoboEnProceso cd : this.robosExplorados){
			if(cd.getCoordenadaRobo().getX()==c.getCoordenadaRobo().getX() && 
					cd.getCoordenadaRobo().getY() == c.getCoordenadaRobo().getY()){
				c.setEquipoDeRobo(cd.getEquipoDeRobo());
				break;
			}
		}
		
		for(RoboEnProceso cd : this.robosASofocar){
			if(cd.getCoordenadaRobo().getX()==c.getCoordenadaRobo().getX() && 
					cd.getCoordenadaRobo().getY() == c.getCoordenadaRobo().getY()){
				c.setEquipoDeRobo(cd.getEquipoDeRobo());
				robosASofocar.remove(cd);
				break;
			}
		}
		for(RoboEnProceso robo : this.robosAEnviarRefuerzos){
			if(robo.getCoordenadaRobo().getX()==c.getCoordenadaRobo().getX() && 
					robo.getCoordenadaRobo().getY() == c.getCoordenadaRobo().getY()){
				robosAEnviarRefuerzos.remove(robo);
				break;
			}
		}
		for(RoboEnProceso cord : this.robosSofocados){
			if(cord.getCoordenadaRobo().getX()==c.getCoordenadaRobo().getX() && 
					cord.getCoordenadaRobo().getY() == c.getCoordenadaRobo().getY()){
				return;
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
		for(RoboEnProceso cd : this.robosExplorados){
			if(cd.getCoordenadaRobo().getX()==robo.getCoordenadaRobo().getX() && 
					cd.getCoordenadaRobo().getY() == robo.getCoordenadaRobo().getY()){
				robo.setEquipoDeRobo(cd.getEquipoDeRobo());
				break;
			}
		}
		for(RoboEnProceso cd : this.robosSofocados){
			if(cd.getCoordenadaRobo().getX()==robo.getCoordenadaRobo().getX() && 
					cd.getCoordenadaRobo().getY() == robo.getCoordenadaRobo().getY()){
				return false;
			}
		}
		for(RoboEnProceso roboExt : this.robosAEnviarRefuerzos){
			if(robo.getCoordenadaRobo().getX()==roboExt.getCoordenadaRobo().getX() && 
					robo.getCoordenadaRobo().getY() == roboExt.getCoordenadaRobo().getY()){
				return false;
			}
		}
		for(RoboEnProceso cd : this.robosASofocar){
			if(cd.getCoordenadaRobo().getX()==robo.getCoordenadaRobo().getX() && 
					cd.getCoordenadaRobo().getY() == robo.getCoordenadaRobo().getY()){
				robosASofocar.remove(cd);
				break;
			}
		}
		
		this.robosAEnviarRefuerzos.add(robo);
		return true;
	}
}
