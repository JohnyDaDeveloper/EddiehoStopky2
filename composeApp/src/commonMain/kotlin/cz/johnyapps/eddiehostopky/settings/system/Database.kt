package cz.johnyapps.eddiehostopky.settings.system

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import cz.johnyapps.eddiehostopky.settings.data.SettingsDao
import cz.johnyapps.eddiehostopky.settings.data.model.SettingsEntity

@Database(entities = [SettingsEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>
