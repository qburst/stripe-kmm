package repositories

import model.ConfirmParams

object ConfirmPaymentValidation {
    fun validateCreatePaymentParams(params: ConfirmParams): String {
        var validationResult = "success"
        when (params) {
            is ConfirmParams.CardParamsWithToken -> {
                if (params.paymentMethodData?.token.isNullOrEmpty()) {
                    validationResult = "Token is mandatory"
                }
            }

            is ConfirmParams.IdealParams -> {
                if (params.paymentMethodData?.bankName.isNullOrEmpty()) {
                    validationResult = "Bank name is mandatory"
                }
            }

            is ConfirmParams.UpiParams -> {
                if (params.paymentMethodData?.vpa.isNullOrEmpty()) {
                    validationResult = "VPA is mandatory"
                }
            }

            is ConfirmParams.FpxParams -> {
                if (params.paymentMethodData?.bankName.isNullOrEmpty()) {
                    validationResult = "Bank name is mandatory"
                }
            }

            is ConfirmParams.PayPalParams -> {
                if (params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details are mandatory"
                }
            }

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

            is ConfirmParams.AuBecsDebitParams -> {
                if (params.paymentMethodData?.bsbNumber.isNullOrEmpty()) {
                    validationResult = "BSB Number is mandatory"
                }
                if (params.paymentMethodData?.accountNumber.isNullOrEmpty()) {
                    validationResult = "Account Number is mandatory"
                }
            }

            is ConfirmParams.BacsDebitParams -> {
                if (params.paymentMethodData?.sortCode.isNullOrEmpty()) {
                    validationResult = "Sort Code is mandatory"
                }
                if (params.paymentMethodData?.accountNumber.isNullOrEmpty()) {
                    validationResult = "Account Number is mandatory"
                }
            }

            is ConfirmParams.SofortParams -> {
                if (params.paymentMethodData?.country.isNullOrEmpty()) {
                    validationResult = "Country is mandatory"
                }
            }

            is ConfirmParams.NetBankingParams -> {
                if (params.paymentMethodData?.bank.isNullOrEmpty()) {
                    validationResult = "Bank name is mandatory"
                }
            }

            is ConfirmParams.USBankAccountParams -> {
                if (params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details are mandatory"
                }
                if (params.paymentMethodData?.linkAccountSessionId == null) {
                    if (params.paymentMethodData?.accountNumber.isNullOrEmpty()) {
                        validationResult = "Account Number is mandatory"
                    }
                    if (params.paymentMethodData?.routingNumber.isNullOrEmpty()) {
                        validationResult = "Routing Number is mandatory"
                    }
                }
            }

            is ConfirmParams.GooglePayParams -> {
                if (params.jsonObject == null) {
                    validationResult = "Google Pay JSON is mandatory"
                }
            }

            is ConfirmParams.BancontactParams -> {
                if (params.paymentMethodData.billingDetails?.name.isNullOrEmpty()) {
                    validationResult = "Name is mandatory"
                }
            }

            is ConfirmParams.EpsDebitParams -> {
                if (params.paymentMethodData?.billingDetails?.name.isNullOrEmpty()) {
                    validationResult = "Name is mandatory"
                }
            }

            is ConfirmParams.OxxoParams -> {
                if (params.paymentMethodData?.billingDetails?.name.isNullOrEmpty()) {
                    validationResult = "Name is mandatory"
                }

                if (params.paymentMethodData?.billingDetails?.email.isNullOrEmpty()) {
                    validationResult = "Email is mandatory"
                }
            }

            is ConfirmParams.AlipayParams -> {
                if (params.paymentMethodData?.billingDetails?.name.isNullOrEmpty()) {
                    validationResult = "Name is mandatory"
                }

                if (params.paymentMethodData?.billingDetails?.email.isNullOrEmpty()) {
                    validationResult = "Email is mandatory"
                }
            }

            is ConfirmParams.AfterpayClearpayParams -> {
                if (params.paymentMethodData?.billingDetails?.name.isNullOrEmpty()) {
                    validationResult = "Name is mandatory"
                }

                if (params.paymentMethodData?.billingDetails?.email.isNullOrEmpty()) {
                    validationResult = "Email is mandatory"
                }
            }

            is ConfirmParams.BlikParams -> {

                if (params.paymentMethodData?.blikCode.isNullOrEmpty()) {
                    validationResult = "Blik Code is mandatory"
                }
            }

            else -> {}
        }
        return validationResult
    }
}
