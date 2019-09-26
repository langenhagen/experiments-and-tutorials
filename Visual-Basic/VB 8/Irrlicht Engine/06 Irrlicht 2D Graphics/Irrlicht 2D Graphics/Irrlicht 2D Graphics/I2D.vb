Imports System
Imports System.Text
Imports System.IO
Imports Irrlicht
Imports Irrlicht.Video
Imports Irrlicht.GUI
Imports Irrlicht.Core
Imports Irrlicht.Scene
Namespace _06._2DGraphics

    Class Program
        Private path As String = String.Empty
        Private device As IrrlichtDevice

        <STAThread()> _
        Shared Sub Main(ByVal args As String())
            Dim prog As Program = New Program
            prog.run()
        End Sub

        Public Sub run()
            Dim driverType As DriverType
            Dim sb As StringBuilder = New StringBuilder
            sb.Append("Please select the driver you want for this example:" & Microsoft.VisualBasic.Chr(10) & "")
            sb.Append("" & Microsoft.VisualBasic.Chr(10) & "(a) Direct3D 9.0c" & Microsoft.VisualBasic.Chr(10) & "(b) Direct3D 8.1" & Microsoft.VisualBasic.Chr(10) & "(c) OpenGL 1.5")
            sb.Append("" & Microsoft.VisualBasic.Chr(10) & "(d) Software Renderer" & Microsoft.VisualBasic.Chr(10) & "(e) Apfelbaum Software Renderer")
            sb.Append("" & Microsoft.VisualBasic.Chr(10) & "(f) Null Device" & Microsoft.VisualBasic.Chr(10) & "(otherKey) exit" & Microsoft.VisualBasic.Chr(10) & "" & Microsoft.VisualBasic.Chr(10) & "")
            Dim tIn As TextReader = Console.In
            Dim tOut As TextWriter = Console.Out
            tOut.Write(sb.ToString)
            Dim input As String = tIn.ReadLine
            Select Case input
                Case "a"
                    driverType = DriverType.DIRECT3D9
                    ' break
                Case "b"
                    driverType = DriverType.DIRECT3D8
                    ' break
                Case "c"
                    driverType = DriverType.OPENGL
                    ' break
                Case "d"
                    driverType = DriverType.SOFTWARE
                    ' break
                Case "e"
                    driverType = DriverType.SOFTWARE2
                    ' break
                Case "f"
                    driverType = DriverType.NULL_DRIVER
                    ' break
                Case Else
                    Return
            End Select
            device = New IrrlichtDevice(driverType, New Dimension2D(800, 600), 32, False, True, True)
            If device Is Nothing Then
                tOut.Write("Device creation failed.")
                Return
            End If
            Dim driver As IVideoDriver = device.VideoDriver
            Dim images As ITexture = driver.GetTexture("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\2ddemo.bmp")
            driver.MakeColorKeyTexture(images, New Position2D(0, 0))
            Dim font As IGUIFont = device.GUIEnvironment.BuiltInFont
            Dim font2 As IGUIFont = device.GUIEnvironment.GetFont("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\fonthaettenschweiler.bmp")
            Dim imp1 As Rect = New Rect(349, 15, 385, 78)
            Dim imp2 As Rect = New Rect(387, 15, 423, 78)
            While device.Run AndAlso Not (driver Is Nothing)
                If device.WindowActive Then
                    Dim time As System.UInt32 = device.Timer.Time
                    driver.BeginScene(True, True, New Color(100, 255, 75, 60))
                    driver.Draw2DImage(images, New Position2D(50, 50), New Rect(0, 0, 342, 224), New Color(255, 255, 200, 255), True)
                    driver.Draw2DImage(images, New Position2D(140, 150), New Rect(0, 0, 360, 230), New Color(0, 255, 255, 255), True)
                    'driver.Draw2DImage(images, New Position2D(164, 125), CType(IIf((time / 500 Mod 2) = 0, imp1, imp2).ToString(), Integer), New Color(255, 255, 255, 255), True)
                    'driver.Draw2DImage(images, New Position2D(270, 105), CType(IIf((time / 500 Mod 2) = 0, imp1, imp2).ToString(), Integer), New Color(255, (CType((time), Integer) Mod 255), 255, 255), True)
                    If Not (font Is Nothing) Then
                        font.Draw("This is some text", New Rect(130, 100, 300, 130), New Color(0, 255, 255, 255), False, False)
                    End If
                    If Not (font2 Is Nothing) Then
                        font2.Draw("This is some text", New Rect(130, 80, 300, 100), New Color(100, CType(time, Integer) Mod 11000, CType(time, Integer) Mod 7000, CType(time, Integer) Mod 3000), False, False)
                    End If
                    driver.Draw2DImage(images, New Position2D(10, 10), New Rect(354, 87, 442, 118), New Color(25, 255, 255, 255), True)
                    Dim m As Position2D = device.CursorControl.Position 'mausding
                    driver.Draw2DRectangle(New Color(100, 255, 100, 255), New Rect(m.X - 20, m.Y - 20, m.X + 20, m.Y + 20))
                    driver.Draw2DImage(images, New Position2D(m.X - 20, m.Y - 20), New Rect(0, 0, 342, 224), New Color(100, 255, 200, 255), True)
                    driver.EndScene()
                End If
            End While
            GC.Collect()
        End Sub
    End Class
End Namespace
