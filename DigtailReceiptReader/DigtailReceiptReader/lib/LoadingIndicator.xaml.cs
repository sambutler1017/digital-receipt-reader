using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace DigtailReceiptReader.lib {
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class LoadingIndicator : ContentView {
        public readonly static BindableProperty EnabledProperty = BindableProperty.Create("Enabled", typeof(bool), typeof(LoadingIndicator), true);
        public readonly static BindableProperty LoadingColorProperty = BindableProperty.Create("LoadingColor", typeof(string), typeof(LoadingIndicator), "Orange");

        public LoadingIndicator() {
            InitializeComponent();
        }

        public bool Enabled {
            get => (bool)GetValue(EnabledProperty);
            set => SetValue(EnabledProperty, value);
        }

        public string LoadingColor {
            get => (string)GetValue(LoadingColorProperty);
            set => SetValue(LoadingColorProperty, value);
        }
    }
}