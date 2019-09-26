/**
 * 
 */
package org.sercho.masp.models.Context;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.sercho.masp.models.EMFTestCase;
import org.sercho.masp.models.UI.Button;
import org.sercho.masp.models.UI.Command;
import org.sercho.masp.models.UI.GraphicalOutput;
import org.sercho.masp.models.UI.GraphicalPropertySet;
import org.sercho.masp.models.UI.LookAndFeel;
import org.sercho.masp.models.UI.UIFactory;
import org.sercho.masp.models.UI.UIModel;
import org.sercho.masp.models.channel.api.GraphicalOutputChannelAPI;
import org.sercho.masp.models.channel.api.TwoDimensionalCallback;

import de.dailab.masp.models.MetaMetaModel.ModelCallback;

/**
 * <code>GraphicalOutputChannelTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since TODO
 */
public abstract class GraphicalOutputChannelTest extends EMFTestCase<ContextFactory> {

    private final class TestGraphicalOutputChannelAPI implements
            GraphicalOutputChannelAPI {

        /**
         * <code>GraphicalOutputChannelTest.TestGraphicalOutputChannelAPI</code>
         * constructor.
         */
        TestGraphicalOutputChannelAPI() {
            // increased visibility
        }

        final Map<String, String> elementIDs = new HashMap<String, String>();

        final Set<String> lookAndFeelIDs = new HashSet<String>();

        @Override
        public void addButton(final String elementID, final String lookAndFeelID, final int x, final int y, final int z, final int width, final int height, final String text) {
            this.elementIDs.put(elementID, lookAndFeelID);
        }

        @Override
        public void addImage(final String elementID, final String lookAndFeelID, final String url, final int x, final int y, final int z, final int width, final int height) {
            // TODO Auto-generated method stub

        }

        @Override
        public void addTextLabel(final String elementID, final String lookAndFeelID, final int x, final int y, final int z, final int width, final int height, final String text) {
            // TODO Auto-generated method stub

        }

        @Override
        public void newHeight(final String elementID, final int newHeight) {
            // TODO Auto-generated method stub

        }

        @Override
        public void newPointingX(final int relativeX) {
            // TODO Auto-generated method stub

        }

        @Override
        public void newPointingY(final int relativeY) {
            // TODO Auto-generated method stub

        }

        @Override
        public void newPositionX(final String elementID, final int relativeX) {
            // TODO Auto-generated method stub

        }

        @Override
        public void newPositionY(final String elementID, final int relativeY) {
            // TODO Auto-generated method stub

        }

        @Override
        public void newPositionZ(final String elementID, final int z) {
            // TODO Auto-generated method stub

        }

        @Override
        public void newText(final String elementID, final String newText) {
            // TODO Auto-generated method stub

        }

        @Override
        public void newURL(final String elementID, final String newURL) {
            // TODO Auto-generated method stub

        }

        @Override
        public void newWidth(final String elementID, final int newWidth) {
            // TODO Auto-generated method stub

        }

        @Override
        public void removeLookAndFeel(final String lookAndFeelID) {
            this.lookAndFeelIDs.remove(lookAndFeelID);
        }

        @Override
        public void setCallback(final TwoDimensionalCallback newCallback) {
            // TODO Auto-generated method stub

        }

        @Override
        public void setLookAndFeel(final String lookAndFeelID, final String backgroundColor, final String fontColor, final String fontName, final int fontSize, final String fontStyle, final String borderColor, final int borderWidth) {
            this.lookAndFeelIDs.add(lookAndFeelID);
        }

        @Override
        public void setLookAndFeelOfElement(final String elementID, final String lookAndFeelID) {
            this.elementIDs.put(elementID, lookAndFeelID);
        }

        @Override
        public void remove(final String elementID) {
            this.elementIDs.remove(elementID);
        }

        public boolean supports(final GraphicalOutput element) {
            return true;
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public boolean isAvailable() {
            // TODO Auto-generated method stub
            return false;
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public boolean isStarted() {
            // TODO Auto-generated method stub
            return false;
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public void start(final Object configuration, final ModelCallback callback) {
            // TODO Auto-generated method stub

        }

        /**
         * {@inheritDoc}
         */

        @Override
        public void stop() {
            // TODO Auto-generated method stub

        }

    }

    private final TestGraphicalOutputChannelAPI api = new TestGraphicalOutputChannelAPI();

    protected GraphicalOutputChannelTest(final ContextFactory contextFactory) {
        super(contextFactory);
    }

    /**
     * Test method for
     * {@link org.sercho.masp.models.Context.Channel#add(org.sercho.masp.models.UI.ConcreteInteractor)}
     * .
     */
    public final void testAdd() {
        final GraphicalOutputChannel channel = this.factory.createGraphicalOutputChannel();
        channel.setApi(this.api);

        // set up look and feel
        final LookAndFeel lf = UIFactory.eINSTANCE.createLookAndFeel();
        GraphicalPropertySet properties = UIFactory.eINSTANCE.createGraphicalPropertySet();
        properties.setBackgroundColor(UIFactory.eINSTANCE.createBlack());
        properties.setFontColor(UIFactory.eINSTANCE.createWhite());
        lf.setActive(properties);
        properties = UIFactory.eINSTANCE.createGraphicalPropertySet();
        properties.setBackgroundColor(UIFactory.eINSTANCE.createBlue());
        properties.setFontColor(UIFactory.eINSTANCE.createWhite());
        lf.setFocused(properties);
        properties = UIFactory.eINSTANCE.createGraphicalPropertySet();
        properties.setBackgroundColor(UIFactory.eINSTANCE.createRed());
        properties.setFontColor(UIFactory.eINSTANCE.createGreen());
        lf.setSelected(properties);

        // set up button
        final Button button = UIFactory.eINSTANCE.createButton();
        button.setText("Cancel");
        button.setHeight(50);
        button.setWidth(200);
        button.setX(100);
        button.setY(100);
        button.setZ(2);
        button.setLookAndFeel(lf);

        // set up UI model
        final UIModel uiModel = UIFactory.eINSTANCE.createUIModel();
        uiModel.getLookAndFeels().add(lf);
        final Command cancelCommand = UIFactory.eINSTANCE.createCommand();
        cancelCommand.getGraphicalOutput().add(button);
        cancelCommand.setModel(uiModel);

        // start test
        channel.add(button);
        // api should contain 3 L&Fs
        assertEquals(3, this.api.lookAndFeelIDs.size());
        assertFalse("Inactive interactor added to API", this.api.elementIDs.containsKey(button.getId()));
        button.activate();
        // now element should be on channel
        assertTrue(this.api.elementIDs.containsKey(button.getId()));
        // its L&F should be one of the existing ones
        assertTrue(this.api.lookAndFeelIDs.contains(this.api.elementIDs.get(button.getId())));
        // the ACTIVE L&F should be set
        assertEquals(lf.getName() + "_ACTIVE", this.api.elementIDs.get(button.getId()));

        // focus element and check if L&F changes
        button.focus();
        assertTrue(this.api.lookAndFeelIDs.contains(this.api.elementIDs.get(button.getId())));
        assertEquals(lf.getName() + "_FOCUSED", this.api.elementIDs.get(button.getId()));

        // select element and check if L&F changes
        button.select();
        assertTrue(this.api.lookAndFeelIDs.contains(this.api.elementIDs.get(button.getId())));
        assertEquals(lf.getName() + "_SELECTED", this.api.elementIDs.get(button.getId()));

        button.unselect();
        assertTrue(this.api.lookAndFeelIDs.contains(this.api.elementIDs.get(button.getId())));
        assertEquals(lf.getName() + "_FOCUSED", this.api.elementIDs.get(button.getId()));

        button.unfocus();
        assertTrue(this.api.lookAndFeelIDs.contains(this.api.elementIDs.get(button.getId())));
        assertEquals(lf.getName() + "_ACTIVE", this.api.elementIDs.get(button.getId()));

        // deactivate, interactor should disappear
        button.deactivate();
        assertFalse(this.api.elementIDs.containsKey(button.getId()));

        button.activate();
        assertTrue(this.api.lookAndFeelIDs.contains(this.api.elementIDs.get(button.getId())));
        assertEquals(lf.getName() + "_ACTIVE", this.api.elementIDs.get(button.getId()));

        // test if interactor is removed correctly
        channel.remove(button);
        assertFalse(this.api.elementIDs.containsKey(button.getId()));
    }
}