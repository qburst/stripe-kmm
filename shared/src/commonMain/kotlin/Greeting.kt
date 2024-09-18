class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.getNumber()}!"

    }

    fun paymentTest(): String {
    return "${platform.getPayment()}"

    }
}

