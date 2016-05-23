package icaro.aplicaciones.Robocop;

import java.util.ArrayList;

public class ListaParejas {
	ArrayList<ParejaLadronPolicia> parejas;
	
	public ListaParejas(){
		parejas = new ArrayList<ParejaLadronPolicia>();
	}
	
	public ArrayList<ParejaLadronPolicia> getParejas() {
		return parejas;
	}
	
	public void setParejas(ArrayList<ParejaLadronPolicia> parejas) {
		this.parejas = parejas;
	}
	
	public void addPareja(ParejaLadronPolicia pareja){
		this.parejas.add(pareja);
	}
}
