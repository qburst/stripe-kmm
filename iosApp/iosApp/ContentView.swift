import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct StripeView: View {

    @State private var paymentResult: Result<Void, Error>? = nil
    @ObservedObject var demoG1 = DemoG1.shared
   

    var body: some View {
        VStack {
            Button("Pay") {
                DemoG1.updatePaymentSheetPresented(isPresented: true)
                
            }
            .sheet(isPresented: $demoG1.isPaymentSheetPresented) {
                PaymentSheetPresenter(
                    stripeSdk: StripeDemoSDK(),
                    completion: { result in
                        paymentResult = result
                        DemoG1.updatePaymentSheetPresented(isPresented: false)
                    }
                )
            }
            if let result = paymentResult {
                switch result {
                case .success:
                    Text("Payment succeeded")
                case .failure(let error):
                    Text("Payment failed: \(error.localizedDescription)")
                }
            }
        }
    }
}
struct ContentView: View {
    var body: some View {
        ZStack {
            ComposeView()
            StripeView()
        }
    }
}



