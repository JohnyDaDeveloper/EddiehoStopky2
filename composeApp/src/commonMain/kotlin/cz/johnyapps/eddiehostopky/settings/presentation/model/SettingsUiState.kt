package cz.johnyapps.eddiehostopky.settings.presentation.model

import cz.johnyapps.eddiehostopky.settings.domain.model.Settings

sealed interface SettingsUiState {
    data object Loading : SettingsUiState

    data class Ready(
        val pauseAllWhenMatchIsPaused: Boolean,
        val offenseCountdownControlledByMatch: Boolean,
        val switchButtons: Boolean,
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
        switchButtons = switchButtons,
        alertBeforeOffenseEndSeconds = alertBeforeOffenseEndSeconds
    )
}

fun SettingsUiState.Ready.toDomainModel(): Settings {
    return Settings(
        pauseAllWhenMatchIsPaused = pauseAllWhenMatchIsPaused,
        offenseCountdownControlledByMatch = offenseCountdownControlledByMatch,
        switchButtons = switchButtons,
        alertBeforeOffenseEndSeconds = alertBeforeOffenseEndSeconds
    )
}
