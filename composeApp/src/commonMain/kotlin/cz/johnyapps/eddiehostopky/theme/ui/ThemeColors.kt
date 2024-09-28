package cz.johnyapps.eddiehostopky.theme.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
val LightColors = ThemeColors(
    primary = Color(0xFF1D36BD),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF950E0E),
    onSecondary = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),
)

@Immutable
data class ThemeColors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val surface: Color,
    val onSurface: Color,
)

internal val LocalThemeColors = staticCompositionLocalOf { LightColors }
