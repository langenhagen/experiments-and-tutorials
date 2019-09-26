VERSION 5.00
Begin VB.Form frm_aendern 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Eintrag ‰ndern"
   ClientHeight    =   5280
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   5655
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   Moveable        =   0   'False
   ScaleHeight     =   5280
   ScaleWidth      =   5655
   StartUpPosition =   1  'Fenstermitte
   Begin VB.CommandButton btn_altBild 
      Caption         =   "Bild laden..."
      Height          =   375
      Left            =   4080
      TabIndex        =   20
      Top             =   720
      Width           =   1095
   End
   Begin VB.TextBox txt_altGeburtstag 
      Height          =   375
      Left            =   2520
      TabIndex        =   10
      ToolTipText     =   "Geburtstag"
      Top             =   3720
      Width           =   2775
   End
   Begin VB.TextBox txt_altWeb 
      Height          =   375
      Left            =   2520
      TabIndex        =   9
      ToolTipText     =   "Homepage"
      Top             =   3360
      Width           =   2775
   End
   Begin VB.TextBox txt_altEmail 
      Height          =   375
      Left            =   2520
      TabIndex        =   8
      ToolTipText     =   "E-Mail Adresse"
      Top             =   3000
      Width           =   2775
   End
   Begin VB.TextBox txt_altHandy 
      Height          =   375
      Left            =   2520
      TabIndex        =   7
      ToolTipText     =   "Handy-Nummer"
      Top             =   2640
      Width           =   2775
   End
   Begin VB.TextBox txt_altTelnummer 
      Height          =   375
      Left            =   2520
      TabIndex        =   6
      ToolTipText     =   "Telefonnummer"
      Top             =   2280
      Width           =   2775
   End
   Begin VB.TextBox txt_altOrt 
      Height          =   375
      Left            =   2520
      TabIndex        =   5
      ToolTipText     =   "Ort"
      Top             =   1920
      Width           =   2775
   End
   Begin VB.TextBox txt_altStraﬂe 
      Height          =   375
      Left            =   2520
      TabIndex        =   4
      ToolTipText     =   "Straﬂe"
      Top             =   1560
      Width           =   2775
   End
   Begin VB.TextBox txt_altName 
      Height          =   375
      Left            =   2520
      TabIndex        =   3
      ToolTipText     =   "Name"
      Top             =   1200
      Width           =   2775
   End
   Begin VB.CommandButton btn_altSpeichern 
      Caption         =   "&Speichern"
      Height          =   375
      Left            =   240
      TabIndex        =   2
      ToolTipText     =   "Beendet den Vorgang und speichert die Daten"
      Top             =   4800
      Width           =   1695
   End
   Begin VB.CommandButton btn_altAbbruch 
      Caption         =   "&Abbrechen"
      Height          =   375
      Left            =   3480
      TabIndex        =   1
      ToolTipText     =   "Bricht den Vorgang ab"
      Top             =   4800
      Width           =   1695
   End
   Begin VB.Label lab_tempName 
      BackStyle       =   0  'Transparent
      Height          =   375
      Left            =   2160
      TabIndex        =   19
      Top             =   4320
      Visible         =   0   'False
      Width           =   735
   End
   Begin VB.Line Line11 
      X1              =   120
      X2              =   120
      Y1              =   4080
      Y2              =   1200
   End
   Begin VB.Label lab_altGeburtstag 
      BackStyle       =   0  'Transparent
      Caption         =   "Geburtstag:"
      Height          =   255
      Left            =   240
      TabIndex        =   18
      Top             =   3840
      Width           =   2295
   End
   Begin VB.Label lab_altweb 
      BackStyle       =   0  'Transparent
      Caption         =   "Homepage:"
      Height          =   255
      Left            =   240
      TabIndex        =   17
      Top             =   3480
      Width           =   2295
   End
   Begin VB.Label lab_altEmail 
      BackStyle       =   0  'Transparent
      Caption         =   "E-Mail:"
      Height          =   255
      Left            =   240
      TabIndex        =   16
      Top             =   3120
      Width           =   2295
   End
   Begin VB.Label lab_altHandy 
      BackStyle       =   0  'Transparent
      Caption         =   "Handy-Nummer:"
      Height          =   255
      Left            =   240
      TabIndex        =   15
      Top             =   2760
      Width           =   2295
   End
   Begin VB.Label lab_altTelnummer 
      BackStyle       =   0  'Transparent
      Caption         =   "Telefonnummer:"
      Height          =   255
      Left            =   240
      TabIndex        =   14
      Top             =   2400
      Width           =   2295
   End
   Begin VB.Label lab_altOrt 
      BackStyle       =   0  'Transparent
      Caption         =   "Ort:"
      Height          =   255
      Left            =   240
      TabIndex        =   13
      Top             =   2040
      Width           =   2295
   End
   Begin VB.Label lab_altStraﬂe 
      BackStyle       =   0  'Transparent
      Caption         =   "Straﬂe:"
      Height          =   255
      Left            =   240
      TabIndex        =   12
      Top             =   1680
      Width           =   2295
   End
   Begin VB.Line Line9 
      X1              =   120
      X2              =   2520
      Y1              =   3720
      Y2              =   3720
   End
   Begin VB.Line Line8 
      X1              =   120
      X2              =   2520
      Y1              =   3360
      Y2              =   3360
   End
   Begin VB.Line Line7 
      X1              =   120
      X2              =   2520
      Y1              =   3000
      Y2              =   3000
   End
   Begin VB.Line Line6 
      X1              =   120
      X2              =   2520
      Y1              =   2640
      Y2              =   2640
   End
   Begin VB.Line Line5 
      X1              =   120
      X2              =   2520
      Y1              =   2280
      Y2              =   2280
   End
   Begin VB.Line Line4 
      X1              =   120
      X2              =   2520
      Y1              =   1920
      Y2              =   1920
   End
   Begin VB.Line Line3 
      X1              =   120
      X2              =   2520
      Y1              =   1200
      Y2              =   1200
   End
   Begin VB.Line Line2 
      X1              =   120
      X2              =   2520
      Y1              =   1560
      Y2              =   1560
   End
   Begin VB.Line Line1 
      X1              =   2520
      X2              =   2520
      Y1              =   1200
      Y2              =   4080
   End
   Begin VB.Label lab_altName 
      BackStyle       =   0  'Transparent
      Caption         =   "Name:"
      Height          =   255
      Left            =   240
      TabIndex        =   11
      Top             =   1320
      Width           =   2295
   End
   Begin VB.Line Line10 
      X1              =   120
      X2              =   2520
      Y1              =   4080
      Y2              =   4080
   End
   Begin VB.Label lab_altUeberschrift 
      BackStyle       =   0  'Transparent
      Caption         =   "ƒndern sie die Daten des Eintrages."
      Height          =   255
      Left            =   120
      TabIndex        =   0
      Top             =   120
      Width           =   2655
   End
End
Attribute VB_Name = "frm_aendern"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub btn_neuSpeichern_Click()

End Sub

Private Sub btn_altAbbruch_Click()

Unload frm_aendern

End Sub

Private Sub btn_altSpeichern_Click()

Kill "C:\Programme\Andis Adressbuch 2.0\" & lab_tempName.Caption & ".AAE"

On Error GoTo BildPfadHilfe

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\temp1.AAO" For Input As DKanal
Line Input #DKanal, varBildPfad
Close

GoTo BildPfadNormal

BildPfadHilfe:
varBildPfad = "kein Bild"

BildPfadNormal:

DKanal10 = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\" & txt_altName.Text & ".AAE" For Output As DKanal10
Print #DKanal, "Name"
Print #DKanal, "" & txt_altName.Text
Print #DKanal, ""
Print #DKanal, "Straﬂe"
Print #DKanal, "" & txt_altStraﬂe.Text
Print #DKanal, ""
Print #DKanal, "Ort"
Print #DKanal, "" & txt_altOrt.Text
Print #DKanal, ""
Print #DKanal, "TelNummer"
Print #DKanal, "" & txt_altTelnummer.Text
Print #DKanal, ""
Print #DKanal, "Handy"
Print #DKanal, "" & txt_altHandy.Text
Print #DKanal, ""
Print #DKanal, "E-Mail"
Print #DKanal, "" & txt_altEmail.Text
Print #DKanal, ""
Print #DKanal, "Website"
Print #DKanal, "" & txt_altWeb.Text
Print #DKanal, ""
Print #DKanal, "Geburtstag"
Print #DKanal, "" & txt_altGeburtstag.Text
Print #DKanal, "" & varBildPfad
Close

Kill "C:\Programme\Andis Adressbuch 2.0\temp.tmp"

Unload frm_aendern

End Sub

Private Sub Form_Load()

Kill "C:\Programme\Andis Adressbuch 2.0\temp.tmp"

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\temp.tmp" For Input As DKanal
Line Input #DKanal, varName
Close

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\" & varName & ".AAE" For Input As DKanal
Line Input #DKanal, varStraﬂe
Line Input #DKanal, varStraﬂe
Line Input #DKanal, varStraﬂe
Line Input #DKanal, varStraﬂe
Line Input #DKanal, varStraﬂe
Line Input #DKanal, varOrt
Line Input #DKanal, varOrt
Line Input #DKanal, varOrt
Line Input #DKanal, varTelNummer
Line Input #DKanal, varTelNummer
Line Input #DKanal, varTelNummer
Line Input #DKanal, varHandy
Line Input #DKanal, varHandy
Line Input #DKanal, varHandy
Line Input #DKanal, varEmail
Line Input #DKanal, varEmail
Line Input #DKanal, varEmail
Line Input #DKanal, varWeb
Line Input #DKanal, varWeb
Line Input #DKanal, varWeb
Line Input #DKanal, varGeburtstag
Line Input #DKanal, varGeburtstag
Line Input #DKanal, varGeburtstag
Close

txt_altName.Text = varName
txt_altStraﬂe.Text = varStraﬂe
txt_altOrt.Text = varOrt
txt_altTelnummer.Text = varTelNummer
txt_altHandy.Text = varHandy
txt_altEmail.Text = varEmail
txt_altWeb.Text = varWeb
txt_altGeburtstag.Text = varGeburtstag

lab_tempName.Caption = varName

End Sub
