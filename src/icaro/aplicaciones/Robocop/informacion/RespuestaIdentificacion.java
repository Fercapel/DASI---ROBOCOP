

package icaro.aplicaciones.Robocop.informacion;

import java.io.Serializable;


public class RespuestaIdentificacion implements Serializable{
	
    public String identAgente;
    public String tipoAgente;
    public String destAgente;
    
    public RespuestaIdentificacion() {
        identAgente = "";
        tipoAgente = "";
        destAgente = "";
    }

    public RespuestaIdentificacion(String identAgenteEmisor, String tipoAgenteEmisor, String identAgenteReceptor) {
    	identAgente= identAgenteEmisor;
    	tipoAgente= tipoAgenteEmisor;
    	destAgente= identAgenteReceptor;
        
    }

    public void   setIdentAgente(String identAgente){
        this.identAgente = identAgente ;
    }
    
    public String   getIdentAgente(){
        return this.identAgente;
    }
    
    public void setTipoAgente(String tipoAgente){
    	this.tipoAgente =tipoAgente;
    }

    public String getTipoAgente(){
    	return this.tipoAgente;
    }
    
    public void setDestAgente(String destAgente){
    	this.destAgente =destAgente;
    }

    public String getDestAgente(){
        return this.destAgente;
    }

    @Override
    public String toString(){
        if ( identAgente == "" )
            return "DecisionAgente:->  Agente Emisor : Null Agent; Tipo Agente :+" + tipoAgente +"; Agente Receptor :+" + destAgente + "\n ";
        else 
        	return "DecisionAgente:->  Agente Emisor : " + identAgente + "; Tipo Agente :+" + tipoAgente +"; Agente Receptor :+" + destAgente + "\n ";
    }
    
}
