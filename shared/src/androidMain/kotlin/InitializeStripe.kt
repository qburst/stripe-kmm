import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe
import com.stripe.android.payments.paymentlauncher.PaymentLauncher
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import model.InitialiseParams

/**
 * Class responsible for initializing Stripe SDK and handling payment-related operations.
 */
class InitializeStripe {

    // Private variables for storing activity, context, and publishable key.
    private var _activity: ComponentActivity? = null
    private var _context: Context? = null
    private var _publishableKey: String? = null

    /**
     * Instance of [Stripe] used for interacting with Stripe SDK.
     */
    lateinit var stripe: Stripe

    /**
     * Instance of [PaymentSheet] used for managing Stripe payment sheets.
     */
    lateinit var paymentSheet: PaymentSheet

    var clientSecret = ""

    /**
     * Callback interface for payment result handling.
     */
    private var paymentResultCallback: PaymentResult? = null

    /**
     * Interface definition for payment result callbacks.
     */
    interface PaymentResult {
        /**
         * Called when payment succeeds.
         * @param status A map containing payment status details.
         */
        fun onSuccess(status: Map<String, Any?>)

        /**
         * Called when payment fails.
         * @param throwable An exception containing the error details.
         */
        fun onFailure(throwable: Throwable)
    }

    /**
     * Initializes the Stripe SDK with the provided parameters. Ensures initialization happens only once.
     *
     * @param initialObject An instance of [InitialiseParams] containing initialization details.
     */
    fun initializeStripe(initialObject: InitialiseParams) {
       // if (_activity == null && _context == null && _publishableKey == null) {
         //   _activity = initialObject.androidActivity as ComponentActivity
           // _context = initialObject.androidContext as Context
            _publishableKey = initialObject.publishableKey

            stripe = Stripe(
                context = _context!!,
                publishableKey = _publishableKey!!
            )

    }
    fun saveContextActivity(activity: ComponentActivity,context: Context){
        _activity=activity
        _context=context
    }

    /**
     * Initializes the Stripe Payment Sheet with the provided parameters.
     *
     * @param initialiseParams An instance of [InitialiseParams] containing initialization details.
     */
    fun initialisePaymentSheet(initialiseParams: InitialiseParams) {
        if ((initialiseParams.androidContext != null) && (initialiseParams.androidActivity != null)) {
//            PaymentConfiguration.init(
//                context = initialiseParams.androidContext as Context,
//                publishableKey = initialiseParams.publishableKey
//            )

            paymentSheet =
                PaymentSheet(initialiseParams.androidActivity as ComponentActivity) { paymentSheet ->
                    onPaymentSheetResult(paymentSheet)
                }
        }
    }

    /**
     * Handles the result of a payment sheet operation.
     *
     * @param paymentSheetResult The result of the payment sheet operation.
     */
    private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        when (paymentSheetResult) {
            is PaymentSheetResult.Canceled -> {
                paymentResultCallback?.onFailure(Throwable(message = "Cancelled"))
                paymentResultCallback = null
            }

            is PaymentSheetResult.Failed -> {
                paymentResultCallback?.onFailure(Throwable(message = "Failed"))
                paymentResultCallback = null
            }

            is PaymentSheetResult.Completed -> {
                // TODO: Handle paymentSheetResult
                paymentResultCallback?.onSuccess(emptyMap())
                paymentResultCallback = null
            }
        }
    }

    /**
     * Sets the callback for handling payment results.
     *
     * @param callback An implementation of [PaymentResult] interface.
     */
    fun setPaymentResultCallback(callback: InitializeStripe.PaymentResult) {
        paymentResultCallback = callback
    }
}

/**
 * Singleton object for managing a single instance of [InitializeStripe].
 */
object SingletonStripeInitialization {
    /**
     * Single instance of [InitializeStripe].
     */
    val StripeInstanse = InitializeStripe()
}
