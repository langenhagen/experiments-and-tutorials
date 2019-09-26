#include <cstdio>
#include <windows.h>
#include <tlhelp32.h>

#include <iostream>


#define TITLE_LENGTH 256


using namespace std;

int xxmain( int, char *[] )
{
    PROCESSENTRY32 entry;
    entry.dwSize = sizeof(PROCESSENTRY32);
	
    HANDLE snapshot = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, NULL);

    if (Process32First(snapshot, &entry) == TRUE)
    {
        while (Process32Next(snapshot, &entry) == TRUE)
        {
            if (stricmp(entry.szExeFile, "geplugin.exe") == 0)
            {  
                HANDLE hProcess = OpenProcess(PROCESS_ALL_ACCESS, FALSE, entry.th32ProcessID);
				entry;
                // Do stuff..

                CloseHandle(hProcess);
            }
        }
    }

    CloseHandle(snapshot);

	cin.get();

    return 0;
}


void EnableDebugPriv()
{
    HANDLE hToken;
    LUID luid;
    TOKEN_PRIVILEGES tkp;

    OpenProcessToken(GetCurrentProcess(), TOKEN_ADJUST_PRIVILEGES | TOKEN_QUERY, &hToken);

    LookupPrivilegeValue(NULL, SE_DEBUG_NAME, &luid);

    tkp.PrivilegeCount = 1;
    tkp.Privileges[0].Luid = luid;
    tkp.Privileges[0].Attributes = SE_PRIVILEGE_ENABLED;

    AdjustTokenPrivileges(hToken, false, &tkp, sizeof(tkp), NULL, NULL);

    CloseHandle(hToken); 
}

int xmain( int, char *[] )
{
    EnableDebugPriv();

    PROCESSENTRY32 entry;
    entry.dwSize = sizeof(PROCESSENTRY32);

    HANDLE snapshot = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, NULL);

    if (Process32First(snapshot, &entry) == TRUE)
    {
        while (Process32Next(snapshot, &entry) == TRUE)
        {
            if (stricmp(entry.szExeFile, "geplugin.exe") == 0)
            {  
                HANDLE hProcess = OpenProcess(PROCESS_ALL_ACCESS, FALSE, entry.th32ProcessID);

                // Do stuff..



				char title[TITLE_LENGTH];

				//GetWindowText( hProcess,title, TITLE_LENGTH);

				cout << hProcess << endl;

                CloseHandle(hProcess);
            }
        }
    }

    CloseHandle(snapshot);

	cin.get();

    return 0;
}

BOOL CALLBACK EnumChildProc(HWND hwnd, LPARAM lParam)
{
	char win_text[128];
	GetWindowText(hwnd, win_text, 128);

	cout << "hwnd_Child = " << hwnd << "\t" << win_text << endl;

	return TRUE; // must return TRUE; If return is FALSE it stops the recursion

}


void main()
{
	//auto hwnd = FindWindowEx( 0,0,0, "Chrome");
	auto hwnd = FindWindow(NULL, "Google Earth - Google Chrome"); // Yes, the name of the window is "Quake 2", case for case.
	if (hwnd != 0)
	{
		cout << " You caught the window!" << endl;

		WINDOWINFO wi;
		char win_text[128];
		
		GetWindowInfo(hwnd, &wi);
		GetWindowText(hwnd, win_text, 128);

		cout << "hwnd  = " << hwnd << "\t" << win_text << endl << endl;

		EnumChildWindows( hwnd, EnumChildProc, 0);
	}

	else
	{
		cout << "open the freakin game..." << endl;
	}

	cin.get();
}