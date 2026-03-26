package repositories

import CreateParams
import model.PaymentError

object CreatePaymentValidation {
    /**
     * Validates create payment parameters and returns a PaymentError if validation fails,
     * or null if validation succeeds.
     */
    fun validateCreatePaymentParams(params: CreateParams): PaymentError? {
        return when(params) {
            is CreateParams.CardParamsWithToken -> {
                if (params.paymentMethodData?.token.isNullOrEmpty()) {
                    PaymentError.TokenMissing
                } else null
            }
            is CreateParams.CardParamsWithPaymentId -> {
                when {
                    params.paymentMethodData.paymentMethodId.isEmpty() -> PaymentError.PaymentMethodIdMissing
                    params.paymentMethodData.billingDetails == null -> PaymentError.BillingDetailsMissing
                    else -> null
                }
            }
            is CreateParams.AuBecsDebitParams -> {
                when {
                    params.paymentMethodData?.formDetails?.bsbNumber.isNullOrEmpty() -> PaymentError.BSBNumberMissing
                    params.paymentMethodData?.formDetails?.accountNumber.isNullOrEmpty() -> PaymentError.AccountNumberMissing
                    else -> null
                }
            }
            is CreateParams.BacsDebitParams -> {
                when {
                    params.paymentMethodData?.bacsDebit?.accountNumber.isNullOrEmpty() -> PaymentError.AccountNumberMissing
                    params.paymentMethodData?.bacsDebit?.sortCode.isNullOrEmpty() -> PaymentError.SortCodeMissing
                    else -> null
                }
            }
            is CreateParams.SofortParams -> {
                if (params.paymentMethodData?.country.isNullOrEmpty()) {
                    PaymentError.CountryMissing
                } else null
            }
            is CreateParams.NetBankingParams -> {
                if (params.paymentMethodData?.bank.isNullOrEmpty()) {
                    PaymentError.BankNameMissing
                } else null
            }
            is CreateParams.KlarnaParams -> {
                when {
                    params.paymentMethodData?.billingDetails == null -> PaymentError.BillingDetailsMissing
                    params.paymentMethodData?.billingDetails?.email.isNullOrEmpty() -> PaymentError.EmailMissing
                    params.paymentMethodData?.billingDetails?.address == null -> PaymentError.GenericError("Address is required for Klarna")
                    else -> null
                }
            }
            is CreateParams.USBankAccountParams -> {
                when {
                    params.paymentMethodData?.billingDetails == null -> PaymentError.BillingDetailsMissing
                    params.paymentMethodData?.billingDetails?.name.isNullOrEmpty() -> PaymentError.NameMissing
                    params.paymentMethodData?.accountNumber.isNullOrEmpty() -> PaymentError.AccountNumberMissing
                    params.paymentMethodData?.routingNumber.isNullOrEmpty() -> PaymentError.RoutingNumberMissing
                    else -> null
                }
            }
            is CreateParams.GooglePayParams -> {
                if (params.jsonObject == null || params.jsonObject.toString().isEmpty()) {
                    PaymentError.GooglePayJsonMissing
                } else null
            }
            is CreateParams.IdealParams -> {
                if (params.paymentMethodData?.bankName.isNullOrEmpty()) {
                    PaymentError.BankNameMissing
                } else null
            }
            is CreateParams.UpiParams -> {
                if (params.paymentMethodData?.vpa.isNullOrEmpty()) {
                    PaymentError.VPAMissing
                } else null
            }
            is CreateParams.FPXParams -> {
                if (params.paymentMethodData?.bankName.isNullOrEmpty()) {
                    PaymentError.BankNameMissing
                } else null
            }
            is CreateParams.PayPalParams -> {
                if (params.paymentMethodData?.billingDetails == null) {
                    PaymentError.BillingDetailsMissing
                } else null
            }
            is CreateParams.SepaDebitParams -> {
                when {
                    params.paymentMethodData?.billingDetails?.name.isNullOrEmpty() -> PaymentError.NameMissing
                    params.paymentMethodData?.billingDetails?.email.isNullOrEmpty() -> PaymentError.EmailMissing
                    params.paymentMethodData?.iban.isNullOrEmpty() -> PaymentError.IBANMissing
                    else -> null
                }
            }
            is CreateParams.BancontactParams -> {
                if (params.paymentMethodData.billingDetails?.name.isNullOrEmpty()) {
                    PaymentError.NameMissing
                } else null
            }
            is CreateParams.EpsDebitParams -> {
                if (params.paymentMethodData?.billingDetails?.name.isNullOrEmpty()) {
                    PaymentError.NameMissing
                } else null
            }
            is CreateParams.OxxoParams -> {
                when {
                    params.paymentMethodData?.billingDetails?.name.isNullOrEmpty() -> PaymentError.NameMissing
                    params.paymentMethodData?.billingDetails?.email.isNullOrEmpty() -> PaymentError.EmailMissing
                    else -> null
                }
            }
            is CreateParams.AlipayParams -> {
                when {
                    params.paymentMethodData?.billingDetails?.name.isNullOrEmpty() -> PaymentError.NameMissing
                    params.paymentMethodData?.billingDetails?.email.isNullOrEmpty() -> PaymentError.EmailMissing
                    else -> null
                }
            }
            is CreateParams.AfterpayClearpayParams -> {
                when {
                    params.paymentMethodData?.billingDetails?.name.isNullOrEmpty() -> PaymentError.NameMissing
                    params.paymentMethodData?.billingDetails?.email.isNullOrEmpty() -> PaymentError.EmailMissing
                    else -> null
                }
            }
            is CreateParams.BlikParams -> {
                if (params.paymentMethodData?.billingDetails?.name.isNullOrEmpty()) {
                    PaymentError.NameMissing
                } else null
            }
            is CreateParams.WeChatPayParams -> {
                if (params.paymentMethodData?.billingDetails?.name.isNullOrEmpty()) {
                    PaymentError.NameMissing
                } else null
            }
            is CreateParams.MultiBancoParams -> {
                if (params.paymentMethodData?.billingDetails?.email.isNullOrEmpty()) {
                    PaymentError.EmailMissing
                } else null
            }
            is CreateParams.AffirmParams -> {
                when {
                    params.paymentMethodData?.billingDetails?.name.isNullOrEmpty() -> PaymentError.NameMissing
                    params.paymentMethodData?.billingDetails?.email.isNullOrEmpty() -> PaymentError.EmailMissing
                    else -> null
                }
            }
            is CreateParams.CashAppParams -> {
                if (params.paymentMethodData?.billingDetails?.name.isNullOrEmpty()) {
                    PaymentError.NameMissing
                } else null
            }
            is CreateParams.SwishParams -> {
                if (params.paymentMethodData?.billingDetails?.name.isNullOrEmpty()) {
                    PaymentError.NameMissing
                } else null
            }
            else -> null
        }
    }
}
