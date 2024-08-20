import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.qburst.stripe_kmm.StripeSdkSingleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

actual class StripeLibraryFunction {


    @Composable
    actual fun showPaymentSheet(clientSecret: Any, publishableKey: String) {

        var onCLicked by remember { mutableStateOf(false) }

        if (onCLicked) {
            LaunchedEffect(clientSecret) {
                CoroutineScope(Dispatchers.Main).launch {
                    StripeSdkSingleton.provideStripeSdk.showPaymentSheet("pi_1PmuqXKJ38Q1wp9dLgw8eijG_secret_5W09ySjx4NofVyUq9os07fOKj")
                }
            }
            onCLicked = false
        }

        return Button(onClick = {
            onCLicked = true
        }) {
            Text("initiate payment Android")
        }
    }
}