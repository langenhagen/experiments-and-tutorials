VERSION 5.00
Begin VB.Form Form1 
   BorderStyle     =   1  'Fest Einfach
   Caption         =   "Mausfadenkreuz V 1.0"
   ClientHeight    =   6060
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   7110
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MouseIcon       =   "Form1.frx":0000
   MousePointer    =   99  'Benutzerdefiniert
   Picture         =   "Form1.frx":030A
   ScaleHeight     =   6060
   ScaleWidth      =   7110
   StartUpPosition =   2  'Bildschirmmitte
   Begin VB.Line Line_y2 
      X1              =   600
      X2              =   600
      Y1              =   360
      Y2              =   120
   End
   Begin VB.Line Line_x2 
      X1              =   600
      X2              =   840
      Y1              =   120
      Y2              =   120
   End
   Begin VB.Line line_Y 
      X1              =   120
      X2              =   120
      Y1              =   120
      Y2              =   360
   End
   Begin VB.Line line_X 
      X1              =   120
      X2              =   360
      Y1              =   120
      Y2              =   120
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub Command1_Click()
End
End Sub

Private Sub Command1_MouseMove(Button As Integer, Shift As Integer, X As Single, Y As Single)

End Sub

Private Sub btn_End_Click()
End
End Sub

Private Sub btn_End_MouseMove(Button As Integer, Shift As Integer, X As Single, Y As Single)
lab_Status.Caption = btn_End.Tag
line_X.Visible = False
line_Y.Visible = False
Line_y2.Visible = False
Line_x2.Visible = False

End Sub

Private Sub Form_MouseMove(Button As Integer, Shift As Integer, X As Single, Y As Single)
lab_Status = ""
line_X.Visible = True
line_Y.Visible = True
Line_y2.Visible = True
Line_x2.Visible = True
line_X.X1 = 0
line_Y.Y1 = 0
line_X.X2 = X - 130
line_X.Y1 = Y
line_X.Y2 = Y
line_Y.X1 = X
line_Y.X2 = X
line_Y.Y2 = Y - 130
Line_x2.X1 = X + 145
Line_y2.Y1 = Y
Line_x2.X2 = X + 10000
Line_x2.Y1 = Y
Line_x2.Y2 = Y
Line_y2.X1 = X
Line_y2.X2 = X
Line_y2.Y2 = Y + 130
Line_y2.Y1 = Y + 1000000
End Sub

Private Sub lab_Status_MouseMove(Button As Integer, Shift As Integer, X As Single, Y As Single)
line_X.Visible = False
line_Y.Visible = False
Line_y2.Visible = False
Line_x2.Visible = False
End Sub
