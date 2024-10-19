package cz.johnyapps.eddiehostopky.settings.domain.model

class Settings(
    val pauseAllWhenMatchIsPaused: Boolean,
    val offenseCountdownControlledByMatch: Boolean,
    val switchButtons: Boolean,
    val alertBeforeOffenseEndSeconds: Int,
) {

    companion object {
        fun createDefault(): Settings {
            return Settings(
                pauseAllWhenMatchIsPaused = true,
                offenseCountdownControlledByMatch = true,
                switchButtons = true,
                alertBeforeOffenseEndSeconds = 5,
            )
        }
    }
}
