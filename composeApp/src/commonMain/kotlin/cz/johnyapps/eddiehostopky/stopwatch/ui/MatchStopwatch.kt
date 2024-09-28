package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cz.johnyapps.eddiehostopky.common.ui.BoxButton
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.stopwatch_offense
import org.jetbrains.compose.resources.stringResource

@Composable
fun MatchStopwatch(
    onMatchStopwatchReset: () -> Unit,
    modifier: Modifier = Modifier,
    state: StopwatchState = rememberStopwatchState(),
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .padding(horizontal = AppTheme.spacing.normal)
                .padding(top = AppTheme.spacing.normal),
            text = stringResource(Res.string.stopwatch_offense)
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

            PlayPauseButton(
                modifier = Modifier.fillMaxWidth(animatedFraction),
                running = state.running,
                onClick = state::toggleRunning
            )

            BoxButton(
                modifier = Modifier.fillMaxWidth(),
                icon = Icons.Filled.RestartAlt,
                iconTint = AppTheme.color.onSecondary,
                color = AppTheme.color.secondary,
                onClick = {
                    state.reset()
                    onMatchStopwatchReset()
                },
            )
        }
    }
}
