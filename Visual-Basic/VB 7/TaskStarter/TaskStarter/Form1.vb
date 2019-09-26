Public Class frm_Task_Starter
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
    Friend WithEvents btn_Start As System.Windows.Forms.Button
    Friend WithEvents NIcon As System.Windows.Forms.NotifyIcon
    Friend WithEvents CMnu As System.Windows.Forms.ContextMenu
    Friend WithEvents Mnu_Hide As System.Windows.Forms.MenuItem
    Friend WithEvents Mnu_End As System.Windows.Forms.MenuItem
    Friend WithEvents Scroll_Tasks As System.Windows.Forms.VScrollBar
    Friend WithEvents dmn_Path As System.Windows.Forms.DomainUpDown
    Friend WithEvents MenuItem2 As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Verlauf As System.Windows.Forms.MenuItem
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Dim resources As System.Resources.ResourceManager = New System.Resources.ResourceManager(GetType(frm_Task_Starter))
        Me.btn_Start = New System.Windows.Forms.Button()
        Me.NIcon = New System.Windows.Forms.NotifyIcon(Me.components)
        Me.CMnu = New System.Windows.Forms.ContextMenu()
        Me.Mnu_Hide = New System.Windows.Forms.MenuItem()
        Me.mnu_Verlauf = New System.Windows.Forms.MenuItem()
        Me.MenuItem2 = New System.Windows.Forms.MenuItem()
        Me.Mnu_End = New System.Windows.Forms.MenuItem()
        Me.Scroll_Tasks = New System.Windows.Forms.VScrollBar()
        Me.dmn_Path = New System.Windows.Forms.DomainUpDown()
        Me.SuspendLayout()
        '
        'btn_Start
        '
        Me.btn_Start.BackColor = System.Drawing.SystemColors.Control
        Me.btn_Start.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.btn_Start.Location = New System.Drawing.Point(752, 0)
        Me.btn_Start.Name = "btn_Start"
        Me.btn_Start.Size = New System.Drawing.Size(104, 20)
        Me.btn_Start.TabIndex = 1
        Me.btn_Start.Text = "Start"
        '
        'NIcon
        '
        Me.NIcon.ContextMenu = Me.CMnu
        Me.NIcon.Icon = CType(resources.GetObject("NIcon.Icon"), System.Drawing.Icon)
        Me.NIcon.Text = "TaskStarter"
        Me.NIcon.Visible = True
        '
        'CMnu
        '
        Me.CMnu.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.Mnu_Hide, Me.mnu_Verlauf, Me.MenuItem2, Me.Mnu_End})
        '
        'Mnu_Hide
        '
        Me.Mnu_Hide.Checked = True
        Me.Mnu_Hide.DefaultItem = True
        Me.Mnu_Hide.Index = 0
        Me.Mnu_Hide.Text = "Anzeigen"
        '
        'mnu_Verlauf
        '
        Me.mnu_Verlauf.Index = 1
        Me.mnu_Verlauf.Text = "Verlauf löschen"
        '
        'MenuItem2
        '
        Me.MenuItem2.Index = 2
        Me.MenuItem2.Text = "-"
        '
        'Mnu_End
        '
        Me.Mnu_End.Index = 3
        Me.Mnu_End.Text = "Beenden"
        '
        'Scroll_Tasks
        '
        Me.Scroll_Tasks.Location = New System.Drawing.Point(720, 0)
        Me.Scroll_Tasks.Name = "Scroll_Tasks"
        Me.Scroll_Tasks.Size = New System.Drawing.Size(20, 20)
        Me.Scroll_Tasks.TabIndex = 2
        '
        'dmn_Path
        '
        Me.dmn_Path.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.dmn_Path.Location = New System.Drawing.Point(8, 0)
        Me.dmn_Path.Name = "dmn_Path"
        Me.dmn_Path.Size = New System.Drawing.Size(744, 20)
        Me.dmn_Path.TabIndex = 3
        '
        'frm_Task_Starter
        '
        Me.AcceptButton = Me.btn_Start
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.BackColor = System.Drawing.Color.FromArgb(CType(255, Byte), CType(192, Byte), CType(255, Byte))
        Me.ClientSize = New System.Drawing.Size(856, 24)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.dmn_Path, Me.Scroll_Tasks, Me.btn_Start})
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None
        Me.Location = New System.Drawing.Point(0, 717)
        Me.Name = "frm_Task_Starter"
        Me.Opacity = 0.699999988079071
        Me.ShowInTaskbar = False
        Me.StartPosition = System.Windows.Forms.FormStartPosition.Manual
        Me.Text = "TaskStarter"
        Me.TopMost = True
        Me.TransparencyKey = System.Drawing.Color.FromArgb(CType(255, Byte), CType(192, Byte), CType(255, Byte))
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub MenuItem1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Mnu_Hide.Click
        If Mnu_Hide.Checked = True Then
            Me.Hide()
            Mnu_Hide.Checked = False
        Else
            Me.Show()
            Mnu_Hide.Checked = True
        End If
    End Sub

    Private Sub Mnu_End_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Mnu_End.Click
        End
    End Sub

    Private Sub btn_Start_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Start.Click
        Try
            System.Diagnostics.Process.Start(dmn_Path.Text)
            dmn_Path.Items.Add(dmn_Path.Text)
        Catch
        End Try
    End Sub

    Private Sub MenuItem1_Click_1(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnu_Verlauf.Click
        dmn_Path.Items.Clear()
        dmn_Path.Text = ""
    End Sub

    Private Sub CMnu_Popup(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CMnu.Popup

    End Sub
End Class
