VERSION 5.00
Begin VB.Form frm_Neu 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Neuen Eintrag hinzuf¸gen"
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
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.CommandButton btn_neuBild 
      Caption         =   "Bild laden..."
      Height          =   375
      Left            =   4080
      TabIndex        =   19
      Top             =   720
      Width           =   1095
   End
   Begin VB.CommandButton btn_neuAbbruch 
      Caption         =   "&Abbrechen"
      Height          =   375
      Left            =   3480
      TabIndex        =   11
      ToolTipText     =   "Bricht den Vorgang ab"
      Top             =   4800
      Width           =   1695
   End
   Begin VB.CommandButton btn_neuSpeichern 
      Caption         =   "&Speichern"
      Height          =   375
      Left            =   240
      TabIndex        =   10
      ToolTipText     =   "Beendet den Vorgang und speichert die Daten"
      Top             =   4800
      Width           =   1695
   End
   Begin VB.TextBox txt_neuName 
      Height          =   375
      Left            =   2520
      TabIndex        =   1
      ToolTipText     =   "Name"
      Top             =   1200
      Width           =   2775
   End
   Begin VB.TextBox txt_neuStraﬂe 
      Height          =   375
      Left            =   2520
      TabIndex        =   2
      ToolTipText     =   "Straﬂe"
      Top             =   1560
      Width           =   2775
   End
   Begin VB.TextBox txt_neuOrt 
      Height          =   375
      Left            =   2520
      TabIndex        =   3
      ToolTipText     =   "Ort"
      Top             =   1920
      Width           =   2775
   End
   Begin VB.TextBox txt_neuTelnummer 
      Height          =   375
      Left            =   2520
      TabIndex        =   4
      ToolTipText     =   "Telefonnummer"
      Top             =   2280
      Width           =   2775
   End
   Begin VB.TextBox txt_neuHandy 
      Height          =   375
      Left            =   2520
      TabIndex        =   5
      ToolTipText     =   "Handy-Nummer"
      Top             =   2640
      Width           =   2775
   End
   Begin VB.TextBox txt_neuEmail 
      Height          =   375
      Left            =   2520
      TabIndex        =   6
      ToolTipText     =   "E-Mail Adresse"
      Top             =   3000
      Width           =   2775
   End
   Begin VB.TextBox txt_neuWeb 
      Height          =   375
      Left            =   2520
      TabIndex        =   7
      ToolTipText     =   "Homepage"
      Top             =   3360
      Width           =   2775
   End
   Begin VB.TextBox txt_neuGeburtstag 
      Height          =   375
      Left            =   2520
      TabIndex        =   8
      ToolTipText     =   "Geburtstag"
      Top             =   3720
      Width           =   2775
   End
   Begin VB.Line Line10 
      X1              =   120
      X2              =   2520
      Y1              =   4080
      Y2              =   4080
   End
   Begin VB.Label lab_neuName 
      BackStyle       =   0  'Transparent
      Caption         =   "Name:"
      Height          =   255
      Left            =   240
      TabIndex        =   18
      Top             =   1320
      Width           =   2295
   End
   Begin VB.Line Line1 
      X1              =   2520
      X2              =   2520
      Y1              =   1200
      Y2              =   4080
   End
   Begin VB.Line Line2 
      X1              =   120
      X2              =   2520
      Y1              =   1560
      Y2              =   1560
   End
   Begin VB.Line Line3 
      X1              =   120
      X2              =   2520
      Y1              =   1200
      Y2              =   1200
   End
   Begin VB.Line Line4 
      X1              =   120
      X2              =   2520
      Y1              =   1920
      Y2              =   1920
   End
   Begin VB.Line Line5 
      X1              =   120
      X2              =   2520
      Y1              =   2280
      Y2              =   2280
   End
   Begin VB.Line Line6 
      X1              =   120
      X2              =   2520
      Y1              =   2640
      Y2              =   2640
   End
   Begin VB.Line Line7 
      X1              =   120
      X2              =   2520
      Y1              =   3000
      Y2              =   3000
   End
   Begin VB.Line Line8 
      X1              =   120
      X2              =   2520
      Y1              =   3360
      Y2              =   3360
   End
   Begin VB.Line Line9 
      X1              =   120
      X2              =   2520
      Y1              =   3720
      Y2              =   3720
   End
   Begin VB.Label lab_neuStraﬂe 
      BackStyle       =   0  'Transparent
      Caption         =   "Straﬂe:"
      Height          =   255
      Left            =   240
      TabIndex        =   17
      Top             =   1680
      Width           =   2295
   End
   Begin VB.Label lab_neuOrt 
      BackStyle       =   0  'Transparent
      Caption         =   "Ort:"
      Height          =   255
      Left            =   240
      TabIndex        =   16
      Top             =   2040
      Width           =   2295
   End
   Begin VB.Label lab_neuTelnummer 
      BackStyle       =   0  'Transparent
      Caption         =   "Telefonnummer:"
      Height          =   255
      Left            =   240
      TabIndex        =   15
      Top             =   2400
      Width           =   2295
   End
   Begin VB.Label lab_neuHandy 
      BackStyle       =   0  'Transparent
      Caption         =   "Handy-Nummer:"
      Height          =   255
      Left            =   240
      TabIndex        =   14
      Top             =   2760
      Width           =   2295
   End
   Begin VB.Label lab_neuEmail 
      BackStyle       =   0  'Transparent
      Caption         =   "E-Mail:"
      Height          =   255
      Left            =   240
      TabIndex        =   13
      Top             =   3120
      Width           =   2295
   End
   Begin VB.Label lab_neuweb 
      BackStyle       =   0  'Transparent
      Caption         =   "Homepage:"
      Height          =   255
      Left            =   240
      TabIndex        =   12
      Top             =   3480
      Width           =   2295
   End
   Begin VB.Label lab_neuGeburtstag 
      BackStyle       =   0  'Transparent
      Caption         =   "Geburtstag:"
      Height          =   255
      Left            =   240
      TabIndex        =   9
      Top             =   3840
      Width           =   2295
   End
   Begin VB.Line Line11 
      X1              =   120
      X2              =   120
      Y1              =   4080
      Y2              =   1200
   End
   Begin VB.Label lab_neuUeberschrift 
      BackStyle       =   0  'Transparent
      Caption         =   "Neuen Eintrag erstellen."
      Height          =   255
      Left            =   120
      TabIndex        =   0
      Top             =   120
      Width           =   2175
   End
End
Attribute VB_Name = "frm_Neu"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub btn_neuAbbruch_Click()

Unload frm_Neu

End Sub

Private Sub btn_neuBild_Click()

frm_Bild.Show 1

End Sub

Private Sub btn_neuSpeichern_Click()

EintragWiederholen:

On Error GoTo EintragFehler

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Counter1.AAO" For Input As DKanal
Line Input #DKanal, varEintrag
Close

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Counter1.AAO" For Output As DKanal
Print #DKanal, varEintrag + 1
Close

GoTo EintragNormal

EintragFehler:

Close

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Counter1.AAO" For Output As DKanal
Print #DKanal, 0
Close

GoTo EintragWiederholen

EintragNormal:

On Error GoTo BildPfadHilfe

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\temp1.AAO" For Input As DKanal
Line Input #DKanal, varBildPfad
Close

GoTo BildPfadNormal

BildPfadHilfe:
varBildPfad = "kein Bild"

BildPfadNormal:

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\" & txt_neuName.Text & ".AAE" For Output As DKanal
Print #DKanal, "Name"
Print #DKanal, "" & txt_neuName.Text
Print #DKanal, ""
Print #DKanal, "Straﬂe"
Print #DKanal, "" & txt_neuStraﬂe.Text
Print #DKanal, ""
Print #DKanal, "Ort"
Print #DKanal, "" & txt_neuOrt.Text
Print #DKanal, ""
Print #DKanal, "TelNummer"
Print #DKanal, "" & txt_neuTelnummer.Text
Print #DKanal, ""
Print #DKanal, "Handy"
Print #DKanal, "" & txt_neuHandy.Text
Print #DKanal, ""
Print #DKanal, "E-Mail"
Print #DKanal, "" & txt_neuEmail.Text
Print #DKanal, ""
Print #DKanal, "Website"
Print #DKanal, "" & txt_neuWeb.Text
Print #DKanal, ""
Print #DKanal, "Geburtstag"
Print #DKanal, "" & txt_neuGeburtstag.Text
Print #DKanal, ""
Print #DKanal, "" & varBildPfad
Close


DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Names.AAO" For Output As DKanal
Print #DKanal, "empty"
Close

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Names.AAO" For Input As DKanal
Input #DKanal, varNamesDaten
Close

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Names.AAO" For Output As DKanal
Print #DKanal, "" & varNamesDaten
Print #DKanal, "" & txt_neuName.Text
Close

Unload frm_Neu

End Sub

