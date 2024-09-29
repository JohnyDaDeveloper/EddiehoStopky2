package cz.johnyapps.eddiehostopky.settings.system

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

class IosDatabaseBuilderFactory : DatabaseBuilderFactory {

    override fun createDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val databaseFilePath = documentDirectory() + "/${DatabaseBuilderFactory.DATABASE_NAME}.db"
        return Room.databaseBuilder<AppDatabase>(
            name = databaseFilePath,
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }
}
