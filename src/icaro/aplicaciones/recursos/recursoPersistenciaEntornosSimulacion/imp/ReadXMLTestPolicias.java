package icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.imp;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import icaro.aplicaciones.Ladron.informacion.VocabularioLadron;
import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.recursosOrganizacion.configuracion.ItfUsoConfiguracion;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.ItfUsoRepositorioInterfaces;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.imp.ClaseGeneradoraRepositorioInterfaces;

public  class ReadXMLTestPolicias {

	private String sequenceTestPath;
	
	public  ReadXMLTestPolicias(String testFilePath){
		this.sequenceTestPath = testFilePath;
	}

	public String gettestFilePaht(){
		return this.sequenceTestPath;
	}
	
	public synchronized Document getDocument(String testFilePath){
		Document doc=null;		
        try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  doc = dBuilder.parse(new File(testFilePath));
			  doc.getDocumentElement().normalize();
			  return doc;
        } catch (Exception e) {
             e.printStackTrace();
        }
        return doc; //this return will be not executed
	}
	
	//tag parameter should be equal to "policia"
	public synchronized NodeList getPoliciasXMLStructure(Document doc, String tag){
	  NodeList nodeLst = doc.getElementsByTagName(tag);
	  return nodeLst;	
	}

	//nodeLst is the value returned by getPoliciasXMLStructure method
    public int getNumberOfPolicias(NodeList nodeLst){
    	return nodeLst.getLength();
    }

	//nodeLst is the value returned by getPoliciasXMLStructure method
    //sequencePosition start with 0
    public synchronized Element getPoliciaElement(NodeList nodeLst, int sequencePosition){
    	  Element info=null;
		  Node fstNode = nodeLst.item(sequencePosition);
		  if (fstNode.getNodeType() == Node.ELEMENT_NODE){
			  info = (Element) fstNode;
		  }		      	
		  return info;
    }

	//Obtain information about name for the current robot (contained in Element info)
    //tag parameter should be equal to "id"
    public synchronized String getPoliciaIDValue(Element info, String tag){
		  NodeList idNmElmntLst = info.getElementsByTagName(tag);
		  Element idNmElmnt = (Element) idNmElmntLst.item(0);
		  NodeList idNm = idNmElmnt.getChildNodes();					  
		  String valueid = ((Node)idNm.item(0)).getNodeValue();					  
    	  return valueid;
    }

	//Obtain information about initial energy for the current robot (contained in Element info)
    //tag parameter should be equal to "initialenergy"    
    public synchronized int getPoliciaInitialEnergy(Element info, String tag){
		  NodeList initialenergyNmElmntLst = info.getElementsByTagName(tag);
		  Element initialenergyNmElmnt = (Element) initialenergyNmElmntLst.item(0);
		  NodeList initialenergyNm = initialenergyNmElmnt.getChildNodes();					  
		  String initialenergy = ((Node)initialenergyNm.item(0)).getNodeValue();					  
		  return Integer.parseInt(initialenergy);
    }


	//Obtain information about x coordinate for the current robot  (contained in Element info)
    //tagcoordinate parameter should be equal to "initialcoordinate"
    //tagdimension parameter should be equal to "x"
    private synchronized String getPoliciaCoordinateX(Element info, String tagcoordinate, String tagdimension){
          String valuex = "";          
		  NodeList coordinateNmElmntLst = info.getElementsByTagName(tagcoordinate);
		  Node coordinateNode = coordinateNmElmntLst.item(0);
		  if (coordinateNode.getNodeType() == Node.ELEMENT_NODE){
			  Element coorInfo = (Element) coordinateNode;			  
			  //Obtain information about x coordinate for the current robot 
			  NodeList xNmElmntLst = coorInfo.getElementsByTagName(tagdimension);
			  Element xNmElmnt = (Element) xNmElmntLst.item(0);
			  NodeList xNm = xNmElmnt.getChildNodes();					  
			  valuex = ((Node)xNm.item(0)).getNodeValue();				  
		  }
		  return valuex;
    }

	//Obtain information about y coordinate for the current robot  (contained in Element info)
    //tagcoordinate parameter should be equal to "initialcoordinate"
    //tagdimension parameter should be equal to "y"    
    private synchronized String getPoliciaCoordinateY(Element info, String tagcoordinate, String tagdimension){
          String valuey = "";        
		  NodeList coordinateNmElmntLst = info.getElementsByTagName(tagcoordinate);
		  Node coordinateNode = coordinateNmElmntLst.item(0);
		  if (coordinateNode.getNodeType() == Node.ELEMENT_NODE){
			  Element coorInfo = (Element) coordinateNode;			  			  
			  //Obtain information about y coordinate for the current robot 
			  NodeList yNmElmntLst = coorInfo.getElementsByTagName(tagdimension);
			  Element yNmElmnt = (Element) yNmElmntLst.item(0);
			  NodeList yNm = yNmElmnt.getChildNodes();					  
			  valuey = ((Node)yNm.item(0)).getNodeValue();					  
		  }
		  return valuey;
    }    

	//Obtain information about z coordinate for the current robot  (contained in Element info)
    //tagcoordinate parameter should be equal to "initialcoordinate"
    //tagdimension parameter should be equal to "z"    
    private String getPoliciaCoordinateZ(Element info, String tagcoordinate, String tagdimension){
          String valuez = "";        
		  NodeList coordinateNmElmntLst = info.getElementsByTagName(tagcoordinate);
		  Node coordinateNode = coordinateNmElmntLst.item(0);
		  if (coordinateNode.getNodeType() == Node.ELEMENT_NODE){
			  Element coorInfo = (Element) coordinateNode;			  			  
			  //Obtain information about z coordinate for the current robot 
			  NodeList zNmElmntLst = coorInfo.getElementsByTagName(tagdimension);
			  Element zNmElmnt = (Element) zNmElmntLst.item(0);
			  NodeList zNm = zNmElmnt.getChildNodes();					  
			  valuez = ((Node)zNm.item(0)).getNodeValue();				  
		  }
		  return valuez;
    }    

	//Obtain information about coordinates for the current robot  (contained in Element info)    
    public synchronized Coordinate getPoliciaCoordinate(Element info){
    	
    	Coordinate robotCoordinate = new Coordinate(Double.parseDouble(this.getPoliciaCoordinateX(info,"initialcoordinate","x")),
    												 Double.parseDouble(this.getPoliciaCoordinateY(info,"initialcoordinate","y")),
    												 Double.parseDouble(this.getPoliciaCoordinateZ(info,"initialcoordinate","z"))
    												);    	
    	return robotCoordinate;
    }

	//Obtain information about heal range for the current robot (contained in Element info)
    //tag parameter should be equal to "healrange"
    public synchronized float getPoliciaHealRange(Element info, String tag){
		  NodeList healrangeNmElmntLst = info.getElementsByTagName(tag);
		  Element healrangeNmElmnt = (Element) healrangeNmElmntLst.item(0);
		  NodeList healrangeNm = healrangeNmElmnt.getChildNodes();					  
		  String valuehealrange = ((Node)healrangeNm.item(0)).getNodeValue();		  		  
    	  return Float.parseFloat(valuehealrange);
    }
    
    
    public synchronized Coordinate getPoliciaCoordinate(String robotName){
    	Coordinate c=null;

    	ItfUsoRepositorioInterfaces itfUsoRepositorioInterfaces;
    	ItfUsoConfiguracion itfconfig;
    	String rutaFicheroPoliciaTest="";
    	
		//Recuperar la ruta del fichero de robots del escenario
    	try{    	
    		itfUsoRepositorioInterfaces = ClaseGeneradoraRepositorioInterfaces.instance();
    		itfconfig = (ItfUsoConfiguracion)itfUsoRepositorioInterfaces.obtenerInterfaz(NombresPredefinidos.NOMBRE_ITF_USO_CONFIGURACION);
    		rutaFicheroPoliciaTest = itfconfig.getValorPropiedadGlobal(VocabularioLadron.rutaFicheroRobotsTest); 
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}

//    	ReadXMLTestPolicias rXMLTPolicias = new ReadXMLTestPolicias(Constantes.rutasFicheroPolicias);

    	//Leer la coordenada del robot examinando el fichero de robots obtenido
    	ReadXMLTestPolicias rXMLTPolicias = new ReadXMLTestPolicias(rutaFicheroPoliciaTest);    	
		Document doc = rXMLTPolicias.getDocument(rXMLTPolicias.gettestFilePaht());
		//Obtain all the robots
		NodeList nodeLst = rXMLTPolicias.getPoliciasXMLStructure(doc, "robot");		
        for(int j=0; j<rXMLTPolicias.getNumberOfPolicias(nodeLst);j++){
  		    //Obtain info about robot located at the test        	
        	Element info = rXMLTPolicias.getPoliciaElement(nodeLst, j);         		        				
		    String valueid = rXMLTPolicias.getPoliciaIDValue(info,"id");
		    
		    if (valueid.equals(robotName)){
			    Coordinate valueInitialCoordinate = rXMLTPolicias.getPoliciaCoordinate(info);
			    System.out.println("coordinate (Coordinate(double,double,double))-> " + valueInitialCoordinate);
			    return valueInitialCoordinate;
		    }		    
        }    	
    	return c;
    }

    
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//
//		String nameFile= constantes.rutassrc + "/utils/TestSequence.xml";
//		
//        try {
//			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			  Document doc = dBuilder.parse(new File(nameFile));
//
//			  doc.getDocumentElement().normalize();
//			  
//			  System.out.println("El elemento raiz es: " + doc.getDocumentElement().getNodeName());
//			  
//			  Element documentElement =  doc.getDocumentElement();
//			  String documentElementName = documentElement.getNodeName();
//			  String documentElementValue = documentElement.getNodeValue();
//			  
//			  System.out.println("Nombre del elemento raiz " + documentElementName);  //sequence
//			  System.out.println("Valor del elemento raiz " + documentElementValue);  //null			   			  
//			  
//			  //Obtain all the victims
//			  NodeList nodeLst = doc.getElementsByTagName("victim");
//
//			  System.out.println("\nNumber de victims included in the sequence --> " + nodeLst.getLength());
//			  
//			  System.out.println("\n==========================================");  
//			  System.out.println("==Info about arrival time for victims=====");  
//			  System.out.println("==========================================\n");  
//			  
//			  for (int j=0; j < nodeLst.getLength(); j++){
//				  
//				  Node fstNode = nodeLst.item(j);
//				  
//				  if (fstNode.getNodeType() == Node.ELEMENT_NODE){
////					  System.out.println("hello");
//					  
//					  Element info = (Element) fstNode;
//
//					  //Obtain information about name for the current victim 
//					  NodeList idNmElmntLst = info.getElementsByTagName("id");
//					  Element idNmElmnt = (Element) idNmElmntLst.item(0);
//					  NodeList idNm = idNmElmnt.getChildNodes();					  
//					  String valueid = ((Node)idNm.item(0)).getNodeValue();					  
//					  System.out.println("name -> " + valueid);
//					  
//					  //Obtain information about arrival time for the current victim 
//					  NodeList timeNmElmntLst = info.getElementsByTagName("time");
//					  Element timeNmElmnt = (Element) timeNmElmntLst.item(0);
//					  NodeList timeNm = timeNmElmnt.getChildNodes();					  
//					  String valuetime = ((Node)timeNm.item(0)).getNodeValue();					  
//					  System.out.println("time -> " + valuetime);
//					  
//					  //Obtain information about severity for the current victim 
//					  NodeList severityNmElmntLst = info.getElementsByTagName("severity");
//					  Element severityNmElmnt = (Element) severityNmElmntLst.item(0);
//					  NodeList severityNm = severityNmElmnt.getChildNodes();					  
//					  String valueseverity = ((Node)severityNm.item(0)).getNodeValue();				  
//					  System.out.println("severity -> " + valueseverity);
//
//					  //__________________________________________________________________
//					  //
//					  //    Obtain information about coordinates for the current victim 
//					  //__________________________________________________________________
//
//					  NodeList coordinateNmElmntLst = info.getElementsByTagName("coordinate");
//
//					  Node coordinateNode = coordinateNmElmntLst.item(0);
//					  if (coordinateNode.getNodeType() == Node.ELEMENT_NODE){
//						  Element coorInfo = (Element) coordinateNode;
//						  
//						  //Obtain information about x coordinate for the current victim 
//						  NodeList xNmElmntLst = coorInfo.getElementsByTagName("x");
//						  Element xNmElmnt = (Element) xNmElmntLst.item(0);
//						  NodeList xNm = xNmElmnt.getChildNodes();					  
//						  String valuex = ((Node)xNm.item(0)).getNodeValue();					  
//						  System.out.println("x -> " + valuex);
//						  
//						  //Obtain information about y coordinate for the current victim 
//						  NodeList yNmElmntLst = coorInfo.getElementsByTagName("y");
//						  Element yNmElmnt = (Element) yNmElmntLst.item(0);
//						  NodeList yNm = yNmElmnt.getChildNodes();					  
//						  String valuey = ((Node)yNm.item(0)).getNodeValue();					  
//						  System.out.println("y -> " + valuey);
//						  
//						  //Obtain information about y coordinate for the current victim 
//						  NodeList zNmElmntLst = coorInfo.getElementsByTagName("z");
//						  Element zNmElmnt = (Element) zNmElmntLst.item(0);
//						  NodeList zNm = zNmElmnt.getChildNodes();					  
//						  String valuez = ((Node)zNm.item(0)).getNodeValue();					  
//						  System.out.println("z -> " + valuez);
//					  }
//
//					  //__________________________________________________________________
//					  //
//					  //    Obtain information about requirements for the current victim 
//					  //__________________________________________________________________
//
//					  NodeList requirementsNmElmntLst = info.getElementsByTagName("requirements");
//					  Node requirementsNode = requirementsNmElmntLst.item(0);
//					  					  
//					  if (requirementsNode.getNodeType() == Node.ELEMENT_NODE){
//						  Element requirementsInfo = (Element) requirementsNode;
//
//						  //Obtain information about requirement for the current victim 
//						  NodeList requirementNmElmntLst = requirementsInfo.getElementsByTagName("requirement");
//
//						  //get the different requirements
//						  for (int reqindex=0; reqindex < requirementNmElmntLst.getLength() ; reqindex++){
//							  Element requNmElmnt = (Element) requirementNmElmntLst.item(reqindex);
//							  NodeList reqNm = requNmElmnt.getChildNodes();					  							  
//							  String valuereq = ((Node)reqNm.item(0)).getNodeValue();					  
//							  System.out.println("requirement " + reqindex + " -> " + valuereq);							  
//						  }						  						  
//					  }					  
//					  					  			  					  					 
//					  System.out.println("-----------------------------------\n\n");  
//				  }					  
//			  }
//			  
//	    } catch (Exception e) {
//	          e.printStackTrace();
//	    }
//	    
//	}

}
