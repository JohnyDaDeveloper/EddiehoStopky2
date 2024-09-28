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
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import cz.johnyapps.eddiehostopky.theme.ui.AppThemedPreview
import kotlinx.datetime.Clock
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.ceil

@Composable
fun Countdown(
    state: CountdownState,
    modifier: Modifier = Modifier,
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
private fun CountdownPreview() {
    AppThemedPreview {
        val state = rememberCountdownState(
            fromValueMs = 20 * 60_000L
        )

        Countdown(
            state = state,
        )
    }
}

@Composable
fun rememberCountdownState(
    fromValueMs: Long,
    initialProgressMs: Long = 0
): CountdownState = remember(initialProgressMs, fromValueMs) {
    LiveCountdownState(
        initialProgressMs = initialProgressMs,
        fromValueMs = fromValueMs
    )
}

interface CountdownState {

    val text: String
    val running: Boolean
    val progressMs: Long

    fun invalidate()

    fun toggleRunning()

    fun reset()
}

class LiveCountdownState(
    initialProgressMs: Long,
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
