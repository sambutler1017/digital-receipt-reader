using DigtailReceiptReader.ViewModels;
using System.ComponentModel;
using Xamarin.Forms;

namespace DigtailReceiptReader.Views {
    public partial class ItemDetailPage : ContentPage {
        public ItemDetailPage() {
            InitializeComponent();
            BindingContext = new ItemDetailViewModel();
        }
    }
}