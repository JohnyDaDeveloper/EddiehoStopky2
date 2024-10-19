package cz.johnyapps.eddiehostopky.migration.domain

import android.content.Context
import cz.johnyapps.eddiehostopky.settings.domain.SaveSettingsUseCase
import cz.johnyapps.eddiehostopky.settings.domain.model.Settings

class AndroidMigrateFrom0To1UseCase(
    context: Context,
    private val saveSettingsUseCase: SaveSettingsUseCase,
) : MigrateFrom0To1UseCase {

    private val legacySharedPreferences = context.getSharedPreferences(
        LEGACY_SHARED_PREFS_NAME,
        Context.MODE_PRIVATE,
    )

    override suspend fun invoke() {
        val defaultSettings = Settings.createDefault()

        val pauseAllWhenMatchIsPaused = legacySharedPreferences.getBoolean(
            LEGACY_KEY_PAUSE_ALL_WHEN_MATCH_IS_PAUSED,
            defaultSettings.pauseAllWhenMatchIsPaused,
        )

        val offenseCountdownControlledByMatch = legacySharedPreferences.getBoolean(
            LEGACY_KEY_OFFENSE_COUNTDOWN_CONTROLLED_BY_MATCH,
            defaultSettings.offenseCountdownControlledByMatch,
        )

        val switchButtons = legacySharedPreferences.getBoolean(
            LEGACY_KEY_RESTART_OFFENSE_COUNTDOWN_BUTTON_AT_LEFT,
            defaultSettings.switchButtons,
        )

        val alertBeforeOffenseEndSeconds = legacySharedPreferences.getInt(
            LEGACY_KEY_ALERT_BEFORE_OFFENSE_END_SECONDS,
            defaultSettings.alertBeforeOffenseEndSeconds,
        )

        val migratedSettings = Settings(
            pauseAllWhenMatchIsPaused = pauseAllWhenMatchIsPaused,
            offenseCountdownControlledByMatch = offenseCountdownControlledByMatch,
            switchButtons = switchButtons,
            alertBeforeOffenseEndSeconds = alertBeforeOffenseEndSeconds
        )

        saveSettingsUseCase(migratedSettings)
    }

    companion object {
        private const val LEGACY_SHARED_PREFS_NAME = "general"
        private const val LEGACY_KEY_PAUSE_ALL_WHEN_MATCH_IS_PAUSED = "stop_all_when_game_stopped"
        private const val LEGACY_KEY_OFFENSE_COUNTDOWN_CONTROLLED_BY_MATCH = "alert_before_attack_end"
        private const val LEGACY_KEY_RESTART_OFFENSE_COUNTDOWN_BUTTON_AT_LEFT = "attack_timer_always_on"
        private const val LEGACY_KEY_ALERT_BEFORE_OFFENSE_END_SECONDS = "attack_timer_reset_other_side"
    }
}
