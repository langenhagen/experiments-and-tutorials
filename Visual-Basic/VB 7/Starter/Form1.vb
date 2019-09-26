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
    Friend WithEvents cmb_Datei As System.Windows.Forms.ComboBox
    Friend WithEvents lst_Verlauf As System.Windows.Forms.ListBox
    Friend WithEvents lbl_Beschreibung2 As System.Windows.Forms.Label
    Friend WithEvents lbl_Beschreibung As System.Windows.Forms.Label
    Friend WithEvents btn_OK As System.Windows.Forms.Button
    Friend WithEvents btn_Hilfe As System.Windows.Forms.Button
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.cmb_Datei = New System.Windows.Forms.ComboBox()
        Me.lst_Verlauf = New System.Windows.Forms.ListBox()
        Me.lbl_Beschreibung2 = New System.Windows.Forms.Label()
        Me.lbl_Beschreibung = New System.Windows.Forms.Label()
        Me.btn_OK = New System.Windows.Forms.Button()
        Me.btn_Hilfe = New System.Windows.Forms.Button()
        Me.SuspendLayout()
        '
        'cmb_Datei
        '
        Me.cmb_Datei.Location = New System.Drawing.Point(8, 48)
        Me.cmb_Datei.Name = "cmb_Datei"
        Me.cmb_Datei.Size = New System.Drawing.Size(344, 21)
        Me.cmb_Datei.TabIndex = 0
        '
        'lst_Verlauf
        '
        Me.lst_Verlauf.Location = New System.Drawing.Point(8, 112)
        Me.lst_Verlauf.Name = "lst_Verlauf"
        Me.lst_Verlauf.Size = New System.Drawing.Size(432, 95)
        Me.lst_Verlauf.TabIndex = 2
        Me.lst_Verlauf.TabStop = False
        '
        'lbl_Beschreibung2
        '
        Me.lbl_Beschreibung2.Location = New System.Drawing.Point(8, 88)
        Me.lbl_Beschreibung2.Name = "lbl_Beschreibung2"
        Me.lbl_Beschreibung2.Size = New System.Drawing.Size(248, 16)
        Me.lbl_Beschreibung2.TabIndex = 2
        Me.lbl_Beschreibung2.Text = "Bereits geöffnete Vorgänge:"
        '
        'lbl_Beschreibung
        '
        Me.lbl_Beschreibung.Location = New System.Drawing.Point(8, 8)
        Me.lbl_Beschreibung.Name = "lbl_Beschreibung"
        Me.lbl_Beschreibung.Size = New System.Drawing.Size(432, 32)
        Me.lbl_Beschreibung.TabIndex = 3
        Me.lbl_Beschreibung.Text = "Geben Sie hier den Namen eines Programms, Ordners, Dokuments oder einer Internetr" & _
        "essource an."
        '
        'btn_OK
        '
        Me.btn_OK.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn_OK.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.btn_OK.Location = New System.Drawing.Point(360, 48)
        Me.btn_OK.Name = "btn_OK"
        Me.btn_OK.Size = New System.Drawing.Size(80, 24)
        Me.btn_OK.TabIndex = 1
        Me.btn_OK.Text = "OK"
        '
        'btn_Hilfe
        '
        Me.btn_Hilfe.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn_Hilfe.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.btn_Hilfe.Location = New System.Drawing.Point(360, 80)
        Me.btn_Hilfe.Name = "btn_Hilfe"
        Me.btn_Hilfe.Size = New System.Drawing.Size(80, 24)
        Me.btn_Hilfe.TabIndex = 2
        Me.btn_Hilfe.Text = "Hilfe"
        '
        'Form1
        '
        Me.AcceptButton = Me.btn_OK
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(448, 213)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.btn_Hilfe, Me.btn_OK, Me.lbl_Beschreibung, Me.lbl_Beschreibung2, Me.lst_Verlauf, Me.cmb_Datei})
        Me.MaximizeBox = False
        Me.Name = "Form1"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Andis Starter"
        Me.ResumeLayout(False)

    End Sub

#End Region

    Dim Geladen As New Collection()

    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub

    Private Sub btn_OK_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_OK.Click
        Dim Datei As Short

        Try
            System.Diagnostics.Process.Start(cmb_Datei.Text)
            GoTo weiter2

nochmal:    Datei += 1
            If lst_Verlauf.Items.Count = Datei Then GoTo normal

weiter2:
            If lst_Verlauf.Items.Count < 1 Then GoTo normal
            If lst_Verlauf.Items.Item(Datei) = cmb_Datei.Text Then GoTo weiter _
            Else GoTo nochmal

normal:
            cmb_Datei.Items.Add(cmb_Datei.Text)
            lst_Verlauf.Items.Add(cmb_Datei.Text)

weiter:
        Catch
            MsgBox("Der angegebene Befehl bzw. die angegebene Pfadangabe existiert nicht. Bitte füllen sie das Feld korrekt aus.")
        End Try


    End Sub

    Private Sub lst_Verlauf_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles lst_Verlauf.SelectedIndexChanged

    End Sub

    Private Sub btn_Hilfe_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Hilfe.Click
        MsgBox("chkdsk" & vbTab & vbTab & "- Ruft Windows Scan-Disk auf" & vbCrLf & "dxdiag" & vbTab & vbTab & _
        "- Ruft das DirectX-Diagnoseprogramm auf" & vbCrLf & "winword.exe" & vbTab & "- Ruft Microsoft Word auf" & vbCrLf _
        & vbCrLf & "Sie können auch die entsprechenden Pfade zu Dateien oder Webseiten eingeben.", MsgBoxStyle.Information, "Standardbefehle")
    End Sub

    Private Sub lst_Verlauf_DoubleClick(ByVal sender As Object, ByVal e As System.EventArgs) Handles lst_Verlauf.DoubleClick
        Try
            System.Diagnostics.Process.Start(lst_Verlauf.SelectedItem)
        Catch
        End Try
    End Sub
End Class
