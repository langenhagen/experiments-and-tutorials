import java.awt.event.KeyEvent;

import javax.media.opengl.GL;

import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.MTLight;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.mesh.MTTriangleMesh;
import org.mt4j.input.gestureAction.DefaultRotateAction;
import org.mt4j.input.gestureAction.DefaultZoomAction;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.arcballProcessor.ArcBallGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.arcballProcessor.ArcballProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.rotateProcessor.RotateProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleEvent;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.zoomProcessor.ZoomProcessor;
import org.mt4j.input.inputProcessors.globalProcessors.CursorTracer;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.modelImporter.ModelImporterFactory;
import org.mt4j.util.opengl.GLMaterial;

/**
 * HW_3DScene
 * 
 * -manages the Scene with the Car
 * 
 * @author Barn
 *
 */
public class HW_3DScene extends AbstractScene{
	private MTApplication mtApp;
	
	//TODO switch button/wireframe	// wtf???
	
	/**
	 * Constructor #1
	 */
	public HW_3DScene(MTApplication mtApplication, String name){
		super(mtApplication, name);
		mtApp = mtApplication;
		
		this.setClearColor(new MTColor(40,40,40,255));
		
		// MALT DEN DOOFEN KRINGEL UM DIE MAUS
		this.registerGlobalInputProcessor(new CursorTracer(mtApp, this));
		
		//MACHE CANVAS ZOOMABLE
		this.getCanvas().registerInputProcessor(new ZoomProcessor(mtApp));
		this.getCanvas().addGestureListener(ZoomProcessor.class, new DefaultZoomAction());
		
		
		// WENN DER OPENGL MODE NICHT AN IST
		if (!(MT4jSettings.getInstance().isOpenGlMode())){
			System.err.println(this.getClass().getName() + " example can only be run in OpenGL mode.");
			return;
		}
		
		// LICHT SETZEN		// PUNKTLICHT: I think GL_LIGHT0 is used by processing
		MTLight.enableLightningAndAmbient(mtApplication, 0, 0, 0, 0);	// AMBIENTLIGHT
		MTLight light = new MTLight(mtApplication, GL.GL_LIGHT1, new Vector3D(100, 50,100));	// PUNKTLICHT
		
		// MATERIAL ERSTELLEN
		GLMaterial material = new GLMaterial(Tools3D.getGL(mtApplication));
		material.setAmbient(new float[]{ .1f, .1f, .1f, 1f });		// AMBIENT
		material.setDiffuse(new float[]{ 1f, 0f, 0f, 1f } );		// Streulicht der Punktlichter
		material.setEmission(new float[]{ 0.0f, 0.0f, .0f, 1f });	// Emissives Licht
		material.setSpecular(new float[]{ 1, 1, 1, 1f });			// Spiegelndes Licht
		material.setShininess(120);									// 0=no shine,  127=max shine
		
		// ???
		//Group used to move to the screen center and to put the mesh group in
		MTComponent group = new MTComponent(mtApplication);

		//Create a group and set the light for the whole mesh group ->better for performance than setting light to more comps
		final MTComponent meshGroup = new MTComponent(mtApplication, "Mesh group");
		meshGroup.setLight(light);	// einzelne MTComponents unterstuetzen offenbar nur eine Lichtquelle
		
		//Desired position for the meshes to appear at
		final Vector3D carPos = new Vector3D(mtApplication.width/2, mtApplication.height/2, -500);
		//Desired scale for the meshes
		float carScale = mtApplication.width*0.7f;

		// LADE MESHES AUS DATEI (EINE DATEI KANN MEHR ALS EINE MESH BEINHALTEN)
		MTTriangleMesh[] meshArr = ModelImporterFactory.loadModel(	mtApplication, 					// die App
																	"res/jazz_Obj/honda_jazz.obj",	// die Datei
																	180, 							// Kantigkeitsgrenze <-- sollte per GUI Variabel sein!
																	true, 							// ob die Textur an der y-Achse gespiegelt werden soll
																	false);							// ob die Textur an der x-Achse gespiegelt werden soll

		// REFERENZIERE GROEßE UND SKALIERUNG AN DER GROEßTEN MESH IM ARRAY
		final MTTriangleMesh biggestMesh = this.getBiggestMesh(meshArr);

		Vector3D translationToScreenCenter = new Vector3D(carPos);
		translationToScreenCenter.subtractLocal(biggestMesh.getCenterPointGlobal());

		Vector3D scalingPoint = new Vector3D(biggestMesh.getCenterPointGlobal());
		float biggestWidth = biggestMesh.getWidthXY(TransformSpace.GLOBAL);	
		float scale = carScale/biggestWidth;

		// GROUP TRANSLIEREN & SKALIEREN
		group.scaleGlobal(scale, scale, scale, scalingPoint);
		group.translateGlobal(translationToScreenCenter);
		
		// MESH ANS CANVAS BINDEN
		this.getCanvas().addChild(group);
		group.addChild(meshGroup);

		//NORMALENVEKTOREN INVERTIEREN j/n?
		boolean invertNormals = false;

		// FUER ALLE MESHES SO ZEUGS AUSFUEHREN
        for (MTTriangleMesh mesh : meshArr) {
            meshGroup.addChild(mesh);
            mesh.unregisterAllInputProcessors(); //Clear previously registered input processors
            mesh.setPickable(true);

            // alle normalenvektoren umdrehen, wenns so gesagt wird
            if (invertNormals){
                Vector3D[] normals = mesh.getGeometryInfo().getNormals();
                for (Vector3D normalVec : normals) {
                    normalVec.scaleLocal(-1);
                }
                // normals müssen nochmal gesettet werden
                mesh.getGeometryInfo().setNormals(normals, mesh.isUseDirectGL(), mesh.isUseVBOs());
            }

            // Wenn eine Mesh mehr als 20 Vertexe hat, in eine Displaylist packen, bessere Laufzeit
            if (mesh.getVertexCount() > 20)
                mesh.generateAndUseDisplayLists();
            
            mesh.setMaterial(material);		// Material setzen, wenn du mit dem Standard unzufrieden bist :)
            //mesh.setDrawNormals(true);	// Normalenvektoren mitzeichnen
        }
		
        // *** INPUTPROZESSOREN UND LISTENER HINZUFUEGEN ***

        // Arcball Gesture-Manipulation zu allen Meshes hinzufuegen
		meshGroup.setComposite(true); // diese Komponente anstatt ihrer Kinder beim Pickíng waehlen
		meshGroup.registerInputProcessor(new ArcballProcessor(mtApplication, biggestMesh));
		meshGroup.addGestureListener(ArcballProcessor.class, new IGestureEventListener(){
			//@Override
			public boolean processGestureEvent(MTGestureEvent ge) {
				ArcBallGestureEvent aEvt =  (ArcBallGestureEvent)ge;
				meshGroup.transform(aEvt.getTransformationMatrix());
				return false;
			}
		});
		
		meshGroup.registerInputProcessor(new ScaleProcessor(mtApplication));
		meshGroup.addGestureListener(ScaleProcessor.class, new IGestureEventListener(){
			//@Override
			public boolean processGestureEvent(MTGestureEvent ge){
				ScaleEvent se = (ScaleEvent)ge;
				meshGroup.scaleGlobal(se.getScaleFactorX(), se.getScaleFactorY(), se.getScaleFactorX(), biggestMesh.getCenterPointGlobal());
				return false;
			}
		});
		
		meshGroup.registerInputProcessor(new RotateProcessor(mtApplication));
		meshGroup.addGestureListener(RotateProcessor.class, new DefaultRotateAction());
	}

	
	// HELPER *************************************************************************************
	
	/**
	 * Ermittelt aus einem Mesh-Array die groeßte Mesh und gibt sie zurück.
	 * @param meshArr
	 * @return
	 */
	public MTTriangleMesh getBiggestMesh(MTTriangleMesh[] meshArr){
		
		MTTriangleMesh curBiggestMesh = meshArr[0];
		
		//Get the biggest mesh and extract its width
		float curBiggestWidth = meshArr[0].getWidthXY( TransformSpace.GLOBAL);
		for(int i=1; i<meshArr.length; i++){
			
			float width = meshArr[i].getWidthXY( TransformSpace.GLOBAL);
			if( width>curBiggestWidth){
                curBiggestWidth = width;
                curBiggestMesh = meshArr[i];
			}
		}
		return curBiggestMesh;
	}
	
	
	
	//@Override
	public void init() {
		mtApp.registerKeyEvent(this);
	}

	//@Override
	public void shutDown() {
		mtApp.unregisterKeyEvent(this);
	}
	
	public void keyEvent(KeyEvent e){
		//System.out.println(e.getKeyCode());
		int evtID = e.getID();
		if (evtID != KeyEvent.KEY_PRESSED)
			return;
		switch (e.getKeyCode()){
		case KeyEvent.VK_F:
			System.out.println("FPS: " + mtApp.frameRate);
			break;
		case KeyEvent.VK_PLUS:
			this.getSceneCam().moveCamAndViewCenter(0, 0, -10);
			break;
		case KeyEvent.VK_MINUS:
			this.getSceneCam().moveCamAndViewCenter(0, 0, +10);
			break;
		case KeyEvent.VK_F12:
			getMTApplication().saveFrame(); //Screenshot
			break;
			default:
				break;
		}
	}


}
