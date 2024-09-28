package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.text.TextStyle
import cz.johnyapps.eddiehostopky.common.util.digits
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import cz.johnyapps.eddiehostopky.theme.ui.AppThemedPreview
import kotlinx.datetime.Clock
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Stopwatch(
    modifier: Modifier = Modifier,
    state: StopwatchState = rememberStopwatchState(),
    textStyle: TextStyle = LocalTextStyle.current,
) {
    Box(
        modifier = modifier.padding(AppTheme.spacing.normal),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.drawWithCache {
                onDrawWithContent {
                    drawContent()
                    state.invalidate()
                }
            },
            text = state.text,
            style = textStyle
        )
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
    val running: Boolean
    val progressMs: Long

    fun invalidate()

    fun toggleRunning()

    fun reset()
}

class LiveStopwatchState(
    initialValueMs: Long,
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

fun StopwatchState.toggleBy(other: StopwatchState) {
    if (running != other.running) {
        if ((other.running && progressMs > 0) || !other.running) {
            toggleRunning()
        }
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
