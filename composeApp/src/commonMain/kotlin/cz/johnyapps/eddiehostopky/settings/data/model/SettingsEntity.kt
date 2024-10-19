package cz.johnyapps.eddiehostopky.settings.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.johnyapps.eddiehostopky.settings.domain.model.Settings

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey val id: Long,
    val pauseAllWhenMatchIsPaused: Boolean,
    val offenseCountdownControlledByMatch: Boolean,
    val switchButtons: Boolean,
    val alertBeforeOffenseEndSeconds: Int,
)

fun SettingsEntity.toDomainModel(): Settings {
    return Settings(
        pauseAllWhenMatchIsPaused = pauseAllWhenMatchIsPaused,
        offenseCountdownControlledByMatch = offenseCountdownControlledByMatch,
        switchButtons = switchButtons,
        alertBeforeOffenseEndSeconds = alertBeforeOffenseEndSeconds
    )
}

fun Settings.toEntity(): SettingsEntity {
    return SettingsEntity(
        id = 0,
        pauseAllWhenMatchIsPaused = pauseAllWhenMatchIsPaused,
        offenseCountdownControlledByMatch = offenseCountdownControlledByMatch,
        switchButtons = switchButtons,
        alertBeforeOffenseEndSeconds = alertBeforeOffenseEndSeconds
    )
}
