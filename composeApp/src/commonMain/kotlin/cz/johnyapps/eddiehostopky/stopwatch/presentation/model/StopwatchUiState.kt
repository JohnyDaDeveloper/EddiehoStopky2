package cz.johnyapps.eddiehostopky.stopwatch.presentation.model

import cz.johnyapps.eddiehostopky.stopwatch.presentation.CountdownState
import cz.johnyapps.eddiehostopky.stopwatch.presentation.LiveCountdownState
import cz.johnyapps.eddiehostopky.stopwatch.presentation.LiveStopwatchState
import cz.johnyapps.eddiehostopky.stopwatch.presentation.StopwatchState
import cz.johnyapps.eddiehostopky.stopwatch.presentation.StopwatchViewModel

sealed interface StopwatchUiState {

    data object Loading : StopwatchUiState

    data class Ready(
        val matchStopwatchState: StopwatchState,
        val offenseCountdownState: CountdownState,
        val penalty1StopwatchState: StopwatchState,
        val penalty2StopwatchState: StopwatchState,
        val switchButtons: Boolean,
        val showOffenseCountdownPlayPauseButton: Boolean,
    ) : StopwatchUiState

    companion object {
        fun initial(): StopwatchUiState {
            return Loading
        }

        fun firstReady(
            switchButtons: Boolean,
            showOffenseCountdownPlayPauseButton: Boolean,
        ): Ready {
            return Ready(
                matchStopwatchState = LiveStopwatchState(),
                offenseCountdownState = LiveCountdownState(fromValueMs = StopwatchViewModel.OFFENSE_COUNTDOWN_FROM_MS),
                penalty1StopwatchState = LiveStopwatchState(),
                penalty2StopwatchState = LiveStopwatchState(),
                switchButtons = switchButtons,
                showOffenseCountdownPlayPauseButton = showOffenseCountdownPlayPauseButton,
            )
        }
    }
}
