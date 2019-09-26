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
    Friend WithEvents SaveFileDialog1 As System.Windows.Forms.SaveFileDialog
    Friend WithEvents OpenFileDialog1 As System.Windows.Forms.OpenFileDialog
    Friend WithEvents MainMenu1 As System.Windows.Forms.MainMenu
    Friend WithEvents mnu_Datei As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Bearbeiten As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Öffnen As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Schließen As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Speichern As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Ende As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Datum As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Optionen As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Uhrzeit As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Help As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Hilfe As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Strich4 As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Info As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Strich2 As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Strich1 As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Strich3 As System.Windows.Forms.MenuItem
    Friend WithEvents txt_Text As System.Windows.Forms.TextBox
    Friend WithEvents status_Status As System.Windows.Forms.StatusBar
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.SaveFileDialog1 = New System.Windows.Forms.SaveFileDialog()
        Me.OpenFileDialog1 = New System.Windows.Forms.OpenFileDialog()
        Me.MainMenu1 = New System.Windows.Forms.MainMenu()
        Me.mnu_Datei = New System.Windows.Forms.MenuItem()
        Me.mnu_Öffnen = New System.Windows.Forms.MenuItem()
        Me.mnu_Schließen = New System.Windows.Forms.MenuItem()
        Me.mnu_Strich1 = New System.Windows.Forms.MenuItem()
        Me.mnu_Speichern = New System.Windows.Forms.MenuItem()
        Me.mnu_Strich2 = New System.Windows.Forms.MenuItem()
        Me.mnu_Ende = New System.Windows.Forms.MenuItem()
        Me.mnu_Bearbeiten = New System.Windows.Forms.MenuItem()
        Me.mnu_Datum = New System.Windows.Forms.MenuItem()
        Me.mnu_Uhrzeit = New System.Windows.Forms.MenuItem()
        Me.mnu_Strich3 = New System.Windows.Forms.MenuItem()
        Me.mnu_Optionen = New System.Windows.Forms.MenuItem()
        Me.mnu_Help = New System.Windows.Forms.MenuItem()
        Me.mnu_Hilfe = New System.Windows.Forms.MenuItem()
        Me.mnu_Strich4 = New System.Windows.Forms.MenuItem()
        Me.mnu_Info = New System.Windows.Forms.MenuItem()
        Me.txt_Text = New System.Windows.Forms.TextBox()
        Me.status_Status = New System.Windows.Forms.StatusBar()
        Me.SuspendLayout()
        '
        'SaveFileDialog1
        '
        Me.SaveFileDialog1.FileName = "doc1"
        '
        'MainMenu1
        '
        Me.MainMenu1.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnu_Datei, Me.mnu_Bearbeiten, Me.mnu_Help})
        '
        'mnu_Datei
        '
        Me.mnu_Datei.Index = 0
        Me.mnu_Datei.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnu_Öffnen, Me.mnu_Schließen, Me.mnu_Strich1, Me.mnu_Speichern, Me.mnu_Strich2, Me.mnu_Ende})
        Me.mnu_Datei.Text = "&Datei"
        '
        'mnu_Öffnen
        '
        Me.mnu_Öffnen.Index = 0
        Me.mnu_Öffnen.Text = "Datei ö&ffnen..."
        '
        'mnu_Schließen
        '
        Me.mnu_Schließen.Enabled = False
        Me.mnu_Schließen.Index = 1
        Me.mnu_Schließen.Text = "&Schließen"
        '
        'mnu_Strich1
        '
        Me.mnu_Strich1.Index = 2
        Me.mnu_Strich1.Text = "-"
        '
        'mnu_Speichern
        '
        Me.mnu_Speichern.Index = 3
        Me.mnu_Speichern.Text = "Datei Speichern &unter..."
        '
        'mnu_Strich2
        '
        Me.mnu_Strich2.Index = 4
        Me.mnu_Strich2.Text = "-"
        '
        'mnu_Ende
        '
        Me.mnu_Ende.Index = 5
        Me.mnu_Ende.Text = "B&eenden"
        '
        'mnu_Bearbeiten
        '
        Me.mnu_Bearbeiten.Index = 1
        Me.mnu_Bearbeiten.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnu_Datum, Me.mnu_Uhrzeit, Me.mnu_Strich3, Me.mnu_Optionen})
        Me.mnu_Bearbeiten.Text = "&Bearbeiten"
        '
        'mnu_Datum
        '
        Me.mnu_Datum.Index = 0
        Me.mnu_Datum.Text = "&Datum einfügen"
        '
        'mnu_Uhrzeit
        '
        Me.mnu_Uhrzeit.Index = 1
        Me.mnu_Uhrzeit.Text = "Datum und &Uhrzeit einfügen"
        '
        'mnu_Strich3
        '
        Me.mnu_Strich3.Index = 2
        Me.mnu_Strich3.Text = "-"
        '
        'mnu_Optionen
        '
        Me.mnu_Optionen.Index = 3
        Me.mnu_Optionen.Text = "Verschlüsslungs&optionen..."
        '
        'mnu_Help
        '
        Me.mnu_Help.Index = 2
        Me.mnu_Help.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnu_Hilfe, Me.mnu_Strich4, Me.mnu_Info})
        Me.mnu_Help.Text = "?"
        '
        'mnu_Hilfe
        '
        Me.mnu_Hilfe.Index = 0
        Me.mnu_Hilfe.Text = "&Hilfe"
        '
        'mnu_Strich4
        '
        Me.mnu_Strich4.Index = 1
        Me.mnu_Strich4.Text = "-"
        '
        'mnu_Info
        '
        Me.mnu_Info.Index = 2
        Me.mnu_Info.Text = "&Info"
        '
        'txt_Text
        '
        Me.txt_Text.AcceptsReturn = True
        Me.txt_Text.AcceptsTab = True
        Me.txt_Text.Location = New System.Drawing.Point(16, 16)
        Me.txt_Text.Multiline = True
        Me.txt_Text.Name = "txt_Text"
        Me.txt_Text.ScrollBars = System.Windows.Forms.ScrollBars.Both
        Me.txt_Text.Size = New System.Drawing.Size(368, 264)
        Me.txt_Text.TabIndex = 0
        Me.txt_Text.Text = ""
        '
        'status_Status
        '
        Me.status_Status.Location = New System.Drawing.Point(0, 349)
        Me.status_Status.Name = "status_Status"
        Me.status_Status.Size = New System.Drawing.Size(400, 24)
        Me.status_Status.TabIndex = 1
        '
        'Form1
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(400, 373)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.status_Status, Me.txt_Text})
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.MaximizeBox = False
        Me.MaximumSize = New System.Drawing.Size(408, 420)
        Me.Menu = Me.MainMenu1
        Me.MinimumSize = New System.Drawing.Size(408, 420)
        Me.Name = "Form1"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Andis Encrypter"
        Me.ResumeLayout(False)

    End Sub

#End Region

    Dim CodeIndex As Integer

    Private Sub mnu_Ende_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnu_Ende.Click
        End
    End Sub

    Private Sub mnu_Info_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnu_Info.Click
        MsgBox("© 2003 Andreas Langenhagen" & vbCrLf & "Version 1.0", MsgBoxStyle.Information, "Info")
    End Sub

    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        varXorActivate = True
    End Sub

    Private Sub StatusBar1_PanelClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.StatusBarPanelClickEventArgs)

    End Sub

    Private Sub mnu_Datum_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnu_Datum.Click
        txt_Text.Text = DateString & vbCrLf & txt_Text.Text
    End Sub

    Private Sub mnu_Uhrzeit_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnu_Uhrzeit.Click
        txt_Text.Text = DateString & " " & TimeString & vbCrLf & txt_Text.Text
    End Sub

    Private Sub mnu_Schließen_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnu_Schließen.Click
        txt_Text.Text = ""
        mnu_Öffnen.Enabled = True
        mnu_Schließen.Enabled = False
    End Sub

    Private Sub mnu_Öffnen_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnu_Öffnen.Click

        If varZusatz = True Then

            Dim AllText, LineOfText As String
            Dim i, charsInFile As Short
            Dim letter As Char
            Dim Decrypt, Eingabe As String
            Dim Code As Integer

            OpenFileDialog1.Filter = "Encrypter-Dateien (*.ECR)|*.ECR|Textdateien (*.TXT)| *.TXT"
            OpenFileDialog1.ShowDialog()
            If OpenFileDialog1.FileName <> "" Then
                Try
                    Eingabe = InputBox("Geben Sie den Codeindex ein.", "Codeindex eingeben")
                    If Eingabe = "" Then Exit Sub
                    Code = CInt(Eingabe)
                    FileOpen(1, OpenFileDialog1.FileName, OpenMode.Input)
                    Do Until EOF(1)
                        LineOfText = LineInput(1)
                        AllText = AllText & LineOfText & vbCrLf
                    Loop

                    Dim test As Integer

                    charsInFile = AllText.Length
                    For i = 0 To charsInFile - 1
                        letter = AllText.Substring(i, 1)
                        Decrypt = Decrypt & Chr(Asc(letter) - Code)

                        test = test + 1

                    Next i
                    txt_Text.Text = Decrypt
                    txt_Text.Select(1, 0)
                    mnu_Schließen.Enabled = True
                    mnu_Öffnen.Enabled = False




            End If
        End If


        If varXorActivate = True Then
            Dim Code, Number As Integer
            Dim Zeichen As Char
            Dim Decrypt, Eingabe As String
            OpenFileDialog1.Filter = "Encrypter-Dateien (*.ECR)|*ECR| Textdateien (*.TXT)|*.TXT"
            OpenFileDialog1.ShowDialog()
            Try
                Eingabe = InputBox("Geben Sie den Codeindex ein.", "Codeindex eingeben")
                If Eingabe = "" Then Exit Sub
                Code = CInt(Eingabe)
                FileOpen(1, OpenFileDialog1.FileName, OpenMode.Input)
                Do Until EOF(1)
                    Input(1, Number)
                    Zeichen = Chr(Number Xor Code)
                    Decrypt = Decrypt + Zeichen
                Loop
                txt_Text.Text = Decrypt
                txt_Text.Select(1, 0)
                mnu_Schließen.Enabled = True
                mnu_Öffnen.Enabled = False
            Catch When Err.Number = 6
                MsgBox("Die Datei ist zu groß, um geöffnet zu werden.")
            Catch
                MsgBox("Falscher Codeindex", MsgBoxStyle.Critical, "Fehler beim Öffnen der Datei.")
            Finally
                FileClose(1)
            End Try
        Else
            Dim AllText, LineOfText As String
            Dim i, charsInFile As Short
            Dim letter As Char
            Dim Decrypt, Eingabe As String
            Dim Code As Integer

            OpenFileDialog1.Filter = "Encrypter-Dateien (*.ECR)|*.ECR|Textdateien (*.TXT)| *.TXT"
            OpenFileDialog1.ShowDialog()
            If OpenFileDialog1.FileName <> "" Then
                Try
                    Eingabe = InputBox("Geben Sie den Codeindex ein.", "Codeindex eingeben")
                    If Eingabe = "" Then Exit Sub
                    Code = CInt(Eingabe)
                    FileOpen(1, OpenFileDialog1.FileName, OpenMode.Input)
                    Do Until EOF(1)
                        LineOfText = LineInput(1)
                        AllText = AllText & LineOfText & vbCrLf
                    Loop

                    Dim test As Integer

                    charsInFile = AllText.Length
                    For i = 0 To charsInFile - 1
                        letter = AllText.Substring(i, 1)
                        Decrypt = Decrypt & Chr(Asc(letter) - Code)

                        test = test + 1

                    Next i
                    txt_Text.Text = Decrypt
                    txt_Text.Select(1, 0)
                    mnu_Schließen.Enabled = True
                    mnu_Öffnen.Enabled = False
                Catch When Err.Number = 6
                    MsgBox("Die Datei ist zu groß, um geöffnet zu werden.")
                Catch
                    MsgBox("Falscher Codeindex", MsgBoxStyle.Critical, "Fehler beim Öffnen der Datei.")
                Finally
                    FileClose(1)
                End Try
            End If
        End If



    End Sub

    Private Sub mnu_Optionen_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnu_Optionen.Click
        Dim Start As New Form2()
        Start.Show()
    End Sub
End Class
