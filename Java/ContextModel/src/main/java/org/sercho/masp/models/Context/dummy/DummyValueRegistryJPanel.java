/**
 * 
 */
package org.sercho.masp.models.Context.dummy;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.LF5Appender;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.sercho.masp.models.XMIUtility;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.dummy.DummyValueRegistry.DummyValueObserver;

import de.dailab.masp.models.MetaMetaModel.MetaModel;
import de.dailab.masp.models.MetaMetaModel.ecore.EcoreMetaModelConverter;

/**
 * <code>DummyValueRegistryJPanel</code> TODO comment
 * 
 * @todo add missing JavaDoc
 * 
 * @author Grzegorz Lehmann
 * @since 3.0
 */
public class DummyValueRegistryJPanel extends JPanel {

    static final Log LOG = LogFactory.getLog(DummyValueRegistryJPanel.class);

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5809424684058273384L;

    private static final class DummyValueRegistryActionListener implements ActionListener {

        /**
         * <code>DummyValueRegistryActionListener</code> constructor.
         * 
         * @param serviceIdentifier
         *            ID of the service to set the values to
         */
        DummyValueRegistryActionListener(final String serviceIdentifier) {
            if(serviceIdentifier == null) {
                throw new IllegalArgumentException("serviceIdentifier is null");
            }
            this.serviceID = serviceIdentifier;
        }

        private final String serviceID;

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(final ActionEvent e) {
            final String newValue = JOptionPane.showInputDialog(null, "Please input new value", DummyValueRegistry.getValue(this.serviceID));
            if(newValue == null) {
                return;
            }
            DummyValueRegistry.set(this.serviceID, newValue);
        }
    }

    private static final class JComponentColorSettingThread extends Thread {

        final JComponent component;

        final Color color;

        private final long timeout;

        /**
         * <code>JComponentColorSettingThread</code> constructor.
         * 
         * @param jComponent
         *            component in which the color should be set
         * @param colorToSet
         *            color to set
         * @param timeToWait
         *            timeout to wait before setting the color
         */
        JComponentColorSettingThread(final JComponent jComponent, final Color colorToSet,
                final long timeToWait) {
            super("Color setting thread");
            if(jComponent == null) {
                throw new IllegalArgumentException("jComponent argument must not be null in constructor JComponentColorSettingThread");
            }
            if(colorToSet == null) {
                throw new IllegalArgumentException("colorToSet argument must not be null in constructor JComponentColorSettingThread");
            }
            setDaemon(true);
            this.component = jComponent;
            this.color = colorToSet;
            if(timeToWait < 0) {
                this.timeout = 0;
            } else {
                this.timeout = timeToWait;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            try {
                sleep(this.timeout);
                SwingUtilities.invokeAndWait(new Runnable() {

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    public void run() {
                        JComponentColorSettingThread.this.component.setForeground(JComponentColorSettingThread.this.color);
                    }
                });

            }
            catch(final InterruptedException e) {
                // cancel
            }
            catch(final InvocationTargetException e) {
                LOG.warn("Failed to set foreground color in component", e.getTargetException());
            }
            catch(final Exception e) {
                LOG.warn("Unexpected error: " + e.getMessage(), e);
            }
        }
    }

    private final class GUIValueObserver implements DummyValueObserver {

        private final Map<String, JLabel> valueLabels = new HashMap<String, JLabel>();

        private final Map<String, Thread> valueLabelColorThreads = new HashMap<String, Thread>();

        private GUIValueObserver() {
            add(new JLabel("Name:"));
            add(new JLabel("Value:"));
            add(new JLabel(""));

            final Map<String, String> allValues = DummyValueRegistry.getAllValues();
            final TreeSet<String> sortedKeySet = new TreeSet<String>(allValues.keySet());
            for(final String key : sortedKeySet) {
                newValue(key, allValues.get(key));
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int compareTo(final Object o) {
            return this == o ? 0 : -1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void newValue(final String sensorID, final String newValue) {
            JLabel valueLabel = this.valueLabels.get(sensorID);
            if(valueLabel == null) {
                valueLabel = createValueLabel(sensorID);
            }
            valueLabel.setForeground(new Color(0, 128, 0));
            valueLabel.setText(newValue);
            Thread labelColorThread = this.valueLabelColorThreads.get(sensorID);

            if((labelColorThread != null) && labelColorThread.isAlive()) {
                // interrupt current thread
                labelColorThread.interrupt();
            }
            labelColorThread = new JComponentColorSettingThread(valueLabel, Color.BLACK, 5000);
            labelColorThread.start();
            this.valueLabelColorThreads.put(sensorID, labelColorThread);
        }

        private synchronized JLabel createValueLabel(final String sensorID) {
            final JLabel valueLabel = new JLabel("");
            this.valueLabels.put(sensorID, valueLabel);
            final JLabel titleLabel = new JLabel(sensorID);
            final JButton setButton = new JButton("SET");
            setButton.addActionListener(new DummyValueRegistryActionListener(sensorID));

            final Runnable addRunnable = new Runnable() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public void run() {
                    add(titleLabel);
                    add(valueLabel);
                    add(setButton);
                }
            };

            if(SwingUtilities.isEventDispatchThread()) {
                addRunnable.run();
            } else {
                try {
                    SwingUtilities.invokeAndWait(addRunnable);
                }
                catch(final InterruptedException e) {
                    // bail out immediately
                    LOG.warn("Interrupted", e);
                    return null;
                }
                catch(final InvocationTargetException e) {
                    LOG.warn("Failed to add GUI components for new dummy sensor", e);
                }
            }
            return valueLabel;
        }
    }

    /**
     * <code>DummyValueRegistryJPanel</code> constructor.
     */
    public DummyValueRegistryJPanel() {
        super(new GridLayout(0, 3));
        DummyValueRegistry.addObserver(new GUIValueObserver());
    }

    /**
     * <code>main</code> for tests
     * 
     * @param args
     *            program arguments, ignored
     */
    public static void main(final String[] args) {
        Logger.getRootLogger().addAppender(new LF5Appender());
        final JFrame jFrame = new JFrame("Dummy value registry");
        jFrame.getContentPane().add(new DummyValueRegistryJPanel());
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setSize(300, 100);
        jFrame.setVisible(true);

        final Environment smartHome;
        try {
            smartHome = (Environment)XMIUtility.convert(DummyValueRegistryJPanel.class.getClassLoader().getResourceAsStream("org/sercho/masp/models/Context/dummy/DummyValueRegistryTest.xmi"), ContextPackage.eINSTANCE).get(0);
        }
        catch(final IOException e) {
            LOG.error("Failed to load SmartHome object, error: " + e.getMessage(), e);
            return;
        }
        System.out.println("test");
        final MetaModel metaModel = EcoreMetaModelConverter.INSTANCE.getCachedOrConvertMetaModel(ContextPackage.eINSTANCE);
        metaModel.getMetaType().getMetaModel().start(metaModel);
        metaModel.start(smartHome);

        System.out.println("Stated Smart Home");
        for(final Device device : smartHome.getDevices()) {
            if(device.getName().equals("MeineTolleLampe")) {
                device.eAdapters().add(new EContentAdapter() {

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    public void notifyChanged(final Notification notification) {
                        super.notifyChanged(notification);
                        System.out.println(notification.getOldValue() + " -> " + notification.getNewValue());
                    }
                });

                return;
            }
        }

        System.out.println("Error: Could not find device...");

    }
}