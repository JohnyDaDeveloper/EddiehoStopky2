package cz.johnyapps.eddiehostopky.settings.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.settings_no
import eddiehostopky.composeapp.generated.resources.settings_yes
import org.jetbrains.compose.resources.stringResource

@Composable
fun BooleanSettingItem(
    title: String,
    value: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SettingItem(
        modifier = modifier,
        title = title,
        onClick = onClick
    ) {
        val textId = if (value) {
            Res.string.settings_yes
        } else {
            Res.string.settings_no
        }

        Text(text = stringResource(textId))
    }
}
