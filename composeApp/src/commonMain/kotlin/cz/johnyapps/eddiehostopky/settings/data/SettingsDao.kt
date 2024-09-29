package cz.johnyapps.eddiehostopky.settings.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.johnyapps.eddiehostopky.settings.data.model.SettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSettings(settings: SettingsEntity)

    @Query("SELECT * FROM settings LIMIT 1")
    suspend fun getSettings(): SettingsEntity?

    @Query("SELECT * FROM settings LIMIT 1")
    fun getSettingsFlow(): Flow<SettingsEntity>
}
