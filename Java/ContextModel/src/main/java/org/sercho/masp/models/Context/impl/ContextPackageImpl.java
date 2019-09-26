/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.sercho.masp.context.providers.location.LocalisationProvider;
import org.sercho.masp.models.Context.Activity;
import org.sercho.masp.models.Context.Area;
import org.sercho.masp.models.Context.Assistant;
import org.sercho.masp.models.Context.AssistantConnection;
import org.sercho.masp.models.Context.Blind;
import org.sercho.masp.models.Context.Channel;
import org.sercho.masp.models.Context.ConfigurationProperty;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.ContextProvider;
import org.sercho.masp.models.Context.CookTop;
import org.sercho.masp.models.Context.Cooker;
import org.sercho.masp.models.Context.Device;
import org.sercho.masp.models.Context.DeviceTool;
import org.sercho.masp.models.Context.Dishwasher;
import org.sercho.masp.models.Context.Display;
import org.sercho.masp.models.Context.DistributionState;
import org.sercho.masp.models.Context.Domain;
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
import org.sercho.masp.models.Context.Need;
import org.sercho.masp.models.Context.Notebook;
import org.sercho.masp.models.Context.Outdoors;
import org.sercho.masp.models.Context.OutputInteractionResource;
import org.sercho.masp.models.Context.Oven;
import org.sercho.masp.models.Context.PhysicalDevice;
import org.sercho.masp.models.Context.PhysicalDeviceWithProgram;
import org.sercho.masp.models.Context.PhysicalSensorDevice;
import org.sercho.masp.models.Context.Place;
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
import org.sercho.masp.models.UI.UIPackage;
import org.sercho.masp.models.channel.api.ChannelAPI;
import org.sercho.masp.models.channel.api.GraphicalOutputChannelAPI;
import org.sercho.masp.models.channel.api.MessageInputChannelAPI;
import org.sercho.masp.models.channel.api.MessageOutputChannelAPI;
import org.sercho.masp.models.channel.api.PointingInputChannelAPI;

import de.dailab.masp.models.Properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ContextPackageImpl extends EPackageImpl implements ContextPackage {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass environmentEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass environmentElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass interactionResourceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass userEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass assistantEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass activityEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass homeOSAssistantEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass thirdPartyAssistantEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass localizationTagEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass elementWithPositionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass placeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass roomEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass outdoorsEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass doorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass windowEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass contextProviderEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass localisationProviderProxyEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass mouseEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass keyboardEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass displayEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass inputInteractionResourceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass outputInteractionResourceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass gestureIREClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass touchpadEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass deviceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass touchscreenEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass physicalDeviceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass physicalDeviceWithProgramEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass lampEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass notebookEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass hoodEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass pcEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass fanEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass remoteControlEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tvEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass ovenEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dishwasherEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass blindEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass hobEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass cookerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass cookTopEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass fridgeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass mixerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass radioEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass socketEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass channelEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass messageInputChannelEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass messageOutputChannelEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass pointingInputChannelEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass graphicalOutputChannelEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass loudspeakerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass microphoneEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass vectorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass areaEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass hapticalInteractionResourcesEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass configurationPropertyEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass heaterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass meterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass waterStorageTankEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass heatingRodEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass meterReadingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass physicalSensorDeviceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass luminanceSensorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass temperatureSensorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass washingMachineEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass assistantConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass toolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass deviceToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass needEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum roomTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum readingSubjectEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum readingMeasurandEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum readingMeasurementMethodEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum readingRateEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum distributionStateEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum modalityEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum domainEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum lampTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType channelAPIEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType messageInputChannelAPIEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType pointingInputChannelAPIEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType messageOutputChannelAPIEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType graphicalOutputChannelAPIEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType localisationProviderEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType placeInfoEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.sercho.masp.models.Context.ContextPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ContextPackageImpl() {
        super(eNS_URI, ContextFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends.
     * 
     * <p>
     * This method is used to initialize {@link ContextPackage#eINSTANCE} when
     * that field is accessed. Clients should not invoke it directly. Instead,
     * they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ContextPackage init() {
        if(isInited)
            return (ContextPackage)EPackage.Registry.INSTANCE.getEPackage(ContextPackage.eNS_URI);

        // Obtain or create and register package
        ContextPackageImpl theContextPackage = (ContextPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ContextPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new ContextPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        UIPackage.eINSTANCE.eClass();
        PropertiesPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theContextPackage.createPackageContents();

        // Initialize created meta-data
        theContextPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theContextPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ContextPackage.eNS_URI, theContextPackage);
        return theContextPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getEnvironment() {
        return environmentEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEnvironment_Places() {
        return (EReference)environmentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEnvironment_Providers() {
        return (EReference)environmentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEnvironment_Devices() {
        return (EReference)environmentEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEnvironment_Users() {
        return (EReference)environmentEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEnvironment_Assistants() {
        return (EReference)environmentEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEnvironment_InitialAssistant() {
        return (EReference)environmentEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEnvironment_ServiceContainers() {
        return (EReference)environmentEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEnvironment_Meters() {
        return (EReference)environmentEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEnvironment_Discoverers() {
        return (EReference)environmentEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEnvironment_Activities() {
        return (EReference)environmentEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEnvironment_Tools() {
        return (EReference)environmentEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEnvironment_Name() {
        return (EAttribute)environmentEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEnvironment_Description() {
        return (EAttribute)environmentEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getEnvironmentElement() {
        return environmentElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getEnvironmentElement_Id() {
        return (EAttribute)environmentElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getEnvironmentElement_Name() {
        return (EAttribute)environmentElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getInteractionResource() {
        return interactionResourceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getInteractionResource_User() {
        return (EReference)interactionResourceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getInteractionResource_Device() {
        return (EReference)interactionResourceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getInteractionResource_Available() {
        return (EAttribute)interactionResourceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getInteractionResource_Modality() {
        return (EAttribute)interactionResourceEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getInteractionResource_Mobile() {
        return (EAttribute)interactionResourceEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getInteractionResource_Personal() {
        return (EAttribute)interactionResourceEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getInteractionResource_InteractionStatus() {
        return (EAttribute)interactionResourceEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getInteractionResource_InteractionStatusTimeStamp() {
        return (EAttribute)interactionResourceEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getUser() {
        return userEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getUser_BirthDate() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getUser_Surname() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getUser_LeftHanded() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getUser_Resources() {
        return (EReference)userEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getUser_LineOfVision() {
        return (EReference)userEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getUser_IrExperience() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getUser_FollowMe() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getUser_Environment() {
        return (EReference)userEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getUser_CurrentAssistants() {
        return (EReference)userEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getUser_PastAssistants() {
        return (EReference)userEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getUser_InstalledAssistants() {
        return (EReference)userEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getUser_PasswordHash() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getAssistant() {
        return assistantEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getAssistant_Activities() {
        return (EReference)assistantEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getAssistant_CurrentUsers() {
        return (EReference)assistantEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getAssistant_PastUsers() {
        return (EReference)assistantEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAssistant_Description() {
        return (EAttribute)assistantEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getAssistant_Connections() {
        return (EReference)assistantEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAssistant_Domain() {
        return (EAttribute)assistantEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getActivity() {
        return activityEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getActivity_Name() {
        return (EAttribute)activityEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getActivity_Description() {
        return (EAttribute)activityEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getActivity_UsedWith() {
        return (EReference)activityEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getActivity_Configuration() {
        return (EReference)activityEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getHomeOSAssistant() {
        return homeOSAssistantEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getThirdPartyAssistant() {
        return thirdPartyAssistantEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getLocalizationTag() {
        return localizationTagEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLocalizationTag_Detected() {
        return (EAttribute)localizationTagEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLocalizationTag_Register() {
        return (EAttribute)localizationTagEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLocalizationTag_Provider() {
        return (EReference)localizationTagEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLocalizationTag_Element() {
        return (EReference)localizationTagEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getElementWithPosition() {
        return elementWithPositionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getElementWithPosition_Tags() {
        return (EReference)elementWithPositionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getElementWithPosition_Position() {
        return (EReference)elementWithPositionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getElementWithPosition_PositionTimeStamp() {
        return (EAttribute)elementWithPositionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getElementWithPosition_Place() {
        return (EReference)elementWithPositionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getPlace() {
        return placeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPlace_Areas() {
        return (EReference)placeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPlace_Elements() {
        return (EReference)placeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPlace_Environment() {
        return (EReference)placeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPlace_Doors() {
        return (EReference)placeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPlace_Windows() {
        return (EReference)placeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPlace_Floor() {
        return (EAttribute)placeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRoom() {
        return roomEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRoom_Type() {
        return (EAttribute)roomEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getOutdoors() {
        return outdoorsEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDoor() {
        return doorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDoor_Open() {
        return (EReference)doorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDoor_Source() {
        return (EReference)doorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDoor_Target() {
        return (EReference)doorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDoor_Span() {
        return (EReference)doorEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getWindow() {
        return windowEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getWindow_Open() {
        return (EReference)windowEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getWindow_Source() {
        return (EReference)windowEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getWindow_Target() {
        return (EReference)windowEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getWindow_Span() {
        return (EReference)windowEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getContextProvider() {
        return contextProviderEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getContextProvider_Environment() {
        return (EReference)contextProviderEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getLocalisationProviderProxy() {
        return localisationProviderProxyEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLocalisationProviderProxy_ApiClass() {
        return (EAttribute)localisationProviderProxyEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLocalisationProviderProxy_Api() {
        return (EAttribute)localisationProviderProxyEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLocalisationProviderProxy_Tags() {
        return (EReference)localisationProviderProxyEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLocalisationProviderProxy_Configuration() {
        return (EReference)localisationProviderProxyEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMouse() {
        return mouseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getMouse_MotionSensor() {
        return (EReference)mouseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getMouse_Buttons() {
        return (EReference)mouseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getKeyboard() {
        return keyboardEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getKeyboard_Keys() {
        return (EReference)keyboardEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDisplay() {
        return displayEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDisplay_XPixels() {
        return (EAttribute)displayEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDisplay_YPixels() {
        return (EAttribute)displayEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDisplay_Direction() {
        return (EReference)displayEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDisplay_Screen() {
        return (EReference)displayEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDisplay_Size() {
        return (EAttribute)displayEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getInputInteractionResource() {
        return inputInteractionResourceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getOutputInteractionResource() {
        return outputInteractionResourceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getGestureIR() {
        return gestureIREClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getGestureIR_GestureRecognition() {
        return (EReference)gestureIREClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTouchpad() {
        return touchpadEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDevice() {
        return deviceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDevice_Resources() {
        return (EReference)deviceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDevice_Mobile() {
        return (EAttribute)deviceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDevice_Environment() {
        return (EReference)deviceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDevice_Manufacturer() {
        return (EAttribute)deviceEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDevice_ModelName() {
        return (EAttribute)deviceEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTouchscreen() {
        return touchscreenEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTouchscreen_TouchSurface() {
        return (EReference)touchscreenEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTouchscreen_GestureRecognition() {
        return (EReference)touchscreenEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getPhysicalDevice() {
        return physicalDeviceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPhysicalDevice_On() {
        return (EReference)physicalDeviceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPhysicalDevice_PowerUsage() {
        return (EReference)physicalDeviceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPhysicalDevice_SubDevice() {
        return (EReference)physicalDeviceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPhysicalDevice_ParentDevice() {
        return (EReference)physicalDeviceEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getPhysicalDeviceWithProgram() {
        return physicalDeviceWithProgramEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPhysicalDeviceWithProgram_Program() {
        return (EReference)physicalDeviceWithProgramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPhysicalDeviceWithProgram_SecondsRemaining() {
        return (EReference)physicalDeviceWithProgramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getLamp() {
        return lampEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLamp_DimmingLevel() {
        return (EReference)lampEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLamp_LampType() {
        return (EAttribute)lampEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getNotebook() {
        return notebookEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getHood() {
        return hoodEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getPC() {
        return pcEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getFan() {
        return fanEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getFan_Speed() {
        return (EReference)fanEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRemoteControl() {
        return remoteControlEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTV() {
        return tvEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTV_CurrentProgram() {
        return (EReference)tvEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getOven() {
        return ovenEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getOven_Temperature() {
        return (EReference)ovenEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDishwasher() {
        return dishwasherEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getBlind() {
        return blindEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getBlind_Level() {
        return (EReference)blindEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getHob() {
        return hobEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getHob_HeatLevel() {
        return (EReference)hobEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCooker() {
        return cookerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCookTop() {
        return cookTopEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getFridge() {
        return fridgeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getFridge_Temperature() {
        return (EReference)fridgeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMixer() {
        return mixerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRadio() {
        return radioEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSocket() {
        return socketEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getChannel() {
        return channelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getChannel_DistributionState() {
        return (EAttribute)channelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getChannel_Elements() {
        return (EReference)channelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getChannel_Api() {
        return (EAttribute)channelEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getChannel_Configuration() {
        return (EReference)channelEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getChannel_Available() {
        return (EAttribute)channelEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getChannel_ApiClass() {
        return (EAttribute)channelEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMessageInputChannel() {
        return messageInputChannelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMessageOutputChannel() {
        return messageOutputChannelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getPointingInputChannel() {
        return pointingInputChannelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getGraphicalOutputChannel() {
        return graphicalOutputChannelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getLoudspeaker() {
        return loudspeakerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLoudspeaker_Voice() {
        return (EReference)loudspeakerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMicrophone() {
        return microphoneEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getMicrophone_VoiceRecognition() {
        return (EReference)microphoneEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getVector() {
        return vectorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getVector_X() {
        return (EAttribute)vectorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getVector_Y() {
        return (EAttribute)vectorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getVector_Z() {
        return (EAttribute)vectorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getArea() {
        return areaEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getArea_Origin() {
        return (EReference)areaEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getArea_Span() {
        return (EReference)areaEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getHapticalInteractionResources() {
        return hapticalInteractionResourcesEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getConfigurationProperty() {
        return configurationPropertyEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getConfigurationProperty_Key() {
        return (EAttribute)configurationPropertyEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getConfigurationProperty_Value() {
        return (EAttribute)configurationPropertyEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getHeater() {
        return heaterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getHeater_TemperatureDemand() {
        return (EReference)heaterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getHeater_TemperatureCurrent() {
        return (EReference)heaterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getHeater_ValvePosition() {
        return (EReference)heaterEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMeter() {
        return meterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getMeter_Readings() {
        return (EReference)meterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getWaterStorageTank() {
        return waterStorageTankEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getWaterStorageTank_TemperatureSensor() {
        return (EReference)waterStorageTankEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getWaterStorageTank_Capacity() {
        return (EAttribute)waterStorageTankEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getHeatingRod() {
        return heatingRodEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getHeatingRod_MaximumPowerWatts() {
        return (EAttribute)heatingRodEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMeterReading() {
        return meterReadingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getMeterReading_Value() {
        return (EReference)meterReadingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getMeterReading_Subject() {
        return (EAttribute)meterReadingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getMeterReading_Measurand() {
        return (EAttribute)meterReadingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getMeterReading_MeasurementMethod() {
        return (EAttribute)meterReadingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getMeterReading_Rate() {
        return (EAttribute)meterReadingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMeterReading_Identifier() {
        return (EAttribute)meterReadingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getPhysicalSensorDevice() {
        return physicalSensorDeviceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPhysicalSensorDevice_Value() {
        return (EReference)physicalSensorDeviceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getLuminanceSensor() {
        return luminanceSensorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTemperatureSensor() {
        return temperatureSensorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getWashingMachine() {
        return washingMachineEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getAssistantConnection() {
        return assistantConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAssistantConnection_Description() {
        return (EAttribute)assistantConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getAssistantConnection_ConnectedAssistant() {
        return (EReference)assistantConnectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTool() {
        return toolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTool_Name() {
        return (EAttribute)toolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTool_Description() {
        return (EAttribute)toolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDeviceTool() {
        return deviceToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDeviceTool_Controls() {
        return (EReference)deviceToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDeviceTool_AppliesTo() {
        return (EReference)deviceToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNeed() {
        return needEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNeed_FulfilledBy() {
        return (EReference)needEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNeed_Name() {
        return (EAttribute)needEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNeed_Description() {
        return (EAttribute)needEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNeed_Configuration() {
        return (EReference)needEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getRoomType() {
        return roomTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getReadingSubject() {
        return readingSubjectEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getReadingMeasurand() {
        return readingMeasurandEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getReadingMeasurementMethod() {
        return readingMeasurementMethodEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getReadingRate() {
        return readingRateEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getDistributionState() {
        return distributionStateEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getModality() {
        return modalityEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getDomain() {
        return domainEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getLampType() {
        return lampTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getChannelAPI() {
        return channelAPIEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getMessageInputChannelAPI() {
        return messageInputChannelAPIEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getPointingInputChannelAPI() {
        return pointingInputChannelAPIEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getMessageOutputChannelAPI() {
        return messageOutputChannelAPIEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getGraphicalOutputChannelAPI() {
        return graphicalOutputChannelAPIEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getLocalisationProvider() {
        return localisationProviderEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getPlaceInfo() {
        return placeInfoEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ContextFactory getContextFactory() {
        return (ContextFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if(isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        environmentEClass = createEClass(ENVIRONMENT);
        createEReference(environmentEClass, ENVIRONMENT__PLACES);
        createEReference(environmentEClass, ENVIRONMENT__PROVIDERS);
        createEReference(environmentEClass, ENVIRONMENT__DEVICES);
        createEReference(environmentEClass, ENVIRONMENT__USERS);
        createEReference(environmentEClass, ENVIRONMENT__ASSISTANTS);
        createEReference(environmentEClass, ENVIRONMENT__INITIAL_ASSISTANT);
        createEReference(environmentEClass, ENVIRONMENT__SERVICE_CONTAINERS);
        createEReference(environmentEClass, ENVIRONMENT__METERS);
        createEReference(environmentEClass, ENVIRONMENT__DISCOVERERS);
        createEReference(environmentEClass, ENVIRONMENT__ACTIVITIES);
        createEReference(environmentEClass, ENVIRONMENT__TOOLS);
        createEAttribute(environmentEClass, ENVIRONMENT__NAME);
        createEAttribute(environmentEClass, ENVIRONMENT__DESCRIPTION);

        environmentElementEClass = createEClass(ENVIRONMENT_ELEMENT);
        createEAttribute(environmentElementEClass, ENVIRONMENT_ELEMENT__ID);
        createEAttribute(environmentElementEClass, ENVIRONMENT_ELEMENT__NAME);

        interactionResourceEClass = createEClass(INTERACTION_RESOURCE);
        createEReference(interactionResourceEClass, INTERACTION_RESOURCE__USER);
        createEReference(interactionResourceEClass, INTERACTION_RESOURCE__DEVICE);
        createEAttribute(interactionResourceEClass, INTERACTION_RESOURCE__AVAILABLE);
        createEAttribute(interactionResourceEClass, INTERACTION_RESOURCE__MODALITY);
        createEAttribute(interactionResourceEClass, INTERACTION_RESOURCE__MOBILE);
        createEAttribute(interactionResourceEClass, INTERACTION_RESOURCE__PERSONAL);
        createEAttribute(interactionResourceEClass, INTERACTION_RESOURCE__INTERACTION_STATUS);
        createEAttribute(interactionResourceEClass, INTERACTION_RESOURCE__INTERACTION_STATUS_TIME_STAMP);

        userEClass = createEClass(USER);
        createEAttribute(userEClass, USER__BIRTH_DATE);
        createEAttribute(userEClass, USER__SURNAME);
        createEAttribute(userEClass, USER__LEFT_HANDED);
        createEReference(userEClass, USER__RESOURCES);
        createEReference(userEClass, USER__LINE_OF_VISION);
        createEAttribute(userEClass, USER__IR_EXPERIENCE);
        createEAttribute(userEClass, USER__FOLLOW_ME);
        createEReference(userEClass, USER__ENVIRONMENT);
        createEReference(userEClass, USER__CURRENT_ASSISTANTS);
        createEReference(userEClass, USER__PAST_ASSISTANTS);
        createEReference(userEClass, USER__INSTALLED_ASSISTANTS);
        createEAttribute(userEClass, USER__PASSWORD_HASH);

        assistantEClass = createEClass(ASSISTANT);
        createEReference(assistantEClass, ASSISTANT__ACTIVITIES);
        createEReference(assistantEClass, ASSISTANT__CURRENT_USERS);
        createEReference(assistantEClass, ASSISTANT__PAST_USERS);
        createEAttribute(assistantEClass, ASSISTANT__DESCRIPTION);
        createEReference(assistantEClass, ASSISTANT__CONNECTIONS);
        createEAttribute(assistantEClass, ASSISTANT__DOMAIN);

        activityEClass = createEClass(ACTIVITY);
        createEAttribute(activityEClass, ACTIVITY__NAME);
        createEAttribute(activityEClass, ACTIVITY__DESCRIPTION);
        createEReference(activityEClass, ACTIVITY__USED_WITH);
        createEReference(activityEClass, ACTIVITY__CONFIGURATION);

        homeOSAssistantEClass = createEClass(HOME_OS_ASSISTANT);

        thirdPartyAssistantEClass = createEClass(THIRD_PARTY_ASSISTANT);

        localizationTagEClass = createEClass(LOCALIZATION_TAG);
        createEAttribute(localizationTagEClass, LOCALIZATION_TAG__DETECTED);
        createEAttribute(localizationTagEClass, LOCALIZATION_TAG__REGISTER);
        createEReference(localizationTagEClass, LOCALIZATION_TAG__PROVIDER);
        createEReference(localizationTagEClass, LOCALIZATION_TAG__ELEMENT);

        elementWithPositionEClass = createEClass(ELEMENT_WITH_POSITION);
        createEReference(elementWithPositionEClass, ELEMENT_WITH_POSITION__TAGS);
        createEReference(elementWithPositionEClass, ELEMENT_WITH_POSITION__POSITION);
        createEAttribute(elementWithPositionEClass, ELEMENT_WITH_POSITION__POSITION_TIME_STAMP);
        createEReference(elementWithPositionEClass, ELEMENT_WITH_POSITION__PLACE);

        placeEClass = createEClass(PLACE);
        createEReference(placeEClass, PLACE__AREAS);
        createEReference(placeEClass, PLACE__ELEMENTS);
        createEReference(placeEClass, PLACE__ENVIRONMENT);
        createEReference(placeEClass, PLACE__DOORS);
        createEReference(placeEClass, PLACE__WINDOWS);
        createEAttribute(placeEClass, PLACE__FLOOR);

        roomEClass = createEClass(ROOM);
        createEAttribute(roomEClass, ROOM__TYPE);

        outdoorsEClass = createEClass(OUTDOORS);

        doorEClass = createEClass(DOOR);
        createEReference(doorEClass, DOOR__OPEN);
        createEReference(doorEClass, DOOR__SOURCE);
        createEReference(doorEClass, DOOR__TARGET);
        createEReference(doorEClass, DOOR__SPAN);

        windowEClass = createEClass(WINDOW);
        createEReference(windowEClass, WINDOW__OPEN);
        createEReference(windowEClass, WINDOW__SOURCE);
        createEReference(windowEClass, WINDOW__TARGET);
        createEReference(windowEClass, WINDOW__SPAN);

        contextProviderEClass = createEClass(CONTEXT_PROVIDER);
        createEReference(contextProviderEClass, CONTEXT_PROVIDER__ENVIRONMENT);

        localisationProviderProxyEClass = createEClass(LOCALISATION_PROVIDER_PROXY);
        createEAttribute(localisationProviderProxyEClass, LOCALISATION_PROVIDER_PROXY__API_CLASS);
        createEAttribute(localisationProviderProxyEClass, LOCALISATION_PROVIDER_PROXY__API);
        createEReference(localisationProviderProxyEClass, LOCALISATION_PROVIDER_PROXY__TAGS);
        createEReference(localisationProviderProxyEClass, LOCALISATION_PROVIDER_PROXY__CONFIGURATION);

        mouseEClass = createEClass(MOUSE);
        createEReference(mouseEClass, MOUSE__MOTION_SENSOR);
        createEReference(mouseEClass, MOUSE__BUTTONS);

        keyboardEClass = createEClass(KEYBOARD);
        createEReference(keyboardEClass, KEYBOARD__KEYS);

        displayEClass = createEClass(DISPLAY);
        createEAttribute(displayEClass, DISPLAY__XPIXELS);
        createEAttribute(displayEClass, DISPLAY__YPIXELS);
        createEReference(displayEClass, DISPLAY__DIRECTION);
        createEReference(displayEClass, DISPLAY__SCREEN);
        createEAttribute(displayEClass, DISPLAY__SIZE);

        inputInteractionResourceEClass = createEClass(INPUT_INTERACTION_RESOURCE);

        outputInteractionResourceEClass = createEClass(OUTPUT_INTERACTION_RESOURCE);

        gestureIREClass = createEClass(GESTURE_IR);
        createEReference(gestureIREClass, GESTURE_IR__GESTURE_RECOGNITION);

        touchpadEClass = createEClass(TOUCHPAD);

        touchscreenEClass = createEClass(TOUCHSCREEN);
        createEReference(touchscreenEClass, TOUCHSCREEN__TOUCH_SURFACE);
        createEReference(touchscreenEClass, TOUCHSCREEN__GESTURE_RECOGNITION);

        deviceEClass = createEClass(DEVICE);
        createEReference(deviceEClass, DEVICE__RESOURCES);
        createEAttribute(deviceEClass, DEVICE__MOBILE);
        createEReference(deviceEClass, DEVICE__ENVIRONMENT);
        createEAttribute(deviceEClass, DEVICE__MANUFACTURER);
        createEAttribute(deviceEClass, DEVICE__MODEL_NAME);

        physicalDeviceEClass = createEClass(PHYSICAL_DEVICE);
        createEReference(physicalDeviceEClass, PHYSICAL_DEVICE__ON);
        createEReference(physicalDeviceEClass, PHYSICAL_DEVICE__POWER_USAGE);
        createEReference(physicalDeviceEClass, PHYSICAL_DEVICE__SUB_DEVICE);
        createEReference(physicalDeviceEClass, PHYSICAL_DEVICE__PARENT_DEVICE);

        physicalDeviceWithProgramEClass = createEClass(PHYSICAL_DEVICE_WITH_PROGRAM);
        createEReference(physicalDeviceWithProgramEClass, PHYSICAL_DEVICE_WITH_PROGRAM__PROGRAM);
        createEReference(physicalDeviceWithProgramEClass, PHYSICAL_DEVICE_WITH_PROGRAM__SECONDS_REMAINING);

        lampEClass = createEClass(LAMP);
        createEReference(lampEClass, LAMP__DIMMING_LEVEL);
        createEAttribute(lampEClass, LAMP__LAMP_TYPE);

        notebookEClass = createEClass(NOTEBOOK);

        hoodEClass = createEClass(HOOD);

        pcEClass = createEClass(PC);

        fanEClass = createEClass(FAN);
        createEReference(fanEClass, FAN__SPEED);

        remoteControlEClass = createEClass(REMOTE_CONTROL);

        tvEClass = createEClass(TV);
        createEReference(tvEClass, TV__CURRENT_PROGRAM);

        ovenEClass = createEClass(OVEN);
        createEReference(ovenEClass, OVEN__TEMPERATURE);

        dishwasherEClass = createEClass(DISHWASHER);

        washingMachineEClass = createEClass(WASHING_MACHINE);

        blindEClass = createEClass(BLIND);
        createEReference(blindEClass, BLIND__LEVEL);

        hobEClass = createEClass(HOB);
        createEReference(hobEClass, HOB__HEAT_LEVEL);

        cookerEClass = createEClass(COOKER);

        cookTopEClass = createEClass(COOK_TOP);

        fridgeEClass = createEClass(FRIDGE);
        createEReference(fridgeEClass, FRIDGE__TEMPERATURE);

        mixerEClass = createEClass(MIXER);

        radioEClass = createEClass(RADIO);

        socketEClass = createEClass(SOCKET);

        heaterEClass = createEClass(HEATER);
        createEReference(heaterEClass, HEATER__TEMPERATURE_DEMAND);
        createEReference(heaterEClass, HEATER__TEMPERATURE_CURRENT);
        createEReference(heaterEClass, HEATER__VALVE_POSITION);

        meterEClass = createEClass(METER);
        createEReference(meterEClass, METER__READINGS);

        waterStorageTankEClass = createEClass(WATER_STORAGE_TANK);
        createEReference(waterStorageTankEClass, WATER_STORAGE_TANK__TEMPERATURE_SENSOR);
        createEAttribute(waterStorageTankEClass, WATER_STORAGE_TANK__CAPACITY);

        heatingRodEClass = createEClass(HEATING_ROD);
        createEAttribute(heatingRodEClass, HEATING_ROD__MAXIMUM_POWER_WATTS);

        meterReadingEClass = createEClass(METER_READING);
        createEReference(meterReadingEClass, METER_READING__VALUE);
        createEAttribute(meterReadingEClass, METER_READING__SUBJECT);
        createEAttribute(meterReadingEClass, METER_READING__MEASURAND);
        createEAttribute(meterReadingEClass, METER_READING__MEASUREMENT_METHOD);
        createEAttribute(meterReadingEClass, METER_READING__RATE);
        createEAttribute(meterReadingEClass, METER_READING__IDENTIFIER);

        physicalSensorDeviceEClass = createEClass(PHYSICAL_SENSOR_DEVICE);
        createEReference(physicalSensorDeviceEClass, PHYSICAL_SENSOR_DEVICE__VALUE);

        luminanceSensorEClass = createEClass(LUMINANCE_SENSOR);

        temperatureSensorEClass = createEClass(TEMPERATURE_SENSOR);

        channelEClass = createEClass(CHANNEL);
        createEAttribute(channelEClass, CHANNEL__DISTRIBUTION_STATE);
        createEReference(channelEClass, CHANNEL__ELEMENTS);
        createEAttribute(channelEClass, CHANNEL__API);
        createEReference(channelEClass, CHANNEL__CONFIGURATION);
        createEAttribute(channelEClass, CHANNEL__AVAILABLE);
        createEAttribute(channelEClass, CHANNEL__API_CLASS);

        messageInputChannelEClass = createEClass(MESSAGE_INPUT_CHANNEL);

        messageOutputChannelEClass = createEClass(MESSAGE_OUTPUT_CHANNEL);

        pointingInputChannelEClass = createEClass(POINTING_INPUT_CHANNEL);

        graphicalOutputChannelEClass = createEClass(GRAPHICAL_OUTPUT_CHANNEL);

        loudspeakerEClass = createEClass(LOUDSPEAKER);
        createEReference(loudspeakerEClass, LOUDSPEAKER__VOICE);

        microphoneEClass = createEClass(MICROPHONE);
        createEReference(microphoneEClass, MICROPHONE__VOICE_RECOGNITION);

        vectorEClass = createEClass(VECTOR);
        createEAttribute(vectorEClass, VECTOR__X);
        createEAttribute(vectorEClass, VECTOR__Y);
        createEAttribute(vectorEClass, VECTOR__Z);

        areaEClass = createEClass(AREA);
        createEReference(areaEClass, AREA__ORIGIN);
        createEReference(areaEClass, AREA__SPAN);

        hapticalInteractionResourcesEClass = createEClass(HAPTICAL_INTERACTION_RESOURCES);

        configurationPropertyEClass = createEClass(CONFIGURATION_PROPERTY);
        createEAttribute(configurationPropertyEClass, CONFIGURATION_PROPERTY__KEY);
        createEAttribute(configurationPropertyEClass, CONFIGURATION_PROPERTY__VALUE);

        assistantConnectionEClass = createEClass(ASSISTANT_CONNECTION);
        createEAttribute(assistantConnectionEClass, ASSISTANT_CONNECTION__DESCRIPTION);
        createEReference(assistantConnectionEClass, ASSISTANT_CONNECTION__CONNECTED_ASSISTANT);

        toolEClass = createEClass(TOOL);
        createEAttribute(toolEClass, TOOL__NAME);
        createEAttribute(toolEClass, TOOL__DESCRIPTION);

        deviceToolEClass = createEClass(DEVICE_TOOL);
        createEReference(deviceToolEClass, DEVICE_TOOL__CONTROLS);
        createEReference(deviceToolEClass, DEVICE_TOOL__APPLIES_TO);

        needEClass = createEClass(NEED);
        createEReference(needEClass, NEED__FULFILLED_BY);
        createEAttribute(needEClass, NEED__NAME);
        createEAttribute(needEClass, NEED__DESCRIPTION);
        createEReference(needEClass, NEED__CONFIGURATION);

        // Create enums
        roomTypeEEnum = createEEnum(ROOM_TYPE);
        readingSubjectEEnum = createEEnum(READING_SUBJECT);
        readingMeasurandEEnum = createEEnum(READING_MEASURAND);
        readingMeasurementMethodEEnum = createEEnum(READING_MEASUREMENT_METHOD);
        readingRateEEnum = createEEnum(READING_RATE);
        distributionStateEEnum = createEEnum(DISTRIBUTION_STATE);
        modalityEEnum = createEEnum(MODALITY);
        domainEEnum = createEEnum(DOMAIN);
        lampTypeEEnum = createEEnum(LAMP_TYPE);

        // Create data types
        channelAPIEDataType = createEDataType(CHANNEL_API);
        messageInputChannelAPIEDataType = createEDataType(MESSAGE_INPUT_CHANNEL_API);
        pointingInputChannelAPIEDataType = createEDataType(POINTING_INPUT_CHANNEL_API);
        messageOutputChannelAPIEDataType = createEDataType(MESSAGE_OUTPUT_CHANNEL_API);
        graphicalOutputChannelAPIEDataType = createEDataType(GRAPHICAL_OUTPUT_CHANNEL_API);
        localisationProviderEDataType = createEDataType(LOCALISATION_PROVIDER);
        placeInfoEDataType = createEDataType(PLACE_INFO);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if(isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        PropertiesPackage thePropertiesPackage = (PropertiesPackage)EPackage.Registry.INSTANCE.getEPackage(PropertiesPackage.eNS_URI);
        UIPackage theUIPackage = (UIPackage)EPackage.Registry.INSTANCE.getEPackage(UIPackage.eNS_URI);

        // Create type parameters
        ETypeParameter channelEClass_C = addETypeParameter(channelEClass, "C");
        ETypeParameter channelEClass_A = addETypeParameter(channelEClass, "A");

        // Set bounds for type parameters
        EGenericType g1 = createEGenericType(theUIPackage.getConcreteInteractor());
        channelEClass_C.getEBounds().add(g1);
        g1 = createEGenericType(this.getChannelAPI());
        channelEClass_A.getEBounds().add(g1);

        // Add supertypes to classes
        interactionResourceEClass.getESuperTypes().add(this.getElementWithPosition());
        userEClass.getESuperTypes().add(this.getElementWithPosition());
        assistantEClass.getESuperTypes().add(this.getEnvironmentElement());
        homeOSAssistantEClass.getESuperTypes().add(this.getAssistant());
        thirdPartyAssistantEClass.getESuperTypes().add(this.getAssistant());
        localizationTagEClass.getESuperTypes().add(this.getElementWithPosition());
        elementWithPositionEClass.getESuperTypes().add(this.getEnvironmentElement());
        placeEClass.getESuperTypes().add(this.getEnvironmentElement());
        roomEClass.getESuperTypes().add(this.getPlace());
        outdoorsEClass.getESuperTypes().add(this.getPlace());
        doorEClass.getESuperTypes().add(this.getElementWithPosition());
        windowEClass.getESuperTypes().add(this.getElementWithPosition());
        localisationProviderProxyEClass.getESuperTypes().add(this.getContextProvider());
        mouseEClass.getESuperTypes().add(this.getHapticalInteractionResources());
        keyboardEClass.getESuperTypes().add(this.getHapticalInteractionResources());
        displayEClass.getESuperTypes().add(this.getOutputInteractionResource());
        inputInteractionResourceEClass.getESuperTypes().add(this.getInteractionResource());
        outputInteractionResourceEClass.getESuperTypes().add(this.getInteractionResource());
        gestureIREClass.getESuperTypes().add(this.getHapticalInteractionResources());
        touchpadEClass.getESuperTypes().add(this.getHapticalInteractionResources());
        touchscreenEClass.getESuperTypes().add(this.getDisplay());
        touchscreenEClass.getESuperTypes().add(this.getTouchpad());
        deviceEClass.getESuperTypes().add(this.getElementWithPosition());
        physicalDeviceEClass.getESuperTypes().add(this.getDevice());
        physicalDeviceWithProgramEClass.getESuperTypes().add(this.getPhysicalDevice());
        lampEClass.getESuperTypes().add(this.getPhysicalDevice());
        notebookEClass.getESuperTypes().add(this.getPhysicalDevice());
        hoodEClass.getESuperTypes().add(this.getPhysicalDevice());
        pcEClass.getESuperTypes().add(this.getPhysicalDevice());
        fanEClass.getESuperTypes().add(this.getPhysicalDevice());
        remoteControlEClass.getESuperTypes().add(this.getPhysicalDevice());
        tvEClass.getESuperTypes().add(this.getPhysicalDevice());
        ovenEClass.getESuperTypes().add(this.getPhysicalDeviceWithProgram());
        dishwasherEClass.getESuperTypes().add(this.getPhysicalDeviceWithProgram());
        washingMachineEClass.getESuperTypes().add(this.getPhysicalDeviceWithProgram());
        blindEClass.getESuperTypes().add(this.getPhysicalDevice());
        hobEClass.getESuperTypes().add(this.getPhysicalDevice());
        cookerEClass.getESuperTypes().add(this.getPhysicalDevice());
        cookTopEClass.getESuperTypes().add(this.getPhysicalDevice());
        fridgeEClass.getESuperTypes().add(this.getPhysicalDevice());
        mixerEClass.getESuperTypes().add(this.getPhysicalDevice());
        radioEClass.getESuperTypes().add(this.getPhysicalDevice());
        socketEClass.getESuperTypes().add(this.getPhysicalDevice());
        heaterEClass.getESuperTypes().add(this.getPhysicalDevice());
        meterEClass.getESuperTypes().add(this.getPhysicalDevice());
        waterStorageTankEClass.getESuperTypes().add(this.getPhysicalDevice());
        heatingRodEClass.getESuperTypes().add(this.getPhysicalDevice());
        physicalSensorDeviceEClass.getESuperTypes().add(this.getDevice());
        luminanceSensorEClass.getESuperTypes().add(this.getPhysicalSensorDevice());
        temperatureSensorEClass.getESuperTypes().add(this.getPhysicalSensorDevice());
        channelEClass.getESuperTypes().add(this.getEnvironmentElement());
        g1 = createEGenericType(this.getChannel());
        EGenericType g2 = createEGenericType(theUIPackage.getMessageInput());
        g1.getETypeArguments().add(g2);
        g2 = createEGenericType(this.getMessageInputChannelAPI());
        g1.getETypeArguments().add(g2);
        messageInputChannelEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(theUIPackage.getOneDimensional());
        messageInputChannelEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getChannel());
        g2 = createEGenericType(theUIPackage.getMessageOutput());
        g1.getETypeArguments().add(g2);
        g2 = createEGenericType(this.getMessageOutputChannelAPI());
        g1.getETypeArguments().add(g2);
        messageOutputChannelEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(theUIPackage.getOneDimensional());
        messageOutputChannelEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getChannel());
        g2 = createEGenericType(theUIPackage.getPointing());
        g1.getETypeArguments().add(g2);
        g2 = createEGenericType(this.getPointingInputChannelAPI());
        g1.getETypeArguments().add(g2);
        pointingInputChannelEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(theUIPackage.getTwoDimensional());
        pointingInputChannelEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getChannel());
        g2 = createEGenericType(theUIPackage.getGraphicalOutput());
        g1.getETypeArguments().add(g2);
        g2 = createEGenericType(this.getGraphicalOutputChannelAPI());
        g1.getETypeArguments().add(g2);
        graphicalOutputChannelEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(theUIPackage.getTwoDimensional());
        graphicalOutputChannelEClass.getEGenericSuperTypes().add(g1);
        loudspeakerEClass.getESuperTypes().add(this.getOutputInteractionResource());
        microphoneEClass.getESuperTypes().add(this.getInputInteractionResource());
        hapticalInteractionResourcesEClass.getESuperTypes().add(this.getInputInteractionResource());
        deviceToolEClass.getESuperTypes().add(this.getTool());

        // Initialize classes and features; add operations and parameters
        initEClass(environmentEClass, Environment.class, "Environment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEnvironment_Places(), this.getPlace(), this.getPlace_Environment(), "places", null, 0, -1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEnvironment_Providers(), this.getContextProvider(), this.getContextProvider_Environment(), "providers", null, 0, -1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEnvironment_Devices(), this.getDevice(), this.getDevice_Environment(), "devices", null, 0, -1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEnvironment_Users(), this.getUser(), this.getUser_Environment(), "users", null, 0, -1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEnvironment_Assistants(), this.getAssistant(), null, "assistants", null, 0, -1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEnvironment_InitialAssistant(), this.getHomeOSAssistant(), null, "initialAssistant", null, 0, 1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEnvironment_ServiceContainers(), thePropertiesPackage.getServiceContainer(), null, "serviceContainers", null, 0, -1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEnvironment_Meters(), this.getMeter(), null, "meters", null, 0, -1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEnvironment_Discoverers(), thePropertiesPackage.getServiceDiscovererProxy(), null, "discoverers", null, 0, -1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEnvironment_Activities(), this.getActivity(), null, "activities", null, 0, -1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEnvironment_Tools(), this.getTool(), null, "tools", null, 0, -1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEnvironment_Name(), ecorePackage.getEString(), "name", null, 0, 1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEnvironment_Description(), ecorePackage.getEString(), "description", null, 0, 1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        EOperation op = addEOperation(environmentEClass, null, "addUser", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "surname", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "birthdate", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(environmentEClass, null, "removeUser", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getUser(), "user", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(environmentEClass, null, "addServiceContainer", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, thePropertiesPackage.getServiceContainer(), "container", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(environmentEClass, null, "removeServiceContainer", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, thePropertiesPackage.getServiceContainer(), "container", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(environmentEClass, this.getPlaceInfo(), "getPlaceInfos", 0, -1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(environmentEClass, null, "addRoom", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getRoomType(), "roomType", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEIntegerObject(), "floor", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(environmentEClass, null, "addOutdoors", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEIntegerObject(), "floor", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(environmentEClass, null, "removePlace", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getPlace(), "place", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(environmentEClass, null, "addDevice", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getDevice(), "device", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(environmentEClass, null, "removeDevice", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getDevice(), "device", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(environmentEClass, null, "addProvider", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getContextProvider(), "provider", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(environmentEClass, null, "removeProvider", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getContextProvider(), "provider", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(environmentElementEClass, EnvironmentElement.class, "EnvironmentElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEnvironmentElement_Id(), ecorePackage.getEString(), "id", null, 1, 1, EnvironmentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEnvironmentElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, EnvironmentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(interactionResourceEClass, InteractionResource.class, "InteractionResource", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getInteractionResource_User(), this.getUser(), this.getUser_Resources(), "user", null, 0, 1, InteractionResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInteractionResource_Device(), this.getDevice(), this.getDevice_Resources(), "device", null, 1, 1, InteractionResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getInteractionResource_Available(), ecorePackage.getEBoolean(), "available", "false", 1, 1, InteractionResource.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getInteractionResource_Modality(), this.getModality(), "modality", "Graphic", 1, 1, InteractionResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getInteractionResource_Mobile(), ecorePackage.getEBooleanObject(), "mobile", "false", 0, 1, InteractionResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getInteractionResource_Personal(), ecorePackage.getEString(), "personal", "", 0, 1, InteractionResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getInteractionResource_InteractionStatus(), ecorePackage.getEBoolean(), "interactionStatus", null, 0, 1, InteractionResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getInteractionResource_InteractionStatusTimeStamp(), ecorePackage.getELong(), "interactionStatusTimeStamp", null, 0, 1, InteractionResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(interactionResourceEClass, ecorePackage.getEDouble(), "getContextRating", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getUser(), "user", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(interactionResourceEClass, null, "getAllChannels", 0, -1, IS_UNIQUE, IS_ORDERED);
        g1 = createEGenericType(this.getChannel());
        g2 = createEGenericType();
        g1.getETypeArguments().add(g2);
        g2 = createEGenericType();
        g1.getETypeArguments().add(g2);
        initEOperation(op, g1);

        op = addEOperation(interactionResourceEClass, null, "setInteractionStatusWithTimeStamp", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEBoolean(), "status", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(userEClass, User.class, "User", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUser_BirthDate(), ecorePackage.getEString(), "birthDate", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_Surname(), ecorePackage.getEString(), "surname", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_LeftHanded(), ecorePackage.getEBoolean(), "leftHanded", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_Resources(), this.getInteractionResource(), this.getInteractionResource_User(), "resources", null, 0, -1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_LineOfVision(), this.getVector(), null, "lineOfVision", null, 1, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        g1 = createEGenericType(ecorePackage.getEMap());
        g2 = createEGenericType(ecorePackage.getEJavaClass());
        g1.getETypeArguments().add(g2);
        EGenericType g3 = createEGenericType();
        g2.getETypeArguments().add(g3);
        EGenericType g4 = createEGenericType(this.getInteractionResource());
        g3.setEUpperBound(g4);
        g2 = createEGenericType(ecorePackage.getEString());
        g1.getETypeArguments().add(g2);
        initEAttribute(getUser_IrExperience(), g1, "irExperience", null, 0, 1, User.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_FollowMe(), ecorePackage.getEBoolean(), "followMe", "false", 1, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_Environment(), this.getEnvironment(), this.getEnvironment_Users(), "environment", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_CurrentAssistants(), this.getAssistant(), this.getAssistant_CurrentUsers(), "currentAssistants", null, 0, -1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_PastAssistants(), this.getAssistant(), this.getAssistant_PastUsers(), "pastAssistants", null, 0, -1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_InstalledAssistants(), this.getAssistant(), null, "installedAssistants", null, 0, -1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_PasswordHash(), ecorePackage.getEString(), "passwordHash", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(userEClass, this.getInteractionResource(), "getUsableResources", 0, -1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(userEClass, null, "addCurrentAssistant", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getAssistant(), "assistant", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(userEClass, null, "removeCurrentAssistant", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getAssistant(), "assistant", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(userEClass, null, "setNewBirthDate", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "newBirthDate", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(userEClass, ecorePackage.getEDate(), "getBirthDateObject", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(userEClass, ecorePackage.getEBoolean(), "isAdult", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(userEClass, null, "setLeftHanded", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(userEClass, null, "setRightHanded", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(userEClass, null, "installAssistant", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "assistantId", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(userEClass, null, "uninstallAssistant", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "assistantId", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(userEClass, null, "setNewPasswordHash", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "passwordHash", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(assistantEClass, Assistant.class, "Assistant", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAssistant_Activities(), this.getActivity(), null, "activities", null, 0, -1, Assistant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAssistant_CurrentUsers(), this.getUser(), this.getUser_CurrentAssistants(), "currentUsers", null, 0, -1, Assistant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAssistant_PastUsers(), this.getUser(), this.getUser_PastAssistants(), "pastUsers", null, 0, -1, Assistant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAssistant_Description(), ecorePackage.getEString(), "description", null, 0, 1, Assistant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAssistant_Connections(), this.getAssistantConnection(), null, "connections", null, 0, -1, Assistant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAssistant_Domain(), this.getDomain(), "domain", "Other", 0, 1, Assistant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(activityEClass, Activity.class, "Activity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getActivity_Name(), ecorePackage.getEString(), "name", null, 1, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getActivity_Description(), ecorePackage.getEString(), "description", null, 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getActivity_UsedWith(), this.getTool(), null, "usedWith", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getActivity_Configuration(), this.getConfigurationProperty(), null, "configuration", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(homeOSAssistantEClass, HomeOSAssistant.class, "HomeOSAssistant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(thirdPartyAssistantEClass, ThirdPartyAssistant.class, "ThirdPartyAssistant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(localizationTagEClass, LocalizationTag.class, "LocalizationTag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLocalizationTag_Detected(), ecorePackage.getEBoolean(), "detected", "false", 1, 1, LocalizationTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLocalizationTag_Register(), ecorePackage.getEBoolean(), "register", null, 1, 1, LocalizationTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLocalizationTag_Provider(), this.getLocalisationProviderProxy(), this.getLocalisationProviderProxy_Tags(), "provider", null, 1, 1, LocalizationTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLocalizationTag_Element(), this.getElementWithPosition(), this.getElementWithPosition_Tags(), "element", null, 0, 1, LocalizationTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(localizationTagEClass, null, "register", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(localizationTagEClass, null, "unregister", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(elementWithPositionEClass, ElementWithPosition.class, "ElementWithPosition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getElementWithPosition_Tags(), this.getLocalizationTag(), this.getLocalizationTag_Element(), "tags", null, 0, -1, ElementWithPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getElementWithPosition_Position(), this.getVector(), null, "position", null, 0, 1, ElementWithPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getElementWithPosition_PositionTimeStamp(), ecorePackage.getELong(), "positionTimeStamp", null, 0, 1, ElementWithPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getElementWithPosition_Place(), this.getPlace(), this.getPlace_Elements(), "place", null, 0, 1, ElementWithPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(elementWithPositionEClass, null, "setPosition", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "x", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "y", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "z", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(elementWithPositionEClass, this.getEnvironment(), "getEnvironment", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(elementWithPositionEClass, null, "addTag", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getLocalizationTag(), "tag", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(elementWithPositionEClass, null, "removeTag", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getLocalizationTag(), "tag", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(placeEClass, Place.class, "Place", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPlace_Areas(), this.getArea(), null, "areas", null, 1, -1, Place.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPlace_Elements(), this.getElementWithPosition(), this.getElementWithPosition_Place(), "elements", null, 0, -1, Place.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPlace_Environment(), this.getEnvironment(), this.getEnvironment_Places(), "environment", null, 0, 1, Place.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPlace_Doors(), this.getDoor(), this.getDoor_Source(), "doors", null, 0, -1, Place.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPlace_Windows(), this.getWindow(), this.getWindow_Source(), "windows", null, 0, -1, Place.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPlace_Floor(), ecorePackage.getEIntegerObject(), "floor", null, 0, 1, Place.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(placeEClass, ecorePackage.getEBoolean(), "contains", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getVector(), "position", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(placeEClass, this.getInteractionResource(), "getInteractionResources", 0, -1, IS_UNIQUE, IS_ORDERED);

        addEOperation(placeEClass, this.getUser(), "getUsers", 0, -1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(placeEClass, this.getArea(), "addArea", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "originX", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "originY", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "originZ", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "spanX", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "spanY", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "spanZ", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(placeEClass, null, "removeArea", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getArea(), "area", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(placeEClass, null, "addElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getElementWithPosition(), "element", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(placeEClass, null, "removeElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getElementWithPosition(), "element", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(placeEClass, this.getDoor(), "addDoor", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "positionX", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "positionY", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "positionZ", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "spanX", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "spanY", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "spanZ", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(placeEClass, this.getWindow(), "addWindow", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "positionX", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "positionY", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "positionZ", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "spanX", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "spanY", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "spanZ", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(placeEClass, null, "removeDoor", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getDoor(), "door", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(placeEClass, null, "removeWindow", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getWindow(), "window", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(placeEClass, null, "setNewFloor", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEIntegerObject(), "newFloor", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(placeEClass, ecorePackage.getEDoubleObject(), "getTemperatureAverage", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(roomEClass, Room.class, "Room", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRoom_Type(), this.getRoomType(), "type", "0", 0, 1, Room.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(roomEClass, null, "setRoomType", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getRoomType(), "newRoomType", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(outdoorsEClass, Outdoors.class, "Outdoors", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(doorEClass, Door.class, "Door", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDoor_Open(), thePropertiesPackage.getBooleanProperty(), null, "open", null, 0, 1, Door.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDoor_Source(), this.getPlace(), this.getPlace_Doors(), "source", null, 1, 1, Door.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDoor_Target(), this.getPlace(), null, "target", null, 1, 1, Door.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDoor_Span(), this.getVector(), null, "span", null, 1, 1, Door.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(windowEClass, Window.class, "Window", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getWindow_Open(), thePropertiesPackage.getBooleanProperty(), null, "open", null, 0, 1, Window.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getWindow_Source(), this.getPlace(), this.getPlace_Windows(), "source", null, 1, 1, Window.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getWindow_Target(), this.getPlace(), null, "target", null, 1, 1, Window.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getWindow_Span(), this.getVector(), null, "span", null, 1, 1, Window.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(contextProviderEClass, ContextProvider.class, "ContextProvider", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContextProvider_Environment(), this.getEnvironment(), this.getEnvironment_Providers(), "environment", null, 0, 1, ContextProvider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(localisationProviderProxyEClass, LocalisationProviderProxy.class, "LocalisationProviderProxy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLocalisationProviderProxy_ApiClass(), ecorePackage.getEString(), "apiClass", null, 1, 1, LocalisationProviderProxy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLocalisationProviderProxy_Api(), this.getLocalisationProvider(), "api", null, 0, 1, LocalisationProviderProxy.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLocalisationProviderProxy_Tags(), this.getLocalizationTag(), this.getLocalizationTag_Provider(), "tags", null, 0, -1, LocalisationProviderProxy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLocalisationProviderProxy_Configuration(), this.getConfigurationProperty(), null, "configuration", null, 0, -1, LocalisationProviderProxy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(localisationProviderProxyEClass, ecorePackage.getEBoolean(), "register", 0, 1, !IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(localisationProviderProxyEClass, this.getVector(), "getPosition", 0, 1, !IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(localisationProviderProxyEClass, null, "unregister", 0, 1, !IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(localisationProviderProxyEClass, null, "newPosition", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "tagID", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "x", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "y", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "z", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(localisationProviderProxyEClass, null, "tagGone", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "tagID", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(localisationProviderProxyEClass, null, "setApiInstance", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getLocalisationProvider(), "apiInstance", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(localisationProviderProxyEClass, null, "addTag", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getLocalizationTag(), "tag", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(localisationProviderProxyEClass, null, "removeTag", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getLocalizationTag(), "tag", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(mouseEClass, Mouse.class, "Mouse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMouse_MotionSensor(), this.getPointingInputChannel(), null, "motionSensor", null, 0, 1, Mouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMouse_Buttons(), this.getMessageInputChannel(), null, "buttons", null, 0, 1, Mouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(keyboardEClass, Keyboard.class, "Keyboard", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKeyboard_Keys(), this.getMessageInputChannel(), null, "keys", null, 0, 1, Keyboard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(displayEClass, Display.class, "Display", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDisplay_XPixels(), ecorePackage.getEInt(), "xPixels", null, 0, 1, Display.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDisplay_YPixels(), ecorePackage.getEInt(), "yPixels", null, 0, 1, Display.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDisplay_Direction(), this.getVector(), null, "direction", null, 0, 1, Display.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDisplay_Screen(), this.getGraphicalOutputChannel(), null, "screen", null, 0, 1, Display.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDisplay_Size(), ecorePackage.getEInt(), "size", null, 0, 1, Display.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(inputInteractionResourceEClass, InputInteractionResource.class, "InputInteractionResource", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(outputInteractionResourceEClass, OutputInteractionResource.class, "OutputInteractionResource", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(gestureIREClass, GestureIR.class, "GestureIR", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGestureIR_GestureRecognition(), this.getMessageInputChannel(), null, "gestureRecognition", null, 0, 1, GestureIR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(touchpadEClass, Touchpad.class, "Touchpad", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(touchscreenEClass, Touchscreen.class, "Touchscreen", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTouchscreen_TouchSurface(), this.getPointingInputChannel(), null, "touchSurface", null, 0, 1, Touchscreen.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTouchscreen_GestureRecognition(), this.getMessageInputChannel(), null, "gestureRecognition", null, 0, 1, Touchscreen.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(deviceEClass, Device.class, "Device", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDevice_Resources(), this.getInteractionResource(), this.getInteractionResource_Device(), "resources", null, 0, -1, Device.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDevice_Mobile(), ecorePackage.getEBoolean(), "mobile", "false", 0, 1, Device.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDevice_Environment(), this.getEnvironment(), this.getEnvironment_Devices(), "environment", null, 0, 1, Device.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDevice_Manufacturer(), ecorePackage.getEString(), "manufacturer", null, 0, 1, Device.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDevice_ModelName(), ecorePackage.getEString(), "modelName", null, 0, 1, Device.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(deviceEClass, null, "addInteractionResource", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getInteractionResource(), "interactionResource", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(deviceEClass, null, "removeInteractionResource", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getInteractionResource(), "interactionResource", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(physicalDeviceEClass, PhysicalDevice.class, "PhysicalDevice", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPhysicalDevice_On(), thePropertiesPackage.getBooleanProperty(), null, "on", null, 0, 1, PhysicalDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPhysicalDevice_PowerUsage(), thePropertiesPackage.getDoubleProperty(), null, "powerUsage", null, 0, 1, PhysicalDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPhysicalDevice_SubDevice(), this.getPhysicalDevice(), this.getPhysicalDevice_ParentDevice(), "subDevice", null, 0, -1, PhysicalDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPhysicalDevice_ParentDevice(), this.getPhysicalDevice(), this.getPhysicalDevice_SubDevice(), "parentDevice", null, 0, 1, PhysicalDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(physicalDeviceEClass, null, "turnOn", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEException(op, thePropertiesPackage.getActorServiceCallException());

        op = addEOperation(physicalDeviceEClass, null, "turnOff", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEException(op, thePropertiesPackage.getActorServiceCallException());

        addEOperation(physicalDeviceEClass, ecorePackage.getEBoolean(), "getOnValue", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(physicalDeviceEClass, null, "addSubDevice", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getPhysicalDevice(), "subDevice", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(physicalDeviceEClass, null, "removeSubDevice", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getPhysicalDevice(), "subDevice", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(physicalDeviceWithProgramEClass, PhysicalDeviceWithProgram.class, "PhysicalDeviceWithProgram", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPhysicalDeviceWithProgram_Program(), thePropertiesPackage.getTextProperty(), null, "program", null, 0, 1, PhysicalDeviceWithProgram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPhysicalDeviceWithProgram_SecondsRemaining(), thePropertiesPackage.getIntegerProperty(), null, "secondsRemaining", null, 0, 1, PhysicalDeviceWithProgram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(lampEClass, Lamp.class, "Lamp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLamp_DimmingLevel(), thePropertiesPackage.getIntegerProperty(), null, "dimmingLevel", null, 0, 1, Lamp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLamp_LampType(), this.getLampType(), "lampType", null, 0, 1, Lamp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(lampEClass, null, "setNewLampeType", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getLampType(), "lampType", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(notebookEClass, Notebook.class, "Notebook", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(hoodEClass, Hood.class, "Hood", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(pcEClass, org.sercho.masp.models.Context.PC.class, "PC", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(fanEClass, Fan.class, "Fan", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFan_Speed(), thePropertiesPackage.getIntegerProperty(), null, "speed", null, 0, 1, Fan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(remoteControlEClass, RemoteControl.class, "RemoteControl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(tvEClass, org.sercho.masp.models.Context.TV.class, "TV", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTV_CurrentProgram(), thePropertiesPackage.getIntegerProperty(), null, "currentProgram", null, 0, 1, org.sercho.masp.models.Context.TV.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ovenEClass, Oven.class, "Oven", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getOven_Temperature(), thePropertiesPackage.getIntegerProperty(), null, "temperature", null, 0, 1, Oven.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dishwasherEClass, Dishwasher.class, "Dishwasher", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(washingMachineEClass, WashingMachine.class, "WashingMachine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(blindEClass, Blind.class, "Blind", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBlind_Level(), thePropertiesPackage.getIntegerProperty(), null, "level", null, 0, 1, Blind.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(hobEClass, Hob.class, "Hob", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getHob_HeatLevel(), thePropertiesPackage.getIntegerProperty(), null, "heatLevel", null, 0, 1, Hob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(cookerEClass, Cooker.class, "Cooker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(cookTopEClass, CookTop.class, "CookTop", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(fridgeEClass, Fridge.class, "Fridge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFridge_Temperature(), thePropertiesPackage.getIntegerProperty(), null, "temperature", null, 0, 1, Fridge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mixerEClass, Mixer.class, "Mixer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(radioEClass, Radio.class, "Radio", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(socketEClass, Socket.class, "Socket", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(heaterEClass, Heater.class, "Heater", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getHeater_TemperatureDemand(), thePropertiesPackage.getDoubleProperty(), null, "temperatureDemand", null, 0, 1, Heater.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getHeater_TemperatureCurrent(), thePropertiesPackage.getDoubleProperty(), null, "temperatureCurrent", null, 0, 1, Heater.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getHeater_ValvePosition(), thePropertiesPackage.getDoubleProperty(), null, "valvePosition", null, 0, 1, Heater.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(meterEClass, Meter.class, "Meter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMeter_Readings(), this.getMeterReading(), null, "readings", null, 0, -1, Meter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(waterStorageTankEClass, WaterStorageTank.class, "WaterStorageTank", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getWaterStorageTank_TemperatureSensor(), this.getTemperatureSensor(), null, "temperatureSensor", null, 0, 1, WaterStorageTank.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getWaterStorageTank_Capacity(), ecorePackage.getEInt(), "capacity", null, 0, 1, WaterStorageTank.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(heatingRodEClass, HeatingRod.class, "HeatingRod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getHeatingRod_MaximumPowerWatts(), ecorePackage.getEInt(), "maximumPowerWatts", null, 1, 1, HeatingRod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(meterReadingEClass, MeterReading.class, "MeterReading", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMeterReading_Value(), thePropertiesPackage.getDoubleProperty(), null, "value", null, 1, 1, MeterReading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMeterReading_Subject(), this.getReadingSubject(), "subject", null, 0, 1, MeterReading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMeterReading_Measurand(), this.getReadingMeasurand(), "measurand", null, 0, 1, MeterReading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMeterReading_MeasurementMethod(), this.getReadingMeasurementMethod(), "measurementMethod", null, 0, 1, MeterReading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMeterReading_Rate(), this.getReadingRate(), "rate", null, 0, 1, MeterReading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMeterReading_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, MeterReading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(physicalSensorDeviceEClass, PhysicalSensorDevice.class, "PhysicalSensorDevice", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPhysicalSensorDevice_Value(), thePropertiesPackage.getDoubleProperty(), null, "value", null, 0, 1, PhysicalSensorDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(luminanceSensorEClass, LuminanceSensor.class, "LuminanceSensor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(temperatureSensorEClass, TemperatureSensor.class, "TemperatureSensor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(channelEClass, Channel.class, "Channel", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getChannel_DistributionState(), this.getDistributionState(), "distributionState", null, 1, 1, Channel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        g1 = createEGenericType(channelEClass_C);
        initEReference(getChannel_Elements(), g1, null, "elements", null, 0, -1, Channel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        g1 = createEGenericType(channelEClass_A);
        initEAttribute(getChannel_Api(), g1, "api", null, 0, 1, Channel.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getChannel_Configuration(), this.getConfigurationProperty(), null, "configuration", null, 0, -1, Channel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getChannel_Available(), ecorePackage.getEBoolean(), "available", null, 0, 1, Channel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getChannel_ApiClass(), ecorePackage.getEString(), "apiClass", null, 0, 1, Channel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(channelEClass, null, "setNewDistributionState", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getDistributionState(), "newDistributionState", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(channelEClass, null, "add", 1, 1, IS_UNIQUE, IS_ORDERED);
        g1 = createEGenericType(channelEClass_C);
        addEParameter(op, g1, "element", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(channelEClass, null, "remove", 1, 1, IS_UNIQUE, IS_ORDERED);
        g1 = createEGenericType(channelEClass_C);
        addEParameter(op, g1, "elementID", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(channelEClass, null, "setNewAvailable", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEBoolean(), "newAvailable", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(channelEClass, null, "setApiInstance", 0, 1, IS_UNIQUE, IS_ORDERED);
        g1 = createEGenericType(channelEClass_A);
        addEParameter(op, g1, "newAPI", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(channelEClass, this.getInteractionResource(), "getInteractionResource", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(channelEClass, null, "interactionRequested", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(channelEClass, null, "interactionFinished", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(messageInputChannelEClass, MessageInputChannel.class, "MessageInputChannel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(messageOutputChannelEClass, MessageOutputChannel.class, "MessageOutputChannel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(pointingInputChannelEClass, PointingInputChannel.class, "PointingInputChannel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(graphicalOutputChannelEClass, GraphicalOutputChannel.class, "GraphicalOutputChannel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(loudspeakerEClass, Loudspeaker.class, "Loudspeaker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLoudspeaker_Voice(), this.getMessageOutputChannel(), null, "voice", null, 0, 1, Loudspeaker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(microphoneEClass, Microphone.class, "Microphone", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMicrophone_VoiceRecognition(), this.getMessageInputChannel(), null, "voiceRecognition", null, 0, 1, Microphone.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(vectorEClass, Vector.class, "Vector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getVector_X(), ecorePackage.getEDouble(), "x", "-1.0", 0, 1, Vector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVector_Y(), ecorePackage.getEDouble(), "y", "-1.0", 0, 1, Vector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVector_Z(), ecorePackage.getEDouble(), "z", "-1.0", 0, 1, Vector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(vectorEClass, ecorePackage.getEDouble(), "distance", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getVector(), "vector", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(vectorEClass, null, "setCoordinates", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "newX", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "newY", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "newZ", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(vectorEClass, this.getVector(), "copy", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(areaEClass, Area.class, "Area", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getArea_Origin(), this.getVector(), null, "origin", null, 1, 1, Area.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getArea_Span(), this.getVector(), null, "span", null, 1, 1, Area.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(areaEClass, null, "setNewOrigin", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "originX", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "originY", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "originZ", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(areaEClass, null, "setNewSpan", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "spanX", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "spanY", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDouble(), "spanZ", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(areaEClass, ecorePackage.getEBoolean(), "contains", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getVector(), "position", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(hapticalInteractionResourcesEClass, HapticalInteractionResources.class, "HapticalInteractionResources", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(configurationPropertyEClass, ConfigurationProperty.class, "ConfigurationProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getConfigurationProperty_Key(), ecorePackage.getEString(), "key", null, 0, 1, ConfigurationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getConfigurationProperty_Value(), ecorePackage.getEString(), "value", null, 0, 1, ConfigurationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(assistantConnectionEClass, AssistantConnection.class, "AssistantConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAssistantConnection_Description(), ecorePackage.getEString(), "description", null, 0, 1, AssistantConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAssistantConnection_ConnectedAssistant(), this.getAssistant(), null, "connectedAssistant", null, 1, 1, AssistantConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(toolEClass, Tool.class, "Tool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTool_Name(), ecorePackage.getEString(), "name", null, 1, 1, Tool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTool_Description(), ecorePackage.getEString(), "description", null, 0, 1, Tool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(deviceToolEClass, DeviceTool.class, "DeviceTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        g1 = createEGenericType(thePropertiesPackage.getProperty());
        g2 = createEGenericType();
        g1.getETypeArguments().add(g2);
        initEReference(getDeviceTool_Controls(), g1, null, "controls", null, 0, -1, DeviceTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDeviceTool_AppliesTo(), this.getPhysicalDevice(), null, "appliesTo", null, 1, 1, DeviceTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(needEClass, Need.class, "Need", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNeed_FulfilledBy(), this.getActivity(), null, "fulfilledBy", null, 0, -1, Need.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNeed_Name(), ecorePackage.getEString(), "name", null, 1, 1, Need.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNeed_Description(), ecorePackage.getEString(), "description", null, 0, 1, Need.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNeed_Configuration(), this.getConfigurationProperty(), null, "configuration", null, 0, 1, Need.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(roomTypeEEnum, RoomType.class, "RoomType");
        addEEnumLiteral(roomTypeEEnum, RoomType.KITCHEN);
        addEEnumLiteral(roomTypeEEnum, RoomType.BATH);
        addEEnumLiteral(roomTypeEEnum, RoomType.BEDROOM);
        addEEnumLiteral(roomTypeEEnum, RoomType.LIVING_ROOM);
        addEEnumLiteral(roomTypeEEnum, RoomType.FITNESS_ROOM);
        addEEnumLiteral(roomTypeEEnum, RoomType.OFFICE);

        initEEnum(readingSubjectEEnum, ReadingSubject.class, "ReadingSubject");
        addEEnumLiteral(readingSubjectEEnum, ReadingSubject.ABSTRACT_OBJECT);
        addEEnumLiteral(readingSubjectEEnum, ReadingSubject.ELECTRICITY);
        addEEnumLiteral(readingSubjectEEnum, ReadingSubject.HEAT_COST_ALLOCATION);
        addEEnumLiteral(readingSubjectEEnum, ReadingSubject.CHILLNESS);
        addEEnumLiteral(readingSubjectEEnum, ReadingSubject.HEAT);
        addEEnumLiteral(readingSubjectEEnum, ReadingSubject.GAS);
        addEEnumLiteral(readingSubjectEEnum, ReadingSubject.WATER_COLD);
        addEEnumLiteral(readingSubjectEEnum, ReadingSubject.WATER_HOT);

        initEEnum(readingMeasurandEEnum, ReadingMeasurand.class, "ReadingMeasurand");
        addEEnumLiteral(readingMeasurandEEnum, ReadingMeasurand.CONSUMPTION);
        addEEnumLiteral(readingMeasurandEEnum, ReadingMeasurand.ACTIVE_POWER);

        initEEnum(readingMeasurementMethodEEnum, ReadingMeasurementMethod.class, "ReadingMeasurementMethod");
        addEEnumLiteral(readingMeasurementMethodEEnum, ReadingMeasurementMethod.INSTANTANEOUS_VALUE);
        addEEnumLiteral(readingMeasurementMethodEEnum, ReadingMeasurementMethod.METER_READING);

        initEEnum(readingRateEEnum, ReadingRate.class, "ReadingRate");
        addEEnumLiteral(readingRateEEnum, ReadingRate.TOTAL);
        addEEnumLiteral(readingRateEEnum, ReadingRate.RATE1);
        addEEnumLiteral(readingRateEEnum, ReadingRate.RATE2);

        initEEnum(distributionStateEEnum, DistributionState.class, "DistributionState");
        addEEnumLiteral(distributionStateEEnum, DistributionState.CALCULATING);
        addEEnumLiteral(distributionStateEEnum, DistributionState.DONE);

        initEEnum(modalityEEnum, Modality.class, "Modality");
        addEEnumLiteral(modalityEEnum, Modality.VOCAL);
        addEEnumLiteral(modalityEEnum, Modality.GRAPHIC);
        addEEnumLiteral(modalityEEnum, Modality.HAPTIC);
        addEEnumLiteral(modalityEEnum, Modality.AUDIO);

        initEEnum(domainEEnum, Domain.class, "Domain");
        addEEnumLiteral(domainEEnum, Domain.OTHER);
        addEEnumLiteral(domainEEnum, Domain.LIVING);
        addEEnumLiteral(domainEEnum, Domain.COMFORT);
        addEEnumLiteral(domainEEnum, Domain.ENERGY);

        initEEnum(lampTypeEEnum, LampType.class, "LampType");
        addEEnumLiteral(lampTypeEEnum, LampType.UNSPECIFIED);
        addEEnumLiteral(lampTypeEEnum, LampType.CEILING_LAMP);
        addEEnumLiteral(lampTypeEEnum, LampType.FLOOR_LAMP);
        addEEnumLiteral(lampTypeEEnum, LampType.TABLE_LAMP);
        addEEnumLiteral(lampTypeEEnum, LampType.WALL_LAMP);
        addEEnumLiteral(lampTypeEEnum, LampType.HANGING_LAMP);
        addEEnumLiteral(lampTypeEEnum, LampType.DEVICE_LAMP);

        // Initialize data types
        initEDataType(channelAPIEDataType, ChannelAPI.class, "ChannelAPI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(messageInputChannelAPIEDataType, MessageInputChannelAPI.class, "MessageInputChannelAPI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(pointingInputChannelAPIEDataType, PointingInputChannelAPI.class, "PointingInputChannelAPI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(messageOutputChannelAPIEDataType, MessageOutputChannelAPI.class, "MessageOutputChannelAPI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(graphicalOutputChannelAPIEDataType, GraphicalOutputChannelAPI.class, "GraphicalOutputChannelAPI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(localisationProviderEDataType, LocalisationProvider.class, "LocalisationProvider", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(placeInfoEDataType, PlaceInfo.class, "PlaceInfo", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http://www.eclipse.org/emf/2002/GenModel
        createGenModelAnnotations();
        // Definition
        createDefinitionAnnotations();
        // Situation
        createSituationAnnotations();
        // Proxy
        createProxyAnnotations();
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/emf/2002/GenModel</b>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createGenModelAnnotations() {
        String source = "http://www.eclipse.org/emf/2002/GenModel";
        addAnnotation(this, source, new String[]{
                "documentation",
                "The Context package enables the definition of runtime context models. The root element of a context model is the Environment."});
        addAnnotation(environmentEClass, source, new String[]{
                "documentation",
                "The Environment element is the root element of the context model. It described the context as the combination of places, users and devices."});
        addAnnotation(environmentEClass.getEOperations().get(2), source, new String[]{
                "documentation",
                "Method is used to add ServiceContainers to the Environment. If the ServiceContainer has no id the id argument must not be null."});
        addAnnotation(environmentEClass.getEOperations().get(8), source, new String[]{
                "documentation",
                "The id must be set only if the id of the device is <code>null</code>."});
        addAnnotation(getEnvironment_Places(), source, new String[]{"documentation",
                "Places in the environment."});
        addAnnotation(getEnvironment_Devices(), source, new String[]{"documentation",
                "Devices in the environment."});
        addAnnotation(getEnvironment_Users(), source, new String[]{"documentation",
                "Users in the environment."});
        addAnnotation(getEnvironment_Discoverers(), source, new String[]{
                "documentation",
                "Service discovery proxies are used to discover new actors and sensors available in the environment. Each proxy must be connected to a service container in which it will place the discovered services."});
        addAnnotation(getEnvironment_Name(), source, new String[]{"documentation",
                "informative name of this environment"});
        addAnnotation(getEnvironment_Description(), source, new String[]{"documentation",
                "informative description of this environment"});
        addAnnotation(environmentElementEClass, source, new String[]{"documentation",
                "EnvironmentElement represents an entity present in the environment."});
        addAnnotation(userEClass, source, new String[]{
                "documentation",
                "User elements represent users in the environment. Each user has a position, is in a place and has a set of properties, e.g. birth date."});
        addAnnotation(getUser_BirthDate(), source, new String[]{"documentation",
                "birthdate of the user"});
        addAnnotation(getUser_Surname(), source, new String[]{"documentation",
                "surname of the user"});
        addAnnotation(getUser_LeftHanded(), source, new String[]{"documentation",
                "true if the user is left-handed, false otherwise"});
        addAnnotation(activityEClass, source, new String[]{"documentation",
                "Represents a short task that is (or must be) executed by a user"});
        addAnnotation(localizationTagEClass, source, new String[]{
                "documentation",
                "LocalizationTag represents a physical tag, which can be attached to environment elements and enables the localization of this element in the environment."});
        addAnnotation(elementWithPositionEClass, source, new String[]{"documentation",
                "ElementWithPosition represents environment entities with a position and a place."});
        addAnnotation(placeEClass.getEOperations().get(3), source, new String[]{
                "documentation",
                "Adds a Area to the Place. The source and span are created by the given parameters."});
        addAnnotation(placeEClass.getEOperations().get(7), source, new String[]{
                "documentation",
                "Adds a Door to the Place. The source and span are created by the given parameters."});
        addAnnotation(placeEClass.getEOperations().get(8), source, new String[]{
                "documentation",
                "Adds a Window to the Place. The source and span are created by the given parameters."});
        addAnnotation(localisationProviderProxyEClass.getEOperations().get(6), source, new String[]{
                "documentation",
                "Method is used to add LocalizationTags to the LocalisationProviderProxy. If the LocalizationTag has no id the id argument must not be null."});
        addAnnotation(deviceEClass.getEOperations().get(0), source, new String[]{
                "documentation",
                "The id must be set only if the id of the interactionResource is <code>null</code>."});
        addAnnotation(physicalDeviceEClass.getEOperations().get(0), source, new String[]{
                "documentation",
                "Attempts to set the value \'true\' to the \'on\' property of this device. The ActorServiceCallException is thrown if the \'on\' property is not set, its actor is not set or the actor throws an exception."});
        addAnnotation(physicalDeviceEClass.getEOperations().get(1), source, new String[]{
                "documentation",
                "Attempts to set the value \'false\' to the \'on\' property of this device. The ActorServiceCallException is thrown if the \'on\' property is not set, its actor is not set or the actor throws an exception."});
        addAnnotation(physicalDeviceEClass.getEOperations().get(3), source, new String[]{
                "documentation",
                "The id must be set only if the id of the subDevice is <code>null</code>."});
        addAnnotation(socketEClass, source, new String[]{
                "documentation",
                "A power socket device, which should only be used if the connected physical device is unknown."});
        addAnnotation(meterReadingEClass, source, new String[]{
                "documentation",
                "A MeterReading describes a reading of meter data of a specific type, described by the attributes of the MeterReading."});
        addAnnotation(getMeterReading_Identifier(), source, new String[]{"documentation",
                "identifier of this reading, must be unique at least within the Meter."});
        addAnnotation(vectorEClass, source, new String[]{"documentation", "3D vector"});
        addAnnotation(vectorEClass.getEOperations().get(0), source, new String[]{
                "documentation",
                "calculates the distance to a point described by another vector"});
        addAnnotation(vectorEClass.getEOperations().get(1), source, new String[]{
                "documentation", "updates the cooridnates of this vector"});
        addAnnotation(vectorEClass.getEOperations().get(2), source, new String[]{
                "documentation", "copies this vector"});
        addAnnotation(getVector_X(), source, new String[]{"documentation", "X coordinate"});
        addAnnotation(getVector_Y(), source, new String[]{"documentation", "Y coordinate"});
        addAnnotation(getVector_Z(), source, new String[]{"documentation", "Z coordinate"});
        addAnnotation(toolEClass, source, new String[]{"documentation",
                "A tool that supports the progress of an Activity"});
        addAnnotation(deviceToolEClass, source, new String[]{"documentation",
                "A special device tool that requires device-funtions (Property) to support an Activity"});
        addAnnotation(needEClass, source, new String[]{"documentation",
                "A need states the requirement of an execution of a special Activity"});
    }

    /**
     * Initializes the annotations for <b>Situation</b>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createSituationAnnotations() {
        String source = "Situation";
        addAnnotation(environmentEClass.getEOperations().get(4), source, new String[]{});
        addAnnotation(getInteractionResource_Available(), source, new String[]{});
        addAnnotation(userEClass.getEOperations().get(1), source, new String[]{
                "modifies", "currentAssistants,pastAssistants"});
        addAnnotation(userEClass.getEOperations().get(2), source, new String[]{
                "modifies", "currentAssistants,pastAssistants"});
        addAnnotation(userEClass.getEOperations().get(5), source, new String[]{});
        addAnnotation(userEClass.getEOperations().get(8), source, new String[]{
                "modifies", "installedAssistants"});
        addAnnotation(userEClass.getEOperations().get(9), source, new String[]{
                "modifies", "installedAssistants"});
        addAnnotation(getUser_LineOfVision(), source, new String[]{});
        addAnnotation(getUser_FollowMe(), source, new String[]{});
        addAnnotation(getUser_CurrentAssistants(), source, new String[]{});
        addAnnotation(getUser_PastAssistants(), source, new String[]{});
        addAnnotation(getUser_InstalledAssistants(), source, new String[]{});
        addAnnotation(getAssistant_CurrentUsers(), source, new String[]{});
        addAnnotation(getAssistant_PastUsers(), source, new String[]{});
        addAnnotation(localizationTagEClass.getEOperations().get(0), source, new String[]{});
        addAnnotation(localizationTagEClass.getEOperations().get(1), source, new String[]{});
        addAnnotation(getLocalizationTag_Detected(), source, new String[]{});
        addAnnotation(getLocalizationTag_Register(), source, new String[]{});
        addAnnotation(elementWithPositionEClass.getEOperations().get(0), source, new String[]{});
        addAnnotation(getElementWithPosition_Position(), source, new String[]{});
        addAnnotation(getElementWithPosition_PositionTimeStamp(), source, new String[]{});
        addAnnotation(placeEClass.getEOperations().get(12), source, new String[]{});
        addAnnotation(localisationProviderProxyEClass.getEOperations().get(0), source, new String[]{});
        addAnnotation(localisationProviderProxyEClass.getEOperations().get(1), source, new String[]{});
        addAnnotation(localisationProviderProxyEClass.getEOperations().get(2), source, new String[]{});
        addAnnotation(localisationProviderProxyEClass.getEOperations().get(3), source, new String[]{});
        addAnnotation(localisationProviderProxyEClass.getEOperations().get(4), source, new String[]{});
        addAnnotation(localisationProviderProxyEClass.getEOperations().get(5), source, new String[]{
                "modifies", "api"});
        addAnnotation(getLocalisationProviderProxy_Api(), source, new String[]{});
        addAnnotation(physicalDeviceEClass.getEOperations().get(0), source, new String[]{});
        addAnnotation(physicalDeviceEClass.getEOperations().get(1), source, new String[]{});
        addAnnotation(physicalDeviceEClass.getEOperations().get(2), source, new String[]{});
        addAnnotation(channelEClass.getEOperations().get(3), source, new String[]{});
        addAnnotation(channelEClass.getEOperations().get(4), source, new String[]{
                "modifies", "api"});
        addAnnotation(channelEClass.getEOperations().get(6), source, new String[]{});
        addAnnotation(channelEClass.getEOperations().get(7), source, new String[]{});
        addAnnotation(getChannel_DistributionState(), source, new String[]{});
        addAnnotation(getChannel_Elements(), source, new String[]{});
        addAnnotation(getChannel_Api(), source, new String[]{});
        addAnnotation(getChannel_Available(), source, new String[]{});
    }

    /**
     * Initializes the annotations for <b>Definition</b>. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createDefinitionAnnotations() {
        String source = "Definition";
        addAnnotation(environmentEClass.getEOperations().get(0), source, new String[]{
                "modifies", "users"});
        addAnnotation(environmentEClass.getEOperations().get(1), source, new String[]{
                "modifies", "users"});
        addAnnotation(environmentEClass.getEOperations().get(2), source, new String[]{
                "modifies", "serviceContainers"});
        addAnnotation(environmentEClass.getEOperations().get(3), source, new String[]{
                "modifies", "serviceContainers"});
        addAnnotation(environmentEClass.getEOperations().get(5), source, new String[]{
                "modifies", "places"});
        addAnnotation(environmentEClass.getEOperations().get(6), source, new String[]{
                "modifies", "places"});
        addAnnotation(environmentEClass.getEOperations().get(7), source, new String[]{
                "modifies", "places"});
        addAnnotation(environmentEClass.getEOperations().get(8), source, new String[]{
                "modifies", "devices"});
        addAnnotation(environmentEClass.getEOperations().get(9), source, new String[]{
                "modifies", "devices"});
        addAnnotation(environmentEClass.getEOperations().get(10), source, new String[]{
                "modifies", "providers"});
        addAnnotation(environmentEClass.getEOperations().get(11), source, new String[]{
                "modifies", "providers"});
        addAnnotation(userEClass.getEOperations().get(3), source, new String[]{
                "modifies", "birthDate"});
        addAnnotation(userEClass.getEOperations().get(6), source, new String[]{
                "modifies", "leftHanded"});
        addAnnotation(userEClass.getEOperations().get(7), source, new String[]{
                "modifies", "leftHanded"});
        addAnnotation(userEClass.getEOperations().get(10), source, new String[]{
                "modifies", "passwordHash"});
        addAnnotation(elementWithPositionEClass.getEOperations().get(2), source, new String[]{
                "modifies", "tags"});
        addAnnotation(elementWithPositionEClass.getEOperations().get(3), source, new String[]{
                "modifies", "tags"});
        addAnnotation(placeEClass.getEOperations().get(3), source, new String[]{
                "modifies", "areas"});
        addAnnotation(placeEClass.getEOperations().get(4), source, new String[]{
                "modifies", "areas"});
        addAnnotation(placeEClass.getEOperations().get(5), source, new String[]{
                "modifies", "elements"});
        addAnnotation(placeEClass.getEOperations().get(6), source, new String[]{
                "modifies", "elements"});
        addAnnotation(placeEClass.getEOperations().get(7), source, new String[]{
                "modifies", "doors"});
        addAnnotation(placeEClass.getEOperations().get(8), source, new String[]{
                "modifies", "windows"});
        addAnnotation(placeEClass.getEOperations().get(9), source, new String[]{
                "modifies", "doors"});
        addAnnotation(placeEClass.getEOperations().get(10), source, new String[]{
                "modifies", "windows"});
        addAnnotation(placeEClass.getEOperations().get(11), source, new String[]{
                "modifies", "floor"});
        addAnnotation(roomEClass.getEOperations().get(0), source, new String[]{
                "modifies", "type"});
        addAnnotation(localisationProviderProxyEClass.getEOperations().get(6), source, new String[]{
                "modifies", "tags"});
        addAnnotation(localisationProviderProxyEClass.getEOperations().get(7), source, new String[]{
                "modifies", "tags"});
        addAnnotation(deviceEClass.getEOperations().get(0), source, new String[]{
                "modifies", "resources"});
        addAnnotation(deviceEClass.getEOperations().get(1), source, new String[]{
                "modifies", "resources"});
        addAnnotation(physicalDeviceEClass.getEOperations().get(3), source, new String[]{
                "modifies", "subDevice"});
        addAnnotation(physicalDeviceEClass.getEOperations().get(4), source, new String[]{
                "modifies", "subDevice"});
        addAnnotation(lampEClass.getEOperations().get(0), source, new String[]{
                "modifies", "lampType"});
        addAnnotation(vectorEClass.getEOperations().get(0), source, new String[]{});
        addAnnotation(vectorEClass.getEOperations().get(1), source, new String[]{
                "modifies", "x,y,z"});
        addAnnotation(areaEClass.getEOperations().get(0), source, new String[]{
                "modifies", "origin"});
        addAnnotation(areaEClass.getEOperations().get(1), source, new String[]{
                "modifies", "span"});
    }

    /**
     * Initializes the annotations for <b>Proxy</b>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createProxyAnnotations() {
        String source = "Proxy";
        addAnnotation(localisationProviderProxyEClass, source, new String[]{
                "proxyReference", "api", "proxyClassName", "apiClass", "proxySetter",
                "setApiInstance", "proxyConfiguration", "configuration", "callbacks",
                "newPosition,tagGone"});
        addAnnotation(channelEClass, source, new String[]{"proxyReference", "api",
                "proxyClassName", "apiClass", "proxyConfiguration", "configuration",
                "proxySetter", "setApiInstance", "callbacks",
                "setNewAvailable,interactionRequested,interactionFinished"});
    }

} // ContextPackageImpl
