package cz.johnyapps.eddiehostopky.settings.domain.model

class Settings(
    val pauseAllWhenMatchIsPaused: Boolean,
    val offenseCountdownControlledByMatch: Boolean,
    val restartOffenseCountdownButtonAtLeft: Boolean,
    val alertBeforeOffenseEndSeconds: Int,
)
