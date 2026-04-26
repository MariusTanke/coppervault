package co.coppervault.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform