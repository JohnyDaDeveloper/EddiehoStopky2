package cz.johnyapps.eddiehostopky.app.ui

import androidx.compose.runtime.Composable
import cz.johnyapps.eddiehostopky.stopwatch.ui.StopwatchScreen
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        StopwatchScreen()
    }
}
