package repository

import model.InitialiseParams

interface StripeRepository {

    @Throws(Throwable::class)
    fun initialise(params: Map<String, Any?>)

    @Throws(Throwable::class)
    fun createPaymentMethod(
        params: Map<String, Any?>,
        options: Map<String, Any?>,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )

}
