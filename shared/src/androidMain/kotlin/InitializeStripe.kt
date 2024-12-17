import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import model.InitialiseParams

class InitializeStripe {

    private var _activity: ComponentActivity? = null
    private var _context: Context? = null
    private var _publishableKey: String? = null
    lateinit var stripe: Stripe
    lateinit var paymentSheet: PaymentSheet
    var clientSecret = ""


    // Initialize Stripe only once
    fun initializeStripe(initialObject: InitialiseParams) {
        if (_activity == null && _context == null && _publishableKey == null) {
            _activity = initialObject.androidActivity as ComponentActivity
            _context = initialObject.androidContext as Context
            _publishableKey = initialObject.publishableKey

            stripe = Stripe(
                context = _context!!,
                publishableKey = _publishableKey!!
            )
        }
    }

    fun initialisePaymentSheet(initialiseParams: InitialiseParams) {
        if((initialiseParams.androidContext != null ) && (initialiseParams.androidActivity != null)) {
            PaymentConfiguration.init(
                context = initialiseParams.androidContext as Context,
                publishableKey = initialiseParams.publishableKey
            )

            paymentSheet =
                PaymentSheet(initialiseParams.androidActivity as ComponentActivity) { paymentSheet ->
                    val result = onPaymentSheetResult(paymentSheet)
                    Toast.makeText(
                        initialiseParams.androidContext as Context,
                        result,
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
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

object SingletonStripeInitialization {
    val StripeInstanse = InitializeStripe()
}