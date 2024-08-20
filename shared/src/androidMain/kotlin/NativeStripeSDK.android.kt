import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

actual class ProvideStripeSdk actual constructor() {

    lateinit var paymentSheet: PaymentSheet

    actual suspend fun initialise(
        publishableKey: String,
        clientSecret: String,
        activity: Any?,
        context: Any?
    ) {

        PaymentConfiguration.init(context = context as Context, publishableKey = publishableKey)

        paymentSheet = PaymentSheet(activity as ComponentActivity) { paymentSheet ->
            val result = onPaymentSheetResult(paymentSheet)
            Toast.makeText(context, result, Toast.LENGTH_LONG).show()
        }

    }

    private fun presentPaymentSheet(
        paymentSheet: PaymentSheet,
        paymentIntentClientSecret: String
    ) {
        paymentSheet.presentWithPaymentIntent(
            paymentIntentClientSecret,
            PaymentSheet.Configuration(
                merchantDisplayName = "Stripe Libray",
                allowsDelayedPaymentMethods = true
            )
        )
    }

    private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult): String {
        when (paymentSheetResult) {
            is PaymentSheetResult.Canceled -> {
                return "cancelled"
            }

            is PaymentSheetResult.Failed -> {
                return "Error: ${paymentSheetResult.error}"
            }

            is PaymentSheetResult.Completed -> {
                return "Completed"
            }
        }
    }

    actual suspend fun showPaymentSheet(clientSecret: String): String {
        CoroutineScope(Dispatchers.Default).launch {
            presentPaymentSheet(
                paymentSheet = paymentSheet,
                clientSecret
            )
        }
        return ""
    }
}