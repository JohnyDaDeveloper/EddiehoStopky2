package cz.johnyapps.eddiehostopky.common.di

import cz.johnyapps.eddiehostopky.common.ui.AndroidVibrationManager
import cz.johnyapps.eddiehostopky.common.ui.VibrationManager
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val commonPlatformModule = module {
    factoryOf(::AndroidVibrationManager) bind VibrationManager::class
}
