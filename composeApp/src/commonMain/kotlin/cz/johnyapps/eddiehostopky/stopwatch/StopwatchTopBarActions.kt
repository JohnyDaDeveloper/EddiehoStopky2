package cz.johnyapps.eddiehostopky.stopwatch

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RowScope.StopwatchTopBarActions(
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onSettingsClick
    ) {
        Icon(
            modifier = modifier,
            imageVector = Icons.Filled.Settings,
            contentDescription = null,
        )
    }
}
