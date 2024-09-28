package cz.johnyapps.eddiehostopky

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
