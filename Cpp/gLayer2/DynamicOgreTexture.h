/******************************************************************************
* @file File contains generic utility/convenience functions
* for the DynamicOgreTexture namespace.
* @author barn
* @version 20131028
******************************************************************************/
#pragma once

#include "defines.h"
#include "IDynamicTexture.hpp"
#include "ShmTexture.hpp"
#include "WebTexture.hpp"

namespace DynamicOgreTexture
{

    /**
     * Creates a dynamic Ogre Shared Memory Texture.
     * @param shm_name The unique name of the shared memory.
     * @param use_alpha Determines whether or not to use alpha channel of the shm texture.
     * TRUE - Use alpha channel.
     * FALSE - Don't use alpha channel.
     * @return Returns a dynamic texture with input from a shared memory.
     */
    IDynamicTexture* createShmTexture(const std::string& shm_name, bool use_alpha)
    {
        IDynamicTexture* ret;

        LOG_INIT
        LOG( "Creating new ShmTexture: " << shm_name << " ...");

        // set up shared memory reader & DynamicTexture
        try
        {
            de::dailab::interprocess::shm::DataReader* data_reader (new de::dailab::interprocess::shm::DataReader( shm_name ));
            LOG( "SUCCESS: Data Reader for SHM named " << shm_name << " successfully created.");

            ret = new DynamicOgreTexture::ShmTexture( shm_name /*name*/, data_reader);
            LOG( "SUCCESS: ShmTexture named " << shm_name << " successfully created.");
        }
        catch( std::exception& e)
        {
            LOG( "EXCEPTION: Failed creating data reader and/or DynamicShmTexture with SHM named: " << shm_name << "\n"
                 "Full exception message:\n" <<
                 e.what() << "\n");
            return 0;
        }

        ret->setUseAlpha(use_alpha);

        return ret;
    }

    /**
     * Creates a dynamic Ogre Texture from a website.
     * @param name The name of the texture. Shall be unique.
     * @param url The url of the website.
     * @param width The width of the virtual browser window that contains the website.
     * @param width The height of the virtual browser window that contains the website.
     * @param use_alpha Determines whether or not to use alpha channel of the website.
     * TRUE - Use alpha channel.
     * FALSE - Don't use alpha channel.
     * @param inject_input - Determines whether mouse input shall be injected into that website's view.
     * TRUE - Inject input.
     * FALSE - Don't inject input.
     * @return Returns a dynamic texture with input from a website.
     */
    IDynamicTexture* createWebTexture( const std::string& name,
                                       const std::string& url,
                                       unsigned int width,
                                       unsigned int height,
                                       bool use_alpha,
                                       bool inject_input)
    {
        IDynamicTexture* ret;

        LOG_INIT
        LOG( "Creating new WebTexture: " << name << " ...");

        DynamicOgreTexture::WebTexture* web_texture (new WebTexture( name         /*name*/,
                                                                    url          /*URL*/,
                                                                    0            /*texture_unit_state*/,
                                                                    width        /*width*/,
                                                                    height       /*height*/,
                                                                    use_alpha    /*use_alpha*/,
                                                                    inject_input /*inject_input*/));

        InputManager::instance()->getMouseListenerList().push_back( web_texture);

        ret = web_texture;

        LOG( "SUCCESS: WebTexture named " << name << " successfully created.");

        return ret;
    }


} // END namespace DynamicOgreTexture
