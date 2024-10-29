interface Platform {
    val name: String
    val data: String
    fun getValue(): String

}

expect fun choosePlatform(): Platform