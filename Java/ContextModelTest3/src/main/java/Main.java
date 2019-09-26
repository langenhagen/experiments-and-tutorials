import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.sercho.masp.models.XMIUtility;
import org.sercho.masp.models.Context.Blind;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.CookTop;
import org.sercho.masp.models.Context.Cooker;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.Dishwasher;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.Fan;
import org.sercho.masp.models.Context.Fridge;
import org.sercho.masp.models.Context.Heater;
import org.sercho.masp.models.Context.HeatingRod;
import org.sercho.masp.models.Context.Hob;
import org.sercho.masp.models.Context.Hood;
import org.sercho.masp.models.Context.Lamp;
import org.sercho.masp.models.Context.Mixer;
import org.sercho.masp.models.Context.Notebook;
import org.sercho.masp.models.Context.Oven;
import org.sercho.masp.models.Context.PC;
import org.sercho.masp.models.Context.PhysicalDevice;
import org.sercho.masp.models.Context.Radio;
import org.sercho.masp.models.Context.RemoteControl;
import org.sercho.masp.models.Context.TV;
import org.sercho.masp.models.Context.WaterStorageTank;



public class Main {
	
	public static void main( String[] args ) throws IOException
    {     
        //final InputStream in = new FileInputStream("G:/Java/SmartHomeGUI/ContextModel_dummy.xmi");
        final InputStream in = new FileInputStream("G:/Java/SmartHomeGUI/ContextModel_1_4.xmi");
        final Environment environment = (Environment)(XMIUtility.convert(in, ContextPackage.eINSTANCE).get(0));
    
        System.out.println();
        System.out.println();
        
        for( Device d : environment.getDevices()){
        	if(!(d instanceof PhysicalDevice))
        		continue;
        	       	
        	processDevice( (PhysicalDevice)d, 0);

        	System.out.println();
        }
    }
    
   
    /**
     * 
     * @param d
     */
    private static void processDevice( PhysicalDevice d, int indentation){
    	
    	if( indentation != 0)
    		System.out.println("-------");

    	// manage indentation
    	String indent = "";
    	for( int i=0; i<indentation; i++){
    		indent = indent + "\t";
    	}
    	
    	// generic information
    	System.out.println( indent + "Device name:  " + d.getName());
    	System.out.println( indent + "Device class: " + d.getClass().getName());
    	System.out.println( indent + "Position:     " + d.getPosition());
    	System.out.println( indent + "On:           " + d.getOn());
    	System.out.println( indent + "OnValue:      " + d.getOnValue());
    	System.out.println( indent + "PowerUsage:   " + d.getPowerUsage());
    	System.out.println( indent + "ModelName:    " + d.getModelName());
    	System.out.println( indent + "Manufacturer: " + d.getManufacturer());
    	
    	// process concrete types
    	@SuppressWarnings("rawtypes")
		Class clazz = d.getClass();
    	if( Hood.class.isAssignableFrom( clazz)){        		       		
    		doHoodStuff(d, indent);
    	}else if( Notebook.class.isAssignableFrom( clazz)){
    		doNotebookStuff(d, indent);
    	}else if( Dishwasher.class.isAssignableFrom( clazz)){
    		doDishwasherStuff(d, indent);
    	}else if( TV.class.isAssignableFrom( clazz)){
    		doTVStuff(d, indent);
    	}else if( PC.class.isAssignableFrom( clazz)){
    		doPCStuff(d, indent);
    	}else if( RemoteControl.class.isAssignableFrom( clazz)){
    		doRemoteControlStuff(d, indent);
    	}else if( Oven.class.isAssignableFrom( clazz)){
    		doOvenStuff(d, indent);
    	}else if( Fan.class.isAssignableFrom( clazz)){
    		doFanStuff(d, indent);
    	}else if( Lamp.class.isAssignableFrom( clazz)){
    		doLampStuff(d, indent);
    	}else if( Blind.class.isAssignableFrom( clazz)){
    		doBlindStuff(d, indent);
    	}else if( Fridge.class.isAssignableFrom( clazz)){
    		doFridgeStuff(d, indent);
    	}else if( Hob.class.isAssignableFrom( clazz)){
    		doHobStuff(d, indent);
    	}else if( Cooker.class.isAssignableFrom( clazz)){
    		doCookerStuff(d, indent);
    	}else if( Heater.class.isAssignableFrom( clazz)){
    		doHeaterStuff(d, indent);
    	}else if( CookTop.class.isAssignableFrom( clazz)){
    		doCookTopStuff(d, indent);
    	}else if( Radio.class.isAssignableFrom( clazz)){
    		doRadioStuff(d, indent);
    	}else if( Mixer.class.isAssignableFrom( clazz)){
    		doMixerStuff(d, indent);
    	}else if( WaterStorageTank.class.isAssignableFrom( clazz)){
    		doWaterStorageTankStuff(d, indent);
    	}else if( HeatingRod.class.isAssignableFrom( clazz)){
    		doHeatingRodStuff(d, indent);	
    	}else{
    		System.err.println("ERROR: The device " + d + " is not a known physical device!");
    		return;
    	}
    	
    	for( PhysicalDevice child : d.getSubDevice()){
    		processDevice( child, indentation+1);
    	}
    	
    	if(indentation == 0)
    		System.out.println(
    				"-----------------------------------------------------------------------------------------------------" +
    				"-----------------------------------------------------------------------------------------------------");

    }
    
    // doXXXStuff /////////////////////////////////////////////////////////////////////////////////
    
    private static void doHoodStuff(Device device, String indent){
    	Hood d = (Hood)device;
    	//empty
    }
    
    private static void doNotebookStuff(Device device, String indent){
    	Notebook d = (Notebook)device;
    	//empty
    }

    private static void doDishwasherStuff(Device device, String indent){
    	Dishwasher d = (Dishwasher)device;
    	//empty
    }
    
    private static void doTVStuff(Device device, String indent){
    	TV d = (TV)device;
    	
    	System.out.println(indent + "CurrentProgram: " + d.getCurrentProgram());
    }
    
    private static void doRemoteControlStuff(Device device, String indent){
    	RemoteControl d = (RemoteControl)device;
    	//empty
    }
    
    private static void doPCStuff(Device device, String indent){
    	PC d = (PC)device;
    	//empty
    }
    
    private static void doOvenStuff(Device device, String indent){
    	Oven d = (Oven)device;
    	System.out.println(indent + "Program: " + d.getProgram() + "; Seconds remaining: " + d.getSecondsRemaining() + "; Temperature: " + d.getTemperature());
    }
    
    private static void doFanStuff(Device device, String indent){
    	Fan d = (Fan)device;
    	System.out.println(indent + "Speed: " + d.getSpeed());
    }
    
    private static void doLampStuff(Device device, String indent){
    	Lamp d = (Lamp)device;
    	System.out.println(indent + "DimmingLevel: " + d.getDimmingLevel());
    }
    
    private static void doBlindStuff(Device device, String indent){
    	Blind d = (Blind)device;
    	
    	System.out.println(indent + "Level: " + d.getLevel());
    }
    
    private static void doFridgeStuff(Device device, String indent){
    	Fridge d = (Fridge)device;   	
    	
    	System.out.println(indent + "Temperature: " + d.getTemperature());
    }
    
    private static void doHobStuff(Device device, String indent){
    	Hob d = (Hob)device;
    	
    	System.out.println(indent + "HeatLevel: " + d.getHeatLevel());
    }
    
    private static void doCookerStuff(Device device, String indent){
    	Cooker d = (Cooker)device;
    	//empty
    }
    
    private static void doHeaterStuff(Device device, String indent){
    	Heater d = (Heater)device;
    	//empty
    }
    
    private static void doCookTopStuff(Device device, String indent){
    	CookTop d = (CookTop)device;
    	//empty
    }
    
    private static void doRadioStuff(Device device, String indent){
    	Radio d = (Radio)device;
    	//empty
    }
    
    private static void doMixerStuff(Device device, String indent){
    	Mixer d = (Mixer)device;
    	//empty
    }
    
    private static void doWaterStorageTankStuff(Device device, String indent){
    	WaterStorageTank d = (WaterStorageTank)device;
    	System.out.println(indent + "Capacity: " + d.getCapacity() + "; Temperature Sensor: " + d.getTemperatureSensor());
    }
    
    private static void doHeatingRodStuff(Device device, String indent){
    	HeatingRod d = (HeatingRod)device;
    	System.out.println(indent + "Maximum power watts: " + d.getMaximumPowerWatts());
    }
    
}
