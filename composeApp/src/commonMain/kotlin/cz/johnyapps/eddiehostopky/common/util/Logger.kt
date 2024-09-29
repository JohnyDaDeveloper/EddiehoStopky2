package cz.johnyapps.eddiehostopky.common.util

import org.koin.mp.KoinPlatform

object Logger {

    private val logWriter by lazy { KoinPlatform.getKoin().get<LogWriter>() }

    fun debug(tag: String, message: String, throwable: Throwable? = null) {
        logWriter.debug(tag, message, throwable)
    }

    fun info(tag: String, message: String, throwable: Throwable? = null) {
        logWriter.info(tag, message, throwable)
    }

    fun warn(tag: String, message: String, throwable: Throwable? = null) {
        logWriter.warn(tag, message, throwable)
    }

    fun error(tag: String, message: String, throwable: Throwable? = null) {
        logWriter.error(tag, message, throwable)
    }
}

interface LogWriter {

    fun debug(tag: String, message: String, throwable: Throwable? = null)

    fun info(tag: String, message: String, throwable: Throwable? = null)

    fun warn(tag: String, message: String, throwable: Throwable? = null)

    fun error(tag: String, message: String, throwable: Throwable? = null)
}
