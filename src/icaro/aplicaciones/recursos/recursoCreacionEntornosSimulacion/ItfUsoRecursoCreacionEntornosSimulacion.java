package icaro.aplicaciones.recursos.recursoCreacionEntornosSimulacion;

import icaro.aplicaciones.recursos.recursoCreacionEntornosSimulacion.imp.EscenarioSimulacionPoliciasLadrones;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

//Other imports used by this Resource
//#start_nodeuseItfSpecialImports:useItfSpecialImports <--useItfSpecialImports-- DO NOT REMOVE THIS
 
//#end_nodeuseItfSpecialImports:useItfSpecialImports <--useItfSpecialImports-- DO NOT REMOVE THIS


public interface ItfUsoRecursoCreacionEntornosSimulacion extends ItfUsoRecursoSimple {
    public void abrirEditor()throws Exception;
    public void cerrarEditor()throws Exception;
    public void MostrarEscenarioActualSimulado(String identEscenario) throws Exception;
    public EscenarioSimulacionPoliciasLadrones getEscenarioPoliciasLadrones (String identEscenario)throws Exception;
       
}
