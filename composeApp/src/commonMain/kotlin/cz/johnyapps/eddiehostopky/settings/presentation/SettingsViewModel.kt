package cz.johnyapps.eddiehostopky.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.johnyapps.eddiehostopky.common.util.Logger
import cz.johnyapps.eddiehostopky.settings.domain.LoadSettingsUseCase
import cz.johnyapps.eddiehostopky.settings.presentation.model.SettingsUiState
import cz.johnyapps.eddiehostopky.settings.presentation.model.toSettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val loadSettingsUseCase: LoadSettingsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState.initial())
    val uiState: StateFlow<SettingsUiState> = _uiState

    init {
        loadSettings()
        collectSettingsChanges()
    }

    fun onPauseAllWhenMatchIsPausedClicked() {
        val currentState = _uiState.value

        if (currentState !is SettingsUiState.Ready) {
            Logger.warn(TAG, "onPauseAllWhenMatchIsPausedClicked: Unexpected state $currentState")
            return
        }

        changeState { currentState.copy(pauseAllWhenMatchIsPaused = !currentState.pauseAllWhenMatchIsPaused) }
    }

    fun onOffenseCountdownControlledByMatchChange() {
        val currentState = _uiState.value

        if (currentState !is SettingsUiState.Ready) {
            Logger.warn(TAG, "onOffenseCountdownControlledByMatchChange: Unexpected state $currentState")
            return
        }

        changeState {
            currentState.copy(
                offenseCountdownControlledByMatch = !currentState.offenseCountdownControlledByMatch
            )
        }
    }

    fun onRestartOffenseCountdownButtonAtLeftChange() {
        val currentState = _uiState.value

        if (currentState !is SettingsUiState.Ready) {
            Logger.warn(TAG, "onRestartOffenseCountdownButtonAtLeftChange: Unexpected state $currentState")
            return
        }

        changeState {
            currentState.copy(
                restartOffenseCountdownButtonAtLeft = !currentState.restartOffenseCountdownButtonAtLeft
            )
        }
    }

    fun onAlertBeforeOffenseEndSecondsChange(newValue: Int) {
        val currentState = _uiState.value

        if (currentState !is SettingsUiState.Ready) {
            Logger.warn(TAG, "onAlertBeforeOffenseEndSecondsChange: Unexpected state $currentState")
            return
        }

        changeState { currentState.copy(alertBeforeOffenseEndSeconds = newValue) }
    }

    private fun changeState(transform: SettingsUiState.() -> SettingsUiState) {
        _uiState.value = _uiState.value.transform()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            val settings = loadSettingsUseCase()
            changeState { settings.toSettingsUiState() }
        }
    }

    private fun collectSettingsChanges() {
        viewModelScope.launch {
            uiState
                .drop(2) // Dropping initial and loaded state
                .collect { state ->
                    // TODO: Save
                    Logger.info(TAG, "collectSettingsChanges: $state")
                }
        }
    }

    companion object {
        private const val TAG = "SettingsViewModel"
    }
}
