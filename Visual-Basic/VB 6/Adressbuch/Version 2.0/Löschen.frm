VERSION 5.00
Begin VB.Form frm_Loeschen 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Alles Löschen?"
   ClientHeight    =   1620
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   3210
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   ScaleHeight     =   1620
   ScaleWidth      =   3210
   StartUpPosition =   1  'Fenstermitte
   Begin VB.CommandButton btn_Nein 
      Caption         =   "&Nein"
      Height          =   375
      Left            =   1680
      TabIndex        =   1
      Top             =   1200
      Width           =   1215
   End
   Begin VB.CommandButton btn_Ja 
      Caption         =   "&Ja"
      Height          =   375
      Left            =   240
      TabIndex        =   2
      Top             =   1200
      Width           =   1215
   End
   Begin VB.Label Label1 
      BackStyle       =   0  'Transparent
      Caption         =   "Wollen sie wirklich alle Einträge unwiderruflich löschen?"
      Height          =   375
      Left            =   120
      TabIndex        =   0
      Top             =   0
      Width           =   2895
   End
End
Attribute VB_Name = "frm_Loeschen"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub btn_Ja_Click()

Kill "C:\Programme\Andis Adressbuch 2.0\*.AAE"
Kill "C:\Programme\Andis Adressbuch 2.0\*.bmp"
Kill "C:\Programme\Andis Adressbuch 2.0\*.jpg"
Kill "C:\Programme\Andis Adressbuch 2.0\*.gif"
Kill "C:\Programme\Andis Adressbuch 2.0\Counter.tmp"
Kill "C:\Programme\Andis Adressbuch 2.0\Counter1.tmp"

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\empty.AAE" For Output As DKanal
Close

Unload frm_Loeschen

End Sub

Private Sub btn_Nein_Click()

Unload frm_Loeschen

End Sub
