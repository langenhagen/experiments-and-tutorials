package org.sercho.masp.models.Context.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class ExceptionWindow extends JPanel implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = -1737220137852531899L;

    private static final String WARN_ICON = "exception_big";

    private static final String INFO_ICON = "warning_big";

    private static final Color BACKGROUND_COLOR = new Color(98, 98, 98);

    private final JLabel exceptionMsg;

    private final JButton acceptButton;

    private final VisualizerManager manager;

    public ExceptionWindow(final Exception exception, final VisualizerManager manager,
            final int level) {
        this.manager = manager;
        final JPanel msgPanel = new JPanel();
        final JPanel buttonsPanel = new JPanel();

        msgPanel.setBackground(BACKGROUND_COLOR);
        buttonsPanel.setBackground(BACKGROUND_COLOR);

        this.exceptionMsg = new JLabel(exception.getMessage());
        final FontMetrics fm = this.exceptionMsg.getFontMetrics(this.exceptionMsg.getFont());
        msgPanel.setPreferredSize(new Dimension(fm.stringWidth(exception.getMessage()) + 60, 80));

        if(level == VisualizerManager.WARN) {
            this.exceptionMsg.setIcon(Images.getImageIcon(WARN_ICON));
        } else if(level == VisualizerManager.INFO) {
            this.exceptionMsg.setIcon(Images.getImageIcon(INFO_ICON));
        }

        this.acceptButton = new JButton("Accept");
        this.acceptButton.addActionListener(this);

        msgPanel.add(this.exceptionMsg);
        buttonsPanel.add(this.acceptButton);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(msgPanel);
        this.add(buttonsPanel);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if(e.getSource() == this.acceptButton) {
            this.manager.closeExceptionWindow();
        }

    }
}
