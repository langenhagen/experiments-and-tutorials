package org.mt4j.test.components.visibleComponents.font;

import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.test.AbstractWindowTestcase;
import org.mt4j.test.testUtil.DummyScene;
import org.mt4j.test.testUtil.TestRunnable;
import org.mt4j.util.MTColor;

public class FontLoadingTest extends AbstractWindowTestcase {

	private DummyScene scene;

	@Override
	public void inStartUp(MTApplication app) {
		this.scene = new DummyScene(app, "scene");
		app.addScene(scene);
	}
	
	public void testLoadFonts(){
		this.runTest(new TestRunnable() {
			@Override
			public void runMTTestCode() {
				IFont font1 = FontManager.getInstance().createFont(getMTApplication(), "arial.ttf", 16, MTColor.GREY, true);
				IFont font2 = FontManager.getInstance().createFont(getMTApplication(), "arial", 16, MTColor.BLACK, false);
				
				MTTextArea ta = new MTTextArea(getMTApplication(), font1);
				ta.setText("this is a\ntest text written for testing\npurposes");
				scene.getCanvas().addChild(ta);
				ta.setFont(font2);
				
				MTTextArea ta2 = new MTTextArea(getMTApplication(),200, 200,100, 200, font2);
				ta2.setText("this is another test text written for testing purposes");
				scene.getCanvas().addChild(ta2);
				ta2.setFont(font1);
			}
		});
	}
	
	

}
