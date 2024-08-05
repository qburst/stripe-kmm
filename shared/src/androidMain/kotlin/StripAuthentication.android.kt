import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult


actual class StripAuthentication actual constructor() {
    lateinit var paymentSheet: PaymentSheet
    lateinit var configuration: PaymentSheet.CustomerConfiguration

    actual suspend fun getCustomers(
        context: Any,
        activity: Any,
        clientSecret: String,
        publishableKey: String
    ){
        PaymentConfiguration.init(context = context as Context, publishableKey = publishableKey)

        paymentSheet = PaymentSheet(activity as ComponentActivity) { paymentSheet ->
            val result = onPaymentSheetResult(paymentSheet)
            Toast.makeText(context, result, Toast.LENGTH_LONG).show()
        }

        presentPaymentSheet(
            paymentSheet = paymentSheet,
            clientSecret
        )
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

}