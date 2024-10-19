package cz.johnyapps.eddiehostopky.common.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme

@Composable
fun ConfirmCancelDialog(
    title: String,
    confirmButtonText: String,
    onConfirmButtonClick: () -> Unit,
    cancelButtonText: String,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    DefaultDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                modifier = Modifier.padding(AppTheme.spacing.normal),
                text = title,
                style = AppTheme.typography.large
            )
        },
        buttons = {
            Row(
                modifier = Modifier.padding(AppTheme.spacing.normal)
            ) {
                TextDialogButton(
                    text = cancelButtonText,
                    onClick = onDismissRequest,
                )

                Spacer(Modifier.weight(1f))

                ButtonDialogButton(
                    text = confirmButtonText,
                    onClick = onConfirmButtonClick
                )
            }
        },
        content = content,
    )
}
