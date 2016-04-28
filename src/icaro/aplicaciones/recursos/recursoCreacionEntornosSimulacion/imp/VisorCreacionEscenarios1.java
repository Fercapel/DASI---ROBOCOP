/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ControlCenterGui2.java
 *
 * Created on 29-dic-2011, 20:42:57
 */
package icaro.aplicaciones.recursos.recursoCreacionEntornosSimulacion.imp;

import java.awt.Component;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.animator.SceneAnimator;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.LayerWidget;
import org.openide.util.Exceptions;
import org.openide.util.Utilities;

import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.ComponentMover;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.NotificadorInfoUsuarioSimulador;

/**
 *
 * @author FGarijo
 */
public class VisorCreacionEscenarios1 extends javax.swing.JFrame {

	/** Creates new form ControlCenterGui2 */
	private NotificadorInfoUsuarioSimulador notifEvts;
	private int intervaloSecuencia = 10000; // valor por defecto. Eso deberia ponerse en otro sitio
	private int numMensajesEnviar = 3;
	private boolean primeraLadron = true;

	private ArrayList identsPoliciasEquipo ;
	private javax.swing.JLabel jLabelAux;
	private String directorioTrabajo;
	private String tituloVentanaVisor = "ROSACE Scenario Visor";
	private String rutassrc = "src/";   //poner "src/main/java" si el proyecto de icaro se monta en un proyecto maven
	private String rutapaqueteConstructorEscenariosROSACE = "utilsDiseniaEscenariosRosace/";
	private static  Image IMAGErobot,IMAGEmujer,IMAGEmujerRes ;
	private String rutaIconos = "\\src\\utilsDiseniaEscenariosRosace\\";
	//    private String rutaPersistenciaEscenario = "\\src\\persistenciaEscenarios\\";
	private String directorioPersistencia = VocabularioRosace.IdentDirectorioPersistenciaSimulacion+File.separator;
	private String imageniconoHombre = "Hombre.png";
	private String imageniconoMujer = "Mujer.png";
	private String imageniconoMujerRescatada = "MujerRescatada.png";
	private String imageniconoHombreRescatado = "HombreRescatado.png";
	private String imageniconoPolicia = "Policia.png";
	private String modeloOrganizativoInicial = "Igualitario";
	private Map<String, JLabel> tablaEntidadesEnEscenario;
	private ArrayList <JLabel> listaEntidadesEnEscenario;
	private JPanel panelVisor;
	private ObjectScene scene;
	private LayerWidget layer;
	JLabel entidadSeleccionada=null;
	private WidgetAction moveAction = ActionFactory.createMoveAction ();
	private Point ultimoPuntoClic ;

	private SceneAnimator animator ;
	private boolean intencionUsuarioCrearPolicia;
	private boolean intencionUsuarioCrearLadron;
	private boolean entidadSeleccionadaParaMover;
	private int numeroPolicias, mumeroLadrons;
	private volatile GestionEscenariosSimulacion gestionEscComp;
	private volatile EscenarioSimulacionPoliciasLadrones escenarioActualComp;
	private  ComponentMover moverComp;
	private ControladorEditorEntornosSimulacion controladorSim;
	private volatile PersistenciaVisualizadorEscenarios persistencia;
	private String modeloOrganizativo;
	private String identEquipoActual;
	private String equipoId;

	public  VisorCreacionEscenarios1(ControladorEditorEntornosSimulacion controlador) throws Exception {
		//        super("visor Escenario ");
		initComponents();
		initEscenario();
		moverComp =new ComponentMover();
		moverComp.addMenuAcciones(jPopupMenuAcionEntidad);
		controladorSim = controlador;

		//        identEquipoActual = escenarioActualComp.getIdentEscenario();
		//        actualizarInfoEquipoEnEscenario();
		//        leerInfoEscenario();
		directorioTrabajo = System.getProperty("user.dir");
		numeroPolicias=0;  mumeroLadrons=0;
		//        tablaEntidadesEnEscenario = new HashMap<String, JLabel>();
		//        listaEntidadesEnEscenario = new ArrayList < JLabel>();

	}

	private void initEscenario(){
		String rutaIconoPolicia =   rutapaqueteConstructorEscenariosROSACE + imageniconoPolicia;
		IMAGErobot = Utilities.loadImage (rutaIconoPolicia);
		IMAGEmujerRes = Utilities.loadImage ( rutapaqueteConstructorEscenariosROSACE +imageniconoMujerRescatada); 
		IMAGEmujer = Utilities.loadImage ( rutapaqueteConstructorEscenariosROSACE +imageniconoMujer);
		//        JLabel label = new javax.swing.JLabel("PoliciaPrueba");
		//            String rutaIconoPolicia = directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + imageniconoPolicia;

		//       label.setIcon(new javax.swing.ImageIcon(rutaIconoPolicia));     //System.out.println("Ruta->" + rutaIconoPolicia);
		//       label.createImage(10,10);
		//            this.getRootPane().add(label);
		//            this.repaint();
	}

	public synchronized void cambiarIconoLadronARescatada(String valor_idLadron) {
		System.out.println("se cambia el icono de la victima a rescatada: "+valor_idLadron );
		//        ((IconNodeWidget)scene.findWidget(valor_idLadron)).setImage(IMAGEmujerRes);

		//        int numeroIdLadron = Integer.parseInt(numeroLadron);
		//
		//        JLabel jlabelLadron = new JLabel();
		//
		//        jlabelLadron = victimaslabel.get(numeroLadron);
		//
		//        if (jlabelLadron != null) {
		//
		//            //String rutaAbsolutaIconoLadron = jlabelLadron.getIcon().toString();			
		//            //System.out.println("victima " + numeroLadron + "  , " + jlabelLadron.getIcon().toString());
		//
		//            if (numeroIdLadron % 2 == 0) {
		//                jlabelLadron.setIcon(new javax.swing.ImageIcon(directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + "HombreRescatado.png"));
		//                //System.out.println("Es un hombre");
		//            } else {
		//                jlabelLadron.setIcon(new javax.swing.ImageIcon(directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + "MujerRescatada.png"));
		//                //System.out.println("Es una mujer");
		//            }
		//
		//        } else {
		//            System.out.println("jlabelLadron nulo");
		//        }
		System.out.println("se cambia el icono de la victima a rescatada");
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jDialogAvisoErrorDefNumEntidades = new javax.swing.JDialog();
		jButton1 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jPopupMenuAcionEntidad = new javax.swing.JPopupMenu();
		jMenuItemEliminar = new javax.swing.JMenuItem();
		jSeparator3 = new javax.swing.JPopupMenu.Separator();
		jMenuItemGuardar = new javax.swing.JMenuItem();
		jPopupMenuAddEntidades = new javax.swing.JPopupMenu();
		jMenuItemAddPolicia = new javax.swing.JMenuItem();
		jSeparator4 = new javax.swing.JPopupMenu.Separator();
		jMenuItemAddLadron = new javax.swing.JMenuItem();
		jFileChooser1 = new javax.swing.JFileChooser();
		jOptionPane1 = new javax.swing.JOptionPane();
		jTextFieldModeloOrganizacion = new javax.swing.JTextField();
		robotIcon = new javax.swing.JLabel();
		intervalNumPolicias = new javax.swing.JTextField();
		victimaIcon1 = new javax.swing.JLabel();
		intervalNumLadrons = new javax.swing.JTextField();
		jTextFieldIdentEquipo = new javax.swing.JTextField();
		jButtonGuardarEscenario = new javax.swing.JButton();
		jSeparator7 = new javax.swing.JSeparator();
		jLabelOrganizacion = new javax.swing.JLabel();
		jLabelIdentEquipo = new javax.swing.JLabel();
		GestionEscenarios = new javax.swing.JMenuBar();
		jMenuEditarEscenario = new javax.swing.JMenu();
		jMenuItemNuevoEscenario = new javax.swing.JMenuItem();
		jSeparator5 = new javax.swing.JPopupMenu.Separator();
		jMenuItemAbrir = new javax.swing.JMenuItem();
		jSeparator6 = new javax.swing.JPopupMenu.Separator();
		jMenuItemEliminarEscenario = new javax.swing.JMenuItem();
		jSeparator8 = new javax.swing.JPopupMenu.Separator();
		jMenuItemGuardarEscenario = new javax.swing.JMenuItem();
		jSeparator1 = new javax.swing.JPopupMenu.Separator();
		jMenuItemSalir = new javax.swing.JMenuItem();
		jMenuOrganizacion = new javax.swing.JMenu();
		jMenuItemModeloJerarquico = new javax.swing.JMenuItem();
		jMenuItemModeloIgualitario = new javax.swing.JMenuItem();
		jMenuItemModeloOtros = new javax.swing.JMenuItem();
		jMenu3 = new javax.swing.JMenu();
		jMenuItemCrearPolicia = new javax.swing.JMenuItem();
		jSeparator2 = new javax.swing.JPopupMenu.Separator();
		jMenuItemCrearLadron = new javax.swing.JMenuItem();

		jDialogAvisoErrorDefNumEntidades.setTitle("Error: Definición de entidades en el escenario");
		jDialogAvisoErrorDefNumEntidades.setBounds(new java.awt.Rectangle(20, 20, 335, 88));
		jDialogAvisoErrorDefNumEntidades.setType(java.awt.Window.Type.POPUP);

		jButton1.setText("Aceptar");

		jLabel1.setText("El numero de entidades no puede ser mayor que 20");

		javax.swing.GroupLayout jDialogAvisoErrorDefNumEntidadesLayout = new javax.swing.GroupLayout(jDialogAvisoErrorDefNumEntidades.getContentPane());
		jDialogAvisoErrorDefNumEntidades.getContentPane().setLayout(jDialogAvisoErrorDefNumEntidadesLayout);
		jDialogAvisoErrorDefNumEntidadesLayout.setHorizontalGroup(
				jDialogAvisoErrorDefNumEntidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jDialogAvisoErrorDefNumEntidadesLayout.createSequentialGroup()
						.addGap(122, 122, 122)
						.addComponent(jButton1)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(jDialogAvisoErrorDefNumEntidadesLayout.createSequentialGroup()
						.addGap(36, 36, 36)
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap())
				);
		jDialogAvisoErrorDefNumEntidadesLayout.setVerticalGroup(
				jDialogAvisoErrorDefNumEntidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAvisoErrorDefNumEntidadesLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jButton1))
				);

		jPopupMenuAcionEntidad.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
			public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
			}
			public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
			}
			public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
				jPopupMenuAcionEntidadPopupMenuWillBecomeVisible(evt);
			}
		});
		jPopupMenuAcionEntidad.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
			public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
			}
			public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
				jPopupMenuAcionEntidadMenuKeyPressed(evt);
			}
			public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
			}
		});

		jMenuItemEliminar.setText("Eliminar");
		jMenuItemEliminar.setToolTipText("");
		jMenuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemEliminarActionPerformed(evt);
			}
		});
		jPopupMenuAcionEntidad.add(jMenuItemEliminar);
		jPopupMenuAcionEntidad.add(jSeparator3);

		jMenuItemGuardar.setText("Guardar");
		jMenuItemGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemGuardarActionPerformed(evt);
			}
		});
		jPopupMenuAcionEntidad.add(jMenuItemGuardar);

		jMenuItemAddPolicia.setText("Añadir Policia");
		jMenuItemAddPolicia.setActionCommand("AddPolicia");
		jMenuItemAddPolicia.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemAddPoliciaActionPerformed(evt);
			}
		});
		jPopupMenuAddEntidades.add(jMenuItemAddPolicia);
		jPopupMenuAddEntidades.add(jSeparator4);

		jMenuItemAddLadron.setText("Añadir Ladron");
		jMenuItemAddLadron.setActionCommand("AddLadron");
		jMenuItemAddLadron.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemAddLadronActionPerformed(evt);
			}
		});
		jPopupMenuAddEntidades.add(jMenuItemAddLadron);

		jFileChooser1.setDialogTitle("Seleccion de escenario");
		jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jFileChooser1ActionPerformed(evt);
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Creación de Escenarios");
		setMinimumSize(new java.awt.Dimension(30, 30));
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				formMouseClicked(evt);
			}
		});

		jTextFieldModeloOrganizacion.setName("Modelo Organizacion"); // NOI18N

		robotIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilsDiseniaEscenariosRosace/Policia.png"))); // NOI18N
		robotIcon.setText("Policias");
		robotIcon.setIconTextGap(2);

		intervalNumPolicias.setText("0");
		intervalNumPolicias.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				intervalNumPoliciasActionPerformed(evt);
			}
		});

		victimaIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		victimaIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilsDiseniaEscenariosRosace/Mujer.png"))); // NOI18N
		victimaIcon1.setText("Ladrons");
		victimaIcon1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
		victimaIcon1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		victimaIcon1.setIconTextGap(2);

		intervalNumLadrons.setText("0");
		intervalNumLadrons.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				intervalNumLadronsActionPerformed(evt);
			}
		});

		jTextFieldIdentEquipo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextFieldIdentEquipoActionPerformed(evt);
			}
		});

		jButtonGuardarEscenario.setText("Guardar");
		jButtonGuardarEscenario.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jButtonGuardarEscenarioMousePressed(evt);
			}
		});
		jButtonGuardarEscenario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonGuardarEscenarioActionPerformed(evt);
			}
		});

		jLabelOrganizacion.setText("Organización");

		jLabelIdentEquipo.setText("Ident Equipo");

		jMenuEditarEscenario.setText("Edición");

		jMenuItemNuevoEscenario.setText("Nuevo Escenario");
		jMenuItemNuevoEscenario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemNuevoEscenarioActionPerformed(evt);
			}
		});
		jMenuEditarEscenario.add(jMenuItemNuevoEscenario);
		jMenuEditarEscenario.add(jSeparator5);

		jMenuItemAbrir.setText("Abrir");
		jMenuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemAbrirActionPerformed(evt);
			}
		});
		jMenuEditarEscenario.add(jMenuItemAbrir);
		jMenuEditarEscenario.add(jSeparator6);

		jMenuItemEliminarEscenario.setText("Eliminar");
		jMenuItemEliminarEscenario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemEliminarEscenarioActionPerformed(evt);
			}
		});
		jMenuEditarEscenario.add(jMenuItemEliminarEscenario);
		jMenuEditarEscenario.add(jSeparator8);

		jMenuItemGuardarEscenario.setText("Guardar");
		jMenuItemGuardarEscenario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemGuardarEscenarioActionPerformed(evt);
			}
		});
		jMenuEditarEscenario.add(jMenuItemGuardarEscenario);
		jMenuEditarEscenario.add(jSeparator1);

		jMenuItemSalir.setText("Salir");
		jMenuEditarEscenario.add(jMenuItemSalir);

		GestionEscenarios.add(jMenuEditarEscenario);

		jMenuOrganizacion.setText("Organizacion del equipo");

		jMenuItemModeloJerarquico.setText("Jerarquico");
		jMenuItemModeloJerarquico.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemModeloJerarquicoActionPerformed(evt);
			}
		});
		jMenuOrganizacion.add(jMenuItemModeloJerarquico);

		jMenuItemModeloIgualitario.setText("Igualitario");
		jMenuItemModeloIgualitario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemModeloIgualitarioActionPerformed(evt);
			}
		});
		jMenuOrganizacion.add(jMenuItemModeloIgualitario);

		jMenuItemModeloOtros.setText("Otros");
		jMenuOrganizacion.add(jMenuItemModeloOtros);

		GestionEscenarios.add(jMenuOrganizacion);

		jMenu3.setText("Añadir entidad");

		jMenuItemCrearPolicia.setText("Policia");
		jMenuItemCrearPolicia.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemCrearPoliciaActionPerformed(evt);
			}
		});
		jMenu3.add(jMenuItemCrearPolicia);
		jMenu3.add(jSeparator2);

		jMenuItemCrearLadron.setText("Ladron");
		jMenuItemCrearLadron.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemCrearLadronActionPerformed(evt);
			}
		});
		jMenu3.add(jMenuItemCrearLadron);

		GestionEscenarios.add(jMenu3);

		setJMenuBar(GestionEscenarios);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabelOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jTextFieldModeloOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(robotIcon)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(intervalNumPolicias, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(victimaIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(intervalNumLadrons, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabelIdentEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jTextFieldIdentEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButtonGuardarEscenario)
										.addGap(0, 0, Short.MAX_VALUE))
								.addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING))
						.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(4, 4, 4)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(intervalNumLadrons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(victimaIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(intervalNumPolicias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(robotIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jTextFieldIdentEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButtonGuardarEscenario)
								.addComponent(jTextFieldModeloOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabelOrganizacion)
								.addComponent(jLabelIdentEquipo))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(538, Short.MAX_VALUE))
				);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
		if (evt.getButton()==3){
			jPopupMenuAddEntidades.show(this,evt.getX(),evt.getY());
			ultimoPuntoClic = new Point(evt.getX(),evt.getY());
		}else {
			String tipoEntidad="Policia";
			if(intencionUsuarioCrearLadron)tipoEntidad="Ladron";
			Point puntoCursor = MouseInfo.getPointerInfo().getLocation();
			this.crearIconoRobVict(tipoEntidad,puntoCursor.x,puntoCursor.y );
		}
	}//GEN-LAST:event_formMouseClicked

	private void jButtonGuardarEscenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarEscenarioActionPerformed
		System.out.println("Ha pulsado el botón Aceptar valores Policias y victimas");
		controladorSim.peticionGuardarEscenario();
		//        String valor ;
		//        setLocationRelativeTo(this);
		//        escenarioActualComp.setIdentEscenario(jTextFieldIdentEquipo.getText());
		//        persistencia.guardarInfoEscenarioSimulacion(directorioPersistencia, escenarioActualComp);
		//
		//        //         String smsg = "Puede cambiar el valor en milisegundos en que deben enviarse las coordenadas";
		//
		//        String smsg = "Se va a guardar el escenario: " +jTextFieldIdentEquipo.getText() ;
		//        JOptionPane.showConfirmDialog(rootPane, smsg,"Confirmar GuardarEscenario",JOptionPane.OK_CANCEL_OPTION );
		//         jOptionPaneAvisoError.setToolTipText(smsg);
		//         jOptionPaneAvisoError.setLocation(20,20);
		//         jOptionPaneAvisoError.setVisible(true);
		//         this.add(jOptionPaneAvisoError);
		//         this.validate();

	}//GEN-LAST:event_jButtonGuardarEscenarioActionPerformed

	private void intervalNumPoliciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intervalNumPoliciasActionPerformed
		int num1 = Integer.parseInt(intervalNumPolicias.getText());
		System.out.println("Numero de robots");
		if (num1>20 ){
			//                    JOptionPane.showInputDialog(rootPane,"El numero de robots tiene que ser menor que 20");
			JOptionPane.showMessageDialog(rootPane,"El numero de robots tiene que ser menor que 20","Error en Numero Entidades", JOptionPane.ERROR_MESSAGE);

		}else System.out.println("Definido el numero de robots : "+ num1);

	}//GEN-LAST:event_intervalNumPoliciasActionPerformed

	private void jMenuItemGuardarEscenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuardarEscenarioActionPerformed
		controladorSim.peticionGuardarEscenario ();
		//         System.out.println("Ha pulsado el botón Aceptar valores Policias y victimas");
		//        String valor ;
		//        setLocationRelativeTo(this);
		//        escenarioActualComp.setIdentEscenario(jTextFieldIdentEquipo.getText());
		//        persistencia.guardarInfoEscenarioSimulacion(directorioPersistencia, escenarioActualComp);
		//
		//        //         String smsg = "Puede cambiar el valor en milisegundos en que deben enviarse las coordenadas";
		//
		//        String smsg = "Se va a guardar el escenario: " +jTextFieldIdentEquipo.getText() ;
		//        JOptionPane.showConfirmDialog(rootPane, smsg,"Confirmar GuardarEscenario",JOptionPane.OK_CANCEL_OPTION );
		//        //         jOptionPaneAvisoError.setToolTipText(smsg);
	}//GEN-LAST:event_jMenuItemGuardarEscenarioActionPerformed

	private void jButtonGuardarEscenarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGuardarEscenarioMousePressed
	}//GEN-LAST:event_jButtonGuardarEscenarioMousePressed

	private void intervalNumLadronsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intervalNumLadronsActionPerformed
		int num1 = Integer.parseInt(intervalNumLadrons.getText());
		System.out.println("Numero de victimas");
		if (num1>20 ){
			JOptionPane.showMessageDialog(rootPane,"El numero de victimas tiene que ser menor que 20","Error en Numero Entidades", JOptionPane.ERROR_MESSAGE);

		}else System.out.println("Definido el numero de victimas : "+ num1);
	}//GEN-LAST:event_intervalNumLadronsActionPerformed

	private void jMenuItemCrearPoliciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCrearPoliciaActionPerformed
		intencionUsuarioCrearPolicia = true;
		intencionUsuarioCrearLadron = false;
	}//GEN-LAST:event_jMenuItemCrearPoliciaActionPerformed

	private void jMenuItemCrearLadronActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCrearLadronActionPerformed
		intencionUsuarioCrearLadron = true;
		intencionUsuarioCrearPolicia = false;
	}//GEN-LAST:event_jMenuItemCrearLadronActionPerformed

	private void jTextFieldIdentEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdentEquipoActionPerformed
	}//GEN-LAST:event_jTextFieldIdentEquipoActionPerformed

	private void jMenuItemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarActionPerformed
		System.out.println("Eliminar menu: "+ "  entidad = "   );
		JLabel compAeliminar =(JLabel)moverComp.getUltimoComponenteSeleccionado();
		compAeliminar.setVisible(false);
		eliminarEntidadSeleccionada();

	}//GEN-LAST:event_jMenuItemEliminarActionPerformed

	private void jMenuItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuardarActionPerformed
		System.out.println("Ha pulsado el botón Aceptar valores Policias y victimas");
		controladorSim.peticionGuardarEscenario();
		//        String valor ;
		//        setLocationRelativeTo(this);
		////        escenarioActualComp.setIdentEscenario(jTextFieldIdentEquipo.getText());
		//        escenarioActualComp.setIdentificadorNormalizado();
		//        persistencia.guardarInfoEscenarioSimulacion(directorioPersistencia, escenarioActualComp);
		//
		//        //         String smsg = "Puede cambiar el valor en milisegundos en que deben enviarse las coordenadas";
		//        jTextFieldIdentEquipo.setText(escenarioActualComp.getIdentEscenario());
		//        String smsg = "Se va a guardar el escenario: " + escenarioActualComp.getIdentEscenario();
		//        JOptionPane.showConfirmDialog(rootPane, smsg,"Confirmar GuardarEscenario",JOptionPane.OK_CANCEL_OPTION );
		//         jOptionPaneAvisoError.setToolTipText(smsg);
	}//GEN-LAST:event_jMenuItemGuardarActionPerformed

	private void jPopupMenuAcionEntidadPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jPopupMenuAcionEntidadPopupMenuWillBecomeVisible
		//         jPopupMenuAcionEntidad.show(, 200, 200);
	}//GEN-LAST:event_jPopupMenuAcionEntidadPopupMenuWillBecomeVisible

	private void jPopupMenuAcionEntidadMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jPopupMenuAcionEntidadMenuKeyPressed
		//        jPopupMenuAcionEntidad.show(this, evt.getX(), e.getY());
	}//GEN-LAST:event_jPopupMenuAcionEntidadMenuKeyPressed

	private void jMenuItemAddPoliciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddPoliciaActionPerformed
		intencionUsuarioCrearPolicia = true;
		intencionUsuarioCrearLadron = false;
		this.crearIconoRobVict("Policia",ultimoPuntoClic.x,ultimoPuntoClic.y );
	}//GEN-LAST:event_jMenuItemAddPoliciaActionPerformed

	private void jMenuItemAddLadronActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddLadronActionPerformed
		intencionUsuarioCrearPolicia = false;
		intencionUsuarioCrearLadron = true;
		this.crearIconoRobVict("Ladron",ultimoPuntoClic.x,ultimoPuntoClic.y );
	}//GEN-LAST:event_jMenuItemAddLadronActionPerformed

	private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed

	}//GEN-LAST:event_jFileChooser1ActionPerformed

	private void jMenuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirActionPerformed
		peticionAbrirEscenario();
	}//GEN-LAST:event_jMenuItemAbrirActionPerformed

	private void jMenuItemNuevoEscenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevoEscenarioActionPerformed
		// Usuario quiere crear un escenario
		// se abre una  ventana vacia , si tiene otra abierta se le debería avisar de que se guardar
		// lo que tiene
		if (escenarioActualComp.getNumPolicias()> 0){
			peticionGuardarEscenario();
			// Se avisa de que el escenario actual se va a guardar antes de abrir el nuevo
			//            escenarioActualComp.setIdentEscenario(jTextFieldIdentEquipo.getText());
			//       
			//
			//        //         String smsg = "Puede cambiar el valor en milisegundos en que deben enviarse las coordenadas";
			//
			//        String smsg = "Se va a guardar el escenario: " +jTextFieldIdentEquipo.getText() ;
			//        JOptionPane.showConfirmDialog(rootPane, smsg,"Confirmar GuardarEscenario",JOptionPane.OK_CANCEL_OPTION );
			//         persistencia.guardarInfoEscenarioSimulacion(directorioPersistencia, escenarioActualComp);
		}
		escenarioActualComp = gestionEscComp.crearEscenarioSimulacion();
		//        jTextFieldIdentEquipo.setText()
		eliminarEntidadesEscenario();
		jTextFieldIdentEquipo.setText(escenarioActualComp.getIdentEscenario());
		intervalNumPolicias.setText(""+0);
		intervalNumLadrons.setText(""+0);

	}//GEN-LAST:event_jMenuItemNuevoEscenarioActionPerformed

	private void jMenuItemModeloJerarquicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModeloJerarquicoActionPerformed
		modeloOrganizativo = "Jerarquico";
		jTextFieldModeloOrganizacion.setText(modeloOrganizativo);
		escenarioActualComp.setmodeloOrganizativo(modeloOrganizativo);
	}//GEN-LAST:event_jMenuItemModeloJerarquicoActionPerformed

	private void jMenuItemModeloIgualitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModeloIgualitarioActionPerformed
		modeloOrganizativo = "Igualitario";
		jTextFieldModeloOrganizacion.setText(modeloOrganizativo);
		escenarioActualComp.setmodeloOrganizativo(modeloOrganizativo);
	}//GEN-LAST:event_jMenuItemModeloIgualitarioActionPerformed

	private void jMenuItemEliminarEscenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarEscenarioActionPerformed
		peticionEliminarEscenario();
	}//GEN-LAST:event_jMenuItemEliminarEscenarioActionPerformed
	private void setIntervaloEnvioMensajesDesdeCC(int intervalo){
		intervaloSecuencia = intervalo ;
		int intervaloEnvioMensajesDesdeCC = 1000;
		String strintervaloEnvioMensajesDesdeCC = "";
	}
	private void jLabelMouseReleased(java.awt.event.MouseEvent evt) {                                   
		// Si tiene una entidad seleccionada se suelta y se anotan la coordenadas
		System.out.println("Released" + " X= "+ evt.getX() +" Y = "+ evt.getY() );
		entidadSeleccionadaParaMover=false;
		evt.consume();

	}  

	public void visualizarIdentsEquipoPolicia ( ArrayList<String> equipoIds){
		//        eqipoIds = eqipoIds.toArray();
		identsPoliciasEquipo = equipoIds;
		//        this.listaComponentes.setListData(identsPoliciasEquipo.toArray());
		//        listaComponentes.setVisible(true);
	}

	public JLabel crearIconoRobVict(String tipoEntidad, int coordX, int coordY){

		JLabel label = new JLabel();
		int correccionX=-30;
		int correccionY=-93;
		coordX=coordX+correccionX;
		coordY=coordY+correccionY;
		String rutaImagen;
		String identEntidad;
		if ( tipoEntidad.startsWith("Policia")){
			rutaImagen=directorioTrabajo+rutaIconos+imageniconoPolicia;
			numeroPolicias= escenarioActualComp.getNumPolicias();
			numeroPolicias++;
			//           intervalNumPolicias.setText(""+numeroPolicias);
			identEntidad=tipoEntidad+numeroPolicias;
			escenarioActualComp.addPoliciaLoc(identEntidad, new Point(coordX,coordY));
			intervalNumPolicias.setText(""+numeroPolicias);
			//           label.setText(tipoEntidad+numeroPolicias);

		}
		else{
			rutaImagen=directorioTrabajo+rutaIconos+imageniconoMujer;
			mumeroLadrons= escenarioActualComp.getNumLadrones();
			mumeroLadrons++;
			//         intervalNumLadrons.setText(""+mumeroLadrons);
			identEntidad=tipoEntidad+mumeroLadrons;
			escenarioActualComp.addLadronLoc(identEntidad, new Point(coordX,coordY));
			//         mumeroLadrons=escenrioSimComp.getNumLadrons();
			intervalNumLadrons.setText(""+mumeroLadrons);
			//         identEntidad=tipoEntidad+mumeroLadrons;
			//         label.setText(tipoEntidad+mumeroLadrons);
		}

		label.setText(identEntidad);
		label.setBounds(10, 10, 100, 100);
		//        label.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
		//            @Override
		//            public void mouseDragged(java.awt.event.MouseEvent evt) {
		//                jLabelMouseDragged(evt);
		//            }
		//            public void mouseReleased(java.awt.event.MouseEvent evt) {
		//                jLabelMouseReleased(evt);
		//            }
		//        });
		this.add(label);
		label.setVisible(true);

		//        label.setIcon(new ImageIcon("E:\\FicheroRed\\Github\\rosaceSIM\\src\\utilsDiseniaEscenariosRosace\\Policia.png"));
		label.setIcon(new ImageIcon(rutaImagen));
		label.setLocation(coordX, coordY);
		System.out.println( "Se crea la entidad : "+label.getText()+ " Coordenadas : X =" + coordX +" , Y = " +coordY );
		//       tablaEntidadesEnEscenario.put(identEntidad, label);
		//       listaEntidadesEnEscenario.add(label);

		moverComp.registerComponent(label);
		return label;
	}
	public void addEntidadEnEscenario (String rutaIcono, String idLadron, Point puntoLoc){
		JLabel label = new JLabel();

		//          String rutaImagen=directorioTrabajo+rutaIconos+imageniconoMujer;
		//           numeroPolicias= escenrioSimComp.getNumPolicias();
		//           numeroPolicias++;
		//           intervalNumPolicias.setText(""+numeroPolicias);
		//           identEntidad=tipoEntidad+numeroPolicias;
		//           escenrioSimComp.addRoboLoc(identEntidad, new Point(coordX,coordY));
		//           intervalNumPolicias.setText(""+numeroPolicias);
		label.setText(idLadron);
		label.setBounds(10, 10, 100, 100);
		this.add(label);
		label.setVisible(true);
		label.setIcon(new ImageIcon(rutaIcono));
		label.setLocation(puntoLoc);
		moverComp.registerComponent(label);
		System.out.println( "Se crea la entidad : "+label.getText()+ " Punto : =" + puntoLoc );
		//       tablaEntidadesEnEscenario.put(identEntidad, label);
	}
	public void eliminarEntidadSeleccionada (){
		JLabel entidadAeliminar=   (JLabel) moverComp.getUltimoComponenteSeleccionado();
		//        escenrioSimComp.eliminarEntidad(((JLabel)moverComp.eliminarUltimoCompSeleccionado()).getName());
		//     moverComp.deregisterComponent(entidadAeliminar);
		entidadAeliminar.setVisible(false);
		escenarioActualComp.eliminarEntidad(entidadAeliminar.getText()); 
		System.out.println( "Se elimina la entidad : "+entidadAeliminar.getText()+ " Coordenadas : X =" + entidadAeliminar.getX() +" , Y = " +entidadAeliminar.getY() );
		actualizarInfoEquipoEnEscenario ();
	}
	public void actualizarInfoEquipoEnEscenario (){
		//        jTextFieldIdentEquipo= escenrioSimComp.get
		jTextFieldModeloOrganizacion.setText(""+escenarioActualComp.getmodeloOrganizativo());
		jTextFieldIdentEquipo.setText(""+escenarioActualComp.getIdentEscenario());
		intervalNumPolicias.setText(""+escenarioActualComp.getNumPolicias());
		intervalNumLadrons.setText(""+escenarioActualComp.getNumLadrones());
		//        intervalNumLadrons.setVisible(true);
	}
	private void setEscenarioActualComp(EscenarioSimulacionPoliciasLadrones escenActualComp){
		escenarioActualComp = escenActualComp;
	}
	private void setPersistencia(PersistenciaVisualizadorEscenarios persistEscenario) {
		//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		persistencia = persistEscenario;
	}
	public void visualizarEscenario(EscenarioSimulacionPoliciasLadrones infoEscenario ){
		eliminarEntidadesEscenario();
		escenarioActualComp= infoEscenario;
		int numPolicias = infoEscenario.getNumPolicias();
		int numVictims = infoEscenario.getNumLadrones();
		jTextFieldIdentEquipo.setText(""+infoEscenario.getIdentEscenario());
		intervalNumPolicias.setText(""+numPolicias);
		intervalNumLadrons.setText(""+numVictims);

		String rutaImagen;
		Set entidades;
		Iterator entries;
		if (numPolicias>0) {
			rutaImagen=directorioTrabajo+rutaIconos+imageniconoPolicia;
			entidades = infoEscenario.getPolicias();
			//          entidades.remove("robotInit");
			entries = entidades.iterator();
			while (entries.hasNext()) {
				Entry thisEntry = (Entry) entries.next();
				addEntidadEnEscenario(rutaImagen,(String)thisEntry.getKey(),(Point)thisEntry.getValue());
				//         intervalNumPolicias.setText(""+infoEscenario.getNumPolicias());
				//         intervalNumLadrons.setText(""+escenrioSimComp.getNumLadrons());
			}
		}
		if (numVictims>0) {
			rutaImagen=directorioTrabajo+rutaIconos+imageniconoMujer;
			entidades = infoEscenario.getLadrones();
			//            entidades.remove("victimInit");
			entries = entidades.iterator();
			while (entries.hasNext()) {
				Entry thisEntry = (Entry) entries.next();
				addEntidadEnEscenario(rutaImagen,(String)thisEntry.getKey(),(Point)thisEntry.getValue());
				//         intervalNumPolicias.setText(""+infoEscenario.getNumPolicias());
				//         intervalNumLadrons.setText(""+escenrioSimComp.getNumLadrons());
			}
		}
		this.setLocation(100,100);
		this.setVisible(true);
	}

	public void setIdentEquipo(String identModeloEquipo){
		identEquipoActual = identModeloEquipo;
		jTextFieldIdentEquipo.setText(identModeloEquipo); 
	}
	private void peticionGuardarEscenario (){
		setLocationRelativeTo(this);
		//        escenarioActualComp.setIdentEscenario(jTextFieldIdentEquipo.getText());
		escenarioActualComp.setIdentificadorNormalizado();

		jTextFieldIdentEquipo.setText(escenarioActualComp.getIdentEscenario());
		String smsg = "Se va a guardar el escenario: " + escenarioActualComp.getIdentEscenario();
		int respuesta= JOptionPane.showConfirmDialog(rootPane, smsg,"Confirmar GuardarEscenario",JOptionPane.OK_CANCEL_OPTION );
		//         jOptionPaneAvisoError.setToolTipText(smsg);
		if (respuesta==JOptionPane.OK_OPTION){
			gestionEscComp.addEscenario(escenarioActualComp);
			persistencia.guardarInfoEscenarioSimulacion(directorioPersistencia, escenarioActualComp);
		}
	}
	public int confirmarPeticionGuardarEscenario (String msgConfirmacion){
		escenarioActualComp.setIdentificadorNormalizado();
		jTextFieldIdentEquipo.setText(escenarioActualComp.getIdentEscenario());
		String smsg = msgConfirmacion + jTextFieldIdentEquipo.getText();
		//       int respuesta= JOptionPane.showConfirmDialog(rootPane, smsg,"Confirmar GuardarEscenario",JOptionPane.OK_CANCEL_OPTION );
		//         jOptionPaneAvisoError.setToolTipText(smsg);
		return JOptionPane.showConfirmDialog(rootPane, smsg,"Confirmar GuardarEscenario",JOptionPane.OK_CANCEL_OPTION );
		//       if (respuesta==JOptionPane.OK_OPTION){
		//           gestionEscComp.addEscenario(escenarioActualComp);
		//             persistencia.guardarInfoEscenarioSimulacion(directorioPersistencia, escenarioActualComp);
		//       }
	}
	public File solicitarSeleccionFichero(String directorio){
		FileNameExtensionFilter filter = new FileNameExtensionFilter("ficheros xml","xml","txt" );

		jFileChooser1.setFileFilter(filter);
		jFileChooser1.setCurrentDirectory(new File(directorio));
		jFileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = jFileChooser1.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return jFileChooser1.getSelectedFile();
		}else return null; // no ha seleccionado nada
	}
	private void eliminarEntidadesEscenario(){
		//         Set entidades;
		//         Iterator entries;
		//         if (infoEscenario.getNumPolicias()>0) {
		//         rutaImagen=directorioTrabajo+rutaIconos+imageniconoPolicia;
		//          entidades = infoEscenario.getPolicias();
		//          entries = entidades.iterator();
		//            while (entries.hasNext()) {
		//                Entry thisEntry = (Entry) entries.next();
		//                Point punto =(Point)thisEntry.getValue();
		//            addEntidadEnEscenario(rutaImagen,(String)thisEntry.getKey(),(Point)thisEntry.getValue());
		//             ((JLabel) this.findComponentAt((Point)thisEntry.getValue())).setVisible(false);
		JLabel labelActual;             
		for( Component comp : this.getContentPane().getComponents() ){
			if (comp instanceof JLabel){
				labelActual = (JLabel)comp;
				if (!labelActual.equals(jLabelIdentEquipo)&&!labelActual.equals(jLabelOrganizacion)
						&&!labelActual.equals(robotIcon)&&!labelActual.equals(victimaIcon1)){                    
					comp.setVisible(false);
					remove(comp);
					System.out.println( "Se borra la entidad : "+ " Coordenadas :  =" + comp.getLocation() );         
				}
			};
		}     
		//         intervalNumPolicias.setText(""+infoEscenario.getNumPolicias());
		//         intervalNumLadrons.setText(""+escenrioSimComp.getNumLadrons());
		//            }
		//        }
	}
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(VisorCreacionEscenarios1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(VisorCreacionEscenarios1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(VisorCreacionEscenarios1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(VisorCreacionEscenarios1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				String  directorioPersistencia = VocabularioRosace.IdentDirectorioPersistenciaSimulacion+File.separator;
				VisorCreacionEscenarios1 visor;
				PersistenciaVisualizadorEscenarios persistencia= new PersistenciaVisualizadorEscenarios();
				GestionEscenariosSimulacion gestionEscComp= new GestionEscenariosSimulacion();
				gestionEscComp.setIdentsEscenariosSimulacion(persistencia.obtenerIdentsEscenarioSimulacion(directorioPersistencia));
				try {
					gestionEscComp = new GestionEscenariosSimulacion();
					gestionEscComp.setIdentsEscenariosSimulacion(persistencia.obtenerIdentsEscenarioSimulacion(directorioPersistencia));
					//        escenarioActualComp = gestionEscComp.crearEscenarioSimulación();
					//                    visor = new VisorCreacionEscenarios1(new ControladorVisualizacionSimulRosace(notifEvts));
					//             
					////                    persistencia= new PersistenciaVisualizadorEscenarios();
					//                    visor.setPersistencia(persistencia);
					//                    visor.setGestorEscenarionComp(gestionEscComp);
					//                    visor.setEscenarioActualComp(gestionEscComp.crearEscenarioSimulación());
					//                    visor.actualizarInfoEquipoEnEscenario();
					//                    visor.setVisible(true);
				} catch (Exception ex) {
					Exceptions.printStackTrace(ex);
				}

			}
		});
	}
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JMenuBar GestionEscenarios;
	private javax.swing.JTextField intervalNumPolicias;
	private javax.swing.JTextField intervalNumLadrons;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButtonGuardarEscenario;
	private javax.swing.JDialog jDialogAvisoErrorDefNumEntidades;
	private javax.swing.JFileChooser jFileChooser1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabelIdentEquipo;
	private javax.swing.JLabel jLabelOrganizacion;
	private javax.swing.JMenu jMenu3;
	private javax.swing.JMenu jMenuEditarEscenario;
	private javax.swing.JMenuItem jMenuItemAbrir;
	private javax.swing.JMenuItem jMenuItemAddPolicia;
	private javax.swing.JMenuItem jMenuItemAddLadron;
	private javax.swing.JMenuItem jMenuItemCrearPolicia;
	private javax.swing.JMenuItem jMenuItemCrearLadron;
	private javax.swing.JMenuItem jMenuItemEliminar;
	private javax.swing.JMenuItem jMenuItemEliminarEscenario;
	private javax.swing.JMenuItem jMenuItemGuardar;
	private javax.swing.JMenuItem jMenuItemGuardarEscenario;
	private javax.swing.JMenuItem jMenuItemModeloIgualitario;
	private javax.swing.JMenuItem jMenuItemModeloJerarquico;
	private javax.swing.JMenuItem jMenuItemModeloOtros;
	private javax.swing.JMenuItem jMenuItemNuevoEscenario;
	private javax.swing.JMenuItem jMenuItemSalir;
	private javax.swing.JMenu jMenuOrganizacion;
	private javax.swing.JOptionPane jOptionPane1;
	private javax.swing.JPopupMenu jPopupMenuAcionEntidad;
	private javax.swing.JPopupMenu jPopupMenuAddEntidades;
	private javax.swing.JPopupMenu.Separator jSeparator1;
	private javax.swing.JPopupMenu.Separator jSeparator2;
	private javax.swing.JPopupMenu.Separator jSeparator3;
	private javax.swing.JPopupMenu.Separator jSeparator4;
	private javax.swing.JPopupMenu.Separator jSeparator5;
	private javax.swing.JPopupMenu.Separator jSeparator6;
	private javax.swing.JSeparator jSeparator7;
	private javax.swing.JPopupMenu.Separator jSeparator8;
	private javax.swing.JTextField jTextFieldIdentEquipo;
	private javax.swing.JTextField jTextFieldModeloOrganizacion;
	private javax.swing.JLabel robotIcon;
	private javax.swing.JLabel victimaIcon1;
	// End of variables declaration//GEN-END:variables

	private void setGestorEscenarionComp(GestionEscenariosSimulacion gestEscComp) {
		//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		this.gestionEscComp=gestEscComp;
	}

	public void visualizarConsejo (String titulo, String msgConsejo, String recomendacion){
		JOptionPane.showMessageDialog(rootPane,msgConsejo + "  "+ recomendacion, titulo,2);
	}
	private void peticionEliminarEscenario() {
		//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		File ficheroseleccionado= peticionUsuarioSeleccionarFichero(directorioPersistencia,"Seleccionar Fichero a Eliminar");
		if(ficheroseleccionado!=null){ 
			//        confirmar eliminacion del fichero y si lo confirma 
			String smsg = "Se va a eliminar el escenario: " + ficheroseleccionado.getName();
			int respuesta=  JOptionPane.showConfirmDialog(rootPane, smsg,"Confirmar EliminarEscenario",JOptionPane.OK_CANCEL_OPTION );
			if ( respuesta== JOptionPane.OK_OPTION) {
				ficheroseleccionado.delete();
				gestionEscComp.eliminarEscenario(ficheroseleccionado.getName());
				System.out.println("Se elimina el fichero :  "+ficheroseleccionado.getName());
			}
		}    


	}
	private void peticionAbrirEscenario() {
		//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		FileNameExtensionFilter filter = new FileNameExtensionFilter("ficheros xml","xml","txt" );

		jFileChooser1.setFileFilter(filter);
		jFileChooser1.setCurrentDirectory(new File(directorioPersistencia));
		jFileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = jFileChooser1.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jFileChooser1.getSelectedFile();
			escenarioActualComp = persistencia.obtenerInfoEscenarioSimulacion(selectedFile.getAbsolutePath());
			escenarioActualComp.setGestorEscenarios(gestionEscComp);
			visualizarEscenario(escenarioActualComp);

			//               fileName = selectedFile.getName();
			// enviariamos el fichero a la persistencia para que nos diera el contenido
			// se visualiza un escenario a partir de la información almacenada
			System.out.println("Ejecuto  accion sobre el fichero "+selectedFile.getAbsolutePath());
		} else {
			System.out.println("File access cancelled by user.");
		}
	}
	public File peticionUsuarioSeleccionarFichero(String directorio, String motivo){
		FileNameExtensionFilter filter = new FileNameExtensionFilter("ficheros xml","xml","txt" );
		jFileChooser1.setDialogTitle(motivo);
		jFileChooser1.setFileFilter(filter);
		jFileChooser1.setCurrentDirectory(new File(directorioPersistencia));
		jFileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = jFileChooser1.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jFileChooser1.getSelectedFile();
			return selectedFile;
		}else{
			// mensaje diciendo que no se ha seleccionado ningun fichero
			return null;
		}
	}
}
