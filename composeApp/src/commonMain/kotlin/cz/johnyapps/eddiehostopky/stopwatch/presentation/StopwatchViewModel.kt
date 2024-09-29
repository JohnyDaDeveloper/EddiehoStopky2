package cz.johnyapps.eddiehostopky.stopwatch.presentation

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.johnyapps.eddiehostopky.settings.domain.GetSettingsFlowUseCase
import cz.johnyapps.eddiehostopky.stopwatch.presentation.model.StopwatchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class StopwatchViewModel(
    private val getSettingsFlowUseCase: GetSettingsFlowUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(StopwatchUiState.initial())
    val uiState: StateFlow<StopwatchUiState> = _uiState

    init {
        collectMatchState()
        collectSettings()
    }

    fun onResetMatchClick() {
        val currentState = uiState.value
        currentState.offenseCountdownState.reset()
        currentState.penalty1StopwatchState.reset()
        currentState.penalty2StopwatchState.reset()
    }

    private fun changeState(transform: StopwatchUiState.() -> StopwatchUiState) {
        _uiState.value = _uiState.value.transform()
    }

    private fun collectMatchState() {
        viewModelScope.launch {
            uiState.collectLatest { state ->
                collectMatchRunning(state)
            }
        }
    }

    private fun collectSettings() {
        viewModelScope.launch {
            getSettingsFlowUseCase().collect { settings ->
                changeState {
                    copy(
                        switchOffenseCountdownButtons = settings.restartOffenseCountdownButtonAtLeft,
                        showOffenseCountdownPlayPauseButton = !settings.offenseCountdownControlledByMatch
                    )
                }
            }
        }
    }

    private suspend fun collectMatchRunning(
        uiState: StopwatchUiState,
    ) {
        snapshotFlow { uiState.matchStopwatchState.running }
            .collect { matchRunning ->
                val currentSettings = getSettingsFlowUseCase().first()
                val shouldUnpause = currentSettings.pauseAllWhenMatchIsPaused || matchRunning

                if (
                    matchRunning != uiState.offenseCountdownState.running &&
                    currentSettings.offenseCountdownControlledByMatch &&
                    shouldUnpause
                ) {
                    uiState.offenseCountdownState.toggleRunning()
                }

                if (shouldUnpause) {
                    uiState.penalty1StopwatchState.toggleBy(uiState.matchStopwatchState)
                    uiState.penalty2StopwatchState.toggleBy(uiState.matchStopwatchState)
                }
            }
    }

    companion object {
        const val OFFENSE_COUNTDOWN_FROM_MS = 30_000L
    }
}
