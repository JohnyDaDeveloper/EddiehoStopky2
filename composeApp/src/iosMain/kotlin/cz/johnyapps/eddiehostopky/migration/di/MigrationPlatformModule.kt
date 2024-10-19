package cz.johnyapps.eddiehostopky.migration.di

import cz.johnyapps.eddiehostopky.migration.domain.IosMigrateFrom0To1UseCase
import cz.johnyapps.eddiehostopky.migration.domain.MigrateFrom0To1UseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val migrationPlatformModule = module {
    factoryOf(::IosMigrateFrom0To1UseCase) bind MigrateFrom0To1UseCase::class
}
