' Jo check den Dreck
' Das 1st Autonome System samt Map, Collision, HUD, Arme und
' was mir noch an PiPaPo einfällt

Imports System
Imports System.Collections.Generic
Imports System.Text
Imports System.IO

Imports Irrlicht
Imports Irrlicht.Video
Imports Irrlicht.Core
Imports Irrlicht.Scene
Imports Irrlicht.GUI

Imports Microsoft.VisualBasic


Namespace First_Autonomes_System

    Class Program

        Implements IEventReceiver

        ' Notwendige Sub Main
        <STAThread()> _
        Shared Sub Main(ByVal args As String())
            Dim FAS As Program = New Program
            FAS.Run()   'das 'Run' kommt vom Namen der Sub
        End Sub

        'Über-Variablendeklarationen
        'Um über TAB die Kollision auszuschalten und wieder anzuschalten muss
        'CCRA neu initialisiert werden, dh. initialisieren von MMap, NMap für SMap für CCRA
        Private Device As IrrlichtDevice = Nothing
        Private Cam As ICameraSceneNode = Nothing
        Private MMap As IAnimatedMesh = Nothing
        Private NMap As ISceneNode = Nothing
        Private SMap As ITriangleSelector = Nothing
        Private CCRA As ISceneNodeAnimator = Nothing

        'OnEvent Funktion zum Abarbeiten der Benutzereingaben
        Public Function OnEvent(ByVal PI As Irrlicht.Event) As Boolean Implements Irrlicht.IEventReceiver.OnEvent
            If PI.Type = EventType.KeyInput Then
                Select Case PI.Key
                    Case KeyCode.KEY_TAB    'Position zurücksetzen
                        Cam.RemoveAnimator(CCRA)
                        Cam.Position = New Vector3D(0, 500, 0)
                        CCRA = Device.SceneManager.CreateCollisionResponseAnimator(SMap, Cam, New Vector3D(15, 70, 25), New Vector3D(0, -3, 0), New Vector3D(0, 60, 0), 0)
                        Cam.AddAnimator(CCRA)
                End Select
            End If
            If PI.Type = EventType.MouseInput Then
                Select Case PI.MouseInputType
                    Case MouseInputEvent.PressedDownRight
                        Cam.RemoveAnimator(CCRA)
                    Case MouseInputEvent.LeftUpRight
                        CCRA = Device.SceneManager.CreateCollisionResponseAnimator(SMap, Cam, New Vector3D(15, 70, 25), New Vector3D(0, -3, 0), New Vector3D(0, 60, 0), 0)
                        Cam.AddAnimator(CCRA)
                End Select

            End If
        End Function

        Sub Run()

            'Device erstellen, bestimmen, dass Device Event Receiver ist
            Device = New IrrlichtDevice(DriverType.DIRECT3D8, New Dimension2D(800, 600), 32, False, True, True)
            Device.EventReceiver = Me
            Device.VideoDriver.SetTextureCreationFlag(TextureCreationFlag.ALWAYS_32_BIT, True) 'für Parallax Mapping
            'Fog
            Device.VideoDriver.SetFog(New Color(0, 145, 225, 255), True, 100, 1000, 10, True, True)

            'Texturen der Skybox laden
            Dim texu As ITexture = Device.VideoDriver.GetTexture("Raumschiffmap\Oben.tga")
            Dim texd As ITexture = Device.VideoDriver.GetTexture("Raumschiffmap\Unten.tga")
            Dim texn As ITexture = Device.VideoDriver.GetTexture("Raumschiffmap\N.tga")
            Dim texo As ITexture = Device.VideoDriver.GetTexture("Raumschiffmap\O.tga")
            Dim texs As ITexture = Device.VideoDriver.GetTexture("Raumschiffmap\S.tga")
            Dim texw As ITexture = Device.VideoDriver.GetTexture("Raumschiffmap\W.tga")
            Dim pblue As ITexture = Device.VideoDriver.GetTexture("PBlue.bmp")

            Device.WindowCaption = "Barn's First Automomes System"  'Headline Caption

            'Kamera initialisieren und setzen
            Cam = Device.SceneManager.AddCameraSceneNodeFPS(Nothing, 100, 800, -1)
            Cam.Position = New Vector3D(0, 500, 0)
            Cam.FarValue = 20000.0F
            'Cam.FOV = 1.0F

            'SkyboxNode kreieren
            Dim Sky As ISceneNode = Device.SceneManager.AddSkyBoxSceneNode(texu, texd, texo, texw, texn, texs, Nothing, -1)

            'Map
            MMap = Device.SceneManager.GetMesh("Raumschiffmap\raumschiff.3ds")
            Dim TMap As IMesh = Device.SceneManager.MeshManipulator.CreateMeshWithTangents(MMap.GetMesh(0))
            NMap = Device.SceneManager.AddMeshSceneNode(TMap, Nothing, 0)  'minPolys nich zwingend nötig
            NMap.SetMaterialFlag(MaterialFlag.LIGHTING, True)
            NMap.Scale = New Vector3D(2.5, 2.5, 2.5)
      NMap.SetMaterialTexture(1, Device.VideoDriver.GetTexture("Boden1N.bmp"))
            Device.SceneManager.MeshManipulator.MakePlanarTextureMapping(TMap, 0.01F)
            NMap.SetMaterialType(MaterialType.PARALLAX_MAP_SOLID)
      NMap.SetMaterialTexture(0, Device.VideoDriver.GetTexture("Boden1.bmp"))

            'Kreisendes Licht / "Taschenlampe" an der Cam
            Dim Licht As ISceneNode = Device.SceneManager.AddLightSceneNode(Nothing, New Vector3D(0, 100, 0), New Colorf(255, 255, 255, 0), 100.0F, 0)
            Dim NLicht As ISceneNode = Device.SceneManager.AddTestSceneNode(50, Licht, 0, Licht.Position)
            Dim Licht2 As ISceneNode = Device.SceneManager.AddLightSceneNode(Nothing, New Vector3D(410, 300, -1400), New Colorf(255, 255, 255, 0), 360.0F, 0)
            Dim NLicht2 As ISceneNode = Device.SceneManager.AddTestSceneNode(50, Licht2, 0, Licht2.Position)
            Dim LichtAnim As ISceneNodeAnimator = Device.SceneManager.CreateFlyCircleAnimator(New Vector3D(0, 500, 0), 250, 0.002F)
            Licht.AddAnimator(LichtAnim)
            Licht2.AddAnimator(LichtAnim)
            Dim Flash As ISceneNode = Device.SceneManager.AddLightSceneNode(Cam, Cam.Position + New Vector3D(30, -10, 10), New Colorf(255, 255, 255), 1.15F, 0)
            'Selector initialisieren; .GetMesh(0) wegen IMesh und nich IAnimatedMesh
            SMap = Device.SceneManager.CreateOctTreeTriangleSelector(MMap.GetMesh(0), NMap, 128)

            'CamCollisionResponseAnimater CCRA
            CCRA = Device.SceneManager.CreateCollisionResponseAnimator(SMap, Cam, New Vector3D(15, 70, 25), New Vector3D(0, -3, 0), New Vector3D(0, 60, 0), 0)
            Cam.AddAnimator(CCRA)

            'Pointer
            Dim Pointer As IBillboardSceneNode = Device.SceneManager.AddBillboardSceneNode(Nothing, New Dimension2Df(20, 20), New Vector3D, 0)
            Pointer.SetMaterialTexture(0, pblue)
            Pointer.SetMaterialFlag(MaterialFlag.LIGHTING, False)
            Pointer.SetMaterialFlag(MaterialFlag.ZBUFFER, False)
            Pointer.SetMaterialType(MaterialType.TRANSPARENT_ADD_COLOR)

            'wird benötigt umd  Line anzuzeigen, warum auch immer--> brauch MaterialType.Transparent_Add_Color
            Dim NLine As ISceneNode = Device.SceneManager.AddTestSceneNode(0.3, Cam, 0, New Vector3D(2.5, -2, 5))
            NLine.SetMaterialType(MaterialType.TRANSPARENT_ADD_COLOR)

            'Mauscursor Invisible
            Device.CursorControl.Visible = False

            While Device.Run() = True 'Device Starten

                If Device.WindowActive Then
                    Device.VideoDriver.BeginScene(True, True, New Color(0, 0, 0, 0))

                    Device.SceneManager.DrawAll() 'DrawAll

                    'Hud ziehen
                    Device.VideoDriver.Draw2DRectangle(New Color(150, 255, 100, 255), New Rect(10, 500, 320, 580))

                    'Hilfslinie für Kollision
                    Dim sline As Line3D = New Line3D
                    sline.start = Cam.Position
                    sline.end = sline.start + (Cam.Target - sline.start).Normalize * 10000

                    Dim XPunkt As Vector3D
                    Dim tri As Triangle3D
                    Dim Font As IGUIFont = Device.GUIEnvironment.BuiltInFont

                    'Kollision Pointer/Wand .. oder auch nich ... "Schusslinie" ziehen
                    'zu beachten ist die  Sache mit der Draw3DLine Routine in der If und in der Else
                    'zwei gänzlich versch. Positionen, machen das quasi möglich
                    'aber sind nicht untereinander austauschbar
                    '.SetTransform() macht das möglich. so wird im Ersteren immer die BildMITTE angepeilt --> Absicht

                    If Device.SceneManager.SceneCollisionManager.GetCollisionPoint(sline, SMap, XPunkt, tri) Then
                        Pointer.Visible = True
                        Pointer.SetMaterialTexture(0, pblue)
                        Pointer.Position = XPunkt
                        Device.VideoDriver.SetTransform(TransformationState.WORLD, New Matrix4)
                        Device.VideoDriver.Draw3DLine(NLine.AbsolutePosition, XPunkt, New Color(0, 255, 0, 0))
                        'Target.Position aufs Hud schreiben, Entfernung Cam<->Target
                        Font.Draw(XPunkt.X, New Rect(100, 520, 200, 580), New Color(0, 255, 255, 255), False, False)
                        Font.Draw(XPunkt.Y, New Rect(100, 530, 200, 580), New Color(0, 255, 255, 255), False, False)
                        Font.Draw(XPunkt.Z, New Rect(100, 540, 200, 580), New Color(0, 255, 255, 255), False, False)
                        Font.Draw((Cam.Position - XPunkt).GetLength, New Rect(185, 520, 300, 580), New Color(0, 255, 255, 255), False, False)
                    Else
                        Device.VideoDriver.Draw3DLine(NLine.Position, NLine.Position + New Vector3D(0, 0, 3000), New Color(0, 255, 0, 0))
                        Pointer.Visible = False
                        Font.Draw("########", New Rect(100, 520, 200, 580), New Color(0, 255, 255, 255), False, False)
                        Font.Draw("########", New Rect(100, 530, 200, 580), New Color(0, 255, 255, 255), False, False)
                        Font.Draw("########", New Rect(100, 540, 200, 580), New Color(0, 255, 255, 255), False, False)
                        Font.Draw("########", New Rect(185, 520, 300, 580), New Color(0, 255, 255, 255), False, False)
                    End If

                    'Hierhin schreiben aus ökonimischen Gründen
                    Font.Draw("Eigene Position", New Rect(15, 510, 100, 580), New Color(0, 255, 255, 255), False, False)
                    Font.Draw("Target Position", New Rect(100, 510, 200, 580), New Color(0, 255, 255, 255), False, False)
                    Font.Draw("Entfernung Cam<->Target", New Rect(185, 510, 300, 580), New Color(0, 255, 255, 255), False, False)

                    'Camera.Position aufs HUD schreiben
                    '(Muss nach der roten Linie kommen, sonst wird diese nicht angezeigt, warum auch immer)
                    Font.Draw(Cam.Position.X.ToString, New Rect(15, 520, 100, 580), New Color(0, 255, 255, 255), False, False)
                    Font.Draw(Cam.Position.Y.ToString, New Rect(15, 530, 100, 580), New Color(0, 255, 255, 255), False, False)
                    Font.Draw(Cam.Position.Z.ToString, New Rect(15, 540, 100, 580), New Color(0, 255, 255, 255), False, False)

                    Device.VideoDriver.EndScene()

                End If
            End While
        End Sub

    End Class

End Namespace