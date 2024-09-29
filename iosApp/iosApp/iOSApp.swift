import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    init() {
        KoinHelperKt.doInitKoin(
            createVibrationManager: { IosVibrationManager() }
        )
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .overlay(alignment: .top) {
                    Color(.statusBar)
                        .background(.regularMaterial)
                        .ignoresSafeArea(edges: .top)
                        .frame(height: 0)
                }
        }
    }
}
