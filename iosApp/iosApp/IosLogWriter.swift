import Foundation
import os
import ComposeApp

class IosLogWriter: LogWriter {
    func debug(tag: String, message: String, throwable: KotlinThrowable?) {
        let logger = Logger(
            subsystem: Bundle.main.bundleIdentifier!,
            category: tag
        )
        
        let fullMessage = createFullMessage(message: message, throwable: throwable)
        
        logger.debug("\(fullMessage, privacy: .public)")
    }
    
    func info(tag: String, message: String, throwable: KotlinThrowable?) {
        let logger = Logger(
            subsystem: Bundle.main.bundleIdentifier!,
            category: tag
        )
        
        let fullMessage = createFullMessage(message: message, throwable: throwable)
        
        logger.info("\(fullMessage, privacy: .public)")
    }
    
    func warn(tag: String, message: String, throwable: KotlinThrowable?) {
        let logger = Logger(
            subsystem: Bundle.main.bundleIdentifier!,
            category: tag
        )
        
        let fullMessage = createFullMessage(message: message, throwable: throwable)
        
        logger.warning("\(fullMessage, privacy: .public)")
    }
    
    func error(tag: String, message: String, throwable: KotlinThrowable?) {
        let logger = Logger(
            subsystem: Bundle.main.bundleIdentifier!,
            category: tag
        )
        
        let fullMessage = createFullMessage(message: message, throwable: throwable)
        
        logger.error("\(fullMessage, privacy: .public)")
    }
    
    private func createFullMessage(message: String, throwable: KotlinThrowable?) -> String {
        let errorMessage = throwable?.message
        
        guard let errorMessage else {
            return message
        }
        
        return message + ": " + errorMessage
    }
}
