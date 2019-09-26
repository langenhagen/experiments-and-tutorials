/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.sercho.masp.context.providers.location.LocalisationProvider;
import org.sercho.masp.models.Context.*;
import org.sercho.masp.models.Context.Activity;
import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.AssistantConnection;
import org.sercho.masp.models.Context.Blind;
import org.sercho.masp.models.Context.ConfigurationProperty;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.CookTop;
import org.sercho.masp.models.Context.Cooker;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.DeviceTool;
import org.sercho.masp.models.Context.Dishwasher;
import org.sercho.masp.models.Context.Display;
import org.sercho.masp.models.Context.DistributionState;
import org.sercho.masp.models.Context.Domain;
import org.sercho.masp.models.Context.Door;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.Fan;
import org.sercho.masp.models.Context.Fridge;
import org.sercho.masp.models.Context.GestureIR;
import org.sercho.masp.models.Context.GraphicalOutputChannel;
import org.sercho.masp.models.Context.Heater;
import org.sercho.masp.models.Context.HeatingRod;
import org.sercho.masp.models.Context.Hob;
import org.sercho.masp.models.Context.HomeOSAssistant;
import org.sercho.masp.models.Context.Hood;
import org.sercho.masp.models.Context.Keyboard;
import org.sercho.masp.models.Context.Lamp;
import org.sercho.masp.models.Context.LampType;
import org.sercho.masp.models.Context.LocalisationProviderProxy;
import org.sercho.masp.models.Context.LocalizationTag;
import org.sercho.masp.models.Context.Loudspeaker;
import org.sercho.masp.models.Context.LuminanceSensor;
import org.sercho.masp.models.Context.MessageInputChannel;
import org.sercho.masp.models.Context.MessageOutputChannel;
import org.sercho.masp.models.Context.Meter;
import org.sercho.masp.models.Context.MeterReading;
import org.sercho.masp.models.Context.Microphone;
import org.sercho.masp.models.Context.Mixer;
import org.sercho.masp.models.Context.Modality;
import org.sercho.masp.models.Context.Mouse;
import org.sercho.masp.models.Context.Notebook;
import org.sercho.masp.models.Context.Outdoors;
import org.sercho.masp.models.Context.Oven;
import org.sercho.masp.models.Context.PC;
import org.sercho.masp.models.Context.PlaceInfo;
import org.sercho.masp.models.Context.PointingInputChannel;
import org.sercho.masp.models.Context.Radio;
import org.sercho.masp.models.Context.ReadingMeasurand;
import org.sercho.masp.models.Context.ReadingMeasurementMethod;
import org.sercho.masp.models.Context.ReadingRate;
import org.sercho.masp.models.Context.ReadingSubject;
import org.sercho.masp.models.Context.RemoteControl;
import org.sercho.masp.models.Context.Room;
import org.sercho.masp.models.Context.RoomType;
import org.sercho.masp.models.Context.Socket;
import org.sercho.masp.models.Context.TV;
import org.sercho.masp.models.Context.TemperatureSensor;
import org.sercho.masp.models.Context.ThirdPartyAssistant;
import org.sercho.masp.models.Context.Tool;
import org.sercho.masp.models.Context.Touchpad;
import org.sercho.masp.models.Context.Touchscreen;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.Vector;
import org.sercho.masp.models.Context.WashingMachine;
import org.sercho.masp.models.Context.WaterStorageTank;
import org.sercho.masp.models.Context.Window;
import org.sercho.masp.models.channel.api.ChannelAPI;
import org.sercho.masp.models.channel.api.GraphicalOutputChannelAPI;
import org.sercho.masp.models.channel.api.MessageInputChannelAPI;
import org.sercho.masp.models.channel.api.MessageOutputChannelAPI;
import org.sercho.masp.models.channel.api.PointingInputChannelAPI;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ContextFactoryImpl extends EFactoryImpl implements ContextFactory {

    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static ContextFactory init() {
        try {
            ContextFactory theContextFactory = (ContextFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.dai-labor.de/~masp/Context-1.3.ecore");
            if(theContextFactory != null) {
                return theContextFactory;
            }
        }
        catch(Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ContextFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ContextFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch(eClass.getClassifierID()) {
            case ContextPackage.ENVIRONMENT:
                return createEnvironment();
            case ContextPackage.USER:
                return createUser();
            case ContextPackage.ACTIVITY:
                return createActivity();
            case ContextPackage.HOME_OS_ASSISTANT:
                return createHomeOSAssistant();
            case ContextPackage.THIRD_PARTY_ASSISTANT:
                return createThirdPartyAssistant();
            case ContextPackage.LOCALIZATION_TAG:
                return createLocalizationTag();
            case ContextPackage.ROOM:
                return createRoom();
            case ContextPackage.OUTDOORS:
                return createOutdoors();
            case ContextPackage.DOOR:
                return createDoor();
            case ContextPackage.WINDOW:
                return createWindow();
            case ContextPackage.LOCALISATION_PROVIDER_PROXY:
                return createLocalisationProviderProxy();
            case ContextPackage.MOUSE:
                return createMouse();
            case ContextPackage.KEYBOARD:
                return createKeyboard();
            case ContextPackage.DISPLAY:
                return createDisplay();
            case ContextPackage.GESTURE_IR:
                return createGestureIR();
            case ContextPackage.TOUCHPAD:
                return createTouchpad();
            case ContextPackage.TOUCHSCREEN:
                return createTouchscreen();
            case ContextPackage.DEVICE:
                return createDevice();
            case ContextPackage.LAMP:
                return createLamp();
            case ContextPackage.NOTEBOOK:
                return createNotebook();
            case ContextPackage.HOOD:
                return createHood();
            case ContextPackage.PC:
                return createPC();
            case ContextPackage.FAN:
                return createFan();
            case ContextPackage.REMOTE_CONTROL:
                return createRemoteControl();
            case ContextPackage.TV:
                return createTV();
            case ContextPackage.OVEN:
                return createOven();
            case ContextPackage.DISHWASHER:
                return createDishwasher();
            case ContextPackage.WASHING_MACHINE:
                return createWashingMachine();
            case ContextPackage.BLIND:
                return createBlind();
            case ContextPackage.HOB:
                return createHob();
            case ContextPackage.COOKER:
                return createCooker();
            case ContextPackage.COOK_TOP:
                return createCookTop();
            case ContextPackage.FRIDGE:
                return createFridge();
            case ContextPackage.MIXER:
                return createMixer();
            case ContextPackage.RADIO:
                return createRadio();
            case ContextPackage.SOCKET:
                return createSocket();
            case ContextPackage.HEATER:
                return createHeater();
            case ContextPackage.METER:
                return createMeter();
            case ContextPackage.WATER_STORAGE_TANK:
                return createWaterStorageTank();
            case ContextPackage.HEATING_ROD:
                return createHeatingRod();
            case ContextPackage.METER_READING:
                return createMeterReading();
            case ContextPackage.LUMINANCE_SENSOR:
                return createLuminanceSensor();
            case ContextPackage.TEMPERATURE_SENSOR:
                return createTemperatureSensor();
            case ContextPackage.MESSAGE_INPUT_CHANNEL:
                return createMessageInputChannel();
            case ContextPackage.MESSAGE_OUTPUT_CHANNEL:
                return createMessageOutputChannel();
            case ContextPackage.POINTING_INPUT_CHANNEL:
                return createPointingInputChannel();
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL:
                return createGraphicalOutputChannel();
            case ContextPackage.LOUDSPEAKER:
                return createLoudspeaker();
            case ContextPackage.MICROPHONE:
                return createMicrophone();
            case ContextPackage.VECTOR:
                return createVector();
            case ContextPackage.AREA:
                return createArea();
            case ContextPackage.CONFIGURATION_PROPERTY:
                return createConfigurationProperty();
            case ContextPackage.ASSISTANT_CONNECTION:
                return createAssistantConnection();
            case ContextPackage.TOOL:
                return createTool();
            case ContextPackage.DEVICE_TOOL:
                return createDeviceTool();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch(eDataType.getClassifierID()) {
            case ContextPackage.ROOM_TYPE:
                return createRoomTypeFromString(eDataType, initialValue);
            case ContextPackage.READING_SUBJECT:
                return createReadingSubjectFromString(eDataType, initialValue);
            case ContextPackage.READING_MEASURAND:
                return createReadingMeasurandFromString(eDataType, initialValue);
            case ContextPackage.READING_MEASUREMENT_METHOD:
                return createReadingMeasurementMethodFromString(eDataType, initialValue);
            case ContextPackage.READING_RATE:
                return createReadingRateFromString(eDataType, initialValue);
            case ContextPackage.DISTRIBUTION_STATE:
                return createDistributionStateFromString(eDataType, initialValue);
            case ContextPackage.MODALITY:
                return createModalityFromString(eDataType, initialValue);
            case ContextPackage.DOMAIN:
                return createDomainFromString(eDataType, initialValue);
            case ContextPackage.LAMP_TYPE:
                return createLampTypeFromString(eDataType, initialValue);
            case ContextPackage.CHANNEL_API:
                return createChannelAPIFromString(eDataType, initialValue);
            case ContextPackage.MESSAGE_INPUT_CHANNEL_API:
                return createMessageInputChannelAPIFromString(eDataType, initialValue);
            case ContextPackage.POINTING_INPUT_CHANNEL_API:
                return createPointingInputChannelAPIFromString(eDataType, initialValue);
            case ContextPackage.MESSAGE_OUTPUT_CHANNEL_API:
                return createMessageOutputChannelAPIFromString(eDataType, initialValue);
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL_API:
                return createGraphicalOutputChannelAPIFromString(eDataType, initialValue);
            case ContextPackage.LOCALISATION_PROVIDER:
                return createLocalisationProviderFromString(eDataType, initialValue);
            case ContextPackage.PLACE_INFO:
                return createPlaceInfoFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch(eDataType.getClassifierID()) {
            case ContextPackage.ROOM_TYPE:
                return convertRoomTypeToString(eDataType, instanceValue);
            case ContextPackage.READING_SUBJECT:
                return convertReadingSubjectToString(eDataType, instanceValue);
            case ContextPackage.READING_MEASURAND:
                return convertReadingMeasurandToString(eDataType, instanceValue);
            case ContextPackage.READING_MEASUREMENT_METHOD:
                return convertReadingMeasurementMethodToString(eDataType, instanceValue);
            case ContextPackage.READING_RATE:
                return convertReadingRateToString(eDataType, instanceValue);
            case ContextPackage.DISTRIBUTION_STATE:
                return convertDistributionStateToString(eDataType, instanceValue);
            case ContextPackage.MODALITY:
                return convertModalityToString(eDataType, instanceValue);
            case ContextPackage.DOMAIN:
                return convertDomainToString(eDataType, instanceValue);
            case ContextPackage.LAMP_TYPE:
                return convertLampTypeToString(eDataType, instanceValue);
            case ContextPackage.CHANNEL_API:
                return convertChannelAPIToString(eDataType, instanceValue);
            case ContextPackage.MESSAGE_INPUT_CHANNEL_API:
                return convertMessageInputChannelAPIToString(eDataType, instanceValue);
            case ContextPackage.POINTING_INPUT_CHANNEL_API:
                return convertPointingInputChannelAPIToString(eDataType, instanceValue);
            case ContextPackage.MESSAGE_OUTPUT_CHANNEL_API:
                return convertMessageOutputChannelAPIToString(eDataType, instanceValue);
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL_API:
                return convertGraphicalOutputChannelAPIToString(eDataType, instanceValue);
            case ContextPackage.LOCALISATION_PROVIDER:
                return convertLocalisationProviderToString(eDataType, instanceValue);
            case ContextPackage.PLACE_INFO:
                return convertPlaceInfoToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Environment createEnvironment() {
        EnvironmentImpl environment = new EnvironmentImpl();
        return environment;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public User createUser() {
        UserImpl user = new UserImpl();
        return user;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Activity createActivity() {
        ActivityImpl activity = new ActivityImpl();
        return activity;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public HomeOSAssistant createHomeOSAssistant() {
        HomeOSAssistantImpl homeOSAssistant = new HomeOSAssistantImpl();
        return homeOSAssistant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ThirdPartyAssistant createThirdPartyAssistant() {
        ThirdPartyAssistantImpl thirdPartyAssistant = new ThirdPartyAssistantImpl();
        return thirdPartyAssistant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LocalizationTag createLocalizationTag() {
        LocalizationTagImpl localizationTag = new LocalizationTagImpl();
        return localizationTag;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Room createRoom() {
        RoomImpl room = new RoomImpl();
        return room;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Outdoors createOutdoors() {
        OutdoorsImpl outdoors = new OutdoorsImpl();
        return outdoors;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Door createDoor() {
        DoorImpl door = new DoorImpl();
        return door;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Window createWindow() {
        WindowImpl window = new WindowImpl();
        return window;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LocalisationProviderProxy createLocalisationProviderProxy() {
        LocalisationProviderProxyImpl localisationProviderProxy = new LocalisationProviderProxyImpl();
        return localisationProviderProxy;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Mouse createMouse() {
        MouseImpl mouse = new MouseImpl();
        return mouse;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Keyboard createKeyboard() {
        KeyboardImpl keyboard = new KeyboardImpl();
        return keyboard;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Display createDisplay() {
        DisplayImpl display = new DisplayImpl();
        return display;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public GestureIR createGestureIR() {
        GestureIRImpl gestureIR = new GestureIRImpl();
        return gestureIR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Touchpad createTouchpad() {
        TouchpadImpl touchpad = new TouchpadImpl();
        return touchpad;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Device createDevice() {
        DeviceImpl device = new DeviceImpl();
        return device;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MessageInputChannel createMessageInputChannel() {
        MessageInputChannelImpl messageInputChannel = new MessageInputChannelImpl();
        return messageInputChannel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MessageOutputChannel createMessageOutputChannel() {
        MessageOutputChannelImpl messageOutputChannel = new MessageOutputChannelImpl();
        return messageOutputChannel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PointingInputChannel createPointingInputChannel() {
        PointingInputChannelImpl pointingInputChannel = new PointingInputChannelImpl();
        return pointingInputChannel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public GraphicalOutputChannel createGraphicalOutputChannel() {
        GraphicalOutputChannelImpl graphicalOutputChannel = new GraphicalOutputChannelImpl();
        return graphicalOutputChannel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Loudspeaker createLoudspeaker() {
        LoudspeakerImpl loudspeaker = new LoudspeakerImpl();
        return loudspeaker;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Microphone createMicrophone() {
        MicrophoneImpl microphone = new MicrophoneImpl();
        return microphone;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Vector createVector() {
        VectorImpl vector = new VectorImpl();
        return vector;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Area createArea() {
        AreaImpl area = new AreaImpl();
        return area;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ConfigurationProperty createConfigurationProperty() {
        ConfigurationPropertyImpl configurationProperty = new ConfigurationPropertyImpl();
        return configurationProperty;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Heater createHeater() {
        HeaterImpl heater = new HeaterImpl();
        return heater;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Meter createMeter() {
        MeterImpl meter = new MeterImpl();
        return meter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public WaterStorageTank createWaterStorageTank() {
        WaterStorageTankImpl waterStorageTank = new WaterStorageTankImpl();
        return waterStorageTank;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public HeatingRod createHeatingRod() {
        HeatingRodImpl heatingRod = new HeatingRodImpl();
        return heatingRod;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MeterReading createMeterReading() {
        MeterReadingImpl meterReading = new MeterReadingImpl();
        return meterReading;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LuminanceSensor createLuminanceSensor() {
        LuminanceSensorImpl luminanceSensor = new LuminanceSensorImpl();
        return luminanceSensor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TemperatureSensor createTemperatureSensor() {
        TemperatureSensorImpl temperatureSensor = new TemperatureSensorImpl();
        return temperatureSensor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public WashingMachine createWashingMachine() {
        WashingMachineImpl washingMachine = new WashingMachineImpl();
        return washingMachine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AssistantConnection createAssistantConnection() {
        AssistantConnectionImpl assistantConnection = new AssistantConnectionImpl();
        return assistantConnection;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Tool createTool() {
        ToolImpl tool = new ToolImpl();
        return tool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DeviceTool createDeviceTool() {
        DeviceToolImpl deviceTool = new DeviceToolImpl();
        return deviceTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Touchscreen createTouchscreen() {
        TouchscreenImpl touchscreen = new TouchscreenImpl();
        return touchscreen;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Lamp createLamp() {
        LampImpl lamp = new LampImpl();
        return lamp;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Notebook createNotebook() {
        NotebookImpl notebook = new NotebookImpl();
        return notebook;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Hood createHood() {
        HoodImpl hood = new HoodImpl();
        return hood;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PC createPC() {
        PCImpl pc = new PCImpl();
        return pc;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Fan createFan() {
        FanImpl fan = new FanImpl();
        return fan;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public RemoteControl createRemoteControl() {
        RemoteControlImpl remoteControl = new RemoteControlImpl();
        return remoteControl;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TV createTV() {
        TVImpl tv = new TVImpl();
        return tv;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Oven createOven() {
        OvenImpl oven = new OvenImpl();
        return oven;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Dishwasher createDishwasher() {
        DishwasherImpl dishwasher = new DishwasherImpl();
        return dishwasher;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Blind createBlind() {
        BlindImpl blind = new BlindImpl();
        return blind;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Hob createHob() {
        HobImpl hob = new HobImpl();
        return hob;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Cooker createCooker() {
        CookerImpl cooker = new CookerImpl();
        return cooker;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public CookTop createCookTop() {
        CookTopImpl cookTop = new CookTopImpl();
        return cookTop;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Fridge createFridge() {
        FridgeImpl fridge = new FridgeImpl();
        return fridge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Mixer createMixer() {
        MixerImpl mixer = new MixerImpl();
        return mixer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Radio createRadio() {
        RadioImpl radio = new RadioImpl();
        return radio;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Socket createSocket() {
        SocketImpl socket = new SocketImpl();
        return socket;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DistributionState createDistributionStateFromString(EDataType eDataType, String initialValue) {
        DistributionState result = DistributionState.get(initialValue);
        if(result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertDistributionStateToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Modality createModalityFromString(EDataType eDataType, String initialValue) {
        Modality result = Modality.get(initialValue);
        if(result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertModalityToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Domain createDomainFromString(EDataType eDataType, String initialValue) {
        Domain result = Domain.get(initialValue);
        if(result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertDomainToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LampType createLampTypeFromString(EDataType eDataType, String initialValue) {
        LampType result = LampType.get(initialValue);
        if(result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertLampTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RoomType createRoomTypeFromString(EDataType eDataType, String initialValue) {
        RoomType result = RoomType.get(initialValue);
        if(result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertRoomTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ReadingSubject createReadingSubjectFromString(EDataType eDataType, String initialValue) {
        ReadingSubject result = ReadingSubject.get(initialValue);
        if(result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertReadingSubjectToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ReadingMeasurand createReadingMeasurandFromString(EDataType eDataType, String initialValue) {
        ReadingMeasurand result = ReadingMeasurand.get(initialValue);
        if(result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertReadingMeasurandToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ReadingMeasurementMethod createReadingMeasurementMethodFromString(EDataType eDataType, String initialValue) {
        ReadingMeasurementMethod result = ReadingMeasurementMethod.get(initialValue);
        if(result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertReadingMeasurementMethodToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ReadingRate createReadingRateFromString(EDataType eDataType, String initialValue) {
        ReadingRate result = ReadingRate.get(initialValue);
        if(result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertReadingRateToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ChannelAPI createChannelAPIFromString(EDataType eDataType, String initialValue) {
        return (ChannelAPI)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertChannelAPIToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MessageInputChannelAPI createMessageInputChannelAPIFromString(EDataType eDataType, String initialValue) {
        return (MessageInputChannelAPI)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertMessageInputChannelAPIToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PointingInputChannelAPI createPointingInputChannelAPIFromString(EDataType eDataType, String initialValue) {
        return (PointingInputChannelAPI)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertPointingInputChannelAPIToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MessageOutputChannelAPI createMessageOutputChannelAPIFromString(EDataType eDataType, String initialValue) {
        return (MessageOutputChannelAPI)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertMessageOutputChannelAPIToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public GraphicalOutputChannelAPI createGraphicalOutputChannelAPIFromString(EDataType eDataType, String initialValue) {
        return (GraphicalOutputChannelAPI)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertGraphicalOutputChannelAPIToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LocalisationProvider createLocalisationProviderFromString(EDataType eDataType, String initialValue) {
        return (LocalisationProvider)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertLocalisationProviderToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PlaceInfo createPlaceInfoFromString(EDataType eDataType, String initialValue) {
        return (PlaceInfo)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertPlaceInfoToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ContextPackage getContextPackage() {
        return (ContextPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ContextPackage getPackage() {
        return ContextPackage.eINSTANCE;
    }

} // ContextFactoryImpl
