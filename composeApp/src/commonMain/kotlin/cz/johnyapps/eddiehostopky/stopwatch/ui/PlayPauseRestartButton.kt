package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlayPauseRestartButton(
    state: PlayPauseRestartButtonState,
    running: Boolean,
    onPlayPauseClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        PlayPauseRestartButtonState.PlayPause -> {
            PlayPauseButton(
                modifier = modifier,
                running = running,
                onClick = onPlayPauseClick
            )
        }

        PlayPauseRestartButtonState.Restart -> {
            RestartButton(
                modifier = modifier,
                onClick = onResetClick
            )
        }

        PlayPauseRestartButtonState.None -> {
            // Nothing
        }
    }
}

enum class PlayPauseRestartButtonState {
    PlayPause,
    Restart,
    None
}
