using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Configuration;
using System.Windows.Forms;

namespace playground_cs
{

    public class Program
    {
        [STAThread] // for open file dialog
        static void Main(string[] args)
        {

            Application.Run(new Form1());
        }



    }


}