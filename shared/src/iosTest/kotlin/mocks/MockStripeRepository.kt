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
            // Extract payment method type and data
            val paymentMethodType = params["paymentMethodType"] as? String
            val paymentMethodData = params["paymentMethodData"] as? Map<*, *>

            // Validate based on the payment method type
            when (paymentMethodType) {
                "Card" -> {
                    val token = paymentMethodData?.get("token") as? String
                    if (token == null) {
                        throw IllegalArgumentException("Token is required for Card payment method.")
                    }
                    // Simulate success scenario for CardParamsWithToken
                    val result = mapOf(
                        "status" to "success",
                        "paymentMethodId" to "generated_payment_method_id"
                    )
                    onSuccess(result)
                }
                "Ideal" -> {
                    val bank = paymentMethodData?.get("bankName") as? String
                    if (bank == null) {
                        throw IllegalArgumentException("Bank name is required for IDEAL payment method.")
                    }
                    // Simulate success scenario for IdealParams
                    val result = mapOf(
                        "status" to "success",
                        "paymentMethodId" to "generated_ideal_payment_method_id"
                    )
                    onSuccess(result)
                }
                "SepaDebit" -> {
                    val iban = paymentMethodData?.get("iban") as? String
                    if (iban == null) {
                        throw IllegalArgumentException("IBAN is required for SEPA Debit payment method.")
                    }
                    // Simulate success scenario for SepaDebit
                    val result = mapOf(
                        "status" to "success",
                        "paymentMethodId" to "generated_sepa_debit_payment_method_id"
                    )
                    onSuccess(result)
                }


                else -> {
                    throw IllegalArgumentException("Invalid payment method type: $paymentMethodType")
                }
            }
        } catch (e: Exception) {
            // Trigger the error callback in case of validation failure or other exceptions
            onError(e)
        }
    }

}
