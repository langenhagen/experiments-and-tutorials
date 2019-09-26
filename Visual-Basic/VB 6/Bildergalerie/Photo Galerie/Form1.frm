VERSION 5.00
Begin VB.Form Form1 
   Caption         =   "Photogalerie"
   ClientHeight    =   3195
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   4680
   LinkTopic       =   "Form1"
   ScaleHeight     =   3195
   ScaleWidth      =   4680
   StartUpPosition =   2  'Bildschirmmitte
   WindowState     =   2  'Maximiert
   Begin VB.PictureBox Picture1 
      Height          =   8595
      Left            =   0
      Picture         =   "Form1.frx":0000
      ScaleHeight     =   8535
      ScaleWidth      =   8460
      TabIndex        =   28
      Top             =   0
      Width           =   8520
   End
   Begin VB.Frame Frame2 
      Caption         =   "Photo wählen"
      Height          =   6255
      Left            =   8640
      TabIndex        =   3
      Top             =   1560
      Width           =   2775
      Begin VB.OptionButton Option25 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Päsentation----------------------------"
         Height          =   255
         Left            =   120
         TabIndex        =   26
         Top             =   5760
         Width           =   2415
      End
      Begin VB.OptionButton Option24 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option24"
         Height          =   255
         Left            =   120
         TabIndex        =   25
         Top             =   5280
         Width           =   2415
      End
      Begin VB.OptionButton Option23 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option23"
         Height          =   255
         Left            =   120
         TabIndex        =   24
         Top             =   5040
         Width           =   2415
      End
      Begin VB.OptionButton Option22 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option22"
         Height          =   195
         Left            =   120
         TabIndex        =   23
         Top             =   4800
         Width           =   2415
      End
      Begin VB.OptionButton Option21 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option21"
         Height          =   255
         Left            =   120
         TabIndex        =   22
         Top             =   4560
         Width           =   2415
      End
      Begin VB.OptionButton Option20 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option20"
         Height          =   255
         Left            =   120
         TabIndex        =   21
         Top             =   4320
         Width           =   2415
      End
      Begin VB.OptionButton Option19 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option19"
         Height          =   255
         Left            =   120
         TabIndex        =   20
         Top             =   4080
         Width           =   2415
      End
      Begin VB.OptionButton Option18 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option18"
         Height          =   255
         Left            =   120
         TabIndex        =   19
         Top             =   3840
         Width           =   2415
      End
      Begin VB.OptionButton Option17 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option17"
         Height          =   255
         Left            =   120
         TabIndex        =   18
         Top             =   3600
         Width           =   2415
      End
      Begin VB.OptionButton Option16 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option16"
         Height          =   255
         Left            =   120
         TabIndex        =   17
         Top             =   3360
         Width           =   2415
      End
      Begin VB.OptionButton Option15 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option15"
         Height          =   195
         Left            =   120
         TabIndex        =   16
         Top             =   3120
         Width           =   2415
      End
      Begin VB.OptionButton Option14 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option14"
         Height          =   195
         Left            =   120
         TabIndex        =   15
         Top             =   2880
         Width           =   2415
      End
      Begin VB.OptionButton Option13 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option13"
         Height          =   195
         Left            =   120
         TabIndex        =   14
         Top             =   2640
         Width           =   2415
      End
      Begin VB.OptionButton Option12 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option12"
         Height          =   255
         Left            =   120
         TabIndex        =   13
         Top             =   2400
         Width           =   2415
      End
      Begin VB.OptionButton Option11 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option11"
         Height          =   195
         Left            =   120
         TabIndex        =   12
         Top             =   2160
         Width           =   2415
      End
      Begin VB.OptionButton Option10 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option10"
         Height          =   195
         Left            =   120
         TabIndex        =   11
         Top             =   1920
         Width           =   2415
      End
      Begin VB.OptionButton Option9 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option9"
         Height          =   255
         Left            =   120
         TabIndex        =   10
         Top             =   1680
         Width           =   2415
      End
      Begin VB.OptionButton Option8 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option8"
         Height          =   255
         Left            =   120
         TabIndex        =   9
         Top             =   1440
         Width           =   2415
      End
      Begin VB.OptionButton Option7 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option7"
         Height          =   255
         Left            =   120
         TabIndex        =   8
         Top             =   1200
         Width           =   2415
      End
      Begin VB.OptionButton Option6 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option6"
         Height          =   255
         Left            =   120
         TabIndex        =   7
         Top             =   960
         Width           =   2415
      End
      Begin VB.OptionButton Option5 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option5"
         Height          =   195
         Left            =   120
         TabIndex        =   6
         Top             =   720
         Width           =   2415
      End
      Begin VB.OptionButton Option4 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Option4"
         Height          =   255
         Left            =   120
         TabIndex        =   5
         Top             =   480
         Width           =   2415
      End
      Begin VB.OptionButton Option3 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Photo1-----------------------------------"
         Height          =   255
         Left            =   120
         TabIndex        =   4
         Top             =   240
         Width           =   2415
      End
   End
   Begin VB.Frame Frame1 
      Caption         =   "Galerie wählen"
      Height          =   1095
      Left            =   8640
      TabIndex        =   0
      Top             =   240
      Width           =   2775
      Begin VB.OptionButton Option26 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Galerie 3  "
         Height          =   195
         Left            =   120
         TabIndex        =   27
         Top             =   720
         Width           =   2535
      End
      Begin VB.OptionButton Option1 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "Photos von &Klassenmitgliedern"
         Height          =   255
         Left            =   120
         TabIndex        =   2
         Top             =   240
         Width           =   2535
      End
      Begin VB.OptionButton Option2 
         Alignment       =   1  'Rechts ausgerichtet
         Caption         =   "&Private Photos"
         Height          =   255
         Left            =   120
         TabIndex        =   1
         Top             =   480
         Width           =   2535
      End
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
