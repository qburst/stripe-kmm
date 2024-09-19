import repositories.ApiResult
import repositories.PaymentRepository

class CreatePaymentApi(private val createPaymentRepository: PaymentRepository) {

    suspend fun createPaymentMethod(params: CreateParams): ApiResult {
        val result = createPaymentRepository.createPaymentMethod(params = params)
        return result
    }

}