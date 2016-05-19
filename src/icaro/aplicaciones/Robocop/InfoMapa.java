package icaro.aplicaciones.Robocop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;

import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop.Construccion;

public class InfoMapa{

	private ArrayList<Construccion> mapa;
	
	private Map<String, Integer> posicionesIniciales;
	
	private ArrayList<String> polis;
	
	private Graph<Integer, Arista> _grafo;
	
	private int numCols;
	private int numFilas;
	
	public InfoMapa(){
		this.mapa = new ArrayList<Construccion>();
		this.posicionesIniciales = new HashMap<String, Integer>();
		this.polis = new ArrayList<String>();
	}
	
	public InfoMapa(int numCols, int numFilas){
		this.mapa = new ArrayList<Construccion>();
		this.posicionesIniciales = new HashMap<String, Integer>();
		this.polis = new ArrayList<String>();
		this.numCols = numCols;
		this.numFilas = numFilas;
		for(int i = 0; i<numCols*numFilas; i++){
			this.mapa.add(Construccion.CALLE);
		}
		rellenarGrafo();
	}
	
	public void rellenarGrafo(){
		_grafo = new DefaultDirectedGraph<Integer, Arista>(Arista.class);
		
		int V = numFilas * numCols;
	    ArrayList<Construccion> auxmapa = mapa;
	    
	    Construccion[][] tablero = new Construccion[numFilas][numCols];
	    for(int i = 0; i < V; i++){
	    	int f = i / numCols;
	    	int c = i % numCols;
	    	tablero[f][c] = auxmapa.get(i);
	    	_grafo.addVertex(i );
	    }
	   
	    for(int f = 0; f < numFilas; f++){
	    	for(int c = 0; c < numCols; c++){
	    		int actual = f*numFilas + c,
    				arriba = (f-1)*numFilas + c,
    				abajo = (f+1)*numFilas + c,
    				izquierda = actual -1,
    				derecha = actual +1;
	    		
	    		if(tablero[f][c] == Construccion.CALLE){
	    			if(f == 0){//primera fila del mapa
	    				if(c == 0){//primera columna del mapa
	    					if(tablero[f][c+1] == Construccion.CALLE)
	    						_grafo.addEdge(actual, derecha);
	    					if(tablero[f+1][c] == Construccion.CALLE)//abajo
	    	    				_grafo.addEdge(actual, abajo);	    					
	    				}
	    				
	    				else if(c == numCols -1){    
	    					if(tablero[f][c-1] == Construccion.CALLE)
	    						_grafo.addEdge(actual, izquierda);
	    					if(tablero[f+1][c] == Construccion.CALLE)//abajo
	    	    				_grafo.addEdge(actual, abajo);	
	    				}
	    				
	    				else{
	    					if(tablero[f][c-1] == Construccion.CALLE)
	    						_grafo.addEdge(actual, izquierda);
	    					if(tablero[f][c+1] == Construccion.CALLE)
	    						_grafo.addEdge(actual, derecha);
	    					if(tablero[f+1][c] == Construccion.CALLE)//abajo
	    	    				_grafo.addEdge(actual, abajo);	
	    				}	    				
	    			}
	    			
	    			else if(f == numFilas-1){//ultima fila del mapa
	    				if(c == 0){//primera columna del mapa//	 
//	    					if(tablero[f-1][c] == Construccion.CALLE)//arriba
	    	    				_grafo.addEdge(actual, arriba);	 
	    					if(tablero[f][c+1] == Construccion.CALLE)
	    						_grafo.addEdge(actual, derecha);
	    				}
	    				else if(c == numCols -1){
	    					if(tablero[f][c-1] == Construccion.CALLE)
	    						_grafo.addEdge(actual, izquierda);
//	    					if(tablero[f-1][c] == Construccion.CALLE)//arriba
	    	    				_grafo.addEdge(actual, arriba);	 
	    				}
	    				
	    				else{
	    					if(tablero[f][c-1] == Construccion.CALLE)
	    						_grafo.addEdge(actual, izquierda);
	    					if(tablero[f][c+1] == Construccion.CALLE)
	    						_grafo.addEdge(actual, derecha);
//	    					if(tablero[f-1][c] == Construccion.CALLE)//arriba
	    	    				_grafo.addEdge(actual, arriba);	
	    				}
	    			}
	    			
	    			else{
	    				if(c == 0){//primera columna del mapa
//	    					if(tablero[f-1][c] == Construccion.CALLE)//arriba
	    	    				_grafo.addEdge(actual, arriba);	
	    					if(tablero[f][c+1] == Construccion.CALLE)
	    						_grafo.addEdge(actual, derecha);
	    					if(tablero[f+1][c] == Construccion.CALLE)//abajo
	    	    				_grafo.addEdge(actual, abajo);	
	    				}
	    				
	    				else if(c == numCols -1){
//	    					if(tablero[f-1][c] == Construccion.CALLE)//arriba
	    	    				_grafo.addEdge(actual, arriba);	
	    					if(tablero[f][c-1] == Construccion.CALLE)
	    						_grafo.addEdge(actual, izquierda);
	    					if(tablero[f+1][c] == Construccion.CALLE)//abajo
	    	    				_grafo.addEdge(actual, abajo);	
	    				}
	    				else{
//	    					if(tablero[f-1][c] == Construccion.CALLE)//arriba
	    	    				_grafo.addEdge(actual, arriba);	
	    					if(tablero[f][c-1] == Construccion.CALLE)
	    						_grafo.addEdge(actual, izquierda);	
	    					if(tablero[f][c+1] == Construccion.CALLE)
	    						_grafo.addEdge(actual, derecha);
	    					if(tablero[f+1][c] == Construccion.CALLE)//abajo
	    	    				_grafo.addEdge(actual, abajo);	
	    				}
	    			}
	    		}
	    		else{
	    			if(f == numFilas-1);
	    			else{
	    				if(tablero[f+1][c] == Construccion.CALLE)//abajo
    	    				_grafo.addEdge(actual, abajo);	
	    			}
	    		}
	    	}
	    }
	}
	
	public ArrayList<Construccion> getConstrucciones(){
		return mapa;
	}
	
	public void setPosicionInicial(String id, Coordenada c){
		if(id.toLowerCase().contains("poli")){
			polis.add(id);
		}
		
		if(c.getX()<numFilas && c.getY()<numCols){
			posicionesIniciales.put(id, c.getPosArray(numCols));
			return;
		}		
		
		posicionesIniciales.put(id, posicionRandom().getPosArray(numCols));
	}
	
	public ArrayList<String> getPolicias(){
		return polis;
	}
	
	public Coordenada getPosicionInicial(String id){
		Coordenada c = new Coordenada();
		if(posicionesIniciales.containsKey(id)){
			c.setPosArray(posicionesIniciales.get(id), numCols);
			return c;
		} 
		return posicionRandom();
	}
	
	private Coordenada posicionRandom(){
		Coordenada c = new Coordenada();
		c.setPosArray((int) Math.ceil(numCols*numFilas*Math.random()), numCols);
		return c;
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
	
	public ArrayList<Coordenada> obtenerCaminoMinimo(Coordenada posInicial, Coordenada posFinal)
	{
		ArrayList<Coordenada> coordenadas = new ArrayList<Coordenada>();

        DijkstraShortestPath<Integer, Arista> path2 = new DijkstraShortestPath<Integer, Arista>(_grafo, posInicial.getPosArray(numCols), posFinal.getPosArray(numCols));
        for(Arista a : path2.getPathEdgeList()){
        	Coordenada c = new Coordenada();
        	c.setPosArray(a.getTargetPosition(), numCols);
        	coordenadas.add(c);
        }
        coordenadas.add(posFinal);
        
        return coordenadas;
	}
}
