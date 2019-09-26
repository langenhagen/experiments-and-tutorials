VERSION 5.00
Begin VB.Form Form1 
   BackColor       =   &H00000000&
   BorderStyle     =   0  'Kein
   Caption         =   "Form1"
   ClientHeight    =   5895
   ClientLeft      =   2925
   ClientTop       =   2400
   ClientWidth     =   7425
   LinkTopic       =   "Form1"
   MouseIcon       =   "Form1.frx":0000
   ScaleHeight     =   5895
   ScaleWidth      =   7425
   ShowInTaskbar   =   0   'False
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.CommandButton Command3 
      Appearance      =   0  '2D
      BackColor       =   &H00000000&
      Default         =   -1  'True
      Height          =   255
      Left            =   120
      MaskColor       =   &H00000000&
      Picture         =   "Form1.frx":044A
      Style           =   1  'Grafisch
      TabIndex        =   4
      Top             =   5520
      Width           =   7215
   End
   Begin VB.CommandButton Command2 
      Appearance      =   0  '2D
      BackColor       =   &H00000000&
      Height          =   495
      Left            =   6480
      Picture         =   "Form1.frx":148C
      Style           =   1  'Grafisch
      TabIndex        =   3
      Top             =   0
      Width           =   495
   End
   Begin VB.CommandButton Command1 
      Appearance      =   0  '2D
      BackColor       =   &H00000000&
      Cancel          =   -1  'True
      Height          =   495
      Left            =   6960
      Picture         =   "Form1.frx":1C3A
      Style           =   1  'Grafisch
      TabIndex        =   2
      Top             =   0
      Width           =   495
   End
   Begin VB.TextBox Text1 
      Appearance      =   0  '2D
      BackColor       =   &H00000000&
      BorderStyle     =   0  'Kein
      BeginProperty Font 
         Name            =   "Fixedsys"
         Size            =   9
         Charset         =   0
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H00FFFFFF&
      Height          =   2895
      Left            =   120
      MouseIcon       =   "Form1.frx":23E8
      MousePointer    =   99  'Benutzerdefiniert
      MultiLine       =   -1  'True
      TabIndex        =   1
      Top             =   1560
      Width           =   6015
   End
   Begin VB.PictureBox Picture1 
      Appearance      =   0  '2D
      BackColor       =   &H80000001&
      BorderStyle     =   0  'Kein
      ForeColor       =   &H80000008&
      Height          =   1335
      Left            =   0
      MouseIcon       =   "Form1.frx":2832
      Picture         =   "Form1.frx":2C7C
      ScaleHeight     =   1335
      ScaleWidth      =   3615
      TabIndex        =   0
      Top             =   0
      Width           =   3615
   End
   Begin VB.Label Label1 
      Appearance      =   0  '2D
      BackColor       =   &H80000005&
      BackStyle       =   0  'Transparent
      Caption         =   "Programmierung,Grafiken,Tests: Andreas Langenhagen"
      ForeColor       =   &H80000005&
      Height          =   495
      Left            =   3720
      TabIndex        =   5
      Top             =   480
      Visible         =   0   'False
      Width           =   2655
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub Command1_Click()
Unload Me
End Sub

Private Sub Command3_Click()
If Text1.Text = "help" Then Text1.Text = "Wozu brauchen sie Hilfe?"
If Text1.Text = "exit" Then Unload Me
End Sub

Private Sub Label1_DblClick()
Label1.Visible = False
End Sub


Private Sub Picture1_DblClick()
Label1.Visible = True
End Sub


