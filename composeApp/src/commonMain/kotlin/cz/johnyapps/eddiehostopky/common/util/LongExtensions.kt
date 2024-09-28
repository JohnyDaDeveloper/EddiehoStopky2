package cz.johnyapps.eddiehostopky.common.util

fun Long.digits(digits: Int): String {
    var text = "$this"

    while (text.length < digits) {
        text = "0$text"
    }

    return text
}
