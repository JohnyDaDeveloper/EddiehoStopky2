package cz.johnyapps.eddiehostopky.theme.ui

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

object AppTheme {

    val color: ThemeColors
        @Composable
        get() = LocalThemeColors.current

    val spacing: ThemeSpacing
        @Composable
        get() = LocalThemeSpacing.current

    val typography: ThemeTypography
        @Composable
        get() = LocalThemeTypography.current
}

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalThemeColors provides AppTheme.color,
        LocalContentColor provides AppTheme.color.onSurface,
        LocalTextStyle provides AppTheme.typography.normal,
    ) {
        Surface(color = AppTheme.color.surface) {
            content()
        }
    }
}

@Composable
fun AppThemedPreview(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    AppTheme {
        Surface(
            modifier = modifier,
            color = AppTheme.color.surface
        ) {
            content()
        }
    }
}
