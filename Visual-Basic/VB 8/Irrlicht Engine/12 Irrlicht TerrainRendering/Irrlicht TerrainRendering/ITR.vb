Imports System
Imports System.Text
Imports System.IO
Imports Irrlicht
Imports Irrlicht.Video
Imports Irrlicht.Core
Imports Irrlicht.Scene
Imports Irrlicht.GUI
Namespace IrrlichtTerrainRendering

    Class Program
        Implements IEventReceiver

        Private path As String = "E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\"
        Private terrain As ITerrainSceneNode
        Private isWireframe As Boolean = False

        <STAThread()> _
        Shared Sub Main(ByVal args As String())
            Dim prog As Program = New Program
            prog.run()
        End Sub

        Public Function OnEvent(ByVal p_event As Irrlicht.Event) As Boolean
            If p_event.Type = EventType.KeyInput AndAlso Not p_event.KeyPressedDown Then
                Select Case p_event.Key
                    Case KeyCode.KEY_KEY_W
                        isWireframe = Not isWireframe
                        terrain.SetMaterialFlag(MaterialFlag.WIREFRAME, isWireframe)

                    Case KeyCode.KEY_KEY_D
                        terrain.SetMaterialType(Microsoft.VisualBasic.IIf(terrain.GetMaterial(0).Type = MaterialType.SOLID, MaterialType.DETAIL_MAP, MaterialType.SOLID))
                        Return True
                End Select
            End If
            Return False
        End Function

        Public Sub run()

            Dim device As IrrlichtDevice = New IrrlichtDevice(DriverType.DIRECT3D9, New Dimension2D(640, 480), 32, False, True, True)

            device.EventReceiver = Me
            Dim smgr As ISceneManager = device.SceneManager
            Dim driver As IVideoDriver = device.VideoDriver
            Dim env As IGUIEnvironment = device.GUIEnvironment
            driver.SetTextureCreationFlag(TextureCreationFlag.ALWAYS_32_BIT, True)
            env.AddImage(driver.GetTexture(path + "irrlichtlogoalpha.tga"), New Position2D(10, 10), True, Nothing, 0, "")
            Dim text As IGUIStaticText = env.AddStaticText("Press 'W' to change wireframe mode" & Microsoft.VisualBasic.Chr(10) & "Press 'D' to toggle detail map", New Rect(10, 453, 200, 475), True, True, Nothing, -1)
            Dim camera As ICameraSceneNode = smgr.AddCameraSceneNodeFPS(Nothing, 100.0F, 1200.0F, -1)
            camera.Position = New Vector3D(1900 * 2, 255 * 2, 3700 * 2)
            camera.Target = New Vector3D(2397 * 2, 343 * 2, 2700 * 2)
            camera.FarValue = 12000.0F
            device.CursorControl.Visible = False
            terrain = smgr.AddTerrainSceneNode(path + "terrain-heightmap.bmp", Nothing, -1, New Vector3D, New Vector3D(40, 4.4F, 40), New Color(255, 255, 255, 255))
            terrain.SetMaterialFlag(MaterialFlag.LIGHTING, False)
            terrain.SetMaterialType(MaterialType.DETAIL_MAP)
            terrain.SetMaterialTexture(0, driver.GetTexture(path + "terrain-texture.jpg"))
            terrain.SetMaterialTexture(1, driver.GetTexture(path + "detailmap3.jpg"))
            terrain.ScaleTexture(1.0F, 20.0F)
            Dim selector As ITriangleSelector = smgr.CreateTerrainTriangleSelector(terrain, 0)
            Dim anim As ISceneNodeAnimator = smgr.CreateCollisionResponseAnimator(selector, camera, New Vector3D(60, 100, 60), New Vector3D(0, 0, 0), New Vector3D(0, 50, 0), 0.0005F)
            camera.AddAnimator(anim)
            driver.SetTextureCreationFlag(TextureCreationFlag.CREATE_MIP_MAPS, False)
            smgr.AddSkyBoxSceneNode(driver.GetTexture(path + "irrlicht2_up.jpg"), driver.GetTexture(path + "irrlicht2_dn.jpg"), driver.GetTexture(path + "irrlicht2_lf.jpg"), driver.GetTexture(path + "irrlicht2_rt.jpg"), driver.GetTexture(path + "irrlicht2_ft.jpg"), driver.GetTexture(path + "irrlicht2_bk.jpg"), Nothing, 0)
            driver.SetTextureCreationFlag(TextureCreationFlag.CREATE_MIP_MAPS, True)
            Dim lastFPS As Integer = -1
            While device.Run
                If device.WindowActive Then
                    device.VideoDriver.BeginScene(True, True, New Color(0, 200, 200, 200))
                    device.SceneManager.DrawAll()
                    device.VideoDriver.EndScene()

                    Dim fps As Integer = device.VideoDriver.FPS
                    If Not (lastFPS = fps) Then
                        device.WindowCaption = "Irrlicht Engine - Terrain example [" + device.VideoDriver.Name + "] FPS:" + fps.ToString
                        lastFPS = fps
                    End If
                End If
            End While
            GC.Collect()
        End Sub

        Public Function OnEvent1(ByVal e As Irrlicht.Event) As Boolean Implements Irrlicht.IEventReceiver.OnEvent

        End Function
    End Class
End Namespace


