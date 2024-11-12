import model.ConfirmOptions
import model.ConfirmParams
import model.InitialiseParams
import striperest.Greetings

actual class ProvideStripeSdk actual constructor()  {
    actual suspend fun initialise(initialiseParams: InitialiseParams) {
//        TODO("Not yet implemented")
    }

    actual suspend fun createPaymentMethod(
        params: CreateParams,
        options: CreateOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
            val greeting = Greetings()
          println( greeting.test())
    }
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
    ) {
    }
}
