package cz.johnyapps.eddiehostopky.settings.domain

import cz.johnyapps.eddiehostopky.settings.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface GetSettingsFlowUseCase {

    operator fun invoke(): Flow<Settings>
}

class LiveGetSettingsFlowUseCase(
    private val repository: SettingsRepository,
) : GetSettingsFlowUseCase {

    override fun invoke(): Flow<Settings> {
        return repository.getSettingsFlow()
    }
}
