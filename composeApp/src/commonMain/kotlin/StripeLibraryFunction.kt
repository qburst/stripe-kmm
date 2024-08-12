import androidx.compose.runtime.Composable

expect class StripeLibraryFunction() {

    @Composable
    fun showPaymentSheet(clientSecret: Any, publishableKey: String)

}