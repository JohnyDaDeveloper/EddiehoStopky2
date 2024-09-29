package cz.johnyapps.eddiehostopky.settings.presentation.model

import cz.johnyapps.eddiehostopky.settings.domain.model.Settings

sealed interface SettingsUiState {
    data object Loading : SettingsUiState

    data class Ready(
        val pauseAllWhenMatchIsPaused: Boolean,
        val offenseCountdownControlledByMatch: Boolean,
        val restartOffenseCountdownButtonAtLeft: Boolean,
        val alertBeforeOffenseEndSeconds: Int,
    ) : SettingsUiState

    companion object {
        fun initial(): SettingsUiState = Loading
    }
}

fun Settings.toSettingsUiState(): SettingsUiState {
    return SettingsUiState.Ready(
        pauseAllWhenMatchIsPaused = pauseAllWhenMatchIsPaused,
        offenseCountdownControlledByMatch = offenseCountdownControlledByMatch,
        restartOffenseCountdownButtonAtLeft = restartOffenseCountdownButtonAtLeft,
        alertBeforeOffenseEndSeconds = alertBeforeOffenseEndSeconds
    )
}
