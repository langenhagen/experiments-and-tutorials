Imports System
Imports System.Text
Imports System.IO
Imports Irrlicht
Imports Irrlicht.Video
Imports Irrlicht.GUI
Imports Irrlicht.Core
Imports Irrlicht.Scene
Namespace _08._SpecialFX

    Class Program
        Private path As String = "E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\"
        Private device As IrrlichtDevice

        <STAThread()> _
        Shared Sub Main(ByVal args As String())
            Dim prog As Program = New Program
            prog.run()
        End Sub

        Public Sub run()

            Dim shadow As Boolean = False
            shadow = True
            
            device = New IrrlichtDevice(DriverType.DIRECT3D8, New Dimension2D(800, 600), 32, False, True, True)

            Dim mesh As IAnimatedMesh = device.SceneManager.GetMesh(path + "room.3ds")
            device.SceneManager.MeshManipulator.MakePlanarTextureMapping(mesh.GetMesh(0), 0.008F)
            Dim node As ISceneNode = device.SceneManager.AddAnimatedMeshSceneNode(mesh, Nothing, 0)
            node.SetMaterialTexture(0, device.VideoDriver.GetTexture(path + "wall.jpg"))
            node.GetMaterial(0).EmissiveColor.Set(0, 0, 0, 0)
            mesh = device.SceneManager.AddHillPlaneMesh("Wasser", New Dimension2Df(20, 20), New Dimension2D(20, 20), New Material, 50, New Dimension2Df(2, 3), New Dimension2Df(1, 1))
            node = device.SceneManager.AddWaterSurfaceSceneNode(mesh.GetMesh(0), 3.0F, 300.0F, 3.0F, Nothing, 0)
            node.Position = New Vector3D(0, 7, 0)
            node.SetMaterialTexture(0, device.VideoDriver.GetTexture(path + "b6leather_skin001.jpg"))
            node.SetMaterialTexture(1, device.VideoDriver.GetTexture(path + "Water.jpg"))
            node.SetMaterialType(MaterialType.REFLECTION_2_LAYER)
            node.SetMaterialFlag(MaterialFlag.LIGHTING, False)
            node = device.SceneManager.AddLightSceneNode(Nothing, New Vector3D(0, 0, 0), New Colorf(1.0F, 1.0F, 1.0F, 1.0F), 600.0F, 0)
            Dim anim As ISceneNodeAnimator = device.SceneManager.CreateFlyCircleAnimator(New Vector3D(0, 150, 0), 250.0F, 0.0005F)
            node.AddAnimator(anim)
            node = device.SceneManager.AddBillboardSceneNode(node, New Dimension2Df(50, 50), New Vector3D, 0)
            node.SetMaterialFlag(MaterialFlag.LIGHTING, False)
            node.SetMaterialType(MaterialType.TRANSPARENT_ADD_COLOR)
            node.SetMaterialTexture(0, device.VideoDriver.GetTexture(path + "particlewhite.bmp"))
            Dim ps As IParticleSystemSceneNode = device.SceneManager.AddParticleSystemSceneNode(False, Nothing, 0, New Vector3D(-70, 60, 40), New Vector3D, New Vector3D(2, 2, 2))
            ps.ParticleSize = New Dimension2Df(20, 10)
            Dim em As IParticleEmitter = ps.CreateBoxEmitter(New Box3D(-7, 0, -7, 7, 1, 7), New Vector3D(0.0F, 0.53F, 0.0F), 1500, 2000, New Color(0, 255, 0, 0), New Color(0, 0, 0, 255), 800, 2000, 45)
            ps.SetEmitter(em)
            Dim paf As IParticleAffector = ps.CreateFadeOutParticleAffector(New Color, 150)
            ps.AddAffector(paf)
            ps.SetMaterialFlag(MaterialFlag.LIGHTING, False)
            ps.SetMaterialTexture(0, device.VideoDriver.GetTexture(path + "particle.bmp"))
            ps.SetMaterialType(MaterialType.TRANSPARENT_VERTEX_ALPHA)
            mesh = device.SceneManager.GetMesh(path + "faerie.md2")
            Dim anode As IAnimatedMeshSceneNode = device.SceneManager.AddAnimatedMeshSceneNode(mesh, Nothing, 0)
            anode.Position = New Vector3D(-50, 45, -60)
            anode.SetMD2Animation(MD2AnimationType.WAVE)
            anode.SetMaterialTexture(0, device.VideoDriver.GetTexture(path + "faerie2.bmp"))
            anode.AddShadowVolumeSceneNode()
            device.SceneManager.ShadowColor = New Color(50, 0, 255, 0)
            anode.Scale = New Vector3D(2, 2, 2)
            anode.SetMaterialFlag(MaterialFlag.NORMALIZE_NORMALS, True)
            Dim camera As ICameraSceneNode = device.SceneManager.AddCameraSceneNodeFPS
            camera.Position = New Vector3D(-50, 50, -150)
            device.CursorControl.Visible = False
            Dim lastFPS As Integer = -1
            While device.Run
                If device.WindowActive Then
                    device.VideoDriver.BeginScene(True, True, New Color(0, 200, 200, 200))
                    device.SceneManager.DrawAll()
                    device.VideoDriver.EndScene()
                    Dim fps As Integer = device.VideoDriver.FPS
                    If Not (lastFPS = fps) Then
                        device.WindowCaption = "Irrlicht Engine - SpecialFX tutorial [" + device.VideoDriver.Name + "] FPS:" + fps.ToString
                        lastFPS = fps
                    End If
                End If
            End While
            GC.Collect()
        End Sub
    End Class
End Namespace



