package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import cz.johnyapps.eddiehostopky.theme.ui.AppThemedPreview
import kotlinx.datetime.Clock
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Stopwatch(
    modifier: Modifier = Modifier,
    state: StopwatchState = rememberStopwatchState()
) {
    Box(
        modifier = modifier.padding(AppTheme.spacing.normal),
        contentAlignment = Alignment.Center,
    ) {
        Text(state.text)
    }
}

@Preview
@Composable
private fun StopwatchPreview() {
    AppThemedPreview {
        Stopwatch()
    }
}

@Composable
fun rememberStopwatchState(
    initialValueMs: Long = 0
): StopwatchState = remember(initialValueMs) {
    LiveStopwatchState(initialValueMs)
}

interface StopwatchState {

    val text: String

    fun invalidate()

    fun start()

    fun pause()
}

class LiveStopwatchState(
    initialValueMs: Long,
) : StopwatchState {

    private var valueMs by mutableLongStateOf(initialValueMs)
    private var running = false
    private var startTime: Long = 0L

    override val text: String
        get() {
            val minutes = valueMs / 60_000
            val seconds = valueMs / 1_000 % 60
            val milliseconds = valueMs % 1_000

            return "${minutes.digits(2)}:${seconds.digits(2)}:${milliseconds.digits(3)}"
        }

    override fun invalidate() {
        if (running) {
            valueMs = Clock.System.now().toEpochMilliseconds() - startTime
        }
    }

    override fun start() {
        startTime = Clock.System.now().toEpochMilliseconds() - valueMs
        running = true
    }

    override fun pause() {
        running = false
    }
}

fun Long.digits(digits: Int): String {
    var text = "$this"

    while (text.length < digits) {
        text = "0$text"
    }

    return text
}


