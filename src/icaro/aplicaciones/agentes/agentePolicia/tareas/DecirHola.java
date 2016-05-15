package icaro.aplicaciones.agentes.agentePolicia.tareas;

import icaro.aplicaciones.Robocop.InfoEquipo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class DecirHola extends TareaSincrona{
    @Override
	public void ejecutar(Object... params) {
		try {
			String saludo = (String) params[0];
			 
			String nombreAgenteEmisor = this.getIdentAgente();
			
			InfoEquipo miEquipo = (InfoEquipo)params[1];
			
			trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, "Se ejecuta la tarea " + this.identTarea + " saludo: "+saludo+ "\n" );
			
			System.out.println("-----------------------------------------------------------Ejecutando saludo: "+ saludo + " Emisor: " + miEquipo.getIdentAgenteJefeEquipo());                    
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	}

}