package repositories

import model.ConfirmParams

object ConfirmPaymentValidation {
    fun validateCreatePaymentParams(params: ConfirmParams): String {
        var validationResult = "success"
        when (params) {

            is ConfirmParams.SepaDebitParams -> {
                if (params.paymentMethodData?.billingDetails?.name.isNullOrEmpty()) {
                    validationResult = "Name is mandatory"
                }
                if (params.paymentMethodData?.billingDetails?.email.isNullOrEmpty()) {
                    validationResult = "Email is mandatory"
                }
                if (params.paymentMethodData?.iban.isNullOrEmpty()) {
                    validationResult = "IBAN is mandatory"
                }
            }

            else -> {}
        }
        return validationResult
    }
}
