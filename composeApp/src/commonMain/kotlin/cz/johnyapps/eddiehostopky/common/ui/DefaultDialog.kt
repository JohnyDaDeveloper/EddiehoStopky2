package cz.johnyapps.eddiehostopky.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.window.Dialog
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import cz.johnyapps.eddiehostopky.theme.ui.buttonColors
import cz.johnyapps.eddiehostopky.theme.ui.textButtonColors

@Composable
fun DefaultDialog(
    title: @Composable () -> Unit,
    buttons: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    Dialog(
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

@Composable
fun TextDialogButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        colors = AppTheme.textButtonColors()
    ) {
        Text(text = text)
    }
}

@Composable
fun ButtonDialogButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = AppTheme.buttonColors(),
    ) {
        Text(text = text)
    }
}
