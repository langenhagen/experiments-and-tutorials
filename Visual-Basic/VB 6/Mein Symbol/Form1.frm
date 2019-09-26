VERSION 5.00
Begin VB.Form Form1 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Mein Symbol"
   ClientHeight    =   1215
   ClientLeft      =   3555
   ClientTop       =   2580
   ClientWidth     =   2310
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   ScaleHeight     =   1215
   ScaleWidth      =   2310
   Begin VB.PictureBox Picture1 
      AutoSize        =   -1  'True
      Height          =   615
      Left            =   0
      ScaleHeight     =   555
      ScaleWidth      =   555
      TabIndex        =   0
      Top             =   0
      Width           =   615
   End
   Begin VB.Timer Timer1 
      Interval        =   150
      Left            =   720
      Top             =   120
   End
   Begin VB.Line Line1 
      X1              =   1320
      X2              =   1320
      Y1              =   -120
      Y2              =   1440
   End
   Begin VB.Label Label6 
      Caption         =   "Andi_4.bmp"
      Height          =   255
      Left            =   1440
      TabIndex        =   6
      Top             =   720
      Width           =   855
   End
   Begin VB.Label Label5 
      BackStyle       =   0  'Transparent
      Caption         =   "Andi_3.bmp"
      Height          =   255
      Left            =   1440
      TabIndex        =   5
      Top             =   480
      Width           =   855
   End
   Begin VB.Label Label4 
      BackStyle       =   0  'Transparent
      Caption         =   "Andi_2.bmp"
      Height          =   255
      Left            =   1440
      TabIndex        =   4
      Top             =   240
      Width           =   975
   End
   Begin VB.Label Label3 
      BackStyle       =   0  'Transparent
      Caption         =   "Andi_1.bmp"
      Height          =   255
      Left            =   1440
      TabIndex        =   3
      Top             =   0
      Width           =   855
   End
   Begin VB.Label Label2 
      BackStyle       =   0  'Transparent
      Caption         =   "Picture1, Timer1"
      Height          =   255
      Left            =   0
      TabIndex        =   2
      Top             =   720
      Width           =   1455
   End
   Begin VB.Label Label1 
      BackStyle       =   0  'Transparent
      Caption         =   "Timer-Intervall:150"
      Height          =   255
      Left            =   0
      TabIndex        =   1
      Top             =   960
      Width           =   1335
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
'Der Timer muss Interval=150 haben
'Achte darauf,
'dass die Objekte die richtigen Namen haben

Private Sub Label1_Click()
'Unwichtig !
End Sub


Private Sub Label2_Click()
'Unwichtig !
End Sub


Private Sub Label3_Click()
'Unwichtig !
End Sub


Private Sub Label4_Click()
'Unwichtig !
End Sub


Private Sub Label5_Click()
'Unwichtig !
End Sub


Private Sub Label6_Click()
'Unwichtig !
End Sub


Private Sub Picture1_Click()
'Hier wird das Bild geliefert
'Achte darauf, dass in anderen Anwendungen
'der gleiche Name für das Objekt ist!!!!
'Ändere ihn ggf. !!
End Sub

Private Sub Timer1_Timer()
'Hier ist der Code zum Anzeigen der Dateien
'Achte darauf, dass in anderen Anwendungen
'der gleiche Name für das Objekt ist!!!!
'Ändere ihn ggf. !!

Static intC As Integer
    

    If intC < 1 Then
        Picture1 = LoadPicture(App.Path & "\Andi_1.bmp")
        intC = 1
    ElseIf intC = 1 Then
        Picture1 = LoadPicture(App.Path & "\Andi_2.bmp")
        intC = 2
    ElseIf intC = 2 Then
        Picture1 = LoadPicture(App.Path & "\Andi_3.bmp")
        intC = 3
        ElseIf intC = 3 Then
        Picture1 = LoadPicture(App.Path & "\Andi_4.bmp")
        intC = 0
        
    End If
End Sub
