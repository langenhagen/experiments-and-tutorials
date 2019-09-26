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
    Friend WithEvents btn_Restart As System.Windows.Forms.Button
    Friend WithEvents btn_Shutdown As System.Windows.Forms.Button
    Friend WithEvents btn_Standby As System.Windows.Forms.Button
    Friend WithEvents btn_ShutUp As System.Windows.Forms.Button
    Friend WithEvents btn_LogOff As System.Windows.Forms.Button
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.btn_Restart = New System.Windows.Forms.Button()
        Me.btn_Shutdown = New System.Windows.Forms.Button()
        Me.btn_Standby = New System.Windows.Forms.Button()
        Me.btn_ShutUp = New System.Windows.Forms.Button()
        Me.btn_LogOff = New System.Windows.Forms.Button()
        Me.SuspendLayout()
        '
        'btn_Restart
        '
        Me.btn_Restart.Location = New System.Drawing.Point(24, 56)
        Me.btn_Restart.Name = "btn_Restart"
        Me.btn_Restart.Size = New System.Drawing.Size(80, 32)
        Me.btn_Restart.TabIndex = 0
        Me.btn_Restart.Text = "Reboot"
        '
        'btn_Shutdown
        '
        Me.btn_Shutdown.Location = New System.Drawing.Point(24, 16)
        Me.btn_Shutdown.Name = "btn_Shutdown"
        Me.btn_Shutdown.Size = New System.Drawing.Size(80, 32)
        Me.btn_Shutdown.TabIndex = 1
        Me.btn_Shutdown.Text = "Shutdown"
        '
        'btn_Standby
        '
        Me.btn_Standby.Location = New System.Drawing.Point(24, 96)
        Me.btn_Standby.Name = "btn_Standby"
        Me.btn_Standby.Size = New System.Drawing.Size(80, 32)
        Me.btn_Standby.TabIndex = 2
        Me.btn_Standby.Text = "Standby"
        '
        'btn_ShutUp
        '
        Me.btn_ShutUp.Location = New System.Drawing.Point(120, 16)
        Me.btn_ShutUp.Name = "btn_ShutUp"
        Me.btn_ShutUp.Size = New System.Drawing.Size(80, 32)
        Me.btn_ShutUp.TabIndex = 3
        Me.btn_ShutUp.Text = "Abschalten O,ô"
        '
        'btn_LogOff
        '
        Me.btn_LogOff.Location = New System.Drawing.Point(120, 56)
        Me.btn_LogOff.Name = "btn_LogOff"
        Me.btn_LogOff.Size = New System.Drawing.Size(80, 32)
        Me.btn_LogOff.TabIndex = 4
        Me.btn_LogOff.Text = "LogOff"
        '
        'Form1
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(400, 387)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.btn_LogOff, Me.btn_ShutUp, Me.btn_Standby, Me.btn_Shutdown, Me.btn_Restart})
        Me.Name = "Form1"
        Me.Text = "Form1"
        Me.ResumeLayout(False)

    End Sub

#End Region


    Private Sub btn_Restart_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Restart.Click
        Dim System, Eigenschaft As Object
        System = GetObject( _
           "winmgmts:{impersonationLevel=impersonate,(Shutdown)}" & _
           "//./root/cimv2").ExecQuery( _
           "SELECT * FROM Win32_OperatingSystem")

        For Each Eigenschaft In System
            Eigenschaft.Reboot()
        Next
    End Sub

    Private Sub btn_Shutdown_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Shutdown.Click
        Dim System, Eigenschaft As Object
        System = GetObject( _
           "winmgmts:{impersonationLevel=impersonate,(Shutdown)}" & _
           "//./root/cimv2").ExecQuery( _
           "SELECT * FROM Win32_OperatingSystem")

        For Each Eigenschaft In System
            Eigenschaft.Shutdown()
        Next
    End Sub

    Private Sub btn_ShutUp_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_ShutUp.Click
        Dim System, Eigenschaft As Object
        System = GetObject( _
           "winmgmts:{impersonationLevel=impersonate,(Shutdown)}" & _
           "//./root/cimv2").ExecQuery( _
           "SELECT * FROM Win32_OperatingSystem")

        For Each Eigenschaft In System
            Eigenschaft.Win32Shutdown(12) '(eigentlich 8 + irgendwie wat erzwingen --> +4
        Next
    End Sub

    Private Sub btn_LogOff_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_LogOff.Click
        Dim System, Eigenschaft As Object
        System = GetObject( _
           "winmgmts:{impersonationLevel=impersonate,(Shutdown)}" & _
           "//./root/cimv2").ExecQuery( _
           "SELECT * FROM Win32_OperatingSystem")

        For Each Eigenschaft In System
            Eigenschaft.Win32Shutdown(4) '(eigentlich 0 + irgendwie wat erzwingen --> +4
        Next
    End Sub

    Private Sub btn_Standby_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Standby.Click

    End Sub
End Class
