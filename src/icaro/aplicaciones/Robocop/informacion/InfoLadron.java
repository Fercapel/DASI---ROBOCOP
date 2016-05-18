/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.Robocop.informacion;
import java.io.Serializable;

import icaro.aplicaciones.Robocop.Coordenada;
import icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop.imp.EstadoAbstractoMovimientoRobocop;


public class InfoLadron implements Serializable{

	  private String  nombreAgente;
      private Coordenada coordActualLadron;
      
      /*public static enum EstadoMovimientoRobot {
  		PARADO, MOVIENDOSE, DESTINO
  	  }*/

  	  public String estadoActual;
      
      
      public InfoLadron(){
    	  this.nombreAgente = "";
    	  this.coordActualLadron = new Coordenada();
    	  this.estadoActual = "PARADO";//EstadoMovimientoRobot.PARADO;
      }
      
      public InfoLadron(String  nombreAgente, Coordenada coordActualLadron){
    	  this.nombreAgente = nombreAgente;
    	  this.coordActualLadron = coordActualLadron;
    	  this.estadoActual = "PARADO";//EstadoMovimientoRobot.PARADO;
      }
      
      public String getNombreAgente(){
    	  return this.nombreAgente;
      }
      
      public void setNombreAgente(String nombreAgente){
    	  this.nombreAgente = nombreAgente;
      }
      
      public Coordenada getCoordenada(){
    	  return this.coordActualLadron;
      }
      
      public void setCoordenada(Coordenada coordenada){
    	  this.coordActualLadron.setX(coordenada.getX());
    	  this.coordActualLadron.setY(coordenada.getY());
    	  this.coordActualLadron.setIdAgente(this.nombreAgente);
    	  this.estadoActual = "MOVIENDOSE";//EstadoMovimientoRobot.MOVIENDOSE;
      }
 
      public String getEstadoActual(){
    	  return this.estadoActual;
      }
      
      public void setEstadoActual(String estado){
    	  this.estadoActual = estado;
      }
      
}