package icaro.aplicaciones.Robocop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EstadoComisaria extends EstadoAgente {

	private ArrayList<String> misAgentes;
	private Map<String, Coordenada> coordenadasAgente;
	
	private boolean puedoEvaluarCaminos;
	
	public EstadoComisaria(String id) {
		super(id);
		puedoEvaluarCaminos = false;
		misAgentes = new ArrayList<String>();
		coordenadasAgente = new HashMap<String, Coordenada>();
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
}
