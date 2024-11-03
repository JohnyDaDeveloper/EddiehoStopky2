package cz.johnyapps.eddiehostopky.app

import com.rickclephas.kmp.nsexceptionkt.core.causes
import com.rickclephas.kmp.nsexceptionkt.core.wrapUnhandledExceptionHook
import cz.johnyapps.eddiehostopky.app.di.appModule
import cz.johnyapps.eddiehostopky.common.ui.VibrationManager
import cz.johnyapps.eddiehostopky.common.util.LogWriter
import cz.johnyapps.eddiehostopky.common.util.Logger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

@Suppress("unused")
fun initKmp(
    createVibrationManager: () -> VibrationManager,
    createLogWriter: () -> LogWriter,
) {
    wrapUnhandledExceptionHook { throwable ->
        val causesMessages = throwable.causes.map { cause ->
            cause.message
        }.joinToString(separator = "\n")

        Logger.error("UncaughtExceptionHook", "${throwable.message}\n$causesMessages")
    }

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
