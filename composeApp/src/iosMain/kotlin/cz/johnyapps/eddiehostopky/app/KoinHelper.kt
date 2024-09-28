package cz.johnyapps.eddiehostopky.app

import cz.johnyapps.eddiehostopky.app.di.appModule
import org.koin.core.context.startKoin

@Suppress("unused")
fun initKoin() {
    startKoin {
        modules(appModule)
    }
}
