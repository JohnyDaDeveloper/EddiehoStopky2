package cz.johnyapps.eddiehostopky.app.di

import cz.johnyapps.eddiehostopky.common.di.commonModule
import org.koin.dsl.module

val appModule = module {
    includes(commonModule)
}
