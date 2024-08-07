import SwiftUI
import ComposeApp



@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoin(stripeRepository: StripeSdk())
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
