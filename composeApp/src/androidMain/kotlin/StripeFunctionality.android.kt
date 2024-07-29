import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.PaymentMethod

class StripeFunctionalityImpl : StripeFunctionality {
    override  fun createPaymentMethod(
    ): Any {
            return "Hello from android"
    }
}

actual fun getStripeFunctionality(): StripeFunctionality = StripeFunctionalityImpl()

