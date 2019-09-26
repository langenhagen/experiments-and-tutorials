VERSION 5.00
Begin VB.Form Form1 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Gummiballsimulation V. 1.0"
   ClientHeight    =   5940
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   6975
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   ScaleHeight     =   5940
   ScaleWidth      =   6975
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.Timer tim_Ball 
      Left            =   4200
      Top             =   5400
   End
   Begin VB.PictureBox pic_Ball 
      Height          =   5175
      Left            =   0
      ScaleHeight     =   5115
      ScaleWidth      =   6915
      TabIndex        =   2
      Top             =   0
      Width           =   6975
      Begin VB.Shape Shape1 
         BackColor       =   &H000000FF&
         BackStyle       =   1  'Undurchsichtig
         Height          =   855
         Left            =   3000
         Shape           =   3  'Kreis
         Top             =   2040
         Width           =   855
      End
   End
   Begin VB.CommandButton btn_Ende 
      Cancel          =   -1  'True
      Caption         =   "&Beenden"
      Height          =   615
      Left            =   4920
      TabIndex        =   1
      Top             =   5280
      Width           =   1935
   End
   Begin VB.CommandButton btn_Start 
      Caption         =   "&Start"
      Height          =   615
      Left            =   120
      TabIndex        =   0
      Top             =   5280
      Width           =   1935
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False

Private Sub btn_Ende_Click()
End
End Sub
