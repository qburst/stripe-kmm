import UIKit
import SwiftUI
import ComposeApp
import SwiftKMMStripeKit

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
      MainViewControllerKt.MainViewController()
    }
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    init() {
        StripeHelpersKt.setStripe(stripeRepository:  StripeSdk())
    }
    
    var body: some View {
        ZStack {
            ComposeView()
        }
    }
}





