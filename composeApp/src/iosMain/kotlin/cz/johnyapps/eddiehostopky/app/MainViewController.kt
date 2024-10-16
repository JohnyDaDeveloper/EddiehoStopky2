package cz.johnyapps.eddiehostopky.app

import androidx.compose.ui.window.ComposeUIViewController
import cz.johnyapps.eddiehostopky.app.ui.App

@Suppress("FunctionNaming", "unused", "FunctionName")
fun MainViewController() = ComposeUIViewController {
    App(
        onStartDestinationReady = {}
    )
}
