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
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.stopwatch_penalty
import org.jetbrains.compose.resources.stringResource

@Composable
fun PenaltyStopwatch(
    number: Int,
    modifier: Modifier = Modifier,
    state: StopwatchState = rememberStopwatchState()
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        PlayPauseButton(
            modifier = Modifier
                .fillMaxHeight(),
            running = state.running,
            onClick = state::toggleRunning,
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = AppTheme.spacing.normal)
                    .padding(top = AppTheme.spacing.normal),
                text = stringResource(Res.string.stopwatch_penalty, number)
            )

            Stopwatch(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                state = state,
                textStyle = AppTheme.typography.large,
            )
        }

        RestartButton(
            modifier = Modifier
                .fillMaxHeight(),
            onClick = state::reset,
        )
    }
}
