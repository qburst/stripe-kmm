interface Platform {
    val name: String
    val data: String
    fun printHelloWorld(): String

}

expect fun choosePlatform(): Platform