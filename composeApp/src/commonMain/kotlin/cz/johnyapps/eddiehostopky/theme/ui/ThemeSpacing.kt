package cz.johnyapps.eddiehostopky.theme.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val DefaultThemeSpacing = ThemeSpacing(
    normal = 16.dp
)

data class ThemeSpacing(
    val normal: Dp
)

val LocalThemeSpacing = staticCompositionLocalOf { DefaultThemeSpacing }
