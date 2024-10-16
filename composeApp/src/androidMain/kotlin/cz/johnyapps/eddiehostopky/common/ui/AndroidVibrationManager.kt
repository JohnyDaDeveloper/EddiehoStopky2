package cz.johnyapps.eddiehostopky.common.ui

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class AndroidVibrationManager(
    private val context: Context,
) : VibrationManager {

    private val vibrator by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }

    override fun click() {
        vibrate(CLICK_DURATION_MS)
    }

    override fun alert() {
        vibrate(ALERT_DURATION_MS)
    }

    private fun vibrate(durationMs: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val effect = VibrationEffect.createOneShot(
                durationMs,
                VibrationEffect.DEFAULT_AMPLITUDE,
            )

            vibrator.vibrate(effect)
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(durationMs)
        }
    }

    companion object {
        private const val CLICK_DURATION_MS = 100L
        private const val ALERT_DURATION_MS = 1_000L
    }
}
