Imports System
Imports System.Text
Imports System.IO
Imports Irrlicht
Imports Irrlicht.Video
'Imports Irrlicht.GUI
Imports Irrlicht.Core
Imports Irrlicht.Scene
Namespace _07._Collisions

    Class Program
        Private path As String = "E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\"
        Private device As IrrlichtDevice

        <STAThread()> _
        Shared Sub Main(ByVal args As String())
            Dim prog As Program = New Program
            prog.run()
        End Sub

        Public Sub run()
           
            device = New IrrlichtDevice(Video.DriverType.DIRECT3D9, New Dimension2D(800, 600), 32, False, True, True)
            device.FileSystem.AddZipFileArchive(path + "map-20kdm2.pk3")
            Dim q3levelmesh As IAnimatedMesh = device.SceneManager.GetMesh("20kdm2.bsp")
            Dim q3node As ISceneNode = Nothing
            If Not (q3levelmesh Is Nothing) Then
                q3node = device.SceneManager.AddOctTreeSceneNode(q3levelmesh, Nothing, 0)
            End If
            Dim selector As ITriangleSelector = Nothing
            If Not (q3node Is Nothing) Then
                q3node.Position = New Vector3D(-1370, -130, -1400)
                selector = device.SceneManager.CreateOctTreeTriangleSelector(q3levelmesh.GetMesh(0), q3node, 128)
            End If
            Dim camera As ICameraSceneNode = device.SceneManager.AddCameraSceneNodeFPS(Nothing, 100, 300, 0)
            camera.Position = New Vector3D(-100, 50, -150)
            'die Kollision mit Kamera
            Dim anim As ISceneNodeAnimator = device.SceneManager.CreateCollisionResponseAnimator(selector, camera, New Vector3D(25, 45, 20), New Vector3D(0, -0.5, 0), New Vector3D(0, 50, 0), 0)
            camera.AddAnimator(anim)
            device.CursorControl.Visible = False


            'el  speciale
            q3node.SetMaterialFlag(MaterialFlag.WIREFRAME, True)
            Dim q4node As ISceneNode = Nothing
            q4node = device.SceneManager.AddOctTreeSceneNode(q3levelmesh, Nothing, 0)
            q4node.SetMaterialType(MaterialType.TRANSPARENT_ADD_COLOR)
            q4node.Position = New Vector3D(-1370, -130, -1400)

            'Liiiicht
            Dim bill As IBillboardSceneNode = device.SceneManager.AddBillboardSceneNode(Nothing, New Dimension2Df(20, 20), New Vector3D, 0)
            bill.SetMaterialType(MaterialType.TRANSPARENT_ADD_COLOR)
            bill.SetMaterialTexture(0, device.VideoDriver.GetTexture(path + "particle.bmp"))
            bill.SetMaterialFlag(MaterialFlag.LIGHTING, False)
            bill.SetMaterialFlag(MaterialFlag.ZBUFFER, False)



            'die Faeries

            Dim material As Material = New Material
            Material.Texture1 = device.VideoDriver.GetTexture(path + "faerie2.bmp")
            Material.Lighting = True

            Dim node As IAnimatedMeshSceneNode = Nothing
            Dim faerie As IAnimatedMesh = device.SceneManager.GetMesh(path + "faerie.md2")
            If Not (faerie Is Nothing) Then
                node = device.SceneManager.AddAnimatedMeshSceneNode(faerie, Nothing, 0)
                node.Position = New Vector3D(-70, 0, -90)
                node.SetMD2Animation(MD2AnimationType.RUN)
                node.SetMaterial(0, material)
                node = device.SceneManager.AddAnimatedMeshSceneNode(faerie, Nothing, 0)
                node.Position = New Vector3D(-70, 0, -30)
                node.SetMD2Animation(MD2AnimationType.SALUTE)
                node.SetMaterial(0, material)
                node = device.SceneManager.AddAnimatedMeshSceneNode(faerie, Nothing, 0)
                node.Position = New Vector3D(-70, 0, -60)
                node.SetMD2Animation(MD2AnimationType.JUMP)
                node.SetMaterial(0, material)
            End If

            material.Texture1 = Nothing
            material.Lighting = False
            device.SceneManager.AddLightSceneNode(Nothing, New Vector3D(-60, 100, 400), New Colorf(1.0F, 1.0F, 1.0F, 1.0F), 600, 0)

            Dim hNode As ISceneNode = device.SceneManager.AddTestSceneNode(1, camera, 2, New Vector3D(2, -2, 5))

            'der komische teil
            Dim selectedSceneNode As ISceneNode = Nothing
            Dim lastSelectedSceneNode As ISceneNode = Nothing
            Dim lastFPS As Integer = -1
            While device.Run
                If device.WindowActive Then
                    device.VideoDriver.BeginScene(True, True, New Color(0, 200, 200, 200))
                    device.SceneManager.DrawAll()

                    'leuchtender Punkt, schussrichtung bla
                    Dim line As Line3D = New Line3D
                    line.start = camera.Position
                    line.end = line.start + (camera.Target - line.start).Normalize * 10000
                    Dim intersection As Vector3D
                    Dim tri As Triangle3D
                    'hier komisch
                    If device.SceneManager.SceneCollisionManager.GetCollisionPoint(line, selector, intersection, tri) Then
                        bill.Position = intersection
                        device.VideoDriver.SetTransform(TransformationState.WORLD, New Matrix4)
                        device.VideoDriver.SetMaterial(material)
                        device.VideoDriver.Draw3DTriangle(tri, New Color(255, 255, 0, 255))


                        device.VideoDriver.Draw3DLine(line.start + New Vector3D(0, 3, 0), intersection, New Color(0, 255, 255, 255))

                        device.VideoDriver.Draw3DLine(camera.Position + hNode.Position, intersection, New Color(0, 255, 255, 255))


                    End If
                    'wofür is das
                    selectedSceneNode = device.SceneManager.SceneCollisionManager.GetSceneNodeFromCameraBB(camera, 0, False)
                    If Not (lastSelectedSceneNode Is Nothing) Then
                        lastSelectedSceneNode.SetMaterialFlag(MaterialFlag.LIGHTING, True)
                    End If

                    'hier is der Mist
                    If selectedSceneNode.Equals(q3node) Then
                        selectedSceneNode = Nothing
                    End If
                    If Not (selectedSceneNode Is Nothing) Then
                        selectedSceneNode.SetMaterialFlag(MaterialFlag.LIGHTING, False)
                    End If
                    lastSelectedSceneNode = selectedSceneNode
                    device.VideoDriver.EndScene()
                    Dim fps As Integer = device.VideoDriver.FPS
                    If Not (lastFPS = fps) Then
                        device.WindowCaption = "Irrlicht Engine - Quake 3 Map example [" + device.VideoDriver.Name + "] FPS:" + fps.ToString
                        lastFPS = fps
                    End If
                End If
            End While
            GC.Collect()
        End Sub
    End Class
End Namespace