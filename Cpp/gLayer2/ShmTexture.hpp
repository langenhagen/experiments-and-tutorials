/**
 * This file provides a FrameListener that maps a dynamic texture
 * from some shared memory to some Texture within Ogre using the
 * SharedMemory Ring Buffer.
 * @author barn
 * @version 20130513
 */
#pragma once

///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers
#include "IDynamicTexture.hpp"
#include "KinectData.hpp"
#include "DataReader.hpp"

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)
#include <assert.h>

#include <OGRE/OgreHardwarePixelBuffer.h>

///////////////////////////////////////////////////////////////////////////////
// DEFINES and MACROS

///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS


namespace DynamicOgreTexture
{
    /**
     * This class represents an exception if an unhandled pixel format
     * occurs.
     */
    class PixelFormatException : public std::exception
    {
    private: // vars

        /// the pixel format from the kinect data.
        KinectData::GeneralImage::Format _format;

        /// detail information of the exception;
        const std::string _detail_info;

    public: // constructor & destructor

        /**
         * Constructor
         * @param format The pixel format that raises problems.
         * @param detail_info Additional information about this exception.
         */
        PixelFormatException( KinectData::GeneralImage::Format format, const std::string& detail_info = "")
            : _format(format),
            _detail_info(detail_info)
        {}

        /// Destructor
        ~PixelFormatException() throw()
        {}

    public: // methods

        /// What
        const char* what() const throw()
        {
            std::ostringstream oss;
            oss <<  "The pixel format " << _format << " raised an exception."
                << "\nDetail:\n" << _detail_info;

            #ifdef GL2_LOG_FILE
                auto log ( Ogre::LogManager::getSingleton().getLog( GL2_LOG_FILE));
                log->logMessage( oss.str());
            #endif

            return oss.str().c_str();
        }
    }; // End class PixelFormatException


    /**
     * This class represents an exception if an unhandled header format
     * occurs.
     */
    class HeaderFormatException : public std::exception
    {
    private: // vars

        /// the pixel format from the kinect data.
        const KinectData::GeneralImage* _image;

        /// detail information of the exception;
        const std::string _detail_info;

    public: // constructor & destructor

        /**
         * Constructor
         * @param image The image with the header that causes problems.
         * @param detail_info Additional information about this exception.
         */
        HeaderFormatException( const KinectData::GeneralImage* image, const std::string& detail_info = "")
            : _image(image),
            _detail_info(detail_info)
        {}

        /// Destructor
        ~HeaderFormatException() throw()
        {}

    public: // methods

        /// What
        const char* what() const throw()
        {
            std::ostringstream oss;
            oss <<  "The header format of image " << _image << " raised an exception."
                << "\nDetail:\n" << _detail_info;

            #ifdef GL2_LOG_FILE
                auto log ( Ogre::LogManager::getSingleton().getLog( GL2_LOG_FILE));
                log->logMessage( oss.str());
            #endif

            return oss.str().c_str();
        }
    }; // End class HeaderFormatException



    /**
     * This class represents a FrameListener, that maps a dynamic shared memory texture
     * onto some texture pointer. It is also viable as a Compositor listener.
     */
    class ShmTexture : public IDynamicTexture
    {
    private: // vars

        /// The shared memory reader.
        de::dailab::interprocess::shm::DataReader* _reader;


    public: // constructor & destructor

        /**
         * Constructor #1.
         * @param texture_name The name of the texture. Should be unique.
         * @param reader the shared memory reader that provides the raw data.
         * @param texture_unit_state the texture unit state on which the raw data
         * shall be printed as a picture. Compositor listeners don't need a texture_unit_state.
         * With this texture used solely as a Compositor Listener, NULL should be sufficient.
         * @param is_use_alpha Specifies, whether to regard alpha channel of the dynamic texture.
         */
        ShmTexture( const std::string& texture_name, de::dailab::interprocess::shm::DataReader* reader,
            Ogre::TextureUnitState* texture_unit_state=NULL, bool is_use_alpha=false)
            : _reader( reader),
            IDynamicTexture( texture_name, texture_unit_state, is_use_alpha)
        {}

        /// Destructor
        ~ShmTexture()
        {}


    public: // methods


        /**
         * Creates the dynamic texture.
         * @return A texture pointer with the image from the shared memory.
         */
        Ogre::TexturePtr createDynamicTexture() throw ( PixelFormatException, HeaderFormatException)
        {
            // grab the picture from the shared memory
            KinectData::GeneralImage img;
            auto bytes_read (_reader->readData(
                img.datagram.dataBytes,
                img.totalLength(),
                de::dailab::interprocess::shm::DataReader::DataRequest::NEWEST_AVAILABLE
                ));

            // grab the right pixel format
            Ogre::PixelFormat pixel_format;
            switch( img.format() )
            {
            case KinectData::GeneralImage::Format::RGB24:
                pixel_format = Ogre::PixelFormat::PF_R8G8B8;
                break;

            case KinectData::GeneralImage::Format::BGR24:
                pixel_format = Ogre::PixelFormat::PF_B8G8R8;
                break;

            case KinectData::GeneralImage::Format::RGBA32:
                pixel_format = Ogre::PixelFormat::PF_R8G8B8A8;
                break;

            case KinectData::GeneralImage::Format::BGRA32:
                pixel_format = Ogre::PixelFormat::PF_B8G8R8A8;
                break;

            default:
                throw PixelFormatException( img.format());
            }

            // helper vars
            //----------------------------------------------
            const int image_length (img.imageLength());
            const int image_width (img.width());
            const int image_height (img.height());
            const int image_bpp (img.bytesPerPixel());
            const int bytes_per_line (image_width*image_bpp);
            //----------------------------------------------*/


            // if a texture with this name already exists... delete it
            auto res_ptr (Ogre::TextureManager::getSingleton().getByName(_texture_name));
            if( res_ptr.getPointer())
                Ogre::TextureManager::getSingleton().remove( res_ptr);

            // Create the texture
            Ogre::TexturePtr texture(Ogre::TextureManager::getSingleton().createManual(
                _texture_name,
                Ogre::ResourceGroupManager::DEFAULT_RESOURCE_GROUP_NAME,
                Ogre::TEX_TYPE_2D,
                image_width,
                image_height,
                0,                // number of mipmaps
                pixel_format,     // pixel format
                Ogre::TU_DYNAMIC_WRITE_ONLY_DISCARDABLE)); // usage; should be TU_DYNAMIC_WRITE_ONLY_DISCARDABLE for
                                                           // textures updated very often (e.g. each frame)

            // Get the pixel buffer
            Ogre::HardwarePixelBufferSharedPtr pixel_buffer (texture->getBuffer());

            // Lock the pixel buffer and get a pixel box
            pixel_buffer->lock(Ogre::HardwareBuffer::HBL_DISCARD); // for best performance use HBL_DISCARD!
            const Ogre::PixelBox& pixel_box ( pixel_buffer->getCurrentLock());
             Ogre::uint8* pDest (static_cast<Ogre::uint8*>(pixel_box.data));


            assert( image_length <= pixel_box.getConsecutiveSize());
            assert( image_length > 0);


            // write image to ogre texture
            // check if pixel information comes in reverse order...
            switch( img.datagram.dataStruct.header.reserved1)
            {
            case 0: // plain std behaviour

                for( int i (0); i < image_length; i+= image_bpp)
                {
                    *pDest++ = img.datagram.dataStruct.payload.image[i];    // red
                    *pDest++ = img.datagram.dataStruct.payload.image[i+1];  // green
                    *pDest++ = img.datagram.dataStruct.payload.image[i+2];  // blue
                    *pDest++ = _is_use_alpha ? img.datagram.dataStruct.payload.image[i+3] : 255;  // alpha
                }
                break;

            case 1: // pixels in reverse order

                for( int i (image_length-1); i>=0; --i)
                {
                    *pDest++ = img.datagram.dataStruct.payload.image[i];
                }
                break;

            case 2: // lines reverse

                for( int line (image_height-1); line >= 0; --line)
                {
                    for( int px(0); px < bytes_per_line; px+=image_bpp)
                    {
                        const int pos (line*bytes_per_line + px);

                        // do not understand why r/g/b are shifted this strange way...
                        *pDest++ = img.datagram.dataStruct.payload.image[pos+2];  // red
                        *pDest++ = img.datagram.dataStruct.payload.image[pos];    // green
                        *pDest++ = img.datagram.dataStruct.payload.image[pos+1];  // blue

                        // alpha channel dependent on whether 24 bit or 32 bit encoding...
                        if( image_bpp == 4)
                            *pDest++ = _is_use_alpha ? img.datagram.dataStruct.payload.image[pos+3] : 255;  // alpha
                        else if( image_bpp == 3)
                            pDest++;  // leave out alpha
                        else
                        {
                            // TODO: unhandled behaviours
                            assert(false);
                        }
                    }
                }
                break;

            case 3: // do RGBA32 but discard alpha (set alpha to 255)

                for( int i (0); i < image_length; i+= image_bpp)
                {
                    *pDest++ = img.datagram.dataStruct.payload.image[i];    // red
                    *pDest++ = img.datagram.dataStruct.payload.image[i+1];  // green
                    *pDest++ = img.datagram.dataStruct.payload.image[i+2];  // blue
                    *pDest++ = 255;  // alpha
                }
                break;

            default:
                std::ostringstream oss;
                oss << "img.datagram.dataStruct.header.reserved1 is set to " << img.datagram.dataStruct.header.reserved1 << "\n"
                       "Cannot handle this value within the Shared MemoryTexture.hpp.\n";
                std::cout << oss.str();

                throw HeaderFormatException( &img, oss.str());
            }

            pixel_buffer->unlock();

            return texture;
        }

    }; // END class ShmTexture

} // END namespace DynamicOgreTexture
