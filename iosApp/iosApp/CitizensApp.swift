import SwiftUI
import Shared

@main
struct CitizensApp: App {
    init() {
        KoinHelper().start()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
