Imports System
Imports System.Text
Imports System.IO
Imports Irrlicht
Imports Irrlicht.Video
Imports Irrlicht.GUI
Imports Irrlicht.Core
Namespace IClassTest

    Class Program
        Implements IEventReceiver
        Private path As String = String.Empty
        Private Dev As IrrlichtDevice


        <STAThread()> _
        Shared Sub Main(ByVal args As String())
            Dim IKT As Program = New Program
            IKT.Menu()
        End Sub


        Public Function OnEvent(ByVal UI As Irrlicht.Event) As Boolean Implements IEventReceiver.OnEvent
            If UI.Type = EventType.GUIEvent Then
                Dim IID As Integer = UI.GUIEventCaller.ID
                Dim GUI As IGUIEnvironment = Dev.GUIEnvironment

                Select Case UI.GUIEventType
                    Case (GUIEvent.BUTTON_CLICKED)

                        If IID = 101 Then   'Start-Knopf
                            Dev.CloseDevice()
                            Return True
                        End If

                        If IID = 102 Then   'Quit-Knopf
                            Dev.CloseDevice()   'Device Beenden, nur wegen Sauberkeit
                            End                 'Und noch das Eingabefenster schließen
                            Return True
                        End If
                End Select

            End If
            Return False
        End Function
        Public Sub Menu()

            'Device initialisieren
            Dev = New IrrlichtDevice(Video.DriverType.DIRECT3D9, New Dimension2D(240, 240), 32, False, True, True)
            Dev.EventReceiver = Me
            Dev.WindowCaption = "IKT"

            Dim GUI As IGUIEnvironment = Dev.GUIEnvironment 'GUI Environment Kürzel-Variable
            Dim Skin As IGUISkin = GUI.Skin                 'Gui.Skin Kürzelvariable

            'Skin und im Folgenden Farben von Buttons festlegen
            GUI.CreateSkin(SkinType.WINDOWS_METALLIC)
            Skin.SetColor(SkinColor.E3D_SHADOW, New Color(255, 150, 130, 150))
            Skin.SetColor(SkinColor.E3D_HIGH_LIGHT, New Color(255, 255, 230, 255))
            Skin.SetColor(SkinColor.E3D_DARK_SHADOW, New Color(255, 80, 60, 80))
            Skin.SetColor(SkinColor.E3D_FACE, New Color(255, 255, 200, 255))


            'Buttons adden
            GUI.AddButton(New Rect(10, 30, 230, 110), Nothing, 101, "Starten")
            GUI.AddButton(New Rect(10, 130, 230, 210), Nothing, 102, "Beenden")

            'Engine starten
            While Dev.Run
                If Dev.WindowActive Then
                    Dev.VideoDriver.BeginScene(True, True, New Color(0, 255, 220, 210))
                    Dev.GUIEnvironment.DrawAll()



                    Dev.VideoDriver.EndScene()
                End If
            End While

        End Sub

    End Class
End Namespace
