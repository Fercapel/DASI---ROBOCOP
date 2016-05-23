package icaro.aplicaciones.Robocop;

import java.util.ArrayList;
import java.util.List;

public class EstadoPolicia extends EstadoAgente{

	private List<Coordenada> camino;
	
	private boolean pedirRefuerzos;
	
	public EstadoPolicia(String id) {
		super(id);
		this.camino = new ArrayList<Coordenada>();
		this.pedirRefuerzos = false;
	}
	
	public void setCamino(List<Coordenada> camino) {
		this.camino = camino;
	}
	
	public List<Coordenada> getCamino() {
		return camino;
	}
	
	public void setPedirRefuerzos(boolean pedirRefuerzos) {
		this.pedirRefuerzos = pedirRefuerzos;
	}
	
	public boolean isPedirRefuerzos() {
		return pedirRefuerzos;
	}
	
	public Coordenada getSiguientePaso(){
		if(this.getCamino().isEmpty()){
			return null;
		}
		
		Coordenada c = this.camino.get(0);
		
		if(this.camino.size()==1){
			this.camino.clear();
		} else {
			this.camino = this.camino.subList(1, this.camino.size());
		}
		return c;
	}
}
