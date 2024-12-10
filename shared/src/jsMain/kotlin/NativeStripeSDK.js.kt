import model.ConfirmOptions
import model.ConfirmParams
import model.InitialiseParams
import striperest.Greetings

actual class ProvideStripeSdk actual constructor()  {
    actual suspend fun initialise(initialiseParams: InitialiseParams) {
//        TODO("Not yet implemented")
    }

    actual suspend fun createPaymentMethod(
        params: CreateParams,
        options: CreateOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
    //    val platform = choosePlatform() as JSMainPlatform
        val greetings = Greetings()
        val token = "sk_test_51Q7UTAF9GluljyyxRunJ924NByvCKrZX4VD9fKjmsZOUpEBFfCyzp1fyipRPVNlPEiHU5RltTQ7SBJADLIYgigOi00dsXJPfNM"
        greetings.fetchApiResponse("https://api.stripe.com/v1/customers",token)
            .then { result ->
                console.log("API Response: $result")
            }
    }
    actual suspend fun confirmPayment(
        paymentIntentClientSecret: String,
        params: ConfirmParams,
        options: ConfirmOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {}

    actual suspend fun handleNextAction(
        paymentIntentClientSecret: String,
        returnURL: String?,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
    }
   actual  suspend fun presentPaymentSheet(
        options: Any,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit){

   }
}
