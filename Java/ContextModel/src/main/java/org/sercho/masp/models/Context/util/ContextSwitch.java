/**
 * Copyright (c) 2009 DAI-Labor.
 * All rights reserved.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Grzegorz Lehmann (DAI-Labor) - Initial API and implementation
 */
package org.sercho.masp.models.Context.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
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
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * 
 * @see org.sercho.masp.models.Context.ContextPackage
 * @generated
 */
public class ContextSwitch<T> {

    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static ContextPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ContextSwitch() {
        if(modelPackage == null) {
            modelPackage = ContextPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if(theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject)
                    : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch(classifierID) {
            case ContextPackage.ENVIRONMENT: {
                Environment environment = (Environment)theEObject;
                T result = caseEnvironment(environment);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.ENVIRONMENT_ELEMENT: {
                EnvironmentElement environmentElement = (EnvironmentElement)theEObject;
                T result = caseEnvironmentElement(environmentElement);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.INTERACTION_RESOURCE: {
                InteractionResource interactionResource = (InteractionResource)theEObject;
                T result = caseInteractionResource(interactionResource);
                if(result == null)
                    result = caseElementWithPosition(interactionResource);
                if(result == null)
                    result = caseEnvironmentElement(interactionResource);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.USER: {
                User user = (User)theEObject;
                T result = caseUser(user);
                if(result == null)
                    result = caseElementWithPosition(user);
                if(result == null)
                    result = caseEnvironmentElement(user);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.ASSISTANT: {
                Assistant assistant = (Assistant)theEObject;
                T result = caseAssistant(assistant);
                if(result == null)
                    result = caseEnvironmentElement(assistant);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.ACTIVITY: {
                Activity activity = (Activity)theEObject;
                T result = caseActivity(activity);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.HOME_OS_ASSISTANT: {
                HomeOSAssistant homeOSAssistant = (HomeOSAssistant)theEObject;
                T result = caseHomeOSAssistant(homeOSAssistant);
                if(result == null)
                    result = caseAssistant(homeOSAssistant);
                if(result == null)
                    result = caseEnvironmentElement(homeOSAssistant);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.THIRD_PARTY_ASSISTANT: {
                ThirdPartyAssistant thirdPartyAssistant = (ThirdPartyAssistant)theEObject;
                T result = caseThirdPartyAssistant(thirdPartyAssistant);
                if(result == null)
                    result = caseAssistant(thirdPartyAssistant);
                if(result == null)
                    result = caseEnvironmentElement(thirdPartyAssistant);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.LOCALIZATION_TAG: {
                LocalizationTag localizationTag = (LocalizationTag)theEObject;
                T result = caseLocalizationTag(localizationTag);
                if(result == null)
                    result = caseElementWithPosition(localizationTag);
                if(result == null)
                    result = caseEnvironmentElement(localizationTag);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.ELEMENT_WITH_POSITION: {
                ElementWithPosition elementWithPosition = (ElementWithPosition)theEObject;
                T result = caseElementWithPosition(elementWithPosition);
                if(result == null)
                    result = caseEnvironmentElement(elementWithPosition);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.PLACE: {
                Place place = (Place)theEObject;
                T result = casePlace(place);
                if(result == null)
                    result = caseEnvironmentElement(place);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.ROOM: {
                Room room = (Room)theEObject;
                T result = caseRoom(room);
                if(result == null)
                    result = casePlace(room);
                if(result == null)
                    result = caseEnvironmentElement(room);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.OUTDOORS: {
                Outdoors outdoors = (Outdoors)theEObject;
                T result = caseOutdoors(outdoors);
                if(result == null)
                    result = casePlace(outdoors);
                if(result == null)
                    result = caseEnvironmentElement(outdoors);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.DOOR: {
                Door door = (Door)theEObject;
                T result = caseDoor(door);
                if(result == null)
                    result = caseElementWithPosition(door);
                if(result == null)
                    result = caseEnvironmentElement(door);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.WINDOW: {
                Window window = (Window)theEObject;
                T result = caseWindow(window);
                if(result == null)
                    result = caseElementWithPosition(window);
                if(result == null)
                    result = caseEnvironmentElement(window);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.CONTEXT_PROVIDER: {
                ContextProvider contextProvider = (ContextProvider)theEObject;
                T result = caseContextProvider(contextProvider);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.LOCALISATION_PROVIDER_PROXY: {
                LocalisationProviderProxy localisationProviderProxy = (LocalisationProviderProxy)theEObject;
                T result = caseLocalisationProviderProxy(localisationProviderProxy);
                if(result == null)
                    result = caseContextProvider(localisationProviderProxy);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.MOUSE: {
                Mouse mouse = (Mouse)theEObject;
                T result = caseMouse(mouse);
                if(result == null)
                    result = caseHapticalInteractionResources(mouse);
                if(result == null)
                    result = caseInputInteractionResource(mouse);
                if(result == null)
                    result = caseInteractionResource(mouse);
                if(result == null)
                    result = caseElementWithPosition(mouse);
                if(result == null)
                    result = caseEnvironmentElement(mouse);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.KEYBOARD: {
                Keyboard keyboard = (Keyboard)theEObject;
                T result = caseKeyboard(keyboard);
                if(result == null)
                    result = caseHapticalInteractionResources(keyboard);
                if(result == null)
                    result = caseInputInteractionResource(keyboard);
                if(result == null)
                    result = caseInteractionResource(keyboard);
                if(result == null)
                    result = caseElementWithPosition(keyboard);
                if(result == null)
                    result = caseEnvironmentElement(keyboard);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.DISPLAY: {
                Display display = (Display)theEObject;
                T result = caseDisplay(display);
                if(result == null)
                    result = caseOutputInteractionResource(display);
                if(result == null)
                    result = caseInteractionResource(display);
                if(result == null)
                    result = caseElementWithPosition(display);
                if(result == null)
                    result = caseEnvironmentElement(display);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.INPUT_INTERACTION_RESOURCE: {
                InputInteractionResource inputInteractionResource = (InputInteractionResource)theEObject;
                T result = caseInputInteractionResource(inputInteractionResource);
                if(result == null)
                    result = caseInteractionResource(inputInteractionResource);
                if(result == null)
                    result = caseElementWithPosition(inputInteractionResource);
                if(result == null)
                    result = caseEnvironmentElement(inputInteractionResource);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.OUTPUT_INTERACTION_RESOURCE: {
                OutputInteractionResource outputInteractionResource = (OutputInteractionResource)theEObject;
                T result = caseOutputInteractionResource(outputInteractionResource);
                if(result == null)
                    result = caseInteractionResource(outputInteractionResource);
                if(result == null)
                    result = caseElementWithPosition(outputInteractionResource);
                if(result == null)
                    result = caseEnvironmentElement(outputInteractionResource);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.GESTURE_IR: {
                GestureIR gestureIR = (GestureIR)theEObject;
                T result = caseGestureIR(gestureIR);
                if(result == null)
                    result = caseHapticalInteractionResources(gestureIR);
                if(result == null)
                    result = caseInputInteractionResource(gestureIR);
                if(result == null)
                    result = caseInteractionResource(gestureIR);
                if(result == null)
                    result = caseElementWithPosition(gestureIR);
                if(result == null)
                    result = caseEnvironmentElement(gestureIR);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.TOUCHPAD: {
                Touchpad touchpad = (Touchpad)theEObject;
                T result = caseTouchpad(touchpad);
                if(result == null)
                    result = caseHapticalInteractionResources(touchpad);
                if(result == null)
                    result = caseInputInteractionResource(touchpad);
                if(result == null)
                    result = caseInteractionResource(touchpad);
                if(result == null)
                    result = caseElementWithPosition(touchpad);
                if(result == null)
                    result = caseEnvironmentElement(touchpad);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.TOUCHSCREEN: {
                Touchscreen touchscreen = (Touchscreen)theEObject;
                T result = caseTouchscreen(touchscreen);
                if(result == null)
                    result = caseDisplay(touchscreen);
                if(result == null)
                    result = caseTouchpad(touchscreen);
                if(result == null)
                    result = caseOutputInteractionResource(touchscreen);
                if(result == null)
                    result = caseHapticalInteractionResources(touchscreen);
                if(result == null)
                    result = caseInputInteractionResource(touchscreen);
                if(result == null)
                    result = caseInteractionResource(touchscreen);
                if(result == null)
                    result = caseElementWithPosition(touchscreen);
                if(result == null)
                    result = caseEnvironmentElement(touchscreen);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.DEVICE: {
                Device device = (Device)theEObject;
                T result = caseDevice(device);
                if(result == null)
                    result = caseElementWithPosition(device);
                if(result == null)
                    result = caseEnvironmentElement(device);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.PHYSICAL_DEVICE: {
                PhysicalDevice physicalDevice = (PhysicalDevice)theEObject;
                T result = casePhysicalDevice(physicalDevice);
                if(result == null)
                    result = caseDevice(physicalDevice);
                if(result == null)
                    result = caseElementWithPosition(physicalDevice);
                if(result == null)
                    result = caseEnvironmentElement(physicalDevice);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.PHYSICAL_DEVICE_WITH_PROGRAM: {
                PhysicalDeviceWithProgram physicalDeviceWithProgram = (PhysicalDeviceWithProgram)theEObject;
                T result = casePhysicalDeviceWithProgram(physicalDeviceWithProgram);
                if(result == null)
                    result = casePhysicalDevice(physicalDeviceWithProgram);
                if(result == null)
                    result = caseDevice(physicalDeviceWithProgram);
                if(result == null)
                    result = caseElementWithPosition(physicalDeviceWithProgram);
                if(result == null)
                    result = caseEnvironmentElement(physicalDeviceWithProgram);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.LAMP: {
                Lamp lamp = (Lamp)theEObject;
                T result = caseLamp(lamp);
                if(result == null)
                    result = casePhysicalDevice(lamp);
                if(result == null)
                    result = caseDevice(lamp);
                if(result == null)
                    result = caseElementWithPosition(lamp);
                if(result == null)
                    result = caseEnvironmentElement(lamp);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.NOTEBOOK: {
                Notebook notebook = (Notebook)theEObject;
                T result = caseNotebook(notebook);
                if(result == null)
                    result = casePhysicalDevice(notebook);
                if(result == null)
                    result = caseDevice(notebook);
                if(result == null)
                    result = caseElementWithPosition(notebook);
                if(result == null)
                    result = caseEnvironmentElement(notebook);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.HOOD: {
                Hood hood = (Hood)theEObject;
                T result = caseHood(hood);
                if(result == null)
                    result = casePhysicalDevice(hood);
                if(result == null)
                    result = caseDevice(hood);
                if(result == null)
                    result = caseElementWithPosition(hood);
                if(result == null)
                    result = caseEnvironmentElement(hood);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.PC: {
                PC pc = (PC)theEObject;
                T result = casePC(pc);
                if(result == null)
                    result = casePhysicalDevice(pc);
                if(result == null)
                    result = caseDevice(pc);
                if(result == null)
                    result = caseElementWithPosition(pc);
                if(result == null)
                    result = caseEnvironmentElement(pc);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.FAN: {
                Fan fan = (Fan)theEObject;
                T result = caseFan(fan);
                if(result == null)
                    result = casePhysicalDevice(fan);
                if(result == null)
                    result = caseDevice(fan);
                if(result == null)
                    result = caseElementWithPosition(fan);
                if(result == null)
                    result = caseEnvironmentElement(fan);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.REMOTE_CONTROL: {
                RemoteControl remoteControl = (RemoteControl)theEObject;
                T result = caseRemoteControl(remoteControl);
                if(result == null)
                    result = casePhysicalDevice(remoteControl);
                if(result == null)
                    result = caseDevice(remoteControl);
                if(result == null)
                    result = caseElementWithPosition(remoteControl);
                if(result == null)
                    result = caseEnvironmentElement(remoteControl);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.TV: {
                TV tv = (TV)theEObject;
                T result = caseTV(tv);
                if(result == null)
                    result = casePhysicalDevice(tv);
                if(result == null)
                    result = caseDevice(tv);
                if(result == null)
                    result = caseElementWithPosition(tv);
                if(result == null)
                    result = caseEnvironmentElement(tv);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.OVEN: {
                Oven oven = (Oven)theEObject;
                T result = caseOven(oven);
                if(result == null)
                    result = casePhysicalDeviceWithProgram(oven);
                if(result == null)
                    result = casePhysicalDevice(oven);
                if(result == null)
                    result = caseDevice(oven);
                if(result == null)
                    result = caseElementWithPosition(oven);
                if(result == null)
                    result = caseEnvironmentElement(oven);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.DISHWASHER: {
                Dishwasher dishwasher = (Dishwasher)theEObject;
                T result = caseDishwasher(dishwasher);
                if(result == null)
                    result = casePhysicalDeviceWithProgram(dishwasher);
                if(result == null)
                    result = casePhysicalDevice(dishwasher);
                if(result == null)
                    result = caseDevice(dishwasher);
                if(result == null)
                    result = caseElementWithPosition(dishwasher);
                if(result == null)
                    result = caseEnvironmentElement(dishwasher);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.WASHING_MACHINE: {
                WashingMachine washingMachine = (WashingMachine)theEObject;
                T result = caseWashingMachine(washingMachine);
                if(result == null)
                    result = casePhysicalDeviceWithProgram(washingMachine);
                if(result == null)
                    result = casePhysicalDevice(washingMachine);
                if(result == null)
                    result = caseDevice(washingMachine);
                if(result == null)
                    result = caseElementWithPosition(washingMachine);
                if(result == null)
                    result = caseEnvironmentElement(washingMachine);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.BLIND: {
                Blind blind = (Blind)theEObject;
                T result = caseBlind(blind);
                if(result == null)
                    result = casePhysicalDevice(blind);
                if(result == null)
                    result = caseDevice(blind);
                if(result == null)
                    result = caseElementWithPosition(blind);
                if(result == null)
                    result = caseEnvironmentElement(blind);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.HOB: {
                Hob hob = (Hob)theEObject;
                T result = caseHob(hob);
                if(result == null)
                    result = casePhysicalDevice(hob);
                if(result == null)
                    result = caseDevice(hob);
                if(result == null)
                    result = caseElementWithPosition(hob);
                if(result == null)
                    result = caseEnvironmentElement(hob);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.COOKER: {
                Cooker cooker = (Cooker)theEObject;
                T result = caseCooker(cooker);
                if(result == null)
                    result = casePhysicalDevice(cooker);
                if(result == null)
                    result = caseDevice(cooker);
                if(result == null)
                    result = caseElementWithPosition(cooker);
                if(result == null)
                    result = caseEnvironmentElement(cooker);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.COOK_TOP: {
                CookTop cookTop = (CookTop)theEObject;
                T result = caseCookTop(cookTop);
                if(result == null)
                    result = casePhysicalDevice(cookTop);
                if(result == null)
                    result = caseDevice(cookTop);
                if(result == null)
                    result = caseElementWithPosition(cookTop);
                if(result == null)
                    result = caseEnvironmentElement(cookTop);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.FRIDGE: {
                Fridge fridge = (Fridge)theEObject;
                T result = caseFridge(fridge);
                if(result == null)
                    result = casePhysicalDevice(fridge);
                if(result == null)
                    result = caseDevice(fridge);
                if(result == null)
                    result = caseElementWithPosition(fridge);
                if(result == null)
                    result = caseEnvironmentElement(fridge);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.MIXER: {
                Mixer mixer = (Mixer)theEObject;
                T result = caseMixer(mixer);
                if(result == null)
                    result = casePhysicalDevice(mixer);
                if(result == null)
                    result = caseDevice(mixer);
                if(result == null)
                    result = caseElementWithPosition(mixer);
                if(result == null)
                    result = caseEnvironmentElement(mixer);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.RADIO: {
                Radio radio = (Radio)theEObject;
                T result = caseRadio(radio);
                if(result == null)
                    result = casePhysicalDevice(radio);
                if(result == null)
                    result = caseDevice(radio);
                if(result == null)
                    result = caseElementWithPosition(radio);
                if(result == null)
                    result = caseEnvironmentElement(radio);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.SOCKET: {
                Socket socket = (Socket)theEObject;
                T result = caseSocket(socket);
                if(result == null)
                    result = casePhysicalDevice(socket);
                if(result == null)
                    result = caseDevice(socket);
                if(result == null)
                    result = caseElementWithPosition(socket);
                if(result == null)
                    result = caseEnvironmentElement(socket);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.HEATER: {
                Heater heater = (Heater)theEObject;
                T result = caseHeater(heater);
                if(result == null)
                    result = casePhysicalDevice(heater);
                if(result == null)
                    result = caseDevice(heater);
                if(result == null)
                    result = caseElementWithPosition(heater);
                if(result == null)
                    result = caseEnvironmentElement(heater);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.METER: {
                Meter meter = (Meter)theEObject;
                T result = caseMeter(meter);
                if(result == null)
                    result = casePhysicalDevice(meter);
                if(result == null)
                    result = caseDevice(meter);
                if(result == null)
                    result = caseElementWithPosition(meter);
                if(result == null)
                    result = caseEnvironmentElement(meter);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.WATER_STORAGE_TANK: {
                WaterStorageTank waterStorageTank = (WaterStorageTank)theEObject;
                T result = caseWaterStorageTank(waterStorageTank);
                if(result == null)
                    result = casePhysicalDevice(waterStorageTank);
                if(result == null)
                    result = caseDevice(waterStorageTank);
                if(result == null)
                    result = caseElementWithPosition(waterStorageTank);
                if(result == null)
                    result = caseEnvironmentElement(waterStorageTank);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.HEATING_ROD: {
                HeatingRod heatingRod = (HeatingRod)theEObject;
                T result = caseHeatingRod(heatingRod);
                if(result == null)
                    result = casePhysicalDevice(heatingRod);
                if(result == null)
                    result = caseDevice(heatingRod);
                if(result == null)
                    result = caseElementWithPosition(heatingRod);
                if(result == null)
                    result = caseEnvironmentElement(heatingRod);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.METER_READING: {
                MeterReading meterReading = (MeterReading)theEObject;
                T result = caseMeterReading(meterReading);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.PHYSICAL_SENSOR_DEVICE: {
                PhysicalSensorDevice physicalSensorDevice = (PhysicalSensorDevice)theEObject;
                T result = casePhysicalSensorDevice(physicalSensorDevice);
                if(result == null)
                    result = caseDevice(physicalSensorDevice);
                if(result == null)
                    result = caseElementWithPosition(physicalSensorDevice);
                if(result == null)
                    result = caseEnvironmentElement(physicalSensorDevice);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.LUMINANCE_SENSOR: {
                LuminanceSensor luminanceSensor = (LuminanceSensor)theEObject;
                T result = caseLuminanceSensor(luminanceSensor);
                if(result == null)
                    result = casePhysicalSensorDevice(luminanceSensor);
                if(result == null)
                    result = caseDevice(luminanceSensor);
                if(result == null)
                    result = caseElementWithPosition(luminanceSensor);
                if(result == null)
                    result = caseEnvironmentElement(luminanceSensor);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.TEMPERATURE_SENSOR: {
                TemperatureSensor temperatureSensor = (TemperatureSensor)theEObject;
                T result = caseTemperatureSensor(temperatureSensor);
                if(result == null)
                    result = casePhysicalSensorDevice(temperatureSensor);
                if(result == null)
                    result = caseDevice(temperatureSensor);
                if(result == null)
                    result = caseElementWithPosition(temperatureSensor);
                if(result == null)
                    result = caseEnvironmentElement(temperatureSensor);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.CHANNEL: {
                Channel<?, ?> channel = (Channel<?, ?>)theEObject;
                T result = caseChannel(channel);
                if(result == null)
                    result = caseEnvironmentElement(channel);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.MESSAGE_INPUT_CHANNEL: {
                MessageInputChannel messageInputChannel = (MessageInputChannel)theEObject;
                T result = caseMessageInputChannel(messageInputChannel);
                if(result == null)
                    result = caseChannel(messageInputChannel);
                if(result == null)
                    result = caseOneDimensional(messageInputChannel);
                if(result == null)
                    result = caseEnvironmentElement(messageInputChannel);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.MESSAGE_OUTPUT_CHANNEL: {
                MessageOutputChannel messageOutputChannel = (MessageOutputChannel)theEObject;
                T result = caseMessageOutputChannel(messageOutputChannel);
                if(result == null)
                    result = caseChannel(messageOutputChannel);
                if(result == null)
                    result = caseOneDimensional(messageOutputChannel);
                if(result == null)
                    result = caseEnvironmentElement(messageOutputChannel);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.POINTING_INPUT_CHANNEL: {
                PointingInputChannel pointingInputChannel = (PointingInputChannel)theEObject;
                T result = casePointingInputChannel(pointingInputChannel);
                if(result == null)
                    result = caseChannel(pointingInputChannel);
                if(result == null)
                    result = caseTwoDimensional(pointingInputChannel);
                if(result == null)
                    result = caseEnvironmentElement(pointingInputChannel);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL: {
                GraphicalOutputChannel graphicalOutputChannel = (GraphicalOutputChannel)theEObject;
                T result = caseGraphicalOutputChannel(graphicalOutputChannel);
                if(result == null)
                    result = caseChannel(graphicalOutputChannel);
                if(result == null)
                    result = caseTwoDimensional(graphicalOutputChannel);
                if(result == null)
                    result = caseEnvironmentElement(graphicalOutputChannel);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.LOUDSPEAKER: {
                Loudspeaker loudspeaker = (Loudspeaker)theEObject;
                T result = caseLoudspeaker(loudspeaker);
                if(result == null)
                    result = caseOutputInteractionResource(loudspeaker);
                if(result == null)
                    result = caseInteractionResource(loudspeaker);
                if(result == null)
                    result = caseElementWithPosition(loudspeaker);
                if(result == null)
                    result = caseEnvironmentElement(loudspeaker);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.MICROPHONE: {
                Microphone microphone = (Microphone)theEObject;
                T result = caseMicrophone(microphone);
                if(result == null)
                    result = caseInputInteractionResource(microphone);
                if(result == null)
                    result = caseInteractionResource(microphone);
                if(result == null)
                    result = caseElementWithPosition(microphone);
                if(result == null)
                    result = caseEnvironmentElement(microphone);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.VECTOR: {
                Vector vector = (Vector)theEObject;
                T result = caseVector(vector);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.AREA: {
                Area area = (Area)theEObject;
                T result = caseArea(area);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.HAPTICAL_INTERACTION_RESOURCES: {
                HapticalInteractionResources hapticalInteractionResources = (HapticalInteractionResources)theEObject;
                T result = caseHapticalInteractionResources(hapticalInteractionResources);
                if(result == null)
                    result = caseInputInteractionResource(hapticalInteractionResources);
                if(result == null)
                    result = caseInteractionResource(hapticalInteractionResources);
                if(result == null)
                    result = caseElementWithPosition(hapticalInteractionResources);
                if(result == null)
                    result = caseEnvironmentElement(hapticalInteractionResources);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.CONFIGURATION_PROPERTY: {
                ConfigurationProperty configurationProperty = (ConfigurationProperty)theEObject;
                T result = caseConfigurationProperty(configurationProperty);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.ASSISTANT_CONNECTION: {
                AssistantConnection assistantConnection = (AssistantConnection)theEObject;
                T result = caseAssistantConnection(assistantConnection);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.TOOL: {
                Tool tool = (Tool)theEObject;
                T result = caseTool(tool);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.DEVICE_TOOL: {
                DeviceTool deviceTool = (DeviceTool)theEObject;
                T result = caseDeviceTool(deviceTool);
                if(result == null)
                    result = caseTool(deviceTool);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case ContextPackage.NEED: {
                Need need = (Need)theEObject;
                T result = caseNeed(need);
                if(result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            default:
                return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Environment</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Environment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEnvironment(Environment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Environment Element</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Environment Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEnvironmentElement(EnvironmentElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Interaction Resource</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Interaction Resource</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInteractionResource(InteractionResource object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>User</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>User</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUser(User object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Assistant</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Assistant</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAssistant(Assistant object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Activity</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Activity</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseActivity(Activity object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Home OS Assistant</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Home OS Assistant</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHomeOSAssistant(HomeOSAssistant object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Third Party Assistant</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Third Party Assistant</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseThirdPartyAssistant(ThirdPartyAssistant object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Localization Tag</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Localization Tag</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLocalizationTag(LocalizationTag object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Element With Position</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Element With Position</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElementWithPosition(ElementWithPosition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Place</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Place</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePlace(Place object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Room</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Room</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRoom(Room object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Outdoors</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Outdoors</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOutdoors(Outdoors object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Door</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Door</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDoor(Door object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Window</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Window</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWindow(Window object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Provider</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Provider</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContextProvider(ContextProvider object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Localisation Provider Proxy</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Localisation Provider Proxy</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLocalisationProviderProxy(LocalisationProviderProxy object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Mouse</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Mouse</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMouse(Mouse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Keyboard</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Keyboard</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKeyboard(Keyboard object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Display</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Display</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDisplay(Display object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Input Interaction Resource</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Input Interaction Resource</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInputInteractionResource(InputInteractionResource object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Output Interaction Resource</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Output Interaction Resource</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOutputInteractionResource(OutputInteractionResource object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Gesture IR</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Gesture IR</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGestureIR(GestureIR object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Touchpad</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Touchpad</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTouchpad(Touchpad object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Touchscreen</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Touchscreen</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTouchscreen(Touchscreen object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Device</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Device</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDevice(Device object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Physical Device</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Physical Device</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePhysicalDevice(PhysicalDevice object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Physical Device With Program</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Physical Device With Program</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePhysicalDeviceWithProgram(PhysicalDeviceWithProgram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Lamp</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Lamp</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLamp(Lamp object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Notebook</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Notebook</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNotebook(Notebook object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Hood</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Hood</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHood(Hood object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>PC</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>PC</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePC(PC object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Fan</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Fan</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFan(Fan object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Remote Control</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Remote Control</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRemoteControl(RemoteControl object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>TV</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>TV</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTV(TV object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Oven</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Oven</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOven(Oven object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Dishwasher</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Dishwasher</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDishwasher(Dishwasher object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Washing Machine</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Washing Machine</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWashingMachine(WashingMachine object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Blind</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Blind</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBlind(Blind object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Hob</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Hob</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHob(Hob object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Cooker</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Cooker</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCooker(Cooker object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Cook Top</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Cook Top</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCookTop(CookTop object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Fridge</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Fridge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFridge(Fridge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Mixer</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Mixer</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMixer(Mixer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Radio</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Radio</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRadio(Radio object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Socket</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Socket</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSocket(Socket object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Heater</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Heater</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHeater(Heater object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Meter</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Meter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMeter(Meter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Water Storage Tank</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Water Storage Tank</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWaterStorageTank(WaterStorageTank object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Heating Rod</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Heating Rod</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHeatingRod(HeatingRod object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Meter Reading</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Meter Reading</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMeterReading(MeterReading object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Physical Sensor Device</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Physical Sensor Device</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePhysicalSensorDevice(PhysicalSensorDevice object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Luminance Sensor</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Luminance Sensor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLuminanceSensor(LuminanceSensor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Temperature Sensor</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Temperature Sensor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTemperatureSensor(TemperatureSensor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Channel</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Channel</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public <C extends ConcreteInteractor, A extends ChannelAPI> T caseChannel(Channel<C, A> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Message Input Channel</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Message Input Channel</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMessageInputChannel(MessageInputChannel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Message Output Channel</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Message Output Channel</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMessageOutputChannel(MessageOutputChannel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Pointing Input Channel</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Pointing Input Channel</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePointingInputChannel(PointingInputChannel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Graphical Output Channel</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Graphical Output Channel</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGraphicalOutputChannel(GraphicalOutputChannel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Loudspeaker</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Loudspeaker</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLoudspeaker(Loudspeaker object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Microphone</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Microphone</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMicrophone(Microphone object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Vector</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Vector</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVector(Vector object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Area</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Area</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseArea(Area object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Haptical Interaction Resources</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Haptical Interaction Resources</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHapticalInteractionResources(HapticalInteractionResources object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Configuration Property</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Configuration Property</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConfigurationProperty(ConfigurationProperty object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Assistant Connection</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Assistant Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAssistantConnection(AssistantConnection object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Tool</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTool(Tool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Device Tool</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Device Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDeviceTool(DeviceTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Need</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Need</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNeed(Need object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>One Dimensional</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>One Dimensional</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOneDimensional(OneDimensional object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Two Dimensional</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Two Dimensional</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTwoDimensional(TwoDimensional object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch, but this is
     * the last case anyway. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // ContextSwitch
