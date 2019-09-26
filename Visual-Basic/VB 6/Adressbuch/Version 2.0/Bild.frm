VERSION 5.00
Begin VB.Form frm_Bild 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Bild laden"
   ClientHeight    =   1980
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   4965
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   ScaleHeight     =   1980
   ScaleWidth      =   4965
   StartUpPosition =   1  'Fenstermitte
   Begin VB.HScrollBar HScroll1 
      Height          =   135
      Left            =   120
      Max             =   1
      TabIndex        =   0
      TabStop         =   0   'False
      Top             =   1125
      Width           =   4695
   End
   Begin VB.CommandButton btn_BildOk 
      Caption         =   "Ok"
      Default         =   -1  'True
      Height          =   375
      Left            =   120
      TabIndex        =   3
      Top             =   1560
      Width           =   1455
   End
   Begin VB.TextBox txt_BildPfad 
      Height          =   285
      Left            =   120
      TabIndex        =   1
      Text            =   "kein Bild"
      Top             =   840
      Width           =   4695
   End
   Begin VB.Label la_Bild_Üueberschrift 
      BackStyle       =   0  'Transparent
      Caption         =   $"Bild.frx":0000
      Height          =   855
      Left            =   120
      TabIndex        =   2
      Top             =   0
      Width           =   4815
   End
End
Attribute VB_Name = "frm_Bild"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False

Private Sub btn_BildOk_Click()

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Counter.AAO" For Input As DKanal
Line Input #DKanal, varBildZahl
Close

If txt_BildPfad.Text <> "kein Bild" Then

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Counter.AAO" For Output As DKanal
Print #DKanal, varBildZahl + 1
Close

Name txt_BildPfad.Text & ".bmp" As "C:\Programme\Andis Adressbuch 2.0\Picture" & varBildZahl + 1 & ".bmp"
End If

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\temp1.tmp" For Output As DKanal
Print #DKanal, txt_BildPfad.Text
Close

Unload frm_Bild

End Sub

Private Sub Form_Load()

ResumeBild:

On Error GoTo BildFehler

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Counter.AAO" For Input As DKanal
Line Input #DKanal, varBildZahl
Close

GoTo BildNormal

BildFehler:

Close

DKanal = FreeFile
Open "C:\Programme\Andis Adressbuch 2.0\Counter.AAO" For Output As DKanal
Print #DKanal, 0
Close

GoTo ResumeBild

BildNormal:

End Sub
