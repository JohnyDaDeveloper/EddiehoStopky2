package cz.johnyapps.eddiehostopky.settings.domain

import cz.johnyapps.eddiehostopky.settings.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun saveSettings(settings: Settings)

    suspend fun getSettings(): Settings

    fun getSettingsFlow(): Flow<Settings>
}
