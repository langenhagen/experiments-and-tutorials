/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextPackage
 * @generated
 */
public interface ContextFactory extends EFactory {

    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    ContextFactory eINSTANCE = org.sercho.masp.models.Context.impl.ContextFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Environment</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Environment</em>'.
     * @generated
     */
    Environment createEnvironment();

    /**
     * Returns a new object of class '<em>User</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>User</em>'.
     * @generated
     */
    User createUser();

    /**
     * Returns a new object of class '<em>Activity</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Activity</em>'.
     * @generated
     */
    Activity createActivity();

    /**
     * Returns a new object of class '<em>Home OS Assistant</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Home OS Assistant</em>'.
     * @generated
     */
    HomeOSAssistant createHomeOSAssistant();

    /**
     * Returns a new object of class '<em>Third Party Assistant</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Third Party Assistant</em>'.
     * @generated
     */
    ThirdPartyAssistant createThirdPartyAssistant();

    /**
     * Returns a new object of class '<em>Localization Tag</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Localization Tag</em>'.
     * @generated
     */
    LocalizationTag createLocalizationTag();

    /**
     * Returns a new object of class '<em>Room</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Room</em>'.
     * @generated
     */
    Room createRoom();

    /**
     * Returns a new object of class '<em>Localisation Provider Proxy</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Localisation Provider Proxy</em>'.
     * @generated
     */
    LocalisationProviderProxy createLocalisationProviderProxy();

    /**
     * Returns a new object of class '<em>Mouse</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Mouse</em>'.
     * @generated
     */
    Mouse createMouse();

    /**
     * Returns a new object of class '<em>Keyboard</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Keyboard</em>'.
     * @generated
     */
    Keyboard createKeyboard();

    /**
     * Returns a new object of class '<em>Display</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Display</em>'.
     * @generated
     */
    Display createDisplay();

    /**
     * Returns a new object of class '<em>Gesture IR</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Gesture IR</em>'.
     * @generated
     */
    GestureIR createGestureIR();

    /**
     * Returns a new object of class '<em>Touchpad</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Touchpad</em>'.
     * @generated
     */
    Touchpad createTouchpad();

    /**
     * Returns a new object of class '<em>Device</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Device</em>'.
     * @generated
     */
    Device createDevice();

    /**
     * Returns a new object of class '<em>Message Input Channel</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Message Input Channel</em>'.
     * @generated
     */
    MessageInputChannel createMessageInputChannel();

    /**
     * Returns a new object of class '<em>Message Output Channel</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Message Output Channel</em>'.
     * @generated
     */
    MessageOutputChannel createMessageOutputChannel();

    /**
     * Returns a new object of class '<em>Pointing Input Channel</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Pointing Input Channel</em>'.
     * @generated
     */
    PointingInputChannel createPointingInputChannel();

    /**
     * Returns a new object of class '<em>Graphical Output Channel</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Graphical Output Channel</em>'.
     * @generated
     */
    GraphicalOutputChannel createGraphicalOutputChannel();

    /**
     * Returns a new object of class '<em>Loudspeaker</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Loudspeaker</em>'.
     * @generated
     */
    Loudspeaker createLoudspeaker();

    /**
     * Returns a new object of class '<em>Microphone</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Microphone</em>'.
     * @generated
     */
    Microphone createMicrophone();

    /**
     * Returns a new object of class '<em>Vector</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Vector</em>'.
     * @generated
     */
    Vector createVector();

    /**
     * Returns a new object of class '<em>Area</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Area</em>'.
     * @generated
     */
    Area createArea();

    /**
     * Returns a new object of class '<em>Configuration Property</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Configuration Property</em>'.
     * @generated
     */
    ConfigurationProperty createConfigurationProperty();

    /**
     * Returns a new object of class '<em>Heater</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Heater</em>'.
     * @generated
     */
    Heater createHeater();

    /**
     * Returns a new object of class '<em>Meter</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Meter</em>'.
     * @generated
     */
    Meter createMeter();

    /**
     * Returns a new object of class '<em>Water Storage Tank</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Water Storage Tank</em>'.
     * @generated
     */
    WaterStorageTank createWaterStorageTank();

    /**
     * Returns a new object of class '<em>Heating Rod</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Heating Rod</em>'.
     * @generated
     */
    HeatingRod createHeatingRod();

    /**
     * Returns a new object of class '<em>Meter Reading</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Meter Reading</em>'.
     * @generated
     */
    MeterReading createMeterReading();

    /**
     * Returns a new object of class '<em>Luminance Sensor</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Luminance Sensor</em>'.
     * @generated
     */
    LuminanceSensor createLuminanceSensor();

    /**
     * Returns a new object of class '<em>Temperature Sensor</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Temperature Sensor</em>'.
     * @generated
     */
    TemperatureSensor createTemperatureSensor();

    /**
     * Returns a new object of class '<em>Washing Machine</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Washing Machine</em>'.
     * @generated
     */
    WashingMachine createWashingMachine();

    /**
     * Returns a new object of class '<em>Assistant Connection</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Assistant Connection</em>'.
     * @generated
     */
    AssistantConnection createAssistantConnection();

    /**
     * Returns a new object of class '<em>Tool</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Tool</em>'.
     * @generated
     */
    Tool createTool();

    /**
     * Returns a new object of class '<em>Device Tool</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Device Tool</em>'.
     * @generated
     */
    DeviceTool createDeviceTool();

    /**
     * Returns a new object of class '<em>Door</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Door</em>'.
     * @generated
     */
    Door createDoor();

    /**
     * Returns a new object of class '<em>Window</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Window</em>'.
     * @generated
     */
    Window createWindow();

    /**
     * Returns a new object of class '<em>Outdoors</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Outdoors</em>'.
     * @generated
     */
    Outdoors createOutdoors();

    /**
     * Returns a new object of class '<em>Touchscreen</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Touchscreen</em>'.
     * @generated
     */
    Touchscreen createTouchscreen();

    /**
     * Returns a new object of class '<em>Lamp</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Lamp</em>'.
     * @generated
     */
    Lamp createLamp();

    /**
     * Returns a new object of class '<em>Notebook</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Notebook</em>'.
     * @generated
     */
    Notebook createNotebook();

    /**
     * Returns a new object of class '<em>Hood</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Hood</em>'.
     * @generated
     */
    Hood createHood();

    /**
     * Returns a new object of class '<em>PC</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return a new object of class '<em>PC</em>'.
     * @generated
     */
    PC createPC();

    /**
     * Returns a new object of class '<em>Fan</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Fan</em>'.
     * @generated
     */
    Fan createFan();

    /**
     * Returns a new object of class '<em>Remote Control</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Remote Control</em>'.
     * @generated
     */
    RemoteControl createRemoteControl();

    /**
     * Returns a new object of class '<em>TV</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return a new object of class '<em>TV</em>'.
     * @generated
     */
    TV createTV();

    /**
     * Returns a new object of class '<em>Oven</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Oven</em>'.
     * @generated
     */
    Oven createOven();

    /**
     * Returns a new object of class '<em>Dishwasher</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Dishwasher</em>'.
     * @generated
     */
    Dishwasher createDishwasher();

    /**
     * Returns a new object of class '<em>Blind</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Blind</em>'.
     * @generated
     */
    Blind createBlind();

    /**
     * Returns a new object of class '<em>Hob</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Hob</em>'.
     * @generated
     */
    Hob createHob();

    /**
     * Returns a new object of class '<em>Cooker</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Cooker</em>'.
     * @generated
     */
    Cooker createCooker();

    /**
     * Returns a new object of class '<em>Cook Top</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Cook Top</em>'.
     * @generated
     */
    CookTop createCookTop();

    /**
     * Returns a new object of class '<em>Fridge</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Fridge</em>'.
     * @generated
     */
    Fridge createFridge();

    /**
     * Returns a new object of class '<em>Mixer</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Mixer</em>'.
     * @generated
     */
    Mixer createMixer();

    /**
     * Returns a new object of class '<em>Radio</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Radio</em>'.
     * @generated
     */
    Radio createRadio();

    /**
     * Returns a new object of class '<em>Socket</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Socket</em>'.
     * @generated
     */
    Socket createSocket();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    ContextPackage getContextPackage();

} // ContextFactory
