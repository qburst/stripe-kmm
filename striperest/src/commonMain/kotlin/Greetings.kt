class Greetings {
    private val platform = choosePlatform()

    fun test(): String {
        return "Hello, ${platform.getValue()}!"

    }
}

