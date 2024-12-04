import com.google.gson.Gson
import com.stripe.android.ApiResultCallback
import com.stripe.android.model.PaymentMethod
import model.ConfirmOptions
import model.ConfirmParams
import model.CreatePaymentModel
import model.InitialiseParams
import model.PresentOptions
import model.SetupParams
import repositories.PaymentRepository
import repositories.PaymentRepositoryImpl
import androidx.activity.ComponentActivity
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.content.Context
import android.widget.Toast

/**
 * This class is the actual implementation for android
 *
 * It is the starting point of Android native stripe SDK
 */
actual class ProvideStripeSdk actual constructor() {
    private val paymentRepository: PaymentRepository = PaymentRepositoryImpl()
    lateinit var paymentSheet: PaymentSheet

    /**
     * Initialize Android Stripe SDK
     * @param [InitialiseParams] with publishable key as a Not null parameter
     */
    actual suspend fun initialise(initialiseParams: InitialiseParams) {
        SingletonStripeInitialization.StripeInstanse.initializeStripe(initialiseParams)

        PaymentConfiguration.init(context = initialiseParams.androidContext as Context, publishableKey = initialiseParams.publishableKey)

        paymentSheet = PaymentSheet(initialiseParams.androidActivity as ComponentActivity) { paymentSheet ->
            val result = onPaymentSheetResult(paymentSheet)
            Toast.makeText(initialiseParams.androidContext, result, Toast.LENGTH_LONG).show()
        }
    }

    /**
     *It creates a payment parameter in Stripe SDK
     * @param [CreateParams] which contains different wallet types
     * @return [onSuccess] -> Success JSON String returns from the api.
     * [onError] -> Throwable error message thrown from validations or from the Api
     */
    actual suspend fun createPaymentMethod(
        params: CreateParams,
        options: CreateOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val result = CreatePaymentApi(paymentRepository).createPaymentMethod(params = params)
        if (result.success != null) {
            onSuccess(result.success)
        } else {
            result.error?.let { onError(it) }
        }
    }

    actual suspend fun confirmPayment(
        paymentIntentClientSecret: String,
        params: ConfirmParams,
        options: ConfirmOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) { }

    actual suspend fun handleNextAction(
        paymentIntentClientSecret: String,
        returnURL: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
    }

    actual suspend fun handleNextActionForSetup(
        setupIntentClientSecret: String,
        returnURL: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
    }

    actual suspend fun initPaymentSheet(
        params: SetupParams,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
    }

    private fun showPaymentSheet(
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

    actual suspend fun presentPaymentSheet(
        options: PresentOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val paymentSheet = paymentSheet
        CoroutineScope(Dispatchers.Main).launch {
            try {
                showPaymentSheet(
                    paymentSheet = paymentSheet,
                    paymentIntentClientSecret = "pi_1PmuqXKJ38Q1wp9dLgw8eijG_secret_5W09ySjx4NofVyUq9os07fOKj"
                )
                onSuccess(mapOf("status" to "Payment sheet presented"))
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}
