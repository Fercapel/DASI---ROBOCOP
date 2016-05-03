

package icaro.aplicaciones.Robocop.informacion;

import java.io.Serializable;



public class PeticionIdentificacion implements Serializable{
	
    public String identAgente;
    
    public PeticionIdentificacion() {
    	identAgente = "";
    }

    public PeticionIdentificacion(String identAgenteEmisor) {
        identAgente= identAgenteEmisor;
    }

    public void   setIdentAgente(String identAgente){
        this.identAgente = identAgente ;
    }
    
    public String   getIdentAgente(){
        return identAgente;
    }

    @Override
    public String toString(){
        if ( identAgente == "" )
            return "PeticionIdentificacion:->  Agente Emisor : Null Agent";
        else 
            return "PeticionIdentificacion:->  Agente Emisor :" + identAgente;
    }
    
}
