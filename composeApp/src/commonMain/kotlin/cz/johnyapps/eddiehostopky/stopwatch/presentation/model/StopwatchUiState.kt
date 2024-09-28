package cz.johnyapps.eddiehostopky.stopwatch.presentation.model

data class StopwatchUiState(
    val a: String,
) {

    companion object {
        fun initial(): StopwatchUiState {
            return StopwatchUiState(
                a = "",
            )
        }
    }
}