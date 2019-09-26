#pragma once

#include <DShow.h>
#include <streams.h>

#include <iostream>

class FooVideoRender : public CBaseVideoRenderer
{

public:

    virtual HRESULT DoRenderSample(IMediaSample *media_sample)
    {
        HRESULT ret(S_OK);
        
        //media_sample->

        BYTE* buffer(nullptr);
 
        media_sample->GetSize();

        HRESULT hr( media_sample->GetPointer( &buffer)); 
        
        std::cout << "\n\n";

        for( long i(0); i< media_sample->GetSize(); ++i)
            std::cout << buffer[i];

        std::cout << "\n\n";


        if( hr < 0)
        {
            // TODO RETURN OR SOMETHIN
        }

        // TODO: read buffer, pushit into some ogre texture and freu your ass off.


            /*

        BYTE* pBuffer = NULL;	
	    HR(pMediaSample->GetPointer(&pBuffer));

	    if(m_mediaType.subtype != MEDIASUBTYPE_RGB32)
	    {
		    const BYTE* rgbaBuffer = m_converter->convert_to_rgb32(pBuffer);
		    return m_renderer->DrawSample(rgbaBuffer);
	    }

	    return m_renderer->DrawSample(pBuffer);*/
    }


    virtual HRESULT CheckMediaType(const CMediaType *pmt)
    {
        HRESULT ret(S_OK);

        return ret;
    }
};