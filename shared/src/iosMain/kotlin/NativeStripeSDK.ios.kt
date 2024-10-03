import di.CoroutineViewModel
import di.Stripe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.Address
import model.BillingDetails
import model.FutureUsage
import model.InitialiseParams
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repository.StripeRepository


actual open class ProvideStripeSdk actual constructor() : CoroutineViewModel() {

    actual suspend fun initialise(initialiseParams: InitialiseParams) {
        // Validate that publishableKey is not empty
        if (initialiseParams.publishableKey.isBlank()) {
            throw IllegalArgumentException("Publishable key cannot be empty.")
        }

        // Validate that if provided, stripeAccountId is not empty
        if (initialiseParams.stripeAccountId != null && initialiseParams.stripeAccountId.isBlank()) {
            throw IllegalArgumentException("Stripe account ID, if provided, cannot be empty.")
        }

        // Validate that if provided, merchantIdentifier is not empty
        if (initialiseParams.merchantIdentifier != null && initialiseParams.merchantIdentifier.isBlank()) {
            throw IllegalArgumentException("Merchant identifier, if provided, cannot be empty.")
        }

        // Validate that if provided, urlScheme is not empty
        if (initialiseParams.urlScheme != null && initialiseParams.urlScheme.isBlank()) {
            throw IllegalArgumentException("URL scheme, if provided, cannot be empty.")
        }

        // Proceed to initialise with validated parameters

        Stripe.provider.initialise(initialiseParams.toDictionary())
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
                    is CreateParams.IdealParams -> params.toDictionary()
                    is CreateParams.OxxoParams -> params.toDictionary()
                    is CreateParams.P24Params -> params.toDictionary()
                    is CreateParams.AlipayParams -> params.toDictionary()
                    is CreateParams.GiropayParams -> params.toDictionary()
                    is CreateParams.SepaDebitParams -> params.toDictionary()
                    is CreateParams.EpsDebitParams -> params.toDictionary()
                    is CreateParams.AuBecsDebitParams -> params.toDictionary()
                    is CreateParams.SofortParams -> params.toDictionary()
                    is CreateParams.GrabPayParams -> params.toDictionary()
                    is CreateParams.FPXParams -> params.toDictionary()
                    is CreateParams.AfterpayClearpayParams -> params.toDictionary()
                    is CreateParams.KlarnaParams -> params.toDictionary()
                    is CreateParams.BancontactParams -> params.toDictionary()
                    is CreateParams.USBankAccountParams -> params.toDictionary()
                    is CreateParams.PayPalParams -> params.toDictionary()
                    is CreateParams.AffirmParams -> params.toDictionary()
                    is CreateParams.CashAppParams -> params.toDictionary()
                    // Handle other possible CreateParams subclasses
                    else -> throw IllegalArgumentException("Unsupported CreateParams type: ${params::class.simpleName}")
                }
                // Convert options to a dictionary using the appropriate toDictionary() method
                val optionsDictionary = options.toDictionary()

                // Call the Swift method with the converted dictionary
                Stripe.provider.createPaymentMethod(
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
