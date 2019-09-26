package src;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.components.interfaces.IMTController;
import org.mt4j.components.visibleComponents.shapes.GeometryInfo;
import org.mt4j.components.visibleComponents.shapes.MTEllipse;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.input.IMTInputEventListener;
import org.mt4j.input.inputData.AbstractCursorInputEvt;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputData.MTInputEvent;
import org.mt4j.input.inputProcessors.globalProcessors.CursorTracer;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;
import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;

import processing.core.PImage;

/**
 * Szene - relevantes Ding zum abgehn mit mt4j
 * @author Barn
 *
 */
public class HelloWorldScene extends AbstractScene{
		
	// Standardkonstruktor
	public HelloWorldScene(MTApplication mtApplication, String name){
		
		// Canvas & Standardkram*******************************************************************
		super(mtApplication, name);
		
		this.setClearColor(new MTColor(146, 150, 188, 255));	// BGColor
		
		// Maus- bzw Toucheingaben auswerten
		this.registerGlobalInputProcessor(new CursorTracer(mtApplication, this));	
		
		
		// Komponenten hinzufuegen*****************************************************************
		
		// Schriftart erstelen
		IFont font = FontManager.getInstance().createFont(mtApplication, "./../res/KINKEE.TTF", 
					50,								   	//Font size
					new MTColor(255, 255, 255, 255),  	//Font fill color
					new MTColor(255, 255, 255, 255)); 	//Font outline color
		
		// textArea anlegen & einstellen
		MTTextArea textArea = new MTTextArea(mtApplication, font);
		textArea.setName("text");
                	
		textArea.setFillColor(new MTColor(122,10,40));
		textArea.setNoStroke(true);
		textArea.setText("Hello World!");
		textArea.setPositionGlobal(new Vector3D(mtApplication.width/2f, mtApplication.height/2f));
		getCanvas().addChild(textArea);	// textArea als Child zum Szenengraphen hinzufuegen
		
		// Rechteck als Child zur Textarea erstellen
		MTRectangle rect = new MTRectangle(0, 0, 100, 30, mtApplication);
		rect.setName("rect");
		rect.setFillColor( new MTColor(20,20,20));
		//rect.setPickable(false); 	//deaktiviert die moeglichkeit zur interaktion
		textArea.addChild( rect);

		rect.updateComponent(100);
		
		// Polygon zeichnen!***********************************************************************
		
		Vertex[] vArr1 = new Vertex[]{	new Vertex(0, 0, -30, 255, 0, 0, 255),
									new Vertex(100, 0, 0, 50 , 255, 0, 255),
									new Vertex(50, 50, 0, 50, 0, 255, 255),
									new Vertex(100, 75, -100, 0, 255, 0, 255)};	// farbe des abschlussvertex egal!!
		
		
		MTPolygon p = new MTPolygon( mtApplication, vArr1);
		p.setFillColor( new MTColor( 255, 0, 0));
		
		getCanvas().addChild( p);

		// Standardpolygon zeichnen!***************************************************************
		
		MTEllipse e = new MTEllipse( mtApplication, new Vector3D(300,300), 100, 60);
		getCanvas().addChild( e);		

		Vertex[] vArr2 = new Vertex[]{	new Vertex(200, 0, 0, 255, 0, 0, 255),
										new Vertex(300, 0, 0, 50 , 255, 0, 255),
										new Vertex(250, 50, 0, 50, 0, 255, 255),
										new Vertex(200, 0, 0, 0, 255, 0, 255)};	// farbe des abschlussvertex egal!!

		GeometryInfo gi1 = e.getGeometryInfo();
		GeometryInfo gi2 = new GeometryInfo( mtApplication, vArr2);
		
		e.setGeometryInfo( gi2); // die Ellipse wird nie dargestellt, stattdessen erscheint ein dreieck
		e.setGeometryInfo( gi1); // das mit dem Dreieck wieder rueckgaengig machen

		e.setLineStipple(  (short)10);
		e.setStrokeWeight( 5);
		
		// Die Dimensionen der Textur sollten 2er-Potenzen sein!
		PImage img = mtApplication.loadImage( "res/Blind3.png");
		e.setTexture( img);

		
		// einen Controller für das Rechteck einsetzen!********************************************
		// der controller kann ähnlich wie eine Animation im Idle Modus, in jedem neuen frame etwas
		// machen. is ne tolle sache, das!
		rect.setController( new IMTController(){

			byte state=0;
			
			@Override
			public void update(long timeDelta) {
				MTRectangle rect = (MTRectangle)getCanvas().getChildByName("text").getChildByName("rect");
				if(rect==null)
					System.out.println("!!!!");
				
				MTColor color = rect.getFillColor();
				
				float r = color.getR();
				float g = color.getG();
				float b = color.getB();

				
				if(state==0){
					if(r==255){
						state+=1;
						color.setR( 0 );
						}
					else
						color.setR( r+1);
				}
				else if(state==1){
					if(g==255){
						state+=1;
						color.setG( 0 );
						}
					else
						color.setG( g+1);
				}
				else if(state==2){
					if(b==255){
						state=0;
						color.setB( 0 );
						}
					else
						color.setB( b+1);
				}

				rect.setFillColor(color);
			}
			
		});
		
		// Input-Listener für das Rechteck!********************************************************
		rect.addInputListener(new IMTInputEventListener() {
			
			public boolean processInputEvent(MTInputEvent inEvent) {	// Methodenimplementation
						
				if (inEvent instanceof AbstractCursorInputEvt) { //Most input events in MT4j are an instance of AbstractCursorInputEvt (mouse, multi-touch..)
					AbstractCursorInputEvt cursorInEvent = (AbstractCursorInputEvt)inEvent;
					InputCursor cursor = cursorInEvent.getCursor();		// extrahiere Cursor
					IMTComponent3D target = cursorInEvent.getTarget();	// extrahiere Target
					switch (cursorInEvent.getId()) {
					case AbstractCursorInputEvt.INPUT_DETECTED:		// EINGABE BEGINNT
						System.out.println("Input detected on: " + target + " at " + cursor.getCurrentEvtPosX() + "," + cursor.getCurrentEvtPosY());
						
						changeColor( cursor.getCurrentEvtPosX(), cursor.getCurrentEvtPosY());
						
						break;
					case AbstractCursorInputEvt.INPUT_UPDATED:		// EINGABE DATED UP (zB drag/drop)
						System.out.println("Input updated on: " + target + " at " + cursor.getCurrentEvtPosX() + "," + cursor.getCurrentEvtPosY());			
						
						changeColor( cursor.getCurrentEvtPosX(), cursor.getCurrentEvtPosY());
						
						break;
					case AbstractCursorInputEvt.INPUT_ENDED:		// EINGABE ENDET
						System.out.println("Input ended on: " + target + " at " + cursor.getCurrentEvtPosX() + "," + cursor.getCurrentEvtPosY());
						break;
					default:
						break;
					}
				}else{
					//handle other input events
				}
			return false;
			}
			
			
			private void changeColor(float posX, float posY){		// Interne Hilfsfunktion
				
				MTTextArea text = (MTTextArea)getCanvas().getChildByName("text");
				
				float height = getMTApplication().getHeight();
				float width = getMTApplication().getWidth();
				
				float g = (text.getFillColor().getG()+1)%255;
				
				text.setFillColor( new MTColor(posX/width*255, g, posY/height*255));
			}
		
		});
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		
	}
}