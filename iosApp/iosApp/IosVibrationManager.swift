import AudioToolbox
import ComposeApp
import UIKit
import CoreHaptics
import os

class IosVibrationManager : VibrationManager {
    
    private var hapticPlayer: CHHapticPatternPlayer? = nil

    private let logger = Logger(
        subsystem: Bundle.main.bundleIdentifier!,
        category: "IosVibrationManager"
    )

    init(hapticEngine: CHHapticEngine?) {
        let duration = 1.0
        let intensity = CHHapticEventParameter(parameterID: .hapticIntensity, value: 1.0)
        let sharpness = CHHapticEventParameter(parameterID: .hapticSharpness, value: 0.5)
        let hapticEvent = CHHapticEvent(eventType: .hapticContinuous, parameters: [intensity, sharpness], relativeTime: 0, duration: duration)

        do {
            let pattern = try CHHapticPattern(events: [hapticEvent], parameters: [])
            hapticPlayer = try hapticEngine?.makePlayer(with: pattern)
        } catch let error {
            logger.error("Failed to play haptic pattern: \(error.localizedDescription)")
        }
    }

    func click() {
        UIDevice.vibrate()
    }
    
    func alert() {
        do {
            try hapticPlayer?.start(atTime: 0)
        } catch let error {
            print("Failed to vibrate: \(error)")
        }
    }
}

extension UIDevice {
    static func vibrate() {
        UIImpactFeedbackGenerator(style: .heavy).impactOccurred()
    }
}
