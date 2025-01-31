import model.ConfirmOptions
import model.ConfirmParams
import model.InitialiseParams
import model.PresentOptions
import model.SetupParams

actual class ProvideStripeSdk {
    actual suspend fun initialise(initialiseParams: InitialiseParams) {
        TODO("Not yet implemented")
    }

    actual suspend fun createPaymentMethod(
            params: CreateParams,
            options: CreateOptions,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {}
    actual suspend fun confirmPayment(
            paymentIntentClientSecret: String,
            params: ConfirmParams,
            options: ConfirmOptions,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {}

    actual suspend fun handleNextAction(
            paymentIntentClientSecret: String,
            returnURL: String?,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {}

    actual suspend fun handleNextActionForSetup(
            setupIntentClientSecret: String,
            returnURL: String?,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {}

    actual suspend fun initPaymentSheet(
            params: SetupParams,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {}

    actual suspend fun presentPaymentSheet(
            options: PresentOptions,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {}
}
