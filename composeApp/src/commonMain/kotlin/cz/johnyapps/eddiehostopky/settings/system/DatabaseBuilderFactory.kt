package cz.johnyapps.eddiehostopky.settings.system

import androidx.room.RoomDatabase

interface DatabaseBuilderFactory {

    fun createDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}
