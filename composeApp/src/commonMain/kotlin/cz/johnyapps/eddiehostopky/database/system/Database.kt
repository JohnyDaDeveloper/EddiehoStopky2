package cz.johnyapps.eddiehostopky.database.system

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import cz.johnyapps.eddiehostopky.migration.data.MigrationDao
import cz.johnyapps.eddiehostopky.migration.data.model.MigrationInfoEntity
import cz.johnyapps.eddiehostopky.settings.data.SettingsDao
import cz.johnyapps.eddiehostopky.settings.data.model.SettingsEntity

@Database(entities = [SettingsEntity::class, MigrationInfoEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao

    abstract fun migrationDao(): MigrationDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>
