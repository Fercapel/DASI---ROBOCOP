/*
 *
 * Clase para gestionar las caracteristicas del ladron en un escenario de simulación :
 * 
 *    -  idLadron -> Identificador del ladron
 *    -  ladronCoordinate -> Coordenada inicial. Tambiï¿½n se podrï¿½a utilizar para poner la coordenada actual del ladron en un instante dado.
 *    -  healRange -> Rango para poder aplicar con ï¿½xito la curaciï¿½n de una victima
 *    -  rangeProximity -> Rango de proximidad para poder ver a otros ladrons que estan a una distancia dentro del rango especificado.
 *    -  ladronCapabilities -> Capacidades. Identifica las habilidades que tiene el ladron para curar a una victima. Las capacidades estan relacionadas con los requisitos de la victima. 
 *                            Deben emparejar totalmente con los requisitos de una victima para que el ladron pueda curarla totalmente. En caso de no emparejar totalmente
 *                            entonces solo permitirï¿½ eliminar los requisitos que emparejan, y la victima todavï¿½a requiere de otros ladrons para poder compensar los requisitos
 *                            que no emparejaron con las capacidades del ladron. 
 *
 */
package icaro.aplicaciones.agentes.agenteLadron.informacion;

import icaro.aplicaciones.Ladron.informacion.LadronCapability;
import icaro.aplicaciones.Rosace.informacion.*;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.InfoCompMovimiento;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class LadronStatus implements Cloneable {

    @Element
    private String idLadron;
    @Element
    private String idLadronRol;
    //@Element
    //private int combustible;
    @Element
    private Point ladronCoordinateActualP;
    private Point ladronCoordinateAnteriorP;
    private Coordinate ladronCoordinate;
    /*
     * Actualmente en nuestra implementacion no se utilizan los atributos rangeProximity y ladronCapabilities.
     * No obstante esta clase ya ofrece metodos para poder considerarlos en el futuro
     */ 
    private float rangeProximity;
    private final double limiteDespalzamiento = 0.5;
    private List<LadronCapability> ladronCapabilities;
    private LadronCapability capability;
    private InfoCompMovimiento infoCompMovt;

    public LadronStatus() {
        ladronCoordinateAnteriorP = new Point(0, 0);
        ladronCoordinateActualP = new Point(1, 1);
        idLadronRol = "indefinido";
        ladronCapabilities = new ArrayList<LadronCapability>();
    }

    public LadronStatus(ArrayList<LadronCapability> initialCapb) {
        ladronCoordinateAnteriorP = new Point(0, 0);
        ladronCoordinateActualP = new Point(1, 1);
        idLadronRol = "indefinido";
        ladronCapabilities = initialCapb;
    }

    public void setIdLadron(String id) {
        this.idLadron = id;
    }

    @XmlElement(name = "idLadron")
    public String getIdLadron() {
        return this.idLadron;
    }

    public void setIdLadronRol(String id) {
        this.idLadronRol = id;
    }

    @XmlElement(name = "idLadronRol")
    public String getIdLadronRol() {
        return this.idLadronRol;
    }

  
    public void setLadronCoordinate(Coordinate coord) {
        this.ladronCoordinate = coord;
    }

    public Coordinate getLadronCoordinate() {
        if (ladronCoordinate == null) {
            ladronCoordinate = new Coordinate(ladronCoordinateActualP.x, ladronCoordinateActualP.y, 0);
        }
        return ladronCoordinate;
    }

    @XmlElement(name = "ladronCoordinateP")
    public Point getLocPoint() {
        return this.ladronCoordinateActualP;
    }

    public void setLocPoint(Point punto) {
        this.ladronCoordinateActualP = punto;
        if (this.ladronCoordinate == null) {
            ladronCoordinate = new Coordinate(0, 0, 0);
        }
        this.ladronCoordinateActualP = punto;
        this.ladronCoordinate.setX(punto.x);
        this.ladronCoordinate.setY(punto.y);
    }

    public void setInfoCompMovt(InfoCompMovimiento compInfo) {
        this.infoCompMovt = compInfo;
    }

    public InfoCompMovimiento getInfoCompMovt() {
        return this.infoCompMovt;
    }

    public void deleteLadronCapability(String capabilityId) {
        ladronCapabilities.remove(capabilityId);
    }

    public void setRangeProximity(float rp) {
        this.rangeProximity = rp;
    }

    public double getRangeProximity() {
        return this.rangeProximity;
    }

    public void setLadronCapability(LadronCapability capabilityR) {
        if (!ladronCapabilities.contains(capabilityR)) {
            ladronCapabilities.add(capabilityR);
        }
    }

    @XmlElement(name = "ladronCapability")
    public LadronCapability getLadronCapability(String identCapab) {
        int i = 0;
        boolean encontrado = false;
        while (i < ladronCapabilities.size() & !encontrado) {
            if (ladronCapabilities.get(i).getNombre().equalsIgnoreCase(identCapab)) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (encontrado) {
            return ladronCapabilities.get(i);
        } else {
            return null;
        }
    }

    @XmlElement(name = "ladronCapabilities")
    public List<LadronCapability> getLadronCapabilities() {
        return this.ladronCapabilities;
    }

    public boolean sinMovimientoSignificativo() {
        if (ladronCoordinateAnteriorP == null) {
            return false;
        }
        return (limiteDespalzamiento >= Math.abs(ladronCoordinateActualP.getY() - ladronCoordinateAnteriorP.getY())
                && limiteDespalzamiento >= Math.abs(ladronCoordinateActualP.getX() - ladronCoordinateAnteriorP.getX()));
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
        return "Ladron: id->" + this.getIdLadron()
                + " ; Ladron: Rol->" + this.getIdLadronRol()
                + " ; coordinate->" + this.getLadronCoordinate();
    }
}
