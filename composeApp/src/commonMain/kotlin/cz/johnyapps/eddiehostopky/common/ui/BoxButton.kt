package cz.johnyapps.eddiehostopky.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme

private val ICON_SIZE = 48.dp

@Composable
fun BoxButton(
    icon: ImageVector,
    color: Color,
    iconTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(color)
            .clickable { onClick() }
            .padding(AppTheme.spacing.normal),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(ICON_SIZE),
            tint = iconTint,
            imageVector = icon,
            contentDescription = null,
        )
    }
}
