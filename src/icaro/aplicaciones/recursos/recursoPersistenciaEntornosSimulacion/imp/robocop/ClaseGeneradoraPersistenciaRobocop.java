package icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.imp.robocop;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;

import icaro.aplicaciones.Robocop.InfoMapa;
import icaro.aplicaciones.Robocop.InfoVariables;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornoSimulacion;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop.Construccion;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.robocop.Coordenada;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;

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
					mapa.setPosicionInicial(vars[0], new Coordenada(Integer.parseInt(vars[1]), Integer.parseInt(vars[2])));
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

		mapa.rellenarGrafo();
		return mapa;
	}
}
