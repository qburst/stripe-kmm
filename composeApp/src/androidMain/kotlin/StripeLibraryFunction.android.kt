import androidx.activity.ComponentActivity
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

actual class StripeLibraryFunction {
    private val stripe = ProvideStripeSdk()

    @Composable
    actual fun showPaymentSheet(clientSecret: Any, publishableKey: String) {
        val context = LocalContext.current
        val activity = LocalContext.current as ComponentActivity
        return Button(onClick = {
            CoroutineScope(Dispatchers.Default).launch {
                stripe.initialise(
                    publishableKey = publishableKey,
                    clientSecret = clientSecret as String,
                    context = context,
                    activity = activity
                )
            }
        }) {
            Text("initiate payment")
        }
    }

}