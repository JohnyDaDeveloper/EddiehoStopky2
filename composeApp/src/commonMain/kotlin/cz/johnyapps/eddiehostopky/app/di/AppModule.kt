package cz.johnyapps.eddiehostopky.app.di

import cz.johnyapps.eddiehostopky.common.di.commonModule
import cz.johnyapps.eddiehostopky.database.di.databaseModule
import cz.johnyapps.eddiehostopky.migration.di.migrationModule
import cz.johnyapps.eddiehostopky.settings.di.settingsModule
import cz.johnyapps.eddiehostopky.stopwatch.di.stopwatchModule
import org.koin.dsl.module

val appModule = module {
    includes(
        commonModule,
        databaseModule,
        settingsModule,
        stopwatchModule,
        migrationModule,
    )
}
