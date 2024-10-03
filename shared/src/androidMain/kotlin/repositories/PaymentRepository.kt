package repositories

import CreateParams
import Mockable

@Mockable
interface PaymentRepository {
    suspend fun createPaymentMethod(
        params: CreateParams,
        idempotencyKey: String? = null,
        stripeAccountId: String? = null,
    ): ApiResult
}