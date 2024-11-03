package cz.johnyapps.eddiehostopky.database.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import cz.johnyapps.eddiehostopky.database.system.AppDatabase
import cz.johnyapps.eddiehostopky.database.system.DatabaseBuilderFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val databaseModule = module {
    includes(databasePlatformModule)
    single<AppDatabase> {
        val builder = get<DatabaseBuilderFactory>().createDatabaseBuilder()
        builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}
