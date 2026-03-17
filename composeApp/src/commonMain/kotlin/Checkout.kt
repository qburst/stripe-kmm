import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.Colors
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun Checkout(onNavigate: () -> Unit) {
//    sk_test_hPRNV2gZ6gcIV99ndFejwEHT
    val stripe = ProvideStripeSdk()
    val initialiseParams = InitialiseParams(
        publishableKey = "pk_test_51T6WegLVz2NSSNqKE9CeO3LTXyVVmtbovZc8WVwMlTrlFSglxsr0YxYQx5IICIK69Ul1PYKoV4OtqI42jyQZJ9KQ00VtVJusQQ",
        appInfo = AppInfo(
            name = "Stripe App",
            version = "1.2.3",
            partnerId = "new",
            url = "https://qburst.com",
        )
    )
    val paymentIntentClientSecret = "pi_3TBupDLVz2NSSNqK1Kq2kAqI_secret_jNbxfyqwUbR6pplTFdacP27c3"
    val returnsUrl = "https://google.com"

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
    var fpxExpanded by remember { mutableStateOf(false) }
    var paymentDetails = remember { mutableStateMapOf<String, String>() }
    var billingDetails = remember { mutableStateMapOf<String, String>() }
    var paymentParams = remember { mutableStateOf(null) }
    val paymentIntentClientSecretForSetup = "seti_1QCegBKJ38Q1wp9dwSb3Jpr9_secret_R4oIFAfIxs3IQj6p9pt9dRdF6okgCyh"

    val scrollState = rememberScrollState()

    val paymentMethods = listOf(
        "Ideal", "Card", "UPI", "FPX", "PayPal", "SEPA Debit", "AU BECS Debit",
        "Bacs Debit", "Sofort", "NetBanking", "US Bank Account", "CashApp", "Swish",
        "Bancontact", "EPS Debit", "OXXO", "Alipay", "Afterpay/Clearpay", "BLIK",
        "WeChat Pay", "Klarna", "Affirm", "Amazon Pay", "Multibanco", "Alma",
        "Sunbit", "Billie", "Satispay", "Revolut Pay", "MobilePay", "Giropay", "Google Pay"
    )

    val fpxBankIdentifiers = listOf(
        "affin_bank",
        "alliance_bank",
        "ambank",
        "bank_islam",
        "bank_muamalat",
        "bank_rakyat",
        "bsn",
        "cimb",
        "hong_leong_bank",
        "hsbc",
        "kfh",
        "maybank2e",
        "maybank2u",
        "ocbc",
        "public_bank",
        "rhb",
        "standard_chartered",
        "uob"
    )


    MaterialTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {

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

            Text(
                text = "Select Payment Method:",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                paymentMethods.forEach { method ->
                    val isSelected = when(method) {
                        "UPI" -> selectedMethod == "Upi"
                        "SEPA Debit" -> selectedMethod == "SEPA"
                        "AU BECS Debit" -> selectedMethod == "AuBecsDebit"
                        "Bacs Debit" -> selectedMethod == "BacsDebit"
                        "US Bank Account" -> selectedMethod == "USBankAccount"
                        "EPS Debit" -> selectedMethod == "EpsDebit"
                        "Afterpay/Clearpay" -> selectedMethod == "AfterpayClearpay"
                        "WeChat Pay" -> selectedMethod == "WeChatPay"
                        "Amazon Pay" -> selectedMethod == "AmazonPay"
                        "Multibanco" -> selectedMethod == "MultiBanco"
                        "Revolut Pay" -> selectedMethod == "RevolutPay"
                        "Google Pay" -> selectedMethod == "GooglePay"
                        else -> selectedMethod == method
                    }

                    Chip(
                        onClick = {
                            val methodKey = when(method) {
                                "UPI" -> "Upi"
                                "SEPA Debit" -> "SEPA"
                                "AU BECS Debit" -> "AuBecsDebit"
                                "Bacs Debit" -> "BacsDebit"
                                "US Bank Account" -> "USBankAccount"
                                "EPS Debit" -> "EpsDebit"
                                "Afterpay/Clearpay" -> "AfterpayClearpay"
                                "WeChat Pay" -> "WeChatPay"
                                "Amazon Pay" -> "AmazonPay"
                                "Multibanco" -> "MultiBanco"
                                "Revolut Pay" -> "RevolutPay"
                                "Google Pay" -> "GooglePay"
                                else -> method
                            }
                            if (selectedMethod == methodKey) {
                                selectedMethod = ""
                                paymentDetails.clear()
                            } else {
                                selectedMethod = methodKey
                                paymentDetails.clear()
                            }
                        },
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
                            contentColor = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface
                        ),
                        modifier = Modifier.wrapContentHeight(),
                        border = BorderStroke(1.dp, Color.Gray)
                    ) {
                        Text(method)
                    }
                }
            }

 Spacer(modifier = Modifier.padding(8.dp))

            AnimatedVisibility(visible = selectedMethod == "Ideal") {
                ExposedDropdownMenuBox(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = paymentDetails["bank"] ?: "",
                        onValueChange = {},
                        label = { Text("Bank Name") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true }
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true }
                    ) {
                        idealBanks.forEach { bank ->
                            DropdownMenuItem(onClick = {
                                paymentDetails["bank"] = bank
                                expanded = false
                            }) {
                                Text(text = bank)
                            }
                        }
                    }
                }
            }

            AnimatedVisibility(visible = selectedMethod == "Card") {
                Column {
                    OutlinedTextField(
                        value = paymentDetails["token"] ?: "",
                        onValueChange = {paymentDetails["token"] = it},
                        label = { Text("Token") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }

            AnimatedVisibility(visible = selectedMethod == "Upi") {
                Column {
                    OutlinedTextField(
                        value = paymentDetails["vpa"] ?: "",
                        onValueChange = {paymentDetails["vpa"] = it},
                        label = { Text("VPA") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }

            AnimatedVisibility(visible = selectedMethod == "FPX") {
                ExposedDropdownMenuBox(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = fpxExpanded,
                    onExpandedChange = { fpxExpanded = !fpxExpanded }
                ) {
                    OutlinedTextField(
                        value = paymentDetails["bank"] ?: "",
                        onValueChange = {},
                        label = { Text("Bank Name") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { fpxExpanded = true }
                    )

                    ExposedDropdownMenu(
                        expanded = fpxExpanded,
                        onDismissRequest = { fpxExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { fpxExpanded = true }
                    ) {
                        fpxBankIdentifiers.forEach { bank ->
                            DropdownMenuItem(onClick = {
                                paymentDetails["bank"] = bank
                                fpxExpanded = false
                            }) {
                                Text(text = bank)
                            }
                        }
                    }
                }
            }

            AnimatedVisibility(visible = selectedMethod == "SEPA") {
                OutlinedTextField(
                    value = paymentDetails["iban"] ?: "",
                    onValueChange = { paymentDetails["iban"] = it },
                    label = { Text("IBAN") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            AnimatedVisibility(visible = selectedMethod == "AuBecsDebit") {
                Column {
                    OutlinedTextField(
                        value = paymentDetails["bsbNumber"] ?: "",
                        onValueChange = { paymentDetails["bsbNumber"] = it },
                        label = { Text("BSB Number") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = paymentDetails["accountNumber"] ?: "",
                        onValueChange = { paymentDetails["accountNumber"] = it },
                        label = { Text("Account Number") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            AnimatedVisibility(visible = selectedMethod == "BacsDebit") {
                Column {
                    OutlinedTextField(
                        value = paymentDetails["sortCode"] ?: "",
                        onValueChange = { paymentDetails["sortCode"] = it },
                        label = { Text("Sort Code") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = paymentDetails["accountNumber"] ?: "",
                        onValueChange = { paymentDetails["accountNumber"] = it },
                        label = { Text("Account Number") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            AnimatedVisibility(visible = selectedMethod == "Sofort") {
                OutlinedTextField(
                    value = paymentDetails["country"] ?: "",
                    onValueChange = { paymentDetails["country"] = it },
                    label = { Text("Country") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            AnimatedVisibility(visible = selectedMethod == "NetBanking") {
                OutlinedTextField(
                    value = paymentDetails["bank"] ?: "",
                    onValueChange = { paymentDetails["bank"] = it },
                    label = { Text("Bank Name") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            AnimatedVisibility(visible = selectedMethod == "USBankAccount") {
                Column {
                    OutlinedTextField(
                        value = paymentDetails["routingNumber"] ?: "",
                        onValueChange = { paymentDetails["routingNumber"] = it },
                        label = { Text("Routing Number") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = paymentDetails["accountNumber"] ?: "",
                        onValueChange = { paymentDetails["accountNumber"] = it },
                        label = { Text("Account Number") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
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
                                            bankName = "regiobank",
                                            billingDetails = BillingDetails(
                                                name = "John Doe",
                                                email = "test@gmail.com",
                                                phone = "+91954453333"
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
                                PaymentResponse = result.toString()
                            },
                            onError = { error ->
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
                            params = when(selectedMethod) {
                                "Ideal" -> {
                                    ConfirmParams.IdealParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataIdeal(
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
                                    ConfirmParams.CardParamsWithToken(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataWithToken(token = paymentDetails["token"],
                                                billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                        phone = billingDetails["phone"],
                                        name = billingDetails["name"])
                                    )
                                    )
                                }
                                "Upi" -> {
                                    ConfirmParams.UpiParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataUpi(vpa = paymentDetails["vpa"],
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "FPX" -> {
                                    ConfirmParams.FpxParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataFpx(
                                            bankName = paymentDetails["bank"],
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "PayPal" -> {
                                    ConfirmParams.PayPalParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataPayPal(
                                            billingDetails = BillingDetails()
                                        )
                                    )
                                }
                                "SEPA" -> {
                                    ConfirmParams.SepaDebitParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataSepaDebit(
                                            iban = paymentDetails["iban"],
                                            billingDetails = BillingDetails()
                                        )
                                    )
                                }
                                "AuBecsDebit" -> {
                                    ConfirmParams.AuBecsDebitParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataAuBecs(
                                            bsbNumber = paymentDetails["bsbNumber"] ?: "",
                                            accountNumber = paymentDetails["accountNumber"] ?: "",
                                            billingDetails = BillingDetails()
                                        )
                                    )
                                }
                                "BacsDebit" -> {
                                    ConfirmParams.BacsDebitParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataBacsDebit(
                                            sortCode = paymentDetails["sortCode"] ?: "",
                                            accountNumber = paymentDetails["accountNumber"] ?: "",
                                            billingDetails = BillingDetails()
                                        )
                                    )
                                }
                                "Sofort" -> {
                                    ConfirmParams.SofortParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataSofort(
                                            country = paymentDetails["country"],
                                            billingDetails = BillingDetails()
                                        )
                                    )
                                }
                                "NetBanking" -> {
                                    ConfirmParams.NetBankingParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataNetBanking(
                                            bank = paymentDetails["bank"],
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "USBankAccount" -> {
                                    ConfirmParams.USBankAccountParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataUSBankAccount(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            ),
                                            accountNumber = paymentDetails["accountNumber"],
                                            routingNumber = paymentDetails["routingNumber"],
                                        )
                                    )
                                }
                                "CashApp" -> {
                                    ConfirmParams.CashAppParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataCashApp(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Swish" -> {
                                    ConfirmParams.SwishParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataSwish(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Bancontact" -> {
                                    ConfirmParams.BancontactParams(
                                        paymentMethodData = ConfirmParams.BancontactDataParams(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "EpsDebit" -> {
                                    ConfirmParams.EpsDebitParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataEps(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Oxxo" -> {
                                    ConfirmParams.OxxoParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataOxxo(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Alipay" -> {
                                    ConfirmParams.AlipayParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataAlipay(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "AfterpayClearpay" -> {
                                    ConfirmParams.AfterpayClearpayParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataAfterpayClearpay(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Blik" -> {
                                    ConfirmParams.BlikParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataBlik(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "WeChatPay" -> {
                                    ConfirmParams.WeChatPayParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataWeChatPay(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Klarna" -> {
                                    ConfirmParams.KlarnaParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataKlarna(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Affirm" -> {
                                    ConfirmParams.AffirmParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataAffirm(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "AmazonPay" -> {
                                    ConfirmParams.AmazonPayParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataAmazonPay(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "MultiBanco" -> {
                                    ConfirmParams.MultiBancoParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataMultiBanco(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Alma" -> {
                                    ConfirmParams.AlmaParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataAlma(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Sunbit" -> {
                                    ConfirmParams.SunbitParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataSunbit(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Billie" -> {
                                    ConfirmParams.BillieParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataBillie(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Satispay" -> {
                                    ConfirmParams.SatispayParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataSatispay(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "RevolutPay" -> {
                                    ConfirmParams.RevolutPayParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataRevolutPay(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "MobilePay" -> {
                                    ConfirmParams.MobilePayParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataMobilePay(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "Giropay" -> {
                                    ConfirmParams.GiropayParams(
                                        paymentMethodData = ConfirmParams.PaymentMethodDataGiropay(
                                            billingDetails = BillingDetails(
                                                email = billingDetails["email"],
                                                phone = billingDetails["phone"],
                                                name = billingDetails["name"]
                                            )
                                        )
                                    )
                                }
                                "GooglePay" -> {
                                    ConfirmParams.GooglePayParams(
                                        jsonObject = null
                                    )
                                }
                                else -> throw IllegalStateException("Selected method cannot be $selectedMethod")
                            },
                            options = options,
                            onSuccess = { result ->
                                print(" result = $result")
                                PaymentResponse = result.toString()
                            },
                            onError = { error ->
                                PaymentResponse = error.toString()
                                print(error)
                                PaymentResponse = error.toString()
                            }
                        )
                    }
                }) {
                    Text("Confirm Payment Method")
                }
//
//                Button(onClick = {
//                    CoroutineScope(Dispatchers.Default).launch {
//                        stripe.handleNextAction(
//                            paymentIntentClientSecret = paymentIntentClientSecret,
//                            returnURL = returnsUrl,
//                            onSuccess = { result ->
//                                print(" result = $result")
//                                // Pass the result back to the UI through the onSuccess callback
//                                PaymentResponse = result.toString()
//                            },
//                            onError = { error ->
//                                // Pass the error back to the UI through the onError callback
//                                PaymentResponse = error.toString()
//                                print(error)
//                                PaymentResponse = error.toString()
//                            }
//                        )
//                    }
//                }) {
//                    Text("Handle Next Actions")
//                }
//
//
//                Button(onClick = {
//                    CoroutineScope(Dispatchers.Default).launch {
//                        stripe.handleNextActionForSetup(
//                            setupIntentClientSecret = paymentIntentClientSecretForSetup,
//                            returnURL = returnsUrl,
//                            onSuccess = { result ->
//                                print(" result = $result")
//                                // Pass the result back to the UI through the onSuccess callback
//                                PaymentResponse = result.toString()
//                            },
//                            onError = { error ->
//                                // Pass the error back to the UI through the onError callback
//                                PaymentResponse = error.toString()
//                                print(error)
//                                PaymentResponse = error.toString()
//                            }
//                        )
//                    }
//                }) {
//                    Text("Handle Next Actions Setup")
//                }

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
