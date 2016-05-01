package icaro.aplicaciones.agentes.agenteLadron.tareas;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class DecirHola extends TareaSincrona{
    @Override
	public void ejecutar(Object... params) {
		try {
			String saludo = (String) params[0];
			 
			String nombreAgenteEmisor = this.getIdentAgente();
			
			trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, "Se ejecuta la tarea " + this.identTarea + " saludo: "+saludo+ nombreAgenteEmisor+"\n" );
			
			System.out.println("-----------------------------------------------------------Ejecutando saludo: "+ saludo +nombreAgenteEmisor);                    
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	}

}