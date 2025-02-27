import android.content.Context
import androidx.activity.ComponentActivity
import com.stripe.android.paymentsheet.PaymentSheet
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.ConfirmOptions
import model.ConfirmParams
import model.InitialiseParams
import model.PresentOptions
import model.SetupParams
import repositories.PaymentRepository
import repositories.PaymentRepositoryImpl

/**
 * This class is the actual implementation for Android.
 *
 * It serves as the starting point of the Android native Stripe SDK.
 */
actual class ProvideStripeSdk actual constructor() {

    private val paymentRepository: PaymentRepository = PaymentRepositoryImpl()

    /**
     * Initializes the Android Stripe SDK.
     *
     * @param initialiseParams An instance of [InitialiseParams] containing the publishable key (not
     * null).
     */
    actual suspend fun initialise(initialiseParams: InitialiseParams) {
//        SingletonStripeInitialization.StripeInstanse.initializeStripe(initialiseParams)
//        SingletonStripeInitialization.StripeInstanse.initialisePaymentSheet(initialiseParams)
        if (initialiseParams.androidContext!=null){
            SingletonStripeInitialization.StripeInstanse.saveContextActivity(initialiseParams.androidActivity as ComponentActivity,initialiseParams.androidContext as Context)
        }else{
            SingletonStripeInitialization.StripeInstanse.initializeStripe(initialiseParams)
            SingletonStripeInitialization.StripeInstanse.initialisePaymentSheet(initialiseParams)
        }
    }

    /**
     * Creates a payment method in the Stripe SDK.
     *
     * @param params An instance of [CreateParams] containing different wallet types.
     * @param options An instance of [CreateOptions] for additional configuration.
     * @param onSuccess A callback function invoked with a success response in the form of a map.
     * @param onError A callback function invoked with an error in case of failure.
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

    /**
     * Confirms a payment intent.
     *
     * @param paymentIntentClientSecret The client secret for the payment intent.
     * @param params An instance of [ConfirmParams] for payment confirmation.
     * @param options Additional configuration options via [ConfirmOptions].
     * @param onSuccess A callback function invoked with a success response in the form of a map.
     * @param onError A callback function invoked with an error in case of failure.
     */
    actual suspend fun confirmPayment(
            paymentIntentClientSecret: String,
            params: ConfirmParams,
            options: ConfirmOptions,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        // You can implement the confirm payment logic here
    }

    /**
     * Handles the next action for a payment intent.
     *
     * @param paymentIntentClientSecret The client secret for the payment intent.
     * @param returnURL The return URL to be used after the action is handled.
     * @param onSuccess A callback function invoked with a success response in the form of a map.
     * @param onError A callback function invoked with an error in case of failure.
     */
    actual suspend fun handleNextAction(
            paymentIntentClientSecret: String,
            returnURL: String?,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        // Implement your handle next action here
    }

    /**
     * Handles the next action for a setup intent.
     *
     * @param setupIntentClientSecret The client secret for the setup intent.
     * @param returnURL The return URL to be used after the action is handled.
     * @param onSuccess A callback function invoked with a success response in the form of a map.
     * @param onError A callback function invoked with an error in case of failure.
     */
    actual suspend fun handleNextActionForSetup(
            setupIntentClientSecret: String,
            returnURL: String?,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        // Implement the setup handle next action here
    }

    /**
     * Initializes the Payment Sheet with the provided setup parameters.
     *
     * @param params An instance of [SetupParams] containing the setup details.
     * @param onSuccess A callback function invoked with a success response in the form of a map.
     * @param onError A callback function invoked with an error in case of failure.
     */
    actual suspend fun initPaymentSheet(
            params: SetupParams,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        try {
            // Fetch the payment intent client secret using the provided function
            val paymentIntentClientSecret = params.paymentIntentClientSecret
            if (paymentIntentClientSecret != null) {
                SingletonStripeInitialization.StripeInstanse.clientSecret =
                        paymentIntentClientSecret
            }

            // Call the success callback once everything is set
            onSuccess(mapOf("Success" to "Success"))
        } catch (e: Exception) {
            onError(e)
        }
    }

    /**
     * Presents the Payment Sheet to the user.
     *
     * @param options An instance of [PresentOptions] for configuring the Payment Sheet.
     * @param onSuccess A callback function invoked with a success response in the form of a map.
     * @param onError A callback function invoked with an error in case of failure.
     */
    actual suspend fun presentPaymentSheet(
            options: PresentOptions,
            onSuccess: (Map<String, Any?>) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            // For Payment Sheet result
            SingletonStripeInitialization.StripeInstanse.setPaymentResultCallback(
                    object : InitializeStripe.PaymentResult {
                        override fun onSuccess(status: Map<String, Any?>) {
                            onSuccess(status)
                        }

                        override fun onFailure(throwable: Throwable) {
                            onError(throwable)
                        }
                    }
            )

            SingletonStripeInitialization.StripeInstanse.paymentSheet.let {
                presentPaymentSheet(
                        paymentSheet = it,
                        SingletonStripeInitialization.StripeInstanse.clientSecret
                )
            }
        }
    }

    /**
     * Helper function to present the Payment Sheet with the provided client secret.
     *
     * @param paymentSheet An instance of [PaymentSheet].
     * @param paymentIntentClientSecret The client secret for the payment intent.
     */
    private fun presentPaymentSheet(paymentSheet: PaymentSheet, paymentIntentClientSecret: String) {
        paymentSheet.presentWithPaymentIntent(
                paymentIntentClientSecret,
                PaymentSheet.Configuration(
                        merchantDisplayName = "Stripe Library",
                        allowsDelayedPaymentMethods = true
                )
        )
    }
}
