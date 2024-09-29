package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cz.johnyapps.eddiehostopky.stopwatch.presentation.StopwatchViewModel
import cz.johnyapps.eddiehostopky.stopwatch.presentation.model.StopwatchUiState
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.stopwatch_title
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopwatchScreen(
    modifier: Modifier = Modifier,
    viewModel: StopwatchViewModel = viewModel { StopwatchViewModel() }
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(Res.string.stopwatch_title))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.color.primary,
                    titleContentColor = AppTheme.color.onPrimary,
                )
            )
        }
    ) { paddingValues ->
        StopwatchScreenContents(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            uiState = uiState,
            onResetMatchClick = viewModel::onResetMatchClick,
        )
    }
}

@Composable
private fun StopwatchScreenContents(
    modifier: Modifier,
    uiState: StopwatchUiState,
    onResetMatchClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        MatchStopwatch(
            state = uiState.matchStopwatchState,
            onMatchStopwatchReset = onResetMatchClick,
        )

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider()

        OffenseCountdown(state = uiState.offenseCountdownState)

        HorizontalDivider()

        PenaltyStopwatch(
            state = uiState.penalty1StopwatchState,
            number = 1
        )

        HorizontalDivider()

        PenaltyStopwatch(
            state = uiState.penalty2StopwatchState,
            number = 2
        )
    }
}
