package icaro.aplicaciones.Robocop;

import java.util.ArrayList;

import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop.Construccion;

public class InfoMapa {

	private ArrayList<Construccion> mapa;
	private int numCols;
	private int numFilas;
	
	public InfoMapa(int numCols, int numFilas){
		this.mapa = new ArrayList<Construccion>();
		this.numCols = numCols;
		this.numFilas = numFilas;
		for(int i = 0; i<numCols*numFilas; i++){
			this.mapa.add(Construccion.CALLE);
		}
	}
	
	public void insertarCasilla(int pos, Construccion c){
		mapa.set(pos, c);
	}
}
