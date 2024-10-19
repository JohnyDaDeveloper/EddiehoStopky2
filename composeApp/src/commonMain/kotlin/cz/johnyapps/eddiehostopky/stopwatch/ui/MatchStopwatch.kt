package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cz.johnyapps.eddiehostopky.stopwatch.presentation.StopwatchState
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.stopwatch_match
import org.jetbrains.compose.resources.stringResource

@Composable
fun MatchStopwatch(
    switchButtons: Boolean,
    onMatchStopwatchReset: () -> Unit,
    modifier: Modifier = Modifier,
    state: StopwatchState = rememberStopwatchState(),
) {
    var confirmResetDialogOpen by remember { mutableStateOf(false) }

    if (confirmResetDialogOpen) {
        ConfirmResetDialog(
            onConfirmClick = {
                state.reset()
                confirmResetDialogOpen = false
                onMatchStopwatchReset()
            },
            onDismissRequest = { confirmResetDialogOpen = false }
        )
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .padding(horizontal = AppTheme.spacing.normal)
                .padding(top = AppTheme.spacing.normal),
            text = stringResource(Res.string.stopwatch_match)
        )

        Stopwatch(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            textStyle = AppTheme.typography.extraLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val targetFraction = if (state.running) { 1f } else { 0.5f }
            val animatedFraction by animateFloatAsState(targetFraction)

            if (!switchButtons) {
                PlayPauseButton(
                    modifier = Modifier.fillMaxWidth(animatedFraction),
                    running = state.running,
                    onClick = state::toggleRunning
                )
            }

            RestartButton(
                modifier = if (switchButtons) {
                    Modifier.fillMaxWidth(1 - animatedFraction)
                } else {
                    Modifier.fillMaxWidth()
                },
                onClick = {
                    if (state.progressMs > 0) {
                        confirmResetDialogOpen = true
                    }
                },
            )

            if (switchButtons) {
                PlayPauseButton(
                    modifier = Modifier.fillMaxWidth(),
                    running = state.running,
                    onClick = state::toggleRunning
                )
            }
        }
    }
}
