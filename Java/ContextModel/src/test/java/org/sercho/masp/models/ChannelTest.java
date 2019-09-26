/**
 * File     ChannelTest.java
 * Package  org.sercho.masp.models
 * Project  ChannelModel
 * Date     01.10.2007
 * Web      http://www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models;

import org.junit.Ignore;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.UI.Button;
import org.sercho.masp.models.UI.Command;
import org.sercho.masp.models.UI.Pointing;
import org.sercho.masp.models.UI.UIFactory;
import org.sercho.masp.models.UI.UIModel;
import org.sercho.masp.models.UI.UIPackage;

/**
 * <code>ChannelTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 1.0-alpha
 */
@Ignore
public final class ChannelTest {

    /**
     * <code>main</code> for tests
     * 
     * @param args
     *            program arguments, ignored
     */
    public static void main(final String[] args) {
        final ContextFactory channelFactory = ContextPackage.eINSTANCE.getContextFactory();
        final UIFactory factory = UIPackage.eINSTANCE.getUIFactory();

        final UIModel uiModel = factory.createUIModel();
        final Command navigation1 = factory.createCommand();
        uiModel.getAbstractInteractors().add(navigation1);
        final Command navigation2 = factory.createCommand();
        uiModel.getAbstractInteractors().add(navigation2);

        final Pointing pointing1 = factory.createPointing();
        final Button button1 = factory.createButton();
        button1.setName("Button1");
        button1.setPosition(0, 0);
        button1.setSize(100, 100);
        navigation1.getGraphicalOutput().add(button1);
        navigation1.getExecuteInput().add(pointing1);

        final Pointing pointing2 = factory.createPointing();
        final Button button2 = factory.createButton();
        button2.setName("Button2");
        button2.setPosition(100, 50);
        button2.setSize(100, 100);
        navigation2.getGraphicalOutput().add(button2);
        navigation2.getExecuteInput().add(pointing2);

        // final PointingInputChannel pointingChannel =
        // channelFactory.createTouchChannel();
        // pointingChannel.setPosition(0, 0);
        // pointingChannel.setSize(200, 200);
        // pointingChannel.add(pointing1);
        // pointingChannel.add(pointing2);
        //
        // navigation1.eAdapters().add(new SingletonAdapterImpl() {
        // /**
        // * {@inheritDoc}
        // */
        // @Override
        // public void notifyChanged(Notification msg) {
        // // System.out.println(msg.getOldValue() + " " + msg.getNewValue());
        // }
        // });
        //
        // final SwingGraphicalOutputChannel swingChannel =
        // channelFactory.createSwingChannel();
        // swingChannel.setSize(200, 100);
        // swingChannel.add(button1);
        // swingChannel.add(button2);
        // final SwingGraphicalOutputChannel swingChannel2 =
        // channelFactory.createSwingChannel();
        // swingChannel2.setSize(200, 100);
        // swingChannel2.add(button2);
        // final SwingGraphicalOutputChannel swingChannel3 =
        // channelFactory.createSwingChannel();
        // swingChannel3.setSize(200, 100);
        // swingChannel3.add(button1);
        //
        // navigation1.setNewState(UIElementState.ACTIVE);
        // navigation2.setNewState(UIElementState.ACTIVE);
        //
        // ((SwingGraphicalOutputChannel)swingChannel).panel.addMouseMotionListener(new
        // MouseAdapter() {
        // /**
        // * {@inheritDoc}
        // */
        // @Override
        // public void mouseMoved(MouseEvent e) {
        // pointingChannel.getReceiver().newPointing(e.getX(), e.getY());
        // }
        // });
    }
}