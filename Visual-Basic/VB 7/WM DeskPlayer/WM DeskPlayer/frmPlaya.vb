Public Class frm_Playa
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
    Friend WithEvents GroupBox1 As System.Windows.Forms.GroupBox
    Friend WithEvents MenuItem4 As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem7 As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem20 As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem21 As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem24 As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem12 As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem29 As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem30 As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem33 As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Datei As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Open As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_URL As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_NewPL As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_SavePL As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_SaveUPL As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_StartWMP As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_End As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Playing As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Play As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Stop As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Next As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Last As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_FF As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Random As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Repeat As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Vol As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_VolUp As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_VolDown As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Mute As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Vis As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_NoVis As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_WMPHelp As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_Links As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_laut As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_soundspectrum As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_analavory As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_About As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem1 As System.Windows.Forms.MenuItem
    Friend WithEvents mnu_EQ As System.Windows.Forms.MenuItem
    Friend WithEvents MMnuPlaya As System.Windows.Forms.MainMenu
    Friend WithEvents mp_SettingsOne As AxWMPLib.AxWindowsMediaPlayer
    Friend WithEvents mp_SettingsTwo As AxWMPLib.AxWindowsMediaPlayer
    Friend WithEvents Scroll_Visualizations As System.Windows.Forms.HScrollBar
    Friend WithEvents lbl_VisName As System.Windows.Forms.Label
    Friend WithEvents lst_PlayList As System.Windows.Forms.ListBox
    Friend WithEvents grp_NowPlaying As System.Windows.Forms.GroupBox
    Friend WithEvents lbl_Info1 As System.Windows.Forms.Label
    Friend WithEvents lbl_Info2 As System.Windows.Forms.Label
    Friend WithEvents lbl_Info3 As System.Windows.Forms.Label
    Friend WithEvents lbl_Info4 As System.Windows.Forms.Label
    Friend WithEvents lbl_Info5 As System.Windows.Forms.Label
    Friend WithEvents lbl_Info6 As System.Windows.Forms.Label
    Friend WithEvents lbl_Titel As System.Windows.Forms.TextBox
    Friend WithEvents lbl_Interpret As System.Windows.Forms.TextBox
    Friend WithEvents lbl_Album As System.Windows.Forms.TextBox
    Friend WithEvents lbl_Genre As System.Windows.Forms.TextBox
    Friend WithEvents lbl_Laenge As System.Windows.Forms.TextBox
    Friend WithEvents lbl_VerstrZeit As System.Windows.Forms.TextBox
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Dim resources As System.Resources.ResourceManager = New System.Resources.ResourceManager(GetType(frm_Playa))
        Me.GroupBox1 = New System.Windows.Forms.GroupBox()
        Me.lst_PlayList = New System.Windows.Forms.ListBox()
        Me.grp_NowPlaying = New System.Windows.Forms.GroupBox()
        Me.lbl_VerstrZeit = New System.Windows.Forms.TextBox()
        Me.lbl_Laenge = New System.Windows.Forms.TextBox()
        Me.lbl_Genre = New System.Windows.Forms.TextBox()
        Me.lbl_Album = New System.Windows.Forms.TextBox()
        Me.lbl_Interpret = New System.Windows.Forms.TextBox()
        Me.lbl_Titel = New System.Windows.Forms.TextBox()
        Me.lbl_Info6 = New System.Windows.Forms.Label()
        Me.lbl_Info5 = New System.Windows.Forms.Label()
        Me.lbl_Info4 = New System.Windows.Forms.Label()
        Me.lbl_Info3 = New System.Windows.Forms.Label()
        Me.lbl_Info2 = New System.Windows.Forms.Label()
        Me.lbl_Info1 = New System.Windows.Forms.Label()
        Me.mp_SettingsOne = New AxWMPLib.AxWindowsMediaPlayer()
        Me.MMnuPlaya = New System.Windows.Forms.MainMenu()
        Me.mnu_Datei = New System.Windows.Forms.MenuItem()
        Me.mnu_Open = New System.Windows.Forms.MenuItem()
        Me.mnu_URL = New System.Windows.Forms.MenuItem()
        Me.MenuItem4 = New System.Windows.Forms.MenuItem()
        Me.mnu_NewPL = New System.Windows.Forms.MenuItem()
        Me.mnu_SavePL = New System.Windows.Forms.MenuItem()
        Me.mnu_SaveUPL = New System.Windows.Forms.MenuItem()
        Me.MenuItem7 = New System.Windows.Forms.MenuItem()
        Me.mnu_StartWMP = New System.Windows.Forms.MenuItem()
        Me.mnu_End = New System.Windows.Forms.MenuItem()
        Me.mnu_Playing = New System.Windows.Forms.MenuItem()
        Me.mnu_Play = New System.Windows.Forms.MenuItem()
        Me.mnu_Stop = New System.Windows.Forms.MenuItem()
        Me.MenuItem20 = New System.Windows.Forms.MenuItem()
        Me.mnu_Next = New System.Windows.Forms.MenuItem()
        Me.mnu_Last = New System.Windows.Forms.MenuItem()
        Me.mnu_FF = New System.Windows.Forms.MenuItem()
        Me.MenuItem21 = New System.Windows.Forms.MenuItem()
        Me.mnu_Random = New System.Windows.Forms.MenuItem()
        Me.mnu_Repeat = New System.Windows.Forms.MenuItem()
        Me.MenuItem24 = New System.Windows.Forms.MenuItem()
        Me.mnu_Vol = New System.Windows.Forms.MenuItem()
        Me.mnu_VolUp = New System.Windows.Forms.MenuItem()
        Me.mnu_VolDown = New System.Windows.Forms.MenuItem()
        Me.mnu_Mute = New System.Windows.Forms.MenuItem()
        Me.MenuItem1 = New System.Windows.Forms.MenuItem()
        Me.mnu_EQ = New System.Windows.Forms.MenuItem()
        Me.mnu_Vis = New System.Windows.Forms.MenuItem()
        Me.mnu_NoVis = New System.Windows.Forms.MenuItem()
        Me.MenuItem12 = New System.Windows.Forms.MenuItem()
        Me.MenuItem29 = New System.Windows.Forms.MenuItem()
        Me.MenuItem30 = New System.Windows.Forms.MenuItem()
        Me.mnu_WMPHelp = New System.Windows.Forms.MenuItem()
        Me.MenuItem33 = New System.Windows.Forms.MenuItem()
        Me.mnu_Links = New System.Windows.Forms.MenuItem()
        Me.mnu_laut = New System.Windows.Forms.MenuItem()
        Me.mnu_soundspectrum = New System.Windows.Forms.MenuItem()
        Me.mnu_analavory = New System.Windows.Forms.MenuItem()
        Me.mnu_About = New System.Windows.Forms.MenuItem()
        Me.mp_SettingsTwo = New AxWMPLib.AxWindowsMediaPlayer()
        Me.Scroll_Visualizations = New System.Windows.Forms.HScrollBar()
        Me.lbl_VisName = New System.Windows.Forms.Label()
        Me.GroupBox1.SuspendLayout()
        Me.grp_NowPlaying.SuspendLayout()
        CType(Me.mp_SettingsOne, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.mp_SettingsTwo, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'GroupBox1
        '
        Me.GroupBox1.Controls.AddRange(New System.Windows.Forms.Control() {Me.lst_PlayList})
        Me.GroupBox1.Location = New System.Drawing.Point(208, 8)
        Me.GroupBox1.Name = "GroupBox1"
        Me.GroupBox1.Size = New System.Drawing.Size(560, 472)
        Me.GroupBox1.TabIndex = 0
        Me.GroupBox1.TabStop = False
        Me.GroupBox1.Text = "PlayList"
        '
        'lst_PlayList
        '
        Me.lst_PlayList.Location = New System.Drawing.Point(8, 16)
        Me.lst_PlayList.Name = "lst_PlayList"
        Me.lst_PlayList.Size = New System.Drawing.Size(544, 446)
        Me.lst_PlayList.TabIndex = 0
        '
        'grp_NowPlaying
        '
        Me.grp_NowPlaying.Controls.AddRange(New System.Windows.Forms.Control() {Me.lbl_VerstrZeit, Me.lbl_Laenge, Me.lbl_Genre, Me.lbl_Album, Me.lbl_Interpret, Me.lbl_Titel, Me.lbl_Info6, Me.lbl_Info5, Me.lbl_Info4, Me.lbl_Info3, Me.lbl_Info2, Me.lbl_Info1})
        Me.grp_NowPlaying.Location = New System.Drawing.Point(8, 8)
        Me.grp_NowPlaying.Name = "grp_NowPlaying"
        Me.grp_NowPlaying.Size = New System.Drawing.Size(192, 256)
        Me.grp_NowPlaying.TabIndex = 1
        Me.grp_NowPlaying.TabStop = False
        Me.grp_NowPlaying.Text = "Now Playing"
        '
        'lbl_VerstrZeit
        '
        Me.lbl_VerstrZeit.BackColor = System.Drawing.SystemColors.Control
        Me.lbl_VerstrZeit.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.lbl_VerstrZeit.Cursor = System.Windows.Forms.Cursors.Default
        Me.lbl_VerstrZeit.Location = New System.Drawing.Point(8, 232)
        Me.lbl_VerstrZeit.Name = "lbl_VerstrZeit"
        Me.lbl_VerstrZeit.ReadOnly = True
        Me.lbl_VerstrZeit.Size = New System.Drawing.Size(168, 13)
        Me.lbl_VerstrZeit.TabIndex = 11
        Me.lbl_VerstrZeit.Text = ""
        '
        'lbl_Laenge
        '
        Me.lbl_Laenge.BackColor = System.Drawing.SystemColors.Control
        Me.lbl_Laenge.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.lbl_Laenge.Cursor = System.Windows.Forms.Cursors.Default
        Me.lbl_Laenge.Location = New System.Drawing.Point(8, 192)
        Me.lbl_Laenge.Name = "lbl_Laenge"
        Me.lbl_Laenge.ReadOnly = True
        Me.lbl_Laenge.Size = New System.Drawing.Size(176, 13)
        Me.lbl_Laenge.TabIndex = 10
        Me.lbl_Laenge.Text = ""
        '
        'lbl_Genre
        '
        Me.lbl_Genre.BackColor = System.Drawing.SystemColors.Control
        Me.lbl_Genre.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.lbl_Genre.Cursor = System.Windows.Forms.Cursors.Default
        Me.lbl_Genre.Location = New System.Drawing.Point(8, 152)
        Me.lbl_Genre.Name = "lbl_Genre"
        Me.lbl_Genre.ReadOnly = True
        Me.lbl_Genre.Size = New System.Drawing.Size(176, 13)
        Me.lbl_Genre.TabIndex = 9
        Me.lbl_Genre.Text = ""
        '
        'lbl_Album
        '
        Me.lbl_Album.BackColor = System.Drawing.SystemColors.Control
        Me.lbl_Album.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.lbl_Album.Cursor = System.Windows.Forms.Cursors.Default
        Me.lbl_Album.Location = New System.Drawing.Point(8, 112)
        Me.lbl_Album.Name = "lbl_Album"
        Me.lbl_Album.ReadOnly = True
        Me.lbl_Album.Size = New System.Drawing.Size(176, 13)
        Me.lbl_Album.TabIndex = 8
        Me.lbl_Album.Text = ""
        '
        'lbl_Interpret
        '
        Me.lbl_Interpret.BackColor = System.Drawing.SystemColors.Control
        Me.lbl_Interpret.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.lbl_Interpret.Cursor = System.Windows.Forms.Cursors.Default
        Me.lbl_Interpret.Location = New System.Drawing.Point(8, 72)
        Me.lbl_Interpret.Name = "lbl_Interpret"
        Me.lbl_Interpret.ReadOnly = True
        Me.lbl_Interpret.Size = New System.Drawing.Size(176, 13)
        Me.lbl_Interpret.TabIndex = 7
        Me.lbl_Interpret.Text = ""
        '
        'lbl_Titel
        '
        Me.lbl_Titel.BackColor = System.Drawing.SystemColors.Control
        Me.lbl_Titel.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.lbl_Titel.Cursor = System.Windows.Forms.Cursors.Default
        Me.lbl_Titel.Location = New System.Drawing.Point(8, 32)
        Me.lbl_Titel.Name = "lbl_Titel"
        Me.lbl_Titel.ReadOnly = True
        Me.lbl_Titel.Size = New System.Drawing.Size(176, 13)
        Me.lbl_Titel.TabIndex = 6
        Me.lbl_Titel.Text = ""
        '
        'lbl_Info6
        '
        Me.lbl_Info6.Location = New System.Drawing.Point(8, 216)
        Me.lbl_Info6.Name = "lbl_Info6"
        Me.lbl_Info6.Size = New System.Drawing.Size(104, 16)
        Me.lbl_Info6.TabIndex = 5
        Me.lbl_Info6.Text = "Verstrichene Zeit:"
        '
        'lbl_Info5
        '
        Me.lbl_Info5.Location = New System.Drawing.Point(8, 176)
        Me.lbl_Info5.Name = "lbl_Info5"
        Me.lbl_Info5.Size = New System.Drawing.Size(40, 16)
        Me.lbl_Info5.TabIndex = 4
        Me.lbl_Info5.Text = "Länge:"
        '
        'lbl_Info4
        '
        Me.lbl_Info4.Location = New System.Drawing.Point(8, 136)
        Me.lbl_Info4.Name = "lbl_Info4"
        Me.lbl_Info4.Size = New System.Drawing.Size(48, 16)
        Me.lbl_Info4.TabIndex = 3
        Me.lbl_Info4.Text = "Genre:"
        '
        'lbl_Info3
        '
        Me.lbl_Info3.Location = New System.Drawing.Point(8, 96)
        Me.lbl_Info3.Name = "lbl_Info3"
        Me.lbl_Info3.Size = New System.Drawing.Size(48, 16)
        Me.lbl_Info3.TabIndex = 2
        Me.lbl_Info3.Text = "Album:"
        '
        'lbl_Info2
        '
        Me.lbl_Info2.Location = New System.Drawing.Point(8, 56)
        Me.lbl_Info2.Name = "lbl_Info2"
        Me.lbl_Info2.Size = New System.Drawing.Size(56, 16)
        Me.lbl_Info2.TabIndex = 1
        Me.lbl_Info2.Text = "Interpret:"
        '
        'lbl_Info1
        '
        Me.lbl_Info1.Location = New System.Drawing.Point(8, 16)
        Me.lbl_Info1.Name = "lbl_Info1"
        Me.lbl_Info1.Size = New System.Drawing.Size(56, 16)
        Me.lbl_Info1.TabIndex = 0
        Me.lbl_Info1.Text = "Titel:"
        '
        'mp_SettingsOne
        '
        Me.mp_SettingsOne.enabled = True
        Me.mp_SettingsOne.Location = New System.Drawing.Point(0, 489)
        Me.mp_SettingsOne.Name = "mp_SettingsOne"
        Me.mp_SettingsOne.OcxState = CType(resources.GetObject("mp_SettingsOne.OcxState"), System.Windows.Forms.AxHost.State)
        Me.mp_SettingsOne.Size = New System.Drawing.Size(778, 45)
        Me.mp_SettingsOne.TabIndex = 0
        '
        'MMnuPlaya
        '
        Me.MMnuPlaya.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnu_Datei, Me.mnu_Playing, Me.mnu_Vis, Me.MenuItem30})
        '
        'mnu_Datei
        '
        Me.mnu_Datei.Index = 0
        Me.mnu_Datei.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnu_Open, Me.mnu_URL, Me.MenuItem4, Me.mnu_NewPL, Me.mnu_SavePL, Me.mnu_SaveUPL, Me.MenuItem7, Me.mnu_StartWMP, Me.mnu_End})
        Me.mnu_Datei.Text = "Datei"
        '
        'mnu_Open
        '
        Me.mnu_Open.Index = 0
        Me.mnu_Open.Shortcut = System.Windows.Forms.Shortcut.CtrlO
        Me.mnu_Open.Text = "Öffnen..."
        '
        'mnu_URL
        '
        Me.mnu_URL.Index = 1
        Me.mnu_URL.Shortcut = System.Windows.Forms.Shortcut.CtrlU
        Me.mnu_URL.Text = "URL öffnen..."
        '
        'MenuItem4
        '
        Me.MenuItem4.Index = 2
        Me.MenuItem4.Text = "-"
        '
        'mnu_NewPL
        '
        Me.mnu_NewPL.Index = 3
        Me.mnu_NewPL.Text = "Neue PlayList"
        '
        'mnu_SavePL
        '
        Me.mnu_SavePL.Index = 4
        Me.mnu_SavePL.Text = "Aktuelle PlayList speichern"
        '
        'mnu_SaveUPL
        '
        Me.mnu_SaveUPL.Index = 5
        Me.mnu_SaveUPL.Text = "Aktuelle PlayList speichern unter..."
        '
        'MenuItem7
        '
        Me.MenuItem7.Index = 6
        Me.MenuItem7.Text = "-"
        '
        'mnu_StartWMP
        '
        Me.mnu_StartWMP.Index = 7
        Me.mnu_StartWMP.Text = "Start Windows MediaPlayer"
        '
        'mnu_End
        '
        Me.mnu_End.Index = 8
        Me.mnu_End.Text = "Beenden"
        '
        'mnu_Playing
        '
        Me.mnu_Playing.Index = 1
        Me.mnu_Playing.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnu_Play, Me.mnu_Stop, Me.MenuItem20, Me.mnu_Next, Me.mnu_Last, Me.mnu_FF, Me.MenuItem21, Me.mnu_Random, Me.mnu_Repeat, Me.MenuItem24, Me.mnu_Vol})
        Me.mnu_Playing.Text = "Wiedergabe"
        '
        'mnu_Play
        '
        Me.mnu_Play.Index = 0
        Me.mnu_Play.Shortcut = System.Windows.Forms.Shortcut.CtrlP
        Me.mnu_Play.Text = "Play/Pause"
        '
        'mnu_Stop
        '
        Me.mnu_Stop.Index = 1
        Me.mnu_Stop.Shortcut = System.Windows.Forms.Shortcut.CtrlS
        Me.mnu_Stop.Text = "Stop"
        '
        'MenuItem20
        '
        Me.MenuItem20.Index = 2
        Me.MenuItem20.Text = "-"
        '
        'mnu_Next
        '
        Me.mnu_Next.Index = 3
        Me.mnu_Next.Shortcut = System.Windows.Forms.Shortcut.CtrlN
        Me.mnu_Next.Text = "Next Track"
        '
        'mnu_Last
        '
        Me.mnu_Last.Index = 4
        Me.mnu_Last.Shortcut = System.Windows.Forms.Shortcut.CtrlL
        Me.mnu_Last.Text = "Last Track"
        '
        'mnu_FF
        '
        Me.mnu_FF.Index = 5
        Me.mnu_FF.Shortcut = System.Windows.Forms.Shortcut.CtrlF
        Me.mnu_FF.Text = "Fast Forward"
        '
        'MenuItem21
        '
        Me.MenuItem21.Index = 6
        Me.MenuItem21.Text = "-"
        '
        'mnu_Random
        '
        Me.mnu_Random.Index = 7
        Me.mnu_Random.Shortcut = System.Windows.Forms.Shortcut.CtrlR
        Me.mnu_Random.Text = "Random"
        '
        'mnu_Repeat
        '
        Me.mnu_Repeat.Index = 8
        Me.mnu_Repeat.Shortcut = System.Windows.Forms.Shortcut.CtrlL
        Me.mnu_Repeat.Text = "Repeat"
        '
        'MenuItem24
        '
        Me.MenuItem24.Index = 9
        Me.MenuItem24.Text = "-"
        '
        'mnu_Vol
        '
        Me.mnu_Vol.Index = 10
        Me.mnu_Vol.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnu_VolUp, Me.mnu_VolDown, Me.mnu_Mute, Me.MenuItem1, Me.mnu_EQ})
        Me.mnu_Vol.Text = "Volume"
        '
        'mnu_VolUp
        '
        Me.mnu_VolUp.Index = 0
        Me.mnu_VolUp.Shortcut = System.Windows.Forms.Shortcut.F10
        Me.mnu_VolUp.Text = "Volume Up"
        '
        'mnu_VolDown
        '
        Me.mnu_VolDown.Index = 1
        Me.mnu_VolDown.Shortcut = System.Windows.Forms.Shortcut.F9
        Me.mnu_VolDown.Text = "Volume Down"
        '
        'mnu_Mute
        '
        Me.mnu_Mute.Index = 2
        Me.mnu_Mute.Shortcut = System.Windows.Forms.Shortcut.F8
        Me.mnu_Mute.Text = "Mute"
        '
        'MenuItem1
        '
        Me.MenuItem1.Index = 3
        Me.MenuItem1.Text = "-"
        '
        'mnu_EQ
        '
        Me.mnu_EQ.Index = 4
        Me.mnu_EQ.Text = "Open Windows EQ"
        '
        'mnu_Vis
        '
        Me.mnu_Vis.Index = 2
        Me.mnu_Vis.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnu_NoVis, Me.MenuItem12, Me.MenuItem29})
        Me.mnu_Vis.Text = "Visualization"
        '
        'mnu_NoVis
        '
        Me.mnu_NoVis.Index = 0
        Me.mnu_NoVis.Text = "keine Visualisierung"
        '
        'MenuItem12
        '
        Me.MenuItem12.Index = 1
        Me.MenuItem12.Text = "-"
        '
        'MenuItem29
        '
        Me.MenuItem29.Index = 2
        Me.MenuItem29.Text = "function not yet available"
        '
        'MenuItem30
        '
        Me.MenuItem30.Index = 3
        Me.MenuItem30.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnu_WMPHelp, Me.MenuItem33, Me.mnu_Links, Me.mnu_About})
        Me.MenuItem30.Text = "El Speciale"
        '
        'mnu_WMPHelp
        '
        Me.mnu_WMPHelp.Index = 0
        Me.mnu_WMPHelp.Shortcut = System.Windows.Forms.Shortcut.F1
        Me.mnu_WMPHelp.Text = "Windows MediaPlayer Hilfe..."
        '
        'MenuItem33
        '
        Me.MenuItem33.Index = 1
        Me.MenuItem33.Text = "-"
        '
        'mnu_Links
        '
        Me.mnu_Links.Index = 2
        Me.mnu_Links.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnu_laut, Me.mnu_soundspectrum, Me.mnu_analavory})
        Me.mnu_Links.Text = "Linkz"
        '
        'mnu_laut
        '
        Me.mnu_laut.Index = 0
        Me.mnu_laut.Text = "laut.de"
        '
        'mnu_soundspectrum
        '
        Me.mnu_soundspectrum.Index = 1
        Me.mnu_soundspectrum.Text = "soundspectrum.com"
        '
        'mnu_analavory
        '
        Me.mnu_analavory.Index = 2
        Me.mnu_analavory.Text = "analavoryproject.com"
        '
        'mnu_About
        '
        Me.mnu_About.Index = 3
        Me.mnu_About.Text = "About a Barn..."
        '
        'mp_SettingsTwo
        '
        Me.mp_SettingsTwo.enabled = True
        Me.mp_SettingsTwo.Location = New System.Drawing.Point(8, 304)
        Me.mp_SettingsTwo.Name = "mp_SettingsTwo"
        Me.mp_SettingsTwo.OcxState = CType(resources.GetObject("mp_SettingsTwo.OcxState"), System.Windows.Forms.AxHost.State)
        Me.mp_SettingsTwo.Size = New System.Drawing.Size(168, 168)
        Me.mp_SettingsTwo.TabIndex = 0
        '
        'Scroll_Visualizations
        '
        Me.Scroll_Visualizations.Location = New System.Drawing.Point(8, 456)
        Me.Scroll_Visualizations.Name = "Scroll_Visualizations"
        Me.Scroll_Visualizations.Size = New System.Drawing.Size(35, 16)
        Me.Scroll_Visualizations.TabIndex = 0
        '
        'lbl_VisName
        '
        Me.lbl_VisName.Location = New System.Drawing.Point(16, 288)
        Me.lbl_VisName.Name = "lbl_VisName"
        Me.lbl_VisName.Size = New System.Drawing.Size(136, 16)
        Me.lbl_VisName.TabIndex = 0
        Me.lbl_VisName.Text = "Name der Vis"
        Me.lbl_VisName.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        '
        'frm_Playa
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(778, 534)
        Me.Controls.AddRange(New System.Windows.Forms.Control() {Me.Scroll_Visualizations, Me.GroupBox1, Me.mp_SettingsOne, Me.lbl_VisName, Me.grp_NowPlaying, Me.mp_SettingsTwo})
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.MaximizeBox = False
        Me.Menu = Me.MMnuPlaya
        Me.Name = "frm_Playa"
        Me.Text = "Windows Media DeskPlayer | Barn 06"
        Me.GroupBox1.ResumeLayout(False)
        Me.grp_NowPlaying.ResumeLayout(False)
        CType(Me.mp_SettingsOne, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.mp_SettingsTwo, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub frm_Playa_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub

    Private Sub Panel1_Paint(ByVal sender As System.Object, ByVal e As System.Windows.Forms.PaintEventArgs)
    End Sub

    Private Sub MenuItem31_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnu_About.Click
        Dim OAbout As New frm_About()
        OAbout.Show()
    End Sub

    Private Sub lbl_Album_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles lbl_Album.TextChanged

    End Sub

    Private Sub lbl_Info5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles lbl_Info5.Click

    End Sub

    Private Sub lbl_Genre_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles lbl_Genre.TextChanged

    End Sub

    Private Sub lbl_Info6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles lbl_Info6.Click

    End Sub
End Class
