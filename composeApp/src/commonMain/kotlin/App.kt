import androidx.compose.foundation.layout.Column
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
import model.AppInfo
import model.BillingDetails
import model.ConfirmParams
import model.FutureUsage
import model.InitialiseParams
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
//    sk_test_hPRNV2gZ6gcIV99ndFejwEHT
    val stripe = ProvideStripeSdk()
    val initialiseParams = InitialiseParams(
        publishableKey = "pk_test_FkQvi0DNueKlNnVwNoJktg2W",
        appInfo = AppInfo(
            name = "Stripe App",
            version = "1.2.3",
            partnerId = "new",
            url = "https://qburst.com",
        )
    )


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


    val paymentIntentClientSecret = "pi_1Q9YG9KJ38Q1wp9dt3k3fpeg_secret_mCmrDxJGcLPOfE3BuBzAl8CM8"
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

    val paymentIntentClientSecretForSetup = "seti_1QCegBKJ38Q1wp9dwSb3Jpr9_secret_R4oIFAfIxs3IQj6p9pt9dRdF6okgCyh"


    val options = CreateOptions(FutureUsage.OFF_SESSION)
    var PaymentResponse by remember { mutableStateOf("Click the button!") }

    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    stripe.initialise(initialiseParams)
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

            Column(Modifier.padding(15.dp)) {
                Text(
                    "publishableKey: ${initialiseParams.publishableKey}",
                    fontWeight = FontWeight.Bold
                )
                Text("Response: $PaymentResponse", fontWeight = FontWeight.Light)
            }
        }
    }
}
