package cz.johnyapps.eddiehostopky.database.di

import cz.johnyapps.eddiehostopky.database.system.DatabaseBuilderFactory
import cz.johnyapps.eddiehostopky.database.system.IosDatabaseBuilderFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val databasePlatformModule = module {
    factoryOf(::IosDatabaseBuilderFactory) bind DatabaseBuilderFactory::class
}
