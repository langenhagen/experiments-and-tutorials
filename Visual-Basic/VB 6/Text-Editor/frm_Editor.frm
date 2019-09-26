VERSION 5.00
Object = "{3B7C8863-D78F-101B-B9B5-04021C009402}#1.2#0"; "RICHTX32.OCX"
Begin VB.Form frm_Editor 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Andis Text Editor"
   ClientHeight    =   6795
   ClientLeft      =   45
   ClientTop       =   615
   ClientWidth     =   7575
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   ScaleHeight     =   6795
   ScaleWidth      =   7575
   StartUpPosition =   2  'Bildschirmmitte
   Begin RichTextLib.RichTextBox RichTextBox1 
      Height          =   6375
      Left            =   120
      TabIndex        =   1
      Top             =   120
      Width           =   6615
      _ExtentX        =   11668
      _ExtentY        =   11245
      _Version        =   393217
      TextRTF         =   $"frm_Editor.frx":0000
   End
   Begin VB.VScrollBar VScroll1 
      Enabled         =   0   'False
      Height          =   6375
      Left            =   6720
      TabIndex        =   0
      Top             =   120
      Width           =   255
   End
   Begin VB.Menu mnu_Datei 
      Caption         =   "&Datei"
      Begin VB.Menu mnu_Dat_öffnen 
         Caption         =   "Datei ö&ffnen"
         Shortcut        =   ^O
      End
      Begin VB.Menu mnu_speichern 
         Caption         =   "Datei speichern"
         Shortcut        =   ^S
      End
      Begin VB.Menu mnu_dat_unter 
         Caption         =   "Datei speichern &unter..."
      End
      Begin VB.Menu mnu_leer1 
         Caption         =   "-"
      End
      Begin VB.Menu mnu_dat_drucken 
         Caption         =   "&Drucken"
         Shortcut        =   ^P
      End
      Begin VB.Menu mnu_dat_leer2 
         Caption         =   "-"
      End
      Begin VB.Menu mnu_dat_ende 
         Caption         =   "B&eenden"
         Shortcut        =   ^Q
      End
   End
   Begin VB.Menu mnu_bearbeiten 
      Caption         =   "&Bearbeiten"
      Begin VB.Menu mnu_bearb_rück 
         Caption         =   "&Rückgängig"
         Shortcut        =   ^Z
      End
      Begin VB.Menu mnu_bearb_wieder 
         Caption         =   "&Wiederholen"
         Shortcut        =   ^Y
      End
      Begin VB.Menu mnu_bearb_leer1 
         Caption         =   "-"
      End
      Begin VB.Menu mnu_bearb_auschschn 
         Caption         =   "&Ausschneiden"
         Shortcut        =   ^X
      End
      Begin VB.Menu mnu_bearb_kop 
         Caption         =   "&Kopieren"
         Shortcut        =   ^C
      End
      Begin VB.Menu mnu_bearb_einf 
         Caption         =   "&Einfügen"
         Shortcut        =   ^V
      End
      Begin VB.Menu mnu_bearb_löschen 
         Caption         =   "&Löschen"
         Shortcut        =   {DEL}
      End
   End
   Begin VB.Menu mnu_extras 
      Caption         =   "Extras"
      Begin VB.Menu mnu_extra_wörter 
         Caption         =   "Wörter zählen..."
      End
      Begin VB.Menu mnu_extra_zeilen 
         Caption         =   "Zeilen zählen..."
      End
      Begin VB.Menu mnu_extra_absätze 
         Caption         =   "Absätze..."
      End
   End
   Begin VB.Menu mnu_hilfe 
      Caption         =   "&?"
      Begin VB.Menu mnu_hilfe_themen 
         Caption         =   "&Hilfethemen"
      End
      Begin VB.Menu mnu_hilfe_leer1 
         Caption         =   "-"
      End
      Begin VB.Menu mnu_hilfe_info 
         Caption         =   "Inf&o"
      End
   End
End
Attribute VB_Name = "frm_Editor"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
