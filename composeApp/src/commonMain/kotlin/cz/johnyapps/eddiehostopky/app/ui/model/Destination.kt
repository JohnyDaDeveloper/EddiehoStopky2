package cz.johnyapps.eddiehostopky.app.ui.model

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cz.johnyapps.eddiehostopky.app.ui.navigate
import cz.johnyapps.eddiehostopky.stopwatch.StopwatchTopBarActions
import eddiehostopky.composeapp.generated.resources.Res
import eddiehostopky.composeapp.generated.resources.settings_title
import eddiehostopky.composeapp.generated.resources.stopwatch_title
import org.jetbrains.compose.resources.stringResource

@Stable
sealed interface Destination {

    val route: String

    @get:Composable
    val name: String

    @Composable
    fun RowScope.Actions(
        modifier: Modifier,
        navController: NavController,
    )

    data object Stopwatch : Destination {
        override val route: String = "stopwatch"
        override val name: String
            @Composable
            get() = stringResource(Res.string.stopwatch_title)

        @Composable
        override fun RowScope.Actions(
            modifier: Modifier,
            navController: NavController,
        ) {
            StopwatchTopBarActions(
                modifier = modifier,
                onSettingsClick = { navController.navigate(Settings) }
            )
        }
    }

    data object Settings : Destination {
        override val route: String = "settings"
        override val name: String
            @Composable
            get() = stringResource(Res.string.settings_title)

        @Composable
        override fun RowScope.Actions(
            modifier: Modifier,
            navController: NavController,
        ) {
        }
    }

    companion object {
        val start: Destination = Stopwatch

        fun byRoute(route: String?): Destination {
            return when (route) {
                Stopwatch.route -> Stopwatch
                Settings.route -> Settings
                else -> Stopwatch
            }
        }
    }
}
