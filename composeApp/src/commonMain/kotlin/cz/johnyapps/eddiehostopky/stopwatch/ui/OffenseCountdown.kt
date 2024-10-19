package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.johnyapps.eddiehostopky.stopwatch.presentation.CountdownState
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.stopwatch_offense
import org.jetbrains.compose.resources.stringResource

@Composable
fun OffenseCountdown(
    state: CountdownState,
    switchButtons: Boolean,
    showPlayPauseButton: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        PlayPauseRestartButton(
            modifier = Modifier.fillMaxHeight(),
            state = when {
                !switchButtons -> PlayPauseRestartButtonState.Restart
                showPlayPauseButton -> PlayPauseRestartButtonState.PlayPause
                else -> PlayPauseRestartButtonState.None
            },
            running = state.running,
            onPlayPauseClick = { state.toggleRunning() },
            onResetClick = { state.reset() }
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = AppTheme.spacing.normal)
                    .padding(top = AppTheme.spacing.normal),
                text = stringResource(Res.string.stopwatch_offense)
            )

            Countdown(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                state = state,
                textStyle = AppTheme.typography.large,
            )
        }

        PlayPauseRestartButton(
            modifier = Modifier.fillMaxHeight(),
            state = when {
                switchButtons -> PlayPauseRestartButtonState.Restart
                showPlayPauseButton -> PlayPauseRestartButtonState.PlayPause
                else -> PlayPauseRestartButtonState.None
            },
            running = state.running,
            onPlayPauseClick = { state.toggleRunning() },
            onResetClick = { state.reset() }
        )
    }
}
