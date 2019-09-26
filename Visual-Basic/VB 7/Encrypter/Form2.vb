Public Class Form2
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
    Friend WithEvents check_Zusatz As System.Windows.Forms.CheckBox
    Friend WithEvents group_Codierung As System.Windows.Forms.GroupBox
    Friend WithEvents rd_ASCII As System.Windows.Forms.RadioButton
    Friend WithEvents rd_Xor As System.Windows.Forms.RadioButton
    Friend WithEvents btn_CodeIndex As System.Windows.Forms.Button
    Friend WithEvents lbl_CodeIndex As System.Windows.Forms.Label
    Friend WithEvents btn_OK As System.Windows.Forms.Button
    Friend WithEvents btn_Abbruch As System.Windows.Forms.Button
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.btn_CodeIndex = New System.Windows.Forms.Button()
        Me.lbl_CodeIndex = New System.Windows.Forms.Label()
        Me.check_Zusatz = New System.Windows.Forms.CheckBox()
        Me.group_Codierung = New System.Windows.Forms.GroupBox()
        Me.rd_ASCII = New System.Windows.Forms.RadioButton()
        Me.rd_Xor = New System.Windows.Forms.RadioButton()
        Me.btn_OK = New System.Windows.Forms.Button()
        Me.btn_Abbruch = New System.Windows.Forms.Button()
        Me.group_Codierung.SuspendLayout()
        Me.SuspendLayout()
        '
        'btn_CodeIndex
        '
        Me.btn_CodeIndex.Location = New System.Drawing.Point(32, 200)
        Me.btn_CodeIndex.Name = "btn_CodeIndex"
        Me.btn_CodeIndex.Size = New System.Drawing.Size(184, 40)
        Me.btn_CodeIndex.TabIndex = 1
        Me.btn_CodeIndex.Text = "Codeindex für die aktuelle Datei festlegen"
        '
        'lbl_CodeIndex
        '
        Me.lbl_CodeIndex.Location = New System.Drawing.Point(248, 200)
        Me.lbl_CodeIndex.Name = "lbl_CodeIndex"
        Me.lbl_CodeIndex.Size = New System.Drawing.Size(128, 72)
        Me.lbl_CodeIndex.TabIndex = 2
        Me.lbl_CodeIndex.Text = "Momentaner Codeindex:"
        '
        'check_Zusatz
        '
        Me.check_Zusatz.Location = New System.Drawing.Point(16, 88)
        Me.check_Zusatz.Name = "check_Zusatz"
        Me.check_Zusatz.Size = New System.Drawing.Size(248, 24)
        Me.check_Zusatz.TabIndex = 3
        Me.check_Zusatz.Text = "Aktiviere zusätzliche ASCII-Sicherung."
        '
        'group_Codierung
        '
        Me.group_Codierung.Controls.AddRange(New System.Windows.Forms.Control() {Me.rd_ASCII, Me.rd_Xor, Me.check_Zusatz})
        Me.group_Codierung.Location = New System.Drawing.Point(24, 24)
        Me.group_Codierung.Name = "group_Codierung"
        Me.group_Codierung.Size = New System.Drawing.Size(296, 136)
        Me.group_Codierung.TabIndex = 4
        Me.group_Codierung.TabStop = False
        Me.group_Codierung.Text = "Codierungsart"
        '
        'rd_ASCII
        '
        Me.rd_ASCII.Location = New System.Drawing.Point(16, 48)
        Me.rd_ASCII.Name = "rd_ASCII"
        Me.rd_ASCII.Size = New System.Drawing.Size(248, 16)
        Me.rd_ASCII.TabIndex = 1
        Me.rd_ASCII.Text = "ASCII-Verschlüsselung"
        '
        'rd_Xor
        '
        Me.rd_Xor.Checked = True
        Me.rd_Xor.Location = New System.Drawing.Point(16, 24)
        Me.rd_Xor.Name = "rd_Xor"
        Me.rd_Xor.Size = New System.Drawing.Size(248, 16)
        Me.rd_Xor.TabIndex = 0
        Me.rd_Xor.TabStop = True
        Me.rd_Xor.Text = "Xor-Verschlüsselung (Empfohlen)"
        '
        'btn_OK
        '
        Me.btn_OK.Location = New System.Drawing.Point(224, 352)
        Me.btn_OK.Name = "btn_OK"
        Me.btn_OK.Size = New System.Drawing.Size(80, 32)
        Me.btn_OK.TabIndex = 6
        Me.btn_OK.Text = "OK"
        '
        'btn_Abbruch
        '
        Me.btn_Abbruch.DialogResult = System.Windows.Forms.DialogResult.Cancel
        Me.btn_Abbruch.Location = New System.Drawing.Point(312, 352)
        Me.btn_Abbruch.Name = "btn_Abbruch"
        Me.btn_Abbruch.Size = New System.Drawing.Size(80, 32)
        Me.btn_Abbruch.TabIndex = 7
        Me.btn_Abbruch.Text = "Abbrechen"
        '
        'Form2
        '
        Me.AcceptButton = Me.btn_OK
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.CancelButton = Me.btn_Abbruch
        Me.ClientSize = New System.Drawing.Size(400, 393)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.btn_Abbruch, Me.btn_OK, Me.group_Codierung, Me.lbl_CodeIndex, Me.btn_CodeIndex})
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog
        Me.MaximizeBox = False
        Me.MaximumSize = New System.Drawing.Size(408, 420)
        Me.MinimizeBox = False
        Me.MinimumSize = New System.Drawing.Size(408, 420)
        Me.Name = "Form2"
        Me.ShowInTaskbar = False
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Andis Encrypter Verschlüsselungsoptionen"
        Me.group_Codierung.ResumeLayout(False)
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub Label1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles lbl_CodeIndex.Click

    End Sub

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_OK.Click
        varCodeIndex = varCodeIndexTemp
        varXorActivate = varXorActivateTemp
        varZusatz = varZusatzTemp
        Me.Hide()
    End Sub

    Private Sub Form2_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        lbl_CodeIndex.Text = "Momentaner Codeindex:" & vbCrLf & vbCrLf & varCodeIndex
        If varXorActivate = True Then rd_Xor.Checked = True Else rd_ASCII.Checked = True
        check_Zusatz.Checked = varZusatz

    End Sub

    Private Sub rd_Xor_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles rd_Xor.CheckedChanged
        check_Zusatz.Enabled = True
        varXorActivateTemp = True
    End Sub

    Private Sub btn_Abbruch_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Abbruch.Click
        Me.Hide()
    End Sub

    Private Sub rd_ASCII_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles rd_ASCII.CheckedChanged
        check_Zusatz.Enabled = False
        check_Zusatz.Checked = False
        varZusatzTemp = False
        varXorActivateTemp = False

    End Sub

    Private Sub btn_CodeIndex_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_CodeIndex.Click
        Try
            varCodeIndexTemp = InputBox("Geben sie eine Zahl für den Codeindex ein.", "Eingabe")
            varCodeIndexTemp = CInt(varCodeIndexTemp)
        Catch
            MsgBox("Geben sie gültige eine Zahl ein.", MsgBoxStyle.Critical, "Fehler")
        Finally
            lbl_CodeIndex.Text = "Momentaner Codeindex:" & vbCrLf & vbCrLf & varCodeIndexTemp
        End Try

    End Sub

    Private Sub check_Zusatz_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles check_Zusatz.CheckedChanged
        If check_Zusatz.Checked = True Then varZusatzTemp = True Else varZusatzTemp = False
    End Sub
End Class
