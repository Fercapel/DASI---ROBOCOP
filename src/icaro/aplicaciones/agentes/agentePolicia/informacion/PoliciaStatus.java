/*
 *
 * Clase para gestionar las caracteristicas del policia en un escenario de simulación :
 * 
 *    -  idPolicia -> Identificador del policia
 *    -  policiaCoordinate -> Coordenada inicial. Tambiï¿½n se podrï¿½a utilizar para poner la coordenada actual del policia en un instante dado.
 *    -  healRange -> Rango para poder aplicar con ï¿½xito la curaciï¿½n de una victima
 *    -  rangeProximity -> Rango de proximidad para poder ver a otros policias que estan a una distancia dentro del rango especificado.
 *    -  policiaCapabilities -> Capacidades. Identifica las habilidades que tiene el policia para curar a una victima. Las capacidades estan relacionadas con los requisitos de la victima. 
 *                            Deben emparejar totalmente con los requisitos de una victima para que el policia pueda curarla totalmente. En caso de no emparejar totalmente
 *                            entonces solo permitirï¿½ eliminar los requisitos que emparejan, y la victima todavï¿½a requiere de otros policias para poder compensar los requisitos
 *                            que no emparejaron con las capacidades del policia. 
 *
 */
package icaro.aplicaciones.agentes.agentePolicia.informacion;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import icaro.aplicaciones.Policia.informacion.PoliciaCapability;
import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.InfoCompMovimiento;

@Root
public class PoliciaStatus implements Cloneable {

    @Element
    private String idPolicia;
    @Element
    private String idPoliciaRol;
    //@Element
    //private int combustible;
    @Element
    private Point policiaCoordinateActualP;
    private Point policiaCoordinateAnteriorP;
    private Coordinate policiaCoordinate;
    /*
     * Actualmente en nuestra implementacion no se utilizan los atributos rangeProximity y policiaCapabilities.
     * No obstante esta clase ya ofrece metodos para poder considerarlos en el futuro
     */ 
    private float rangeProximity;
    private final double limiteDespalzamiento = 0.5;
    private List<PoliciaCapability> policiaCapabilities;
    private PoliciaCapability capability;
    private InfoCompMovimiento infoCompMovt;

    public PoliciaStatus() {
        policiaCoordinateAnteriorP = new Point(0, 0);
        policiaCoordinateActualP = new Point(1, 1);
        idPoliciaRol = "indefinido";
        policiaCapabilities = new ArrayList<PoliciaCapability>();
    }

    public PoliciaStatus(ArrayList<PoliciaCapability> initialCapb) {
        policiaCoordinateAnteriorP = new Point(0, 0);
        policiaCoordinateActualP = new Point(1, 1);
        idPoliciaRol = "indefinido";
        policiaCapabilities = initialCapb;
    }

    public void setIdPolicia(String id) {
        this.idPolicia = id;
    }

    @XmlElement(name = "idPolicia")
    public String getIdPolicia() {
        return this.idPolicia;
    }

    public void setIdPoliciaRol(String id) {
        this.idPoliciaRol = id;
    }

    @XmlElement(name = "idPoliciaRol")
    public String getIdPoliciaRol() {
        return this.idPoliciaRol;
    }

  
    public void setPoliciaCoordinate(Coordinate coord) {
        this.policiaCoordinate = coord;
    }

    public Coordinate getPoliciaCoordinate() {
        if (policiaCoordinate == null) {
            policiaCoordinate = new Coordinate(policiaCoordinateActualP.x, policiaCoordinateActualP.y, 0);
        }
        return policiaCoordinate;
    }

    @XmlElement(name = "policiaCoordinateP")
    public Point getLocPoint() {
        return this.policiaCoordinateActualP;
    }

    public void setLocPoint(Point punto) {
        this.policiaCoordinateActualP = punto;
        if (this.policiaCoordinate == null) {
            policiaCoordinate = new Coordinate(0, 0, 0);
        }
        this.policiaCoordinateActualP = punto;
        this.policiaCoordinate.setX(punto.x);
        this.policiaCoordinate.setY(punto.y);
    }

    public void setInfoCompMovt(InfoCompMovimiento compInfo) {
        this.infoCompMovt = compInfo;
    }

    public InfoCompMovimiento getInfoCompMovt() {
        return this.infoCompMovt;
    }

    public void deletePoliciaCapability(String capabilityId) {
        policiaCapabilities.remove(capabilityId);
    }

    public void setRangeProximity(float rp) {
        this.rangeProximity = rp;
    }

    public double getRangeProximity() {
        return this.rangeProximity;
    }

    public void setPoliciaCapability(PoliciaCapability capabilityR) {
        if (!policiaCapabilities.contains(capabilityR)) {
            policiaCapabilities.add(capabilityR);
        }
    }

    @XmlElement(name = "policiaCapability")
    public PoliciaCapability getPoliciaCapability(String identCapab) {
        int i = 0;
        boolean encontrado = false;
        while (i < policiaCapabilities.size() & !encontrado) {
            if (policiaCapabilities.get(i).getNombre().equalsIgnoreCase(identCapab)) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (encontrado) {
            return policiaCapabilities.get(i);
        } else {
            return null;
        }
    }

    @XmlElement(name = "policiaCapabilities")
    public List<PoliciaCapability> getPoliciaCapabilities() {
        return this.policiaCapabilities;
    }

    public boolean sinMovimientoSignificativo() {
        if (policiaCoordinateAnteriorP == null) {
            return false;
        }
        return (limiteDespalzamiento >= Math.abs(policiaCoordinateActualP.getY() - policiaCoordinateAnteriorP.getY())
                && limiteDespalzamiento >= Math.abs(policiaCoordinateActualP.getX() - policiaCoordinateAnteriorP.getX()));
    }

    @Override
    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }

    @Override
    public String toString() {
        return "Policia: id->" + this.getIdPolicia()
                + " ; Policia: Rol->" + this.getIdPoliciaRol()
                + " ; coordinate->" + this.getPoliciaCoordinate();
    }
}
