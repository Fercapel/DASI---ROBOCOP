package icaro.aplicaciones.Robocop;

import org.jgrapht.graph.DefaultWeightedEdge;

public class Arista extends DefaultWeightedEdge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getSourcePosition(){
		return (int) this.getSource();
	}
	
	public int getTargetPosition(){
		return (int) this.getSource();
	}
}
