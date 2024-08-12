import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

actual class StripeLibraryFunction {
    private val stripe = ProvideStripeSdk()
    @Composable
    actual fun showPaymentSheet(clientSecret: Any, publishableKey: String) {
        return Button(onClick = {
            CoroutineScope(Dispatchers.Default).launch {
                stripe.initialise(
                    publishableKey = publishableKey,
                    clientSecret = clientSecret as String,
                    context = null,
                    activity = null
                )
            }
        }) {
            Text("initiate payment")
        }
    }
}