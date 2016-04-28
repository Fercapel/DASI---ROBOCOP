/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Ladron.informacion;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author Fercapel
 */
  @Root
public class LadronCapability {

   @Attribute
   private String nombre = "sinDefinir" ;

   @Element
   private String descripcion = "Capacidad inicial sin definir";
  
    public void  setNombre(String nom) {
       nombre= nom;
   }
   public String getNombre() {
      return nombre;
   }
public void  setDescripcion(String desc) {
       descripcion= desc;
   }
   public String getDescripcion() {
      return descripcion;
   }
}
    
