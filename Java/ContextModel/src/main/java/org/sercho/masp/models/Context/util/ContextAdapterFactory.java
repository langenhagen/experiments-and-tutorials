/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.sercho.masp.models.Context.*;
import org.sercho.masp.models.Context.Activity;
import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.Assistant;
import org.sercho.masp.models.Context.AssistantConnection;
import org.sercho.masp.models.Context.Blind;
import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.Context.ConfigurationProperty;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.ContextProvider;
import org.sercho.masp.models.Context.CookTop;
import org.sercho.masp.models.Context.Cooker;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.DeviceTool;
import org.sercho.masp.models.Context.Dishwasher;
import org.sercho.masp.models.Context.Display;
import org.sercho.masp.models.Context.Door;
import org.sercho.masp.models.Context.ElementWithPosition;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.EnvironmentElement;
import org.sercho.masp.models.Context.Fan;
import org.sercho.masp.models.Context.Fridge;
import org.sercho.masp.models.Context.GestureIR;
import org.sercho.masp.models.Context.GraphicalOutputChannel;
import org.sercho.masp.models.Context.HapticalInteractionResources;
import org.sercho.masp.models.Context.Heater;
import org.sercho.masp.models.Context.HeatingRod;
import org.sercho.masp.models.Context.Hob;
import org.sercho.masp.models.Context.HomeOSAssistant;
import org.sercho.masp.models.Context.Hood;
import org.sercho.masp.models.Context.InputInteractionResource;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.Keyboard;
import org.sercho.masp.models.Context.Lamp;
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
import org.sercho.masp.models.Context.Mouse;
import org.sercho.masp.models.Context.Need;
import org.sercho.masp.models.Context.Notebook;
import org.sercho.masp.models.Context.Outdoors;
import org.sercho.masp.models.Context.OutputInteractionResource;
import org.sercho.masp.models.Context.Oven;
import org.sercho.masp.models.Context.PC;
import org.sercho.masp.models.Context.PhysicalDevice;
import org.sercho.masp.models.Context.PhysicalDeviceWithProgram;
import org.sercho.masp.models.Context.PhysicalSensorDevice;
import org.sercho.masp.models.Context.Place;
import org.sercho.masp.models.Context.PointingInputChannel;
import org.sercho.masp.models.Context.Radio;
import org.sercho.masp.models.Context.RemoteControl;
import org.sercho.masp.models.Context.Room;
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
import org.sercho.masp.models.UI.ConcreteInteractor;
import org.sercho.masp.models.UI.OneDimensional;
import org.sercho.masp.models.UI.TwoDimensional;
import org.sercho.masp.models.channel.api.ChannelAPI;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextPackage
 * @generated
 */
public class ContextAdapterFactory extends AdapterFactoryImpl {

    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static ContextPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ContextAdapterFactory() {
        if(modelPackage == null) {
            modelPackage = ContextPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc --> This implementation returns <code>true</code> if
     * the object is either the model's package or is an instance object of the
     * model. <!-- end-user-doc -->
     * 
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if(object == modelPackage) {
            return true;
        }
        if(object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ContextSwitch<Adapter> modelSwitch = new ContextSwitch<Adapter>() {

        @Override
        public Adapter caseEnvironment(Environment object) {
            return createEnvironmentAdapter();
        }

        @Override
        public Adapter caseEnvironmentElement(EnvironmentElement object) {
            return createEnvironmentElementAdapter();
        }

        @Override
        public Adapter caseInteractionResource(InteractionResource object) {
            return createInteractionResourceAdapter();
        }

        @Override
        public Adapter caseUser(User object) {
            return createUserAdapter();
        }

        @Override
        public Adapter caseAssistant(Assistant object) {
            return createAssistantAdapter();
        }

        @Override
        public Adapter caseActivity(Activity object) {
            return createActivityAdapter();
        }

        @Override
        public Adapter caseHomeOSAssistant(HomeOSAssistant object) {
            return createHomeOSAssistantAdapter();
        }

        @Override
        public Adapter caseThirdPartyAssistant(ThirdPartyAssistant object) {
            return createThirdPartyAssistantAdapter();
        }

        @Override
        public Adapter caseLocalizationTag(LocalizationTag object) {
            return createLocalizationTagAdapter();
        }

        @Override
        public Adapter caseElementWithPosition(ElementWithPosition object) {
            return createElementWithPositionAdapter();
        }

        @Override
        public Adapter casePlace(Place object) {
            return createPlaceAdapter();
        }

        @Override
        public Adapter caseRoom(Room object) {
            return createRoomAdapter();
        }

        @Override
        public Adapter caseOutdoors(Outdoors object) {
            return createOutdoorsAdapter();
        }

        @Override
        public Adapter caseDoor(Door object) {
            return createDoorAdapter();
        }

        @Override
        public Adapter caseWindow(Window object) {
            return createWindowAdapter();
        }

        @Override
        public Adapter caseContextProvider(ContextProvider object) {
            return createContextProviderAdapter();
        }

        @Override
        public Adapter caseLocalisationProviderProxy(LocalisationProviderProxy object) {
            return createLocalisationProviderProxyAdapter();
        }

        @Override
        public Adapter caseMouse(Mouse object) {
            return createMouseAdapter();
        }

        @Override
        public Adapter caseKeyboard(Keyboard object) {
            return createKeyboardAdapter();
        }

        @Override
        public Adapter caseDisplay(Display object) {
            return createDisplayAdapter();
        }

        @Override
        public Adapter caseInputInteractionResource(InputInteractionResource object) {
            return createInputInteractionResourceAdapter();
        }

        @Override
        public Adapter caseOutputInteractionResource(OutputInteractionResource object) {
            return createOutputInteractionResourceAdapter();
        }

        @Override
        public Adapter caseGestureIR(GestureIR object) {
            return createGestureIRAdapter();
        }

        @Override
        public Adapter caseTouchpad(Touchpad object) {
            return createTouchpadAdapter();
        }

        @Override
        public Adapter caseTouchscreen(Touchscreen object) {
            return createTouchscreenAdapter();
        }

        @Override
        public Adapter caseDevice(Device object) {
            return createDeviceAdapter();
        }

        @Override
        public Adapter casePhysicalDevice(PhysicalDevice object) {
            return createPhysicalDeviceAdapter();
        }

        @Override
        public Adapter casePhysicalDeviceWithProgram(PhysicalDeviceWithProgram object) {
            return createPhysicalDeviceWithProgramAdapter();
        }

        @Override
        public Adapter caseLamp(Lamp object) {
            return createLampAdapter();
        }

        @Override
        public Adapter caseNotebook(Notebook object) {
            return createNotebookAdapter();
        }

        @Override
        public Adapter caseHood(Hood object) {
            return createHoodAdapter();
        }

        @Override
        public Adapter casePC(PC object) {
            return createPCAdapter();
        }

        @Override
        public Adapter caseFan(Fan object) {
            return createFanAdapter();
        }

        @Override
        public Adapter caseRemoteControl(RemoteControl object) {
            return createRemoteControlAdapter();
        }

        @Override
        public Adapter caseTV(TV object) {
            return createTVAdapter();
        }

        @Override
        public Adapter caseOven(Oven object) {
            return createOvenAdapter();
        }

        @Override
        public Adapter caseDishwasher(Dishwasher object) {
            return createDishwasherAdapter();
        }

        @Override
        public Adapter caseWashingMachine(WashingMachine object) {
            return createWashingMachineAdapter();
        }

        @Override
        public Adapter caseBlind(Blind object) {
            return createBlindAdapter();
        }

        @Override
        public Adapter caseHob(Hob object) {
            return createHobAdapter();
        }

        @Override
        public Adapter caseCooker(Cooker object) {
            return createCookerAdapter();
        }

        @Override
        public Adapter caseCookTop(CookTop object) {
            return createCookTopAdapter();
        }

        @Override
        public Adapter caseFridge(Fridge object) {
            return createFridgeAdapter();
        }

        @Override
        public Adapter caseMixer(Mixer object) {
            return createMixerAdapter();
        }

        @Override
        public Adapter caseRadio(Radio object) {
            return createRadioAdapter();
        }

        @Override
        public Adapter caseSocket(Socket object) {
            return createSocketAdapter();
        }

        @Override
        public Adapter caseHeater(Heater object) {
            return createHeaterAdapter();
        }

        @Override
        public Adapter caseMeter(Meter object) {
            return createMeterAdapter();
        }

        @Override
        public Adapter caseWaterStorageTank(WaterStorageTank object) {
            return createWaterStorageTankAdapter();
        }

        @Override
        public Adapter caseHeatingRod(HeatingRod object) {
            return createHeatingRodAdapter();
        }

        @Override
        public Adapter caseMeterReading(MeterReading object) {
            return createMeterReadingAdapter();
        }

        @Override
        public Adapter casePhysicalSensorDevice(PhysicalSensorDevice object) {
            return createPhysicalSensorDeviceAdapter();
        }

        @Override
        public Adapter caseLuminanceSensor(LuminanceSensor object) {
            return createLuminanceSensorAdapter();
        }

        @Override
        public Adapter caseTemperatureSensor(TemperatureSensor object) {
            return createTemperatureSensorAdapter();
        }

        @Override
        public <C extends ConcreteInteractor, A extends ChannelAPI> Adapter caseChannel(Channel<C, A> object) {
            return createChannelAdapter();
        }

        @Override
        public Adapter caseMessageInputChannel(MessageInputChannel object) {
            return createMessageInputChannelAdapter();
        }

        @Override
        public Adapter caseMessageOutputChannel(MessageOutputChannel object) {
            return createMessageOutputChannelAdapter();
        }

        @Override
        public Adapter casePointingInputChannel(PointingInputChannel object) {
            return createPointingInputChannelAdapter();
        }

        @Override
        public Adapter caseGraphicalOutputChannel(GraphicalOutputChannel object) {
            return createGraphicalOutputChannelAdapter();
        }

        @Override
        public Adapter caseLoudspeaker(Loudspeaker object) {
            return createLoudspeakerAdapter();
        }

        @Override
        public Adapter caseMicrophone(Microphone object) {
            return createMicrophoneAdapter();
        }

        @Override
        public Adapter caseVector(Vector object) {
            return createVectorAdapter();
        }

        @Override
        public Adapter caseArea(Area object) {
            return createAreaAdapter();
        }

        @Override
        public Adapter caseHapticalInteractionResources(HapticalInteractionResources object) {
            return createHapticalInteractionResourcesAdapter();
        }

        @Override
        public Adapter caseConfigurationProperty(ConfigurationProperty object) {
            return createConfigurationPropertyAdapter();
        }

        @Override
        public Adapter caseAssistantConnection(AssistantConnection object) {
            return createAssistantConnectionAdapter();
        }

        @Override
        public Adapter caseTool(Tool object) {
            return createToolAdapter();
        }

        @Override
        public Adapter caseDeviceTool(DeviceTool object) {
            return createDeviceToolAdapter();
        }

        @Override
        public Adapter caseNeed(Need object) {
            return createNeedAdapter();
        }

        @Override
        public Adapter caseOneDimensional(OneDimensional object) {
            return createOneDimensionalAdapter();
        }

        @Override
        public Adapter caseTwoDimensional(TwoDimensional object) {
            return createTwoDimensionalAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.InteractionResource
     * <em>Interaction Resource</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.InteractionResource
     * @generated
     */
    public Adapter createInteractionResourceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Environment <em>Environment</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Environment
     * @generated
     */
    public Adapter createEnvironmentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.User <em>User</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.User
     * @generated
     */
    public Adapter createUserAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Assistant <em>Assistant</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Assistant
     * @generated
     */
    public Adapter createAssistantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Activity <em>Activity</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Activity
     * @generated
     */
    public Adapter createActivityAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.HomeOSAssistant
     * <em>Home OS Assistant</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.HomeOSAssistant
     * @generated
     */
    public Adapter createHomeOSAssistantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.ThirdPartyAssistant
     * <em>Third Party Assistant</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.ThirdPartyAssistant
     * @generated
     */
    public Adapter createThirdPartyAssistantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.LocalizationTag
     * <em>Localization Tag</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.LocalizationTag
     * @generated
     */
    public Adapter createLocalizationTagAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.EnvironmentElement
     * <em>Environment Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.EnvironmentElement
     * @generated
     */
    public Adapter createEnvironmentElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.ElementWithPosition
     * <em>Element With Position</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.ElementWithPosition
     * @generated
     */
    public Adapter createElementWithPositionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Room <em>Room</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Room
     * @generated
     */
    public Adapter createRoomAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.ContextProvider <em>Provider</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.ContextProvider
     * @generated
     */
    public Adapter createContextProviderAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.LocalisationProviderProxy
     * <em>Localisation Provider Proxy</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.LocalisationProviderProxy
     * @generated
     */
    public Adapter createLocalisationProviderProxyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Mouse <em>Mouse</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Mouse
     * @generated
     */
    public Adapter createMouseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Keyboard <em>Keyboard</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Keyboard
     * @generated
     */
    public Adapter createKeyboardAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Display <em>Display</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Display
     * @generated
     */
    public Adapter createDisplayAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.InputInteractionResource
     * <em>Input Interaction Resource</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.InputInteractionResource
     * @generated
     */
    public Adapter createInputInteractionResourceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.OutputInteractionResource
     * <em>Output Interaction Resource</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.OutputInteractionResource
     * @generated
     */
    public Adapter createOutputInteractionResourceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.GestureIR <em>Gesture IR</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.GestureIR
     * @generated
     */
    public Adapter createGestureIRAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Touchpad <em>Touchpad</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Touchpad
     * @generated
     */
    public Adapter createTouchpadAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Device <em>Device</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Device
     * @generated
     */
    public Adapter createDeviceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Channel <em>Channel</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Channel
     * @generated
     */
    public Adapter createChannelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.PointingInputChannel
     * <em>Pointing Input Channel</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.PointingInputChannel
     * @generated
     */
    public Adapter createPointingInputChannelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.MessageInputChannel
     * <em>Message Input Channel</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.MessageInputChannel
     * @generated
     */
    public Adapter createMessageInputChannelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.MessageOutputChannel
     * <em>Message Output Channel</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.MessageOutputChannel
     * @generated
     */
    public Adapter createMessageOutputChannelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.GraphicalOutputChannel
     * <em>Graphical Output Channel</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.GraphicalOutputChannel
     * @generated
     */
    public Adapter createGraphicalOutputChannelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Loudspeaker <em>Loudspeaker</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Loudspeaker
     * @generated
     */
    public Adapter createLoudspeakerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Microphone <em>Microphone</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Microphone
     * @generated
     */
    public Adapter createMicrophoneAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Vector <em>Vector</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Vector
     * @generated
     */
    public Adapter createVectorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Area <em>Area</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Area
     * @generated
     */
    public Adapter createAreaAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.HapticalInteractionResources
     * <em>Haptical Interaction Resources</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.HapticalInteractionResources
     * @generated
     */
    public Adapter createHapticalInteractionResourcesAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.ConfigurationProperty
     * <em>Configuration Property</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.ConfigurationProperty
     * @generated
     */
    public Adapter createConfigurationPropertyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Heater <em>Heater</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Heater
     * @generated
     */
    public Adapter createHeaterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Meter <em>Meter</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Meter
     * @generated
     */
    public Adapter createMeterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.WaterStorageTank
     * <em>Water Storage Tank</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.WaterStorageTank
     * @generated
     */
    public Adapter createWaterStorageTankAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.HeatingRod <em>Heating Rod</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.HeatingRod
     * @generated
     */
    public Adapter createHeatingRodAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.MeterReading
     * <em>Meter Reading</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.MeterReading
     * @generated
     */
    public Adapter createMeterReadingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.PhysicalSensorDevice
     * <em>Physical Sensor Device</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.PhysicalSensorDevice
     * @generated
     */
    public Adapter createPhysicalSensorDeviceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.LuminanceSensor
     * <em>Luminance Sensor</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.LuminanceSensor
     * @generated
     */
    public Adapter createLuminanceSensorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.TemperatureSensor
     * <em>Temperature Sensor</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.TemperatureSensor
     * @generated
     */
    public Adapter createTemperatureSensorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.WashingMachine
     * <em>Washing Machine</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.WashingMachine
     * @generated
     */
    public Adapter createWashingMachineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.AssistantConnection
     * <em>Assistant Connection</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.AssistantConnection
     * @generated
     */
    public Adapter createAssistantConnectionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Tool <em>Tool</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Tool
     * @generated
     */
    public Adapter createToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.DeviceTool <em>Device Tool</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.DeviceTool
     * @generated
     */
    public Adapter createDeviceToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Need <em>Need</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Need
     * @generated
     */
    public Adapter createNeedAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Door <em>Door</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Door
     * @generated
     */
    public Adapter createDoorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Window <em>Window</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Window
     * @generated
     */
    public Adapter createWindowAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Place <em>Place</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Place
     * @generated
     */
    public Adapter createPlaceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Outdoors <em>Outdoors</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Outdoors
     * @generated
     */
    public Adapter createOutdoorsAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Touchscreen <em>Touchscreen</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Touchscreen
     * @generated
     */
    public Adapter createTouchscreenAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.PhysicalDevice
     * <em>Physical Device</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.PhysicalDevice
     * @generated
     */
    public Adapter createPhysicalDeviceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.PhysicalDeviceWithProgram
     * <em>Physical Device With Program</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.PhysicalDeviceWithProgram
     * @generated
     */
    public Adapter createPhysicalDeviceWithProgramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Lamp <em>Lamp</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Lamp
     * @generated
     */
    public Adapter createLampAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Notebook <em>Notebook</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Notebook
     * @generated
     */
    public Adapter createNotebookAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Hood <em>Hood</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Hood
     * @generated
     */
    public Adapter createHoodAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.PC <em>PC</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.PC
     * @generated
     */
    public Adapter createPCAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Fan <em>Fan</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Fan
     * @generated
     */
    public Adapter createFanAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.RemoteControl
     * <em>Remote Control</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.RemoteControl
     * @generated
     */
    public Adapter createRemoteControlAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.TV <em>TV</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.TV
     * @generated
     */
    public Adapter createTVAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Oven <em>Oven</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Oven
     * @generated
     */
    public Adapter createOvenAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Dishwasher <em>Dishwasher</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Dishwasher
     * @generated
     */
    public Adapter createDishwasherAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Blind <em>Blind</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Blind
     * @generated
     */
    public Adapter createBlindAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Hob <em>Hob</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Hob
     * @generated
     */
    public Adapter createHobAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Cooker <em>Cooker</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Cooker
     * @generated
     */
    public Adapter createCookerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.CookTop <em>Cook Top</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.CookTop
     * @generated
     */
    public Adapter createCookTopAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Fridge <em>Fridge</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Fridge
     * @generated
     */
    public Adapter createFridgeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Mixer <em>Mixer</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Mixer
     * @generated
     */
    public Adapter createMixerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Radio <em>Radio</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Radio
     * @generated
     */
    public Adapter createRadioAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.Context.Socket <em>Socket</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.Context.Socket
     * @generated
     */
    public Adapter createSocketAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.UI.OneDimensional <em>One Dimensional</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.UI.OneDimensional
     * @generated
     */
    public Adapter createOneDimensionalAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.sercho.masp.models.UI.TwoDimensional <em>Two Dimensional</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.sercho.masp.models.UI.TwoDimensional
     * @generated
     */
    public Adapter createTwoDimensionalAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This
     * default implementation returns null. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // ContextAdapterFactory
