/*****************************************************************************
 >>> This is gLayer 2
 It layers multiple screens either from a webpage
 or some shared memory onto each other. It is also able to
 create, enable and disable compositors via a JSON api.

 using
    - OgreSDK
    - Awesomium SDK
    - Boost
    - Shm_Ring_Buffer
    - Taksi (the taksi_shm_hack)
    - JsonCpp

TODO:
    - const correctness ?
    - destructors ?
    - input handling
    - JSON parsing only works inline


@file Main file of gLayer2 project
@author barn
@version 20131115

******************************************************************************/


///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers
#include "defines.h"
#include "common.h"
#include "BoilerplateOgre.h"
#include "InputManager.hpp"
#include "DynamicOgreTexture.h"
#include "IDynamicTexture.hpp"
#include "JsonClient.hpp"

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)
#include <iostream>
#include <assert.h>
#include <conio.h>

#include <OGRE/Ogre.h>
#include <OGRE/OgreRoot.h>
#include <OGRE/OgreFrustum.h>

#include <boost/bind.hpp>
#include <boost/thread.hpp>
#include <boost/asio.hpp>


///////////////////////////////////////////////////////////////////////////////
// DEFINES and MACROS


///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS


/// main function
int main(int argc, const char* argv[])
{
    std::cout << "**************************\n"
                 "*** Welcome to gLayer2 ***\n"
                 "**************************\n"
                 "\n"
                 "Usage: glayer2.exe\n"
                 "or     glayer2.exe <config-file>\n"
                 "\n"
                 "\tFirst one uses the standard config file \"gLayer2.cfg\" in the application directory.\n"
                 "\tSecond lets you use a specified config file.\n"
                 "\n";


    //// parse console parameters
    std::string glayer_config_file_name;
    if(argc >= 2)
    {
        glayer_config_file_name = argv[1];
    }
    else
    {
        glayer_config_file_name = GL2_CONFIG_FILE;
    }


    //// init ogre
    Ogre::Root* ogre ( new Ogre::Root( OGRE_PLUGIN_FILE, GL2_OGRE_CONFIG_FILE, GL2_LOG_FILE));

    if(!ogre->restoreConfig() && !ogre->showConfigDialog())
    {
        std::cerr << "ERROR: Failed to create OGRE root instance. Shutting down.\n";
        safe_release(ogre);
        EXIT(-1);
    }

    // init logger and LOG(MSG) macro
    LOG_INIT;

    ogre->setFrameSmoothingPeriod( GL2_OGRE_FRAME_SMOOTHING_PERIOD);

    BoilerplateOgre::parse_resource_file( OGRE_RES_FILE);

    ogre->initialise( false);
    Ogre::RenderWindow* ogre_window (
        BoilerplateOgre::create_window_from_config_file( ogre,
                                                         glayer_config_file_name.c_str(),
                                                         GL2_WINDOW_PARAMETER_SECTION_NAME));

    // necessary to init the window before initialization of the resources
    Ogre::ResourceGroupManager::getSingleton().initialiseAllResourceGroups();

    assert( !Ogre::MaterialManager::getSingleton().getByName( GL2_RESOURCE_OMEGA_MERGE_TEXTURE).isNull() );

    //// === init ogre complete ===

    // init InputManager
    InputManager* input_manager (InputManager::setup( ogre_window));


    //// create scene

    Ogre::SceneManager* scene_manager (ogre->createSceneManager( Ogre::ST_GENERIC, GL2_SCENE_MANAGER_NAME));
    Ogre::Camera* camera (scene_manager->createCamera( GL2_CAMERA_NAME));
    Ogre::Viewport* viewport (ogre_window->addViewport( camera));

    viewport->setBackgroundColour( Ogre::ColourValue( 0.0f, 0.0f, 0.0f));

    //// === create scene complete ===

    StringCompositorMap string_compositor_map;
    StringViewportMap string_viewport_map;
    string_viewport_map.insert( std::make_pair( "vp0", viewport));

    //// get miscellaneous config entries
    StringStringMap misc( readStringStringPairsFromFile( glayer_config_file_name, GL2_MISC_SECTION_NAME));
    const unsigned short awe_remote_debugging_port ( atoi(misc["awe_remote_debugging_port"].c_str()) );
    if( 0==awe_remote_debugging_port)
    {
        LOG( "ERROR: Failed to read variable remote_debugging_port from config file. "
            "Using standard remote debugging port " << DynamicOgreTexture::__awe_remote_debugging_port);
    }
    else
    {
        DynamicOgreTexture::__awe_remote_debugging_port = atoi( misc.at("awe_remote_debugging_port").c_str());
    }


    //// create web | shm textures from file
    StringStringMap layers (readStringStringPairsFromFile( glayer_config_file_name, GL2_LAYER_SECTION_NAME));
    for( auto it (layers.begin()); it != layers.end(); ++it)
    {
        // set up compositors
        Ogre::CompositorInstance* compositor (
            Ogre::CompositorManager::getSingleton().addCompositor( viewport, "omega/compositor/merge_textures"));

        std::string type (it->first);
        std::string name (it->second);

        DynamicOgreTexture::IDynamicTexture* texture;

        bool use_alpha (type.rfind(" -a") == type.length() -3); // TODO not safe in case of new parameters

        bool error (false);
        if( 0==stricmp( type.substr(0,4).c_str(), "http"))
        {
            // WEB TEXTURE

            texture = DynamicOgreTexture::createWebTexture( name,
                                                            name,
                                                            1920U,
                                                            1080U,
                                                            use_alpha,
                                                            false /*inject_input*/);
        }
        else if( 0==stricmp( type.substr(0,3).c_str(), "shm"))
        {
            // SHM TEXTURE

            texture = DynamicOgreTexture::createShmTexture( name, use_alpha);
        }
        else
        {
            // ERROR CASE

            LOG( "ERROR: Layer named " << name << " of type " << type << " unknown/not supported. Type not supported.");
            error = true;
        }

        if( !error)
        {
            compositor->addListener(texture);
            compositor->setEnabled(true);
            string_compositor_map.insert( std::make_pair( it->second, compositor));
        }
    }


    //// create tcp stream for json messages

    StringStringMap json_connection_info ( readStringStringPairsFromFile(glayer_config_file_name, GL2_CONNECTION_SECTION_NAME));
    const unsigned short port ( atoi(json_connection_info["port"].c_str()) );

    std::string in_context ( json_connection_info["in_context"]);
    std::string out_context( json_connection_info["out_context"]);


    boost::asio::io_service io_service;
    DynamicOgreTexture::JsonClient* json_client(0);
    boost::thread* thread(0);

    if( 0==json_connection_info["host_ip"].compare("") || 0==port)
    {
        LOG( "ERROR: Either host_ip= " << json_connection_info["host_ip"] << " or port= " << port <<
             " or both of given connection are not valid. host_ip must be a valid address and port a valid port >0.");
    }
    else
    {
        LOG( "Connecting to " << json_connection_info["host_ip"] << ":" << port << ".\n"
             "Subscribing to following contexts:\n"
             "    in_context=  " << in_context << "\n"
             "    out_context= " << out_context << " ...");

        try
        {
            json_client = new DynamicOgreTexture::JsonClient( io_service,
                                                              json_connection_info["host_ip"],
                                                              port,
                                                              string_compositor_map,
                                                              string_viewport_map);
        }
        catch( std::exception& e)
        {
            LOG( "EXCEPTION: Connection failed!\n" <<
                 e.what());
        }

        thread = new boost::thread( boost::bind(&boost::asio::io_service::run, &io_service));
    }

    // the timer to measure and publish the time since the last frame.
    Ogre::Timer timer;

    timer.getMilliseconds();

    //// render loop
    while( true)
    {
        if (ogre_window->isActive())
           ogre->renderOneFrame();
        else if (ogre_window->isVisible())
           ogre_window->setActive(true);

        Ogre::WindowEventUtilities::messagePump();
        input_manager->capture();


        // JSON part dont know why but must be inline...
        if( json_client!=0 && json_client->isReady())
        {
            json_client->handleJSONMessage( in_context);
            json_client->setReadyFalse();
        } // END json part

    } // END render loop

    //// *** shutdown everything ****

    LOG( "gLayer2 is shutting down...");

    for( auto it (string_compositor_map.begin()); it != string_compositor_map.end(); ++it)
    {
        Ogre::CompositorInstance* compositor (it->second);
        it->second = 0;
        safe_release(compositor);
    }


    thread->join();
    safe_release( thread);
    safe_release(json_client);
    safe_release(ogre);

    EXIT(0);
}
