package cz.johnyapps.eddiehostopky.settings.di

import cz.johnyapps.eddiehostopky.database.system.AppDatabase
import cz.johnyapps.eddiehostopky.settings.data.LiveSettingsRepository
import cz.johnyapps.eddiehostopky.settings.data.SettingsDao
import cz.johnyapps.eddiehostopky.settings.domain.GetSettingsFlowUseCase
import cz.johnyapps.eddiehostopky.settings.domain.GetSettingsUseCase
import cz.johnyapps.eddiehostopky.settings.domain.LiveGetSettingsFlowUseCase
import cz.johnyapps.eddiehostopky.settings.domain.LiveGetSettingsUseCase
import cz.johnyapps.eddiehostopky.settings.domain.LiveSaveSettingsUseCase
import cz.johnyapps.eddiehostopky.settings.domain.SaveSettingsUseCase
import cz.johnyapps.eddiehostopky.settings.domain.SettingsRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val settingsModule = module {
    includes(settingsPlatformModule)

    factoryOf(::LiveGetSettingsUseCase) bind GetSettingsUseCase::class
    factoryOf(::LiveSaveSettingsUseCase) bind SaveSettingsUseCase::class
    factoryOf(::LiveGetSettingsFlowUseCase) bind GetSettingsFlowUseCase::class

    singleOf(::LiveSettingsRepository) bind SettingsRepository::class
    factory<SettingsDao> {
        val database = get<AppDatabase>()
        database.settingsDao()
    }
}
