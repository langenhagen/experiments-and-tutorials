VERSION 5.00
Begin VB.Form Form1 
   BorderStyle     =   0  'Kein
   Caption         =   "Hi Andi!"
   ClientHeight    =   3930
   ClientLeft      =   0
   ClientTop       =   0
   ClientWidth     =   5280
   LinkTopic       =   "Form1"
   ScaleHeight     =   3930
   ScaleWidth      =   5280
   ShowInTaskbar   =   0   'False
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.TextBox Text1 
      Appearance      =   0  '2D
      BorderStyle     =   0  'Kein
      Height          =   2295
      Left            =   120
      MousePointer    =   1  'Pfeil
      MultiLine       =   -1  'True
      OLEDragMode     =   1  'Automatisch
      TabIndex        =   2
      TabStop         =   0   'False
      Text            =   "Form1.frx":0000
      Top             =   1080
      Width           =   3015
   End
   Begin VB.Image Image1 
      Appearance      =   0  '2D
      BorderStyle     =   1  'Fest Einfach
      Height          =   1215
      Left            =   3360
      Stretch         =   -1  'True
      Top             =   1800
      Visible         =   0   'False
      Width           =   1695
   End
   Begin VB.Label Label2 
      Alignment       =   2  'Zentriert
      Appearance      =   0  '2D
      BackColor       =   &H80000005&
      BorderStyle     =   1  'Fest Einfach
      Caption         =   "Antwort"
      ForeColor       =   &H80000008&
      Height          =   255
      Left            =   120
      TabIndex        =   4
      Top             =   3600
      Width           =   3015
   End
   Begin VB.OLE OLE1 
      Appearance      =   0  '2D
      BackStyle       =   0  'Transparent
      Class           =   "Package"
      Height          =   735
      Left            =   4320
      OleObjectBlob   =   "Form1.frx":0021
      SourceDoc       =   "C:\AOL 6.0\aol.exe"
      TabIndex        =   3
      Top             =   960
      Width           =   735
   End
   Begin VB.Label Label3 
      Appearance      =   0  '2D
      BackColor       =   &H80000005&
      BorderStyle     =   1  'Fest Einfach
      Caption         =   "                                              Beenden"
      ForeColor       =   &H80000008&
      Height          =   615
      Left            =   3600
      TabIndex        =   1
      Top             =   3120
      Width           =   1575
   End
   Begin VB.Label Label1 
      Appearance      =   0  '2D
      AutoSize        =   -1  'True
      BackColor       =   &H80000005&
      BackStyle       =   0  'Transparent
      Caption         =   "Hi Andi! Wie geht´s denn so ? "
      BeginProperty Font 
         Name            =   "Papyrus"
         Size            =   14.25
         Charset         =   0
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H80000008&
      Height          =   450
      Left            =   120
      TabIndex        =   0
      Top             =   120
      Width           =   3735
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False

Private Sub Label2_Click()
If Text1 = "Hallo Computer" Then Text1.Text = "Hi Andi!"
If Text1 = "Josephine hat mitgekriegt, dass ich sie liebe" Then Text1.Text = "Und?"
If Text1 = "Fabian ist doof" Then Text1.Text = "Wusste ich schon immer!"
If Text1 = "Gib hier die Befehlscodes ein!" Then Text1.Text = "Gib sie schon ein!!!"
If Text1 = "Zeige bitte das Bild der Göttin. Code Alpha Tango 43h" Then Image1.Visible = True
If Text1 = "Gut" Then Text1.Text = "Das freut mich!"
If Text1 = "Naja" Then Text1.Text = "Aha..."
If Text1 = "Normal" Then Text1.Text = "Aha..."
If Text1 = "Schlecht" Then Text1.Text = "Schade"
If Text1 = "q" Then Image1.Visible = False
If Text1 = "Q" Then Image1.Visible = False
If Text1 = "Beende dich" Then Unload Me

End Sub

Private Sub Label3_Click()
Unload Me
End Sub



Private Sub Text1_()

End Sub

