/******************************************************************************
/* @file Contains widely used defines and macros.
/* @author barn
/* @version 20131105
/******************************************************************************/
#pragma once


///////////////////////////////////////////////////////////////////////////////
// GENERIC DEFINES & MACROS


/// a classical exit macro for console applications
#define CONSOLE_EXIT( return_value) \
    printf("\n*** PRESS ANY KEY TO EXIT ***\n"); \
    getch(); \
    exit(return_value);


/// EXIT macro
#define EXIT(return_value) CONSOLE_EXIT(return_value)

/// cout with preceeding endl command
#define coutl std::cout << std::endl

///////////////////////////////////////////////////////////////////////////////
// APPLICATION SPECIFIC DEFINES & MACROS

#ifdef NDEBUG //release
    #define OGRE_PLUGIN_FILE "plugins.cfg"
    #define OGRE_RES_FILE "resources.cfg"
#else // debug
    #define OGRE_PLUGIN_FILE "plugins_d.cfg"
    #define OGRE_RES_FILE "resources_d.cfg"
#endif


#define GL2_OGRE_CONFIG_FILE "ogre.cfg"
#define GL2_CONFIG_FILE "gLayer2.cfg"

#define GL2_LOG_FILE "gLayer2.log"

#define GL2_SCENE_MANAGER_NAME "scene_manager0"
#define GL2_CAMERA_NAME "cam0"

#define GL2_WINDOW_PARAMETER_SECTION_NAME "Main Window"
#define GL2_LAYER_SECTION_NAME "[Layers]"
#define GL2_CONNECTION_SECTION_NAME "[JSON]"
#define GL2_MISC_SECTION_NAME "[MISC]"

/// convenience macro that help saving LoC when logging.
/// Invoke this macro before using LOG(MSG) within some scope.
#define LOG_INIT Ogre::Log* m_log (Ogre::LogManager::getSingleton().getLog( GL2_LOG_FILE)); \
    std::ostringstream log_oss;

/// convenience macro that help saving LoC when logging.
/// Just handle some string as parameter, concatenate with stream out operator << .
/// Dont forget to call LOG_INIT before using this macro within some certain scope.
#define LOG(MSG) log_oss << MSG; \
    m_log->logMessage( log_oss.str() ); \
    log_oss.str("");


//// MINOR MACROS //// <-- TODO: dunno if its better to put them into local files


/// frame smoothing period for ogre applications
#define GL2_OGRE_FRAME_SMOOTHING_PERIOD 0.5f

/// a simple resource that will be used by the compositors
#define GL2_RESOURCE_OMEGA_MERGE_TEXTURE "omega/material/merge_textures"

/// checks if a given string is "none"
#define IS_NONE(string) 0==string.compare("none")