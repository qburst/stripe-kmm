import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.PaymentMethod

class StripeFunctionalityImpl : StripeFunctionality {
    override  fun createPaymentMethod(
    ): Any {
       return "Hello from ios"
    }

}

actual fun getStripeFunctionality(): StripeFunctionality = StripeFunctionalityImpl()