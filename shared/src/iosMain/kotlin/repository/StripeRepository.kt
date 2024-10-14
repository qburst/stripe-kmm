package repository
/**
 * `StripeRepository` interface defines the contract for interacting with the Stripe SDK.
 * It includes methods for initializing the SDK and creating payment methods. This interface
 * is used across platforms (Kotlin Multiplatform), but the actual implementation is provided
 * in Swift for iOS.
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
     * @throws Throwable If the initialization process fails for any reason, this method
     *         throws an exception.
     */
    @Throws(Throwable::class)
    fun initialise(params: Map<String, Any?>)

    /**
     * Creates a payment method using the provided parameters and options.
     *
     * This method allows the creation of various payment methods (e.g., card, PayPal,
     * Klarna, etc.). It accepts two maps: one for the payment method parameters and one
     * for additional options. The `onSuccess` callback is invoked if the payment method
     * is created successfully, and the `onError` callback is triggered if an error occurs
     * during the process.
     *
     * @param params A map containing the parameters for the payment method (e.g., card details).
     * @param options A map containing additional options for payment method creation (e.g.,
     *                future usage preferences).
     * @param onSuccess A callback function invoked with the result of the payment method creation.
     * @param onError A callback function invoked with the error if the payment method creation fails.
     * @throws Throwable If the payment method creation process fails for any reason, this method
     *         throws an exception.
     */
    @Throws(Throwable::class)
    fun createPaymentMethod(
        params: Map<String, Any?>,
        options: Map<String, Any?>,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )

    @Throws(Throwable::class)
    fun handleNextAction(
        paymentIntentClientSecret: String,
        returnURL: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )

    @Throws(Throwable::class)
    fun confirmPayment(
        paymentIntentClientSecret: String,
        params: Map<String, Any?>,
        options: Map<String, Any?>,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )
}
