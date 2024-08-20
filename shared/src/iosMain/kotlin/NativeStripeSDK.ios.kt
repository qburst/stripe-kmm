import di.CoroutineViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.Address
import model.BillingDetails
import model.FutureUsage
import model.InitialiseParams
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repository.StripeRepository


actual class ProvideStripeSdk actual constructor() : KoinComponent, CoroutineViewModel() {

    val stripe: StripeRepository by inject()
    actual suspend fun initialise(initialiseParams: InitialiseParams) {
            stripe.initialise(initialiseParams.toDictionary())
    }
    actual suspend fun createPaymentMethod(
        params: CreateParams,
        options: CreateOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            withContext(Dispatchers.Default) {
                // Convert params to a dictionary using the appropriate toDictionary() method
                val paramsDictionary = when (params) {
                    is CreateParams.CardParamsWithToken -> params.toDictionary()
                    is CreateParams.CardParamsWithPaymentId -> params.toDictionary()

                    // Handle other possible CreateParams subclasses
                    else -> throw IllegalArgumentException("Unsupported CreateParams type: ${params::class.simpleName}")
                }
                // Convert options to a dictionary using the appropriate toDictionary() method
                val optionsDictionary = options.toDictionary()

                // Call the Swift method with the converted dictionary
                stripe.createPaymentMethod(
                    params = paramsDictionary,
                    options = optionsDictionary,
                    onSuccess = { result ->
                        // Pass the result back to the UI through the onSuccess callback
                        onSuccess(result)
                    },
                    onError = { error ->
                        // Pass the error back to the UI through the onError callback
                        onError(error)
                    }
                )
            }
        } catch (e: Exception) {
            // Handle any other exceptions and pass them to the onError callback
            onError(e)
        }
    }

}
