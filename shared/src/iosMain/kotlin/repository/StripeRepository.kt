package repository

/**
 * `StripeRepository` interface defines the contract for interacting with the Stripe SDK. It
 * includes methods for initializing the SDK and creating payment methods. This interface is used
 * across platforms (Kotlin Multiplatform), but the actual implementation is provided in Swift for
 * iOS.
 */
interface StripeRepository {

    /**
     * Initializes the Stripe SDK with the provided parameters.
     *
     * This method takes a map of parameters required to initialize the Stripe SDK. The map
     * typically includes keys such as `publishableKey`, `stripeAccountId`, `merchantIdentifier`,
     * and `urlScheme`. These parameters allow the SDK to set up the necessary configurations.
     *
     * @param params A map containing the initialization parameters for Stripe.
     * @throws Throwable If the initialization process fails for any reason, this method throws an
     * exception.
     */
    @Throws(Throwable::class) fun initialise(params: Map<String, Any?>)

    /**
     * Creates a payment method using the provided parameters and options.
     *
     * This method allows the creation of various payment methods (e.g., card, PayPal, Klarna,
     * etc.). It accepts two maps: one for the payment method parameters and one for additional
     * options. The `onSuccess` callback is invoked if the payment method is created successfully,
     * and the `onError` callback is triggered if an error occurs during the process.
     *
     * @param params A map containing the parameters for the payment method (e.g., card details).
     * @param options A map containing additional options for payment method creation (e.g., future
     * usage preferences).
     * @param onSuccess A callback function invoked with the result of the payment method creation.
     * @param onError A callback function invoked with the error if the payment method creation
     * fails.
     * @throws Throwable If the payment method creation process fails for any reason, this method
     * throws an exception.
     */
    @Throws(Throwable::class)
    fun createPaymentMethod(
            params: Map<String, Any?>,
            options: Map<String, Any?>,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    )

    /**
     * Handles the next action for a payment using the provided payment intent client secret.
     *
     * This method is used to complete a payment process that requires additional actions. The
     * result is passed to the `onSuccess` callback, and any errors are passed to the `onError`
     * callback.
     *
     * @param paymentIntentClientSecret The client secret of the payment intent.
     * @param returnURL The URL to return to after completing the action (optional).
     * @param onSuccess A callback function invoked with the result of the action.
     * @param onError A callback function invoked with the error if the action fails.
     * @throws Throwable If the action handling process fails for any reason, this method throws an
     * exception.
     */
    @Throws(Throwable::class)
    fun handleNextAction(
            paymentIntentClientSecret: String,
            returnURL: String?,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    )

    /**
     * Handles the next action for a setup using the provided setup intent client secret.
     *
     * This method is used to complete the setup process that requires additional actions. The
     * result is passed to the `onSuccess` callback, and any errors are passed to the `onError`
     * callback.
     *
     * @param setupIntentClientSecret The client secret of the setup intent.
     * @param returnURL The URL to return to after completing the action (optional).
     * @param onSuccess A callback function invoked with the result of the action.
     * @param onError A callback function invoked with the error if the action fails.
     * @throws Throwable If the action handling process fails for any reason, this method throws an
     * exception.
     */
    @Throws(Throwable::class)
    fun handleNextActionForSetup(
            setupIntentClientSecret: String,
            returnURL: String?,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    )

    /**
     * Confirms a payment using the provided payment intent client secret and additional parameters.
     *
     * This method is used to confirm a payment after the payment method has been created. It passes
     * the result to the `onSuccess` callback or the error to the `onError` callback.
     *
     * @param paymentIntentClientSecret The client secret of the payment intent.
     * @param params A map containing parameters for the payment confirmation (e.g., payment method
     * details).
     * @param options A map containing additional options for payment confirmation.
     * @param onSuccess A callback function invoked with the result of the payment confirmation.
     * @param onError A callback function invoked with the error if the payment confirmation fails.
     * @throws Throwable If the payment confirmation process fails for any reason, this method
     * throws an exception.
     */
    @Throws(Throwable::class)
    fun confirmPayment(
            paymentIntentClientSecret: String,
            params: Map<String, Any?>,
            options: Map<String, Any?>,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    )

    /**
     * Initializes the payment sheet with the provided parameters.
     *
     * This method is used to initialize the payment sheet, which is used to present the payment UI.
     * It invokes the `onSuccess` callback on successful initialization and the `onError` callback
     * on failure.
     *
     * @param params A map containing the parameters for the payment sheet initialization.
     * @param onSuccess A callback function invoked with the result of the initialization.
     * @param onError A callback function invoked with the error if the initialization fails.
     * @throws Throwable If the initialization process fails for any reason, this method throws an
     * exception.
     */
    @Throws(Throwable::class)
    fun initPaymentSheet(
            params: Map<String, Any?>,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    )

    /**
     * Presents the payment sheet with the provided options.
     *
     * This method is used to present the payment sheet to the user, allowing them to complete the
     * payment. It invokes the `onSuccess` callback on successful presentation and the `onError`
     * callback on failure.
     *
     * @param options A map containing options for presenting the payment sheet.
     * @param onSuccess A callback function invoked with the result of presenting the payment sheet.
     * @param onError A callback function invoked with the error if the presentation fails.
     * @throws Throwable If the presentation process fails for any reason, this method throws an
     * exception.
     */
    @Throws(Throwable::class)
    fun presentPaymentSheet(
            options: Map<String, Any?>,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    )
}
