VERSION 5.00
Begin VB.Form WinSeek 
   BackColor       =   &H80000000&
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Suchen"
   ClientHeight    =   4980
   ClientLeft      =   1920
   ClientTop       =   1890
   ClientWidth     =   7680
   BeginProperty Font 
      Name            =   "MS Sans Serif"
      Size            =   8.25
      Charset         =   0
      Weight          =   700
      Underline       =   0   'False
      Italic          =   0   'False
      Strikethrough   =   0   'False
   EndProperty
   ForeColor       =   &H00000080&
   Icon            =   "Form1.frx":0000
   MaxButton       =   0   'False
   MinButton       =   0   'False
   ScaleHeight     =   4980
   ScaleWidth      =   7680
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.PictureBox Picture2 
      BorderStyle     =   0  'Kein
      Height          =   3975
      Left            =   0
      ScaleHeight     =   3975
      ScaleWidth      =   7695
      TabIndex        =   9
      Top             =   0
      Visible         =   0   'False
      Width           =   7695
      Begin VB.ListBox lstFoundFiles 
         Height          =   3375
         ItemData        =   "Form1.frx":0442
         Left            =   0
         List            =   "Form1.frx":0444
         TabIndex        =   10
         Top             =   360
         Width           =   7695
      End
      Begin VB.Shape Shape1 
         Height          =   975
         Left            =   1680
         Top             =   2160
         Width           =   1455
      End
      Begin VB.Label lblCount 
         AutoSize        =   -1  'True
         Caption         =   "0"
         Height          =   195
         Left            =   2040
         TabIndex        =   12
         Top             =   120
         Width           =   120
      End
      Begin VB.Label lblfound 
         Caption         =   "&Gefundene Dateien:"
         Height          =   255
         Left            =   120
         TabIndex        =   11
         Top             =   120
         Width           =   1815
      End
   End
   Begin VB.CommandButton cmdExit 
      BackColor       =   &H00C0C0C0&
      Caption         =   "&Beenden"
      Height          =   720
      Left            =   1560
      TabIndex        =   1
      Top             =   4080
      Width           =   1200
   End
   Begin VB.CommandButton cmdSearch 
      BackColor       =   &H00C0C0C0&
      Caption         =   "&Suchen"
      Default         =   -1  'True
      Height          =   720
      Left            =   240
      TabIndex        =   0
      Top             =   4080
      Width           =   1200
   End
   Begin VB.PictureBox Picture1 
      BorderStyle     =   0  'Kein
      Height          =   3255
      Left            =   0
      ScaleHeight     =   3255
      ScaleWidth      =   5055
      TabIndex        =   2
      Top             =   0
      Width           =   5055
      Begin VB.DriveListBox drvList 
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   8.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   315
         Left            =   2400
         TabIndex        =   7
         Top             =   480
         Width           =   2655
      End
      Begin VB.TextBox txtSearchSpec 
         Height          =   285
         Left            =   2400
         TabIndex        =   4
         Text            =   "*.*"
         Top             =   120
         Width           =   2655
      End
      Begin VB.DirListBox dirList 
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   8.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   1890
         Left            =   2520
         TabIndex        =   6
         Top             =   1200
         Width           =   2535
      End
      Begin VB.FileListBox filList 
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   8.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   1845
         Left            =   0
         TabIndex        =   5
         Top             =   1200
         Width           =   2415
      End
      Begin VB.Label Label3 
         Caption         =   "Suchen in Ordner:"
         Height          =   255
         Left            =   2520
         TabIndex        =   14
         Top             =   960
         Width           =   1575
      End
      Begin VB.Label Label2 
         Caption         =   "Dateien im Ordner:"
         Height          =   255
         Left            =   0
         TabIndex        =   13
         Top             =   960
         Width           =   1695
      End
      Begin VB.Label Label1 
         Caption         =   "Suchen in Laufwerk:"
         Height          =   255
         Left            =   600
         TabIndex        =   8
         Top             =   480
         Width           =   1815
      End
      Begin VB.Label lblCriteria 
         Caption         =   "Suchen nach:"
         Height          =   255
         Left            =   600
         TabIndex        =   3
         Top             =   120
         Width           =   1335
      End
   End
End
Attribute VB_Name = "WinSeek"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit
Dim SearchFlag As Integer   ' Wird als Flag beim Abbrechen usw. verwendet.

Private Sub cmdExit_Click()
    If cmdExit.Caption = "&Beenden" Then
        End
    Else
        ' Wenn Abbrechen gewählt wurde,
        ' die Suche beenden.
        SearchFlag = False
    End If
End Sub

Private Sub cmdSearch_Click()
' Initialisieren für die Suche, dann
' Aurufen von DirDiver, um rekursive
' Suche durchzuführen.
Dim FirstPath As String, DirCount As Integer, NumFiles As Integer
Dim result As Integer
  ' Prüfen, was der Benutzer zuletzt
  ' durchgeführt hat.
    If cmdSearch.Caption = "&Neue Suche" Then
    ' Initialisieren und Beenden, falls
    ' gerade zurückgesetzt wurde.
        ResetSearch
        txtSearchSpec.SetFocus
        Exit Sub
    End If

    ' Aktualisieren von dirList.Path,
    ' wenn es nicht das momentan gewählte
    ' Verzeichnis ist. Andernfalls
    ' Durchführen der Suche.
    If dirList.Path <> dirList.List(dirList.ListIndex) Then
        dirList.Path = dirList.List(dirList.ListIndex)
        ' Verlassen der Routine, damit
        ' Benutzer vor der Suche die
        ' Liste ansehen kann.
        Exit Sub
    End If

    ' Fortfahren mit der Suche.
    Picture2.Move 0, 0
    Picture1.Visible = False
    Picture2.Visible = True

    cmdExit.Caption = "Abbrechen"

    filList.Pattern = txtSearchSpec.Text
    FirstPath = dirList.Path
    DirCount = dirList.ListCount

    ' Beginnen mit der rekursiven Suche
    ' in den Verzeichnissen. Zurücksetzen
    ' des globalen foundfiles-Indikators
    ' (Anzahl der gefundenen Dateien).
    NumFiles = 0
    result = DirDiver(FirstPath, DirCount, "")
    filList.Path = dirList.Path
    cmdSearch.Caption = "&Neue Suche"
    cmdSearch.SetFocus
    cmdExit.Caption = "&Beenden"
End Sub

Private Function DirDiver(NewPath As String, DirCount As Integer, BackUp As String) As Integer
' Rekursive Suche in NewPath und den
' darunterliegenden Verzeichnissen...
' NewPath wird in dieser Rekursion
' durchsucht. BackUp ist die Quelle
' dieser Rekursion. DirCount ist die
' Anzahl der Unterverzeichnisse in
' diesem Verzeichnis.
Static FirstErr As Integer
Dim DirsToPeek As Integer, AbandonSearch As Integer, ind As Integer
Dim OldPath As String, ThePath As String, entry As String
Dim retval As Integer
    SearchFlag = True           ' Festgelegt, damit der Benutzer unterbrechen kann.
    DirDiver = False            ' Wird auf TRUE gesetzt, wenn ein Fehler auftritt.
    retval = DoEvents()         ' Prüfen auf Ereignisse (z.B. Abbrechen durch den Benutzer).
    If SearchFlag = False Then
        DirDiver = True
        Exit Function
    End If
    On Local Error GoTo DirDriverHandler
    ' Wieviele Verzeichnisse befinden
    ' sich unter diesem?
    DirsToPeek = dirList.ListCount
    Do While DirsToPeek > 0 And SearchFlag = True
        ' Speichern des alten Pfads für
        ' die nächste Rekursion.
        OldPath = dirList.Path
        If dirList.ListCount > 0 Then
            ' Ermitteln der niedrigsten
            ' Verzeichnisebene.
            dirList.Path = dirList.List(DirsToPeek - 1)
            AbandonSearch = DirDiver((dirList.Path), DirCount%, OldPath)
        End If
        ' Wechseln in die nächsthöhere
        ' Verzeichnisebene.
        DirsToPeek = DirsToPeek - 1
        If AbandonSearch = True Then Exit Function
    Loop
    ' Aufrufen der Funktion zum Aufzählen
    ' der Dateien.
    If filList.ListCount Then
        ' Prüfen, ob Pfad länger als 3
        ' Zeichen ist.
        If Len(dirList.Path) <= 3 Then
            ' Auf der Stammverzeichnisebene
            ' unverändert lassen...
            ThePath = dirList.Path
        Else
            ' anderenfalls ein "\" vor dem
            ' Verzeichnisnamen einfügen.
            ThePath = dirList.Path + "\"
        End If
        ' Hinzufügen der passenden Dateien
        ' in diesem Verzeichnis zum Listenfeld.
        For ind = 0 To filList.ListCount - 1
            entry = ThePath + filList.List(ind)
            lstFoundFiles.AddItem entry
            lblCount.Caption = Str(Val(lblCount.Caption) + 1)
        Next ind
    End If
    ' Wechseln ins übergeordnete Verzeichnis,
    ' falls vorhanden.
    If BackUp <> "" Then
        dirList.Path = BackUp
    End If
    Exit Function
DirDriverHandler:
    If Err = 7 Then
        ' Falls kein freier Speicher mehr,
        ' Annahme, daß Listenfeld voll ist.
        ' Erstellen einer Mitteilung und
        ' Ändern des entsprechenden Flags.
        DirDiver = True
        MsgBox "Das Listenfeld ist voll. Die Suche wird abgebrochen."
        Exit Function           ' Hinweis: EXIT setzt ERR auf 0 zurück.
    Else
        ' Bei anderem Fehler entsprechende
        ' Fehlermeldung anzeigen und
        ' Programm beenden.
        MsgBox Error
        End
    End If
End Function

Private Sub DirList_Change()
    ' Aktualisieren des Datei-Listenfelds,
    ' so daß es mit dem Dir-Listenfeld
    ' übereinstimmt.
    filList.Path = dirList.Path
End Sub

Private Sub DirList_LostFocus()
    dirList.Path = dirList.List(dirList.ListIndex)
End Sub

Private Sub DrvList_Change()
    On Error GoTo DriveHandler
    dirList.Path = drvList.Drive
    Exit Sub

DriveHandler:
    drvList.Drive = dirList.Path
    Exit Sub
End Sub

Private Sub Form_Load()
    Picture2.Move 0, 0
    Picture2.Width = WinSeek.ScaleWidth
    Picture2.BackColor = WinSeek.BackColor
    lblCount.BackColor = WinSeek.BackColor
    lblCriteria.BackColor = WinSeek.BackColor
    lblfound.BackColor = WinSeek.BackColor
    Picture1.Move 0, 0
    Picture1.Width = WinSeek.ScaleWidth
    Picture1.BackColor = WinSeek.BackColor
End Sub

Private Sub Form_Unload(Cancel As Integer)
    End
End Sub

Private Sub ResetSearch()
    ' Neu Initialisieren vor dem Beginn
    ' einer neuen Suche.
    lstFoundFiles.Clear
    lblCount.Caption = 0
    SearchFlag = False                  ' Flag, ob Suche gerade durchgeführt wird
    Picture2.Visible = False
    cmdSearch.Caption = "&Suchen"
    cmdExit.Caption = "&Beenden"
    Picture1.Visible = True
    ' Zurücksetzen des Pfads.
    dirList.Path = CurDir: drvList.Drive = dirList.Path
End Sub

Private Sub txtSearchSpec_Change()
    ' Aktualisieren des Dateilistenfelds,
    ' falls Benutzer das Suchmuster ändert.
    filList.Pattern = txtSearchSpec.Text
End Sub

Private Sub txtSearchSpec_GotFocus()
    ' Markieren des aktuellen Eintrags.
    txtSearchSpec.SelStart = 0
    txtSearchSpec.SelLength = Len(txtSearchSpec.Text)
End Sub

