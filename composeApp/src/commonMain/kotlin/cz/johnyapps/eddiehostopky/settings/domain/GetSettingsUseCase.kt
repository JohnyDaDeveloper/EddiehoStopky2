package cz.johnyapps.eddiehostopky.settings.domain

import cz.johnyapps.eddiehostopky.settings.domain.model.Settings

interface GetSettingsUseCase {

    suspend operator fun invoke(): Settings
}

class LiveGetSettingsUseCase(
    private val settingsRepository: SettingsRepository,
) : GetSettingsUseCase {

    override suspend fun invoke(): Settings {
        return settingsRepository.getSettings()
    }
}
