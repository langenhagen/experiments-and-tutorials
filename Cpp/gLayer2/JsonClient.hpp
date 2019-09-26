/**
 * This file provides a Json TCP client that can handle json messages
 * which are concerned with manipulating the ogre compositors.
 * @author barn
 * @version 20131111
 */
#pragma once

///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers
#include "defines.h"
#include "common.h"
#include "DynamicOgreTexture.h"

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)
#include <sstream>

#include <boost/bind.hpp>
#include <boost/asio.hpp>


#include <json/value.h>
#include <json/reader.h>
#include <json/writer.h>

///////////////////////////////////////////////////////////////////////////////
// DEFINES and MACROS

///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS

using boost::asio::ip::tcp;
namespace asio = boost::asio;


namespace DynamicOgreTexture
{
    /**
     * This class represents a TCP client based on boost asio
     * in order to handle JSON messages.
     */
    class JsonClient
    {

    private: // vars

        /// Specifies, whether the Client is connected or not.
        bool _connected;

        /// The io service.
        boost::asio::io_service& _io_service;

        /// The socket.
        tcp::socket _socket;

        /// This enum stores the maximum message length.
        enum { max_msg_length = 1024 };

        /// The freshly received message.
        char _in_message[max_msg_length];

        /// The cut-to-length string variant of _in_message.
        std::string _message;

        /// The map of compositors and their keys.
        StringCompositorMap& _string_compositor_map;

        /// The map of viewports and their keys.
        StringViewportMap& _string_viewport_map;

        /// Specifies, if a message lies ready at _message.
        bool _ready;

        // A Mutex ensuring correct parallel usage.
        boost::mutex _mutex;

    public: // constructors & destructor

        /**
         * Constructor #1.
         * @param io_service The io_service to be used.
         * @param host The host IP.
         * @param port The Port number at the host endpoint.
         * @param string_compositor_map The map of compositors and their keys to work on.
         * @param string_viewport_map The map of viewports and their keys to work on.
         */
        JsonClient( boost::asio::io_service& io_service,
                    const std::string& host,
                    const short port,
                    StringCompositorMap& string_compositor_map,
                    StringViewportMap& string_viewport_map)
        :
        _ready(false),
        _connected(false),
        _io_service(io_service),
        _socket(io_service),
        _string_compositor_map(string_compositor_map),
        _string_viewport_map(string_viewport_map)
        {
            tcp::endpoint endpoint( asio::ip::address::from_string(host),
                                    port);

            _socket.async_connect( endpoint,
			                       boost::bind( &JsonClient::onConnect,
                                                this,
                                                asio::placeholders::error));
        }

    public: // methods

        bool isReady()
        {
            lock_t lock(_mutex);
            return _ready;
        }

        void setReadyFalse()
        {
            lock_t lock(_mutex);
           _ready = false;
        }

        /**
         * If a message is available, interpretes and handles a JSON message that's concerned with manipulating the
         * compositor chain or its compositors.
         * @param in_context The context on which to listen when interpreting json messages.
         */
        void handleJSONMessage( const std::string& in_context)
        {
            lock_t lock(_mutex);

            LOG_INIT

            LOG( "Incoming JSON-Message:\n" << _message);
            Json::Value json_root;   // will contain the json_root value after parsing.
            Json::Reader json_reader;
            bool parsingSuccessful (json_reader.parse( _message, json_root ));

            if ( !parsingSuccessful )
            {
                // report to the user the failure and their locations in the document.
                LOG( "ERROR: Failed to parse JSON\n" << json_reader.getFormatedErrorMessages());
                return;
            }


            // INTERCEPT ERRORS
            if( 0!=json_root.get("ctx", "none").asString().compare( in_context) )
            {
                // return gracefully if not compositors-business
                return;
            }
            if( IS_NONE(json_root.get("id",  "none").asString()))
            {
                LOG( "ERROR: No field called ID in JSON Message.");
                return;
            }
            // ERRORS concerning ctx, id INTERCEPTED

            std::string evt (json_root.get("evt", "none").asString());

            //// TRIGGER ACTIONS
                 if( 0==evt.compare( "create"   ))      createCompositor( json_root);
            else if( 0==evt.compare( "enable"   ))      enableCompositor( json_root.get("id", "none").asString(), true);
            else if( 0==evt.compare( "disable"  ))      enableCompositor( json_root.get("id", "none").asString(), false);
            else if( 0==evt.compare( "change_vp"))      changeViewport  ( json_root);

            else
            {
                // error case
                LOG( "ERROR: Incoming JSON message's evt value \"" << evt << "\" is unknown and cannot be handled.");
            }
        }

        /// Calls the private doRead() function.
        void read()
        {
            // idle while message is not read.
            while( _ready==true)
                ;

            _socket.async_read_some( asio::buffer( _in_message, max_msg_length),
                                     boost::bind( &JsonClient::onRead,
                                                  this,
                                                  asio::placeholders::error,
                                                  asio::placeholders::bytes_transferred));
        }

        /** Retrieves the cut-to-length string representation of the newes received message.
         * @return The message.
         */
        std::string message()
        {
            lock_t lock(_mutex);
            _ready = false;
            return _message;
        }

    private: // actions

        /**
         * Creates a new compositor from a JSON value and puts this new compositor in the list.
         * param json_value The JSON value that represents the JSON notation with all
         * entries for the object call.
         * @return returns TRUE in case of success, otherwise returns FALSE.
         */
        bool createCompositor( Json::Value json_root)
        {
            LOG_INIT

            std::string  id            (json_root.get("id",  "none").asString()); /* asserted by handleJSONMessage: IS_NONE(id)==false */
            std::string  listener      (json_root.get("listener", "none").asString());
            int          pos           (json_root.get("pos", -1).asInt());
            std::string  viewport_name (json_root.get("viewport", "none").asString());
            std::string  uri           (json_root.get("uri", "none").asString());
            unsigned int width         (json_root.get("width", 1920U).asUInt());
            unsigned int height        (json_root.get("height", 1080U).asUInt());
            bool         use_alpha     (json_root.get("use_alpha", false).asBool());
            bool         inject_input  (json_root.get("inject_input", false).asBool());

            if( IS_NONE(listener) || IS_NONE( viewport_name) || IS_NONE(uri))
            {
                LOG( "ERROR: In the incoming JSON create message, there's either no listener, viewport or uri specified:\n"
                        "listener: " << listener << "\t viewport: " << viewport_name << "\t uri: " << uri << ".");
                return false;
            }


            if( !isViewportValid( viewport_name))
                return false;

            Ogre::Viewport* viewport(_string_viewport_map.at(viewport_name));


            // set up compositors
            Ogre::CompositorInstance* compositor (
                Ogre::CompositorManager::getSingleton().addCompositor( viewport,
                                                                       "omega/compositor/merge_textures",
                                                                       pos));

            DynamicOgreTexture::IDynamicTexture* texture(0);

            if( 0==listener.compare("web"))
            {
                texture = DynamicOgreTexture::createWebTexture( id,
                                                                uri,
                                                                width,
                                                                height,
                                                                use_alpha,
                                                                inject_input);
            }
            else if( 0==listener.compare("shm"))
            {
                texture = DynamicOgreTexture::createShmTexture( uri, use_alpha);
            }
            else
            {
                LOG( "ERROR: In the incoming JSON create message, the listener of type " << listener <<
                     " unknown/not supported. Type not supported.");
                return false;
            }

            compositor->addListener(texture);
            compositor->setEnabled(true);

            _string_compositor_map.insert( std::make_pair( id, compositor));

            return true;
        }

        /**
         * Enables the compositor with the given ID.
         * @param id The ID of the compositor to be enabled.
         * @param enable Specifies, if the compositor shall be enabled or disabled.
         * TRUE  - enable compositor
         * FALSE - disable compositor
         * @return returns TRUE in case of success, otherwise returns FALSE.
         */
        bool enableCompositor( const std::string& id, bool enable)
        {
            LOG_INIT

            bool ret(false);

            if( isIDValid( id))
            {
                _string_compositor_map.at(id)->setEnabled(enable);
                LOG( "Compositor " << id << " enabled.");
                ret = true;
            }

            return ret;
        }

        bool changeViewport( Json::Value json_root)
        {
            bool ret(false);

            return ret;
        }


    private: // helpers


        /** Logs a boost error message with the given error
        and a string that specifies a user given information.
        @param error_code The original error code.
        @param error_in An additional information string
        that precedes the other information given by the error code.
        */
        void logErrorMessage( const boost::system::error_code& error_code, const std::string& error_in)
        {
            LOG_INIT;

            LOG( "ERROR: " << error_in << ":\n"
                 "   Error No:  " << error_code.value() << "\n"
                 "   Error Msg: " << error_code.message() << "\n");
        }



        /** Utility function that checks if the given ID corresponds to a compositor
        in the _string_compositor_map and logs a message in case of non-existence.
        Purpose is simple. Prevents you from always writing a try/catch when using
        an existing compositor.
        @param id The id to look for.
        @return Returns TRUE in case of existence of an entry with the given ID,
        otherwise returns FALSE.
        */
        inline bool isIDValid( const std::string& id)
        {
            bool ret(false);

            try
            {
                _string_compositor_map.at(id);
                ret = true;
            }
            catch( std::exception& e)
            {
                LOG_INIT
                LOG( "EXCEPTION: Couldn't find compositor with corresponding ID " << id << ".\n" <<
                    e.what());
            }

            return ret;
        }


        /** Utility function that checks if the given viewport's name corresponds to a viewport
        in the _string_viewport_map and logs a message in case of non-existence.
        Purpose is simple. Prevents you from always writing a try/catch when referencing
        to a (maybe not existent) viewport.
        @param viewport_name The viewport's associated name to look for.
        @return Returns TRUE in case of existence of an entry with the given name,
        otherwise returns FALSE.
        */
        inline bool isViewportValid( const std::string& viewport_name)
        {
            bool ret(false);

            try
            {
                _string_viewport_map.at(viewport_name);
                ret = true;
            }
            catch( std::exception& e)
            {
                LOG_INIT
                LOG( "EXCEPTION: Couldn't find viewport with associated name" << viewport_name << ".\n" <<
                    e.what());
            }

            return ret;
        }

    private: // handlers

        /**
         * Handles a new connection.
         * @param error_code The boost error code.
         */
        void onConnect(const boost::system::error_code& error_code)
        {
            if (error_code)
            {
                logErrorMessage(error_code, "JsonClient::onConnect");
            }
            else
            {
                LOG_INIT;
                LOG("JsonClient::onConnect: connected\n");

                _connected = true;

                read();
            }
        }


        /**
         * Handles the completion of a read message, meaning starts a new reading process
         * for continuous reading.
         * @param error_code The error code that is handed to this handler.
         * @param bytes The number of bytes transferred.
         */
        void onRead( const boost::system::error_code& error_code, size_t bytes)
        {
            if( error_code == asio::error::eof)
            {
                logErrorMessage(error_code, "JsonClient::onRead: disconnected");
                _connected = false;
            }
            else if (error_code)
            {
                logErrorMessage(error_code, "JsonClient::onRead");
            }
            else
            {
                {
                    lock_t lock(_mutex);
                    _message = std::string(_in_message, bytes);
                    _ready = true;
                }
                read();
            }
        }

    }; // END class JsonClient

} // END namespace DynamicOgreTexture
