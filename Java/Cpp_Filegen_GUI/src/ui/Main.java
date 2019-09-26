/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


/**
 *
 * @author Barn
 */
public class Main {
    
    /**
     * @param args
	 * The command line arguments.<br>
	 * Usage:<br>
	 * You may use no, one or two arguments in order to start the application.<br>
	 * Argument one: The path of the config file to be used.<br>
	 * Argument two: The path of the log file to be used.
     */
    public static void main(String args[]) {
        
        String configFile = args.length > 0 ? args[0] : "Config.txt";
        String logFile = args.length > 1 ? args[1] : "FileGen.log";
        
        startUI( configFile, logFile);
    }
    
    // PRIVATE HELPERS ////////////////////////////////////////////////////////////////////////////
    
	
	/**
	 * Starts the ui.
	 * @param configFile
	 * The config file for the CppFileGenerator.
	 * @param logFile
	 * The log file for the CppFileGenerator.
	 */
    private static void startUI( final String configFile, final String logFile)
    {
        /* 
         * Set the Windows look and feel
         * If Windows Look & Feel is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | 
				InstantiationException | 
				IllegalAccessException | 
				javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIFrame( configFile, logFile).setVisible(true);
            }
        });
    }
    
}
