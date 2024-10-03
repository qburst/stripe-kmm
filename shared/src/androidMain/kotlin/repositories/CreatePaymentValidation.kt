package repositories

import CreateParams

object CreatePaymentValidation {
    fun validateCreatePaymentParams(params: CreateParams): String {
        var validationResult = "success"
        when(params) {
            is CreateParams.IdealParams -> {
                if (params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.OxxoParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            else -> {

            }
        }
        return validationResult
    }
}