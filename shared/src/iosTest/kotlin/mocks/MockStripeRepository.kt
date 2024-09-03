package mocks

import repository.StripeRepository

open class MockStripeRepository : StripeRepository {

     var initialiseCalledWith: Map<String, Any?>? = null  // Variable to hold the parameters used in initialise()

    override fun initialise(params: Map<String, Any?>) {
        initialiseCalledWith = params
    }

    override fun createPaymentMethod(
        params: Map<String, Any?>,
        options: Map<String, Any?>,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            // Extract payment method type and data from params
            val paymentMethodData = params["paymentMethodData"] as? Map<*, *>
            val token = paymentMethodData?.get("token") as? String
            val paymentId = paymentMethodData?.get("paymentMethodId") as? String

            // Validate that either 'token' or 'paymentId' is provided
            if (token.isNullOrBlank() && paymentId.isNullOrBlank()) {
                throw IllegalArgumentException("Either 'token' or 'paymentMethodId' must be provided.")
            }

            // Simulate success scenario
            val result = mapOf(
                "status" to "success",
                "paymentMethodId" to (paymentId ?: "generated_payment_method_id")
            )

            // Trigger the success callback
            onSuccess(result)
        } catch (e: Exception) {
            // Trigger the error callback in case of validation failure or other exceptions
            onError(e)
        }
    }

}
