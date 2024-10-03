package repositories

import CreateParams
import Mockable
import com.google.gson.Gson
import com.stripe.android.ApiResultCallback
import com.stripe.android.model.PaymentMethod
import kotlinx.coroutines.suspendCancellableCoroutine
import model.CreatePaymentModel
import kotlin.coroutines.resume

@Mockable
class PaymentRepositoryImpl: PaymentRepository {

    override suspend fun createPaymentMethod(
        params: CreateParams,
        idempotencyKey: String?,
        stripeAccountId: String?,
    ): ApiResult = suspendCancellableCoroutine { continuation ->

        val apiReturn = object : ApiResultCallback<PaymentMethod> {
            override fun onSuccess(result: PaymentMethod) {
                val gson = Gson()
                continuation.resume(
                    ApiResult(
                        success = mapOf("result" to gson.toJson(result)),
                        error = null
                    )
                )
            }

            override fun onError(e: Exception) {
                continuation.resume(ApiResult(success = null, error = e))
            }
        }

        SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
            paymentMethodCreateParams = CreatePaymentModel().createCardPaymentParamsWithToken(params as CreateParams.CardParamsWithToken),
            idempotencyKey = idempotencyKey,
            stripeAccountId = stripeAccountId,
            callback = apiReturn
        )

    }
}