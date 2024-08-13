import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.stripe.android.paymentsheet.PaymentOptionCallback
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResultCallback
import com.stripe.android.paymentsheet.PaymentSheet.FlowController
import com.stripe.android.paymentsheet.PaymentSheetResult

actual class StripAuthentication actual constructor() {
    private lateinit var flowController: FlowController

    actual suspend fun getCustomers(
        context: Any,
        activity: Any,
        clientSecret: String,
        publishableKey: String
    ) {

        fun showToast(message: String) {
            Toast.makeText(context as Context, message, Toast.LENGTH_LONG).show()
        }

        val paymentOptionCallback = PaymentOptionCallback { paymentOption ->
            showToast("Selected Payment Option: ${paymentOption?.toString() ?: "None"}")
            flowController.confirm()
        }

        val paymentResultCallback = PaymentSheetResultCallback { paymentSheetResult ->
            when (paymentSheetResult) {
                is PaymentSheetResult.Canceled -> {
                    showToast("Payment Cancelled")
                }
                is PaymentSheetResult.Failed -> {
                    showToast("Payment Failed")
                }
                is PaymentSheetResult.Completed -> {
                    showToast("Payment Completed")
                }
            }
        }

        val configComplete = FlowController.ConfigCallback {
            isReady, error ->
            showToast("isReady: $isReady, error: ${error?.message}")
            handlePaymentSheet(isReady, error, flowController)
        }

        flowController = FlowController.create(
            activity as ComponentActivity,
            paymentOptionCallback,
            paymentResultCallback
        )

        flowController.configureWithPaymentIntent(
            clientSecret,
            PaymentSheet.Configuration(merchantDisplayName = "Stripe Library"),
            configComplete
        )
    }

    private fun handlePaymentSheet(isReady: Boolean, error: Throwable?, flowController: FlowController) {
        if (isReady) {
            flowController.presentPaymentOptions()
        }
        else error?.printStackTrace()
    }
}
