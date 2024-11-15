package cz.johnyapps.eddiehostopky.settings.data

import cz.johnyapps.eddiehostopky.settings.domain.SettingsRepository
import cz.johnyapps.eddiehostopky.settings.domain.model.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

/**
 * Room DB doesn't work on iOS simulators.
 */
internal class InMemorySettingsRepository : SettingsRepository {
    private var currentSettings = MutableStateFlow<Settings?>(null)

    override suspend fun saveSettings(settings: Settings) {
        currentSettings.value = settings
    }

    override suspend fun getSettings(): Settings {
        return currentSettings.value ?: Settings.createDefault()
    }

    override fun getSettingsFlow(): Flow<Settings> {
        return currentSettings.map { current ->
            current ?: Settings.createDefault()
        }
    }
}
