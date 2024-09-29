package cz.johnyapps.eddiehostopky.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.johnyapps.eddiehostopky.common.di.koinViewModel
import cz.johnyapps.eddiehostopky.settings.presentation.SettingsViewModel
import cz.johnyapps.eddiehostopky.settings.presentation.model.SettingsUiState
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.settings_alert_before_offense_end_seconds
import eddiehostopky.composeapp.generated.resources.settings_offense_countdown_controlled_by_match
import eddiehostopky.composeapp.generated.resources.settings_pause_all_when_match_is_paused
import eddiehostopky.composeapp.generated.resources.settings_restart_offense_countdown_button_at_left
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = koinViewModel(::SettingsViewModel)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SettingsScreenContent(
        modifier = modifier,
        uiState = uiState,
        onPauseAllWhenMatchIsPausedClicked = viewModel::onPauseAllWhenMatchIsPausedClicked,
        onOffenseCountdownControlledByMatchChange = viewModel::onOffenseCountdownControlledByMatchChange,
        onRestartOffenseCountdownButtonAtLeftChange = viewModel::onRestartOffenseCountdownButtonAtLeftChange,
        onAlertBeforeOffenseEndSecondsChange = viewModel::onAlertBeforeOffenseEndSecondsChange,
    )
}

@Composable
private fun SettingsScreenContent(
    uiState: SettingsUiState,
    onPauseAllWhenMatchIsPausedClicked: () -> Unit,
    onOffenseCountdownControlledByMatchChange: () -> Unit,
    onRestartOffenseCountdownButtonAtLeftChange: () -> Unit,
    onAlertBeforeOffenseEndSecondsChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is SettingsUiState.Loading -> {
            // TODO
        }

        is SettingsUiState.Ready -> {
            Column(
                modifier = modifier.fillMaxSize(),
            ) {
                BooleanSettingItem(
                    value = uiState.pauseAllWhenMatchIsPaused,
                    title = stringResource(Res.string.settings_pause_all_when_match_is_paused),
                    onClick = onPauseAllWhenMatchIsPausedClicked,
                )

                BooleanSettingItem(
                    value = uiState.offenseCountdownControlledByMatch,
                    title = stringResource(Res.string.settings_offense_countdown_controlled_by_match),
                    onClick = onOffenseCountdownControlledByMatchChange,
                )

                BooleanSettingItem(
                    value = uiState.restartOffenseCountdownButtonAtLeft,
                    title = stringResource(Res.string.settings_restart_offense_countdown_button_at_left),
                    onClick = onRestartOffenseCountdownButtonAtLeftChange,
                )

                IntSettingItem(
                    title = stringResource(Res.string.settings_alert_before_offense_end_seconds),
                    value = uiState.alertBeforeOffenseEndSeconds,
                    onSaveValue = onAlertBeforeOffenseEndSecondsChange,
                )
            }
        }
    }
}
