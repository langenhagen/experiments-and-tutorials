using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace HelloScreensaver
{
    public partial class frmscr : Form
    {
        public frmscr()
        {
            InitializeComponent();
        }


        private void frmscr_Load(object sender, EventArgs e)
        {
            //Cursor.Hide();
        }


        private void webBrowser_PreviewKeyDown(object sender, PreviewKeyDownEventArgs e)
        {
            this.Close();
        }


    }
}
