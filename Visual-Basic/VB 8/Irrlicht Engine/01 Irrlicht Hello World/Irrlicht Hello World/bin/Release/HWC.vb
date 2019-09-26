Imports Irrlicht
Imports Irrlicht.Video
Imports Irrlicht.Core
Imports Irrlicht.Scene

Module HWC

    Sub Main()


        Dim Device As New IrrlichtDevice(DriverType.DIRECT3D9)  'Device quasi SubEngine

        Device.ResizeAble = True
        Device.WindowCaption = "1st HW Irrlicht | Barn 2006"

        'Texturen bereitstellen // man siehts ja
        Dim texSydney As ITexture = Device.VideoDriver.GetTexture("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.0\media\sydney.bmp")
        Dim texWall As ITexture = Device.VideoDriver.GetTexture("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.0\media\wall.bmp")
        Dim texLogo As ITexture = Device.VideoDriver.GetTexture("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.0\media\irrlichtlogoaligned.jpg")

        'Mesh von der Ollen laden
        Dim MSydney As Irrlicht.Scene.IAnimatedMesh = Device.SceneManager.GetMesh("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.0\media\sydney.md2")
       
        If MSydney Is Nothing Then
            System.Windows.Forms.MessageBox.Show("Mesh von Sydney nich gefunden", "HWC/Main/MSydney")
            Return
        End If

        'Kamera
        Dim Kamera As ICameraSceneNode = Device.SceneManager.AddCameraSceneNodeFPS(Nothing, 100, 100, -1)
        Kamera.Position = New Vector3D(20, 0, -50)

        'Knoten is der Bezug zwischen 3d Welt und 3d Modell (TestSceneNode) macht Würdel
        Dim Knoten As ISceneNode = Device.SceneManager.AddTestSceneNode(0, Nothing, -1, New Vector3D(30, -15, 0))
        Knoten.SetMaterialTexture(0, texWall)

        Knoten = Device.SceneManager.AddAnimatedMeshSceneNode(MSydney, Nothing, -1)
        Knoten.SetMaterialTexture(0, texSydney)
        Knoten.SetMaterialFlag(MaterialFlag.LIGHTING, False)

        ' Cursor zähmen
        Device.CursorControl.Visible = False

        ' Und laufen lassen
        Dim fps As Integer = 0

        While Device.Run() = True
            If Device.WindowActive Then
                'BG Farbe
                Device.VideoDriver.BeginScene(True, True, New Color(0, 200, 50, 100))
                Device.SceneManager.DrawAll() 'Der Rote Knopf

                ' Logo für den ders brauch
                Device.VideoDriver.Draw2DImage(texLogo, New Position2D(0, 0), New Rect(0, 0, 80, 30), New Rect(New Position2D(0, 0), Device.VideoDriver.ScreenSize), New Color(255, 255, 255, 255), False)
                Device.VideoDriver.EndScene()

                'FPS in der Headleiste anzeigen
                If (fps <> Device.VideoDriver.FPS) Then
                    fps = Device.VideoDriver.FPS
                    Device.WindowCaption = Device.VideoDriver.Name + " FPS: " + fps.ToString + " | 1st HW Irrlicht | Barn 2006"
                End If

            End If
        End While
    End Sub

End Module
