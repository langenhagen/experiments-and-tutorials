Imports System
Imports System.Text
Imports System.IO
Imports Irrlicht
Imports Irrlicht.Video
Imports Irrlicht.GUI
Imports Irrlicht.Core
Imports Irrlicht.Scene
Namespace _UserInterface

    Class Program
        Implements IEventReceiver
        Private path As String = String.Empty
        Private device As IrrlichtDevice
        Private cnt As Integer = 0
        Private listbox As IGUIListBox

        <STAThread()> _
        Shared Sub Main(ByVal args As String())
            Dim prog As Program = New Program
            prog.run()
        End Sub

        Public Function OnEvent(ByVal p_e As Irrlicht.Event) As Boolean Implements IEventReceiver.OnEvent
            If p_e.Type = EventType.GUIEvent Then
                Dim id As Integer = p_e.GUIEventCaller.ID
                Dim env As IGUIEnvironment = device.GUIEnvironment
                Select Case p_e.GUIEventType
                    Case (GUIEvent.SCROLL_BAR_CHANGED)
                        If id = 104 Then
                        End If

                    Case (GUIEvent.BUTTON_CLICKED)
                        If id = 101 Then
                            device.CloseDevice()
                            Return True
                        End If
                        If id = 102 Then
                            listbox.AddItem("Window Created Test")
                            cnt += 30
                            If cnt > 200 Then
                                cnt = 0
                            End If
                            Dim window As IGUIElement = env.AddWindow(New Rect(100 + cnt, 100 + cnt, 300 + cnt, 200 + cnt), False, "Test Window", Nothing, 0)
                            env.AddStaticText("Please close me", New Rect(35, 35, 140, 50), True, False, window, 0)
                            Return True
                        End If
                        If id = 103 Then
                            listbox.AddItem("File open")
                            env.AddFileOpenDialog("Please choose a file", False, Nothing, 0)
                            Return True
                        End If

                End Select
            End If
            Return False
        End Function

        Public Sub run()
            'Dim driverType As DriverType
            Dim sb As StringBuilder = New StringBuilder
            sb.Append("Please select the driver you want for this example:" & Microsoft.VisualBasic.Chr(10) & "")
            sb.Append("" & Microsoft.VisualBasic.Chr(10) & "(a) Direct3D 9.0c" & Microsoft.VisualBasic.Chr(10) & "(b) Direct3D 8.1" & Microsoft.VisualBasic.Chr(10) & "(c) OpenGL 1.5")
            sb.Append("" & Microsoft.VisualBasic.Chr(10) & "(d) Software Renderer" & Microsoft.VisualBasic.Chr(10) & "(e) Apfelbaum Software Renderer")
            sb.Append("" & Microsoft.VisualBasic.Chr(10) & "(f) Null Device" & Microsoft.VisualBasic.Chr(10) & "(otherKey) exit" & Microsoft.VisualBasic.Chr(10) & "" & Microsoft.VisualBasic.Chr(10) & "")
            Dim tIn As TextReader = Console.In
            Dim tOut As TextWriter = Console.Out
            tOut.Write(sb.ToString)
            'Dim input As String = tIn.ReadLine
            'Select Case input
            '   Case "a"
            'driverType = driverType.DIRECT3D9
            ' break
            '    Case "b"
            'driverType = driverType.DIRECT3D8
            ' break
            '    Case "c"
            'driverType = driverType.OPENGL
            ' break
            '    Case "d"
            'driverType = driverType.SOFTWARE
            ' break
            '    Case "e"
            'driverType = driverType.SOFTWARE2
            ' break
            '    Case "f"
            'driverType = driverType.NULL_DRIVER
            ' break
            '    Case Else
            'Return
            'End Select
            device = New IrrlichtDevice(Video.DriverType.DIRECT3D9, New Dimension2D(800, 600), 32, False, True, True)
            If device Is Nothing Then
                tOut.Write("Device creation failed.")
                Return
            End If
            device.EventReceiver = Me
            Dim smgr As ISceneManager = device.SceneManager
            Dim driver As IVideoDriver = device.VideoDriver
            Dim env As IGUIEnvironment = device.GUIEnvironment
            env.AddButton(New Rect(10, 210, 100, 240), Nothing, 101, "Quit")
            env.AddButton(New Rect(10, 250, 100, 290), Nothing, 102, "New Window")
            env.AddButton(New Rect(10, 300, 100, 340), Nothing, 103, "File Open")
            env.AddStaticText("Transparent Control:", New Rect(150, 20, 350, 40), True, False, Nothing, 0)
            Dim scrollbar As IGUIElement = env.AddScrollBar(True, New Rect(150, 45, 350, 60), Nothing, 104)
            env.AddStaticText("Logging Listbox:", New Rect(50, 80, 250, 100), True, False, Nothing, 0)
            listbox = env.AddListBox(New Rect(50, 110, 250, 180), Nothing, 0, True)

            Dim skin As IGUISkin = env.Skin
            'jetz die Experimente
            env.CreateSkin(SkinType.WINDOWS_METALLIC)
            skin.SetColor(SkinColor.E3D_SHADOW, New Color(25, 75, 233, 150)) 'dit türkise
            skin.SetColor(SkinColor.E3D_HIGH_LIGHT, New Color(255, 233, 150, 75)) 'dit orange
            skin.SetColor(SkinColor.E3D_DARK_SHADOW, New Color(255, 255, 100, 100)) 'der verkackte rand rechts/unten
            skin.SetColor(SkinColor.SCROLLBAR, New Color(255, 255, 100, 100)) 'klar isset
            skin.SetColor(SkinColor.E3D_FACE, New Color(255, 250, 200, 250)) ' dit face halt; dit rosane

            Dim font As IGUIFont = env.GetFont("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\fonthaettenschweiler.bmp")
            If Not (font Is Nothing) Then
                skin.Font = font
            End If
            Dim img As IGUIElement = env.AddImage(driver.GetTexture("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\irrlichtlogoalpha.tga"), New Position2D(10, 10), False, Nothing, 0, "")
            While device.Run
                If device.WindowActive Then
                    device.VideoDriver.BeginScene(True, True, New Color(0, 122, 65, 171))
                    device.SceneManager.DrawAll()
                    device.GUIEnvironment.DrawAll()
                    device.VideoDriver.EndScene()
                End If
            End While
            GC.Collect()
        End Sub
    End Class
End Namespace

