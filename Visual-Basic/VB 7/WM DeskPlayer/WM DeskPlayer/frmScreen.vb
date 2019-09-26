Imports WMPLib

Public Class frm_Screen
    Inherits System.Windows.Forms.Form

#Region " Vom Windows Form Designer generierter Code "

    Public Sub New()
        MyBase.New()

        ' Dieser Aufruf ist für den Windows Form-Designer erforderlich.
        InitializeComponent()

        ' Initialisierungen nach dem Aufruf InitializeComponent() hinzufügen

    End Sub

    ' Die Form überschreibt den Löschvorgang der Basisklasse, um Komponenten zu bereinigen.
    Protected Overloads Overrides Sub Dispose(ByVal disposing As Boolean)
        If disposing Then
            If Not (components Is Nothing) Then
                components.Dispose()
            End If
        End If
        MyBase.Dispose(disposing)
    End Sub

    ' Für Windows Form-Designer erforderlich
    Private components As System.ComponentModel.IContainer

    'HINWEIS: Die folgende Prozedur ist für den Windows Form-Designer erforderlich
    'Sie kann mit dem Windows Form-Designer modifiziert werden.
    'Verwenden Sie nicht den Code-Editor zur Bearbeitung.
    Friend WithEvents NIcon As System.Windows.Forms.NotifyIcon
    Friend WithEvents MenuItem4 As System.Windows.Forms.MenuItem
    Friend WithEvents CMnuIcon As System.Windows.Forms.ContextMenu
    Friend WithEvents MenuItem13 As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuSndContrl As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuStop As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuNext As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuLast As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuSettings As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuRandom As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuPlay As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuRepeat As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuVisContrl As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuEnd As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuVolUp As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuVolDown As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuOpenEQ As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuS2 As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuS1 As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuS3 As System.Windows.Forms.MenuItem
    Friend WithEvents IMnuVol As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem2 As System.Windows.Forms.MenuItem
    Friend WithEvents IMnu_Mute As System.Windows.Forms.MenuItem
    Friend WithEvents WMPlaya As AxWMPLib.AxWindowsMediaPlayer
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Dim resources As System.Resources.ResourceManager = New System.Resources.ResourceManager(GetType(frm_Screen))
        Me.NIcon = New System.Windows.Forms.NotifyIcon(Me.components)
        Me.CMnuIcon = New System.Windows.Forms.ContextMenu()
        Me.IMnuSndContrl = New System.Windows.Forms.MenuItem()
        Me.IMnuPlay = New System.Windows.Forms.MenuItem()
        Me.IMnuStop = New System.Windows.Forms.MenuItem()
        Me.MenuItem2 = New System.Windows.Forms.MenuItem()
        Me.IMnuNext = New System.Windows.Forms.MenuItem()
        Me.IMnuLast = New System.Windows.Forms.MenuItem()
        Me.IMnuS2 = New System.Windows.Forms.MenuItem()
        Me.IMnuRandom = New System.Windows.Forms.MenuItem()
        Me.IMnuRepeat = New System.Windows.Forms.MenuItem()
        Me.IMnuVol = New System.Windows.Forms.MenuItem()
        Me.IMnuVolUp = New System.Windows.Forms.MenuItem()
        Me.IMnuVolDown = New System.Windows.Forms.MenuItem()
        Me.IMnu_Mute = New System.Windows.Forms.MenuItem()
        Me.IMnuS3 = New System.Windows.Forms.MenuItem()
        Me.IMnuOpenEQ = New System.Windows.Forms.MenuItem()
        Me.IMnuVisContrl = New System.Windows.Forms.MenuItem()
        Me.MenuItem13 = New System.Windows.Forms.MenuItem()
        Me.IMnuS1 = New System.Windows.Forms.MenuItem()
        Me.IMnuSettings = New System.Windows.Forms.MenuItem()
        Me.IMnuEnd = New System.Windows.Forms.MenuItem()
        Me.MenuItem4 = New System.Windows.Forms.MenuItem()
        Me.WMPlaya = New AxWMPLib.AxWindowsMediaPlayer()
        CType(Me.WMPlaya, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'NIcon
        '
        Me.NIcon.ContextMenu = Me.CMnuIcon
        Me.NIcon.Icon = CType(resources.GetObject("NIcon.Icon"), System.Drawing.Icon)
        Me.NIcon.Text = "WM DeskPlayer"
        Me.NIcon.Visible = True
        '
        'CMnuIcon
        '
        Me.CMnuIcon.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.IMnuSndContrl, Me.IMnuVol, Me.IMnuVisContrl, Me.IMnuS1, Me.IMnuSettings, Me.IMnuEnd})
        '
        'IMnuSndContrl
        '
        Me.IMnuSndContrl.Index = 0
        Me.IMnuSndContrl.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.IMnuPlay, Me.IMnuStop, Me.MenuItem2, Me.IMnuNext, Me.IMnuLast, Me.IMnuS2, Me.IMnuRandom, Me.IMnuRepeat})
        Me.IMnuSndContrl.Text = "MusicControl"
        '
        'IMnuPlay
        '
        Me.IMnuPlay.Index = 0
        Me.IMnuPlay.Text = "Play"
        '
        'IMnuStop
        '
        Me.IMnuStop.Index = 1
        Me.IMnuStop.Text = "Stop"
        '
        'MenuItem2
        '
        Me.MenuItem2.Index = 2
        Me.MenuItem2.Text = "-"
        '
        'IMnuNext
        '
        Me.IMnuNext.Index = 3
        Me.IMnuNext.Text = "Next Track"
        '
        'IMnuLast
        '
        Me.IMnuLast.Index = 4
        Me.IMnuLast.Text = "Last Track"
        '
        'IMnuS2
        '
        Me.IMnuS2.Index = 5
        Me.IMnuS2.Text = "-"
        '
        'IMnuRandom
        '
        Me.IMnuRandom.Index = 6
        Me.IMnuRandom.RadioCheck = True
        Me.IMnuRandom.Text = "Random"
        '
        'IMnuRepeat
        '
        Me.IMnuRepeat.Index = 7
        Me.IMnuRepeat.Text = "Repeat"
        '
        'IMnuVol
        '
        Me.IMnuVol.Index = 1
        Me.IMnuVol.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.IMnuVolUp, Me.IMnuVolDown, Me.IMnu_Mute, Me.IMnuS3, Me.IMnuOpenEQ})
        Me.IMnuVol.Text = "Volume"
        '
        'IMnuVolUp
        '
        Me.IMnuVolUp.Index = 0
        Me.IMnuVolUp.Text = "Volume Up"
        '
        'IMnuVolDown
        '
        Me.IMnuVolDown.Index = 1
        Me.IMnuVolDown.Text = "Volume Down"
        '
        'IMnu_Mute
        '
        Me.IMnu_Mute.Index = 2
        Me.IMnu_Mute.Text = "Mute"
        '
        'IMnuS3
        '
        Me.IMnuS3.Index = 3
        Me.IMnuS3.Text = "-"
        '
        'IMnuOpenEQ
        '
        Me.IMnuOpenEQ.Index = 4
        Me.IMnuOpenEQ.Text = "Open Windows EQ"
        '
        'IMnuVisContrl
        '
        Me.IMnuVisContrl.Index = 2
        Me.IMnuVisContrl.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.MenuItem13})
        Me.IMnuVisContrl.Text = "VisualisationControl"
        '
        'MenuItem13
        '
        Me.MenuItem13.Index = 0
        Me.MenuItem13.Text = "not yet available"
        '
        'IMnuS1
        '
        Me.IMnuS1.Index = 3
        Me.IMnuS1.Text = "-"
        '
        'IMnuSettings
        '
        Me.IMnuSettings.DefaultItem = True
        Me.IMnuSettings.Index = 4
        Me.IMnuSettings.Text = "Einstellungen..."
        '
        'IMnuEnd
        '
        Me.IMnuEnd.Index = 5
        Me.IMnuEnd.Text = "Beenden"
        '
        'MenuItem4
        '
        Me.MenuItem4.Index = -1
        Me.MenuItem4.Text = ""
        '
        'WMPlaya
        '
        Me.WMPlaya.enabled = True
        Me.WMPlaya.Name = "WMPlaya"
        Me.WMPlaya.OcxState = CType(resources.GetObject("WMPlaya.OcxState"), System.Windows.Forms.AxHost.State)
        Me.WMPlaya.Size = New System.Drawing.Size(592, 336)
        Me.WMPlaya.TabIndex = 0
        '
        'frm_Screen
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(1024, 739)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.WMPlaya})
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.Name = "frm_Screen"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.Manual
        Me.Text = "Windows Media DeskPlayer"
        Me.TopMost = True
        Me.TransparencyKey = System.Drawing.Color.Black
        CType(Me.WMPlaya, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub frm_DPlayer_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        WMPlaya.Size = New System.Drawing.Size(Me.Size.Width, Me.Size.Height)
    End Sub

    Private Sub NIcon_MouseDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles NIcon.MouseDown
        NIcon.ContextMenu.GetContextMenu()
    End Sub

    Private Sub NIcon_DoubleClick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles NIcon.DoubleClick
        Dim OSettings As New frm_Playa()
        OSettings.Show()
    End Sub

    Private Sub CMnuIcon_Popup(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CMnuIcon.Popup

    End Sub

    Private Sub IMnuSettings_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles IMnuSettings.Click
        Dim OSettings As New frm_Playa()
        OSettings.Show()
    End Sub

    Private Sub IMnuEnd_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles IMnuEnd.Click
        End
    End Sub

    Private Sub IMnuVolDown_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles IMnuVolDown.Click

    End Sub

    Protected Overrides Sub Finalize()
        MyBase.Finalize()
    End Sub

    Private Sub AxWindowsMediaPlayer1_Enter(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles WMPlaya.Enter

    End Sub

    Private Sub IMnuPlay_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles IMnuPlay.Click
        If WMPlaya.playState = WMPPlayState.wmppsPlaying Then
            WMPlaya.controls.pause()
            IMnuPlay.Text = "Play"
        Else
            WMPlaya.controls.play()
            IMnuPlay.Text = "Pause"
        End If
    End Sub
End Class
