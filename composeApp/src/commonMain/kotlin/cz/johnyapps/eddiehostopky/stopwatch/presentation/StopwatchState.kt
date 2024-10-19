package cz.johnyapps.eddiehostopky.stopwatch.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.johnyapps.eddiehostopky.common.util.digits
import kotlinx.datetime.Clock

interface StopwatchState {

    val text: String
    val running: Boolean
    val progressMs: Long

    fun invalidate()

    fun toggleRunning()

    fun reset()
}

class LiveStopwatchState(
    initialValueMs: Long = 0,
) : StopwatchState {

    override var progressMs by mutableLongStateOf(initialValueMs)

    private var startTime: Long = 0L

    private var _running by mutableStateOf(false)
    override val running: Boolean get() = _running

    private var _text by mutableStateOf(progressMs.toStopwatchText())
    override val text: String get() = _text

    override fun invalidate() {
        if (running) {
            progressMs = Clock.System.now().toEpochMilliseconds() - startTime
        }

        _text = progressMs.toStopwatchText()
    }

    override fun toggleRunning() {
        if (running) {
            progressMs = Clock.System.now().toEpochMilliseconds() - startTime
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

fun Long.toStopwatchText(): String {
    val minutes = this / 60_000
    val seconds = this / 1_000 % 60
    val milliseconds = this % 1_000

    val stringMilliseconds = when {
        milliseconds < 10 -> "0$milliseconds"
        milliseconds < 100 -> "$milliseconds"
        else -> "$milliseconds".take(2)
    }

    return "${minutes.digits(2)}:${seconds.digits(2)}:$stringMilliseconds"
}
