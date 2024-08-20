import SwiftUI
import ComposeApp


@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoin(stripeRepository: StripeSdk())
    }
    
    @State private var cardData: [String: Any?] = [:]
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
