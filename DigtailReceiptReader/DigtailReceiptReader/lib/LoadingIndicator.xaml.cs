using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace DigtailReceiptReader.lib {
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class LoadingIndicator : ContentView {
        public readonly static BindableProperty IsLoadingProperty    = BindableProperty.Create("IsLoading", typeof(bool), typeof(LoadingIndicator), false);
        public readonly static BindableProperty LoadingColorProperty = BindableProperty.Create("LoadingColor", typeof(string), typeof(LoadingIndicator), "Orange");

        public LoadingIndicator() {
            InitializeComponent();
        }

        public bool IsLoading {
            get => (bool)GetValue(IsLoadingProperty);
            set => SetValue(IsLoadingProperty, value);
        }

        public string LoadingColor {
            get => (string)GetValue(LoadingColorProperty);
            set => SetValue(LoadingColorProperty, value);
        }
    }
}