package icaro.aplicaciones.Robocop.informacion;

import java.io.Serializable;

public class Coordenadas implements Serializable{

  public double x, y;
  
  public Coordenadas(double x, double y) {
    this.x = x;
    this.y = y;
  }
  public synchronized void setX (double XCoord){
      this.x=XCoord;
  }
  public synchronized double getX (){
      return this.x;
  }
  public synchronized void setY (double YCoord){
      this.y=YCoord;
  }
  public synchronized double getY (){
      return this.y;
  }
  @Override
  public String toString() {
    return "Coordinate: (" + x + "," + y +")";
  }
  
} 
