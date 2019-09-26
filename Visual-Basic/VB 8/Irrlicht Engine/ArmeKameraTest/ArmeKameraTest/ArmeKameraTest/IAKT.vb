Imports System
Imports System.Text
Imports System.IO
Imports Irrlicht
Imports Irrlicht.Video
Imports Irrlicht.Core
Imports Irrlicht.Scene
Imports Microsoft.VisualBasic

Namespace _ArmeKameratest

    Class Program

        Implements IEventReceiver

        Private Pfad As String = String.Empty
        Private Device As IrrlichtDevice = Nothing
        Private arme As IAnimatedMeshSceneNode = Nothing
        Private camera As ICameraSceneNode = Nothing
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
            If Not (arme Is Nothing) AndAlso p_e.Type = EventType.KeyInput Then

                Select Case p_e.Key

                    Case KeyCode.KEY_KEY_W
                        Dim fore As ISceneNodeAnimator = Device.SceneManager.CreateFlyStraightAnimator(arme.Position, New Vector3D(arme.Position.X, arme.Position.Y, arme.Position.Z + 10), 100, False)
                        arme.AddAnimator(fore)
                        camera.AddAnimator(fore)
                        camera.Position = New Vector3D(0, 1000, 0)
                        fore.__dtor()


                    Case KeyCode.KEY_KEY_S
                        Dim back As ISceneNodeAnimator = Device.SceneManager.CreateFlyStraightAnimator(arme.Position, New Vector3D(arme.Position.X, arme.Position.Y, arme.Position.Z - 10), 100, False)
                        arme.AddAnimator(back)
                        camera.AddAnimator(back)
                        back.__dtor()

                        Return True
                End Select
            End If
            Return False
        End Function

        Sub Run()

            Device = New IrrlichtDevice(DriverType.DIRECT3D9, New Dimension2D(800, 600), 32, False, True, True)

            Device.EventReceiver = Me

            'Hier wichtich
            camera = Device.SceneManager.AddCameraSceneNodeFPS(Nothing, 100, 100, 0)
            arme = Device.SceneManager.AddAnimatedMeshSceneNode(Device.SceneManager.GetMesh("arme.3ds"), camera, 0)
            arme.Scale = (New Vector3D(8, 8, 8))


            Dim n As ISceneNode = Device.SceneManager.AddTestSceneNode(10, Nothing, 0, New Vector3D, New Vector3D, New Vector3D(1, 1, 1))
            n.SetMaterialTexture(0, Device.VideoDriver.GetTexture("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\t351sml.jpg"))
            Dim anim As ISceneNodeAnimator = Device.SceneManager.CreateFlyCircleAnimator(New Vector3D(0, 0, 30), 20, -0.002F)
            n.AddAnimator(anim)

            anim.__dtor()

            Dim anms As IAnimatedMeshSceneNode = Device.SceneManager.AddAnimatedMeshSceneNode(Device.SceneManager.GetMesh("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\sydney.md2"), Nothing, 0)

            If Not (anms Is Nothing) Then
                anim = Device.SceneManager.CreateFlyStraightAnimator(New Vector3D(100, 0, 60), New Vector3D(-100, 0, 60), 10000, True)
                anms.AddAnimator(anim)
                anim.__dtor()

                anms.Position = New Vector3D(0, 0, 40)
                anms.SetMaterialFlag(MaterialFlag.LIGHTING, False)
                anms.SetFrameLoop(500, 600) '320, 360
                anms.AnimationSpeed = 50
                anms.Rotation = New Vector3D(0, 90, 0) '0,180,0
                anms.SetMaterialTexture(0, Device.VideoDriver.GetTexture("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\sydney.BMP"))
            End If



            Device.CursorControl.Visible = False
            Dim lastFPS As Integer = -1
            While Device.Run
                If Device.WindowActive Then
                    Device.VideoDriver.BeginScene(True, True, New Color(0, 200, 200, 200))
                    Device.SceneManager.DrawAll()

                    'das Jerät
                    Device.VideoDriver.Draw2DRectangle(New Color(150, 255, 100, 255), New Rect(10, 500, 320, 580))
                    Device.VideoDriver.Draw2DImage(Device.VideoDriver.GetTexture("HUD.bmp"), New Position2D(10, 300), New Rect(0, 0, 357, 158), New Color(100, 255, 100, 255), True)


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

