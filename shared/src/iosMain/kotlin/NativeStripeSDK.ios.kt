import di.CoroutineViewModel
import di.Stripe
import helpers.mapToThrowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.ConfirmOptions
import model.ConfirmParams
import model.InitialiseParams
import model.PresentOptions
import model.SetupParams

/**
 * `ProvideStripeSdk` class is the actual implementation for platform-specific Stripe SDK
 * functionalities. It extends the `CoroutineViewModel` for coroutine management and provides
 * methods for initializing the Stripe SDK and creating payment methods.
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
        if (initialiseParams.stripeAccountId != null && initialiseParams.stripeAccountId.isBlank()
        ) {
            throw IllegalArgumentException("Stripe account ID, if provided, cannot be empty.")
        }

        // Validate that if provided, merchantIdentifier is not empty
        if (initialiseParams.merchantIdentifier != null &&
                        initialiseParams.merchantIdentifier.isBlank()
        ) {
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
     * `CreateParams` subclass provided (such as CardParams, IdealParams, AlipayParams, etc.). The
     * parameters and options are converted to dictionary format and passed to the Stripe provider
     * for processing. If successful, the result is passed to the `onSuccess` callback; otherwise,
     * errors are passed to the `onError` callback.
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
                val paramsDictionary =
                        when (params) {
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
                            else ->
                                    throw IllegalArgumentException(
                                            "Unsupported CreateParams type: ${params::class.simpleName}"
                                    )
                        }
                // Convert options to a dictionary using the appropriate toDictionary() method
                val optionsDictionary = options.toDictionary()

                // Call the Swift method with the converted dictionary
                Stripe.provider.createPaymentMethod(
                        params = paramsDictionary,
                        options = optionsDictionary,
                        onSuccess = { result -> onSuccess(result) },
                        onError = { errorMap ->
                            onError(mapToThrowable(errorMap)) // Convert Map to Throwable
                        }
                )
            }
        } catch (e: Exception) {
            // Handle any other exceptions and pass them to the onError callback
            onError(e)
        }
    }

    /**
     * Handles the next action required to confirm a payment using the provided
     * PaymentIntentClientSecret.
     *
     * This method interacts with Stripe's SDK to complete a payment that requires additional
     * actions, such as authentication. It passes the result or error back to the caller through
     * callbacks.
     *
     * @param paymentIntentClientSecret The client secret associated with the PaymentIntent.
     * @param returnURL The URL to redirect back to after the action, if applicable.
     * @param onSuccess Callback to be invoked upon successful handling of the next action. It
     * returns a map with relevant data, such as the `paymentIntentId`.
     * @param onError Callback to be invoked if an error occurs during handling the next action. The
     * error is passed as a `Throwable`.
     * @throws Exception Any unexpected exception is caught and passed to the `onError` callback.
     */
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
                        onSuccess = { result -> onSuccess(result) },
                    onError = { errorMap ->
                        onError(mapToThrowable(errorMap)) // Convert Map to Throwable
                    }
                )
            }
        } catch (e: Exception) {
            // Handle any other exceptions and pass them to the onError callback
            onError(e)
        }
    }

    /**
     * Handles the next action required to complete a setup using the provided
     * SetupIntentClientSecret.
     *
     * This method interacts with Stripe's SDK to complete a setup that requires additional actions,
     * such as authentication for future payment methods. It passes the result or error back to the
     * caller through callbacks.
     *
     * @param setupIntentClientSecret The client secret associated with the SetupIntent.
     * @param returnURL The URL to redirect back to after the action, if applicable.
     * @param onSuccess Callback to be invoked upon successful handling of the next action. It
     * returns a map with relevant data, such as the `setupIntentId`.
     * @param onError Callback to be invoked if an error occurs during handling the next action. The
     * error is passed as a `Throwable`.
     * @throws Exception Any unexpected exception is caught and passed to the `onError` callback.
     */
    actual suspend fun handleNextActionForSetup(
            setupIntentClientSecret: String,
            returnURL: String?,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        try {
            withContext(Dispatchers.Default) {
                Stripe.provider.handleNextActionForSetup(
                        setupIntentClientSecret = setupIntentClientSecret,
                        returnURL = returnURL,
                        onSuccess = { result -> onSuccess(result) },
                        onError = { errorMap ->
                        onError(mapToThrowable(errorMap)) // Convert Map to Throwable
                    }
                )
            }
        } catch (e: Exception) {
            // Handle any other exceptions and pass them to the onError callback
            onError(e)
        }
    }

    /**
     * Confirms the payment using the provided PaymentIntentClientSecret and ConfirmParams.
     *
     * This method is used to confirm a payment after it has been initiated. It interacts with
     * Stripe's SDK and provides success and error callbacks.
     *
     * @param paymentIntentClientSecret The client secret for the payment intent.
     * @param params The parameters for the payment confirmation.
     * @param options Additional options for the payment confirmation.
     * @param onSuccess Callback function invoked on successful payment confirmation.
     * @param onError Callback function invoked if the payment confirmation fails.
     * @throws IllegalArgumentException If unsupported `ConfirmParams` are provided.
     */
    actual suspend fun confirmPayment(
            paymentIntentClientSecret: String,
            params: ConfirmParams,
            options: ConfirmOptions,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        try {
            withContext(Dispatchers.Default) {
                val paramsDictionary =
                        when (params) {
                            is ConfirmParams.CardParamsWithToken -> params.toDictionary()
                            is ConfirmParams.IdealParams -> params.toDictionary()
                            else ->
                                    throw IllegalArgumentException(
                                            "Unsupported CreateParams type: ${params::class.simpleName}"
                                    )
                        }
                val optionsDictionary = options.toDictionary()
                Stripe.provider.confirmPayment(
                        paymentIntentClientSecret = paymentIntentClientSecret,
                        params = paramsDictionary,
                        options = optionsDictionary,
                        onSuccess = { result -> onSuccess(result) },
                        onError = { errorMap ->
                        onError(mapToThrowable(errorMap)) // Convert Map to Throwable
                        }
                )
            }
        } catch (e: Exception) {
            // Handle any other exceptions and pass them to the onError callback
            onError(e)
        }
    }

    /**
     * Initializes the payment sheet with the provided parameters.
     *
     * This method is used to initialize the payment sheet for the user to interact with, allowing
     * them to complete a payment. The result or error is passed back through callbacks.
     *
     * @param params The parameters for initializing the payment sheet.
     * @param onSuccess Callback function invoked when the payment sheet is successfully
     * initialized.
     * @param onError Callback function invoked if the payment sheet initialization fails.
     */
    actual suspend fun initPaymentSheet(
            params: SetupParams,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        try {
            withContext(Dispatchers.Default) {
                Stripe.provider.initPaymentSheet(
                    params = params.toDictionary(),
                    onSuccess = { result -> onSuccess(result) },
                    onError = { errorMap ->
                        onError(mapToThrowable(errorMap)) // Convert Map to Throwable
                    }
                )
            }
        } catch (e: Exception) {
            // Handle any other exceptions and pass them to the onError callback
            onError(e)
        }
    }

    /**
     * Presents the payment sheet to the user.
     *
     * This method is used to present the payment sheet to the user so they can complete the
     * payment. It provides the result or error to the caller via callbacks.
     *
     * @param options The options for presenting the payment sheet.
     * @param onSuccess Callback function invoked when the payment sheet is successfully presented.
     * @param onError Callback function invoked if there is an error presenting the payment sheet.
     */
    actual suspend fun presentPaymentSheet(
            options: PresentOptions,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        try {
            withContext(Dispatchers.Default) {
                Stripe.provider.presentPaymentSheet(
                    options = options.toDictionary(),
                    onSuccess = { result -> onSuccess(result) },
                    onError = { errorMap ->
                        onError(mapToThrowable(errorMap)) // Convert Map to Throwable
                    }
                )
            }
        } catch (e: Exception) {
            // Handle any other exceptions and pass them to the onError callback
            onError(e)
        }
    }
}
