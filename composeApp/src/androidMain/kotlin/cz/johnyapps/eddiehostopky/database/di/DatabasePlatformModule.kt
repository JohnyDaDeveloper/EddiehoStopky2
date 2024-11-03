package cz.johnyapps.eddiehostopky.database.di

import cz.johnyapps.eddiehostopky.database.system.AndroidDatabaseBuilderFactory
import cz.johnyapps.eddiehostopky.database.system.DatabaseBuilderFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val databasePlatformModule = module {
    factoryOf(::AndroidDatabaseBuilderFactory) bind DatabaseBuilderFactory::class
}
