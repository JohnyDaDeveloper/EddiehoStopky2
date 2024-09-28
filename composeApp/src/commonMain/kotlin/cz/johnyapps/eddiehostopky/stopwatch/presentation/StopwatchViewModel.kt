package cz.johnyapps.eddiehostopky.stopwatch.presentation

import androidx.lifecycle.ViewModel
import cz.johnyapps.eddiehostopky.stopwatch.presentation.model.StopwatchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StopwatchViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(StopwatchUiState.initial())
    val uiState: StateFlow<StopwatchUiState> = _uiState

    private fun changeState(transform: StopwatchUiState.() -> StopwatchUiState) {
        _uiState.value = _uiState.value.transform()
    }
}