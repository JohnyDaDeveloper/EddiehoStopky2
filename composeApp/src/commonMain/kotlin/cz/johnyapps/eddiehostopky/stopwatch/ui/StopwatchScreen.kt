package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.johnyapps.eddiehostopky.common.di.koinViewModel
import cz.johnyapps.eddiehostopky.common.ui.FullScreenLoading
import cz.johnyapps.eddiehostopky.stopwatch.presentation.StopwatchViewModel
import cz.johnyapps.eddiehostopky.stopwatch.presentation.model.StopwatchUiState

@Composable
fun StopwatchScreen(
    modifier: Modifier = Modifier,
    viewModel: StopwatchViewModel = koinViewModel(::StopwatchViewModel),
    onReady: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    StopwatchScreenContents(
        modifier = modifier.fillMaxSize(),
        uiState = uiState,
        onResetMatchClick = viewModel::onResetMatchClick,
        onReady = onReady,
    )
}

@Composable
private fun StopwatchScreenContents(
    modifier: Modifier,
    uiState: StopwatchUiState,
    onResetMatchClick: () -> Unit,
    onReady: () -> Unit,
) {
    LaunchedEffect(onReady, uiState) {
        if (uiState is StopwatchUiState.Ready) {
            onReady()
        }
    }

    when (uiState) {
        is StopwatchUiState.Loading -> {
            FullScreenLoading(modifier = modifier)
        }

        is StopwatchUiState.Ready -> {
            StopwatchScreenReadyContents(
                modifier = modifier,
                uiState = uiState,
                onResetMatchClick = onResetMatchClick,
            )
        }
    }
}

@Composable
private fun StopwatchScreenReadyContents(
    modifier: Modifier,
    uiState: StopwatchUiState.Ready,
    onResetMatchClick: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        MatchStopwatch(
            state = uiState.matchStopwatchState,
            onMatchStopwatchReset = onResetMatchClick,
        )

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider()

        OffenseCountdown(
            state = uiState.offenseCountdownState,
            switchButtons = uiState.switchOffenseCountdownButtons,
            showPlayPauseButton = uiState.showOffenseCountdownPlayPauseButton,
        )

        HorizontalDivider()

        PenaltyStopwatch(
            state = uiState.penalty1StopwatchState,
            number = 1
        )

        HorizontalDivider()

        PenaltyStopwatch(
            state = uiState.penalty2StopwatchState,
            number = 2
        )
    }
}
