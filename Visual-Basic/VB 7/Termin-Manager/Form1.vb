Public Class Form1
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
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents btn_Neu As System.Windows.Forms.Button
    Friend WithEvents btn_Del As System.Windows.Forms.Button
    Friend WithEvents btn_Manag As System.Windows.Forms.Button
    Friend WithEvents btn_Close As System.Windows.Forms.Button
    Friend WithEvents lbl_Aufgabe As System.Windows.Forms.Label
    Friend WithEvents cldr_Kalender As System.Windows.Forms.MonthCalendar
    Friend WithEvents lbl_Uhr As System.Windows.Forms.Label
    Friend WithEvents tim_Uhr As System.Windows.Forms.Timer
    Friend WithEvents pic_Uhr As System.Windows.Forms.PictureBox
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Dim resources As System.Resources.ResourceManager = New System.Resources.ResourceManager(GetType(Form1))
        Me.btn_Neu = New System.Windows.Forms.Button()
        Me.btn_Del = New System.Windows.Forms.Button()
        Me.btn_Manag = New System.Windows.Forms.Button()
        Me.btn_Close = New System.Windows.Forms.Button()
        Me.lbl_Aufgabe = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.cldr_Kalender = New System.Windows.Forms.MonthCalendar()
        Me.lbl_Uhr = New System.Windows.Forms.Label()
        Me.tim_Uhr = New System.Windows.Forms.Timer(Me.components)
        Me.pic_Uhr = New System.Windows.Forms.PictureBox()
        Me.SuspendLayout()
        '
        'btn_Neu
        '
        Me.btn_Neu.Location = New System.Drawing.Point(56, 48)
        Me.btn_Neu.Name = "btn_Neu"
        Me.btn_Neu.Size = New System.Drawing.Size(128, 24)
        Me.btn_Neu.TabIndex = 0
        Me.btn_Neu.Text = "Neuer Termin"
        '
        'btn_Del
        '
        Me.btn_Del.Location = New System.Drawing.Point(56, 136)
        Me.btn_Del.Name = "btn_Del"
        Me.btn_Del.Size = New System.Drawing.Size(128, 24)
        Me.btn_Del.TabIndex = 1
        Me.btn_Del.Text = "Alle Termine Löschen"
        '
        'btn_Manag
        '
        Me.btn_Manag.Location = New System.Drawing.Point(56, 80)
        Me.btn_Manag.Name = "btn_Manag"
        Me.btn_Manag.Size = New System.Drawing.Size(128, 24)
        Me.btn_Manag.TabIndex = 2
        Me.btn_Manag.Text = "Termine Verwalten"
        '
        'btn_Close
        '
        Me.btn_Close.Location = New System.Drawing.Point(448, 192)
        Me.btn_Close.Name = "btn_Close"
        Me.btn_Close.Size = New System.Drawing.Size(80, 24)
        Me.btn_Close.TabIndex = 3
        Me.btn_Close.Text = "Schließen"
        '
        'lbl_Aufgabe
        '
        Me.lbl_Aufgabe.Location = New System.Drawing.Point(8, 8)
        Me.lbl_Aufgabe.Name = "lbl_Aufgabe"
        Me.lbl_Aufgabe.Size = New System.Drawing.Size(424, 32)
        Me.lbl_Aufgabe.TabIndex = 4
        Me.lbl_Aufgabe.Text = "Wählen sie die von ihnen gewünschete Aktion aus."
        '
        'Label2
        '
        Me.Label2.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D
        Me.Label2.Location = New System.Drawing.Point(236, 44)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(172, 162)
        Me.Label2.TabIndex = 6
        '
        'cldr_Kalender
        '
        Me.cldr_Kalender.Location = New System.Drawing.Point(240, 48)
        Me.cldr_Kalender.Name = "cldr_Kalender"
        Me.cldr_Kalender.TabIndex = 7
        '
        'lbl_Uhr
        '
        Me.lbl_Uhr.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D
        Me.lbl_Uhr.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lbl_Uhr.Location = New System.Drawing.Point(274, 12)
        Me.lbl_Uhr.Name = "lbl_Uhr"
        Me.lbl_Uhr.Size = New System.Drawing.Size(162, 28)
        Me.lbl_Uhr.TabIndex = 8
        Me.lbl_Uhr.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        '
        'tim_Uhr
        '
        Me.tim_Uhr.Enabled = True
        Me.tim_Uhr.Interval = 1000
        '
        'pic_Uhr
        '
        Me.pic_Uhr.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.pic_Uhr.Image = CType(resources.GetObject("pic_Uhr.Image"), System.Drawing.Bitmap)
        Me.pic_Uhr.Location = New System.Drawing.Point(8, 48)
        Me.pic_Uhr.Name = "pic_Uhr"
        Me.pic_Uhr.Size = New System.Drawing.Size(40, 40)
        Me.pic_Uhr.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.pic_Uhr.TabIndex = 9
        Me.pic_Uhr.TabStop = False
        '
        'Form1
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(530, 223)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.pic_Uhr, Me.lbl_Uhr, Me.cldr_Kalender, Me.Label2, Me.lbl_Aufgabe, Me.btn_Close, Me.btn_Manag, Me.btn_Del, Me.btn_Neu})
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.MaximizeBox = False
        Me.Name = "Form1"
        Me.Text = "Termin-Manager"
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        lbl_Uhr.Text = TimeString
    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Close.Click
        End
    End Sub

    Private Sub lbl_Uhr_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles lbl_Uhr.Click
        lbl_Uhr.Text = TimeString
    End Sub

    Private Sub tim_Uhr_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles tim_Uhr.Tick
        lbl_Uhr.Text = TimeString
    End Sub

    Private Sub bt As System.EventArgs) Handles btn_Neu.Click
        Dim frm_Neu As New Frm_Neu()
        frm_Neu.ShowDialog()
    End Sub

    Private Sub btn_Manag_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Manag.Click
        Dim frm_Verwalten As New frm_Verwalten()
        frm_Verwalten.ShowDialog()
    End Sub

    Private Sub cldr_Kalender_DateChanged(ByVal sender As System.Object, ByVal e As System.Windows.Forms.DateRangeEventArgs) Handles cldr_Kalender.DateChanged

    End Sub

    Private Sub btn_Neu_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Neu.Click

    End Sub
End Class
