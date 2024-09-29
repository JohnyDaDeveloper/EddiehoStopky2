package cz.johnyapps.eddiehostopky.stopwatch.presentation

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.johnyapps.eddiehostopky.stopwatch.presentation.model.StopwatchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StopwatchViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(StopwatchUiState.initial())
    val uiState: StateFlow<StopwatchUiState> = _uiState

    init {
        collectMatchState()
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

    private suspend fun collectMatchRunning(
        uiState: StopwatchUiState,
    ) {
        snapshotFlow { uiState.matchStopwatchState.running }
            .collect { matchRunning ->
                if (matchRunning != uiState.offenseCountdownState.running) {
                    uiState.offenseCountdownState.toggleRunning()
                }

                uiState.penalty1StopwatchState.toggleBy(uiState.matchStopwatchState)
                uiState.penalty2StopwatchState.toggleBy(uiState.matchStopwatchState)
            }
    }
}
