package cz.johnyapps.eddiehostopky.migration.di

import cz.johnyapps.eddiehostopky.database.system.AppDatabase
import cz.johnyapps.eddiehostopky.migration.data.MigrationDao
import cz.johnyapps.eddiehostopky.migration.data.RoomMigrationRepository
import cz.johnyapps.eddiehostopky.migration.domain.ExecuteMigrationUseCase
import cz.johnyapps.eddiehostopky.migration.domain.LiveExecuteMigrationUseCase
import cz.johnyapps.eddiehostopky.migration.domain.MigrationRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val migrationModule = module {
    factoryOf(::LiveExecuteMigrationUseCase) bind ExecuteMigrationUseCase::class

    single<MigrationDao> {
        val database = get<AppDatabase>()
        database.migrationDao()
    }

    singleOf(::RoomMigrationRepository) bind MigrationRepository::class

    includes(migrationPlatformModule)
}
