package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.johnyapps.eddiehostopky.common.ui.BoxButton
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme

@Composable
fun PlayPauseButton(
    running: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxButton(
        modifier = modifier,
        icon = if (running) {
            Icons.Filled.Pause
        } else {
            Icons.Filled.PlayArrow
        },
        iconTint = AppTheme.color.onPrimary,
        color = AppTheme.color.primary,
        onClick = onClick
    )
}
