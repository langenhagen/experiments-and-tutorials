#include <iostream>
#include <sstream>
#include <string>
#include <windows.h>

#include <Awesomium/WebCore.h>
#include <Awesomium/BitmapSurface.h>
#include <Awesomium/STLHelpers.h>


using namespace std;


// Various macro definitions
#define WIDTH   1280
#define HEIGHT  1024
//#define URL     "http://www.google.com"
//#define URL "http://www.socialmediadelivered.com/wp-content/uploads/2011/12/Nelson_GIF_by_sambu.gif"
#define URL "http://memebase.cheezburger.com/senorgif"

void Update(int sleep_ms);

void main(int argc, char* argv[])
{
	cout << "*** HELLO_AWESOMIUM ***" << endl;

	string web_url, shm_id;


	if( argc == 1)
	{
		cout << "Type web adress: ";           cin >> web_url;
		cout << "Type shared memory id: ";     cin >> shm_id;
	}
	else if( argc == 2)
	{
		web_url = argv[1];
	}
	else if( argc == 3)
	{
		web_url = argv[1];
		shm_id = argv[2];
	}
	else
	{
		cout	<< "Usage: Hello_Awesomium.exe or " << endl
				<< "Usage: Hello_Awesomium.exe <URL> or" << endl
				<< "       Hello_Awesomium.exe <URL> <shared memory id> (not workin atm)";

		cin.get();
		exit(1);
	}


	// Create the WebCore singleton with default configuration
	// ...is only possible when WebCore is not initialized
	Awesomium::WebCore* awe_web_core = Awesomium::WebCore::Initialize(Awesomium::WebConfig());



	// Create a new WebView instance with a certain width and height, using the
	// WebCore we just created
	Awesomium::WebView* awe_view = awe_web_core->CreateWebView( WIDTH, HEIGHT);


	// Load a certain URL into our WebView instance
	Awesomium::WebURL awe_url( Awesomium::WSLit(web_url.c_str()));
	awe_view->LoadURL(awe_url);

	std::cout << "Page is now loading..." << std::endl;

	// Wait for our WebView to finish loading
	while (awe_view->IsLoading())
		Update(50);

	std::cout << "Page has finished loading." << std::endl;

	std::cout << "Page title is: " << awe_view->title() << std::endl;


	// Update once more a little longer to allow scripts and plugins
	// to finish loading on the page.
	Update(300);


	// Get the WebView's rendering Surface. The default Surface is of
	// type 'BitmapSurface', we must cast it before we can use it.
	Awesomium::BitmapSurface* awe_surface = (Awesomium::BitmapSurface*)awe_view->surface();


	int cntr = 0;

	unsigned char dest_buffer[WIDTH*HEIGHT*4];

	// Make sure our surface is not NULL-- it may be NULL if the WebView
	// process has crashed.
	while(true)
	{
		if (awe_surface != NULL && awe_surface->is_dirty())
		{
			cout << "IS DIRTY\n";

			awe_surface->set_is_dirty(false);

			std::ostringstream oss;
			oss << "./result" << cntr++ << ".jpg";

			awe_surface->SaveToJPEG(Awesomium::WSLit( oss.str().c_str()));

			Update( 20);
		}
	}



	//// shutdown everything

	// Destroy our WebView instance
	awe_view->Destroy();

	// Update once more before we shutdown for good measure
	Update(100);

	// Destroy our WebCore instance
	Awesomium::WebCore::Shutdown();


	cout << endl << endl << "*** END OF PROGRAM ***";
	cin.sync();
	cin.get();
}


void Update(int sleep_ms)
{
	// Sleep a specified amount
#if defined(__WIN32__) || defined(_WIN32)
	Sleep(sleep_ms);
#elif defined(__APPLE__)
	usleep(sleep_ms * 1000);
#endif

	// You must call WebCore::update periodically
	// during the lifetime of your application.
	Awesomium::WebCore::instance()->Update();
}
