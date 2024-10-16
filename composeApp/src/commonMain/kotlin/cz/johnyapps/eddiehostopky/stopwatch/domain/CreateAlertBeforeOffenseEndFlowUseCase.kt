package cz.johnyapps.eddiehostopky.stopwatch.domain

import cz.johnyapps.eddiehostopky.settings.domain.GetSettingsFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

interface CreateAlertBeforeOffenseEndFlowUseCase {

    suspend operator fun invoke(
        offenseRemainingMsFlow: Flow<Long>,
    ): Flow<Unit>
}

class LiveCreateAlertBeforeOffenseEndFlowUseCase(
    private val getSettingsFlowUseCase: GetSettingsFlowUseCase,
) : CreateAlertBeforeOffenseEndFlowUseCase {

    override suspend fun invoke(
        offenseRemainingMsFlow: Flow<Long>,
    ): Flow<Unit> {
        val alertBeforeEndMsFlow = getSettingsFlowUseCase()
            .map { it.alertBeforeOffenseEndSeconds * 1_000L }

        return combine(
            offenseRemainingMsFlow,
            alertBeforeEndMsFlow
        ) { offenseRemainingMs, alertBeforeEndMs ->
            alertBeforeEndMs > offenseRemainingMs
        }.distinctUntilChanged()
            .filter { it }
            .map { }
    }
}
