package cz.johnyapps.eddiehostopky.settings.data

import cz.johnyapps.eddiehostopky.settings.data.model.toDomainModel
import cz.johnyapps.eddiehostopky.settings.data.model.toEntity
import cz.johnyapps.eddiehostopky.settings.domain.SettingsRepository
import cz.johnyapps.eddiehostopky.settings.domain.model.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class LiveSettingsRepository(
    private val dao: SettingsDao,
) : SettingsRepository {

    override suspend fun saveSettings(settings: Settings) {
        dao.insertSettings(settings.toEntity())
    }

    override suspend fun getSettings(): Settings {
        return dao.getSettings()?.toDomainModel()
            ?: createDefaultSettings()
    }

    override fun getSettingsFlow(): Flow<Settings> {
        return flow {
            emit(getSettings())
            emitAll(
                dao.getSettingsFlow()
                    .map { entity ->
                        entity?.toDomainModel()
                            ?: createDefaultSettings()
                    }
            )
        }
    }

    private fun createDefaultSettings(): Settings {
        return Settings(
            pauseAllWhenMatchIsPaused = true,
            offenseCountdownControlledByMatch = true,
            restartOffenseCountdownButtonAtLeft = true,
            alertBeforeOffenseEndSeconds = 5,
        )
    }
}
