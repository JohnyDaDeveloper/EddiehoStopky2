package cz.johnyapps.eddiehostopky.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import cz.johnyapps.eddiehostopky.common.ui.Dialog
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import cz.johnyapps.eddiehostopky.theme.ui.buttonColors
import cz.johnyapps.eddiehostopky.theme.ui.textButtonColors
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.cancel
import eddiehostopky.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringResource

@Composable
fun IntSettingItem(
    title: String,
    value: Int,
    onSaveValue: (Int) -> Unit,
    modifier: Modifier = Modifier,
    valueUnit: String? = null,
) {
    var dialogOpen by remember { mutableStateOf(false) }

    if (dialogOpen) {
        IntValueDialog(
            title = title,
            initialValue = value,
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
        Text(text = "$value")
    }
}

@Composable
private fun IntValueDialog(
    title: String,
    initialValue: Int,
    valueUnit: String?,
    onDismissRequest: () -> Unit,
    onSaveValue: (Int) -> Unit,
) {
    var currentValue by remember { mutableIntStateOf(initialValue) }

    Dialog(
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
                TextButton(
                    onClick = onDismissRequest,
                    colors = AppTheme.textButtonColors()
                ) {
                    Text(text = stringResource(Res.string.cancel))
                }

                Spacer(Modifier.weight(1f))

                Button(
                    onClick = {
                        onSaveValue(currentValue)
                        onDismissRequest()
                    },
                    colors = AppTheme.buttonColors(),
                ) {
                    Text(text = stringResource(Res.string.save))
                }
            }
        },
        onDismissRequest = onDismissRequest,
    ) {
        IntValueDialogContent(
            value = currentValue,
            onValueChange = { currentValue = it },
            valueUnit = valueUnit,
        )
    }
}

@Composable
private fun IntValueDialogContent(
    value: Int,
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
                onValueChange(newValue)
            },
            colors = AppTheme.textButtonColors(),
        ) {
            Text(
                text = "-",
                style = AppTheme.typography.large
            )
        }

        val currentText = if (valueUnit != null) {
            "$value$valueUnit"
        } else {
            "$value"
        }

        Text(
            modifier = Modifier
                .weight(1f),
            text = currentText,
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
