package cz.johnyapps.eddiehostopky.theme.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LightColors = ThemeColors(
    primary = Color(0xFF90b7f5),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),
)

@Immutable
data class ThemeColors(
    val primary: Color,
    val surface: Color,
    val onSurface: Color,
)

internal val LocalThemeColors = staticCompositionLocalOf { LightColors }
