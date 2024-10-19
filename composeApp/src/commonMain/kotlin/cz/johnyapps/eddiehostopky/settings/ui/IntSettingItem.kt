package cz.johnyapps.eddiehostopky.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cz.johnyapps.eddiehostopky.common.ui.ConfirmCancelDialog
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import cz.johnyapps.eddiehostopky.theme.ui.textButtonColors
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.cancel
import eddiehostopky.composeapp.generated.resources.save
import eddiehostopky.composeapp.generated.resources.settings_no
import org.jetbrains.compose.resources.stringResource

@Composable
fun IntSettingItem(
    title: String,
    value: Int,
    maxValue: Int,
    onSaveValue: (Int) -> Unit,
    modifier: Modifier = Modifier,
    valueUnit: String? = null,
) {
    var dialogOpen by remember { mutableStateOf(false) }

    if (dialogOpen) {
        IntValueDialog(
            title = title,
            initialValue = value,
            maxValue = maxValue,
            valueUnit = valueUnit,
            onDismissRequest = { dialogOpen = false },
            onSaveValue = onSaveValue,
        )
    }

    SettingItem(
        modifier = modifier,
        title = title,
        onClick = { dialogOpen = true },
    ) {
        Text(text = value.valueToText(valueUnit))
    }
}

@Composable
private fun IntValueDialog(
    title: String,
    initialValue: Int,
    maxValue: Int,
    valueUnit: String?,
    onDismissRequest: () -> Unit,
    onSaveValue: (Int) -> Unit,
) {
    var currentValue by remember { mutableIntStateOf(initialValue) }

    ConfirmCancelDialog(
        title = title,
        confirmButtonText = stringResource(Res.string.save),
        onConfirmButtonClick = {
            onSaveValue(currentValue)
            onDismissRequest()
        },
        cancelButtonText = stringResource(Res.string.cancel),
        onDismissRequest = onDismissRequest
    ) {
        IntValueDialogContent(
            value = currentValue,
            maxValue = maxValue,
            onValueChange = { currentValue = it },
            valueUnit = valueUnit,
        )
    }
}

@Composable
private fun IntValueDialogContent(
    value: Int,
    maxValue: Int,
    onValueChange: (Int) -> Unit,
    valueUnit: String?,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(AppTheme.spacing.normal),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            modifier = Modifier.weight(1f),
            onClick = {
                val newValue = (value - 1)
                    .coerceAtLeast(0)
                    .coerceAtMost(maxValue)
                onValueChange(newValue)
            },
            colors = AppTheme.textButtonColors(),
        ) {
            Text(
                text = "-",
                style = AppTheme.typography.large
            )
        }

        Text(
            modifier = Modifier
                .weight(1f),
            text = value.valueToText(valueUnit),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.large
        )

        TextButton(
            modifier = Modifier.weight(1f),
            onClick = {
                onValueChange(value + 1)
            },
            colors = AppTheme.textButtonColors(),
        ) {
            Text(
                text = "+",
                style = AppTheme.typography.large
            )
        }
    }
}

@Composable
private fun Int.valueToText(unit: String?): String {
    return when {
        this == 0 -> stringResource(Res.string.settings_no)
        unit != null -> "$this$unit"
        else -> "$this"
    }
}
