package cz.johnyapps.eddiehostopky.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform

interface VibrationManager {

    fun click()

    fun alert()
}

@Composable
fun rememberVibrationManager(): VibrationManager {
    return remember {
        KoinPlatform.getKoin().get<VibrationManager>()
    }
}
