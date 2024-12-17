
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.BillingDetails
import model.ConfirmParams
import model.FutureUsage
import model.PresentOptions
import model.SetupParams
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(stripe : ProvideStripeSdk) {


    val params = CreateParams.IdealParams(
        paymentMethodData = CreateParams.PaymentMethodDataIdeal(
            bankName = "regiobank",
            billingDetails = BillingDetails(
                name = "John Doe",
                email = "test@gmail.com",
                phone = "+91954453333"
            )
        )
    )



    val returnsUrl = "https://google.com"
    val confirmParams = ConfirmParams.CardParamsWithToken(
        paymentMethodData = ConfirmParams.PaymentMethodDataWithToken(
            token = "tok_1Q8IhfKJ38Q1wp9dRbNcXL3J",
            billingDetails = BillingDetails(
                name = "John Doe",
                email = "test@gmail.com",
                phone = "+91954453333"
            )
        )
    )
    val paymentIntentClientSecret = "pi_1QWvFjKJ38Q1wp9dZx7sMpil_secret_xCUHjSyAY2wO6LtEiLLrBnPZE"
    val paymentIntentClientSecretForSetup = "seti_1QFVM9KJ38Q1wp9d1C1GwLEG_secret_R7kruZMp6KaJj36bi63kij16cZaZviZ"


    val options = CreateOptions(FutureUsage.OFF_SESSION)
    var PaymentResponse by remember { mutableStateOf("Click the button!") }

    // payment sheet params

    var paymentIntentParams = SetupParams(
        merchantDisplayName = "Qburst",
        paymentIntentClientSecret = "pi_1QWvFjKJ38Q1wp9dZx7sMpil_secret_xCUHjSyAY2wO6LtEiLLrBnPZE"
    )

    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                CoroutineScope(Dispatchers.Default).launch {
//                    stripe.initialise(initialiseParams)
                }
            }) {
                Text("setup stripe")
            }

            Button(onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    stripe.createPaymentMethod(
                        params = params,
                        options = options,
                        onSuccess = { result ->
                            print(" result = $result")
                            // Pass the result back to the UI through the onSuccess callback
                            PaymentResponse = result.toString()
                        },
                        onError = { error ->
                            // Pass the error back to the UI through the onError callback
                            PaymentResponse = error.toString()
                            print(error)
                            PaymentResponse = error.toString()
                        }
                    )
                }
            }) {
                Text("Create Payment Method")
            }

            Button(onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    stripe.confirmPayment(
                        paymentIntentClientSecret = paymentIntentClientSecret,
                        params = confirmParams,
                        options = options,
                        onSuccess = { result ->
                            print(" result = $result")
                            // Pass the result back to the UI through the onSuccess callback
                            PaymentResponse = result.toString()
                        },
                        onError = { error ->
                            // Pass the error back to the UI through the onError callback
                            PaymentResponse = error.toString()
                            print(error)
                            PaymentResponse = error.toString()
                        }
                    )
                }
            }) {
                Text("Confirm Payment Method")
            }

            Button(onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    stripe.handleNextAction(
                        paymentIntentClientSecret = paymentIntentClientSecret,
                        returnURL = returnsUrl,
                        onSuccess = { result ->
                            print(" result = $result")
                            // Pass the result back to the UI through the onSuccess callback
                            PaymentResponse = result.toString()
                        },
                        onError = { error ->
                            // Pass the error back to the UI through the onError callback
                            PaymentResponse = error.toString()
                            print(error)
                            PaymentResponse = error.toString()
                        }
                    )
                }
            }) {
                Text("Handle Next Actions")
            }


            Button(onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    stripe.handleNextActionForSetup(
                        setupIntentClientSecret = paymentIntentClientSecretForSetup,
                        returnURL = returnsUrl,
                        onSuccess = { result ->
                            print(" result = $result")
                            // Pass the result back to the UI through the onSuccess callback
                            PaymentResponse = result.toString()
                        },
                        onError = { error ->
                            // Pass the error back to the UI through the onError callback
                            PaymentResponse = error.toString()
                            print(error)
                            PaymentResponse = error.toString()
                        }
                    )
                }
            }) {
                Text("Handle Next Actions Setup")
            }



            Button(onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    stripe.initPaymentSheet(
                        params = paymentIntentParams,
                        onSuccess = { result ->
                            print(" result = $result")
                            // Pass the result back to the UI through the onSuccess callback
                            PaymentResponse = result.toString()
                        },
                        onError = { error ->
                            // Pass the error back to the UI through the onError callback
                            PaymentResponse = error.toString()
                            print(error)
                            PaymentResponse = error.toString()
                        }
                    )
                }
            }) {
                Text("Setup Payment Sheet")
            }


            Button(onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    stripe.presentPaymentSheet(
                        options = PresentOptions(),
                        onSuccess = { result ->
                            print(" result = $result")
                            // Pass the result back to the UI through the onSuccess callback
                            PaymentResponse = result.toString()
                        },
                        onError = { error ->
                            // Pass the error back to the UI through the onError callback
                            PaymentResponse = error.toString()
                            print(error)
                            PaymentResponse = error.toString()
                        }
                    )
                }
            }) {
                Text("Show Payment Sheet")
            }

            Column(Modifier.padding(15.dp)) {
//                Text(
////                    "publishableKey: ${initialiseParams.publishableKey}",
//                    fontWeight = FontWeight.Bold
//                )
                Text("Response: $PaymentResponse", fontWeight = FontWeight.Light)
            }
        }
    }
}
