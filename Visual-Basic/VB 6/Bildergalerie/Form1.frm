VERSION 5.00
Begin VB.Form Form1 
   Caption         =   "Bildergalerie"
   ClientHeight    =   6135
   ClientLeft      =   60
   ClientTop       =   330
   ClientWidth     =   7500
   LinkTopic       =   "Form1"
   ScaleHeight     =   6135
   ScaleWidth      =   7500
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.CommandButton Command6 
      Caption         =   ">"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   18
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   495
      Left            =   1680
      TabIndex        =   6
      Top             =   2400
      Width           =   735
   End
   Begin VB.CommandButton Command5 
      Caption         =   "<"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   18
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   495
      Left            =   120
      TabIndex        =   5
      Top             =   2400
      Width           =   735
   End
   Begin VB.CommandButton Command4 
      Caption         =   "&Beenden"
      Height          =   735
      Left            =   5520
      TabIndex        =   4
      Top             =   5400
      Width           =   1935
   End
   Begin VB.CommandButton Command3 
      Caption         =   "&Löschen"
      Height          =   735
      Left            =   360
      TabIndex        =   3
      Top             =   5040
      Width           =   1935
   End
   Begin VB.CommandButton Command2 
      Caption         =   "&Hinzufügen"
      Height          =   735
      Left            =   360
      TabIndex        =   2
      Top             =   4200
      Width           =   1935
   End
   Begin VB.CommandButton Command1 
      Caption         =   "&Anschauen"
      Height          =   735
      Left            =   360
      TabIndex        =   1
      Top             =   3360
      Width           =   1935
   End
   Begin VB.ListBox List1 
      Height          =   2010
      Left            =   0
      TabIndex        =   0
      Top             =   120
      Width           =   2535
   End
   Begin VB.Image Image1 
      BorderStyle     =   1  'Fest Einfach
      Height          =   4095
      Left            =   2640
      Top             =   120
      Width           =   4815
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub Command4_Click()
Unload Me
End Sub
