package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cz.johnyapps.eddiehostopky.stopwatch.presentation.StopwatchViewModel
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.stopwatch_title
import org.jetbrains.compose.resources.stringResource

private const val OFFENSE_COUNTDOWN_FROM_MS = 30_000L

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopwatchScreen(
    modifier: Modifier = Modifier,
    stopwatchViewModel: StopwatchViewModel = viewModel { StopwatchViewModel() }
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(Res.string.stopwatch_title))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.color.primary,
                    titleContentColor = AppTheme.color.onPrimary,
                )
            )
        }
    ) { paddingValues ->
        StopwatchScreenContents(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
private fun StopwatchScreenContents(
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        val matchStopwatchState = rememberStopwatchState()
        val offenseCountdownState = rememberCountdownState(OFFENSE_COUNTDOWN_FROM_MS)
        val penalty1StopwatchState = rememberStopwatchState()
        val penalty2StopwatchState = rememberStopwatchState()

        LaunchedEffect(
            matchStopwatchState,
            offenseCountdownState
        ) {
            snapshotFlow { matchStopwatchState.running }
                .collect { running ->
                    if (offenseCountdownState.running != running) {
                        offenseCountdownState.toggleRunning()
                    }
                }
        }

        LaunchedEffect(
            matchStopwatchState,
            penalty1StopwatchState,
            penalty2StopwatchState
        ) {
            snapshotFlow { matchStopwatchState.running }
                .collect {
                    penalty1StopwatchState.toggleBy(matchStopwatchState)
                    penalty2StopwatchState.toggleBy(matchStopwatchState)
                }
        }

        MatchStopwatch(
            state = matchStopwatchState,
            onMatchStopwatchReset = {
                offenseCountdownState.reset()
                penalty1StopwatchState.reset()
                penalty2StopwatchState.reset()
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider()

        OffenseCountdown(state = offenseCountdownState)

        HorizontalDivider()

        PenaltyStopwatch(
            state = penalty1StopwatchState,
            number = 1
        )

        HorizontalDivider()

        PenaltyStopwatch(
            state = penalty2StopwatchState,
            number = 2
        )
    }
}
