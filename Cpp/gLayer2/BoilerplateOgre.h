/******************************************************************************
* @file File contains boiler plate code for setting up ogre.
* @author barn
* @version 20130430
******************************************************************************/
#pragma once

///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers


///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)
#include <OIS/OIS.h>
#include <OGRE/Ogre.h>

#include <Ogre/OgreRoot.h>
#include <Ogre/OgreConfigFile.h>

///////////////////////////////////////////////////////////////////////////////
// DEFINES and MACROS

///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS


namespace BoilerplateOgre
{

    /**
     * @brief Parses a resource cfg file.
     * @param fname The full filename, including the path.
     * @param recursive Indicates, whether to check the paths in the config file recursively or not.
     */
    void parse_resource_file( const char* fname, bool recursive = false) {
        Ogre::ConfigFile cf;
        cf.load( fname);

        Ogre::ResourceGroupManager& resource_manager (Ogre::ResourceGroupManager::getSingleton());

        // for every section
        auto section_iter (cf.getSectionIterator());
        while( section_iter.hasMoreElements()) {
            Ogre::String section_name (section_iter.peekNextKey());

            // get type / path of every ogre resource
            auto section (section_iter.getNext());
            for( auto it (section->begin()); it != section->end(); ++it) {
                resource_manager.addResourceLocation(
                    it->second /*name*/,
                    it->first /*location type*/,
                    section_name,
                    recursive);
            }
        }

    } // END function parse_and_init_resource_file

    /**
     * Creates an Ogre RenderWindow with parameters specified in a config file.
     * For further info which commands can be used confirm the source code and
     * http://www.ogre3d.org/docs/api/html/classOgre_1_1Root.html#a537b7d1d0937f799cfe4936f6b672620
     * Cite of Ogre documentation: "Parameters that are not recognized by a certain render system or platform
     * are silently ignored, unless stated otherwise".
     * @param ogre_root The root object that creates the window.
     * @param fname The full filename of the config, including the path.
     * @param section_name The section in the config file that stores a specific set of params.
     * @return Returns a new Ogre::RenderWindow
     */
    Ogre::RenderWindow* create_window_from_config_file( Ogre::Root* ogre_root, const char* fname, const char* section_name) {
        Ogre::RenderWindow* ret;

        Ogre::ConfigFile cf;
        cf.load( fname);

        // stores the window parameters
        Ogre::NameValuePairList window_params;

        // find right section
        auto section_iter (cf.getSectionIterator());
        while( section_iter.hasMoreElements()) {
            Ogre::String current_section_name (section_iter.peekNextKey());
            auto section (section_iter.getNext());
            if( stricmp( current_section_name.c_str(), section_name) != 0)
                continue;

            // get param/value pair
            for( auto it (section->begin()); it != section->end(); ++it)
                window_params[it->first] = it->second;
        }

        return ogre_root->createRenderWindow( window_params["title"],
                                              atoi( window_params["width"].c_str()),
                                              atoi( window_params["height"].c_str()),
                                                    stricmp( window_params["fullscreen"].c_str(), "true") == 0,
                                              &window_params);

    } // END create_RenderWindow_from_config_file

} // END namespace BoilerplateOgre
