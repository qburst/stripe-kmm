interface Platform {
    fun printHelloWorld(): String

}

expect fun choosePlatform(): Platform