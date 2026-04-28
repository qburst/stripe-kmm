package repositories

import CreateParams
import Mockable
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.model.PaymentMethodCreateParams
import model.ApiResult
import model.ConfirmOptions
import model.ConfirmParams

/**
 * This class act as the interface to connect with the repository from the api class
 *
 * It will be mocked while writing the testcases using [Mockable] library
 */
@Mockable
interface PaymentRepository {

    /**
     * This function calls the actual [Stripe] sdk and get the result out of it
     * @param [CreateParams] -> It takes different type os wallet parameters as the input.
     * @return [ApiResult] -> This will have Success and Error response on success response error will be null.
     */
    suspend fun createPaymentMethod(
        params: CreateParams,
        idempotencyKey: String? = null,
        stripeAccountId: String? = null,
    ): ApiResult

    suspend fun confirmPayment(
        paymentIntentClientSecret: String,
        params: ConfirmParams,
        options: ConfirmOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )

    suspend fun handleNextAction(
        paymentIntentClientSecret: String,
        returnUrl: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )

    suspend fun handleNextActionForSetup(
        setupIntentClientSecret: String,
        returnUrl: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )
}