package cz.johnyapps.eddiehostopky.app

import cz.johnyapps.eddiehostopky.app.di.appModule
import cz.johnyapps.eddiehostopky.common.ui.VibrationManager
import cz.johnyapps.eddiehostopky.common.util.LogWriter
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

@Suppress("unused")
fun initKoin(
    createVibrationManager: () -> VibrationManager,
    createLogWriter: () -> LogWriter,
) {
    startKoin {
        modules(
            appModule,
            module {
                factoryOf(createVibrationManager) bind VibrationManager::class
                factoryOf(createLogWriter) bind LogWriter::class
            }
        )
    }
}
