Imports WMPLib

Public Class frm_WMPTester
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
    Friend WithEvents WMPlayer As AxWMPLib.AxWindowsMediaPlayer
    Friend WithEvents btn_Play As System.Windows.Forms.Button
    Friend WithEvents btn_Stop As System.Windows.Forms.Button
    Friend WithEvents txt_Path As System.Windows.Forms.TextBox
    Friend WithEvents btn_Exit As System.Windows.Forms.Button
    Friend WithEvents btn_Go As System.Windows.Forms.Button
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Dim resources As System.Resources.ResourceManager = New System.Resources.ResourceManager(GetType(frm_WMPTester))
        Me.WMPlayer = New AxWMPLib.AxWindowsMediaPlayer()
        Me.btn_Play = New System.Windows.Forms.Button()
        Me.btn_Stop = New System.Windows.Forms.Button()
        Me.txt_Path = New System.Windows.Forms.TextBox()
        Me.btn_Exit = New System.Windows.Forms.Button()
        Me.btn_Go = New System.Windows.Forms.Button()
        CType(Me.WMPlayer, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'WMPlayer
        '
        Me.WMPlayer.Name = "WMPlayer"
        Me.WMPlayer.OcxState = CType(resources.GetObject("WMPlayer.OcxState"), System.Windows.Forms.AxHost.State)
        Me.WMPlayer.Size = New System.Drawing.Size(1024, 648)
        Me.WMPlayer.TabIndex = 0
        '
        'btn_Play
        '
        Me.btn_Play.BackColor = System.Drawing.Color.Transparent
        Me.btn_Play.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.btn_Play.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_Play.ForeColor = System.Drawing.Color.Firebrick
        Me.btn_Play.Location = New System.Drawing.Point(40, 704)
        Me.btn_Play.Name = "btn_Play"
        Me.btn_Play.Size = New System.Drawing.Size(96, 24)
        Me.btn_Play.TabIndex = 1
        Me.btn_Play.Text = "Play"
        '
        'btn_Stop
        '
        Me.btn_Stop.BackColor = System.Drawing.Color.Transparent
        Me.btn_Stop.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.btn_Stop.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_Stop.ForeColor = System.Drawing.Color.Firebrick
        Me.btn_Stop.Location = New System.Drawing.Point(144, 704)
        Me.btn_Stop.Name = "btn_Stop"
        Me.btn_Stop.Size = New System.Drawing.Size(96, 24)
        Me.btn_Stop.TabIndex = 2
        Me.btn_Stop.Text = "Stop"
        '
        'txt_Path
        '
        Me.txt_Path.ForeColor = System.Drawing.Color.FromArgb(CType(10, Byte), CType(10, Byte), CType(10, Byte))
        Me.txt_Path.Location = New System.Drawing.Point(40, 672)
        Me.txt_Path.Name = "txt_Path"
        Me.txt_Path.Size = New System.Drawing.Size(728, 20)
        Me.txt_Path.TabIndex = 3
        Me.txt_Path.Text = "D:\Andreas_jr\Gerippte Musik\My Playlists\Télépopmusik - Genetic World.wpl"
        '
        'btn_Exit
        '
        Me.btn_Exit.BackColor = System.Drawing.Color.Transparent
        Me.btn_Exit.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.btn_Exit.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_Exit.ForeColor = System.Drawing.Color.Firebrick
        Me.btn_Exit.Location = New System.Drawing.Point(336, 704)
        Me.btn_Exit.Name = "btn_Exit"
        Me.btn_Exit.Size = New System.Drawing.Size(96, 24)
        Me.btn_Exit.TabIndex = 5
        Me.btn_Exit.Text = "Exit"
        '
        'btn_Go
        '
        Me.btn_Go.BackColor = System.Drawing.Color.Transparent
        Me.btn_Go.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.btn_Go.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_Go.ForeColor = System.Drawing.Color.Firebrick
        Me.btn_Go.Location = New System.Drawing.Point(776, 672)
        Me.btn_Go.Name = "btn_Go"
        Me.btn_Go.Size = New System.Drawing.Size(32, 24)
        Me.btn_Go.TabIndex = 7
        Me.btn_Go.Text = "Go"
        '
        'frm_WMPTester
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.BackColor = System.Drawing.Color.Black
        Me.ClientSize = New System.Drawing.Size(1024, 768)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.btn_Go, Me.btn_Exit, Me.txt_Path, Me.btn_Stop, Me.btn_Play, Me.WMPlayer})
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None
        Me.Name = "frm_WMPTester"
        Me.Opacity = 0.5
        Me.ShowInTaskbar = False
        Me.StartPosition = System.Windows.Forms.FormStartPosition.Manual
        Me.Text = "WMPlugin testen | Barn 06"
        Me.TopMost = True
        Me.TransparencyKey = System.Drawing.Color.Black
        CType(Me.WMPlayer, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        WMPlayer.URL = txt_Path.Text
    End Sub

    Private Sub btn_Play_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Play.Click
        If WMPlayer.playState = WMPPlayState.wmppsPlaying Then
            WMPlayer.controls.pause()
            btn_Play.Text = "Play"
        Else
            WMPlayer.controls.play()
            btn_Play.Text = "Pause"
        End If
    End Sub

    Private Sub btn_Stop_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Stop.Click
        WMPlayer.controls.stop()
        btn_Play.Text = "Play"
    End Sub

    Private Sub btn_Exit_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Exit.Click
        End
    End Sub

    Private Sub txt_Path_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txt_Path.TextChanged

    End Sub

    Private Sub btn_Go_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Go.Click
        WMPlayer.URL = txt_Path.Text
    End Sub
End Class
