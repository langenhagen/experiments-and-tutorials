#include <iostream>
#include <boost/gil/gil_all.hpp>
#include <boost/mpl/vector.hpp>
#include <boost/gil/extension/io_new/bmp_all.hpp>
#include <boost/gil/pixel.hpp>

using namespace std;
using namespace boost::gil;


void main()
{
	
	cout << "Reading image... ";
	rgb8_image_t img;
	read_image( "botticelli-primavera.bmp", img, bmp_tag() );
	rgb8c_view_t view1 = img._view;
	cout << "done\n\n";

	cout << "Color converting & resizing... ";
	unsigned char* ultra_pixels = new unsigned char[ view1.num_channels() * view1.width() * view1.height() / 4 ];
	rgb8c_view_t::iterator it = view1.begin();

	int width = view1.width();
	rgb8c_view_t::iterator it1;
	rgb8c_view_t::iterator itWidth; 
	rgb8c_view_t::iterator it1Width;
	it1 = it+1;
	itWidth = it + width; 
	it1Width = itWidth + 1;

	for ( int i = 0; i < view1.num_channels() * width * view1.height() / 4 ; i=i+3, it+=2, it1+=2, itWidth+=2, it1Width+=2)
	{	

		ultra_pixels[i+0] = ( (*it)[2] + (*(it1))[2] + (*(itWidth))[2] + (*(it1Width))[1]) / 4;
		ultra_pixels[i+1] = ( (*it)[0] + (*(it1))[0] + (*(itWidth))[0] + (*(it1Width))[0]) / 4;
		ultra_pixels[i+2] = ( (*it)[1] + (*(it1))[1] + (*(itWidth))[1] + (*(it1Width))[1]) / 4;

		if( (it - view1.begin() +2 ) % width == 0)
		{
			it = itWidth;
			it1 = it+1;
			itWidth = it + width; 
			it1Width = itWidth + 1;
		}
	}
	rgb8c_view_t view2 = interleaved_view( width / 2 , view1.height() / 2, (const rgb8c_pixel_t*)ultra_pixels, view1.num_channels()*width / 2);

	cout << "done\n\n";



	/*cout << "view 1 Pixel_size: " << view1.pixels().pixel_size() << endl;
	cout << "view 1Row_size: " << view1.pixels().row_size() << endl;

	cout << "view 2 Pixel_size: " << view2.pixels().pixel_size() << endl;
	cout << "view 2 Row_size: " << view2.pixels().row_size() << endl;*/

	cout << "Save file to disk... ";
	boost::gil::write_view("bild.bmp", view2, bmp_tag()); 
	cout << "done" << endl;

	cin.get();
}