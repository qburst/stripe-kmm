import di.CoroutineViewModel
import di.Stripe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.Address
import model.BillingDetails
import model.ConfirmOptions
import model.ConfirmParams
import model.FutureUsage
import model.InitialiseParams
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repository.StripeRepository

/**
 * `ProvideStripeSdk` class is the actual implementation for platform-specific
 * Stripe SDK functionalities. It extends the `CoroutineViewModel` for coroutine
 * management and provides methods for initializing the Stripe SDK and creating
 * payment methods.
 */
actual open class ProvideStripeSdk actual constructor() : CoroutineViewModel() {

    /**
     * Initializes the Stripe SDK with the provided `InitialiseParams`.
     *
     * This function validates several fields of the `InitialiseParams` (such as `publishableKey`,
     * `stripeAccountId`, `merchantIdentifier`, and `urlScheme`) to ensure they are not empty or
     * null if provided. Once validated, the function proceeds to initialize the Stripe SDK by
     * passing a dictionary version of the `InitialiseParams` to the Stripe provider.
     *
     * @param initialiseParams The parameters required for initializing the Stripe SDK.
     * @throws IllegalArgumentException If any required field is empty or improperly formatted.
     */
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

    /**
     * Creates a payment method with the provided `CreateParams` and `CreateOptions`.
     *
     * This function handles the creation of various types of payment methods based on the
     * `CreateParams` subclass provided (such as CardParams, IdealParams, AlipayParams, etc.).
     * The parameters and options are converted to dictionary format and passed to the Stripe
     * provider for processing. If successful, the result is passed to the `onSuccess` callback;
     * otherwise, errors are passed to the `onError` callback.
     *
     * @param params The parameters for creating the payment method, supporting multiple types.
     * @param options Additional options for customizing the payment method creation process.
     * @param onSuccess Callback function to handle the successful creation of a payment method.
     * @param onError Callback function to handle errors that occur during the process.
     * @throws IllegalArgumentException If unsupported `CreateParams` are provided.
     */
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




    actual suspend fun confirmPayment(
        paymentIntentClientSecret: String,
        params: ConfirmParams,
        options: ConfirmOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            withContext(Dispatchers.Default) {
                val paramsDictionary = when (params) {
                    is ConfirmParams.CardParamsWithToken -> params.toDictionary()
                    is ConfirmParams.IdealParams -> params.toDictionary()

                    else -> throw IllegalArgumentException("Unsupported CreateParams type: ${params::class.simpleName}")
                }
                val optionsDictionary = options.toDictionary()
                Stripe.provider.confirmPayment(
                    paymentIntentClientSecret = paymentIntentClientSecret,
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

    actual suspend fun handleNextAction(
        paymentIntentClientSecret: String,
        returnURL: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            withContext(Dispatchers.Default) {
                Stripe.provider.handleNextAction(
                    paymentIntentClientSecret = paymentIntentClientSecret,
                    returnURL = returnURL,
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
