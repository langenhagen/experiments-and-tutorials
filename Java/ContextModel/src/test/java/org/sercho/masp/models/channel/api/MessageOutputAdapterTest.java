/**
 * 
 */
package org.sercho.masp.models.channel.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.sercho.masp.models.UI.MessageOutput;
import org.sercho.masp.models.UI.UIFactory;

/**
 * <code>MessageOutputAdapterTest</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 1.1
 */
public class MessageOutputAdapterTest extends TestCase {

    private final static class TestMessageOutputChannelAPI extends
            AbstractMessageOutputChannelAPI {

        /**
         * <code>TestMessageOutputChannelAPI</code> constructor.
         */
        TestMessageOutputChannelAPI() {
            // increased visibility
        }

        final Map<String, String> active = new HashMap<String, String>();

        final Map<String, String> focused = new HashMap<String, String>();

        final Map<String, String> selected = new HashMap<String, String>();

        /**
         * {@inheritDoc}
         */
        @Override
        protected void activated(final String elementID, final String text) {
            removed(elementID);
            this.active.put(elementID, text);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void focused(final String elementID, final String text) {
            removed(elementID);
            this.focused.put(elementID, text);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void removed(final String elementID) {
            this.active.remove(elementID);
            this.focused.remove(elementID);
            this.selected.remove(elementID);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void selected(final String elementID, final String text) {
            removed(elementID);
            this.selected.put(elementID, text);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setCallback(final OneDimensionalCallback newCallback) {
            // TODO Auto-generated method stub

        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void close() {
            // TODO Auto-generated method stub

        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void startHook(final Map<String, String> configurationMap) {
            // TODO Auto-generated method stub

        }

    }

    public final void testNotify() {
        final TestMessageOutputChannelAPI api = new TestMessageOutputChannelAPI();
        final MessageOutputAdapter adapter = new MessageOutputAdapter(api);
        final MessageOutput message = UIFactory.eINSTANCE.createMessageOutput();
        adapter.messageOutputAdded(message);
        message.setId("M1");
        message.setMessage("HeeeEEEEE?????!?!??!?!!");
        assertArraysMatchedCollection(api.active.keySet());
        assertArraysMatchedCollection(api.focused.keySet());
        assertArraysMatchedCollection(api.selected.keySet());
        message.activate();
        message.setId("M2");
        message.setId("M1");
        assertArraysMatchedCollection(api.active.keySet(), message.getId());
        assertArraysMatchedCollection(api.focused.keySet());
        assertArraysMatchedCollection(api.selected.keySet());
        message.focus();
        assertArraysMatchedCollection(api.active.keySet());
        assertArraysMatchedCollection(api.focused.keySet(), message.getId());
        assertArraysMatchedCollection(api.selected.keySet());
        message.select();
        assertArraysMatchedCollection(api.active.keySet());
        assertArraysMatchedCollection(api.focused.keySet());
        assertArraysMatchedCollection(api.selected.keySet(), message.getId());
        message.unselect();
        assertArraysMatchedCollection(api.active.keySet());
        assertArraysMatchedCollection(api.focused.keySet(), message.getId());
        assertArraysMatchedCollection(api.selected.keySet());
        message.unfocus();
        assertArraysMatchedCollection(api.active.keySet(), message.getId());
        assertArraysMatchedCollection(api.focused.keySet());
        assertArraysMatchedCollection(api.selected.keySet());
    }

    private static <C> void assertArraysMatchedCollection(final Collection<C> claim, final C... reference) {
        assertEquals(reference.length, claim.size());
        for(final C c : reference) {
            assertTrue(claim.contains(c));
        }
    }
}
