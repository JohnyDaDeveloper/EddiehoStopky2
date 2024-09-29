import AudioToolbox
import ComposeApp
import UIKit

class IosVibrationManager : VibrationManager {
    
    func click() {
        UIDevice.vibrate()
    }
}

extension UIDevice {
    static func vibrate() {
        UIImpactFeedbackGenerator(style: .medium).impactOccurred()
    }
}
