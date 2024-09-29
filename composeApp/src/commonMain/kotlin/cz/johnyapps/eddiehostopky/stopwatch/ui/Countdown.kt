package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.text.TextStyle
import cz.johnyapps.eddiehostopky.stopwatch.presentation.CountdownState
import cz.johnyapps.eddiehostopky.stopwatch.presentation.LiveCountdownState
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import cz.johnyapps.eddiehostopky.theme.ui.AppThemedPreview
import org.jetbrains.compose.ui.tooling.preview.Preview

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
