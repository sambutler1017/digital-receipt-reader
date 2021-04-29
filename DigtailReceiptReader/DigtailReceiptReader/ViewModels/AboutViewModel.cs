using System;
using System.Windows.Input;
using Xamarin.Essentials;
using Xamarin.Forms;

namespace DigtailReceiptReader.ViewModels {
    public class AboutViewModel : BaseViewModel {
        public static bool isLoading = true;
        public bool IsLoading { get => isLoading; set => SetProperty(ref isLoading, value); }

        public Command ToggleLoadingCommand { get; }

        public AboutViewModel() {
            Title = "About";
            ToggleLoadingCommand = new Command(ToggleLoading);
            OpenWebCommand = new Command(async () => await Browser.OpenAsync("https://aka.ms/xamarin-quickstart"));
        }

        public void ToggleLoading() {
            IsLoading = !IsLoading;
        }

        public ICommand OpenWebCommand { get; }
    }
}