Public Class frm_About
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
    Friend WithEvents lblDDicated As System.Windows.Forms.Label
    Friend WithEvents btn_Mkay As System.Windows.Forms.Button
    Friend WithEvents lbl_Aka As System.Windows.Forms.Label
    Friend WithEvents lnk_Mail As System.Windows.Forms.LinkLabel
    Friend WithEvents pic_Logo As System.Windows.Forms.PictureBox
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Dim resources As System.Resources.ResourceManager = New System.Resources.ResourceManager(GetType(frm_About))
        Me.lbl_Aka = New System.Windows.Forms.Label()
        Me.lnk_Mail = New System.Windows.Forms.LinkLabel()
        Me.lblDDicated = New System.Windows.Forms.Label()
        Me.btn_Mkay = New System.Windows.Forms.Button()
        Me.pic_Logo = New System.Windows.Forms.PictureBox()
        Me.SuspendLayout()
        '
        'lbl_Aka
        '
        Me.lbl_Aka.BackColor = System.Drawing.Color.Transparent
        Me.lbl_Aka.Location = New System.Drawing.Point(0, 8)
        Me.lbl_Aka.Name = "lbl_Aka"
        Me.lbl_Aka.Size = New System.Drawing.Size(152, 16)
        Me.lbl_Aka.TabIndex = 0
        Me.lbl_Aka.Text = "Barn aka Rainbringer   | 2006"
        '
        'lnk_Mail
        '
        Me.lnk_Mail.Location = New System.Drawing.Point(152, 8)
        Me.lnk_Mail.Name = "lnk_Mail"
        Me.lnk_Mail.Size = New System.Drawing.Size(112, 16)
        Me.lnk_Mail.TabIndex = 1
        Me.lnk_Mail.TabStop = True
        Me.lnk_Mail.Text = "rainbringer@web.de"
        '
        'lblDDicated
        '
        Me.lblDDicated.BackColor = System.Drawing.Color.Transparent
        Me.lblDDicated.Location = New System.Drawing.Point(72, 32)
        Me.lblDDicated.Name = "lblDDicated"
        Me.lblDDicated.Size = New System.Drawing.Size(120, 48)
        Me.lblDDicated.TabIndex = 2
        Me.lblDDicated.Text = "D·Dicated to Schenkovich and his PlasticBullcock"
        '
        'btn_Mkay
        '
        Me.btn_Mkay.Location = New System.Drawing.Point(184, 64)
        Me.btn_Mkay.Name = "btn_Mkay"
        Me.btn_Mkay.Size = New System.Drawing.Size(72, 32)
        Me.btn_Mkay.TabIndex = 3
        Me.btn_Mkay.Text = "MKay"
        '
        'pic_Logo
        '
        Me.pic_Logo.Image = CType(resources.GetObject("pic_Logo.Image"), System.Drawing.Bitmap)
        Me.pic_Logo.Location = New System.Drawing.Point(0, 32)
        Me.pic_Logo.Name = "pic_Logo"
        Me.pic_Logo.Size = New System.Drawing.Size(64, 64)
        Me.pic_Logo.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage
        Me.pic_Logo.TabIndex = 4
        Me.pic_Logo.TabStop = False
        '
        'frm_About
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(258, 101)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.btn_Mkay, Me.pic_Logo, Me.lnk_Mail, Me.lbl_Aka, Me.lblDDicated})
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frm_About"
        Me.ShowInTaskbar = False
        Me.Text = "WM DeskPlaya"
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub frmAbout_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Mkay.Click

    End Sub
End Class
