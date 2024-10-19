package cz.johnyapps.eddiehostopky.stopwatch.presentation

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.johnyapps.eddiehostopky.common.ui.VibrationManager
import cz.johnyapps.eddiehostopky.common.util.Constants
import cz.johnyapps.eddiehostopky.common.util.Logger
import cz.johnyapps.eddiehostopky.settings.domain.GetSettingsFlowUseCase
import cz.johnyapps.eddiehostopky.stopwatch.domain.CreateAlertBeforeOffenseEndFlowUseCase
import cz.johnyapps.eddiehostopky.stopwatch.presentation.model.StopwatchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class StopwatchViewModel(
    private val getSettingsFlowUseCase: GetSettingsFlowUseCase,
    private val createAlertBeforeOffenseEndFlowUseCase: CreateAlertBeforeOffenseEndFlowUseCase,
    private val vibrationManager: VibrationManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(StopwatchUiState.initial())
    val uiState: StateFlow<StopwatchUiState> = _uiState

    init {
        collectMatchState()
        collectSettings()
        collectOffenseStateForAlert()
    }

    fun onResetMatchClick() {
        val currentState = uiState.value

        if (currentState !is StopwatchUiState.Ready) {
            Logger.warn(TAG, "onResetMatchClick: Unexpected state: $currentState")
            return
        }

        currentState.offenseCountdownState.reset()
        currentState.penalty1StopwatchState.reset()
        currentState.penalty2StopwatchState.reset()
    }

    private fun collectOffenseStateForAlert() {
        viewModelScope.launch {
            uiState.filterIsInstance<StopwatchUiState.Ready>()
                .collectLatest { currentState ->
                    val offenseRemainingMsFlow = snapshotFlow { currentState.offenseCountdownState.progressMs }
                        .map { OFFENSE_COUNTDOWN_FROM_MS - it }

                    createAlertBeforeOffenseEndFlowUseCase(offenseRemainingMsFlow)
                        .collect {
                            vibrationManager.alert()
                        }
                }
        }
    }

    private fun collectMatchState() {
        viewModelScope.launch {
            uiState.filterIsInstance<StopwatchUiState.Ready>()
                .collectLatest { state ->
                    synchronizeGameStates(state)
                }
        }
    }

    private fun collectSettings() {
        viewModelScope.launch {
            // TODO: Re-think this process

            val initialSettings = getSettingsFlowUseCase().first()
            _uiState.value = StopwatchUiState.firstReady(
                switchButtons = initialSettings.switchButtons,
                showOffenseCountdownPlayPauseButton = !initialSettings.offenseCountdownControlledByMatch
            )

            getSettingsFlowUseCase().collect { settings ->
                val uiState = _uiState.value

                if (uiState !is StopwatchUiState.Ready) {
                    Logger.warn(TAG, "collectSettings: Expected ready state, but was: $uiState")
                    return@collect
                }

                _uiState.value = uiState.copy(
                    switchButtons = settings.switchButtons,
                    showOffenseCountdownPlayPauseButton = !settings.offenseCountdownControlledByMatch
                )
            }
        }
    }

    private suspend fun synchronizeGameStates(
        uiState: StopwatchUiState.Ready,
    ) {
        snapshotFlow { uiState.matchStopwatchState.running }
            .collect { matchRunning ->
                val currentSettings = getSettingsFlowUseCase().first()
                val matchStopwatchState = uiState.matchStopwatchState

                if (
                    currentSettings.pauseAllWhenMatchIsPaused ||
                    currentSettings.offenseCountdownControlledByMatch
                ) {
                    if (matchRunning != uiState.offenseCountdownState.running) {
                        uiState.offenseCountdownState.toggleRunning()
                    }
                }

                if (currentSettings.pauseAllWhenMatchIsPaused) {
                    uiState.penalty1StopwatchState.toggleBy(matchStopwatchState)
                    uiState.penalty2StopwatchState.toggleBy(matchStopwatchState)
                }
            }
    }

    companion object {
        private const val TAG = "StopwatchViewModel"
        const val OFFENSE_COUNTDOWN_FROM_MS = Constants.OFFENSE_DURATION_SECONDS * 1_000L
    }
}

fun StopwatchState.toggleBy(other: StopwatchState) {
    if (running != other.running) {
        if ((other.running && progressMs > 0) || !other.running) {
            toggleRunning()
        }
    }
}
