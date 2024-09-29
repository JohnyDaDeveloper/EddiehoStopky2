package cz.johnyapps.eddiehostopky.settings.system

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class AndroidDatabaseBuilderFactory(
    private val context: Context,
) : DatabaseBuilderFactory {

    override fun createDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val databaseFile = context.getDatabasePath("${DatabaseBuilderFactory.DATABASE_NAME}.db")
        return Room.databaseBuilder<AppDatabase>(
            context = context,
            name = databaseFile.absolutePath
        )
    }
}
