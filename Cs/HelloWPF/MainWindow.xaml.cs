using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace HelloWPF
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            Console.WriteLine("JO Eh!");
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            button.Content = "JO";
            label.Content = "Jo E!";

            var items = comboBox.Items;

            ComboBoxItem cbi;
            cbi = new ComboBoxItem();
            cbi.Content = "NAse";
            
            //items.Add(cbi);
            cbi.Content = "APP";
            items.Add(cbi);
            cbi = new ComboBoxItem();
            cbi.Content = "RAUFEL";
            items.Add(cbi);
        }

        private void comboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            
        }
    }
}
