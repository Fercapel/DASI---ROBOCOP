/*
 * DarAcceso.java
 *
 * Creado 23 de abril de 2007, 12:49
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.agenteLadron.objetivos;

import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

/**
 *
 * @author Francisco J Garijo
 */
public class Escapar extends Objetivo {

    public String id;
    public Coordinate destino;

    public Escapar() {
        super.setgoalId("Escapar");
    }

    public Escapar(String id, Coordinate cc) {
        super.setgoalId("Escapar");
        this.destino = cc;
        super.setobjectReferenceId(id);
    }
    
    
    public void setDestinoId(String destinoId) {
        super.setobjectReferenceId(id);
    }
    

    public String getDestinoId() {
        return id;
    }
    
    public Coordinate getDestino()
    {
        return destino;
    }
    public void  setDestino(Coordinate dest)
    {
        destino = dest;
    }
    

}
