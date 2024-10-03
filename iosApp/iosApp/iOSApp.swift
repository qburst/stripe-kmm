import SwiftUI
import ComposeApp
import SwiftKMMStripeKit

@main
struct iOSApp: App {
    init() {
        StripeHelpersKt.setStripe(stripeRepository:  StripeSdk())
    }

    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
