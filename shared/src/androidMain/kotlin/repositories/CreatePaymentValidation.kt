package repositories

import CreateParams

object CreatePaymentValidation {
    fun validateCreatePaymentParams(params: CreateParams): String {
        var validationResult = "success"
        when(params) {
            is CreateParams.CardParamsWithToken -> {
                if (params.paymentMethodData?.token == null) {
                    validationResult = "Token is mandatory"
                }
            }
            is CreateParams.CardParamsWithPaymentId -> {
                if (params.paymentMethodData.paymentMethodId == "") {
                    validationResult = "Payment method id is mandatory"
                }
                if (params.paymentMethodData.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.AuBecsDebitParams -> {
                if(params.paymentMethodData?.formDetails?.accountNumber == null) {
                    validationResult = "Account number is mandatory"
                }
                if(params.paymentMethodData?.formDetails?.bsbNumber == null) {
                    validationResult = "BSB number is mandatory"
                }
            }
            is CreateParams.BacsDebitParams -> {
                if(params.paymentMethodData?.bacsDebit?.accountNumber == null) {
                    validationResult = "Account number is mandatory"
                }
                if(params.paymentMethodData?.bacsDebit?.sortCode == null) {
                    validationResult = "Sort code is mandatory"
                }
            }
            is CreateParams.SofortParams -> {
                if(params.paymentMethodData?.country == null) {
                    validationResult = "Country is mandatory"
                }
            }
            is CreateParams.NetBankingParams -> {
                if(params.paymentMethodData?.bank == null) {
                    validationResult = "Bank is mandatory"
                }
            }
            is CreateParams.KlarnaParams -> {
                // These are not mandatory in stripe
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
                if(params.paymentMethodData?.billingDetails?.email == null) {
                    validationResult = "Email is mandatory"
                }
                if(params.paymentMethodData?.billingDetails?.address == null) {
                    validationResult = "Address is mandatory"
                }
            }
            is CreateParams.USBankAccountParams -> {
                // These are not mandatory in stripe
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
                if(params.paymentMethodData?.billingDetails?.name == null) {
                    validationResult = "Name is mandatory"
                }
                if(params.paymentMethodData?.accountNumber == null) {
                    validationResult = "Account number is mandatory"
                }
                if(params.paymentMethodData?.routingNumber == null) {
                    validationResult = "Routing number is mandatory"
                }
            }
            is CreateParams.GooglePayParams -> {
                if(params.jsonObject == null) {
                    validationResult = "Google pay payment data is mandatory"
                }
            }

            else -> {}
        }
        return validationResult
    }
}