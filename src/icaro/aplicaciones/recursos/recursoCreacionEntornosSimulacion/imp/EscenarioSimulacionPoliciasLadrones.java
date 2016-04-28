/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.recursos.recursoCreacionEntornosSimulacion.imp;

import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JLabel;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

/**
 *
 * @author FGarijo
 */
@Root(name = "escenarioSimulacion",strict=false)
public class EscenarioSimulacionPoliciasLadrones {
	@Element
	private String identEscenario;
	@Element
	private String modeloOrganizativo;
	@Element
	private int numPolicias;
	@Element
	private int numLadrones;
	@ElementMap(entry="policias", key="key", attribute=true, inline=true)
	private Map<String, Point> policiasLoc;     
	@ElementMap(entry="ladrones", key="key", attribute=true, inline=true)
	private Map<String, Point> ladronesLoc;
	private String policiaInicialId = "initRobot";
	private String ladronInicialId = "initVictim";
	private volatile GestionEscenariosSimulacion migestor;


	public  EscenarioSimulacionPoliciasLadrones (){
		migestor=null;
		modeloOrganizativo = "SinDefinir";
		numPolicias=0;
		numLadrones= 0;
		policiasLoc = new HashMap<String, Point>();
		policiasLoc.put(policiaInicialId, new Point(0,0));
		ladronesLoc = new HashMap<String, Point>();
		ladronesLoc.put(ladronInicialId, new Point(0,0));
	}
	public synchronized void setGestorEscenarios( GestionEscenariosSimulacion gestorEsc){
		migestor=gestorEsc;     
	}
	public void setNumLadrones( int numLadr){
		numLadrones=numLadr;
	}
	@XmlElement (name = "numLadrones")
	public synchronized int  getNumLadrones( ){
		return numLadrones;
	}
	public void setNumPolicias( int numPolicia){
		numPolicias=numPolicia;
	}
	@XmlElement (name = "numPolicias")
	public synchronized int  getNumPolicias( ){
		return numPolicias;
	}
	public void setmodeloOrganizativo( String modeloOrg){
		modeloOrganizativo=modeloOrg;
	}
	@XmlElement (name = "modeloOrganizativo")
	public String  getmodeloOrganizativo( ){
		return modeloOrganizativo;
	}
	public void addLadronLoc (String idLadron,Point puntoEnEscenario ){
		if(numLadrones==0)ladronesLoc.remove(ladronInicialId);
		this.ladronesLoc.put(idLadron, puntoEnEscenario);
		numLadrones++;
	}
	public void eliminaLadron (String idLadron){
		if(ladronesLoc.containsKey(idLadron)){
			ladronesLoc.remove(idLadron);
			numLadrones--;
		}
	}
	public void addPoliciaLoc (String idPolicia,Point puntoEnEscenario ){
		if(numPolicias==0)policiasLoc.remove(policiaInicialId);
		this.policiasLoc.put(idPolicia, puntoEnEscenario);
		numPolicias++;
	}
	public void eliminaRobot (String idPolicia){
		if(policiasLoc.containsKey(idPolicia)){
			policiasLoc.remove(idPolicia);
			numPolicias--;
		}   
	}
	public void eliminarEntidad (String idEntidad){
		if(idEntidad.contains("Policia")){
			if( policiasLoc.containsKey(idEntidad)){
				policiasLoc.remove(idEntidad);
				numPolicias--;
			}else if(idEntidad.startsWith("init"))policiasLoc.remove(idEntidad);
			if(numPolicias==0)policiasLoc.put(policiaInicialId, new Point(0,0));
		}
		else if(idEntidad.contains("Ladron")){
			if( ladronesLoc.containsKey(idEntidad)){
				ladronesLoc.remove(idEntidad);
				numLadrones--;
			}else if(idEntidad.startsWith("init"))ladronesLoc.remove(idEntidad);
			if(numLadrones==0)ladronesLoc.put(ladronInicialId, new Point(0,0));
		}
	}
	public Set getLadrones (){
		if(numLadrones>0)ladronesLoc.remove(ladronInicialId);
		return this.ladronesLoc.entrySet();
	}
	public Set getPolicias (){
		if(numPolicias>0)policiasLoc.remove(policiaInicialId);
		return this.policiasLoc.entrySet();
	}

	public Point getVictimLoc (String idVictima){
		return this.ladronesLoc.get(idVictima);
	}
	public Point getRobotLoc (String idRobot){
		return this.policiasLoc.get(idRobot);
	}
	public void setIdentEscenario(String escenarioId) {
		identEscenario = escenarioId;
	}
	public synchronized String getIdentEscenario() {
		//        throw new UnsupportedOperationException("Not supported yet."); 
		if (identEscenario==null&& migestor!=null)return migestor.getIdentEscenario(modeloOrganizativo, numPolicias, numLadrones);
		return this.identEscenario;
	}
	public synchronized void  setIdentificadorNormalizado(){
		// se lo pide al gestor para que verifique posibles conflictos
		this.identEscenario= migestor.getIdentEscenario(modeloOrganizativo, numPolicias, numLadrones);

	} 
	public ArrayList getListIdentsPolicias(){
		if (numPolicias<=0)return null;
		else{
			policiasLoc.remove(policiaInicialId);
			ArrayList listaIdents = new ArrayList(); 
			Object[] identPolicias= policiasLoc.keySet().toArray();
			for(int i=0; i<identPolicias.length; i++){
				listaIdents.add(identPolicias[i]);
			}
			return listaIdents;
		}
	}
}