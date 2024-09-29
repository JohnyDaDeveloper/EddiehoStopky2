package cz.johnyapps.eddiehostopky.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cz.johnyapps.eddiehostopky.app.ui.model.Destination
import cz.johnyapps.eddiehostopky.theme.ui.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    destination: Destination,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val previousEntry = navController.previousBackStackEntry

    TopAppBar(
        modifier = modifier,
        title = {
            Crossfade(destination.name) { title ->
                Text(text = title)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.color.primary,
            titleContentColor = AppTheme.color.onPrimary,
        ),
        navigationIcon = {
            CompositionLocalProvider(
                LocalContentColor provides AppTheme.color.onPrimary
            ) {
                AnimatedVisibility(
                    visible = previousEntry != null,
                    enter = expandHorizontally(),
                    exit = shrinkHorizontally(),
                ) {
                    NavigateBackButton(
                        onClick = { navController.navigateUp() }
                    )
                }
            }
        },
        actions = {
            CompositionLocalProvider(
                LocalContentColor provides AppTheme.color.onPrimary
            ) {
                Crossfade(destination) { destination ->
                    with(destination) {
                        Actions(
                            modifier = modifier,
                            navController = navController,
                        )
                    }
                }
            }
        }
    )
}
