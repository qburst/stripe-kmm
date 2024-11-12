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

/**
 * This class is the actual implementation for android
 *
 * It is the starting point of Android native stripe SDK
 */
actual class ProvideStripeSdk actual constructor() {
    private val paymentRepository: PaymentRepository = PaymentRepositoryImpl()

    /**
     * Initialize Android Stripe SDK
     * @param [InitialiseParams] with publishable key as a Not null parameter
     */
    actual suspend fun initialise(initialiseParams: InitialiseParams) {
        SingletonStripeInitialization.StripeInstanse.initializeStripe(initialiseParams)
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

    actual suspend fun presentPaymentSheet(
        options: PresentOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
    }
}
