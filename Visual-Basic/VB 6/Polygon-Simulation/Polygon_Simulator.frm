VERSION 5.00
Begin VB.Form Polygon_Simulator 
   BackColor       =   &H00000000&
   Caption         =   "3D-Polygon-Simulator"
   ClientHeight    =   3195
   ClientLeft      =   75
   ClientTop       =   360
   ClientWidth     =   4680
   LinkTopic       =   "Form1"
   ScaleHeight     =   3195
   ScaleWidth      =   4680
   StartUpPosition =   2  'Bildschirmmitte
   WindowState     =   2  'Maximiert
   Begin VB.PictureBox Picture5 
      Height          =   615
      Left            =   10680
      ScaleHeight     =   555
      ScaleWidth      =   555
      TabIndex        =   11
      Top             =   240
      Width           =   615
   End
   Begin VB.Timer Timer2 
      Interval        =   150
      Left            =   7560
      Top             =   840
   End
   Begin VB.PictureBox Picture4 
      AutoSize        =   -1  'True
      BackColor       =   &H00000000&
      Height          =   3750
      Left            =   240
      Picture         =   "Polygon_Simulator.frx":0000
      ScaleHeight     =   3690
      ScaleWidth      =   4785
      TabIndex        =   10
      Top             =   240
      Width           =   4845
   End
   Begin VB.PictureBox Picture3 
      AutoSize        =   -1  'True
      BackColor       =   &H00000000&
      Height          =   975
      Left            =   240
      ScaleHeight     =   915
      ScaleWidth      =   1035
      TabIndex        =   9
      Top             =   240
      Visible         =   0   'False
      Width           =   1095
   End
   Begin VB.PictureBox Picture2 
      AutoSize        =   -1  'True
      BackColor       =   &H00000000&
      Height          =   975
      Left            =   240
      ScaleHeight     =   915
      ScaleWidth      =   1035
      TabIndex        =   8
      Top             =   240
      Visible         =   0   'False
      Width           =   1095
   End
   Begin VB.Timer Timer1 
      Interval        =   1
      Left            =   7200
      Top             =   840
   End
   Begin VB.OptionButton PyramideOpt 
      BackColor       =   &H00000000&
      Caption         =   "&Pyramiden-Simulator"
      ForeColor       =   &H00FFFFFF&
      Height          =   255
      Left            =   9240
      TabIndex        =   6
      Top             =   2760
      Width           =   1935
   End
   Begin VB.OptionButton WürfelOpt 
      BackColor       =   &H00000000&
      Caption         =   "&Würfel-Simulator"
      ForeColor       =   &H00FFFFFF&
      Height          =   255
      Left            =   9240
      TabIndex        =   5
      Top             =   2520
      Width           =   1935
   End
   Begin VB.OptionButton KugelOpt 
      BackColor       =   &H00000000&
      Caption         =   "&Kugel-Simulator"
      ForeColor       =   &H00FFFFFF&
      Height          =   255
      Left            =   9240
      TabIndex        =   4
      Top             =   2280
      Width           =   1935
   End
   Begin VB.OptionButton leerOpt 
      BackColor       =   &H00000000&
      Caption         =   "[leer]"
      ForeColor       =   &H00FFFFFF&
      Height          =   255
      Left            =   9240
      TabIndex        =   3
      Top             =   3000
      Width           =   1935
   End
   Begin VB.CommandButton EndeButton 
      Caption         =   "&Beenden"
      Height          =   375
      Left            =   9840
      TabIndex        =   1
      Top             =   7320
      Width           =   975
   End
   Begin VB.PictureBox Picture1 
      AutoSize        =   -1  'True
      BackColor       =   &H00000000&
      Height          =   975
      Left            =   240
      ScaleHeight     =   915
      ScaleWidth      =   1035
      TabIndex        =   0
      Top             =   240
      Visible         =   0   'False
      Width           =   1095
   End
   Begin VB.Label PolygonLabel 
      BackColor       =   &H00000000&
      Caption         =   "3D-Polygone zum Auswählen:"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   8.25
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H00FFFFFF&
      Height          =   375
      Left            =   6600
      TabIndex        =   7
      Top             =   2280
      Width           =   2775
   End
   Begin VB.Label SiLabel 
      BackColor       =   &H00000000&
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H00FFFFFF&
      Height          =   735
      Left            =   6600
      TabIndex        =   2
      Top             =   240
      Width           =   2535
   End
End
Attribute VB_Name = "Polygon_Simulator"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False


Private Sub Bild_Click()

End Sub

Private Sub EndeButton_Click()
Unload Me
End Sub

Private Sub Form_Load()
 Set Picture1 = LoadPicture(App.Path & "\andi.jpg")
End Sub

Private Sub KugelOpt_Click()
Picture1.Visible = True
Picture2.Visible = False
Picture3.Visible = False
SiLabel.Caption = "Kugel-Simulator"
Picture4.Visible = False
End Sub


Private Sub PyramideOpt_Click()
Picture1.Visible = False
Picture2.Visible = False
Picture3.Visible = True
SiLabel.Caption = "Pyramiden-Simulator"
Picture4.Visible = False
End Sub

Private Sub Timer1_Timer()
 Static Intervall  As Integer
   
   
    
    If Intervall < 1 Then
        Picture1 = LoadPicture(App.Path & "\a_1.bmp")
        Intervall = 1
    ElseIf Intervall = 1 Then
        Picture1 = LoadPicture(App.Path & "\a_2.BMP")
        Intervall = 2
    ElseIf Intervall = 2 Then
        Picture1 = LoadPicture(App.Path & "\a_3.bmp")
        Intervall = 3
        ElseIf Intervall = 3 Then
        Picture1 = LoadPicture(App.Path & "\a_4.bmp")
        Intervall = 4
        ElseIf Intervall = 4 Then
        Picture1 = LoadPicture(App.Path & "\a_5.bmp")
        Intervall = 5
        ElseIf Intervall = 5 Then
        Picture1 = LoadPicture(App.Path & "\a_6.bmp")
        Intervall = 6
        ElseIf Intervall = 6 Then
        Picture1 = LoadPicture(App.Path & "\a_7.bmp")
        Intervall = 7
        ElseIf Intervall = 7 Then
        Picture1 = LoadPicture(App.Path & "\a_8.bmp")
        Intervall = 8
        ElseIf Intervall = 8 Then
        Picture1 = LoadPicture(App.Path & "\a_9.bmp")
        Intervall = 9
        ElseIf Intervall = 9 Then
        Picture1 = LoadPicture(App.Path & "\a_10.bmp")
        Intervall = 0
   End If
   
   
   
   
   Static ba  As Integer
 If ba < 1 Then
        Picture2 = LoadPicture(App.Path & "\b_1.bmp")
        ba = 1
    ElseIf ba = 1 Then
        Picture2 = LoadPicture(App.Path & "\b_2.BMP")
        ba = 2
    ElseIf ba = 2 Then
        Picture2 = LoadPicture(App.Path & "\b_3.bmp")
        ba = 3
        ElseIf ba = 3 Then
        Picture2 = LoadPicture(App.Path & "\b_4.bmp")
        ba = 4
        ElseIf ba = 4 Then
        Picture2 = LoadPicture(App.Path & "\b_5.bmp")
        ba = 5
        ElseIf ba = 5 Then
        Picture2 = LoadPicture(App.Path & "\b_6.bmp")
        ba = 6
        ElseIf ba = 6 Then
        Picture2 = LoadPicture(App.Path & "\b_7.bmp")
        ba = 7
        ElseIf ba = 7 Then
        Picture2 = LoadPicture(App.Path & "\b_8.bmp")
        ba = 8
        ElseIf ba = 8 Then
        Picture2 = LoadPicture(App.Path & "\b_9.bmp")
        ba = 9
        ElseIf ba = 9 Then
        Picture2 = LoadPicture(App.Path & "\b_10.bmp")
        ba = 10
        ElseIf ba = 10 Then
        Picture2 = LoadPicture(App.Path & "\b_11.bmp")
        ba = 11
         ElseIf ba = 11 Then
        Picture2 = LoadPicture(App.Path & "\b_12.bmp")
        ba = 12
         ElseIf ba = 12 Then
        Picture2 = LoadPicture(App.Path & "\b_13.bmp")
        ba = 13
         ElseIf ba = 13 Then
        Picture2 = LoadPicture(App.Path & "\b_14.bmp")
        ba = 14
         ElseIf ba = 14 Then
        Picture2 = LoadPicture(App.Path & "\b_15.bmp")
        ba = 15
         ElseIf ba = 15 Then
        Picture2 = LoadPicture(App.Path & "\b_16.bmp")
        ba = 16
         ElseIf ba = 16 Then
        Picture2 = LoadPicture(App.Path & "\b_17.bmp")
        ba = 17
         ElseIf ba = 17 Then
        Picture2 = LoadPicture(App.Path & "\b_18.bmp")
        ba = 18
         ElseIf ba = 18 Then
        Picture2 = LoadPicture(App.Path & "\b_19.bmp")
        ba = 19
         ElseIf ba = 19 Then
        Picture2 = LoadPicture(App.Path & "\b_20.bmp")
        ba = 0
       End If
       
        
        
        Static cae  As Integer
 If cae < 1 Then
        Picture3 = LoadPicture(App.Path & "\c_1.bmp")
        cae = 1
    ElseIf cae = 1 Then
        Picture3 = LoadPicture(App.Path & "\c_2.BMP")
        cae = 2
    ElseIf cae = 2 Then
        Picture3 = LoadPicture(App.Path & "\c_3.bmp")
        cae = 3
        ElseIf cae = 3 Then
        Picture3 = LoadPicture(App.Path & "\c_4.bmp")
        cae = 4
        ElseIf cae = 4 Then
        Picture3 = LoadPicture(App.Path & "\c_5.bmp")
        cae = 5
        ElseIf cae = 5 Then
        Picture3 = LoadPicture(App.Path & "\c_6.bmp")
        cae = 6
        ElseIf cae = 6 Then
        Picture3 = LoadPicture(App.Path & "\c_7.bmp")
        cae = 7
        ElseIf cae = 7 Then
        Picture3 = LoadPicture(App.Path & "\c_8.bmp")
        cae = 8
        ElseIf cae = 8 Then
        Picture3 = LoadPicture(App.Path & "\c_9.bmp")
        cae = 9
        ElseIf cae = 9 Then
        Picture3 = LoadPicture(App.Path & "\c_10.bmp")
        cae = 10
        ElseIf cae = 10 Then
        Picture3 = LoadPicture(App.Path & "\c_11.bmp")
        cae = 11
         ElseIf cae = 11 Then
        Picture3 = LoadPicture(App.Path & "\c_12.bmp")
        cae = 12
         ElseIf cae = 12 Then
        Picture3 = LoadPicture(App.Path & "\c_13.bmp")
        cae = 13
         ElseIf cae = 13 Then
        Picture3 = LoadPicture(App.Path & "\c_14.bmp")
        cae = 14
         ElseIf cae = 14 Then
        Picture3 = LoadPicture(App.Path & "\c_15.bmp")
        cae = 15
         ElseIf cae = 15 Then
        Picture3 = LoadPicture(App.Path & "\c_16.bmp")
        cae = 16
         ElseIf cae = 16 Then
        Picture3 = LoadPicture(App.Path & "\c_17.bmp")
        cae = 17
         ElseIf cae = 17 Then
        Picture3 = LoadPicture(App.Path & "\c_18.bmp")
        cae = 18
         ElseIf cae = 18 Then
        Picture3 = LoadPicture(App.Path & "\c_19.bmp")
        cae = 19
         ElseIf cae = 19 Then
        Picture3 = LoadPicture(App.Path & "\c_20.bmp")
        cae = 0
   
   
    
    
  End If
End Sub

Private Sub Timer3_Timer()
 
End Sub

Private Sub Timer2_Timer()
Static intC As Integer
    

    If intC < 1 Then
        Picture5 = LoadPicture(App.Path & "\Andi_1.bmp")
        intC = 1
    ElseIf intC = 1 Then
        Picture5 = LoadPicture(App.Path & "\Andi_2.bmp")
        intC = 2
    ElseIf intC = 2 Then
        Picture5 = LoadPicture(App.Path & "\Andi_3.bmp")
        intC = 3
        ElseIf intC = 3 Then
        Picture5 = LoadPicture(App.Path & "\Andi_4.bmp")
        intC = 0
        
    End If
End Sub

Private Sub WürfelOpt_Click()
Picture1.Visible = False
Picture2.Visible = True
Picture3.Visible = False
SiLabel.Caption = "Würfel-Simulator"
Picture4.Visible = False
End Sub
