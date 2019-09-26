#include <stack>
#include <sstream>

#include <cstdio>
#include <windows.h>
#include <tlhelp32.h>

#include <iostream>


#define TITLE_LENGTH 256
#define SLEEP_MS 2000
#define INDENT << endl << cstr_indent << 

static std::stack<HWND> __process_stack = std::stack<HWND>();

void printHandleInfo( HWND hwnd);

using namespace std;

BOOL SaveToFile(HBITMAP hBitmap, LPCTSTR lpszFileName)
{
  HDC hDC;

  int iBits;

  WORD wBitCount;

  DWORD dwPaletteSize=0, dwBmBitsSize=0, dwDIBSize=0, dwWritten=0;

  BITMAP Bitmap;

  BITMAPFILEHEADER bmfHdr;

  BITMAPINFOHEADER bi;

  LPBITMAPINFOHEADER lpbi;

  HANDLE fh, hDib, hPal,hOldPal=NULL;
 
  hDC = CreateDC("DISPLAY", NULL, NULL, NULL);
  iBits = GetDeviceCaps(hDC, BITSPIXEL) * GetDeviceCaps(hDC, PLANES);
  DeleteDC(hDC);
  if (iBits <= 1)
    wBitCount = 1;
  else if (iBits <= 4)
    wBitCount = 4;
  else if (iBits <= 8)
    wBitCount = 8;
  else
    wBitCount = 24;
  GetObject(hBitmap, sizeof(Bitmap), (LPSTR)&Bitmap);
  bi.biSize = sizeof(BITMAPINFOHEADER);
  bi.biWidth = Bitmap.bmWidth;
  bi.biHeight = Bitmap.bmHeight;
  bi.biPlanes = 1;
  bi.biBitCount = wBitCount;
  bi.biCompression = BI_RGB;
  bi.biSizeImage = 0;
  bi.biXPelsPerMeter = 0;
  bi.biYPelsPerMeter = 0;
  bi.biClrImportant = 0;
  bi.biClrUsed = 0;
  dwBmBitsSize = ((Bitmap.bmWidth * wBitCount + 31) / 32) * 4 * Bitmap.bmHeight;

  hDib = GlobalAlloc(GHND,dwBmBitsSize + dwPaletteSize + sizeof(BITMAPINFOHEADER));
  lpbi = (LPBITMAPINFOHEADER)GlobalLock(hDib);
  *lpbi = bi;

  hPal = GetStockObject(DEFAULT_PALETTE);
  if (hPal)
  {
    hDC = GetDC(NULL);
    hOldPal = SelectPalette(hDC, (HPALETTE)hPal, FALSE);
    RealizePalette(hDC);
  }
 

  GetDIBits(hDC, hBitmap, 0, (UINT) Bitmap.bmHeight, (LPSTR)lpbi + sizeof(BITMAPINFOHEADER)
    +dwPaletteSize, (BITMAPINFO *)lpbi, DIB_RGB_COLORS);

  if (hOldPal)
  {
    SelectPalette(hDC, (HPALETTE)hOldPal, TRUE);
    RealizePalette(hDC);
    ReleaseDC(NULL, hDC);
  }

  fh = CreateFile(lpszFileName, GENERIC_WRITE,0, NULL, CREATE_ALWAYS,
    FILE_ATTRIBUTE_NORMAL | FILE_FLAG_SEQUENTIAL_SCAN, NULL);
 
  if (fh == INVALID_HANDLE_VALUE)
    return FALSE;

  bmfHdr.bfType = 0x4D42; // "BM"
  dwDIBSize = sizeof(BITMAPFILEHEADER) + sizeof(BITMAPINFOHEADER) + dwPaletteSize + dwBmBitsSize;
  bmfHdr.bfSize = dwDIBSize;
  bmfHdr.bfReserved1 = 0;
  bmfHdr.bfReserved2 = 0;
  bmfHdr.bfOffBits = (DWORD)sizeof(BITMAPFILEHEADER) + (DWORD)sizeof(BITMAPINFOHEADER) + dwPaletteSize;

  WriteFile(fh, (LPSTR)&bmfHdr, sizeof(BITMAPFILEHEADER), &dwWritten, NULL);

  WriteFile(fh, (LPSTR)lpbi, dwDIBSize, &dwWritten, NULL);
  GlobalUnlock(hDib);
  GlobalFree(hDib);
  CloseHandle(fh);
  return TRUE;
}


BOOL CALLBACK EnumChildProc(HWND hwnd, LPARAM lParam)
{
	//printHandleInfo( hwnd);
	 
	char window_text[TITLE_LENGTH];
	GetWindowText( hwnd, window_text,  TITLE_LENGTH);
	
	if( stricmp( window_text, "capture0* [512x512, 100%]") == 0)
	{
		int i=0;
		
		while(1)
		{

			RECT rc;
		
			GetClientRect(hwnd, &rc);

			//create
			HDC hdcScreen = GetDC(NULL);
			HDC hdc = CreateCompatibleDC(hdcScreen);
			HDC hdc2 = CreateCompatibleDC(hdcScreen);
		
			HBITMAP hbmp = CreateCompatibleBitmap(hdcScreen, 
				rc.right - rc.left, rc.bottom - rc.top);
			HBITMAP hbmp2 = CreateCompatibleBitmap(hdcScreen, 
				rc.right - rc.left - 4, rc.bottom - rc.top - 16 - 22);
		
			SelectObject(hdc, hbmp);
			SelectObject(hdc2, hbmp2);

		
			//Print to memory hdc
			PrintWindow(hwnd, hdc, PW_CLIENTONLY);
		
			BitBlt( 
				hdc2,
				0, 
				0, 
				rc.right - rc.left,// - 6, 
				rc.bottom - rc.top,// - 16 - 23, 
				hdc, 
				2,
				22,
				SRCCOPY); 


			/*
			// save to bitmap
			std::string name("capture");
			std::stringstream ss;
			ss << name << i++ << ".bmp";
			bool result = SaveToFile( hbmp2, ss.str().c_str());
			*/
			//copy to clipboard
			OpenClipboard(NULL);
			EmptyClipboard();
			SetClipboardData(CF_BITMAP, hbmp2);
			CloseClipboard();
			/**/
			//release

			DeleteDC(hdc);
			DeleteDC(hdc2);
			DeleteObject(hbmp);
			DeleteObject(hbmp2);
			ReleaseDC(NULL, hdcScreen);

			Sleep(SLEEP_MS);

		} // END while(1)

		cout << "success copy to clipboard, please paste it to the 'mspaint'" << endl;
		cin.get();
	}
	
	return TRUE; // must return TRUE; If return is FALSE it stops the recursion
}


void main()
{
	auto hwnd0 = FindWindow(NULL, "Texture Maker Enterprise v3.0.3");
	auto hwnd1 = FindWindowEx( hwnd0, NULL, "MDIClient", NULL);
	auto hwnd = FindWindowEx( hwnd1, NULL, NULL, "capture0* [512x512, 100%]");
		
	__process_stack.push(hwnd);

	if (hwnd != 0)
	{
		cout << "** You caught the window! **" << endl
			 << endl;

		printHandleInfo( hwnd);

		EnumChildWindows( hwnd, EnumChildProc, 0);
	}
	else
	{
		cout << "open the freakin window..." << endl;
	}

	cin.get();
}


void printHandleInfo( HWND hwnd)
{
	WINDOWINFO wi;
	GetWindowInfo(hwnd, &wi);
	
	char window_text[TITLE_LENGTH];
	char window_class[TITLE_LENGTH];

	GetWindowText( hwnd, window_text,  TITLE_LENGTH);
	GetClassName(  hwnd, window_class, TITLE_LENGTH);
	
	HWND parent_hwnd( GetParent( hwnd));


	if( __process_stack.top() == parent_hwnd)
		__process_stack.push( hwnd);

	std::string str_indent;
	
	for( int i=1; i<__process_stack.size(); ++i)
		str_indent.append("\t");
	auto cstr_indent = str_indent.c_str();

	// define IND << cstr_indent <<

	cout INDENT "hwnd=                 " << hwnd
		 INDENT "parent=               " << parent_hwnd
		 INDENT "window_text=          " << window_text
		 INDENT "window_class=         " << window_class 
		 INDENT "wi.atomWindowType=    " << wi.atomWindowType
		 INDENT "wi.dwWindowStatus=    " << wi.dwWindowStatus
		 INDENT "wi.cbSize=            " << wi.cbSize
		 INDENT "wi.cxWindowBorders=   " <<  wi.cxWindowBorders
		 INDENT "wi.cyWindowBorders=   " << wi.cyWindowBorders
		 INDENT "wi.dwExStyle=         " << wi.dwExStyle
		 INDENT "wi.dwStyle=           " << wi.dwStyle
		 INDENT "wi.dwWindowStatus=    " << wi.dwWindowStatus
		 INDENT "wi.rcClient.bottom=   " << wi.rcClient.bottom
		 INDENT "wi.rcClient.left=     " << wi.rcClient.left
		 INDENT "wi.rcClient.right=    " << wi.rcClient.right
		 INDENT "wi.rcClient.top=      " << wi.rcClient.top
		 INDENT "wi.rcWindow.bottom=   " << wi.rcWindow.bottom
		 INDENT "wi.rcWindow.left=     " << wi.rcWindow.left
		 INDENT "wi.rcWindow.right=    " << wi.rcWindow.right
		 INDENT "wi.rcWindow.top=      " << wi.rcWindow.top
		 INDENT "wi.wCreatorVersion=   " << wi.wCreatorVersion
		 << endl;
}