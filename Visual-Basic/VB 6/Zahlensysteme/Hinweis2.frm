VERSION 5.00
Begin VB.Form frm_Hinweis2 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Hinweis"
   ClientHeight    =   1620
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   3240
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   ScaleHeight     =   1620
   ScaleWidth      =   3240
   StartUpPosition =   1  'Fenstermitte
   Begin VB.CommandButton btn_Ok 
      Caption         =   "Ok"
      Height          =   615
      Left            =   840
      TabIndex        =   1
      Top             =   960
      Width           =   1455
   End
   Begin VB.Label lab_Hinweis 
      BackStyle       =   0  'Transparent
      Caption         =   "Sie müssen im Textfeld Zahlensystemsystem# eine Zahl eingeben."
      Height          =   675
      Left            =   120
      TabIndex        =   0
      Top             =   120
      Width           =   3000
   End
End
Attribute VB_Name = "frm_Hinweis2"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub btn_Ok_Click()

Unload frm_Hinweis2

End Sub
