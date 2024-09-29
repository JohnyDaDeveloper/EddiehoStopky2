package cz.johnyapps.eddiehostopky.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme

@Composable
fun Dialog(
    title: @Composable () -> Unit,
    buttons: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .clip(AppTheme.shapes.normal)
                .background(AppTheme.color.surface)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            title()

            HorizontalDivider()

            content()

            buttons()
        }
    }
}
