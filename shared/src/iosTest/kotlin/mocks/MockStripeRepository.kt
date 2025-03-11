package mocks
import repository.StripeRepository


/**
 * `MockStripeRepository` is a mock implementation of the `StripeRepository` interface designed
 * for testing purposes. It provides simulated behavior for Stripe operations like initialization
 * and creating payment methods. This class can be used in unit tests to verify logic that
 * interacts with the `StripeRepository` without making actual API calls to Stripe.
 */
open class MockStripeRepository : StripeRepository {

    /**
     * Holds the parameters passed to the `initialise()` method. This allows tests to inspect
     * what values were passed when the method was called.
     */
    var initialiseCalledWith: Map<String, Any?>? = null

    /**
     * Simulates the `initialise()` method. It saves the provided parameters to
     * `initialiseCalledWith` so they can be inspected during testing.
     *
     * @param params A map of initialisation parameters, such as publishable key and other configurations.
     */
    override fun initialise(params: Map<String, Any?>) {
        initialiseCalledWith = params
    }

    /**
     * Simulates the creation of a payment method, validating the input parameters
     * based on the payment method type and calling either the `onSuccess` or `onError` callbacks.
     *
     * Supported Payment Methods:
     * - **Card**: Requires a "token" parameter.
     * - **Ideal**: Requires a "bankName" parameter.
     * - **SepaDebit**: Requires an "iban" parameter.
     *
     * @param params A map of parameters for the payment method, including type and data.
     * @param options A map of options for creating the payment method.
     * @param onSuccess A callback function invoked when the payment method creation succeeds, returning a result map.
     * @param onError A callback function invoked when an error occurs during payment method creation.
     */
    override fun createPaymentMethod(
        params: Map<String, Any?>,
        options: Map<String, Any?>,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Map<String, Any?>) -> Unit
    ) {
        try {
            // Extract the payment method type and data from the params map
            val paymentMethodType = params["paymentMethodType"] as? String
            val paymentMethodData = params["paymentMethodData"] as? Map<*, *>

            // Perform validation and simulate success based on the payment method type
            when (paymentMethodType) {
                "Card" -> {
                    val token = paymentMethodData?.get("token") as? String
                    if (token == null) {
                        throw IllegalArgumentException("Token is required for Card payment method.")
                    }
                    // Simulate success for Card payment method
                    val result = mapOf(
                        "status" to "success",
                        "paymentMethodId" to "generated_payment_method_id"
                    )
                    onSuccess(result)
                }
                "Ideal" -> {
                    val bank = paymentMethodData?.get("bankName") as? String
                    if (bank == null) {
                        throw IllegalArgumentException("Bank name is required for IDEAL payment method.")
                    }
                    // Simulate success for IDEAL payment method
                    val result = mapOf(
                        "status" to "success",
                        "paymentMethodId" to "generated_ideal_payment_method_id"
                    )
                    onSuccess(result)
                }
                "SepaDebit" -> {
                    val iban = paymentMethodData?.get("iban") as? String
                    if (iban == null) {
                        throw IllegalArgumentException("IBAN is required for SEPA Debit payment method.")
                    }
                    // Simulate success for SEPA Debit payment method
                    val result = mapOf(
                        "status" to "success",
                        "paymentMethodId" to "generated_sepa_debit_payment_method_id"
                    )
                    onSuccess(result)
                }
                else -> {
                    // Throw an exception for unsupported payment method types
                    throw IllegalArgumentException("Invalid payment method type: $paymentMethodType")
                }
            }
        } catch (e: Exception) {
            // Trigger the error callback in case of validation failure or other exceptions
            val errorResult = mapOf("error" to (e.message ?: "Unknown error"))
            onError(errorResult)
        }
    }

    override fun confirmPayment(
        paymentIntentClientSecret: String,
        params: Map<String, Any?>,
        options: Map<String, Any?>,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Map<String, Any?>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun initPaymentSheet(
        params: Map<String, Any?>,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Map<String, Any?>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun presentPaymentSheet(
        options: Map<String, Any?>,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Map<String, Any?>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    /**
     * Mock implementation of handleNextAction for testing.
     */
    override fun handleNextAction(
        paymentIntentClientSecret: String,
        returnURL: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Map<String, Any?>) -> Unit
    ) {
        try {
            if (paymentIntentClientSecret == "pi_client_secret_12345") {
                val result = mapOf("status" to "success", "paymentIntentId" to "payment_intent_id_12345")
                onSuccess(result)
            } else {
                throw IllegalArgumentException("Invalid payment intent client secret.")
            }
        } catch (e: Exception) {
            val errorResult = mapOf("error" to (e.message ?: "Unknown error"))
            onError(errorResult)
        }
    }

    /**
     * Mock implementation of handleNextActionForSetup for testing.
     */
    override fun handleNextActionForSetup(
        setupIntentClientSecret: String,
        returnURL: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Map<String, Any?>) -> Unit
    ) {
        try {
            if (setupIntentClientSecret == "si_client_secret_67890") {
                val result = mapOf("status" to "success", "setupIntentId" to "setup_intent_id_67890")
                onSuccess(result)
            } else {
                throw IllegalArgumentException("Invalid setup intent client secret.")
            }
        } catch (e: Exception) {
            val errorResult = mapOf("error" to (e.message ?: "Unknown error"))
            onError(errorResult)
        }
    }
}
