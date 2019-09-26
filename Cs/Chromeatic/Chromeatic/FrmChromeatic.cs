using System;
using System.Windows.Forms;

namespace Chromeatic
{
    public partial class frmChromeatic : Form
    {
        public frmChromeatic()
        {
            InitializeComponent();
        }

        private void txtCommand_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Escape ||
                e.KeyCode == Keys.W && e.Control)
            {
                Application.Exit();
            }
        }

        private void btnGo_Click(object sender, EventArgs e)
        {
            string chromePath = "\"" + @"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" + "\"";
            //chromePath = chromePath + txtCommand.Text;
            //try
            {
                System.Diagnostics.Process.Start(chromePath );
            }
            //catch (Exception exc)
            //{
            //    /* DO NOTHING */
            //}
            Application.Exit();
        }

    }
}
