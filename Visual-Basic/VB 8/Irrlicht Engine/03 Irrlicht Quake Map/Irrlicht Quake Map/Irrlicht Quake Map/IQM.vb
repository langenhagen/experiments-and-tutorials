Imports System
Imports System.Collections.Generic
Imports System.Text
Imports System.IO
Imports Irrlicht
Imports Irrlicht.Video
Imports Irrlicht.Core
Imports Irrlicht.Scene

Module IQM

    Dim driverType As DriverType

    Sub Main()

        Dim sb As StringBuilder = New StringBuilder
        sb.AppendLine("Ok, wähl ma den Grafikmodus:")
        sb.AppendLine("(a) Direct3D 9.0c" & Microsoft.VisualBasic.Chr(10) & "(b) Direct3D 8.1" & Microsoft.VisualBasic.Chr(10) & "(c) OpenGL 1.5")
        sb.AppendLine("(d) Software Renderer" & Microsoft.VisualBasic.Chr(10) & "(e) Apfelbaum Software Renderer")
        sb.AppendLine("(f) Null Device" & Microsoft.VisualBasic.Chr(10) & "(andere Taste) exit" & Microsoft.VisualBasic.Chr(10) & "" & Microsoft.VisualBasic.Chr(10) & "")

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

        Dim device As IrrlichtDevice = New IrrlichtDevice(driverType, New Dimension2D(800, 600), 32, False, True, True)
        If device Is Nothing Then
            tOut.Write("Szene konnte nicht gerendert werden")
            Return
        End If

        device.FileSystem.AddZipFileArchive("D:\Andreas_jr\Andi\Privatszeug\Programmierungen\Eigenes\VB 8.0\Irrlicht Engine\03 Irrlicht Quake Map\Irrlicht Quake Map\Irrlicht Quake Map\bin\Debug\map-20kdm2.pk3")

        Dim Map As IAnimatedMesh = device.SceneManager.GetMesh("20kdm2.bsp")
        Dim node As ISceneNode = Nothing

        If Not (Map Is Nothing) Then
            node = device.SceneManager.AddOctTreeSceneNode(Map, Nothing, 0)
        End If

        If Not (node Is Nothing) Then
            node.Position = (New Vector3D(-1300, -144, -1249))
        End If

        device.SceneManager.AddCameraSceneNodeMaya(Nothing, 100, 100, 100, 1)
        device.SceneManager.AddCameraSceneNodeFPS()

        device.CursorControl.Visible = False
        Dim lastFPS As Integer = -1
        While device.Run
            If device.WindowActive Then
                device.VideoDriver.BeginScene(True, True, New Color(0, 200, 200, 200))
                device.SceneManager.DrawAll()
                device.VideoDriver.EndScene()
                Dim fps As Integer = device.VideoDriver.FPS
                If Not (lastFPS = fps) Then
                    device.WindowCaption = "Barn's - Quake 3 Map Baeispiel [" + device.VideoDriver.Name + "] FPS:" + fps.ToString
                    lastFPS = fps
                End If
            End If
        End While

    End Sub

End Module
