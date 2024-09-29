package cz.johnyapps.eddiehostopky.theme.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

val DefaultThemeShapes = ThemeShapes(
    normal = RoundedCornerShape(8.dp)
)

@Immutable
data class ThemeShapes(
    val normal: Shape,
)

val LocalThemeShapes = staticCompositionLocalOf { DefaultThemeShapes }
