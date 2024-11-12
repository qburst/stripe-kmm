package striperest
import choosePlatform


class Greetings {
    private val platform = choosePlatform()

   public fun test(): String {
        return "Hello, ${platform.printHelloWorld()}!"

    }
}

