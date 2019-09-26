/**
 * File     LocalizationGUI.java
 * Package  org.sercho.masp.models
 * Project  ContextModel
 * Date     21.01.2008
 * Web      http://www.dai-labor.de
 * 
 * @author  Dirk Roscher
 */
package org.sercho.masp.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.sercho.masp.context.providers.location.Vector;

/**
 * <code>LocalizationGUI</code> can be utilized to configure the position of
 * preconfigured users in the context model via a graphical user interface.
 * 
 * @author Dirk Roscher
 */
public class LocalizationGUI extends JFrame {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -208838772382491620L;

    private javax.swing.ButtonGroup buttonGroup1;

    private javax.swing.JButton executeButton;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JRadioButton jRadioButton1;

    private javax.swing.JRadioButton jRadioButton2;

    private javax.swing.JComboBox positionCombo;

    private javax.swing.JComboBox usersCombo;

    private javax.swing.JTextField xText;

    private javax.swing.JTextField yText;

    private final Set<String> userIds;

    private static final Map<String, Vector> POSITIONS = new HashMap<String, Vector>();

    static {
        POSITIONS.put("KITCHEN PC", DummyLocalisationProvider.POS_KITCHEN_PC);
        POSITIONS.put("KITCHEN SMALL TOUCH", DummyLocalisationProvider.POS_SHEM_TOUCH);
        POSITIONS.put("LIVING ROOM SHARP 65", DummyLocalisationProvider.POS_LIVING_ROOM_SHARP_65);
        POSITIONS.put("LIVING ROOM DELL 30", DummyLocalisationProvider.POS_LIVING_ROOM_DELL_30);
        POSITIONS.put("LIVING ROOM TOUCH", DummyLocalisationProvider.POS_LIVING_ROOM_TOUCH);
        POSITIONS.put("WORKROOM", DummyLocalisationProvider.POS_WORKROOM);
    }

    public LocalizationGUI(final Set<String> myUserIds) {
        if(myUserIds == null) {
            throw new IllegalArgumentException("myUserIds is null");
        }
        this.userIds = myUserIds;
        initComponents();
    }

    private void initComponents() {

        this.buttonGroup1 = new javax.swing.ButtonGroup();
        this.jPanel1 = new javax.swing.JPanel();
        this.jRadioButton1 = new javax.swing.JRadioButton();
        this.jRadioButton2 = new javax.swing.JRadioButton();
        this.usersCombo = new javax.swing.JComboBox();
        this.positionCombo = new javax.swing.JComboBox();
        this.executeButton = new javax.swing.JButton();
        this.xText = new javax.swing.JTextField();
        this.yText = new javax.swing.JTextField();
        this.jLabel1 = new javax.swing.JLabel();
        this.jLabel2 = new javax.swing.JLabel();
        this.jLabel3 = new javax.swing.JLabel();
        this.jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(590, 225));
        setResizable(false);
        setTitle("Localization GUI");
        getContentPane().setLayout(null);

        this.jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Localization"));
        this.jPanel1.setLayout(null);

        this.buttonGroup1.add(this.jRadioButton1);
        this.jRadioButton1.setText("dynamic");
        this.jPanel1.add(this.jRadioButton1);
        this.jRadioButton1.setBounds(20, 80, 120, 23);

        this.buttonGroup1.add(this.jRadioButton2);
        this.jRadioButton2.setSelected(true);
        this.jRadioButton2.setText("static");
        this.jPanel1.add(this.jRadioButton2);
        this.jRadioButton2.setBounds(20, 40, 120, 23);

        this.usersCombo.setModel(new javax.swing.DefaultComboBoxModel(this.userIds.toArray()));
        this.jPanel1.add(this.usersCombo);
        this.usersCombo.setBounds(200, 40, 80, 20);

        this.positionCombo.setModel(new javax.swing.DefaultComboBoxModel(POSITIONS.keySet().toArray()));
        this.jPanel1.add(this.positionCombo);
        this.positionCombo.setBounds(370, 40, 160, 20);

        this.executeButton.setText("execute");
        this.executeButton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                executeButtonActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.executeButton);
        this.executeButton.setBounds(443, 140, 90, 23);

        this.xText.setText("1");
        this.jPanel1.add(this.xText);
        this.xText.setBounds(200, 90, 80, 20);

        this.yText.setText("1");
        this.jPanel1.add(this.yText);
        this.yText.setBounds(370, 90, 160, 20);

        this.jLabel1.setText("x");
        this.jPanel1.add(this.jLabel1);
        this.jLabel1.setBounds(160, 90, 30, 20);

        this.jLabel2.setText("y");
        this.jPanel1.add(this.jLabel2);
        this.jLabel2.setBounds(310, 90, 60, 20);

        this.jLabel3.setText("users");
        this.jPanel1.add(this.jLabel3);
        this.jLabel3.setBounds(160, 40, 40, 20);

        this.jLabel4.setText("positions");
        this.jPanel1.add(this.jLabel4);
        this.jLabel4.setBounds(310, 40, 60, 20);

        getContentPane().add(this.jPanel1);
        this.jPanel1.setBounds(5, 5, 570, 180);
    }

    /**
     * Execute button action performed.
     * 
     * @param evt
     *            the evt
     */
    private void executeButtonActionPerformed(final java.awt.event.ActionEvent evt) {
        if(this.jRadioButton2.isSelected()) {
            // System.out.println(".actionPerformed(): usersCombo=" +
            // usersCombo.getSelectedItem() + "; positionCombo=" +
            // positionCombo.getSelectedItem());
            DummyLocalisationProvider.setPos((String)this.usersCombo.getSelectedItem(), POSITIONS.get(this.positionCombo.getSelectedItem()));
        } else if(this.jRadioButton1.isSelected()) {
            try {
                DummyLocalisationProvider.setPos((String)this.usersCombo.getSelectedItem(), new Vector(Double.parseDouble(this.xText.getText()), Double.parseDouble(this.yText.getText()), 1.0));
            }
            catch(final NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Can not parse postition!", "Number format exception", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * <code>main</code>
     * 
     * @param args
     */
    public static void main(final String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                final Set<String> userIds = new HashSet<String>();
                userIds.add("Marco");
                new LocalizationGUI(userIds).setVisible(true);
            }
        });
    }

}
