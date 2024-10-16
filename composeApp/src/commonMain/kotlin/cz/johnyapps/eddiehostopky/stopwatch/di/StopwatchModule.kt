package cz.johnyapps.eddiehostopky.stopwatch.di

import cz.johnyapps.eddiehostopky.stopwatch.domain.CreateAlertBeforeOffenseEndFlowUseCase
import cz.johnyapps.eddiehostopky.stopwatch.domain.LiveCreateAlertBeforeOffenseEndFlowUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val stopwatchModule = module {
    factoryOf(::LiveCreateAlertBeforeOffenseEndFlowUseCase) bind CreateAlertBeforeOffenseEndFlowUseCase::class
}
