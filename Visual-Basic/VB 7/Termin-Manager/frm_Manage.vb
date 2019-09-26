Public Class frm_Manage
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
    Friend WithEvents tab_Neu As System.Windows.Forms.TabControl
    Friend WithEvents utab_Termin As System.Windows.Forms.TabPage
    Friend WithEvents grp_Betreff As System.Windows.Forms.GroupBox
    Friend WithEvents txt_User As System.Windows.Forms.TextBox
    Friend WithEvents txt_Betreff As System.Windows.Forms.TextBox
    Friend WithEvents lbl_User As System.Windows.Forms.Label
    Friend WithEvents lbl_Betreff As System.Windows.Forms.Label
    Friend WithEvents grp_Datum As System.Windows.Forms.GroupBox
    Friend WithEvents lbl_Repeat2 As System.Windows.Forms.Label
    Friend WithEvents num_Days As System.Windows.Forms.NumericUpDown
    Friend WithEvents lbl_Repeat1 As System.Windows.Forms.Label
    Friend WithEvents check_Repeat As System.Windows.Forms.CheckBox
    Friend WithEvents dtp_Time As System.Windows.Forms.DateTimePicker
    Friend WithEvents dtp_Datum As System.Windows.Forms.DateTimePicker
    Friend WithEvents check_Uhrzeit As System.Windows.Forms.CheckBox
    Friend WithEvents lbl_Datum As System.Windows.Forms.Label
    Friend WithEvents lbl_Uhrzeit As System.Windows.Forms.Label
    Friend WithEvents utab_Text As System.Windows.Forms.TabPage
    Friend WithEvents txt_Text As System.Windows.Forms.TextBox
    Friend WithEvents lbl_Text As System.Windows.Forms.Label
    Friend WithEvents btn_Cancel As System.Windows.Forms.Button
    Friend WithEvents btn_Ok As System.Windows.Forms.Button
    Friend WithEvents btn_Give As System.Windows.Forms.Button
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.tab_Neu = New System.Windows.Forms.TabControl()
        Me.utab_Termin = New System.Windows.Forms.TabPage()
        Me.grp_Betreff = New System.Windows.Forms.GroupBox()
        Me.txt_User = New System.Windows.Forms.TextBox()
        Me.txt_Betreff = New System.Windows.Forms.TextBox()
        Me.lbl_User = New System.Windows.Forms.Label()
        Me.lbl_Betreff = New System.Windows.Forms.Label()
        Me.grp_Datum = New System.Windows.Forms.GroupBox()
        Me.lbl_Repeat2 = New System.Windows.Forms.Label()
        Me.num_Days = New System.Windows.Forms.NumericUpDown()
        Me.lbl_Repeat1 = New System.Windows.Forms.Label()
        Me.check_Repeat = New System.Windows.Forms.CheckBox()
        Me.dtp_Time = New System.Windows.Forms.DateTimePicker()
        Me.dtp_Datum = New System.Windows.Forms.DateTimePicker()
        Me.check_Uhrzeit = New System.Windows.Forms.CheckBox()
        Me.lbl_Datum = New System.Windows.Forms.Label()
        Me.lbl_Uhrzeit = New System.Windows.Forms.Label()
        Me.utab_Text = New System.Windows.Forms.TabPage()
        Me.txt_Text = New System.Windows.Forms.TextBox()
        Me.lbl_Text = New System.Windows.Forms.Label()
        Me.btn_Cancel = New System.Windows.Forms.Button()
        Me.btn_Ok = New System.Windows.Forms.Button()
        Me.btn_Give = New System.Windows.Forms.Button()
        Me.tab_Neu.SuspendLayout()
        Me.utab_Termin.SuspendLayout()
        Me.grp_Betreff.SuspendLayout()
        Me.grp_Datum.SuspendLayout()
        CType(Me.num_Days, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.utab_Text.SuspendLayout()
        Me.SuspendLayout()
        '
        'tab_Neu
        '
        Me.tab_Neu.Controls.AddRange(New System.Windows.Forms.Control() {Me.utab_Termin, Me.utab_Text})
        Me.tab_Neu.Location = New System.Drawing.Point(8, 8)
        Me.tab_Neu.Name = "tab_Neu"
        Me.tab_Neu.SelectedIndex = 0
        Me.tab_Neu.Size = New System.Drawing.Size(392, 272)
        Me.tab_Neu.TabIndex = 3
        '
        'utab_Termin
        '
        Me.utab_Termin.Controls.AddRange(New System.Windows.Forms.Control() {Me.grp_Betreff, Me.grp_Datum})
        Me.utab_Termin.Location = New System.Drawing.Point(4, 22)
        Me.utab_Termin.Name = "utab_Termin"
        Me.utab_Termin.Size = New System.Drawing.Size(384, 246)
        Me.utab_Termin.TabIndex = 0
        Me.utab_Termin.Text = "Termin festlegen"
        '
        'grp_Betreff
        '
        Me.grp_Betreff.Controls.AddRange(New System.Windows.Forms.Control() {Me.txt_User, Me.txt_Betreff, Me.lbl_User, Me.lbl_Betreff})
        Me.grp_Betreff.Location = New System.Drawing.Point(8, 168)
        Me.grp_Betreff.Name = "grp_Betreff"
        Me.grp_Betreff.Size = New System.Drawing.Size(368, 72)
        Me.grp_Betreff.TabIndex = 7
        Me.grp_Betreff.TabStop = False
        Me.grp_Betreff.Text = "Betreff und Empfänger"
        '
        'txt_User
        '
        Me.txt_User.Location = New System.Drawing.Point(72, 40)
        Me.txt_User.MaxLength = 50
        Me.txt_User.Name = "txt_User"
        Me.txt_User.Size = New System.Drawing.Size(216, 20)
        Me.txt_User.TabIndex = 9
        Me.txt_User.Text = ""
        '
        'txt_Betreff
        '
        Me.txt_Betreff.Location = New System.Drawing.Point(72, 16)
        Me.txt_Betreff.MaxLength = 50
        Me.txt_Betreff.Name = "txt_Betreff"
        Me.txt_Betreff.Size = New System.Drawing.Size(216, 20)
        Me.txt_Betreff.TabIndex = 8
        Me.txt_Betreff.Text = ""
        '
        'lbl_User
        '
        Me.lbl_User.Location = New System.Drawing.Point(8, 40)
        Me.lbl_User.Name = "lbl_User"
        Me.lbl_User.Size = New System.Drawing.Size(64, 16)
        Me.lbl_User.TabIndex = 1
        Me.lbl_User.Text = "Empfänger:"
        Me.lbl_User.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        '
        'lbl_Betreff
        '
        Me.lbl_Betreff.Location = New System.Drawing.Point(8, 24)
        Me.lbl_Betreff.Name = "lbl_Betreff"
        Me.lbl_Betreff.Size = New System.Drawing.Size(64, 16)
        Me.lbl_Betreff.TabIndex = 0
        Me.lbl_Betreff.Text = "Betreff:"
        Me.lbl_Betreff.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        '
        'grp_Datum
        '
        Me.grp_Datum.Controls.AddRange(New System.Windows.Forms.Control() {Me.lbl_Repeat2, Me.num_Days, Me.lbl_Repeat1, Me.check_Repeat, Me.dtp_Time, Me.dtp_Datum, Me.check_Uhrzeit, Me.lbl_Datum, Me.lbl_Uhrzeit})
        Me.grp_Datum.Location = New System.Drawing.Point(8, 8)
        Me.grp_Datum.Name = "grp_Datum"
        Me.grp_Datum.Size = New System.Drawing.Size(368, 152)
        Me.grp_Datum.TabIndex = 6
        Me.grp_Datum.TabStop = False
        Me.grp_Datum.Text = "Datum und Uhrzeit"
        '
        'lbl_Repeat2
        '
        Me.lbl_Repeat2.Location = New System.Drawing.Point(152, 120)
        Me.lbl_Repeat2.Name = "lbl_Repeat2"
        Me.lbl_Repeat2.Size = New System.Drawing.Size(64, 16)
        Me.lbl_Repeat2.TabIndex = 11
        Me.lbl_Repeat2.Text = "Tagen"
        Me.lbl_Repeat2.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
        '
        'num_Days
        '
        Me.num_Days.Enabled = False
        Me.num_Days.Location = New System.Drawing.Point(104, 120)
        Me.num_Days.Maximum = New Decimal(New Integer() {356, 0, 0, 0})
        Me.num_Days.Minimum = New Decimal(New Integer() {1, 0, 0, 0})
        Me.num_Days.Name = "num_Days"
        Me.num_Days.Size = New System.Drawing.Size(48, 20)
        Me.num_Days.TabIndex = 7
        Me.num_Days.Value = New Decimal(New Integer() {356, 0, 0, 0})
        '
        'lbl_Repeat1
        '
        Me.lbl_Repeat1.Location = New System.Drawing.Point(16, 120)
        Me.lbl_Repeat1.Name = "lbl_Repeat1"
        Me.lbl_Repeat1.Size = New System.Drawing.Size(88, 16)
        Me.lbl_Repeat1.TabIndex = 9
        Me.lbl_Repeat1.Text = "Im Abstand von:"
        Me.lbl_Repeat1.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        '
        'check_Repeat
        '
        Me.check_Repeat.Location = New System.Drawing.Point(16, 96)
        Me.check_Repeat.Name = "check_Repeat"
        Me.check_Repeat.Size = New System.Drawing.Size(128, 16)
        Me.check_Repeat.TabIndex = 6
        Me.check_Repeat.Text = "Zyklisch wiederholen"
        '
        'dtp_Time
        '
        Me.dtp_Time.Enabled = False
        Me.dtp_Time.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.dtp_Time.Format = System.Windows.Forms.DateTimePickerFormat.Time
        Me.dtp_Time.Location = New System.Drawing.Point(112, 40)
        Me.dtp_Time.Name = "dtp_Time"
        Me.dtp_Time.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me.dtp_Time.ShowUpDown = True
        Me.dtp_Time.Size = New System.Drawing.Size(248, 20)
        Me.dtp_Time.TabIndex = 4
        Me.dtp_Time.Value = New Date(2003, 9, 10, 0, 0, 0, 0)
        '
        'dtp_Datum
        '
        Me.dtp_Datum.Location = New System.Drawing.Point(112, 16)
        Me.dtp_Datum.MinDate = New Date(2003, 1, 1, 0, 0, 0, 0)
        Me.dtp_Datum.Name = "dtp_Datum"
        Me.dtp_Datum.Size = New System.Drawing.Size(248, 20)
        Me.dtp_Datum.TabIndex = 3
        '
        'check_Uhrzeit
        '
        Me.check_Uhrzeit.Location = New System.Drawing.Point(16, 72)
        Me.check_Uhrzeit.Name = "check_Uhrzeit"
        Me.check_Uhrzeit.Size = New System.Drawing.Size(144, 16)
        Me.check_Uhrzeit.TabIndex = 5
        Me.check_Uhrzeit.Text = "Uhrzeit berücksichtigen"
        '
        'lbl_Datum
        '
        Me.lbl_Datum.Location = New System.Drawing.Point(0, 16)
        Me.lbl_Datum.Name = "lbl_Datum"
        Me.lbl_Datum.Size = New System.Drawing.Size(112, 23)
        Me.lbl_Datum.TabIndex = 4
        Me.lbl_Datum.Text = "Datum des Termines:"
        Me.lbl_Datum.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        '
        'lbl_Uhrzeit
        '
        Me.lbl_Uhrzeit.Location = New System.Drawing.Point(0, 40)
        Me.lbl_Uhrzeit.Name = "lbl_Uhrzeit"
        Me.lbl_Uhrzeit.Size = New System.Drawing.Size(112, 24)
        Me.lbl_Uhrzeit.TabIndex = 5
        Me.lbl_Uhrzeit.Text = "Uhrzeit:"
        Me.lbl_Uhrzeit.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        '
        'utab_Text
        '
        Me.utab_Text.Controls.AddRange(New System.Windows.Forms.Control() {Me.txt_Text, Me.lbl_Text})
        Me.utab_Text.Location = New System.Drawing.Point(4, 22)
        Me.utab_Text.Name = "utab_Text"
        Me.utab_Text.Size = New System.Drawing.Size(384, 246)
        Me.utab_Text.TabIndex = 1
        Me.utab_Text.Text = "Infotext"
        Me.utab_Text.Visible = False
        '
        'txt_Text
        '
        Me.txt_Text.Location = New System.Drawing.Point(8, 40)
        Me.txt_Text.Multiline = True
        Me.txt_Text.Name = "txt_Text"
        Me.txt_Text.ScrollBars = System.Windows.Forms.ScrollBars.Vertical
        Me.txt_Text.Size = New System.Drawing.Size(368, 200)
        Me.txt_Text.TabIndex = 2
        Me.txt_Text.Text = ""
        '
        'lbl_Text
        '
        Me.lbl_Text.Location = New System.Drawing.Point(8, 8)
        Me.lbl_Text.Name = "lbl_Text"
        Me.lbl_Text.Size = New System.Drawing.Size(368, 24)
        Me.lbl_Text.TabIndex = 0
        Me.lbl_Text.Text = "Tragen sie hier den Text ein, welcher bei der späteren Benachrichtigung stehen so" & _
        "ll."
        '
        'btn_Cancel
        '
        Me.btn_Cancel.DialogResult = System.Windows.Forms.DialogResult.Cancel
        Me.btn_Cancel.Location = New System.Drawing.Point(232, 288)
        Me.btn_Cancel.Name = "btn_Cancel"
        Me.btn_Cancel.Size = New System.Drawing.Size(80, 24)
        Me.btn_Cancel.TabIndex = 1
        Me.btn_Cancel.Text = "Abbrechen"
        '
        'btn_Ok
        '
        Me.btn_Ok.Location = New System.Drawing.Point(144, 288)
        Me.btn_Ok.Name = "btn_Ok"
        Me.btn_Ok.Size = New System.Drawing.Size(80, 24)
        Me.btn_Ok.TabIndex = 0
        Me.btn_Ok.Text = "OK"
        '
        'btn_Give
        '
        Me.btn_Give.Location = New System.Drawing.Point(320, 288)
        Me.btn_Give.Name = "btn_Give"
        Me.btn_Give.Size = New System.Drawing.Size(80, 24)
        Me.btn_Give.TabIndex = 2
        Me.btn_Give.Text = "Übernehmen"
        '
        'frm_Manage
        '
        Me.AcceptButton = Me.btn_Ok
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.CancelButton = Me.btn_Cancel
        Me.ClientSize = New System.Drawing.Size(402, 319)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.btn_Give, Me.btn_Cancel, Me.btn_Ok, Me.tab_Neu})
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frm_Manage"
        Me.Text = "Termin ändern"
        Me.tab_Neu.ResumeLayout(False)
        Me.utab_Termin.ResumeLayout(False)
        Me.grp_Betreff.ResumeLayout(False)
        Me.grp_Datum.ResumeLayout(False)
        CType(Me.num_Days, System.ComponentModel.ISupportInitialize).EndInit()
        Me.utab_Text.ResumeLayout(False)
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub check_Repeat_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles check_Repeat.CheckedChanged

    End Sub

    Private Sub tab_Neu_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles tab_Neu.SelectedIndexChanged

    End Sub

    Private Sub check_Uhrzeit_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles check_Uhrzeit.CheckedChanged
        If check_Uhrzeit.Checked = True Then
            dtp_Time.Enabled = True
        Else : dtp_Time.Enabled = True
        End If
    End Sub
End Class
