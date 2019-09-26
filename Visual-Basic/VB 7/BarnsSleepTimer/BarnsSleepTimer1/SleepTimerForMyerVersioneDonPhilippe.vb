Imports System.IO
Imports System.IO.Path
Imports Microsoft.Win32

Public Class frmTimer
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
    Friend WithEvents btnStart As System.Windows.Forms.Button
    Friend WithEvents btnHide As System.Windows.Forms.Button
    Friend WithEvents btnSave As System.Windows.Forms.Button
    Friend WithEvents grpSet As System.Windows.Forms.GroupBox
    Friend WithEvents grpInfo As System.Windows.Forms.GroupBox
    Friend WithEvents grpTime As System.Windows.Forms.GroupBox
    Friend WithEvents rbnMin As System.Windows.Forms.RadioButton
    Friend WithEvents rbnUhr As System.Windows.Forms.RadioButton
    Friend WithEvents chkLogOff As System.Windows.Forms.CheckBox
    Friend WithEvents nudMin As System.Windows.Forms.NumericUpDown
    Friend WithEvents dtpUhr As System.Windows.Forms.DateTimePicker
    Friend WithEvents chkAuto As System.Windows.Forms.CheckBox
    Friend WithEvents tmrTimer As System.Windows.Forms.Timer
    Friend WithEvents lblInfo As System.Windows.Forms.Label
    Friend WithEvents lblUhr As System.Windows.Forms.Label
    Friend WithEvents lblMin As System.Windows.Forms.Label
    Friend WithEvents tmrHide As System.Windows.Forms.Timer
    Friend WithEvents ntfIcon As System.Windows.Forms.NotifyIcon
    Friend WithEvents tltTips As System.Windows.Forms.ToolTip
    Friend WithEvents CMnuIcon As System.Windows.Forms.ContextMenu
    Friend WithEvents CmnuShow As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem2 As System.Windows.Forms.MenuItem
    Friend WithEvents CMnuTime As System.Windows.Forms.MenuItem
    Friend WithEvents CMnuExit As System.Windows.Forms.MenuItem
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Dim resources As System.Resources.ResourceManager = New System.Resources.ResourceManager(GetType(frmTimer))
        Me.btnStart = New System.Windows.Forms.Button()
        Me.btnHide = New System.Windows.Forms.Button()
        Me.btnSave = New System.Windows.Forms.Button()
        Me.grpSet = New System.Windows.Forms.GroupBox()
        Me.chkLogOff = New System.Windows.Forms.CheckBox()
        Me.rbnUhr = New System.Windows.Forms.RadioButton()
        Me.rbnMin = New System.Windows.Forms.RadioButton()
        Me.grpInfo = New System.Windows.Forms.GroupBox()
        Me.lblMin = New System.Windows.Forms.Label()
        Me.lblUhr = New System.Windows.Forms.Label()
        Me.grpTime = New System.Windows.Forms.GroupBox()
        Me.nudMin = New System.Windows.Forms.NumericUpDown()
        Me.dtpUhr = New System.Windows.Forms.DateTimePicker()
        Me.lblInfo = New System.Windows.Forms.Label()
        Me.chkAuto = New System.Windows.Forms.CheckBox()
        Me.tmrTimer = New System.Windows.Forms.Timer(Me.components)
        Me.tltTips = New System.Windows.Forms.ToolTip(Me.components)
        Me.tmrHide = New System.Windows.Forms.Timer(Me.components)
        Me.ntfIcon = New System.Windows.Forms.NotifyIcon(Me.components)
        Me.CMnuIcon = New System.Windows.Forms.ContextMenu()
        Me.CMnuTime = New System.Windows.Forms.MenuItem()
        Me.MenuItem2 = New System.Windows.Forms.MenuItem()
        Me.CmnuShow = New System.Windows.Forms.MenuItem()
        Me.CMnuExit = New System.Windows.Forms.MenuItem()
        Me.grpSet.SuspendLayout()
        Me.grpInfo.SuspendLayout()
        Me.grpTime.SuspendLayout()
        CType(Me.nudMin, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'btnStart
        '
        Me.btnStart.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnStart.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btnStart.Location = New System.Drawing.Point(280, 184)
        Me.btnStart.Name = "btnStart"
        Me.btnStart.Size = New System.Drawing.Size(144, 32)
        Me.btnStart.TabIndex = 0
        Me.btnStart.Text = "Timer starten"
        Me.tltTips.SetToolTip(Me.btnStart, "Startet den Timer mit den eingegebenen Informationen.")
        '
        'btnHide
        '
        Me.btnHide.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnHide.DialogResult = System.Windows.Forms.DialogResult.Cancel
        Me.btnHide.Location = New System.Drawing.Point(280, 232)
        Me.btnHide.Name = "btnHide"
        Me.btnHide.Size = New System.Drawing.Size(144, 24)
        Me.btnHide.TabIndex = 1
        Me.btnHide.Text = "Fenster verstecken"
        Me.tltTips.SetToolTip(Me.btnHide, "Blendet das Fenster aus. Es kann über das Tray-Symbol wieder geöffnet werden.")
        '
        'btnSave
        '
        Me.btnSave.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnSave.Location = New System.Drawing.Point(16, 232)
        Me.btnSave.Name = "btnSave"
        Me.btnSave.Size = New System.Drawing.Size(184, 24)
        Me.btnSave.TabIndex = 2
        Me.btnSave.Text = "Einstellungen speichern"
        Me.tltTips.SetToolTip(Me.btnSave, "Speichert die Einstellungen. Deaktiviert ggf. den Autostart, falls der Minuten-Co" & _
        "untdown aktiviert wurde.")
        '
        'grpSet
        '
        Me.grpSet.BackColor = System.Drawing.Color.Transparent
        Me.grpSet.Controls.AddRange(New System.Windows.Forms.Control() {Me.chkLogOff, Me.rbnUhr, Me.rbnMin})
        Me.grpSet.Location = New System.Drawing.Point(8, 8)
        Me.grpSet.Name = "grpSet"
        Me.grpSet.Size = New System.Drawing.Size(200, 104)
        Me.grpSet.TabIndex = 3
        Me.grpSet.TabStop = False
        Me.grpSet.Text = "Verhalten"
        '
        'chkLogOff
        '
        Me.chkLogOff.BackColor = System.Drawing.Color.Transparent
        Me.chkLogOff.Cursor = System.Windows.Forms.Cursors.Hand
        Me.chkLogOff.Location = New System.Drawing.Point(8, 72)
        Me.chkLogOff.Name = "chkLogOff"
        Me.chkLogOff.Size = New System.Drawing.Size(128, 24)
        Me.chkLogOff.TabIndex = 2
        Me.chkLogOff.Text = "Nur ausloggen"
        Me.tltTips.SetToolTip(Me.chkLogOff, "Der Benutzer wird nur abgemeldet, der Computer bleibt jedoch weiterhin aktiv.")
        '
        'rbnUhr
        '
        Me.rbnUhr.BackColor = System.Drawing.Color.Transparent
        Me.rbnUhr.Cursor = System.Windows.Forms.Cursors.Hand
        Me.rbnUhr.Location = New System.Drawing.Point(8, 40)
        Me.rbnUhr.Name = "rbnUhr"
        Me.rbnUhr.Size = New System.Drawing.Size(128, 24)
        Me.rbnUhr.TabIndex = 1
        Me.rbnUhr.Text = "Nach Uhrzeit richten"
        Me.tltTips.SetToolTip(Me.rbnUhr, "Timer nach feststehender Uhrzeit richten.")
        '
        'rbnMin
        '
        Me.rbnMin.BackColor = System.Drawing.Color.Transparent
        Me.rbnMin.Checked = True
        Me.rbnMin.Cursor = System.Windows.Forms.Cursors.Hand
        Me.rbnMin.Location = New System.Drawing.Point(8, 16)
        Me.rbnMin.Name = "rbnMin"
        Me.rbnMin.Size = New System.Drawing.Size(160, 24)
        Me.rbnMin.TabIndex = 0
        Me.rbnMin.TabStop = True
        Me.rbnMin.Text = "Minuten-Countown"
        Me.tltTips.SetToolTip(Me.rbnMin, "Lässt den Timer nach eingegebener Anzahl von Minuten laufen.")
        '
        'grpInfo
        '
        Me.grpInfo.BackColor = System.Drawing.Color.Transparent
        Me.grpInfo.Controls.AddRange(New System.Windows.Forms.Control() {Me.lblMin, Me.lblUhr})
        Me.grpInfo.Location = New System.Drawing.Point(216, 8)
        Me.grpInfo.Name = "grpInfo"
        Me.grpInfo.Size = New System.Drawing.Size(208, 104)
        Me.grpInfo.TabIndex = 4
        Me.grpInfo.TabStop = False
        Me.grpInfo.Text = "Info"
        '
        'lblMin
        '
        Me.lblMin.Location = New System.Drawing.Point(16, 64)
        Me.lblMin.Name = "lblMin"
        Me.lblMin.Size = New System.Drawing.Size(184, 16)
        Me.lblMin.TabIndex = 1
        Me.lblMin.Text = "Computer schaltet in ... Minuten ab."
        '
        'lblUhr
        '
        Me.lblUhr.BackColor = System.Drawing.Color.Transparent
        Me.lblUhr.Location = New System.Drawing.Point(16, 24)
        Me.lblUhr.Name = "lblUhr"
        Me.lblUhr.Size = New System.Drawing.Size(184, 16)
        Me.lblUhr.TabIndex = 0
        Me.lblUhr.Text = "Computer schaltet um ... ab."
        '
        'grpTime
        '
        Me.grpTime.BackColor = System.Drawing.Color.Transparent
        Me.grpTime.Controls.AddRange(New System.Windows.Forms.Control() {Me.nudMin, Me.dtpUhr, Me.lblInfo, Me.chkAuto})
        Me.grpTime.Location = New System.Drawing.Point(8, 120)
        Me.grpTime.Name = "grpTime"
        Me.grpTime.Size = New System.Drawing.Size(200, 104)
        Me.grpTime.TabIndex = 5
        Me.grpTime.TabStop = False
        Me.grpTime.Text = "Timer"
        '
        'nudMin
        '
        Me.nudMin.Cursor = System.Windows.Forms.Cursors.Hand
        Me.nudMin.Location = New System.Drawing.Point(16, 48)
        Me.nudMin.Maximum = New Decimal(New Integer() {20160, 0, 0, 0})
        Me.nudMin.Minimum = New Decimal(New Integer() {1, 0, 0, 0})
        Me.nudMin.Name = "nudMin"
        Me.nudMin.Size = New System.Drawing.Size(88, 20)
        Me.nudMin.TabIndex = 0
        Me.nudMin.TextAlign = System.Windows.Forms.HorizontalAlignment.Right
        Me.tltTips.SetToolTip(Me.nudMin, "Anzahl der Minuten eingeben, Werte zwischen 1 und 20160 (14 Tage) zulässig.")
        Me.nudMin.Value = New Decimal(New Integer() {1, 0, 0, 0})
        '
        'dtpUhr
        '
        Me.dtpUhr.Cursor = System.Windows.Forms.Cursors.Hand
        Me.dtpUhr.CustomFormat = "HH:mm"
        Me.dtpUhr.Format = System.Windows.Forms.DateTimePickerFormat.Custom
        Me.dtpUhr.Location = New System.Drawing.Point(16, 48)
        Me.dtpUhr.MinDate = New Date(2007, 1, 30, 0, 0, 0, 0)
        Me.dtpUhr.Name = "dtpUhr"
        Me.dtpUhr.ShowUpDown = True
        Me.dtpUhr.Size = New System.Drawing.Size(88, 20)
        Me.dtpUhr.TabIndex = 1
        Me.tltTips.SetToolTip(Me.dtpUhr, "Uhrzeit der Abschaltung eingeben. Werte liegen immer in der Zukunft.")
        Me.dtpUhr.Visible = False
        '
        'lblInfo
        '
        Me.lblInfo.BackColor = System.Drawing.Color.Transparent
        Me.lblInfo.Location = New System.Drawing.Point(16, 16)
        Me.lblInfo.Name = "lblInfo"
        Me.lblInfo.Size = New System.Drawing.Size(168, 32)
        Me.lblInfo.TabIndex = 3
        Me.lblInfo.Text = "Bitte geben sie die gewünschte Anzahl der Minuten ein."
        '
        'chkAuto
        '
        Me.chkAuto.BackColor = System.Drawing.Color.Transparent
        Me.chkAuto.Cursor = System.Windows.Forms.Cursors.Hand
        Me.chkAuto.Enabled = False
        Me.chkAuto.Location = New System.Drawing.Point(16, 80)
        Me.chkAuto.Name = "chkAuto"
        Me.chkAuto.Size = New System.Drawing.Size(184, 16)
        Me.chkAuto.TabIndex = 2
        Me.chkAuto.Text = "Timer mit Windows autostarten"
        Me.tltTips.SetToolTip(Me.chkAuto, "Lässt Autostart des Timers beim Start des Betriebssystems zu. Bei Wiederaufruf de" & _
        "s Programms mit aktivierter und gespeicherter Option wird das Programm zunächst " & _
        "getarnt sei, doch ist über den Tray aufrufbar. Nur beim Timer nach Uhrzeit mögli" & _
        "ch.")
        '
        'tmrTimer
        '
        Me.tmrTimer.Interval = 60000
        '
        'tltTips
        '
        Me.tltTips.AutoPopDelay = 6000
        Me.tltTips.InitialDelay = 1000
        Me.tltTips.ReshowDelay = 100
        '
        'tmrHide
        '
        Me.tmrHide.Interval = 1
        '
        'ntfIcon
        '
        Me.ntfIcon.ContextMenu = Me.CMnuIcon
        Me.ntfIcon.Icon = CType(resources.GetObject("ntfIcon.Icon"), System.Drawing.Icon)
        Me.ntfIcon.Text = "SleepTimer by Barn"
        Me.ntfIcon.Visible = True
        '
        'CMnuIcon
        '
        Me.CMnuIcon.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.CMnuTime, Me.MenuItem2, Me.CmnuShow, Me.CMnuExit})
        '
        'CMnuTime
        '
        Me.CMnuTime.DefaultItem = True
        Me.CMnuTime.Enabled = False
        Me.CMnuTime.Index = 0
        Me.CMnuTime.Text = "inaktiv"
        Me.CMnuTime.Visible = False
        '
        'MenuItem2
        '
        Me.MenuItem2.Index = 1
        Me.MenuItem2.Text = "-"
        '
        'CmnuShow
        '
        Me.CmnuShow.Checked = True
        Me.CmnuShow.DefaultItem = True
        Me.CmnuShow.Index = 2
        Me.CmnuShow.ShowShortcut = False
        Me.CmnuShow.Text = "Anzeigen"
        '
        'CMnuExit
        '
        Me.CMnuExit.Index = 3
        Me.CMnuExit.Text = "Beenden"
        '
        'frmTimer
        '
        Me.AcceptButton = Me.btnStart
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.BackgroundImage = CType(resources.GetObject("$this.BackgroundImage"), System.Drawing.Bitmap)
        Me.CancelButton = Me.btnHide
        Me.ClientSize = New System.Drawing.Size(434, 264)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.grpTime, Me.grpInfo, Me.grpSet, Me.btnSave, Me.btnHide, Me.btnStart})
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.MaximizeBox = False
        Me.Name = "frmTimer"
        Me.Opacity = 0.85000002384185791
        Me.Text = "SleepTimer by Barn 1.5 ~ Versione  Don Philippe"
        Me.grpSet.ResumeLayout(False)
        Me.grpInfo.ResumeLayout(False)
        Me.grpTime.ResumeLayout(False)
        CType(Me.nudMin, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Minutes As Integer      'Anzahl der Minuten in Minutes als Private Variable
    Private Uhrz As Date            'Für die Uhrzeitübermittlung
    Private Force As Byte

    Private Sub frmTimer_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Registry.CurrentUser.CreateSubKey("Software\BarnsSleepTimer") 'Against Errors

        '############################################################################
        'Registry-Key für die Zeit checken
        '############################################################################

        Try
            Dim SavedZeit As Date
            SavedZeit = Registry.CurrentUser.OpenSubKey("Software\BarnsSleepTimer").GetValue("Time").ToString()
            rbnUhr.Checked = True
            dtpUhr.Visible = True
            nudMin.Visible = False
            dtpUhr.Value = SavedZeit
            lblInfo.Text = "Bitte geben sie die gewünschte Uhrzeit ein."
        Catch
        End Try

        '############################################################################
        'Registry-Key für die Minuten checken
        '############################################################################

        Try
            Dim Mins As Integer
            Mins = Registry.CurrentUser.OpenSubKey("Software\BarnsSleepTimer").GetValue("Mins").ToString()
            rbnMin.Checked = True
            dtpUhr.Visible = False
            nudMin.Visible = True
            nudMin.Value = Mins
            lblInfo.Text = "Bitte geben sie die gewünschte Anzahl der Minuten ein."
        Catch
        End Try

        '############################################################################
        'Registry-Key für Autostart checken und Häkchen checken, unsichtbar machen.
        '############################################################################

        Try   'Wenn der SubKey da is, Häkchen setzen, tmrEnabled für Unsichtbar einschalten
            Registry.CurrentUser.OpenSubKey("Software\Microsoft\Windows\CurrentVersion\Run").GetValue("BarnsSleepTimer").ToString()
            chkAuto.Checked = True
            tmrHide.Enabled = True
        Catch   'Wenner nich da is, nix tun...
        End Try


        '############################################################################
        'Registry-Key für Erzwingen checken und Häkchen checken, unsichtbar machen.
        '############################################################################

        Try   'Wenn der SubKey da is, Häkchen setzen, tmrEnabled für Unsichtbar einschalten
            Registry.CurrentUser.OpenSubKey("Software\BarnsSleepTimer").GetValue("LogOff").ToString()
            chkLogOff.Checked = True
            Force = 4
        Catch   'Wenner nich da is, nix tun...
            Force = 12
        End Try

    End Sub

    Private Sub tmrTimer_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles tmrTimer.Tick

        '############################################################################
        'Timer für Minuten-Eingabe
        '############################################################################

        Minutes -= 1

        lblMin.Text = "Computer schaltet in " & Minutes & " Minuten ab."

        ntfIcon.Text = "SleepTimer by Barn" & vbNewLine & "Abschaltung in " & _
          Minutes & " Minuten"
        CMnuTime.Text = Minutes & " Minuten"

        '############################################################################
        'SHUTDOWN für Minuten-Eingabe
        '############################################################################

        If (Minutes = 1) Then
            lblMin.Text = "Computer schaltet in 1 Minute ab."
            ntfIcon.Text = "SleepTimer by Barn" & vbNewLine & "Abschaltung in " & _
                            "1 Minute"
            CMnuTime.Text = "1 Minuten"
        End If


        If (Minutes = 0) Then

            Dim iSystem, iEigenschaft As Object
            iSystem = GetObject( _
               "winmgmts:{impersonationLevel=impersonate,(Shutdown)}" & _
               "//./root/cimv2").ExecQuery( _
               "SELECT * FROM Win32_OperatingSystem")

            If (Force = 12) Then
                System.Diagnostics.Process.Start("C:\WINDOWS\system32\shutdown.exe", "-s -t 01")
            Else

                For Each iEigenschaft In iSystem
                    iEigenschaft.Win32Shutdown(Force)
                Next
            End If

        End If

    End Sub

    Private Sub btnSave_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnSave.Click

        '############################################################################
        'Autostart checken!
        '############################################################################

        If (chkAuto.Checked = True) Then        'Wenn Autostart aktiviert wurde, in Reg eintragen

            Dim Pfad As String
            Pfad = Environment.CurrentDirectory.ToString & "\SleepTimer by Barn.exe"
            Registry.CurrentUser.OpenSubKey("Software\Microsoft\Windows\CurrentVersion\Run", True).SetValue("BarnsSleepTimer", Pfad)

        Else                                    'Wenn Autostart deaktiviert...

            Try     'Wenn der SubKey da is, löschen, da Autostart deaktiviert
                Registry.CurrentUser.OpenSubKey("Software\Microsoft\Windows\CurrentVersion\Run", True).DeleteValue("BarnsSleepTimer")
            Catch   'Wenner nich da is, nix tun...
            End Try

        End If

        '############################################################################
        'Time-Key oder Minuten-Key saven! Wenn alles glatt geht...
        '############################################################################

        If (rbnUhr.Checked = True) Then     'Wenn die Uhr angwewählt

            Dim SavedZeit As Date             'Datum+Uhrzeit in den Key 'Time' übertragen...
            SavedZeit = dtpUhr.Value
            Registry.CurrentUser.OpenSubKey("Software\BarnsSleepTimer", True).SetValue("Time", SavedZeit)
            Registry.CurrentUser.OpenSubKey("Software\BarnsSleepTimer", True).DeleteValue("Mins", False)
        Else
            Dim Mins As Integer             'Datum+Uhrzeit in den Key 'Time' übertragen...
            Mins = nudMin.Value
            Registry.CurrentUser.OpenSubKey("Software\BarnsSleepTimer", True).SetValue("Mins", Mins)
            Registry.CurrentUser.OpenSubKey("Software\BarnsSleepTimer", True).DeleteValue("Time", False)
        End If

        '############################################################################
        'LogOff checken!
        '############################################################################

        If (chkLogOff.Checked = True) Then 'Wenn chkForce aktiviert wurde, in Reg eintragen

            Registry.CurrentUser.OpenSubKey("Software\BarnsSleepTimer", True).SetValue("LogOff", "Force to LogOff")
        Else                                    'Wenn Force deaktiviert...
            'Wenn der SubKey da is, löschen, da Autostart deaktiviert
            Registry.CurrentUser.OpenSubKey("Software\BarnsSleepTimer", True).DeleteValue("LogOff", False)

        End If

    End Sub

    Private Sub rbnMin_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles rbnMin.CheckedChanged

        '############################################################################
        'Wenn der Minutenchecker aktiv is, Autostarthäkchen disablen
        'Die Visibilität der Eingabeobjekte einstellen
        'Außerdedem lblInfo's Infotext einstellen
        '############################################################################

        chkAuto.Enabled = False
        chkAuto.Checked = False

        nudMin.Visible = True
        dtpUhr.Visible = False

        lblInfo.Text = "Bitte geben sie die gewünschte Anzahl der Minuten ein."


    End Sub

    Private Sub grpSet_Enter(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles grpSet.Enter

    End Sub

    Private Sub rbnUhr_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles rbnUhr.CheckedChanged

        '############################################################################
        'Wenn der Uhrchecker aktiv is, Autostarthäkchen enablen
        'Die Visibilität der Eingabeobjekte einstellen
        'Außerdem lblInfo's Infotext einstellen
        '############################################################################

        chkAuto.Enabled = True

        nudMin.Visible = False
        dtpUhr.Visible = True

        lblInfo.Text = "Bitte geben sie die gewünschte Uhrzeit ein."

    End Sub

    Private Sub btnHide_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnHide.Click
        CmnuShow.Checked = False
        tmrHide.Enabled = True
    End Sub

    Private Sub btnStart_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnStart.Click

        If (btnStart.Text = "Timer stoppen") Then   'Timer beide ausschalten, falls Button
            tmrTimer.Enabled = False               'schon aktiviert wurde
            tmrTimer.Enabled = False
            btnSave.Enabled = True
            chkAuto.Enabled = True
            nudMin.Enabled = True
            dtpUhr.Enabled = True
            chkLogOff.Enabled = True
            rbnUhr.Enabled = True
            rbnMin.Enabled = True
            lblUhr.Text = "Computer schaltet um ... ab."
            lblMin.Text = "Computer schaltet in ... Minuten ab."
            btnStart.Text = "Timer starten"
            ntfIcon.Text = "SleepTimer by Barn" & vbNewLine & "DDicated to Philipp"
            CMnuTime.Visible = False
        Else                                        'Wenn Butten aktiviert ist

            btnSave.Enabled = False
            chkAuto.Enabled = False
            nudMin.Enabled = False
            dtpUhr.Enabled = False
            chkLogOff.Enabled = False
            rbnUhr.Enabled = False
            rbnMin.Enabled = False

            If (rbnMin.Checked = True) Then           'Fallunterscheidung: Minutenanzahl

                Minutes = nudMin.Value            'Den Wert des Textfeldes in Minutes eintragen

                Uhrz = Now                        'Globale Variable Uhrz auf akt. Zeit setzen
                Uhrz = Uhrz.AddMinutes(Minutes)   'Und plus Minutes für Zeit des Shutdowns
                tmrTimer.Enabled = True        'Timer dann Aktivieren

                'Info-Labels schonmal beschriften weil wegen Warten auf Timer-Tick

                lblUhr.Text = "Computer schaltet um " & Uhrz.Hour.ToString & ":" & _
                      Uhrz.Minute.ToString() & " ab."

                If (Minutes <> 1) Then
                    lblMin.Text = "Computer schaltet in " & Minutes & " Minuten ab."
                    ntfIcon.Text = "SleepTimer by Barn" & vbNewLine & "Abschaltung in " & _
                                    Minutes & " Minuten"
                    CMnuTime.Text = Minutes & " Minuten"
                    CMnuTime.Visible = True
                Else
                    lblMin.Text = "Computer schaltet in 1 Minute ab."
                    ntfIcon.Text = "SleepTimer by Barn" & vbNewLine & "Abschaltung in " & _
                                    "1 Minute"
                    CMnuTime.Text = "1 Minute"
                    CMnuTime.Visible = True
                End If

            Else                                'Fallunterscheidung: Uhrzeit

                Dim DEing As Date                 'Hilfsvariable der Übersicht halber
                DEing = dtpUhr.Value

                'Minutes einstellen...
                Minutes = (DEing.Hour - Now.Hour) * 60 + (DEing.Minute - Now.Minute)

                If (Minutes <= 0) Then     'Wenn minuten kleiner als Null sind, nächsten Tach...
                    Minutes = Minutes + 1440
                End If

                lblUhr.Text = "Computer schaltet um " & DEing.Hour.ToString & ":" & _
                      DEing.Minute.ToString & " ab."
                If (Minutes <> 1) Then
                    lblMin.Text = "Computer schaltet in " & Minutes & " Minuten ab."
                    ntfIcon.Text = "SleepTimer by Barn" & vbNewLine & "Abschaltung in " & _
                                    Minutes & " Minuten"
                    CMnuTime.Text = Minutes & " Minuten"
                    CMnuTime.Visible = True

                Else
                    lblMin.Text = "Computer schaltet in 1 Minute ab."
                    ntfIcon.Text = "SleepTimer by Barn" & vbNewLine & "Abschaltung in " & _
                                    "1 Minute"
                    CMnuTime.Text = "1 Minute"
                    CMnuTime.Visible = True

                End If

                tmrTimer.Enabled = True        'Und Timer Aktivieren

            End If

            btnStart.Text = "Timer stoppen"           'Text des Buttons ändern.

        End If

    End Sub

    Private Sub tmrTimerUhr_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs)

        '############################################################################
        'Timer für Uhrzeit-Eingabe
        '############################################################################


        Minutes -= 1

        lblMin.Text = "Computer schaltet in " & Minutes & " Minuten ab."

        '############################################################################
        'SHUTDOWN für Minuten-Eingabe
        '############################################################################

        If (Minutes = 0) Then

            Dim System, Eigenschaft As Object
            System = GetObject( _
               "winmgmts:{impersonationLevel=impersonate,(Shutdown)}" & _
               "//./root/cimv2").ExecQuery( _
               "SELECT * FROM Win32_OperatingSystem")

            For Each Eigenschaft In System
                Eigenschaft.Win32Shutdown() '(eigentlich 8 + irgendwie wat erzwingen --> +4
            Next

        End If


    End Sub

    Private Sub tmrHide_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles tmrHide.Tick

        '############################################################################
        'Timer für halbwegs "eleganten" Autostart
        '###########################################################################

        If Me.Opacity > 0.0 Then
            Me.Opacity -= 0.24
        Else
            Me.Hide()
            Me.Opacity = 0.85
            tmrHide.Enabled = False
        End If

    End Sub

    Private Sub chkAuto_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles chkAuto.CheckedChanged

    End Sub

    Private Sub chkForce_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles chkLogOff.CheckedChanged
        If (chkLogOff.Checked = True) Then
            Force = 4
        Else
            Force = 12
        End If


    End Sub

    Private Sub grpTime_Enter(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles grpTime.Enter

    End Sub

    Private Sub CmnuShow_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CmnuShow.Click
        If CmnuShow.Checked = True Then
            CmnuShow.Checked = False
            tmrHide.Enabled = True
        Else
            Me.Show()
            Me.WindowState = FormWindowState.Normal
            CmnuShow.Checked = True
        End If
    End Sub

    Private Sub CMnuExit_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CMnuExit.Click
        End
    End Sub

    Private Sub CMnuIcon_Popup(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CMnuIcon.Popup

    End Sub

    Private Sub ntfIcon_MouseDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles ntfIcon.MouseDown

    End Sub

    Private Sub ntfIcon_DoubleClick(ByVal sender As Object, ByVal e As System.EventArgs) Handles ntfIcon.DoubleClick
        If CmnuShow.Checked = True Then
            CmnuShow.Checked = False
            tmrHide.Enabled = True
        Else
            Me.Show()
            CmnuShow.Checked = True
        End If
    End Sub

    Private Sub nudMin_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles nudMin.ValueChanged

    End Sub
End Class

