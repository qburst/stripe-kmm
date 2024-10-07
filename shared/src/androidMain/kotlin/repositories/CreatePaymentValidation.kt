package repositories

import CreateParams

object CreatePaymentValidation {
    fun validateCreatePaymentParams(params: CreateParams): String {
        var validationResult = "success"
        when(params) {
            is CreateParams.CardParamsWithToken -> {
                // Nullable params
                if (params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
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
            is CreateParams.P24Params -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.AlipayParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.GiropayParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.SepaDebitParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.EpsDebitParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
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
                if(params.paymentMethodData?.formDetails?.email == null) {
                    validationResult = "Email is mandatory"
                }
                if(params.paymentMethodData?.formDetails?.name == null) {
                    validationResult = "Name is mandatory"
                }
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.BacsDebitParams -> {
                if(params.paymentMethodData?.bacsDebit?.accountNumber == null) {
                    validationResult = "Account number is mandatory"
                }
                if(params.paymentMethodData?.bacsDebit?.sortCode == null) {
                    validationResult = "Sort code is mandatory"
                }
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.SofortParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.UpiParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.NetBankingParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.GrabPayParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.FPXParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.AfterpayClearpayParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
                if(params.paymentMethodData?.shippingDetails == null) {
                    validationResult = "Shipping details is mandatory"
                }
            }
            is CreateParams.KlarnaParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
                if(params.paymentMethodData?.billingDetails?.email == null) {
                    validationResult = "Email is mandatory"
                }
                if(params.paymentMethodData?.billingDetails?.address == null) {
                    validationResult = "Address is mandatory"
                }
                if(params.paymentMethodData?.shippingDetails == null) {
                    validationResult = "Shipping details is mandatory"
                }
            }
            is CreateParams.BancontactParams -> {
//                if(params.paymentMethodData.billingDetails == null) {
//                    validationResult = "Billing details is mandatory"
//                }
            }
            is CreateParams.USBankAccountParams -> {
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
            is CreateParams.PayPalParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.BlikParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.WeChatPayParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.AffirmParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
                if(params.paymentMethodData?.shippingDetails == null) {
                    validationResult = "Shipping details is mandatory"
                }
            }
            is CreateParams.CashAppParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.MultiBancoParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.AlmaParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.SunbitParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.BillieParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.SatispayParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.RevolutPayParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.MobilePayParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.AmazonPayParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }
            is CreateParams.GooglePayParams -> {

            }
            is CreateParams.SwishParams -> {
                if(params.paymentMethodData?.billingDetails == null) {
                    validationResult = "Billing details is mandatory"
                }
            }

            else -> {}
        }
        return validationResult
    }
}