VERSION 5.00
Begin VB.Form frm_Zahlensystem 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Andis ZahlenSystem 1.0 Beta"
   ClientHeight    =   3030
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   6120
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   ScaleHeight     =   3030
   ScaleWidth      =   6120
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.VScrollBar scroll_Systemnr 
      Height          =   285
      Left            =   1920
      Max             =   2
      Min             =   16
      TabIndex        =   4
      Top             =   1680
      Value           =   2
      Width           =   255
   End
   Begin VB.OptionButton opt_Zahlensystem 
      Caption         =   "Umrechnung in Zahlensystem #"
      Height          =   195
      Left            =   240
      TabIndex        =   1
      Top             =   600
      Width           =   2655
   End
   Begin VB.OptionButton opt_Dezimalsystem 
      Caption         =   "Umrechnung in Dezimalsystem"
      Height          =   195
      Left            =   240
      TabIndex        =   0
      Top             =   360
      Value           =   -1  'True
      Width           =   2655
   End
   Begin VB.CommandButton btn_Berechnen 
      Caption         =   "Berechnen"
      Default         =   -1  'True
      Height          =   375
      Left            =   240
      TabIndex        =   6
      Top             =   2400
      Width           =   1215
   End
   Begin VB.TextBox txt_SystemNummer 
      Height          =   285
      Left            =   1560
      TabIndex        =   3
      Text            =   "2"
      ToolTipText     =   "Geben sie hier das Gewünschte Zahlensystem ein (Eine Zahl zwischen 1 und 17)"
      Top             =   1680
      Width           =   375
   End
   Begin VB.TextBox txt_Zahlensystemx 
      Height          =   285
      Left            =   2280
      TabIndex        =   5
      ToolTipText     =   "Geben sie hier die gewünschte Zahl ein"
      Top             =   1680
      Width           =   3375
   End
   Begin VB.TextBox txt_Dezimalsystem 
      Height          =   285
      Left            =   1560
      TabIndex        =   2
      ToolTipText     =   "Geben sie hier die gewünschte Dezimalzahl ein"
      Top             =   1320
      Width           =   4095
   End
   Begin VB.Label lab_Rechenoperation 
      BackStyle       =   0  'Transparent
      Caption         =   "Welche Rechenoperation wollen sie durchführen?"
      Height          =   255
      Left            =   120
      TabIndex        =   9
      Top             =   120
      Width           =   3615
   End
   Begin VB.Label lab_Zahlensystemx 
      Alignment       =   1  'Rechts
      BackStyle       =   0  'Transparent
      Caption         =   "Zahlensystem #"
      Height          =   255
      Left            =   240
      TabIndex        =   8
      Top             =   1680
      Width           =   1215
   End
   Begin VB.Label lab_Dezimalsystem 
      Alignment       =   1  'Rechts
      BackStyle       =   0  'Transparent
      Caption         =   "Dezimalsystem:"
      Height          =   255
      Left            =   240
      TabIndex        =   7
      Top             =   1320
      Width           =   1215
   End
End
Attribute VB_Name = "frm_Zahlensystem"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub btn_Berechnen_Click()

If opt_Dezimalsystem.Value = True Then GoTo Von_X_in_Dezimal 'Übergabe an Algoritmus #2

If opt_Zahlensystem.Value = True Then  'Beginn des Algorhitmus #1

If txt_Dezimalsystem.Text = "" Then
frm_Hinweis.Show 1
Exit Sub
End If

Dim varSystemNummer As Byte  'Initialisieren der Variablen
Dim varZahlensystem As Long
Dim varDezimalsystem As Long
Dim Rest As Byte
Dim varZahl As Long

On Error GoTo Ende

varSystemNummer = txt_SystemNummer.Text
varDezimalsystem = txt_Dezimalsystem.Text

Wiederholen:

varZahl = varDezimalsystem / varSystemNummer - 0.4999999999 'Rechnung
Rest = varDezimalsystem Mod varSystemNummer


varDezimalsystem = varZahl

Ergebnis = Rest & Ergebnis




If varDezimalsystem <> 0 Then GoTo Wiederholen

txt_Zahlensystemx.Text = Ergebnis

End If 'Ende des Algorhitmus #1
Exit Sub

Von_X_in_Dezimal: 'Beginn des Algoritmus #2

If txt_Zahlensystemx.Text = "" Then
frm_Hinweis2.Show 1
Exit Sub
End If


Dim System As Byte
System = txt_SystemNummer.Text

Ziffern = Len(txt_Zahlensystemx.Text)
Zahl = txt_Zahlensystemx


For i = 0 To Ziffern
z = i + 1

AktuelleZiffer = Ziffern - i

If i = Ziffern Then GoTo Weiter

Ziffer = Mid$(Zahl, z, 1)
Potenz = Ziffer * System ^ (AktuelleZiffer - 1)

Ergebnis = Ergebnis + Potenz


Next i
 
Weiter:

txt_Dezimalsystem.Text = Ergebnis
Exit Sub 'Beenden des Algoritmus #2

Ende:                'Fehlerbehebungsmaßnahmen
Exit Sub


End Sub

Private Sub scroll_Systemnr_Change()

txt_SystemNummer.Text = scroll_Systemnr.Value

End Sub

Private Sub txt_SystemNummer_Change()

On Error GoTo SystemNummerFehler

scroll_Systemnr.Value = txt_SystemNummer.Text

If txt_SystemNummer.Text > 16 Then txt_SystemNummer.Text = 16
If txt_SystemNummer.Text < 2 Then txt_SystemNummer.Text = 2

GoTo SystemNummerNormal

SystemNummerFehler:
    
    txt_SystemNummer.Text = 16

SystemNummerNormal:



End Sub
