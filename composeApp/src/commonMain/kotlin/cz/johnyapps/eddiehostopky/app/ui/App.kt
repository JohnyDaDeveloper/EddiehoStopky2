package cz.johnyapps.eddiehostopky.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import cz.johnyapps.eddiehostopky.stopwatch.ui.StopwatchScreen
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalLifecycleOwner as OldLocalLifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner as NewLocalLifecycleOwner

@Composable
@Preview
fun App() {
    AppTheme {
        // Workaround for https://issuetracker.google.com/issues/336842920
        CompositionLocalProvider(
            NewLocalLifecycleOwner provides OldLocalLifecycleOwner.current
        ) {
            StopwatchScreen()
        }
    }
}
