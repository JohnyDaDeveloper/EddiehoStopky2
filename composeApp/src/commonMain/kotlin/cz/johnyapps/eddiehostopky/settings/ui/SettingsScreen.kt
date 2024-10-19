package cz.johnyapps.eddiehostopky.settings.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.johnyapps.eddiehostopky.common.di.koinViewModel
import cz.johnyapps.eddiehostopky.common.util.Constants
import cz.johnyapps.eddiehostopky.settings.presentation.SettingsViewModel
import cz.johnyapps.eddiehostopky.settings.presentation.model.SettingsUiState
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.settings_alert_before_offense_end_seconds
import eddiehostopky.composeapp.generated.resources.settings_offense_countdown_controlled_by_match
import eddiehostopky.composeapp.generated.resources.settings_pause_all_when_match_is_paused
import eddiehostopky.composeapp.generated.resources.settings_switch_buttons
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
        onSwitchButtonsClick = viewModel::onSwitchButtonsClick,
        onAlertBeforeOffenseEndSecondsChange = viewModel::onAlertBeforeOffenseEndSecondsChange,
    )
}

@Composable
private fun SettingsScreenContent(
    uiState: SettingsUiState,
    onPauseAllWhenMatchIsPausedClicked: () -> Unit,
    onOffenseCountdownControlledByMatchChange: () -> Unit,
    onSwitchButtonsClick: () -> Unit,
    onAlertBeforeOffenseEndSecondsChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is SettingsUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    color = AppTheme.color.primary
                )
            }
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
                    value = uiState.switchButtons,
                    title = stringResource(Res.string.settings_switch_buttons),
                    onClick = onSwitchButtonsClick,
                )

                IntSettingItem(
                    title = stringResource(Res.string.settings_alert_before_offense_end_seconds),
                    value = uiState.alertBeforeOffenseEndSeconds,
                    maxValue = Constants.OFFENSE_DURATION_SECONDS,
                    onSaveValue = onAlertBeforeOffenseEndSecondsChange,
                    valueUnit = "s"
                )
            }
        }
    }
}
