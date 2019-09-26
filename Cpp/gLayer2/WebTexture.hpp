/**
 * This file provides a FrameListener that maps a dynamic texture
 * from some website to some Texture within Ogre using Awesomium SDK.
 * @author barn
 * @version 20131104
 */
#pragma once

///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers
#include "IDynamicTexture.hpp"
#include "InputManager.hpp"

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)
#include <Awesomium/WebCore.h>
#include <Awesomium/BitmapSurface.h>
#include <Awesomium/STLHelpers.h>

#include <OGRE/OgreHardwarePixelBuffer.h>

///////////////////////////////////////////////////////////////////////////////
// DEFINES and MACROS

#if defined(__WIN32__) || defined(_WIN32)
    #define WEBTEXTURE_SLEEP(sleep_ms) Sleep(sleep_ms)
#elif defined(__APPLE__)
    #define WEBTEXTURE_SLEEP(sleep_ms) usleep(sleep_ms * 1000);
#endif


///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS


namespace DynamicOgreTexture
{
    /// Constants
    const unsigned int __website_view_width  (1920);
    const unsigned int __website_view_height (1080);
    const unsigned int __webtexture_sleep_ms (20);

    unsigned short __awe_remote_debugging_port(1225);

    /**
     * This class represents a FrameListener, that maps a dynamic texture of a website
     * onto some texture pointer. It is also viable as a Compositor listener.
     */
    class WebTexture : public IDynamicTexture, public IMouseListener, public IKeyListener
    {
    private: // vars

        /// The awesomium WebView of the internet page to be used.
        Awesomium::WebView* _awe_view;

        /// The Awesomium WebView dimensions.
        unsigned int _website_view_width, _website_view_height;

        /// Specifies, whether mouse & keyboard input shall be injected into the browser.
        bool _inject_input;

    public: // constructor & destructor

        /**
         * Constructor #1.
         * @param texture_name The name of the texture. Should be unique.
         * @param web_adress The website's address that should be taken as texture.
         * @param texture_unit_state the texture unit state on which the website
         * shall be printed as a picture. Compositor listeners don't need a texture_unit_state.
         * @param website_view_width The width of the screenshot that shall be taken from the website.
         * @param website_view_height The width of the screenshot that shall be taken from the website.
         * shall be printed as a picture. Compositor listeners don't need a texture_unit_state.
         * With this texture used solely as a Compositor Listener, NULL should be sufficient.
         * @param is_use_alpha Specifies, whether to regard alpha channel of the dynamic texture.
         * @param inject_input Specifies, whether mouse & keyboard input shall be injected into the browser.
         */
        WebTexture( const std::string& texture_name,
            const std::string& web_url,
            Ogre::TextureUnitState* texture_unit_state = NULL,
            unsigned int website_view_width = __website_view_width,
            unsigned int website_view_height = __website_view_height,
            bool is_use_alpha = false,
            bool inject_input = false)
            :
            _website_view_width( website_view_width),
            _website_view_height( website_view_height),
            _inject_input( inject_input),
            IDynamicTexture( texture_name, texture_unit_state, is_use_alpha)
        {
            // create awesomium core singleton if necessary
            Awesomium::WebCore* awe_web_core (Awesomium::WebCore::instance());

            if( awe_web_core == NULL)
            {
                // Setup the Web Config
                Awesomium::WebConfig awe_web_config = Awesomium::WebConfig();
                awe_web_config.remote_debugging_port = DynamicOgreTexture::__awe_remote_debugging_port;

                awe_web_core = Awesomium::WebCore::Initialize( awe_web_config);
            }

            // Load a certain URL into WebView instance
            _awe_view = awe_web_core->CreateWebView( website_view_width, website_view_height);
            _awe_view->LoadURL( Awesomium::WebURL( Awesomium::WSLit(web_url.c_str())));

            // Wait for our WebView to finish loading
            while (_awe_view->IsLoading())
                update( __webtexture_sleep_ms);

            _awe_view->SetTransparent(is_use_alpha);
        }

        /**
         * Destructor.
         * Doesnt shut down the Awesomium WebCore singleton!
         */
        ~WebTexture()
        {
            _awe_view->Destroy();
        }


    public: // methods

        /**
         * Creates the dynamic texture.
         * @return A texture pointer with the image from the website.
         */
        Ogre::TexturePtr createDynamicTexture()
        {
            // Get the WebView's rendering Surface. The default Surface is of
            // type 'BitmapSurface', we must cast it before we can use it.
            Awesomium::BitmapSurface* awe_surface ((Awesomium::BitmapSurface*)_awe_view->surface());


            // if texture is not dirty or some error happened to the awesomium bitmap surface, return old texture...
            if (awe_surface == NULL || !awe_surface->is_dirty())
                return Ogre::TextureManager::getSingleton().getByName(_texture_name);

            // set alpha if changed
            if(_awe_view->IsTransparent() != _is_use_alpha)
                _awe_view->SetTransparent(_is_use_alpha);


            // if a texture with this name already exists... delete it
            auto res_ptr ( Ogre::TextureManager::getSingleton().getByName(_texture_name));
            if( res_ptr.getPointer())
                Ogre::TextureManager::getSingleton().remove( res_ptr);

            // Create the texture
            Ogre::TexturePtr texture(Ogre::TextureManager::getSingleton().createManual(
                _texture_name,
                Ogre::ResourceGroupManager::DEFAULT_RESOURCE_GROUP_NAME,
                Ogre::TEX_TYPE_2D,
                _website_view_width,
                _website_view_height,
                0,                // number of mipmaps
                Ogre::PixelFormat::PF_B8G8R8A8,     // pixel format
                Ogre::TU_DYNAMIC_WRITE_ONLY_DISCARDABLE)); // usage; should be TU_DYNAMIC_WRITE_ONLY_DISCARDABLE for
                                                           // textures updated very often (e.g. each frame)

            // Get the pixel buffer
            Ogre::HardwarePixelBufferSharedPtr pixel_buffer (texture->getBuffer());

            // Lock the pixel buffer and get a pixel box
            pixel_buffer->lock(Ogre::HardwareBuffer::HBL_DISCARD); // for best performance use HBL_DISCARD!
            const Ogre::PixelBox& pixel_box (pixel_buffer->getCurrentLock());
            Ogre::uint8* pDest (static_cast<Ogre::uint8*>(pixel_box.data));

            assert( awe_surface->row_span()*awe_surface->height() <= pixel_box.getConsecutiveSize());


            awe_surface->CopyTo( pDest, awe_surface->row_span(), 4, false, false);

            pixel_buffer->unlock();

            // dont know why this is necessary but it should not be...
            awe_surface->set_is_dirty(true);

            update( __webtexture_sleep_ms);

            return texture;
        }


        /// Inherited from IMouseListener
        bool onMouseMoved( const OIS::MouseEvent &arg)
        {
            if( !_inject_input)
                return false;

            auto mouse_position (InputManager::instance()->getMousePositionNormalized());
            _awe_view->InjectMouseMove( mouse_position.first * _website_view_width, mouse_position.second * _website_view_height);

            return true;
        }

        /// Inherited from IMouseListener
        bool onMousePressed( const OIS::MouseEvent &arg, OIS::MouseButtonID id)
        {
            if( !_inject_input)
                return false;

            _awe_view->InjectMouseDown( InputManager::instance()->OISMouseButton2AwesomiumMouseButton( id));

            return true;
        }

        /// Inherited from IMouseListener
        bool onMouseReleased( const OIS::MouseEvent &arg, OIS::MouseButtonID id)
        {
            if( !_inject_input)
                return false;

            _awe_view->InjectMouseUp( InputManager::instance()->OISMouseButton2AwesomiumMouseButton( id));

            return true;
        }

        /// Inherited from IKeyListener
        bool onKeyPressed ( const OIS::KeyEvent &arg)
        {
            if( !_inject_input)
                return false;

            // TODO

            return true;
        }

        /// Inherited from IKeyListener
        bool onKeyReleased( const OIS::KeyEvent &arg)
        {
            if( !_inject_input)
                return false;

            // TODO

            return true;
        }


        /*
         * Moves a mouse to a specified coordinate.
         * @param x The horizontal position to move the mouse to.
         * @param y The vertical position to move the mouse to.
         */
        inline void inject_mouse_move( int x, int y)
        {
            _awe_view->InjectMouseMove(x,y);
        }


        /**
         * Injects a mouse button input into awesomium view manually.
         *
         * @param button The mouse button to be injected (left/middle/right).
         * @param is_button_down Specifies, whether the mouse input shall be injected as
         * a button down or up event.
         * TRUE - press button
         * FALSE - release button
         */
        void inject_mouse_input(Awesomium::MouseButton button, bool is_button_down)
        {
            if( is_button_down)
                _awe_view->InjectMouseDown( button);
            else
                _awe_view->InjectMouseUp( button);
        }


        /**
         * Injects a keyboard input into awesomium view.
         * TODO evaluate, do something about native_key_codes
         * @param key_code The Awesomium key code that shall be injected into awesomium.
         * See Awesomium/WebKeyboardCodes.h for a complete list of codes.
         * @param modifiers The modifiers (ALT,STRG, ...) that modify the key_code.
         * Modifiers are encoded in the Awesomium::WebKeyboardEvent::Modifiers enum.
         * They can be OR'd together.
         * @param key_down Specifies whether the key is pressed down (true) or if it is up (false).
         */
        void inject_key_input( int key_code, int modifiers, bool key_down=true)
        {
            Awesomium::WebKeyboardEvent key_event;

            key_event.type =
                key_down ? Awesomium::WebKeyboardEvent::kTypeKeyDown : Awesomium::WebKeyboardEvent::kTypeKeyUp;

            char* buf (new char[20]);

            key_event.virtual_key_code = key_code;

            Awesomium::GetKeyIdentifierFromVirtualKeyCode( key_event.virtual_key_code, &buf);
            strcpy( key_event.key_identifier, buf);

            delete[] buf;

            key_event.modifiers = modifiers;

            // TODO whut about native_key_code
            //key_event.native_key_code = event.key.keysym.scancode;

            _awe_view->InjectKeyboardEvent( key_event);
        }

    private: // helpers


        /**
         * Sleeps a specified amount of time and updates the Awesomium WebCore instance.
         * @param sleep_ms The number of milliseconds to sleep before updating.
         */
        inline void update(int sleep_ms)
        {
            // Sleep a specified amount
            WEBTEXTURE_SLEEP( sleep_ms);

            // You must call WebCore::update periodically
            // during the lifetime of your application.
            Awesomium::WebCore::instance()->Update();
        }

    }; // END class WebTexture

} // END namespace DynamicOgreTexture


// undefine stuff
#ifdef WEBTEXTURE_SLEEP
    #undef WEBTEXTURE_SLEEP
#endif
