import model.ApiResult
import repositories.PaymentRepository

/**
 * This Api class is to connect with Repository class form actual android implementation
 * @constructor gets [PaymentRepository] implementation class to get data from the stripe api
 */
class CreatePaymentApi(private val createPaymentRepository: PaymentRepository) {

    /**
     * This function is to connect with the right repository api from the actual class.
     * @param [CreateParams] it takes the different types of wallet system to connect with the stripe api
     * @return [ApiResult] -> which contains Success and Error parameters for success case error will be null and vise verse
     */
    suspend fun createPaymentMethod(params: CreateParams): ApiResult {
        val result = createPaymentRepository.createPaymentMethod(params = params)
        return result
    }

}