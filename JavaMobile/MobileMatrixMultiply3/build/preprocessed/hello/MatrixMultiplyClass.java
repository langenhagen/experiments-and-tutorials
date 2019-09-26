package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.SimpleTableModel;
import org.netbeans.microedition.lcdui.TableItem;

public class MatrixMultiplyClass extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    private String text;
    private int[][] intMatrix;
    private SimpleTableModel tblModel;
    private int numOfCols;
    private int numOfLines;
    private int quadcntr;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command okCommand;
    private Form EnterMatrix;
    private TextField txtMatrix;
    private Form ShowMatrix;
    private TableItem table;
    private SimpleTableModel tableModel;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The MatrixMultiplyClass constructor.
     */
    public MatrixMultiplyClass() {
      quadcntr = 1;
   }

    /**
     * multyplyMatrix()
     * quadriert die intMatrix und gibt sie zurück.
     */
     int[][] multiplyMatrix(){
      System.out.println("Multiplying Matrix...");
      int[][] newMatrix = new int[numOfLines][numOfCols];

      quadcntr*=2;

      for(int i=0; i<numOfLines; i++)
        for(int j=0; j<numOfCols; j++)
          newMatrix[i][j]=0;


      for(int i=0; i<numOfLines; i++)   // Positionen in der Matrix!
        for(int j=0; j<numOfCols; j++)   // Positionen in der Matrix!
          for(int k=0; k<numOfLines; k++)   // MultPfade!
              newMatrix[i][j]+= intMatrix[i][k]*intMatrix[k][j];


      for(int i=0; i<numOfLines; i++){
          for( int j=0; j<numOfCols; j++)
            System.out.print("" + newMatrix[i][j] + "  ");
          System.out.print("\n");
        }
        return newMatrix;
      }

    /**
     * buildTable. Liefert dem Table der Matrixseite ein Model
     */
    SimpleTableModel makeTableModel(){
      SimpleTableModel model = new SimpleTableModel(numOfLines, numOfCols);
      for(int i=0; i<numOfLines; i++)
        for(int j=0; j<numOfCols; j++)
          model.setValue(j, i, ""+ intMatrix[i][j]);

      return model;
    }


    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
      switchDisplayable(null, getEnterMatrix());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
      Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
      if (alert == null) {
        display.setCurrent(nextDisplayable);
      } else {
        display.setCurrent(alert, nextDisplayable);
      }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
      if (displayable == EnterMatrix) {//GEN-BEGIN:|7-commandAction|1|19-preAction
        if (command == exitCommand) {//GEN-END:|7-commandAction|1|19-preAction
                // write pre-action user code here
          exitMIDlet();//GEN-LINE:|7-commandAction|2|19-postAction
                // write post-action user code here
        } else if (command == okCommand) {//GEN-LINE:|7-commandAction|3|23-preAction


          switchDisplayable(null, getShowMatrix());//GEN-LINE:|7-commandAction|4|23-postAction

          // write post-action user code here
        }//GEN-BEGIN:|7-commandAction|5|42-preAction
      } else if (displayable == ShowMatrix) {
        if (command == exitCommand) {//GEN-END:|7-commandAction|5|42-preAction
          // write pre-action user code here
          switchDisplayable(null, getEnterMatrix());//GEN-LINE:|7-commandAction|6|42-postAction
          // write post-action user code here
        } else if (command == okCommand) {//GEN-LINE:|7-commandAction|7|43-preAction

          intMatrix = multiplyMatrix();

          switchDisplayable(null, getShowMatrix());//GEN-LINE:|7-commandAction|8|43-postAction

          tblModel =  makeTableModel();
          switchDisplayable(null, null);
          getTable().setModel( tblModel);
                    getTable().setLabel("Matrix hoch " + quadcntr);
        }//GEN-BEGIN:|7-commandAction|9|7-postCommandAction
      }//GEN-END:|7-commandAction|9|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|10|
    //</editor-fold>//GEN-END:|7-commandAction|10|


    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
      if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
        exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
      }//GEN-BEGIN:|18-getter|2|
      return exitCommand;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: EnterMatrix ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of EnterMatrix component.
     * @return the initialized component instance
     */
    public Form getEnterMatrix() {
      if (EnterMatrix == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
        EnterMatrix = new Form("Enter Matrix", new Item[] { getTxtMatrix() });//GEN-BEGIN:|14-getter|1|14-postInit
        EnterMatrix.addCommand(getExitCommand());
        EnterMatrix.addCommand(getOkCommand());
        EnterMatrix.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here

      }//GEN-BEGIN:|14-getter|2|
      return EnterMatrix;
    }
    //</editor-fold>//GEN-END:|14-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
      if (okCommand == null) {//GEN-END:|22-getter|0|22-preInit
        // write pre-init user code here
        okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|22-getter|1|22-postInit
        // write post-init user code here
      }//GEN-BEGIN:|22-getter|2|
      return okCommand;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtMatrix ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initiliazed instance of txtMatrix component.
     * @return the initialized component instance
     */
    public TextField getTxtMatrix() {
      if (txtMatrix == null) {//GEN-END:|24-getter|0|24-preInit
        // write pre-init user code here
        txtMatrix = new TextField("Matrix", "#*", 256, TextField.ANY);//GEN-BEGIN:|24-getter|1|24-postInit
        txtMatrix.setLayout(ImageItem.LAYOUT_CENTER | Item.LAYOUT_TOP | Item.LAYOUT_VCENTER | Item.LAYOUT_SHRINK | Item.LAYOUT_VEXPAND);
        txtMatrix.setPreferredSize(-1, 200);//GEN-END:|24-getter|1|24-postInit
        // write post-init user code here
      }//GEN-BEGIN:|24-getter|2|
      return txtMatrix;
    }
    //</editor-fold>//GEN-END:|24-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ShowMatrix ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initiliazed instance of ShowMatrix component.
     * @return the initialized component instance
     */
    public Form getShowMatrix() {
      if (ShowMatrix == null) {//GEN-END:|25-getter|0|25-preInit
        // write pre-init user code here

text = txtMatrix.getString();
        // ------------------------------------------------
        // Anzahl der Lines -----------
        String txtline = text;
        numOfLines = 0;
        int i = txtline.indexOf('#');
        System.out.println(txtline);
        while( i != -1){
          txtline = txtline.substring(i+1); // Anfang entspricht einer Zeile!
          numOfLines++;
          i = txtline.indexOf('#');
        }
        System.out.println("l:"+ numOfLines);
        // txtline nun LETZTE Zeile ohne '#' !!
        // Anzahl der Cols
        String txtcol = txtline;
        numOfCols = 0;
        System.out.println(txtcol);
        int j = txtcol.indexOf("*");
        while( j !=-1){
          txtcol = txtcol.substring(j+1); // Anfang entspricht einer Zahl!!!
          numOfCols++;
          j = txtcol.indexOf('*');
        }
        // Anzahl der Lines und Cols korrekt.
        // ------------------------------------------------
        System.out.println("l:"+numOfLines+", c:"+numOfCols);
        // Zahlen getten und in Matrix eintragen!!

        intMatrix = new int[numOfLines][numOfCols];
        String t = text;
        for(int a=0; a<numOfLines; a++){
          t = t.substring(t.indexOf('#')+1);
          for(int b=0; b<numOfCols;b++){
            t = t.substring(t.indexOf('*')+1);
            if( t.indexOf('*') == -1)
              intMatrix[a][b] = Integer.valueOf(t).intValue();
            else if( t.indexOf('*') > t.indexOf('#') && t.indexOf('#') != -1)
              intMatrix[a][b] =
                Integer.valueOf(t.substring(0, t.indexOf('#'))).intValue();
            else
              intMatrix[a][b] =
                Integer.valueOf(t.substring(0, t.indexOf('*'))).intValue();
          }
        } // intMatrix initialisiert !

        ShowMatrix = new Form("Matrix", new Item[] { getTable() });//GEN-BEGIN:|25-getter|1|25-postInit
        ShowMatrix.addCommand(getExitCommand());
        ShowMatrix.addCommand(getOkCommand());
        ShowMatrix.setCommandListener(this);//GEN-END:|25-getter|1|25-postInit
        // write post-init user code here

          tblModel =  makeTableModel();
          getTable().setModel( tblModel);

      }//GEN-BEGIN:|25-getter|2|
      return ShowMatrix;
    }
    //</editor-fold>//GEN-END:|25-getter|2|





    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tableModel ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initiliazed instance of tableModel component.
     * @return the initialized component instance
     */
    public SimpleTableModel getTableModel() {
      if (tableModel == null) {//GEN-END:|32-getter|0|32-preInit
        // write pre-init user code here
        tableModel = new SimpleTableModel(new java.lang.String[][] {//GEN-BEGIN:|32-getter|1|32-postInit
          new java.lang.String[] { "" }}, null);//GEN-END:|32-getter|1|32-postInit
        // write post-init user code here
      }//GEN-BEGIN:|32-getter|2|
      return tableModel;
    }
    //</editor-fold>//GEN-END:|32-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: table ">//GEN-BEGIN:|37-getter|0|37-preInit
    /**
     * Returns an initiliazed instance of table component.
     * @return the initialized component instance
     */
    public TableItem getTable() {
      if (table == null) {//GEN-END:|37-getter|0|37-preInit
        // write pre-init user code here
        table = new TableItem(getDisplay(), "The Matrix you entered");//GEN-BEGIN:|37-getter|1|37-postInit
        table.setPreferredSize(200, 200);
        table.setLayout(ImageItem.LAYOUT_CENTER | Item.LAYOUT_TOP | Item.LAYOUT_VCENTER | ImageItem.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_EXPAND | Item.LAYOUT_VEXPAND);
        table.setModel(getTableModel());//GEN-END:|37-getter|1|37-postInit
        // write post-init user code here
      }//GEN-BEGIN:|37-getter|2|
      return table;
    }
    //</editor-fold>//GEN-END:|37-getter|2|









    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

}
