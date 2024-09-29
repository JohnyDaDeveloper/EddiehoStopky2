package cz.johnyapps.eddiehostopky.settings.di

import cz.johnyapps.eddiehostopky.settings.domain.FakeLoadSettingsUseCase
import cz.johnyapps.eddiehostopky.settings.domain.LoadSettingsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val settingsModule = module {
    factoryOf(::FakeLoadSettingsUseCase) bind LoadSettingsUseCase::class
}
