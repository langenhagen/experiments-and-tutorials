#include <string>
#include <conio.h>
#include <iostream>

#include <DShow.h>

std::string app_path(bool x=false);

void main()
{
    // Init MFC Initializers
    HRESULT hr( CoInitialize(NULL));
    if(hr < 0)
    {
        std::cout << "Error at CoInitialize(NULL): " <<  hr << "\n";
    }

    // Build Filter Graph
    IGraphBuilder *graph;
    hr = CoCreateInstance( CLSID_FilterGraph, // object identifier 
                           NULL, 
                           CLSCTX_INPROC, 
                           IID_IGraphBuilder, // interface identifier 
                           (void **)&graph); // pointer to the top-level interface
    if(hr < 0)
    {
        std::cout << "Error at CoCreateInstance pGraph: " <<  hr << "\n";
    }
    
    // Build Media Control
    IMediaControl *media_control;
    hr = graph->QueryInterface( IID_IMediaControl       /* interface identifier */, 
                                (void **)&media_control /* ptr to the interface */); 
    if(hr < 0)
    {
        std::cout << "Error at QueryInterface pMediaControl: " <<  hr << "\n";
    }
    

    // Build Media Event
    IMediaEvent *media_event;
    graph->QueryInterface(IID_IMediaEvent, (void **)&media_event);
    if(hr < 0)
    {
        std::cout << "Error at QueryInterface media_event: " <<  hr << "\n";
    }
    

    // Get file and render
    std::string filename = app_path().append( "\\click_green.avi");

    WCHAR	*filename_wchar = new WCHAR[filename.size()+1];
    MultiByteToWideChar( CP_ACP, 0, filename.c_str(), -1, filename_wchar, filename.size()+1);
    
    hr = graph->RenderFile( filename_wchar, NULL);
    if(hr < 0)
    {
        std::cout << "Error at renderFile: " <<  hr << "\n";
    }

    // Run the media file
    hr = media_control->Run();
    if(hr < 0)
    {
        std::cout << "media_control->run(): " <<  hr << "\n";
    }

    // Wait for completion and do the diddelidy.
    long evt_code(0);
    media_event->WaitForCompletion(INFINITE, &evt_code);

    std::cout << "PLAYBACK ENDED. evt_code= " << evt_code << "\n";

    // Uniregister MFC Components
    CoUninitialize();

    getch();

}


/** Retrieves current full path of the application.
TODO: currently works on windows only. Maybe make it multi-platform.

@param append_app_name Specifies, whether the executable name 
shall be on the returned string or not.
TRUE - retrieve full path with application file name.
FALSE - retrieve full path without application file name.
@return The full path of the running application.
*/
std::string app_path( bool append_app_name) 
{
    char buffer[MAX_PATH];
    std::string ret;

    GetModuleFileName( NULL, buffer, MAX_PATH );
    ret = std::string(buffer);
    std::string::size_type pos = ret.find_last_of( "\\/" );
    
    if( !append_app_name)
    {
        ret = ret.substr( 0, pos);
    }

    return ret;
}