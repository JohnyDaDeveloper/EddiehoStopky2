package cz.johnyapps.eddiehostopky.settings.domain

import cz.johnyapps.eddiehostopky.settings.domain.model.Settings

interface LoadSettingsUseCase {

    suspend operator fun invoke(): Settings
}

class FakeLoadSettingsUseCase : LoadSettingsUseCase {

    override suspend fun invoke(): Settings {
        return Settings(
            pauseAllWhenMatchIsPaused = true,
            offenseCountdownControlledByMatch = true,
            restartOffenseCountdownButtonAtLeft = true,
            alertBeforeOffenseEndSeconds = 5,
        )
    }
}
