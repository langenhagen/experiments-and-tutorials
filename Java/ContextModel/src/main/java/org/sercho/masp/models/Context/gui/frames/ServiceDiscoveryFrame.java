/**
 * 
 */
package org.sercho.masp.models.Context.gui.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.gui.ServiceDiscovererManager;
import org.sercho.masp.models.Context.gui.VisualizerManager;
import org.sercho.masp.models.Context.gui.actionListener.ServiceSelectedActionListener;
import org.sercho.masp.models.Context.gui.enums.ServiceType;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.ConfigurationPropertyTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.ServiceContainerTreeNode;
import org.sercho.masp.models.Context.gui.panels.EnvironmentTreePanel.ServiceTreeNode;

import de.dailab.masp.models.Properties.Actor;
import de.dailab.masp.models.Properties.Sensor;
import de.dailab.masp.models.Properties.Service;

/**
 * A {@link JFrame} to discover {@link Service}s via the
 * {@link ServiceDiscovererManager}.
 * 
 * @author Andre Schulz
 * @since 1.3.7
 */
public class ServiceDiscoveryFrame extends JFrame implements ActionListener {

    private static final String ADD_TEXT = "Add";

    private static final String CANCEL_TEXT = "Cancel";

    private static final String EXIT_TEXT = "Exit";

    /**
     * The size of the frame.
     */
    private static Dimension FRAME_SIZE = new Dimension(700, 700);

    /**
     * The title of the frame.
     */
    private static String FRAME_TITLE = "Service Discovery";

    private static final String OK_TEXT = "Ok";

    /**
     * The <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8131388275847552976L;

    private JButton cancelButton;

    private JLabel hint;

    private final boolean multipleServices;

    private JButton okButton;

    private final ServiceType serviceType;

    private JTree tree;

    private JScrollPane treeScrollPane;

    private final VisualizerManager visualizerManager;

    private static final transient Log LOG = LogFactory.getLog(ServiceDiscoveryFrame.class);

    private final ServiceSelectedActionListener serviceSelectedActionListener;

    public ServiceDiscoveryFrame(final VisualizerManager visualizerManager,
            final ServiceType serviceType, final boolean multipleServices,
            final ServiceSelectedActionListener selectedServiceActionListener) {
        super();

        if(LOG.isDebugEnabled()) {
            LOG.debug("starting ServiceDiscoveryFrame...");
        }

        // TODO check arguments

        this.visualizerManager = visualizerManager;
        this.serviceType = serviceType;
        this.multipleServices = multipleServices;
        this.serviceSelectedActionListener = selectedServiceActionListener;

        initComponents();
        initLayout();

        super.setVisible(true);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        if(LOG.isDebugEnabled()) {
            LOG.debug("ServiceDiscoveryFrame ready");
        }
    }

    /**
     * 
     */
    private void initComponents() {
        super.setTitle(FRAME_TITLE);

        // +++++ hint label +++++
        final StringBuilder sb = new StringBuilder("<html>");
        sb.append("Select ");

        switch(this.serviceType) {
            case ACTOR:
                sb.append("an Actor");
                break;
            case SENSOR:
                sb.append("a Sensor");
                break;
            case SERVICE:
                sb.append("a Service");
                break;
            case SERVICE_CONTAINER:
                sb.append("a Service Container");
                break;
            default:
                throw new IllegalArgumentException("unknown ServiceType " + this.serviceType);
        }

        sb.append(" and press '");

        if(this.multipleServices) {
            sb.append(ADD_TEXT);
            this.okButton = new JButton(ADD_TEXT);
        } else {
            sb.append(OK_TEXT);
            this.okButton = new JButton(OK_TEXT);
        }
        sb.append("' to add it!");
        sb.append("</html>");

        // TODO add SrviceContainer or Property to hint

        this.hint = new JLabel(sb.toString());
        // ----- hint label -----

        // +++++ buttons +++++
        if(this.multipleServices) {
            this.okButton = new JButton(ADD_TEXT);
            this.cancelButton = new JButton(EXIT_TEXT);
        } else {
            this.okButton = new JButton(OK_TEXT);
            this.cancelButton = new JButton(CANCEL_TEXT);
        }

        this.okButton.addActionListener(this);
        this.okButton.setEnabled(false);

        this.cancelButton.addActionListener(this);
        // ----- buttons -----

        initTree();

        this.treeScrollPane = new JScrollPane(this.tree);
    }

    /**
     * 
     */
    private void initLayout() {
        super.setLayout(new BorderLayout());
        super.setSize(FRAME_SIZE);

        super.add(this.hint, BorderLayout.NORTH);
        super.add(this.treeScrollPane, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel();
        // buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(this.cancelButton);// , BorderLayout.WEST);
        buttonPanel.add(this.okButton);// , BorderLayout.EAST);
        super.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * 
     */
    private void initTree() {
        final ServiceDiscovererManager serviceDiscovererManager = this.visualizerManager.getServiceDiscovererManager();

        final EnvironmentTreePanel environmentTreePanel = new EnvironmentTreePanel(serviceDiscovererManager.getAllDiscoveredServices(), this.visualizerManager, this.multipleServices);

        this.tree = environmentTreePanel.getEnvironmentTree();

        this.tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(final TreeSelectionEvent e) {
                if(LOG.isDebugEnabled()) {
                    LOG.debug("valueChanged " + e);
                    LOG.debug(ServiceDiscoveryFrame.this.tree);
                    LOG.debug(ServiceDiscoveryFrame.this.tree.getLastSelectedPathComponent());
                    LOG.debug(Arrays.toString(ServiceDiscoveryFrame.this.tree.getSelectionPaths()));
                    if(ServiceDiscoveryFrame.this.tree.getLastSelectedPathComponent() != null) {
                        LOG.debug(ServiceDiscoveryFrame.this.serviceType + " " + ServiceDiscoveryFrame.this.tree.getLastSelectedPathComponent().getClass());
                    }
                }

                final Object node = ServiceDiscoveryFrame.this.tree.getLastSelectedPathComponent();

                if(node != null) {
                    boolean nodeValid = false;

                    switch(ServiceDiscoveryFrame.this.serviceType) {
                        case ACTOR:
                        case SENSOR:
                            if(node instanceof ServiceTreeNode) {
                                if(!(node instanceof ConfigurationPropertyTreeNode)) {
                                    final ServiceTreeNode serviceTreeNode = (ServiceTreeNode)node;

                                    switch(ServiceDiscoveryFrame.this.serviceType) {
                                        case ACTOR:
                                            if(serviceTreeNode.getService() instanceof Actor) {
                                                nodeValid = true;
                                            }
                                        case SENSOR:
                                            if(serviceTreeNode.getService() instanceof Sensor) {
                                                nodeValid = true;
                                            }
                                    }
                                }
                            }

                            break;
                        case SERVICE:
                            if(node instanceof ServiceContainerTreeNode) {
                                if(!(node instanceof ConfigurationPropertyTreeNode)) {
                                    nodeValid = true;
                                }
                            }

                            break;
                        case SERVICE_CONTAINER:
                            if(node instanceof ServiceContainerTreeNode) {
                                if(!(node instanceof ServiceTreeNode)) {
                                    nodeValid = true;
                                }
                            }

                            break;
                        default:
                            nodeValid = false;
                            break;
                    }// switch

                    ServiceDiscoveryFrame.this.okButton.setEnabled(nodeValid);
                }
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if(e.getSource() == this.cancelButton) {
            super.setVisible(false);
            super.dispose();
        } else if(e.getSource() == this.okButton) {
            if(this.okButton.isEnabled()) {
                for(final Service service : getSelectedServices()) {
                    this.serviceSelectedActionListener.finishAction(service);
                }
            } else {
                LOG.warn("invalid action source: button is disabled");
            }
        } else {
            LOG.warn("unknown action source on action event " + e);
        }
    }

    /**
     * Returns a {@link Collection} of selected {@link Service}s.
     * 
     * @return A {@link Collection} of selected {@link Service}s.
     */
    private Collection<Service> getSelectedServices() {
        if(LOG.isDebugEnabled()) {
            LOG.debug("selected paths: " + Arrays.toString(this.tree.getSelectionPaths()));
        }

        final LinkedList<Service> services = new LinkedList<Service>();

        Object node;

        for(final TreePath treePath : this.tree.getSelectionPaths()) {
            node = treePath.getLastPathComponent();

            if(node != null) {
                if(node instanceof ServiceTreeNode) {
                    services.add(((ServiceTreeNode)node).getService());
                } else if(node instanceof ServiceContainerTreeNode) {
                    services.add(((ServiceContainerTreeNode)node).getServiceContainer());
                } else {
                    throw new IllegalStateException("Last selected path component is of invalid node type " + node.getClass());
                }
            }
        }
        return services;
    }
}
