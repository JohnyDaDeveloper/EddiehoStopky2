package cz.johnyapps.eddiehostopky.settings.di

import cz.johnyapps.eddiehostopky.database.system.DatabaseBuilderFactory
import cz.johnyapps.eddiehostopky.settings.system.IosDatabaseBuilderFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val settingsPlatformModule = module {
    factoryOf(::IosDatabaseBuilderFactory) bind DatabaseBuilderFactory::class
}
