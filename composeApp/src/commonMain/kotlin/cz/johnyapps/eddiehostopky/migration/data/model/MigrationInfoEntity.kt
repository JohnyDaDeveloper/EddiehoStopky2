package cz.johnyapps.eddiehostopky.migration.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "migration_info")
data class MigrationInfoEntity(
    @PrimaryKey val id: Long,
    val migratedToVersion: Int,
)
