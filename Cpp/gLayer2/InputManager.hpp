/**
 * This file contains the gLayer Input Handler singleton that allows connection to OIS from everywhere.
 * @author barn
 * @version 20130527
 */
#pragma once

///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers
#include "defines.h"

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)

#include <OIS/OISEvents.h>
#include <OIS/OISInputManager.h>
#include <OIS/OISKeyboard.h>
#include <OIS/OISMouse.h>

#include <Awesomium/WebCore.h>

///////////////////////////////////////////////////////////////////////////////
// DEFINES and MACROS


///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS

/// Interface for Mouse Listeners that can be used by the InputManager
class IMouseListener
{
public:
    virtual bool onMouseMoved    ( const OIS::MouseEvent &arg) = 0;
    virtual bool onMousePressed  ( const OIS::MouseEvent &arg, OIS::MouseButtonID id) = 0;
    virtual bool onMouseReleased ( const OIS::MouseEvent &arg, OIS::MouseButtonID id) = 0;
};

/// Interface for Key Listeners that can be used by the InputManager
class IKeyListener
{
public:
    virtual bool onKeyPressed  ( const OIS::KeyEvent &arg) = 0;
    virtual bool onKeyReleased ( const OIS::KeyEvent &arg) = 0;
};


/**
 * This class represents an input manager singleton.
 * It enables handling of mouse and keyboard via one singleton class.
 * It is also able to dispatch events to IMouseListener implementations,
 * therefore enabling multiple listeners on one OIS mouse or OIS keyboard.
 */
class InputManager : public OIS::MouseListener, public OIS::KeyListener
{

private: // class vars

    /// The singleton instance.
    static InputManager* __instance;

private: // instance vars

    /// OIS input manager instance.
    OIS::InputManager* _manager;

    /// OIS mouse instance.
    OIS::Mouse* _mouse;

    /// OIS keyboard instance.
    OIS::Keyboard* _keyboard;

    /// current mouse position in x and y dimension.
    float _mouse_position_x, _mouse_position_y;

    /// current mouse acceleration in x and y dimension.
    float _mouse_speed_x, _mouse_speed_y;

    /// List of all attached mouse listeners
    std::list<IMouseListener*> _mouse_listeners;

    // List of all attached key listeners;
    std::list<IKeyListener*> _key_listeners;

protected: // constructor

    /**
     * Protected Main constructor. Sets up OIS.
     * @param win The window on which to listen for input.
     * @param mouse_speed_x The speed of the mouse in x direction.
     * @param mouse_speed_y The speed of the mouse in y direction.
     */
    InputManager( Ogre::RenderWindow* win, float mouse_speed_x=0.002f, float mouse_speed_y=0.002f)
        : _mouse_position_x(0),
        _mouse_position_y(0),
        _mouse_speed_x( mouse_speed_x),
        _mouse_speed_y( mouse_speed_y)
    {
        setupOIS( win);

        // set event callbacks
        _mouse->setEventCallback(this);
        _keyboard->setEventCallback(this);

        // necessary to allow callback handling
        _mouse->setBuffered(true);
        _keyboard->setBuffered(true);
    }

public: // destructor

    /// Destructor.
    ~InputManager()
    {
        safe_release( _keyboard);
        safe_release( _mouse);

        __instance = 0;
    }

public: // class methods

    /**
     * Sets up the singleton instance of the InputManager which sets OIS.
     * Only sets it up, if the singleton instance is NULL.
     * @param win The window on which to listen for input.
     * @return The singleton InputManager.
     * @see instance()
     */
    static InputManager* setup( Ogre::RenderWindow* win, float mouse_speed_x=0.002f, float mouse_speed_y=0.002f)
    {
        if (__instance == 0)
            __instance = new InputManager( win, mouse_speed_x, mouse_speed_y);
        return __instance;
    }

    /**
     * Retrieves the singleton instance of the InputManager.
     * Before you call this method, setup() should be called once.
     * @return The singleton InputManager or NULL, if setup() is not called.
     * @see setup()
     */
    static InputManager* instance()
    {
        return __instance;
    }


public: // methods

    /// Calls capture on the keyboard and the mouse instance.
    inline void capture()
    {
        _keyboard->capture();
        _mouse->capture();
    }

    /// Retrieves the Mouse.
    inline OIS::Mouse* mouse()
    {
        return _mouse;
    }

    /// Retrieves the keyboard.
    inline OIS::Keyboard* keyboard()
    {
        return _keyboard;
    }

    /**
     * Generates and return the Awesomium::KeyboardEvents out of OIS.
     * @param A list of all Awesomium WebKeyboardEvents.
     */
    std::list<Awesomium::WebKeyboardEvent>* getAwesomiumKeyCodes()
    {
        return 0;

        // TODO do for all downkeys

        Awesomium::WebKeyboardEvent key_event;

        //if(!(event.type == SDL_KEYDOWN || event.type == SDL_KEYUP))
            //return;

        key_event.type = Awesomium::WebKeyboardEvent::kTypeKeyDown;

        char* buf (new char[20]);

        //key_event.virtual_key_code = getWebKeyFromSDLKey(event.key.keysym.sym); // translate key codes from ois to awe lookat Awesomium/WebKeyboardCodes.h
        Awesomium::GetKeyIdentifierFromVirtualKeyCode(key_event.virtual_key_code, &buf);
        strcpy(key_event.key_identifier, buf);
        delete[] buf;


        key_event.modifiers = 0;


        // TODO modifiers
    /*
        if (event.key.keysym.mod & KMOD_LALT || event.key.keysym.mod & KMOD_RALT)
        key_event.modifiers |= Awesomium::WebKeyboardEvent::kModAltKey;
        if (event.key.keysym.mod & KMOD_LCTRL || event.key.keysym.mod & KMOD_RCTRL)
        key_event.modifiers |= Awesomium::WebKeyboardEvent::kModControlKey;
        if (event.key.keysym.mod & KMOD_LMETA || event.key.keysym.mod & KMOD_RMETA)
        key_event.modifiers |= Awesomium::WebKeyboardEvent::kModMetaKey;
        if (event.key.keysym.mod & KMOD_LSHIFT || event.key.keysym.mod & KMOD_RSHIFT)
        key_event.modifiers |= Awesomium::WebKeyboardEvent::kModShiftKey;
        if (event.key.keysym.mod & KMOD_NUM)
        key_event.modifiers |= Awesomium::WebKeyboardEvent::kModIsKeypad;


        //key_event.native_key_code = event.key.keysym.scancode;


        if (event.type == SDL_KEYUP)
        {
            web_view->InjectKeyboardEvent(key_event);
        }
        else
        {
            unsigned int chr;
            if ((event.key.keysym.unicode & 0xFF80) == 0)
            chr = event.key.keysym.unicode & 0x7F;
            else
            chr = event.key.keysym.unicode;

            key_event.text[0] = chr;
            key_event.unmodified_text[0] = chr;


            /// web_view->InjectKeyboardEvent(key_event); // TODO put this into webtexture

            if (chr)
            {
                key_event.type = Awesomium::WebKeyboardEvent::kTypeChar;
                key_event.virtual_key_code = chr;
                key_event.native_key_code = chr;
                web_view->InjectKeyboardEvent(key_event);
            }
        }
        */
    }


    /**
     * TODO: WINDOW SIZE SHALL BE SET
     * Retrieves the mouse position within a [0,WINDOW_SIZE_X][0,WINDOW_SIZE_Y] range.
     * @param mouse the mouse to be handled.
     */
    std::pair<float,float> getMousePosition( OIS::Mouse* mouse)
    {
        _mouse_position_x = limit( _mouse_position_x + mouse->getMouseState().X.rel * _mouse_speed_x, 0, 1);
        _mouse_position_y = limit( _mouse_position_y  + mouse->getMouseState().Y.rel * _mouse_speed_y, 0, 1);

        return std::make_pair(_mouse_position_x, _mouse_position_y);
    }


    /**
     * Retrieves the mouse position within a [0,1][0,1] range.
     * @param mouse the mouse to be handled.
     * @return A pair of floats within [0,1][0,1]
     * where the first one indicates the x position, the second one the y position.
     */
    std::pair<float,float> getMousePositionNormalized( OIS::Mouse* mouse)
    {
        _mouse_position_x = limit( _mouse_position_x + mouse->getMouseState().X.rel * _mouse_speed_x, 0, 1);
        _mouse_position_y = limit( _mouse_position_y  + mouse->getMouseState().Y.rel * _mouse_speed_y, 0, 1);

        return std::make_pair(_mouse_position_x, _mouse_position_y);
    }


    /**
     * Retrieves the mouse position within a [0,WINDOW_SIZE_X][0,WINDOW_SIZE_Y] range.
     * It handles the mouse connected to the manager.
     */
    inline std::pair<float,float> getMousePosition()
    {
        return getMousePosition( _mouse);
    }


    /**
     * Retrieves the mouse position within a [0,1][0,1] range.
     * It handles the mouse connected to the manager.
     * @return A pair of floats within [0,1][0,1]
     * where the first one indicates the x position, the second one the y position.
     */
    inline std::pair<float,float> getMousePositionNormalized()
    {
        return getMousePositionNormalized( _mouse);
    }


    /**
     * Retrieves a reference to the list of all mouse listeners.
     * @return A reference to the list of all mouse listeners.
     */
    inline std::list<IMouseListener*>& getMouseListenerList()
    {
        return _mouse_listeners;
    }


    /**
     * Retrieves a reference to the list of all key listeners.
     * @return A reference to the list of all key listeners.
     */
    inline std::list<IKeyListener*>& getKeyListenerList()
    {
        return _key_listeners;
    }


    /**
     * Maps an OIS mouse button id onto an Awesomium mouse button id.
     *
     * @param id The OIS mouse button id to be mapped.
     * @return The awesomium mouse button id corresponding to the input,
     * or left mouse button id in case of no reconnaissance.
     */
    Awesomium::MouseButton OISMouseButton2AwesomiumMouseButton( OIS::MouseButtonID id)
    {
        Awesomium::MouseButton ret;
        switch( id)
        {
        case OIS::MouseButtonID::MB_Left:
            ret = Awesomium::MouseButton::kMouseButton_Left;
            break;
        case OIS::MouseButtonID::MB_Right:
            ret = Awesomium::MouseButton::kMouseButton_Right;
                break;
        case OIS::MouseButtonID::MB_Middle:
            ret = Awesomium::MouseButton::kMouseButton_Middle;
            break;
        default:
            ret = Awesomium::MouseButton::kMouseButton_Left;
        }
        return ret;
    }


public: // inherited methods

    /// Inherited from OIS::MouseListener
    bool mouseMoved( const OIS::MouseEvent &arg)
    {
        for( auto it ( _mouse_listeners.begin()); it != _mouse_listeners.end(); ++it)
            (*it)->onMouseMoved( arg);

        return true;
    }


    /// Inherited from OIS::MouseListener
    bool mousePressed( const OIS::MouseEvent &arg, OIS::MouseButtonID id)
    {
        for( auto it (_mouse_listeners.begin()); it != _mouse_listeners.end(); ++it)
            (*it)->onMousePressed( arg, id);
        return true;
    }


    /// Inherited from OIS::MouseListener
    bool mouseReleased( const OIS::MouseEvent &arg, OIS::MouseButtonID id)
    {
        for( auto it (_mouse_listeners.begin()); it != _mouse_listeners.end(); ++it)
            (*it)->onMouseReleased( arg, id);
        return true;
    }


    /// Inherited from OIS::KeyListener
    bool keyPressed( const OIS::KeyEvent &arg)
    {
        for( auto it (_key_listeners.begin()); it != _key_listeners.end(); ++it)
            (*it)->onKeyPressed( arg);
        return true;
    }


    /// Inherited from OIS::KeyListener
    bool keyReleased( const OIS::KeyEvent &arg)
    {
        for( auto it (_key_listeners.begin()); it != _key_listeners.end(); ++it)
            (*it)->onKeyReleased( arg);
        return true;
    }


private: // helpers

    /*
     * @brief sets up the object OIS oriented input system and inits the OIS-powered member vars.
     * @param win The window on which to listen for input.
     * @return Returns a tuple of a an input manager a keyboard and a mouse.
     */
    std::tuple<OIS::InputManager*,OIS::Keyboard*, OIS::Mouse*> setupOIS( Ogre::RenderWindow* win)
    {
        size_t windowHnd (0);
        std::stringstream windowHndStr;

        win->getCustomAttribute("WINDOW", &windowHnd);
        windowHndStr << windowHnd;

        OIS::ParamList pl;
        pl.insert(std::make_pair(std::string("WINDOW"), windowHndStr.str()));
        _manager = OIS::InputManager::createInputSystem( pl);
        _keyboard = static_cast<OIS::Keyboard*>( _manager->createInputObject( OIS::OISKeyboard, false));
        _mouse = static_cast<OIS::Mouse*>( _manager->createInputObject( OIS::OISMouse, false));

        return std::tuple<OIS::InputManager*,OIS::Keyboard*, OIS::Mouse*>( _manager, _keyboard, _mouse);
    }

    /** Limits the first input value to the closed range
    given by the lower bound and the upper bound.
    @param x The input value to be limited.
    @param lower_bound The lower bound of the range to limit within.
    @param upper_bound The upper bound of the range to limit within.
    */
    inline float limit( float x, float lower_bound, float upper_bound)
    {
        return std::max( lower_bound, std::min( x, upper_bound));
    }

}; // END class InputManager

//// INITIALIZE STATIC VARIABLE
InputManager* InputManager::__instance(0);
