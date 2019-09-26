VERSION 5.00
Begin VB.Form frm_Optionen 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Optionen von Andis Adressbuch V.2.0"
   ClientHeight    =   4725
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   6165
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   Moveable        =   0   'False
   ScaleHeight     =   4725
   ScaleWidth      =   6165
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.Frame Frame1 
      Caption         =   "Anzahl der gesamten Einträge"
      Height          =   2055
      Left            =   3720
      TabIndex        =   8
      Top             =   360
      Width           =   2415
      Begin VB.Label lab_Eintraege 
         BackStyle       =   0  'Transparent
         Caption         =   "Einträge"
         Height          =   255
         Left            =   720
         TabIndex        =   10
         Top             =   480
         Width           =   1095
      End
      Begin VB.Label lab_AnzahlEintraege 
         Alignment       =   1  'Rechts
         BackStyle       =   0  'Transparent
         Caption         =   "0"
         Height          =   255
         Left            =   120
         TabIndex        =   9
         Top             =   480
         Width           =   495
      End
   End
   Begin VB.CommandButton btn_Abbruch 
      Caption         =   "&Abbruch"
      Height          =   375
      Left            =   1680
      TabIndex        =   7
      ToolTipText     =   "Schließt das Optionen-Menü"
      Top             =   4320
      Width           =   1455
   End
   Begin VB.CommandButton btn_Ok 
      Caption         =   "&Ok"
      Height          =   375
      Left            =   120
      TabIndex        =   6
      ToolTipText     =   "Schließt das Optionen-Menü und speichert die Änderungen"
      Top             =   4320
      Width           =   1455
   End
   Begin VB.Frame frame_Geburtstage 
      Caption         =   "Informieren bei Geburtstagen"
      Height          =   2055
      Left            =   120
      TabIndex        =   1
      Top             =   360
      Width           =   3495
      Begin VB.HScrollBar scroll_AnzahlTage 
         Height          =   255
         Left            =   120
         Max             =   14
         TabIndex        =   5
         Top             =   1080
         Width           =   3255
      End
      Begin VB.TextBox txt_AnzahlTage 
         Alignment       =   1  'Rechts
         BeginProperty DataFormat 
            Type            =   1
            Format          =   "0"
            HaveTrueFalseNull=   0
            FirstDayOfWeek  =   0
            FirstWeekOfYear =   0
            LCID            =   1031
            SubFormatType   =   1
         EndProperty
         Height          =   285
         Left            =   120
         TabIndex        =   4
         Text            =   "0"
         ToolTipText     =   "Tage vor dem Geburtstag, an denen man informiert wird"
         Top             =   720
         Width           =   375
      End
      Begin VB.CheckBox check_Informieren 
         Caption         =   "&Informieren bei Geburtstagen"
         Height          =   375
         Left            =   120
         TabIndex        =   2
         ToolTipText     =   "Gibt an, ob man bei bevorstehenden Geburtstagen informiert werden soll"
         Top             =   240
         Width           =   2895
      End
      Begin VB.Label lab_AnzahlTage 
         BackStyle       =   0  'Transparent
         Caption         =   "Tage vor einem Geburtstag informieren"
         Height          =   285
         Left            =   600
         TabIndex        =   3
         Top             =   720
         Width           =   2895
      End
   End
   Begin VB.CommandButton btn_AllesLöschen 
      Caption         =   "Alle Adressen &Löschen"
      Height          =   495
      Left            =   3840
      TabIndex        =   0
      ToolTipText     =   "Löscht alle Adressen aus dem Speicher"
      Top             =   4080
      Width           =   1935
   End
End
Attribute VB_Name = "frm_Optionen"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub btn_Abbruch_Click()

Unload frm_Optionen

End Sub

Private Sub btn_AllesLöschen_Click()

frm_Loeschen.Show 1

End Sub

Private Sub btn_Ok_Click()

Dim varAnzahlTage As Byte
Dim varInfo As Byte

varInfo = check_Informieren.Value
varAnzahlTage = txt_AnzahlTage.Text

If varInfo = 0 Then varAnzahlTage = 0

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Options.AAO" For Output As DKanal
Print #DKanal, "" & varInfo
Print #DKanal, "" & varAnzahlTage
Close

Unload frm_Optionen

End Sub

Private Sub check_Informieren_Click()


If check_Informieren.Value = 0 Then txt_AnzahlTage.Enabled = False
If check_Informieren.Value = 0 Then scroll_AnzahlTage.Enabled = False

If check_Informieren.Value = 1 Then txt_AnzahlTage.Enabled = True
If check_Informieren.Value = 1 Then scroll_AnzahlTage.Enabled = True

End Sub

Private Sub Form_Load()

On Error GoTo AnzahlEintraegeFehler

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Counter1.AAO" For Input As DKanal
Line Input #DKanal, varAnzahlEintraege
Close

GoTo AnzahlEintraegeNormal

AnzahlEintraegeFehler:
Close
lab_AnzahlEintraege.Caption = 0

AnzahlEintraegeNormal:

lab_AnzahlEintraege.Caption = varAnzahlEintraege

If lab_AnzahlEintraege.Caption = 1 Then lab_Eintraege.Caption = "Eintrag"

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Options.AAO" For Input As DKanal
Line Input #DKanal, varInfo
Line Input #DKanal, varAnzahlTage
Close

check_Informieren.Value = varInfo
scroll_AnzahlTage.Value = varAnzahlTage

If check_Informieren.Value = 0 Then txt_AnzahlTage.Enabled = False
If check_Informieren.Value = 0 Then scroll_AnzahlTage.Enabled = False

If check_Informieren.Value = 1 Then txt_AnzahlTage.Enabled = True
If check_Informieren.Value = 1 Then scroll_AnzahlTage.Enabled = True

End Sub

Private Sub lab_AnzahlEinträge_Click()

End Sub

Private Sub scroll_AnzahlTage_Change()

txt_AnzahlTage = scroll_AnzahlTage.Value

End Sub

Private Sub txt_AnzahlTage_Change()


If txt_AnzahlTage.Text > 14 Then txt_AnzahlTage.Text = 14
If txt_AnzahlTage.Text < 0 Then txt_AnzahlTage.Text = 0
scroll_AnzahlTage.Value = txt_AnzahlTage.Text

End Sub
