package icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.imp.robocop;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.jgraph.graph.DefaultEdge;
import org.netbeans.modules.visual.graph.layout.hierarchicalsupport.DirectedGraph;

import icaro.aplicaciones.Robocop.InfoMapa;
import icaro.aplicaciones.Robocop.InfoVariables;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornoSimulacion;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop.Construccion;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;
import salvo.jesus.graph.WeightedGraph;

//TODO: imports nuevos
import java.util.List;
import org.jgrapht.alg.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;

public class ClaseGeneradoraPersistenciaRobocop extends ImplRecursoSimple
		implements ItfUsoRecursoPersistenciaEntornoSimulacion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InfoMapa mapa;

	public ClaseGeneradoraPersistenciaRobocop(String idRecurso) throws RemoteException {
		super(idRecurso);
	}

	/*
	 * Lee mapas de la forma:
	 * 
	 * numFilas
	 * numColumnas
	 * CHHHHHHH       
	 *   ...
	 * SSSSSSSS
	 * HHSHSHHH
	 * AgenteId;x;y
	 * AgenteId2;x;y
	 *    ...
	 *    
	 * La representación del mapa está formada por los siguientes caracteres:   	
	 * C para comisaria
	 * H para hogares
	 * S para calles
	 * B para banco
	 * otros caracteres para almacenes
	 */
	@Override
	public InfoMapa obtenerMapa() throws Exception {
		if(mapa==null){
			mapa = new InfoMapa();
	
			Charset charset = Charset.forName("UTF8");
			int j = 0;
			int nFilas=0;
			for (String line : Files.readAllLines(Paths.get(InfoVariables.PATH_MAPA), charset)) {
				if (j == 0) {
					nFilas = Integer.parseInt(line);
					mapa.setNumeroFilas(nFilas);
				} else if (j == 1) {
					mapa.setNumeroColumnas(Integer.parseInt(line));
				} else if(j>=nFilas+2){
					String[] vars = line.split(";"); 
					mapa.setPosicionInicial(vars[0], Integer.parseInt(vars[1]), Integer.parseInt(vars[2]));
				} else {
					char[] l = line.toCharArray();
					for (int i = 0; i < line.length(); i++) {
						char c = l[i];
						switch (c) {
						case 'C':
							mapa.insertarCasilla(Construccion.COMISARIA);
							break;
						case 'H':
							mapa.insertarCasilla(Construccion.CASA);
							break;
						case 'S':
							mapa.insertarCasilla(Construccion.CALLE);
							break;
						case 'B':
							mapa.insertarCasilla(Construccion.BANCO);
							break;
						default:
							mapa.insertarCasilla(Construccion.ALMACEN);
							break;
						}
					}
				}
				j++;
			}
		}

//////////////////////////////////////////////////////////////////////////
		//mira los imports nuevos
		Graph<Integer, DefaultWeightedEdge> _grafo =
	            new DefaultDirectedGraph<Integer, DefaultWeightedEdge>
	            (DefaultWeightedEdge.class);
	    int numFilas = mapa.getNumeroFilas(),
    		numColumnas = mapa.getNumeroColumnas();
	    int V = numFilas * numColumnas;
	    ArrayList<Construccion> auxmapa = mapa.getMapa();
	    
	    Construccion[][] tablero = new Construccion[numFilas][numColumnas];
	    for(int i = 0; i < V; i++){
	    	int f = i / numFilas;
	    	int c = i % numColumnas;
	    	tablero[f][c] = auxmapa.get(i);
	    	_grafo.addVertex(i );
	    }
	   
	    for(int f = 0; f < numFilas; f++){
	    	for(int c = 0; c < numColumnas; c++){
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
	    				
	    				else if(c == numColumnas -1){    
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
	    				else if(c == numColumnas -1){
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
	    				
	    				else if(c == numColumnas -1){
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

        //Primero hacemos los recorridos de los policias hasta la casa del robo
	    int casa_robo = 38;
        DijkstraShortestPath<Integer, DefaultWeightedEdge> path2 = new DijkstraShortestPath<Integer, DefaultWeightedEdge>(_grafo, 40, casa_robo);
        DijkstraShortestPath<Integer, DefaultWeightedEdge> path3 = new DijkstraShortestPath<Integer, DefaultWeightedEdge>(_grafo, 31, casa_robo);
       
        //Nos quedamos con el poli que esta mas cerca y mostramos su camino
        int origen = (path2.getPathLength() <= path3.getPathLength() ? 40 : 31);        
        System.err.println("\n" + (path2.getPathLength() <= path3.getPathLength() ? path2.getPathEdgeList() : path3.getPathEdgeList()));
        
        //Mostramos el camino que sigue el poli desde la casa del robo hasta la comisaria
        DijkstraShortestPath<Integer, DefaultWeightedEdge> path4 = new DijkstraShortestPath<Integer, DefaultWeightedEdge>(_grafo, casa_robo, 0);
        System.err.println("\nDIST: ("+ path3.getPathLength() +")" + path4.getPathEdgeList() + "\n");

		
		////////////////////////////////////////////////////////////////////////////
		return mapa;
	}
}
