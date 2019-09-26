Imports Irrlicht
Imports Irrlicht.Video
Imports Irrlicht.Core
Imports Irrlicht.Scene

Module Main

    Sub Main()

        Dim Device As New IrrlichtDevice(DriverType.DIRECT3D9)  'Device quasi SubEngine

        Device.ResizeAble = True
        Device.WindowCaption = " Maps__) Irrlicht__) Barn 2006"

        Dim texMap As ITexture = Device.VideoDriver.GetTexture("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.0\media\irrlichtlogoaligned.jpg")
        Dim Map As Irrlicht.Scene.IAnimatedMesh = Device.SceneManager.GetMesh("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.0\media\\20kdm2.bsp")
        Dim Kamera As ICameraSceneNode = Device.SceneManager.AddCameraSceneNodeFPS(Nothing, 100, 1000, -1)
        Kamera.Position = New Vector3D(0, 0, 0)

        Dim Knoten As ISceneNode = Device.SceneManager.AddOctTreeSceneNode(Map, Nothing, -1)
        Knoten.Position = New Vector3D(-1300, -144, -1249)
        Knoten.SetMaterialFlag(MaterialFlag.LIGHTING, False)

        Device.CursorControl.Visible = True
        Dim fps As Integer = 0

        While Device.Run() = True
            If Device.WindowActive Then
                Device.VideoDriver.BeginScene(True, True, New Color(0, 200, 50, 100))
                Device.SceneManager.DrawAll()

                Device.VideoDriver.EndScene()

                If (fps <> Device.VideoDriver.FPS) Then
                    fps = Device.VideoDriver.FPS
                    Device.WindowCaption = Device.VideoDriver.Name + " FPS: " + fps.ToString + "__) Maps by Irrlicht__) Barn 2006"
                End If

            End If
        End While
    End Sub

End Module
