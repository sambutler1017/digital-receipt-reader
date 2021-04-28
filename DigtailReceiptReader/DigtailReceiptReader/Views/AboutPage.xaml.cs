using System;
using System.ComponentModel;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace DigtailReceiptReader.Views {
    public partial class AboutPage : ContentPage {
        public AboutPage() {
            InitializeComponent();
        }

        private void Button_Clicked(object sender, EventArgs e) {
            DisplayAlert("Title", "Hello World", "OK");
        }
    }
}