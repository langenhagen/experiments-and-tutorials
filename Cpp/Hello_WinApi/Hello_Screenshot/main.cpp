#include <iostream>

#include <windows.h>

using namespace std;

int main()
{ 
    RECT rc;
    
	const char* window_name("Rechner");

	HWND hwnd = FindWindow( NULL, window_name);    //the window can't be min
    if (hwnd == NULL)
    {
        cout << "it can't find any '" << window_name << "'" << endl;
        
		cin.get();
		return 0;
    }
    GetClientRect(hwnd, &rc);

    //create
    HDC hdcScreen = GetDC(NULL);
    HDC hdc = CreateCompatibleDC(hdcScreen);
    HBITMAP hbmp = CreateCompatibleBitmap(hdcScreen, 
        rc.right - rc.left, rc.bottom - rc.top);
    SelectObject(hdc, hbmp);

    //Print to memory hdc
    PrintWindow(hwnd, hdc, PW_CLIENTONLY);

    //copy to clipboard
    OpenClipboard(NULL);
    EmptyClipboard();
    SetClipboardData(CF_BITMAP, hbmp);
    CloseClipboard();

    //release
    DeleteDC(hdc);
    DeleteObject(hbmp);
    ReleaseDC(NULL, hdcScreen);

    cout << "success copy to clipboard, please paste it to the 'mspaint'" << endl;

	cin.get();

    return 0;
}