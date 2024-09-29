package cz.johnyapps.eddiehostopky.app.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.johnyapps.eddiehostopky.app.ui.model.Destination
import cz.johnyapps.eddiehostopky.settings.ui.SettingsScreen
import cz.johnyapps.eddiehostopky.stopwatch.ui.StopwatchScreen
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    val currentEntry by navController.currentBackStackEntryAsState()
    val currentDestination = Destination.byRoute(currentEntry?.destination?.route)

    AppTheme {
        Scaffold(
            topBar = {
                AppTopBar(
                    destination = currentDestination,
                    navController = navController,
                )
            }
        ) { paddingValues ->
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                navController = navController,
                startDestination = Destination.start.route
            ) {
                destination(Destination.Stopwatch) {
                    StopwatchScreen()
                }

                destination(Destination.Settings) {
                    SettingsScreen()
                }
            }
        }
    }
}

private fun NavGraphBuilder.destination(
    destination: Destination,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.route
    ) { entry ->
        // Workaround for https://issuetracker.google.com/issues/336842920
        CompositionLocalProvider(
            LocalLifecycleOwner provides entry,
        ) {
            content(entry)
        }
    }
}

fun NavController.navigate(destination: Destination) {
    navigate(destination.route)
}
