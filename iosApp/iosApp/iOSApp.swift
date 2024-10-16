import SwiftUI
import ComposeApp
import CoreHaptics
import os

@main
struct iOSApp: App {
    
    private let logger = Logger(
        subsystem: Bundle.main.bundleIdentifier!,
        category: "IosVibrationManager"
    )
    
    init() {
        let hapticEngine: CHHapticEngine?
        
        do {
            let engine = try CHHapticEngine()
            try engine.start()
            
            hapticEngine = engine
        } catch let error {
            logger.error("Engine Error: \(error)")
            hapticEngine = nil
        }
        
        KoinHelperKt.doInitKoin(
            createVibrationManager: { IosVibrationManager(hapticEngine: hapticEngine) },
            createLogWriter: { IosLogWriter() }
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
