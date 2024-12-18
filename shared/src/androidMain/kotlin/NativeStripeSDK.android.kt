import com.stripe.android.paymentsheet.PaymentSheet
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.ConfirmOptions
import model.ConfirmParams
import model.InitialiseParams
import model.PresentOptions
import model.SetupParams
import org.json.JSONObject
import repositories.PaymentRepository
import repositories.PaymentRepositoryImpl

/**
 * This class is the actual implementation for android
 *
 * It is the starting point of Android native stripe SDK
 */
actual class ProvideStripeSdk actual constructor() {
    private val paymentRepository: PaymentRepository = PaymentRepositoryImpl()

    /**
     * Initialize Android Stripe SDK
     * @param [InitialiseParams] with publishable key as a Not null parameter
     */
    actual suspend fun initialise(initialiseParams: InitialiseParams) {
        SingletonStripeInitialization.StripeInstanse.initializeStripe(initialiseParams)
        SingletonStripeInitialization.StripeInstanse.initialisePaymentSheet(initialiseParams)
    }

    /**
     *It creates a payment parameter in Stripe SDK
     * @param [CreateParams] which contains different wallet types
     * @return [onSuccess] -> Success JSON String returns from the api.
     * [onError] -> Throwable error message thrown from validations or from the Api
     */
    actual suspend fun createPaymentMethod(
        params: CreateParams,
        options: CreateOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val result = CreatePaymentApi(paymentRepository).createPaymentMethod(params = params)
        if (result.success != null) {
            onSuccess(result.success)
        } else {
            result.error?.let { onError(it) }
        }
    }

    actual suspend fun confirmPayment(
        paymentIntentClientSecret: String,
        params: ConfirmParams,
        options: ConfirmOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        // You can implement the confirm payment logic here
    }

    actual suspend fun handleNextAction(
        paymentIntentClientSecret: String,
        returnURL: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        // Implement your handle next action here
    }

    actual suspend fun handleNextActionForSetup(
        setupIntentClientSecret: String,
        returnURL: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        // Implement the setup handle next action here
    }

    actual suspend fun initPaymentSheet(
        params: SetupParams,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            // Fetch the payment intent client secret using the provided function
            val paymentIntentClientSecret = fetchPaymentIntentClientSecret()
            SingletonStripeInitialization.StripeInstanse.clientSecret = paymentIntentClientSecret

            // Call the success callback once everything is set
            onSuccess(mapOf("Success" to "Success"))
        } catch (e: Exception) {
            onError(e)
        }
    }

    actual suspend fun presentPaymentSheet(
        options: PresentOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            SingletonStripeInitialization.StripeInstanse.paymentSheet.let {
                presentPaymentSheet(
                    paymentSheet = it,
                    SingletonStripeInitialization.StripeInstanse.clientSecret
                )
            }
        }
    }

    private fun presentPaymentSheet(
        paymentSheet: PaymentSheet,
        paymentIntentClientSecret: String
    ) {
        paymentSheet.presentWithPaymentIntent(
            paymentIntentClientSecret,
            PaymentSheet.Configuration(
                merchantDisplayName = "Stripe Library",
                allowsDelayedPaymentMethods = true
            )
        )
    }

    /**
     * Fetches the PaymentIntent client secret from Stripe's API.
     */
    private suspend fun fetchPaymentIntentClientSecret(): String {
        val client = HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json() // Configure JSON serialization
            }
        }

        try {
            val response: HttpResponse = client.post("https://api.stripe.com/v1/payment_intents") {
                header(HttpHeaders.Authorization, "Bearer sk_test_hPRNV2gZ6gcIV99ndFejwEHT")
                contentType(ContentType.Application.FormUrlEncoded)
                setBody(
                    listOf(
                        "amount" to "4000", // Amount in the smallest currency unit (e.g., paise for INR)
                        "currency" to "inr",
                        "automatic_payment_methods[enabled]" to "true"
                    ).formUrlEncode()
                )
            }

            // Parse the response JSON to get the paymentIntentClientSecret
            val responseBody = response.bodyAsText()
            val jsonObject = JSONObject(responseBody)
            return jsonObject.getString("client_secret")
        } catch (e: Exception) {
            throw Exception("Error fetching Payment Intent: ${e.message}")
        } finally {
            client.close() // Close the client
        }
    }
}
