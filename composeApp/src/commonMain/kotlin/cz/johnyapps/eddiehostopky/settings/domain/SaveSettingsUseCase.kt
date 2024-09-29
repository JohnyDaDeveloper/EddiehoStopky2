package cz.johnyapps.eddiehostopky.settings.domain

import cz.johnyapps.eddiehostopky.settings.domain.model.Settings

interface SaveSettingsUseCase {

    suspend operator fun invoke(settings: Settings)
}

class LiveSaveSettingsUseCase(
    private val repository: SettingsRepository,
) : SaveSettingsUseCase {

    override suspend fun invoke(settings: Settings) {
        repository.saveSettings(settings)
    }
}
