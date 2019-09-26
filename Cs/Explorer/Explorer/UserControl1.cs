using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Explorer
{
    public partial class Explorer : WebBrowser
    {
        public Explorer()
        {
            InitializeComponent();
        }


        [SuppressUnmanagedCodeSecurity]
        class NewExplorer : WebBrowser
        {
            [DllImport("shell32.dll")]
            private static extern int SHGetSpecialFolderLocation(IntPtr hwnd, int csidl, ref IntPtr ppidl);
            [DllImport("kernel32.dll")]
            private static extern uint LocalSize(IntPtr hMem);

            /// <summary>
            /// Navigate to a special folder.
            /// </summary>
            /// <param name="csidl">The CSIDL of the special folder.</param>
            public void Navigate2CSIDL(CSIDLValues csidl)
            {
                const int S_OK = 0;

                IntPtr pidl = IntPtr.Zero;

                if (SHGetSpecialFolderLocation(IntPtr.Zero, (int)csidl, ref pidl) == S_OK)
                {
                    uint cbpidl = LocalSize(pidl);
                    if (cbpidl > 0)
                    {
                        byte[] abpidl = new byte[cbpidl];
                        Marshal.Copy(pidl, abpidl, 0, ((int)cbpidl - 1));
                        object location = (object)abpidl;
                        Marshal.FreeCoTaskMem(pidl);

                        try
                        {
                            object nil = Type.Missing;
                            ((SHDocVw.WebBrowser)base.ActiveXInstance).Navigate2(ref location, ref nil, ref nil, ref nil, ref nil);
                        }
                        catch (COMException exception)
                        {
                            if (exception.ErrorCode != -2147023673 /*Operation was canceled by the user*/)
                            {
                                throw;
                            }
                        }
                    }
                }
                else
                {
                    throw new ArgumentOutOfRangeException();
                }
            }

            /// <summary>
            /// Returns the shell folder object displayed in the webbrowser control.
            /// </summary>
            public Shell32.Folder2 Folder
            {
                get
                {
                    IShellFolderViewDual2 folderview = this.FolderView;
                    if (folderview != null)
                    {
                        return folderview.Folder as Folder2;
                    }
                    else
                    {
                        return null;
                    }
                }
            }

            /// <summary>
            /// Returns the shell folderview object displayed in the webbrowser control.
            /// </summary>
            public Shell32.IShellFolderViewDual2 FolderView
            {
                get
                {
                    IShellFolderViewDual2 ret;
                    ret = ((SHDocVw.WebBrowser)base.ActiveXInstance).Document as IShellFolderViewDual2;
                    return ret;
                }
            }

            /// <summary>
            /// Tries to set the views to list views.
            /// </summary>
            /// <returns>Returns TRUE if operation was successful, otherwise returns FALSE</returns>
            public bool setViewToListView()
            {
                IShellFolderViewDual2 fview = this.FolderView;
                if (fview == null)
                    return false;

                FolderviewMode viewmode = (FolderviewMode)(FolderviewMode.List);
                fview.CurrentViewMode = (uint)viewmode;
                return true;
            }

        }


    }
}
