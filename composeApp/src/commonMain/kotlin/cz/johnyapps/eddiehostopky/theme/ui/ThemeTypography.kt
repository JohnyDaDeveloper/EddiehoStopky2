package cz.johnyapps.eddiehostopky.theme.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val DefaultThemeTypography = ThemeTypography(
    extraLarge = TextStyle(
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
    ),
    large = TextStyle(
        fontSize = 24.sp,
    ),
    normal = TextStyle(
        fontSize = 16.sp,
    )
)

data class ThemeTypography(
    val extraLarge: TextStyle,
    val large: TextStyle,
    val normal: TextStyle,
)

val LocalThemeTypography = staticCompositionLocalOf { DefaultThemeTypography }
