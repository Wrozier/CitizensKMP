import SwiftUI
import Shared

@main
struct CitizensApp: App {
    init() {
        KoinHelper().initKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
