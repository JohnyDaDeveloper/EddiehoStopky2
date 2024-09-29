package cz.johnyapps.eddiehostopky.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme

@Composable
fun SettingItem(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .minimumInteractiveComponentSize()
                .clickable { onClick() }
                .padding(AppTheme.spacing.normal),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(modifier = Modifier.width(AppTheme.spacing.normal))

            state()
        }

        HorizontalDivider()
    }
}
