package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

import icaro.aplicaciones.Robocop.informacion.PeticionIdentificacion;

public class Contactar extends TareaSincrona{
    @Override
	public void ejecutar(Object... params) {
		try {
			String mensaje = (String) params[0];
			 
			String nombreAgenteEmisor = this.getIdentAgente();
			
			trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, "Se ejecuta la tarea " + this.identTarea + " mensaje: "+mensaje + nombreAgenteEmisor+"\n" );
			
			PeticionIdentificacion peticion = new PeticionIdentificacion (nombreAgenteEmisor);
			
			
			System.out.println("-----------------------------------------------------------Ejecutando petición de identificación: "+ mensaje +nombreAgenteEmisor);                    
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	}

}