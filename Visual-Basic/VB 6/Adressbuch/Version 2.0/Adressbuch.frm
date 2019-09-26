VERSION 5.00
Begin VB.Form frm_Adressbuch 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Andis Adressbuch V.2.0"
   ClientHeight    =   6570
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   7230
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   ScaleHeight     =   6570
   ScaleWidth      =   7230
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.CommandButton btn_Optionen 
      Caption         =   "&Optionen"
      Height          =   375
      Left            =   240
      TabIndex        =   14
      ToolTipText     =   "Ändern sie die Einstellungen des Programms"
      Top             =   6000
      Width           =   2295
   End
   Begin VB.CommandButton btn_drucken 
      Caption         =   "Eintrag &drucken"
      Height          =   375
      Left            =   240
      TabIndex        =   13
      ToolTipText     =   "Gewählten Eintrag drucken"
      Top             =   2520
      Width           =   2295
   End
   Begin VB.CommandButton btn_Neu 
      Caption         =   "&Neuer Eintrag"
      Height          =   375
      Left            =   240
      TabIndex        =   10
      ToolTipText     =   "Neuen Eintrag erstellen"
      Top             =   1080
      Width           =   2295
   End
   Begin VB.CommandButton btn_löschen 
      Caption         =   "Eintrag &löschen"
      Height          =   375
      Left            =   240
      TabIndex        =   11
      ToolTipText     =   "Gewählten Eintrag löschen"
      Top             =   1560
      Width           =   2295
   End
   Begin VB.CommandButton btn_ändern 
      Caption         =   "&Eintrag ändern"
      Height          =   375
      Left            =   240
      TabIndex        =   12
      ToolTipText     =   "Gewählten Eintrag ändern"
      Top             =   2040
      Width           =   2295
   End
   Begin VB.PictureBox pic_Foto 
      Height          =   2415
      Left            =   4920
      ScaleHeight     =   2355
      ScaleWidth      =   1875
      TabIndex        =   22
      ToolTipText     =   "Foto der Person"
      Top             =   120
      Width           =   1935
   End
   Begin VB.TextBox txt_Geburtstag 
      Height          =   375
      Left            =   2520
      TabIndex        =   9
      ToolTipText     =   "Geburtstag"
      Top             =   5520
      Width           =   2775
   End
   Begin VB.TextBox txt_Web 
      Height          =   375
      Left            =   2520
      TabIndex        =   8
      ToolTipText     =   "Homepage"
      Top             =   5160
      Width           =   2775
   End
   Begin VB.TextBox txt_Email 
      Height          =   375
      Left            =   2520
      TabIndex        =   7
      ToolTipText     =   "E-Mail Adresse"
      Top             =   4800
      Width           =   2775
   End
   Begin VB.TextBox txt_Handy 
      Height          =   375
      Left            =   2520
      TabIndex        =   6
      ToolTipText     =   "Handy-Nummer"
      Top             =   4440
      Width           =   2775
   End
   Begin VB.TextBox txt_Telnummer 
      Height          =   375
      Left            =   2520
      TabIndex        =   5
      ToolTipText     =   "Telefonnummer"
      Top             =   4080
      Width           =   2775
   End
   Begin VB.TextBox txt_Ort 
      Height          =   375
      Left            =   2520
      TabIndex        =   4
      ToolTipText     =   "Ort"
      Top             =   3720
      Width           =   2775
   End
   Begin VB.TextBox txt_Straße 
      Height          =   375
      Left            =   2520
      TabIndex        =   3
      ToolTipText     =   "Straße"
      Top             =   3360
      Width           =   2775
   End
   Begin VB.TextBox txt_Name 
      Height          =   375
      Left            =   2520
      TabIndex        =   2
      ToolTipText     =   "Name"
      Top             =   3000
      Width           =   2775
   End
   Begin VB.ComboBox cmb_Name 
      Height          =   315
      Left            =   240
      TabIndex        =   0
      Text            =   "Name der Person"
      ToolTipText     =   "Name der gesuchten Person hier eingeben oder auswählen"
      Top             =   240
      Width           =   3015
   End
   Begin VB.Line Line11 
      X1              =   120
      X2              =   120
      Y1              =   5880
      Y2              =   3000
   End
   Begin VB.Label lab_Geburtstag 
      BackStyle       =   0  'Transparent
      Caption         =   "Geburtstag:"
      Height          =   255
      Left            =   240
      TabIndex        =   21
      Top             =   5640
      Width           =   2295
   End
   Begin VB.Label lab_web 
      BackStyle       =   0  'Transparent
      Caption         =   "Homepage:"
      Height          =   255
      Left            =   240
      TabIndex        =   20
      Top             =   5280
      Width           =   2295
   End
   Begin VB.Label lab_Email 
      BackStyle       =   0  'Transparent
      Caption         =   "E-Mail:"
      Height          =   255
      Left            =   240
      TabIndex        =   19
      Top             =   4920
      Width           =   2295
   End
   Begin VB.Label lab_Handy 
      BackStyle       =   0  'Transparent
      Caption         =   "Handy-Nummer:"
      Height          =   255
      Left            =   240
      TabIndex        =   18
      Top             =   4560
      Width           =   2295
   End
   Begin VB.Label lab_Telnummer 
      BackStyle       =   0  'Transparent
      Caption         =   "Telefonnummer:"
      Height          =   255
      Left            =   240
      TabIndex        =   17
      Top             =   4200
      Width           =   2295
   End
   Begin VB.Label lab_Ort 
      BackStyle       =   0  'Transparent
      Caption         =   "Ort:"
      Height          =   255
      Left            =   240
      TabIndex        =   16
      Top             =   3840
      Width           =   2295
   End
   Begin VB.Label lab_Straße 
      BackStyle       =   0  'Transparent
      Caption         =   "Straße:"
      Height          =   255
      Left            =   240
      TabIndex        =   15
      Top             =   3480
      Width           =   2295
   End
   Begin VB.Line Line10 
      X1              =   120
      X2              =   2520
      Y1              =   5880
      Y2              =   5880
   End
   Begin VB.Line Line9 
      X1              =   120
      X2              =   2520
      Y1              =   5520
      Y2              =   5520
   End
   Begin VB.Line Line8 
      X1              =   120
      X2              =   2520
      Y1              =   5160
      Y2              =   5160
   End
   Begin VB.Line Line7 
      X1              =   120
      X2              =   2520
      Y1              =   4800
      Y2              =   4800
   End
   Begin VB.Line Line6 
      X1              =   120
      X2              =   2520
      Y1              =   4440
      Y2              =   4440
   End
   Begin VB.Line Line5 
      X1              =   120
      X2              =   2520
      Y1              =   4080
      Y2              =   4080
   End
   Begin VB.Line Line4 
      X1              =   120
      X2              =   2520
      Y1              =   3720
      Y2              =   3720
   End
   Begin VB.Line Line3 
      X1              =   120
      X2              =   2520
      Y1              =   3000
      Y2              =   3000
   End
   Begin VB.Line Line2 
      X1              =   120
      X2              =   2520
      Y1              =   3360
      Y2              =   3360
   End
   Begin VB.Line Line1 
      X1              =   2520
      X2              =   2520
      Y1              =   3000
      Y2              =   5880
   End
   Begin VB.Label lab_Name 
      BackStyle       =   0  'Transparent
      Caption         =   "Name:"
      Height          =   255
      Left            =   240
      TabIndex        =   1
      Top             =   3120
      Width           =   2295
   End
End
Attribute VB_Name = "frm_Adressbuch"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub btn_ändern_Click()
If txt_Name.Text <> "" Then
Dkanal7 = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\temp.tmp" For Output As Dkanal7
Print #Dkanal7, "" & txt_Name.Text
Close

frm_aendern.Show 1
End If
End Sub

Private Sub btn_löschen_Click()

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Counter1.AAO" For Input As DKanal
Line Input #DKanal, varAnzahlEintraege
Close

Kill "C:\Programme\Andis Adressbuch 2.0\" & txt_Name.Text & ".AAE"

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Counter1.AAO" For Output As DKanal
Print #DKanal, varAnzahlEintraege - 1
Close

End Sub

Private Sub btn_Neu_Click()

frm_Neu.Show 1

End Sub

Private Sub btn_Optionen_Click()

frm_Optionen.Show 1

End Sub

Private Sub Form_Load()

On Error GoTo OptionsErrorHilfe

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Options.AAO" For Input As DKanal
Line Input #DKanal, varInfo
Line Input #DKanal, varAnzahlTage

Close
GoTo Normalverfahren

OptionsErrorHilfe:

Close
DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Options.AAO" For Output As DKanal
Print #DKanal, 0
Print #DKanal, 0
Close

Normalverfahren:

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\empty.AAE" For Output As DKanal
Close

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\temp.tmp" For Output As DKanal
Close
Kill "C:\Programme\Andis Adressbuch 2.0\temp.tmp"

End Sub

