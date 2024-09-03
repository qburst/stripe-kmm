import com.google.gson.Gson
import com.stripe.android.ApiResultCallback
import com.stripe.android.model.PaymentMethod
import model.CreatePaymentModel
import model.InitialiseParams


actual class ProvideStripeSdk actual constructor() {

    actual suspend fun initialise(initialiseParams: InitialiseParams) {
        SingletonStripeInitialization.StripeInstanse.initializeStripe(initialiseParams)
    }

    actual suspend fun createPaymentMethod(
        params: CreateParams,
        options: CreateOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {

        val callback = object : ApiResultCallback<PaymentMethod> {
            override fun onSuccess(result: PaymentMethod) {
                val gson = Gson()
                onSuccess(mapOf("result" to gson.toJson(result)))
            }

            override fun onError(e: Exception) {
                onError(e)
            }
        }

        SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
            paymentMethodCreateParams = CreatePaymentModel().createCardPaymentParamsWithToken(params as CreateParams.CardParamsWithToken),
            callback = callback
        )
    }
}
