package cz.johnyapps.eddiehostopky.theme.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val DefaultThemeSpacings = ThemeSpacings(
    normal = 16.dp
)

@Immutable
data class ThemeSpacings(
    val normal: Dp
)

val LocalThemeSpacings = staticCompositionLocalOf { DefaultThemeSpacings }
