package cz.johnyapps.eddiehostopky.migration.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.johnyapps.eddiehostopky.migration.data.model.MigrationInfoEntity

@Dao
interface MigrationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfoMigration(migration: MigrationInfoEntity)

    @Query("SELECT * FROM migration_info LIMIT 1")
    suspend fun getMigrationInfo(): MigrationInfoEntity?
}
