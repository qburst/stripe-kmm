import com.google.gson.Gson
import com.stripe.android.ApiResultCallback
import com.stripe.android.model.PaymentMethod
import model.CreatePaymentModel
import model.InitialiseParams
import repositories.PaymentRepository
import repositories.PaymentRepositoryImpl


actual class ProvideStripeSdk actual constructor() {
    private val paymentRepository: PaymentRepository = PaymentRepositoryImpl()

    actual suspend fun initialise(initialiseParams: InitialiseParams) {
        SingletonStripeInitialization.StripeInstanse.initializeStripe(initialiseParams)
    }

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
}
