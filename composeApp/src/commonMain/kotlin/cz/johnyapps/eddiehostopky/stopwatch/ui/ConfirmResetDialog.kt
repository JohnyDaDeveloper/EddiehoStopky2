package cz.johnyapps.eddiehostopky.stopwatch.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.johnyapps.eddiehostopky.common.ui.ConfirmCancelDialog
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.cancel
import eddiehostopky.composeapp.generated.resources.confirm
import eddiehostopky.composeapp.generated.resources.stopwatch_match_reset_dialog_description
import eddiehostopky.composeapp.generated.resources.stopwatch_match_reset_dialog_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun ConfirmResetDialog(
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    ConfirmCancelDialog(
        title = stringResource(Res.string.stopwatch_match_reset_dialog_title),
        confirmButtonText = stringResource(Res.string.confirm),
        onConfirmButtonClick = onConfirmClick,
        cancelButtonText = stringResource(Res.string.cancel),
        onDismissRequest = onDismissRequest,
    ) {
        Text(
            modifier = Modifier.padding(AppTheme.spacing.normal),
            text = stringResource(Res.string.stopwatch_match_reset_dialog_description)
        )
    }
}
