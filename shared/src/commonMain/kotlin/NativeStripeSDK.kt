import model.ConfirmOptions
import model.ConfirmParams
import model.InitialiseParams
import model.PresentOptions
import model.SetupParams

/**
 * Expects an implementation for the Stripe SDK functionality on different platforms.
 */
expect class ProvideStripeSdk() {

    /**
     * Initializes the Stripe SDK with the provided parameters.
     *
     * @param initialiseParams An instance of [InitialiseParams] containing initialization data.
     */
    suspend fun initialise(initialiseParams: InitialiseParams)

    /**
     * Creates a payment method in the Stripe SDK.
     *
     * @param params An instance of [CreateParams] containing payment details.
     * @param options An instance of [CreateOptions] for additional configurations.
     * @param onSuccess A callback function invoked on success with a response map.
     * @param onError A callback function invoked on error with a throwable.
     */
    suspend fun createPaymentMethod(
        params: CreateParams,
        options: CreateOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )

    /**
     * Confirms a payment intent using the provided parameters.
     *
     * @param paymentIntentClientSecret The client secret for the payment intent.
     * @param params An instance of [ConfirmParams] containing confirmation data.
     * @param options Additional configuration options via [ConfirmOptions].
     * @param onSuccess A callback function invoked on success with a response map.
     * @param onError A callback function invoked on error with a throwable.
     */
    suspend fun confirmPayment(
        paymentIntentClientSecret: String,
        params: ConfirmParams,
        options: ConfirmOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )

    /**
     * Handles the next action for a payment intent.
     *
     * @param paymentIntentClientSecret The client secret for the payment intent.
     * @param returnURL An optional return URL to be used after action handling.
     * @param onSuccess A callback function invoked on success with a response map.
     * @param onError A callback function invoked on error with a throwable.
     */
    suspend fun handleNextAction(
        paymentIntentClientSecret: String,
        returnURL: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )

    /**
     * Handles the next action for a setup intent.
     *
     * @param setupIntentClientSecret The client secret for the setup intent.
     * @param returnURL An optional return URL to be used after action handling.
     * @param onSuccess A callback function invoked on success with a response map.
     * @param onError A callback function invoked on error with a throwable.
     */
    suspend fun handleNextActionForSetup(
        setupIntentClientSecret: String,
        returnURL: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )

    /**
     * Initializes the Payment Sheet with the provided setup parameters.
     *
     * @param params An instance of [SetupParams] containing setup details.
     * @param onSuccess A callback function invoked on success with a response map.
     * @param onError A callback function invoked on error with a throwable.
     */
    suspend fun initPaymentSheet(
        params: SetupParams,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )

    /**
     * Presents the Payment Sheet to the user.
     *
     * @param options An instance of [PresentOptions] for configuring the Payment Sheet.
     * @param onSuccess A callback function invoked on success with a response map.
     * @param onError A callback function invoked on error with a throwable.
     */
    suspend fun presentPaymentSheet(
        options: PresentOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )
}
