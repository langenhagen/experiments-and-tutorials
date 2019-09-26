/**
 * 
 */
package org.sercho.masp.models.Context.gui;

import javax.swing.JComponent;

import org.sercho.masp.models.Context.Environment;

import de.dailab.masp.models.MegaModel.MetaModelResource;
import de.dailab.masp.models.MegaModel.ModelResource;
import de.dailab.masp.models.MetaEditor.AbstractEditorPageFactory;
import de.dailab.masp.models.MetaEditor.swing.MetaEditorComponentObserver;
import de.dailab.masp.models.MetaEditor.swing.SwingEditorPage;
import de.dailab.masp.models.MetaMetaModel.ModelCallback;

/**
 * <code>ContextModelVisualizerPageFactory</code> creates
 * {@link ContextModelVisualizer} pages.
 * 
 * @author Grzegorz Lehmann
 * @since 1.0
 */
public final class ContextModelVisualizerPageFactory extends AbstractEditorPageFactory {

    public static final class ContextModelVisualizerPage implements SwingEditorPage {

        private volatile Environment environment;

        /**
         * {@inheritDoc}
         */
        @Override
        public JComponent getPageComponent() {
            return new VisualizerManager(this.environment, VisualizerManager.DisplayType.EMBEDDED).getMainPanel();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isStarted() {
            return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setObserver(final MetaEditorComponentObserver newObserver) {
            // TODO Auto-generated method stub

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void showModel(final ModelResource<?> newModelResource) {
            this.environment = (Environment)newModelResource.getValue();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void start(final Object configuration, final ModelCallback callback) {
            // nothing to do here yet
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void stop() {
            // nothing to do here yet
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends SwingEditorPage> getPageClassHook(final MetaModelResource metaModel, final ModelResource<?> model) {
        return ContextModelVisualizerPage.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void startHook(final Object configuration, final ModelCallback callback) {
        // nothing to do here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void stopHook() {
        // nothing to do here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean supportsHook(final MetaModelResource metaModel) {
        return "ContextMetaModel.xmi".equals(metaModel.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRuntimeView() {
        return true;
    }
}