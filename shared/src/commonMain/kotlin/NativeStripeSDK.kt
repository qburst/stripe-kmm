
expect class ProvideStripeSdk() {

    suspend fun initialise(publishableKey: String, clientSecret: String, activity: Any? = null, context: Any? = null)

    suspend fun showPaymentSheet(clientSecret: String): String
}