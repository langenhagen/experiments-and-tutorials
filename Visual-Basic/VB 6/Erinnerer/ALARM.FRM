VERSION 5.00
Begin VB.Form AlarmForm 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Erinnerer"
   ClientHeight    =   255
   ClientLeft      =   5010
   ClientTop       =   4245
   ClientWidth     =   3030
   BeginProperty Font 
      Name            =   "MS Sans Serif"
      Size            =   8.25
      Charset         =   0
      Weight          =   700
      Underline       =   0   'False
      Italic          =   0   'False
      Strikethrough   =   0   'False
   EndProperty
   Icon            =   "ALARM.frx":0000
   LinkTopic       =   "Form2"
   MaxButton       =   0   'False
   PaletteMode     =   1  'ZReihenfolge
   ScaleHeight     =   255
   ScaleWidth      =   3030
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.CommandButton Command2 
      Height          =   255
      Left            =   1560
      TabIndex        =   1
      Top             =   0
      Width           =   1455
   End
   Begin VB.Timer Timer1 
      Interval        =   500
      Left            =   2640
      Top             =   120
   End
   Begin VB.Label lblTime 
      BackStyle       =   0  'Transparent
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   12
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   390
      Left            =   120
      TabIndex        =   0
      Top             =   0
      Width           =   1410
   End
End
Attribute VB_Name = "AlarmForm"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit
Dim AlarmTime
Const conMinimized = 1



Private Sub Command2_Click()
AlarmTime = InputBox("Gib die Weckzeit ein:", "Weckzeit", AlarmTime)
    If AlarmTime = "" Then Exit Sub
    If Not IsDate(AlarmTime) Then
        MsgBox "Keine gültige Uhrzeit eingegeben! Bitte versuche es noch einmal."
    Else            ' Zeichenfolge, die von InputBox übergegeben
                    ' wird, hat gültiges Zeitformat,
        AlarmTime = CDate(AlarmTime)  ' daher speichern als
                                      ' Datum/Zeit-Wert.
    End If
End Sub

Private Sub Form_Load()
    AlarmTime = ""
End Sub

Private Sub Form_Resize()
    If WindowState = conMinimized Then   ' Anzeigen der Zeit in Überschrift,
                                         ' falls Formular minimiert.
        SetCaptionTime
    Else
        Caption = "Erinnerer"
    End If
End Sub

Private Sub SetCaptionTime()
    Caption = Format(Time, "Short Time")   ' Anzeigen der Zeit im
                                           ' Format "Short Time".
End Sub

Private Sub Timer1_Timer()
Static AlarmSounded As Integer
    If lblTime.Caption <> CStr(Time) Then
        ' Sekundenzahl ist jetzt verschieden vom angezeigten Wert.
        If Time >= AlarmTime And Not AlarmSounded Then
            Beep
            MsgBox "Nun ist es " & Time
            AlarmSounded = True
        ElseIf Time < AlarmTime Then
            AlarmSounded = False
        End If
        If WindowState = conMinimized Then
            ' Wenn minimiert, Formulartitel jede Minute aktualisieren.
            If Minute(CDate(Caption)) <> Minute(Time) Then SetCaptionTime
        Else
            ' Ansonsten Caption-Eigenschaft des Bezeichnungsfelds
            ' jede Sekunde aktualisieren.
            lblTime.Caption = Time
        End If
    End If
End Sub

