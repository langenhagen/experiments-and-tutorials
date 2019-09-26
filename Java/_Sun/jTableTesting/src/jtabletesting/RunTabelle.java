/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtabletesting;

/*
 * RunTabelle.java requires no other files.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;

public class RunTabelle extends JPanel {

    public RunTabelle( char[][] arr) {
        super(new GridLayout(1,0));

        int tWidth = arr.length;

        String[] colNames = new String[tWidth];
        for(int i=0; i< tWidth; i++){
          colNames[i] = "Partition #" + i;
        }

        // Zellen Füllen
        Object[][] cells = new Object[tWidth][arr[0].length];
        for(int i=0; i< tWidth;i++)
          for( int j=0; j<arr[i].length; j++){
            cells[i][j] = new Character( arr[j][i]).toString();
          }

        final JTable table = new JTable(cells, colNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 400));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.

      char[][] arr = { {'b', 'c', 'd'}, { 'e', 'd', 'c'}, {'k', 'y'}};

      JFrame frame = new JFrame("Run returns");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      RunTabelle runTable = new RunTabelle( arr);
      runTable.setOpaque(true); //content panes must be opaque
      frame.setContentPane(runTable);

      frame.pack();
      frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
