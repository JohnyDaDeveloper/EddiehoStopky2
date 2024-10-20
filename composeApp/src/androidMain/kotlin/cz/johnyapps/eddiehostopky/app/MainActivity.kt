package cz.johnyapps.eddiehostopky.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cz.johnyapps.eddiehostopky.app.di.appModule
import cz.johnyapps.eddiehostopky.app.ui.App
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var keepSplashScreenOn = true
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplashScreenOn }

        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = 0x1D36BD,
            )
        )

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            App(
                onStartDestinationReady = {
                    keepSplashScreenOn = false
                }
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(
        onStartDestinationReady = {},
    )
}
