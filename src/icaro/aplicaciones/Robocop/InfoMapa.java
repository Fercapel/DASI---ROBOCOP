package icaro.aplicaciones.Robocop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop.Construccion;

public class InfoMapa {

	private ArrayList<Construccion> mapa;
	private Map<String, Integer> posicionesIniciales;
	
	private int numCols;
	private int numFilas;
	
	public InfoMapa(){
		this.mapa = new ArrayList<Construccion>();
		this.posicionesIniciales = new HashMap<String, Integer>();
	}
	
	public InfoMapa(int numCols, int numFilas){
		this.mapa = new ArrayList<Construccion>();
		this.numCols = numCols;
		this.numFilas = numFilas;
		for(int i = 0; i<numCols*numFilas; i++){
			this.mapa.add(Construccion.CALLE);
		}
	}
	
	public ArrayList<Construccion> getConstrucciones(){
		return mapa;
	}
	
	public void setPosicionInicial(String id, int x, int y){
		if(x<numFilas && y<numCols){
			posicionesIniciales.put(id, y*numCols+x);
			return;
		}
		
		posicionesIniciales.put(id, posicionRandom());
	}
	
	public int getPosicionInicial(String id){
		if(posicionesIniciales.containsKey(id)){
			return posicionesIniciales.get(id);
		} 
		return posicionRandom();
	}
	
	private int posicionRandom(){
		return (int) Math.ceil(numCols*numFilas*Math.random());
	}
	
	public void insertarCasilla(Construccion c){
		mapa.add(c);
	}
	
	public void insertarCasilla(int pos, Construccion c){
		mapa.set(pos, c);
	}
	
	public void setNumeroColumnas(int n){
		this.numCols = n;
	}
	
	public void setNumeroFilas(int n){
		this.numFilas = n;
	}
	
	public int getNumeroColumnas(){
		return this.numCols;
	}
	
	public int getNumeroFilas(){
		return this.numFilas;
	}
}
