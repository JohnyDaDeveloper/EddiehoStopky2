package cz.johnyapps.eddiehostopky.app.di

import cz.johnyapps.eddiehostopky.common.di.commonModule
import cz.johnyapps.eddiehostopky.settings.di.settingsModule
import org.koin.dsl.module

val appModule = module {
    includes(commonModule)
    includes(settingsModule)
}
