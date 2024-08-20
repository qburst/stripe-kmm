import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.Address
import model.AppInfo
import model.BillingDetails
import model.FutureUsage
import model.InitialiseParams
import model.ThreeDSecureConfigurationParams
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
 fun App() {
   val stripe = ProvideStripeSdk()
    val initialiseParams = InitialiseParams(
        publishableKey = "pk_test_FkQvi0DNueKlNnVwNoJktg2W",
        appInfo = AppInfo(
            name = "Stripe App",
            version = "1.2.3",
            partnerId = "new",
            url = "https://qburst.com"
        )
    )

            val params = CreateParams.CardParamsWithToken(
            paymentMethodData = CreateParams.PaymentMethodDataWithToken(
                token = "tok_1PplP0KJ38Q1wp9dP9O9HKC0",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )

    val options  = CreateOptions(FutureUsage.OFF_SESSION)
    var PaymentResponse by remember { mutableStateOf("Click the button!") }

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    stripe.initialise(initialiseParams)
                }
            }) {
                Text("initiate payment")
            }
            Button(onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    stripe.createPaymentMethod(
                        params = params,
                        options = options,
                        onSuccess = { result ->
                            print("payment response:$result")
                            // Pass the result back to the UI through the onSuccess callback
                            PaymentResponse = result.get("id").toString()
                        },
                        onError = { error ->
                            // Pass the error back to the UI through the onError callback
                            print(error)
                        }
                    )
                }
            }) {
                Text("Create Payment Method")
            }
            Column(Modifier.padding(15.dp), ) {
                Text("publishableKey: ${initialiseParams.publishableKey}",  fontWeight = FontWeight.Bold)
                Text("Response: $PaymentResponse",  fontWeight = FontWeight.Light)
            }
        }
    }
}
