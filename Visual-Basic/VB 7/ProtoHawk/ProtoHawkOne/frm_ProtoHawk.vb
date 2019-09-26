Imports WMPLib

Public Class frm_ProtoHawk
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
    Friend WithEvents btn_Exit As System.Windows.Forms.Button
    Friend WithEvents Pic As System.Windows.Forms.PictureBox
    Friend WithEvents AxWindowsMediaPlayer1 As AxWMPLib.AxWindowsMediaPlayer
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Dim resources As System.Resources.ResourceManager = New System.Resources.ResourceManager(GetType(frm_ProtoHawk))
        Me.btn_Exit = New System.Windows.Forms.Button()
        Me.Pic = New System.Windows.Forms.PictureBox()
        Me.AxWindowsMediaPlayer1 = New AxWMPLib.AxWindowsMediaPlayer()
        CType(Me.AxWindowsMediaPlayer1, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'btn_Exit
        '
        Me.btn_Exit.BackColor = System.Drawing.Color.Gainsboro
        Me.btn_Exit.Location = New System.Drawing.Point(24, 16)
        Me.btn_Exit.Name = "btn_Exit"
        Me.btn_Exit.Size = New System.Drawing.Size(136, 64)
        Me.btn_Exit.TabIndex = 0
        Me.btn_Exit.Text = "Exit"
        '
        'Pic
        '
        Me.Pic.Image = CType(resources.GetObject("Pic.Image"), System.Drawing.Bitmap)
        Me.Pic.Location = New System.Drawing.Point(24, 96)
        Me.Pic.Name = "Pic"
        Me.Pic.Size = New System.Drawing.Size(391, 52)
        Me.Pic.SizeMode = System.Windows.Forms.PictureBoxSizeMode.AutoSize
        Me.Pic.TabIndex = 1
        Me.Pic.TabStop = False
        '
        'AxWindowsMediaPlayer1
        '
        Me.AxWindowsMediaPlayer1.enabled = True
        Me.AxWindowsMediaPlayer1.Location = New System.Drawing.Point(264, 272)
        Me.AxWindowsMediaPlayer1.Name = "AxWindowsMediaPlayer1"
        Me.AxWindowsMediaPlayer1.OcxState = CType(resources.GetObject("AxWindowsMediaPlayer1.OcxState"), System.Windows.Forms.AxHost.State)
        Me.AxWindowsMediaPlayer1.Size = New System.Drawing.Size(248, 72)
        Me.AxWindowsMediaPlayer1.TabIndex = 2
        '
        'frm_ProtoHawk
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.BackColor = System.Drawing.Color.Black
        Me.ClientSize = New System.Drawing.Size(536, 384)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.AxWindowsMediaPlayer1, Me.Pic, Me.btn_Exit})
        Me.ForeColor = System.Drawing.Color.Black
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None
        Me.Name = "frm_ProtoHawk"
        Me.Text = "frm_ProtoHawk"
        Me.WindowState = System.Windows.Forms.FormWindowState.Maximized
        CType(Me.AxWindowsMediaPlayer1, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub frm_ProtoHawk_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Pic.Location = New System.Drawing.Point((Me.Size.Width / 2) - 196, (Me.Size.Height / 2) - 26)

    End Sub

    Private Sub Button1_Click_1(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Exit.Click
        End
    End Sub

End Class
