VERSION 5.00
Begin VB.Form Calculator 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Taschenrechner"
   ClientHeight    =   2970
   ClientLeft      =   5055
   ClientTop       =   4680
   ClientWidth     =   3240
   ClipControls    =   0   'False
   BeginProperty Font 
      Name            =   "Wingdings"
      Size            =   9.75
      Charset         =   2
      Weight          =   700
      Underline       =   0   'False
      Italic          =   0   'False
      Strikethrough   =   0   'False
   EndProperty
   Icon            =   "CALC.frx":0000
   LinkMode        =   1  'Quelle
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MouseIcon       =   "CALC.frx":030A
   PaletteMode     =   1  'ZReihenfolge
   ScaleHeight     =   2970
   ScaleWidth      =   3240
   StartUpPosition =   1  'Fenstermitte
   WhatsThisHelp   =   -1  'True
   Begin VB.Timer Timer1 
      Interval        =   150
      Left            =   960
      Top             =   840
   End
   Begin VB.PictureBox Picture1 
      AutoSize        =   -1  'True
      Height          =   615
      Left            =   120
      ScaleHeight     =   555
      ScaleWidth      =   555
      TabIndex        =   21
      Top             =   600
      Width           =   615
   End
   Begin VB.CommandButton Command1 
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Left            =   120
      TabIndex        =   20
      Top             =   2520
      Width           =   480
   End
   Begin VB.CommandButton Number 
      Caption         =   "7"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   7
      Left            =   120
      TabIndex        =   7
      Top             =   1440
      Width           =   480
   End
   Begin VB.CommandButton Number 
      Caption         =   "8"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   8
      Left            =   600
      TabIndex        =   8
      Top             =   1440
      Width           =   480
   End
   Begin VB.CommandButton Number 
      Caption         =   "9"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   345
      Index           =   9
      Left            =   1080
      Style           =   1  'Grafisch
      TabIndex        =   9
      Top             =   1440
      Width           =   480
   End
   Begin VB.CommandButton Cancel 
      Caption         =   "C"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Left            =   2040
      TabIndex        =   10
      Top             =   600
      Width           =   600
   End
   Begin VB.CommandButton CancelEntry 
      Caption         =   "CE"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Left            =   2640
      TabIndex        =   11
      Top             =   600
      Width           =   600
   End
   Begin VB.CommandButton Number 
      Caption         =   "4"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   4
      Left            =   120
      TabIndex        =   4
      Top             =   1800
      Width           =   480
   End
   Begin VB.CommandButton Number 
      Caption         =   "5"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   5
      Left            =   600
      TabIndex        =   5
      Top             =   1800
      Width           =   480
   End
   Begin VB.CommandButton Number 
      Caption         =   "6"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   6
      Left            =   1080
      TabIndex        =   6
      Top             =   1800
      Width           =   480
   End
   Begin VB.CommandButton Operator 
      Caption         =   "+"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   1
      Left            =   2520
      TabIndex        =   12
      Top             =   1800
      Width           =   360
   End
   Begin VB.CommandButton Operator 
      Caption         =   "-"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   3
      Left            =   2880
      TabIndex        =   13
      Top             =   1800
      Width           =   360
   End
   Begin VB.CommandButton Number 
      Caption         =   "1"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   1
      Left            =   120
      TabIndex        =   1
      Top             =   2160
      Width           =   480
   End
   Begin VB.CommandButton Number 
      Caption         =   "2"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   2
      Left            =   600
      TabIndex        =   2
      Top             =   2160
      Width           =   480
   End
   Begin VB.CommandButton Number 
      Caption         =   "3"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   3
      Left            =   1080
      TabIndex        =   3
      Top             =   2160
      Width           =   480
   End
   Begin VB.CommandButton Operator 
      Caption         =   "x"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   2
      Left            =   2520
      TabIndex        =   14
      Top             =   2160
      Width           =   360
   End
   Begin VB.CommandButton Operator 
      Caption         =   "/"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   0
      Left            =   2880
      TabIndex        =   15
      Top             =   2160
      Width           =   360
   End
   Begin VB.CommandButton Number 
      Caption         =   "0"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   0
      Left            =   600
      TabIndex        =   0
      Top             =   2520
      Width           =   480
   End
   Begin VB.CommandButton Decimal 
      Caption         =   ","
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Left            =   1080
      TabIndex        =   18
      Top             =   2520
      Width           =   480
   End
   Begin VB.CommandButton Operator 
      Caption         =   "="
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Index           =   4
      Left            =   2520
      TabIndex        =   16
      Top             =   2520
      Width           =   360
   End
   Begin VB.CommandButton Percent 
      Caption         =   "%"
      BeginProperty Font 
         Name            =   "System"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   360
      Left            =   2880
      TabIndex        =   17
      Top             =   2520
      Width           =   360
   End
   Begin VB.Label Readout 
      Alignment       =   1  'Rechts
      BackColor       =   &H00000000&
      BorderStyle     =   1  'Fest Einfach
      Caption         =   "0."
      BeginProperty Font 
         Name            =   "System"
         Size            =   19.5
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H00C0C000&
      Height          =   495
      Left            =   0
      TabIndex        =   19
      Top             =   0
      Width           =   3240
   End
End
Attribute VB_Name = "Calculator"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
' ------------------------------------------------------------------------
'               Copyright (C) 1994 Microsoft Corporation
'
' Sie haben das lizenzgebührenfreie Recht, die Beispieldateien zu
' verwenden, zu ändern und zu vertreiben. Das kann auf jegliche,
' für Sie vorteilhafte Weise geschehen, vorausgesetzt, daß Sie sich
' damit einverstanden erklären, daß Microsoft keine Garantie,
' Verpflichtung oder Haftung für die Beispieldateien trägt.
' ------------------------------------------------------------------------
Option Explicit
Dim Op1, Op2                ' Zuvor eingegebener Operand.
Dim DecimalFlag As Integer  ' Dezimalkomma bereits vorhanden?
Dim NumOps As Integer       ' Anzahl der Operanden.
Dim LastInput               ' Zeigt Typ des letzten Tastenanschlags an.
Dim OpFlag                  ' Zeigt gegenwärtige Operation an.
Dim TempReadout

' Click-Ereignisprozedur für die C-Taste (Abbrechen).
' Setzt Anzeige zurück und initialisiert Variablen.
Private Sub Cancel_Click()
    Readout = Format(0, "0.")
    Op1 = 0
    Op2 = 0
    Form_Load
End Sub

' Click-Ereignisprozedur für die CE-Taste (Eingabe abbrechen).
Private Sub CancelEntry_Click()
    Readout = Format(0, "0.")
    DecimalFlag = False
    LastInput = "CE"
End Sub

' Click-Ereignisprozedur für das Dezimalkomma (,).
' Wenn der letzte Tastenanschlag ein Operator war:
' Anzeige auf "0" initialisieren. Sonst der Anzeige
' ein Dezimalkomma hinzufügen.
Private Sub Decimal_Click()
    If LastInput = "NEG" Then
        Readout = Format(0, "-0.")
    ElseIf LastInput <> "NUMS" Then
        Readout = Format(0, "0.")
    End If
    DecimalFlag = True
    LastInput = "NUMS"
End Sub

' Initialisierungsroutine für die Form.
' Setze alle Variablen auf Ausgangswerte.
Private Sub Form_Load()
    DecimalFlag = False
    NumOps = 0
    LastInput = "NONE"
    OpFlag = " "
    Readout = Format(0, "0.")
    'Decimal.Caption = Format(0, ".")
End Sub

' Click-Ereignisprozedur für Zahlentasten (0-9).
' Füge zur Zahl in der Anzeige eine neue Zahl hinzu.
Private Sub Number_Click(Index As Integer)
    If LastInput <> "NUMS" Then
        Readout = Format(0, ".")
        DecimalFlag = False
    End If
    If DecimalFlag Then
        Readout = Readout + Number(Index).Caption
    Else
        Readout = Left(Readout, InStr(Readout, Format(0, ".")) - 1) + Number(Index).Caption + Format(0, ".")
    End If
    If LastInput = "NEG" Then Readout = "-" & Readout
    LastInput = "NUMS"
End Sub

' Click-Ereignisprozedur für die Operatortasten (+, -, x, /, =).
' Wenn der unmittelbar zurückliegende Tastenanschlag
' ein Teil einer Zahl war, wird NumOps um Eins erhöht.
' Wenn ein Operand vorhanden ist, setze Op1.
' Wenn zwei Operanden vorhanden sind, setze Op1 auf
' das Ergebnis aus der Operation mit Op1 und der
' aktuellen Eingabezeichenfolge. Zeige dann das Ergebnis an.
Private Sub Operator_Click(Index As Integer)
    TempReadout = Readout
    If LastInput = "NUMS" Then
        NumOps = NumOps + 1
    End If
    Select Case NumOps
        Case 0
        If Operator(Index).Caption = "-" And LastInput <> "NEG" Then
            Readout = "-" & Readout
            LastInput = "NEG"
        End If
        Case 1
        Op1 = Readout
        If Operator(Index).Caption = "-" And LastInput <> "NUMS" And OpFlag <> "=" Then
            Readout = "-"
            LastInput = "NEG"
        End If
        Case 2
        Op2 = TempReadout
        Select Case OpFlag
            Case "+"
                Op1 = CDbl(Op1) + CDbl(Op2)
            Case "-"
                Op1 = CDbl(Op1) - CDbl(Op2)
            Case "X"
                Op1 = CDbl(Op1) * CDbl(Op2)
            Case "/"
                If Op2 = 0 Then
                   MsgBox "Division durch Null ist nicht möglich", 48, "Rechner"
                Else
                   Op1 = CDbl(Op1) / CDbl(Op2)
                End If
            Case "="
                Op1 = CDbl(Op2)
            Case "%"
                Op1 = CDbl(Op1) * CDbl(Op2)
            End Select
        Readout = Op1
        NumOps = 1
    End Select
    If LastInput <> "NEG" Then
        LastInput = "OPS"
        OpFlag = Operator(Index).Caption
    End If
End Sub

' Click-Ereignisprozedur für die Prozenttaste (%).
' Berechne einen Prozentsatz des ersten Operanden und zeige ihn an.
Private Sub Percent_Click()
    Readout = Readout / 100
    LastInput = "Ops"
    OpFlag = "%"
    NumOps = NumOps + 1
    DecimalFlag = True
End Sub

Private Sub Timer1_Timer()
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
