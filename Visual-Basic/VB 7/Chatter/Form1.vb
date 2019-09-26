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
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents txt_Name As System.Windows.Forms.TextBox
    Friend WithEvents txt_Message As System.Windows.Forms.TextBox
    Friend WithEvents btn_Go As System.Windows.Forms.Button
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.txt_Name = New System.Windows.Forms.TextBox()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.txt_Message = New System.Windows.Forms.TextBox()
        Me.btn_Go = New System.Windows.Forms.Button()
        Me.SuspendLayout()
        '
        'txt_Name
        '
        Me.txt_Name.Location = New System.Drawing.Point(8, 32)
        Me.txt_Name.Name = "txt_Name"
        Me.txt_Name.Size = New System.Drawing.Size(184, 20)
        Me.txt_Name.TabIndex = 0
        Me.txt_Name.Text = ""
        '
        'Label1
        '
        Me.Label1.Location = New System.Drawing.Point(8, 8)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(240, 16)
        Me.Label1.TabIndex = 1
        Me.Label1.Text = "Name des PCs, mit dem sie chatten möchten:"
        '
        'Label2
        '
        Me.Label2.Location = New System.Drawing.Point(8, 64)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(240, 16)
        Me.Label2.TabIndex = 2
        Me.Label2.Text = "Nachricht:"
        '
        'txt_Message
        '
        Me.txt_Message.Location = New System.Drawing.Point(8, 88)
        Me.txt_Message.Name = "txt_Message"
        Me.txt_Message.Size = New System.Drawing.Size(296, 20)
        Me.txt_Message.TabIndex = 1
        Me.txt_Message.Text = ""
        '
        'btn_Go
        '
        Me.btn_Go.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn_Go.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.btn_Go.ForeColor = System.Drawing.SystemColors.ControlText
        Me.btn_Go.Location = New System.Drawing.Point(224, 32)
        Me.btn_Go.Name = "btn_Go"
        Me.btn_Go.Size = New System.Drawing.Size(80, 24)
        Me.btn_Go.TabIndex = 2
        Me.btn_Go.Text = "Abschicken"
        '
        'Form1
        '
        Me.AcceptButton = Me.btn_Go
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(320, 117)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.btn_Go, Me.txt_Message, Me.Label2, Me.Label1, Me.txt_Name})
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog
        Me.MaximizeBox = False
        Me.Name = "Form1"
        Me.Text = "Andis Chatter"
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub TextBox1_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txt_Name.TextChanged

    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Go.Click
        Try
            System.Diagnostics.Process.Start("net send " & txt_Name.Text & " " & txt_Message.Text)
        Catch
            MsgBox("Die Nachricht konnte nicht gesendet werden. Überprüfen Sie, ob der Computername richtig geschrieben ist.", MsgBoxStyle.Critical, "Fehler beim Versenden")
        End Try
    End Sub

    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub
End Class
