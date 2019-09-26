Public Class frm_Verwalten
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
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents lst_Termine As System.Windows.Forms.ListBox
    Friend WithEvents btn_Neu As System.Windows.Forms.Button
    Friend WithEvents btn_Manage As System.Windows.Forms.Button
    Friend WithEvents btn_Del As System.Windows.Forms.Button
    Friend WithEvents btn_Schließen As System.Windows.Forms.Button
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.lst_Termine = New System.Windows.Forms.ListBox()
        Me.btn_Neu = New System.Windows.Forms.Button()
        Me.btn_Manage = New System.Windows.Forms.Button()
        Me.btn_Del = New System.Windows.Forms.Button()
        Me.btn_Schließen = New System.Windows.Forms.Button()
        Me.SuspendLayout()
        '
        'Label1
        '
        Me.Label1.Location = New System.Drawing.Point(0, 8)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(568, 16)
        Me.Label1.TabIndex = 0
        Me.Label1.Text = "Hier können sie ihre Termine verwalten, löschen und Neue hinzufügen."
        '
        'lst_Termine
        '
        Me.lst_Termine.HorizontalScrollbar = True
        Me.lst_Termine.Location = New System.Drawing.Point(104, 56)
        Me.lst_Termine.Name = "lst_Termine"
        Me.lst_Termine.SelectionMode = System.Windows.Forms.SelectionMode.MultiExtended
        Me.lst_Termine.Size = New System.Drawing.Size(456, 199)
        Me.lst_Termine.TabIndex = 0
        '
        'btn_Neu
        '
        Me.btn_Neu.Location = New System.Drawing.Point(8, 56)
        Me.btn_Neu.Name = "btn_Neu"
        Me.btn_Neu.Size = New System.Drawing.Size(88, 32)
        Me.btn_Neu.TabIndex = 1
        Me.btn_Neu.Text = "Neuer Termin"
        '
        'btn_Manage
        '
        Me.btn_Manage.Enabled = False
        Me.btn_Manage.Location = New System.Drawing.Point(8, 96)
        Me.btn_Manage.Name = "btn_Manage"
        Me.btn_Manage.Size = New System.Drawing.Size(88, 32)
        Me.btn_Manage.TabIndex = 2
        Me.btn_Manage.Text = "Termin bearbeiten"
        '
        'btn_Del
        '
        Me.btn_Del.Enabled = False
        Me.btn_Del.Location = New System.Drawing.Point(8, 136)
        Me.btn_Del.Name = "btn_Del"
        Me.btn_Del.Size = New System.Drawing.Size(88, 32)
        Me.btn_Del.TabIndex = 3
        Me.btn_Del.Text = "Termine löschen"
        '
        'btn_Schließen
        '
        Me.btn_Schließen.DialogResult = System.Windows.Forms.DialogResult.Cancel
        Me.btn_Schließen.Location = New System.Drawing.Point(480, 264)
        Me.btn_Schließen.Name = "btn_Schließen"
        Me.btn_Schließen.Size = New System.Drawing.Size(80, 24)
        Me.btn_Schließen.TabIndex = 4
        Me.btn_Schließen.Text = "Schließen"
        '
        'frm_Verwalten
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.CancelButton = Me.btn_Schließen
        Me.ClientSize = New System.Drawing.Size(568, 293)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.btn_Schließen, Me.btn_Del, Me.btn_Manage, Me.btn_Neu, Me.lst_Termine, Me.Label1})
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frm_Verwalten"
        Me.ShowInTaskbar = False
        Me.Text = "Termine verwalten"
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub btn_Neu_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Neu.Click
        Dim frm_Neu As New Frm_Neu()
        frm_Neu.ShowDialog()
    End Sub

    Private Sub frm_Verwalten_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub
End Class
