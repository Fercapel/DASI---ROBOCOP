package icaro.aplicaciones.Robocop;

import java.util.ArrayList;
import java.util.List;

public class ParejaLadronPolicia {
	private List<Coordenada> caminoAComisaria;
	
	private String policia;
	
	private String ladron;
	
	public ParejaLadronPolicia(String caco, String poli){
		this.policia = poli;
		this.ladron = caco;
		this.caminoAComisaria = new ArrayList<>();
	}
	
	public void setCaminoAComisaria(List<Coordenada> list) {
		this.caminoAComisaria = list;
	}
	
	public List<Coordenada> getCaminoAComisaria() {
		return caminoAComisaria;
	}
	
	public Coordenada getSiguientePaso(){
		if(this.getCaminoAComisaria().isEmpty()){
			return null;
		}
		
		Coordenada c = this.caminoAComisaria.get(0);
		
		if(this.caminoAComisaria.size()==1){
			this.caminoAComisaria.clear();
		} else {
			this.caminoAComisaria = this.caminoAComisaria.subList(1, this.caminoAComisaria.size());
		}
		return c;
	}
	
	public String getLadron() {
		return ladron;
	}
	
	public String getPolicia() {
		return policia;
	}
}
