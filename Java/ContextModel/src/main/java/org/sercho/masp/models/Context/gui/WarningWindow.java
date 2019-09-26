package org.sercho.masp.models.Context.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WarningWindow extends JPanel implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = -1790323804086363157L;

    private final JLabel exceptionMsg;

    private final JButton acceptButton;

    private final VisualizerManager manager;

    public WarningWindow(final String warningMessage, final VisualizerManager manager) {
        this.manager = manager;
        final JPanel msgPanel = new JPanel();
        final JPanel buttonsPanel = new JPanel();
        msgPanel.setBackground(Color.white);
        buttonsPanel.setBackground(Color.white);
        // JTextArea detaislText = new JTextArea(20, 20);
        // detaislText.setEditable(false);
        // detaislText.append(" Details  | " + exception.getMessage());

        this.exceptionMsg = new JLabel(warningMessage);
        this.exceptionMsg.setIcon(Images.getImageIcon("warning_big"));
        this.acceptButton = new JButton("Accept");
        this.acceptButton.addActionListener(this);
        msgPanel.add(this.exceptionMsg);
        // detailsPanel.add(detaislText);
        buttonsPanel.add(this.acceptButton);
        this.setLayout(new GridLayout(2, 0));
        this.add(msgPanel);
        buttonsPanel.setBackground(new Color(98, 98, 98));
        msgPanel.setBackground(new Color(98, 98, 98));
        this.add(buttonsPanel);

    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if(e.getSource() == this.acceptButton) {
            this.manager.closeExceptionWindow();
        }

    }
}
