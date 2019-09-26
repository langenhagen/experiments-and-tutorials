VERSION 5.00
Begin VB.Form FormAdress 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Andis Adressbuch Adressbuch V 1.2"
   ClientHeight    =   4200
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   5595
   Icon            =   "Form1.frx":0000
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   ScaleHeight     =   4200
   ScaleWidth      =   5595
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.CommandButton Command1 
      Height          =   495
      Left            =   4560
      Picture         =   "Form1.frx":0442
      Style           =   1  'Grafisch
      TabIndex        =   17
      ToolTipText     =   "Zum Drucken der Adresse hier drauf drücken"
      Top             =   240
      Width           =   495
   End
   Begin VB.CommandButton LöschenButton 
      Caption         =   "&Löschen"
      Height          =   375
      Left            =   2640
      TabIndex        =   16
      ToolTipText     =   "Zum Löschen von Adressen klicken sie hier drauf"
      Top             =   3720
      Width           =   2055
   End
   Begin VB.TextBox TxtNamen 
      BackColor       =   &H0000FFFF&
      Height          =   285
      Left            =   360
      MouseIcon       =   "Form1.frx":0AFC
      TabIndex        =   15
      ToolTipText     =   "Geben sie hier den gesuchten Namen ein"
      Top             =   120
      Width           =   2655
   End
   Begin VB.CommandButton ButtonHinzu 
      Caption         =   "&Hinzufügen"
      Height          =   375
      Left            =   360
      TabIndex        =   14
      ToolTipText     =   "Zum Hinzufügen von Adressen klicken sie hier drauf"
      Top             =   3720
      Width           =   2055
   End
   Begin VB.TextBox TextWeb 
      Height          =   375
      Left            =   2400
      Locked          =   -1  'True
      TabIndex        =   13
      ToolTipText     =   "Websitenadresse"
      Top             =   3120
      Width           =   2775
   End
   Begin VB.TextBox TextHandy 
      Height          =   405
      Left            =   2400
      Locked          =   -1  'True
      TabIndex        =   12
      ToolTipText     =   "Handynummer"
      Top             =   2760
      Width           =   2775
   End
   Begin VB.TextBox TextMail 
      Height          =   375
      Left            =   2400
      Locked          =   -1  'True
      TabIndex        =   11
      ToolTipText     =   "E-Mail-Adresse"
      Top             =   2400
      Width           =   2775
   End
   Begin VB.TextBox TextTel 
      Height          =   375
      Left            =   2400
      Locked          =   -1  'True
      TabIndex        =   10
      ToolTipText     =   "Telefonnummer"
      Top             =   2040
      Width           =   2775
   End
   Begin VB.TextBox TextPLZ 
      Height          =   375
      Left            =   2400
      Locked          =   -1  'True
      TabIndex        =   9
      ToolTipText     =   "Postleitzahl"
      Top             =   1680
      Width           =   2775
   End
   Begin VB.TextBox TextOrt 
      Height          =   375
      Left            =   2400
      Locked          =   -1  'True
      TabIndex        =   8
      ToolTipText     =   "Ort"
      Top             =   1320
      Width           =   2775
   End
   Begin VB.TextBox TextStr 
      Height          =   375
      Left            =   2400
      Locked          =   -1  'True
      TabIndex        =   7
      ToolTipText     =   "Straße"
      Top             =   960
      Width           =   2775
   End
   Begin VB.Line Line9 
      X1              =   120
      X2              =   120
      Y1              =   3480
      Y2              =   960
   End
   Begin VB.Line Line8 
      X1              =   120
      X2              =   2400
      Y1              =   960
      Y2              =   960
   End
   Begin VB.Line Line7 
      X1              =   120
      X2              =   2400
      Y1              =   3480
      Y2              =   3480
   End
   Begin VB.Line Line6 
      X1              =   120
      X2              =   2400
      Y1              =   3120
      Y2              =   3120
   End
   Begin VB.Line Line5 
      X1              =   120
      X2              =   2400
      Y1              =   2760
      Y2              =   2760
   End
   Begin VB.Line Line4 
      X1              =   120
      X2              =   2400
      Y1              =   2400
      Y2              =   2400
   End
   Begin VB.Line Line3 
      X1              =   120
      X2              =   2400
      Y1              =   2040
      Y2              =   2040
   End
   Begin VB.Line Line2 
      X1              =   120
      X2              =   2400
      Y1              =   1680
      Y2              =   1680
   End
   Begin VB.Line Line1 
      Index           =   0
      X1              =   120
      X2              =   2400
      Y1              =   1320
      Y2              =   1320
   End
   Begin VB.Label LabelWeb 
      Caption         =   "Website:"
      Height          =   255
      Left            =   240
      TabIndex        =   6
      Top             =   3240
      Width           =   2055
   End
   Begin VB.Label LabelHandy 
      Caption         =   "Handy:"
      Height          =   255
      Left            =   240
      TabIndex        =   5
      Top             =   2880
      Width           =   2055
   End
   Begin VB.Label LabelMail 
      Caption         =   "E-Mail:"
      Height          =   255
      Left            =   240
      TabIndex        =   4
      Top             =   2520
      Width           =   2055
   End
   Begin VB.Label LabelTel 
      Caption         =   "Telefonnummer:"
      Height          =   255
      Left            =   240
      TabIndex        =   3
      Top             =   2160
      Width           =   2055
   End
   Begin VB.Label LabelPLZ 
      Caption         =   "Postleitzahl:"
      Height          =   255
      Left            =   240
      TabIndex        =   2
      Top             =   1800
      Width           =   2055
   End
   Begin VB.Label LabelOrt 
      Caption         =   "Ort:"
      Height          =   255
      Left            =   240
      TabIndex        =   1
      Top             =   1440
      Width           =   2055
   End
   Begin VB.Label LabelStr 
      Caption         =   "Straße:"
      Height          =   255
      Left            =   240
      TabIndex        =   0
      Top             =   1080
      Width           =   2055
   End
End
Attribute VB_Name = "FormAdress"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub TxtNamen_Change()

If TxtNamen = "Alexander Beyer" Then TextStr = "Bahnhofstraße 53"
If TxtNamen = "Alexander Beyer" Then TextOrt = "Rehfelde"
If TxtNamen = "Alexander Beyer" Then TextPLZ = "15345"
If TxtNamen = "Alexander Beyer" Then TextTel = "033435/75193"
If TxtNamen = "Alexander Beyer" Then TextMail = ""
If TxtNamen = "Alexander Beyer" Then TextHandy = ""
If TxtNamen = "Alexander Beyer" Then TextWeb = ""


If TxtNamen = "Fabian Pluskat" Then TextStr = "Im Winkel 8"
If TxtNamen = "Fabian Pluskat" Then TextOrt = "Rehfelde"
If TxtNamen = "Fabian Pluskat" Then TextPLZ = "15345"
If TxtNamen = "Fabian Pluskat" Then TextTel = "033435/76912 (76911)"
If TxtNamen = "Fabian Pluskat" Then TextMail = "Fabi01@T-Online.de"
If TxtNamen = "Fabian Pluskat" Then TextHandy = "01623332055"
If TxtNamen = "Fabian Pluskat" Then TextWeb = ""


If TxtNamen = "Paul Hiemer" Then TextStr = "Scharnhorststraße"
If TxtNamen = "Paul Hiemer" Then TextOrt = "Strausberg"
If TxtNamen = "Paul Hiemer" Then TextPLZ = "15344"
If TxtNamen = "Paul Hiemer" Then TextMail = "Volkmarhiemer@yahoo.de"
If TxtNamen = "Paul Hiemer" Then TextTel = "03341/420135"
If TxtNamen = "Paul Hiemer" Then TextHandy = "01731618301"
If TxtNamen = "Paul Hiemer" Then TextWeb = ""


If TxtNamen = "Josephine Mantke" Then TextStr = "Bahnhofstraße 44"
If TxtNamen = "Josephine Mantke" Then TextOrt = "Rehfelde"
If TxtNamen = "Josephine Mantke" Then TextPLZ = "15345"
If TxtNamen = "Josephine Mantke" Then TextMail = ""
If TxtNamen = "Josephine Mantke" Then TextTel = ""
If TxtNamen = "Josephine Mantke" Then TextHandy = ""
If TxtNamen = "Josephine Mantke" Then TextWeb = ""


If TxtNamen = "Eigene Adresse" Then TextStr = "Goethestraße 14"
If TxtNamen = "Eigene Adresse" Then TextOrt = "Rehfelde"
If TxtNamen = "Eigene Adresse" Then TextPLZ = "15345"
If TxtNamen = "Eigene Adresse" Then TextMail = "Anlangenhagen@aol.com"
If TxtNamen = "Eigene Adresse" Then TextTel = "033435/75842"
If TxtNamen = "Eigene Adresse" Then TextHandy = ""
If TxtNamen = "Eigene Adresse" Then TextWeb = ""


If TxtNamen = "Daniel Seifert" Then TextStr = ""
If TxtNamen = "Daniel Seifert" Then TextOrt = "Straußberg"
If TxtNamen = "Daniel Seifert" Then TextPLZ = "15344"
If TxtNamen = "Daniel Seifert" Then TextTel = "03341/472181"
If TxtNamen = "Daniel Seifert" Then TextMail = ""
If TxtNamen = "Daniel Seifert" Then TextHandy = ""
If TxtNamen = "Daniel Seifert" Then TextWeb = ""


If TxtNamen = "Franziska Thurm" Then TextStr = "Goethestraße 4a"
If TxtNamen = "Franziska Thurm" Then TextOrt = "Rehfelde"
If TxtNamen = "Franziska Thurm" Then TextPLZ = "15345"
If TxtNamen = "Franziska Thurm" Then TextTel = "033435/75914"
If TxtNamen = "Franziska Thurm" Then TextMail = "Franziska_thurm@yahoo.de"
If TxtNamen = "Franziska Thurm" Then TextHandy = ""
If TxtNamen = "Franziska Thurm" Then TextWeb = ""

If TxtNamen = "Sebastian Schenk" Then TextStr = ""
If TxtNamen = "Sebastian Schenk" Then TextOrt = "Buckow"
If TxtNamen = "Sebastian Schenk" Then TextPLZ = ""
If TxtNamen = "Sebastian Schenk" Then TextTel = "033433/57955"
If TxtNamen = "Sebastian Schenk" Then TextMail = ""
If TxtNamen = "Sebastian Schenk" Then TextHandy = ""
If TxtNamen = "Sebastian Schenk" Then TextWeb = ""

End Sub


