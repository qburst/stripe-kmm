import SwiftUI
import ComposeApp
import SwiftKMMStripeKit

@main
struct iOSApp: App {
    init() {
      let stripeSdk = StripeSdk()
        KoinKt.doInitKoin(stripeRepository: stripeSdk)
    }

    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
