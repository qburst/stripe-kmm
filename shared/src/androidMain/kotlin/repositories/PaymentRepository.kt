package repositories

import CreateParams
import Mockable
import model.ApiResult

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
}