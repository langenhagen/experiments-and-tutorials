Imports System
Imports System.Text
Imports System.IO
Imports Irrlicht
Imports Irrlicht.Video
Imports Irrlicht.Core
Imports Irrlicht.Scene
Imports Microsoft.VisualBasic

Namespace _Movement

    Class Program

        Implements IEventReceiver

        Private Pfad As String = String.Empty
        Private Node As ISceneNode = Nothing
        Private Device As IrrlichtDevice = Nothing
        Private arme As ISceneNode = Nothing

        ''' <summary>
        ''' Main entry point for the program.
        ''' </summary>
        ''' <param name="args">Arguments to pass the software.</param>

        <STAThread()> _
        Shared Sub Main(ByVal args As String())
            Dim pgm As Program = New Program
            pgm.Run()
        End Sub

        Public Function OnEvent(ByVal p_e As Irrlicht.Event) As Boolean Implements Irrlicht.IEventReceiver.OnEvent
            If Not (Node Is Nothing) AndAlso p_e.Type = EventType.KeyInput AndAlso Not p_e.KeyPressedDown Then
                Select Case p_e.Key
                    Case KeyCode.KEY_ESCAPE
                        Device.CloseDevice()


                    Case KeyCode.KEY_KEY_W, KeyCode.KEY_KEY_S
                        Dim v As Vector3D = arme.Position
                        v.Y += CType(IIf(p_e.Key = KeyCode.KEY_KEY_W, 2.0F, -2.0F), System.Decimal)
                        arme.Position = v
                        Return True
                End Select
            End If
            Return False
        End Function

        Sub Run()
            Dim driverType As DriverType
            Dim sb As StringBuilder = New StringBuilder
            sb.Append("Please select the driver you want for this example:" & Microsoft.VisualBasic.Chr(10) & "")
            sb.Append("" & Microsoft.VisualBasic.Chr(10) & "(a) Direct3D 9.0c" & Microsoft.VisualBasic.Chr(10) & "(b) Direct3D 8.1" & Microsoft.VisualBasic.Chr(10) & "(c) OpenGL 1.5")
            sb.Append("" & Microsoft.VisualBasic.Chr(10) & "(d) Software Renderer" & Microsoft.VisualBasic.Chr(10) & "(e)Apfelbaum Software Renderer")
            sb.Append("" & Microsoft.VisualBasic.Chr(10) & "(f) Null Device" & Microsoft.VisualBasic.Chr(10) & "(otherKey) exit" & Microsoft.VisualBasic.Chr(10) & "" & Microsoft.VisualBasic.Chr(10) & "")

            Dim tIn As TextReader = Console.In
            Dim tOut As TextWriter = Console.Out
            tOut.Write(sb.ToString)
            Dim input As String = tIn.ReadLine

            Select Case input
                Case "a"
                    driverType = driverType.DIRECT3D9
                Case "b"
                    driverType = driverType.DIRECT3D8
                Case "c"
                    driverType = driverType.OPENGL
                Case "d"
                    driverType = driverType.SOFTWARE
                Case "e"
                    driverType = driverType.SOFTWARE2
                Case "f"
                    driverType = driverType.NULL_DRIVER
                Case Else
                    Return
            End Select

            Device = New IrrlichtDevice(driverType, New Dimension2D(800, 600), 32, False, True, True)
            If Device Is Nothing Then
                tOut.Write("Device creation failed.")
                Return
            End If

            Device.EventReceiver = Me

            'Get a reference to the video driver and the SceneManager so that
            'we do not always have to write device.VideoDriver and
            'device.SceneManager
            Dim smgr As ISceneManager = Device.SceneManager
            Dim driver As IVideoDriver = Device.VideoDriver

            Node = smgr.AddTestSceneNode(10, Nothing, 0, New Vector3D(0, 0, 30), New Vector3D, New Vector3D(1, 1, 1))
            Node.SetMaterialTexture(0, driver.GetTexture("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\wall.bmp"))
            Dim n As ISceneNode = smgr.AddTestSceneNode(10, Nothing, 0, New Vector3D, New Vector3D, New Vector3D(1, 1, 1))
            n.SetMaterialTexture(0, driver.GetTexture("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\t351sml.jpg"))
            Dim anim As ISceneNodeAnimator = smgr.CreateFlyCircleAnimator(New Vector3D(0, 0, 30), 20, -0.002F)
            n.AddAnimator(anim)

            anim.__dtor() 'was das?

            Dim anms As IAnimatedMeshSceneNode = smgr.AddAnimatedMeshSceneNode(smgr.GetMesh("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\sydney.md2"), Nothing, 0)



            'ARME
            Dim arme As IAnimatedMeshSceneNode = smgr.AddAnimatedMeshSceneNode(smgr.GetMesh("arme.3ds"), Nothing, 0)
            arme.Scale = (New Vector3D(7, 7, 7))

            If Not (anms Is Nothing) Then
                anim = smgr.CreateFlyStraightAnimator(New Vector3D(100, 0, 60), New Vector3D(-100, 0, 60), 10000, True)
                anms.AddAnimator(anim)
                anim.__dtor()

                'To make to model look better, we disable lighting (we have created no lights,
                'and so the model would be black), set the frames between which the animation
                'should loop, rotate the model around 180 degrees, and adjust the animation
                'speed and the texture.
                'To set the right animation (frames and speed), we would also be able to just
                'call "anms->setMD2Animation(scene::EMAT_RUN)" for the 'run' animation
                'instead of "setFrameLoop" and "setAnimationSpeed", but this only works with
                'MD2 animations, and so you know how to start other animations.

                anms.Position = New Vector3D(0, 0, 40)
                anms.SetMaterialFlag(MaterialFlag.LIGHTING, False)
                anms.SetFrameLoop(500, 600) '320, 360
                anms.AnimationSpeed = 50
                anms.Rotation = New Vector3D(0, 90, 0) '0,180,0
                anms.SetMaterialTexture(0, driver.GetTexture("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\sydney.BMP"))
            End If

            Dim camera As ICameraSceneNode = smgr.AddCameraSceneNodeFPS(Nothing, 100, 100, 0)
            camera.Position = New Vector3D(0, 0, 0)
            Device.CursorControl.Visible = False
            Dim lastFPS As Integer = -1
            While Device.Run
                If Device.WindowActive Then
                    Device.VideoDriver.BeginScene(True, True, New Color(0, 200, 200, 200))
                    Device.SceneManager.DrawAll()
                    Device.VideoDriver.EndScene()
                    Dim fps As Integer = Device.VideoDriver.FPS
                    If Not (lastFPS = fps) Then
                        Device.WindowCaption = "Irrlicht Engine - Movement example [" + Device.VideoDriver.Name + "] FPS:" + fps.ToString
                        lastFPS = fps
                    End If
                End If
            End While
            GC.Collect()

        End Sub
    End Class
End Namespace

