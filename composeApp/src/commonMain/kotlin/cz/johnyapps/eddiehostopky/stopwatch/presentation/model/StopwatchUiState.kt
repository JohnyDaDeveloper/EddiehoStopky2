package cz.johnyapps.eddiehostopky.stopwatch.presentation.model

import cz.johnyapps.eddiehostopky.stopwatch.presentation.CountdownState
import cz.johnyapps.eddiehostopky.stopwatch.presentation.LiveCountdownState
import cz.johnyapps.eddiehostopky.stopwatch.presentation.LiveStopwatchState
import cz.johnyapps.eddiehostopky.stopwatch.presentation.StopwatchState
import cz.johnyapps.eddiehostopky.stopwatch.presentation.StopwatchViewModel

data class StopwatchUiState(
    val matchStopwatchState: StopwatchState,
    val offenseCountdownState: CountdownState,
    val penalty1StopwatchState: StopwatchState,
    val penalty2StopwatchState: StopwatchState,
    val switchOffenseCountdownButtons: Boolean,
    val showOffenseCountdownPlayPauseButton: Boolean,
) {

    companion object {
        fun initial(): StopwatchUiState {
            return StopwatchUiState(
                matchStopwatchState = LiveStopwatchState(),
                offenseCountdownState = LiveCountdownState(fromValueMs = StopwatchViewModel.OFFENSE_COUNTDOWN_FROM_MS),
                penalty1StopwatchState = LiveStopwatchState(),
                penalty2StopwatchState = LiveStopwatchState(),
                switchOffenseCountdownButtons = false,
                showOffenseCountdownPlayPauseButton = false,
            )
        }
    }
}
