package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.johnyapps.eddiehostopky.common.ui.BoxButton
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme

@Composable
fun RestartButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    BoxButton(
        modifier = modifier,
        icon = Icons.Filled.RestartAlt,
        iconTint = AppTheme.color.onSecondary,
        color = AppTheme.color.secondary,
        onClick = onClick,
    )
}
