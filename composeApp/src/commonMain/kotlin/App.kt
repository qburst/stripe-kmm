import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.AppInfo
import model.BillingDetails
import model.ConfirmParams
import model.FutureUsage
import model.InitialiseParams
import model.IntentParams
import model.PresentOptions
import model.SetupParams
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
    val paymentIntentClientSecret = "pi_1QFVQFKJ38Q1wp9dnqLojinN_secret_H2qSeJTtRZaTAA4MaQsC6pbuY"
    val paymentIntentClientSecretForSetup = "seti_1QFVM9KJ38Q1wp9d1C1GwLEG_secret_R7kruZMp6KaJj36bi63kij16cZaZviZ"


    val options = CreateOptions(FutureUsage.OFF_SESSION)
    var PaymentResponse by remember { mutableStateOf("Click the button!") }
    var selectedMethod by remember { mutableStateOf("") }
    var bankName by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    val idealBanks = listOf(
        "abn_amro",
        "asn_bank",
        "bunq",
        "handelsbanken",
        "ing",
        "knab",
        "moneyou",
        "n26",
        "nn",
        "rabobank",
        "regiobank",
        "revolut",
        "sns_bank",
        "triodos_bank",
        "van_lanschot",
        "yoursafe"
    )
    var expanded by remember { mutableStateOf(false) }
    var paymentDetails = remember { mutableStateMapOf<String, String>() }
    var billingDetails = remember { mutableStateMapOf<String, String>() }

    fun setPaymentDetail(maxLength: Int, key: String): (String) -> Unit {
        return { newValue ->
            if (newValue.length <= maxLength) {
                paymentDetails[key] = newValue  // Update state only if within max length
            }
        }
    }

    // payment sheet params

    var paymentIntentParams = SetupParams(
        merchantDisplayName = "Qburst",
            paymentIntentClientSecret = "pi_1QGJFAKJ38Q1wp9dTYiMh8Ky_secret_XIqXggjC122NecfipP2KQOWQH"
    )

    MaterialTheme {
        Column(Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.Start) {

            OutlinedTextField(
                value = billingDetails["name"]?:"",
                onValueChange = { billingDetails["name"] = it },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = billingDetails["email"]?:"",
                onValueChange = { billingDetails["email"] = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = billingDetails["phone"]?:"",
                onValueChange = { billingDetails["phone"] = it },
                label = { Text("Phone") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Row(verticalAlignment = Alignment.CenterVertically,) {
                RadioButton(
                    selected = selectedMethod == "Ideal",
                    onClick = {
                        selectedMethod = "Ideal"
                        paymentDetails.clear()
                    },
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Ideal")
            }
            AnimatedVisibility(visible = selectedMethod == "Ideal") {

                Box(modifier = Modifier.fillMaxWidth()
                    .clickable { expanded = !expanded }) {
                    OutlinedTextField(
                        value = paymentDetails["bank"] ?: "",
                        onValueChange = { },
                        label = { Text("Bank Name") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true }
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }, // Close the dropdown when clicked outside
                    ) {
                        idealBanks.forEach { bank ->
                            DropdownMenuItem(onClick = {
                                paymentDetails["bank"] = bank// Set selected bank
                                expanded = false // Close dropdown
                            }) {
                                Text(text = bank)
                            }
                        }
                    }
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically,) {
                RadioButton(
                    selected = selectedMethod == "Card",
                    onClick = { selectedMethod = "Card" },
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Card")
            }
            AnimatedVisibility(visible = selectedMethod == "Card") {
                Column() {

                    OutlinedTextField(
                        value = paymentDetails["token"] ?: "",
                        onValueChange = {paymentDetails["token"] = it},
                        label = { Text("Token") },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically,) {
                RadioButton(
                    selected = selectedMethod == "Upi",
                    onClick = { selectedMethod = "Upi" },
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "UPI")
            }
            AnimatedVisibility(visible = selectedMethod == "Upi") {
                Column() {

                    OutlinedTextField(
                        value = paymentDetails["vpa"] ?: "",
                        onValueChange = {paymentDetails["vpa"] = it},
                        label = { Text("VPA") },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically,) {
                RadioButton(
                    selected = selectedMethod == "CashApp",
                    onClick = { selectedMethod = "CashApp" },
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "CashApp")
            }

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
                            params = when(selectedMethod) {
                                "Ideal" -> {
                                    CreateParams.IdealParams(
                                        paymentMethodData = CreateParams.PaymentMethodDataIdeal(
                                            bankName = paymentDetails["bank"],
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Card" ->{
                                    CreateParams.CardParamsWithToken(
                                        paymentMethodData = CreateParams.PaymentMethodDataWithToken(token = paymentDetails["token"],
                                                billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                        phone = billingDetails["phone"],
                                        name = billingDetails["name"])
                                    )
                                    )
                                }
                                "Upi" ->{
                                    CreateParams.UpiParams(
                                        paymentMethodData = CreateParams.PaymentMethodDataUpi(
                                            vpa = paymentDetails["vpa"],
                                            billingDetails = BillingDetails()
                                        )
                                    )
                                }
                                "CashApp" ->{ CreateParams.CashAppParams(
                                    paymentMethodData = CreateParams.PaymentMethodDataCashApp(
                                        billingDetails = BillingDetails(),
                                    )
                                )}
                                else -> throw IllegalStateException("Selected method cannot be $selectedMethod")
                            },


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

                Column() {
                    Text(
                        "publishableKey: ${initialiseParams.publishableKey}",
                        fontWeight = FontWeight.Bold
                    )
                    Text("Response: $PaymentResponse", fontWeight = FontWeight.Light)
                }
            }
        }
    }
}
