VERSION 5.00
Begin VB.Form Form1 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Andis Stoppuhr Version 1.0 "
   ClientHeight    =   2565
   ClientLeft      =   3270
   ClientTop       =   2670
   ClientWidth     =   3495
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   ScaleHeight     =   2565
   ScaleWidth      =   3495
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.Timer tim_Grafik 
      Enabled         =   0   'False
      Interval        =   500
      Left            =   120
      Top             =   1560
   End
   Begin VB.Timer tim_Stoppuhr 
      Interval        =   10
      Left            =   3000
      Top             =   1440
   End
   Begin VB.CommandButton btn_Null 
      Caption         =   "&Reset"
      Height          =   615
      Left            =   2520
      TabIndex        =   3
      Tag             =   "Hier wird der Timer auf Null zurückgesetzt."
      Top             =   720
      Width           =   855
   End
   Begin VB.CommandButton btn_Stop 
      Caption         =   "S&top"
      Height          =   615
      Left            =   1320
      TabIndex        =   2
      Tag             =   "Hiermit wird der Timer gestoppt."
      Top             =   720
      Width           =   855
   End
   Begin VB.CommandButton btn_Start 
      Caption         =   "&Start"
      Height          =   615
      Left            =   120
      TabIndex        =   1
      Tag             =   "Hiermit wird die Stoppuhr gestartet. "
      Top             =   720
      Width           =   855
   End
   Begin VB.PictureBox pic_Ablauf 
      Height          =   375
      Left            =   120
      ScaleHeight     =   315
      ScaleWidth      =   3195
      TabIndex        =   0
      Top             =   120
      Width           =   3255
      Begin VB.Label lab_Grafik3 
         Appearance      =   0  '2D
         BackColor       =   &H00800000&
         BorderStyle     =   1  'Fest Einfach
         ForeColor       =   &H00800000&
         Height          =   255
         Left            =   600
         TabIndex        =   8
         Top             =   30
         Visible         =   0   'False
         Width           =   255
      End
      Begin VB.Label lab_Grafik2 
         Appearance      =   0  '2D
         BackColor       =   &H00800000&
         BorderStyle     =   1  'Fest Einfach
         ForeColor       =   &H00800000&
         Height          =   255
         Left            =   315
         TabIndex        =   7
         Top             =   30
         Visible         =   0   'False
         Width           =   255
      End
      Begin VB.Label lab_Grafik1 
         Appearance      =   0  '2D
         BackColor       =   &H00800000&
         BorderStyle     =   1  'Fest Einfach
         ForeColor       =   &H00800000&
         Height          =   255
         Left            =   30
         TabIndex        =   6
         Top             =   30
         Visible         =   0   'False
         Width           =   255
      End
   End
   Begin VB.Label lab_Status 
      Alignment       =   2  'Zentriert
      Appearance      =   0  '2D
      BackColor       =   &H80000004&
      BackStyle       =   0  'Transparent
      BorderStyle     =   1  'Fest Einfach
      ForeColor       =   &H80000008&
      Height          =   375
      Left            =   0
      TabIndex        =   5
      Top             =   2280
      Width           =   3495
   End
   Begin VB.Label lab_Zeit 
      Alignment       =   2  'Zentriert
      BackStyle       =   0  'Transparent
      BorderStyle     =   1  'Fest Einfach
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   12
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   375
      Left            =   960
      TabIndex        =   4
      Top             =   1560
      Width           =   1575
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Dim Startzeit As Date
Dim Zeit As Date
Private Sub btn_Null_Click()
Startzeit = Time$
Zeit = Startzeit
lab_Zeit.Caption = Format(0, "Long Time")
End Sub
Private Sub btn_Null_MouseMove(Button As Integer, Shift As Integer, X As Single, Y As Single)
lab_Status.Caption = btn_Null.Tag
End Sub

Private Sub btn_Start_Click()
Zeit = Time$
Startzeit = Zeit - CDate(lab_Zeit.Caption)
tim_Stoppuhr.Enabled = True
tim_Grafik = True
End Sub

Private Sub btn_Start_MouseMove(Button As Integer, Shift As Integer, X As Single, Y As Single)
lab_Status.Caption = btn_Start.Tag
End Sub

Private Sub btn_Stop_Click()
tim_Stoppuhr.Enabled = False
End Sub

Private Sub btn_Stop_MouseMove(Button As Integer, Shift As Integer, X As Single, Y As Single)
lab_Status.Caption = btn_Stop.Tag
End Sub

Private Sub Form_Load()
lab_Zeit = Format(0, "Long Time")
Call btn_Null_Click
Call btn_Stop_Click
End Sub

Private Sub Form_MouseMove(Button As Integer, Shift As Integer, X As Single, Y As Single)
lab_Status.Caption = ""
End Sub

Private Sub Picture1_Click()

End Sub

Private Sub tim_Stoppuhr_Timer()
Zeit = Time$
lab_Zeit.Caption = Format(Zeit - Startzeit, "Long Time")
DoEvents
End Sub


