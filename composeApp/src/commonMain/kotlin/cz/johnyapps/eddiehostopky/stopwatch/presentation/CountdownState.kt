package cz.johnyapps.eddiehostopky.stopwatch.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.datetime.Clock
import kotlin.math.ceil

interface CountdownState {

    val text: String
    val running: Boolean
    val progressMs: Long

    fun invalidate()

    fun toggleRunning()

    fun reset()
}

class LiveCountdownState(
    initialProgressMs: Long = 0,
    private val fromValueMs: Long,
) : CountdownState {

    override var progressMs by mutableLongStateOf(initialProgressMs)
    private var startTime: Long = 0L

    private var _running by mutableStateOf(false)
    override val running: Boolean get() = _running

    private var _text by mutableStateOf(progressMs.toCountdownText(fromValueMs))
    override val text: String get() = _text

    override fun invalidate() {
        if (running) {
            progressMs = (Clock.System.now().toEpochMilliseconds() - startTime)
                .coerceAtMost(fromValueMs)
        }

        _text = progressMs.toCountdownText(fromValueMs)
    }

    override fun toggleRunning() {
        if (running) {
            _running = false
        } else {
            startTime = Clock.System.now().toEpochMilliseconds() - progressMs
            _running = true
            invalidate()
        }
    }

    override fun reset() {
        startTime = Clock.System.now().toEpochMilliseconds()
        progressMs = 0
        invalidate()
    }
}

fun Long.toCountdownText(fromValueMs: Long): String {
    val remainingMs = fromValueMs - this

    val seconds = ceil(remainingMs / 1000f)
        .toInt()

    return "${seconds}s"
}
