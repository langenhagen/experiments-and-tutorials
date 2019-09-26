VERSION 5.00
Begin VB.Form Form1 
   BackColor       =   &H00000000&
   BorderStyle     =   0  'Kein
   Caption         =   "Wilkommen!"
   ClientHeight    =   4755
   ClientLeft      =   0
   ClientTop       =   0
   ClientWidth     =   6570
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   ScaleHeight     =   4755
   ScaleWidth      =   6570
   ShowInTaskbar   =   0   'False
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.Timer Timer2 
      Interval        =   150
      Left            =   5400
      Top             =   4200
   End
   Begin VB.PictureBox Picture2 
      AutoSize        =   -1  'True
      BorderStyle     =   0  'Kein
      Height          =   135
      Left            =   120
      ScaleHeight     =   135
      ScaleWidth      =   135
      TabIndex        =   3
      Top             =   1080
      Width           =   135
   End
   Begin VB.Timer Timer1 
      Interval        =   1
      Left            =   4920
      Top             =   4200
   End
   Begin VB.PictureBox Picture1 
      AutoSize        =   -1  'True
      Height          =   675
      Left            =   5640
      Picture         =   "Form1.frx":0000
      ScaleHeight     =   615
      ScaleWidth      =   615
      TabIndex        =   1
      Top             =   3840
      Width           =   675
   End
   Begin VB.Image Image1 
      Height          =   105
      Left            =   5520
      Picture         =   "Form1.frx":03B3
      Top             =   4560
      Width           =   225
   End
   Begin VB.Label Label3 
      BackStyle       =   0  'Transparent
      Caption         =   "Das Computersystem"
      ForeColor       =   &H000000FF&
      Height          =   255
      Left            =   3360
      TabIndex        =   4
      Top             =   2040
      Width           =   1575
   End
   Begin VB.Line Line6 
      BorderColor     =   &H000000FF&
      X1              =   3240
      X2              =   4920
      Y1              =   2280
      Y2              =   2280
   End
   Begin VB.Line Line5 
      BorderColor     =   &H000000FF&
      X1              =   3240
      X2              =   3240
      Y1              =   2160
      Y2              =   2410
   End
   Begin VB.Line Line4 
      BorderColor     =   &H000000FF&
      X1              =   3000
      X2              =   3240
      Y1              =   3240
      Y2              =   2400
   End
   Begin VB.Line Line3 
      BorderColor     =   &H000000FF&
      X1              =   3000
      X2              =   3240
      Y1              =   1320
      Y2              =   2160
   End
   Begin VB.Line Line2 
      BorderColor     =   &H000000FF&
      X1              =   2160
      X2              =   3000
      Y1              =   3240
      Y2              =   3240
   End
   Begin VB.Line Line1 
      BorderColor     =   &H000000FF&
      X1              =   2160
      X2              =   3000
      Y1              =   1320
      Y2              =   1320
   End
   Begin VB.Label Label2 
      BackStyle       =   0  'Transparent
      Caption         =   "Doppelklicken sie, um dieses Fenster zu schlieﬂen."
      ForeColor       =   &H00FFFFFF&
      Height          =   255
      Left            =   120
      TabIndex        =   2
      Top             =   4440
      Width           =   3735
   End
   Begin VB.Label Label1 
      Alignment       =   2  'Zentriert
      Appearance      =   0  '2D
      BackColor       =   &H80000005&
      BackStyle       =   0  'Transparent
      Caption         =   "Der Computer ist nun bereit, ihre Befehle entgegenzunehmen."
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   13.5
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H00FFFFFF&
      Height          =   735
      Left            =   0
      TabIndex        =   0
      Top             =   120
      Width           =   5775
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub Form_DblClick()
Unload Me
End Sub

Private Sub Image1_DblClick()
Unload Me
End Sub

Private Sub Label1_DblClick()
Unload Me
End Sub

Private Sub Label2_DblClick()
Unload Me
End Sub

Private Sub Label3_DblClick()
Unload Me
End Sub

Private Sub Picture1_DblClick()
Unload Me
End Sub

Private Sub Picture2_DblClick()
Unload Me
End Sub

Private Sub Timer1_Timer()
Static Intervall  As Integer
   
   If Intervall < 1 Then
        Picture2 = LoadPicture(App.Path & "\a_1.jpg")
        Intervall = 1
    ElseIf Intervall = 1 Then
        Picture2 = LoadPicture(App.Path & "\a_2.jpg")
        Intervall = 2
    ElseIf Intervall = 2 Then
        Picture2 = LoadPicture(App.Path & "\a_3.jpg")
        Intervall = 3
    ElseIf Intervall = 3 Then
        Picture2 = LoadPicture(App.Path & "\a_4.jpg")
        Intervall = 4
    ElseIf Intervall = 4 Then
        Picture2 = LoadPicture(App.Path & "\a_5.jpg")
        Intervall = 5
    ElseIf Intervall = 5 Then
        Picture2 = LoadPicture(App.Path & "\a_6.jpg")
        Intervall = 6
    ElseIf Intervall = 6 Then
        Picture2 = LoadPicture(App.Path & "\a_7.jpg")
        Intervall = 7
    ElseIf Intervall = 7 Then
        Picture2 = LoadPicture(App.Path & "\a_8.jpg")
        Intervall = 8
    ElseIf Intervall = 8 Then
        Picture2 = LoadPicture(App.Path & "\a_9.jpg")
        Intervall = 9
    ElseIf Intervall = 9 Then
        Picture2 = LoadPicture(App.Path & "\a_10.jpg")
        Intervall = 0
   End If
End Sub

Private Sub Timer2_Timer()
Static intC As Integer
    

    If intC < 1 Then
        Picture1 = LoadPicture(App.Path & "\Andi_1.jpg")
        intC = 1
        Image1.Move (5750.456)
    ElseIf intC = 1 Then
        Picture1 = LoadPicture(App.Path & "\Andi_2.jpg")
        intC = 2
        Image1.Move (5870.456)
    ElseIf intC = 2 Then
        Picture1 = LoadPicture(App.Path & "\Andi_3.jpg")
        intC = 3
        Image1.Move (5990.456)
        ElseIf intC = 3 Then
        Picture1 = LoadPicture(App.Path & "\Andi_4.jpg")
        intC = 0
        Image1.Move (6110.456)
End If
End Sub
