Imports System
Imports System.Collections.Generic
Imports System.ComponentModel
Imports System.Data
Imports System.Drawing
Imports System.Text
Imports System.Windows.Forms
Imports Irrlicht
Imports Irrlicht.Core
Imports Irrlicht.Scene

Namespace _14.WindowsForm
    ''' <summary>
    ''' Shows irrlicht running in a windows .net form
    ''' </summary>
    ''' <remarks>
    ''' Create as a normal windows application, add the usual irrlicht refs and dll's..
    ''' This has been collapsed from 3 files into one for posting.
    ''' </remarks>

    Public Class Form1
        Inherits System.Windows.Forms.Form

        Private device As IrrlichtDevice
        Private cam As ICameraSceneNode = Nothing

        Public Sub New()
            InitializeComponent()
        End Sub

        Private Sub Form1_Load(ByVal sender As Object, ByVal e As EventArgs) Handles MyBase.Load
            ' this is not necessarily the best place to load and 
            ' initialise Irrlicht, however, it does work. 
            Me.Show()
            runIrrlichtInWindowsFormTest(pictureBox1)
        End Sub

        Sub runIrrlichtInWindowsFormTest(ByVal c As Control)
            device = New IrrlichtDevice(Irrlicht.Video.DriverType.DIRECT3D9, New Dimension2D(c.Width, c.Height), 32, False, True, True, True, c.Handle)
            cam = device.SceneManager.AddCameraSceneNode(Nothing, New Vector3D, New Vector3D, -1)
            Dim anim As ISceneNodeAnimator = device.SceneManager.CreateFlyCircleAnimator(New Vector3D(0, 10, 0), 30.0F, 0.001F)
            cam.AddAnimator(anim)
            Dim cube As ISceneNode = device.SceneManager.AddTestSceneNode(25, Nothing, -1, New Vector3D)
            cube.SetMaterialTexture(0, device.VideoDriver.GetTexture("E:\Microsoft Visual Studio .NET\SDKs\irrlicht-1.1\media\rockwall.bmp"))
            ' draw everything
            ' Note, using device.WindowActive will not work on a control, since we don't
            ' really activate controls..
            While device.Run AndAlso c.Enabled
                device.VideoDriver.BeginScene(True, True, New Irrlicht.Video.Color(255, 0, 0, 50))
                device.SceneManager.DrawAll()
                device.GUIEnvironment.DrawAll()
                device.VideoDriver.EndScene()
            End While
        End Sub

        Private Sub button1_Click(ByVal sender As Object, ByVal e As EventArgs) Handles button1.Click
            pictureBox1.Enabled = False
            Me.Close()
        End Sub
        Private components As System.ComponentModel.IContainer = Nothing

        Protected Overloads Overrides Sub Dispose(ByVal disposing As Boolean)
            If disposing AndAlso (Not (components Is Nothing)) Then
                components.Dispose()
            End If
            MyBase.Dispose(disposing)
        End Sub

        Private Sub InitializeComponent()
            Me.pictureBox1 = New System.Windows.Forms.PictureBox
            Me.button1 = New System.Windows.Forms.Button
            Me.label1 = New System.Windows.Forms.Label
            Me.Button2 = New System.Windows.Forms.Button
            Me.Button3 = New System.Windows.Forms.Button
            CType(Me.pictureBox1, System.ComponentModel.ISupportInitialize).BeginInit()
            Me.SuspendLayout()
            '
            'pictureBox1
            '
            Me.pictureBox1.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D
            Me.pictureBox1.Location = New System.Drawing.Point(12, 37)
            Me.pictureBox1.Name = "pictureBox1"
            Me.pictureBox1.Size = New System.Drawing.Size(267, 207)
            Me.pictureBox1.TabIndex = 0
            Me.pictureBox1.TabStop = False
            '
            'button1
            '
            Me.button1.Location = New System.Drawing.Point(206, 250)
            Me.button1.Name = "button1"
            Me.button1.Size = New System.Drawing.Size(75, 23)
            Me.button1.TabIndex = 1
            Me.button1.Text = "Quit"
            Me.button1.UseVisualStyleBackColor = True
            '
            'label1
            '
            Me.label1.AutoSize = True
            Me.label1.Location = New System.Drawing.Point(12, 13)
            Me.label1.Name = "label1"
            Me.label1.Size = New System.Drawing.Size(94, 13)
            Me.label1.TabIndex = 2
            Me.label1.Text = "Irrlicht in a window"
            '
            'Button2
            '
            Me.Button2.Location = New System.Drawing.Point(18, 264)
            Me.Button2.Name = "Button2"
            Me.Button2.Size = New System.Drawing.Size(65, 19)
            Me.Button2.TabIndex = 3
            Me.Button2.Text = "Fov + 0.1"
            Me.Button2.UseVisualStyleBackColor = True
            '
            'Button3
            '
            Me.Button3.Location = New System.Drawing.Point(104, 264)
            Me.Button3.Name = "Button3"
            Me.Button3.Size = New System.Drawing.Size(67, 19)
            Me.Button3.TabIndex = 4
            Me.Button3.Text = "Fov - 0.1"
            Me.Button3.UseVisualStyleBackColor = True
            '
            'Form1
            '
            Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
            Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
            Me.ClientSize = New System.Drawing.Size(292, 293)
            Me.Controls.Add(Me.Button3)
            Me.Controls.Add(Me.Button2)
            Me.Controls.Add(Me.label1)
            Me.Controls.Add(Me.button1)
            Me.Controls.Add(Me.pictureBox1)
            Me.Name = "Form1"
            Me.Text = "Form1"
            CType(Me.pictureBox1, System.ComponentModel.ISupportInitialize).EndInit()
            Me.ResumeLayout(False)
            Me.PerformLayout()

        End Sub
        Private pictureBox1 As System.Windows.Forms.PictureBox
        Private WithEvents button1 As System.Windows.Forms.Button
        Private WithEvents label1 As System.Windows.Forms.Label

        <STAThread()> _
        Shared Sub Main()
            Application.EnableVisualStyles()
            Application.SetCompatibleTextRenderingDefault(False)
            Application.Run(New Form1)
        End Sub
        Friend WithEvents Button2 As System.Windows.Forms.Button
        Friend WithEvents Button3 As System.Windows.Forms.Button

        Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
            cam.FOV = cam.FOV + 0.1F
        End Sub

        Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button3.Click
            cam.FOV = cam.FOV - 0.1F
        End Sub
    End Class
End Namespace


