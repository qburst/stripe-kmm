package repositories

import model.ConfirmParams
import model.PaymentError

object ConfirmPaymentValidation {
    /**
     * Validates confirm payment parameters and returns a PaymentError if validation fails,
     * or null if validation succeeds.
     */
    fun validateCreatePaymentParams(params: ConfirmParams): PaymentError? {
        return when (params) {
            is ConfirmParams.CardParamsWithToken -> {
                if (params.paymentMethodData?.token.isNullOrEmpty()) {
                    PaymentError.TokenMissing
                } else null
            }

            is ConfirmParams.IdealParams -> {
                if (params.paymentMethodData?.bankName.isNullOrEmpty()) {
                    PaymentError.BankNameMissing
                } else null
            }

            is ConfirmParams.UpiParams -> {
                if (params.paymentMethodData?.vpa.isNullOrEmpty()) {
                    PaymentError.VPAMissing
                } else null
            }

            is ConfirmParams.FpxParams -> {
                if (params.paymentMethodData?.bankName.isNullOrEmpty()) {
                    PaymentError.BankNameMissing
                } else null
            }

            is ConfirmParams.PayPalParams -> {
                if (params.paymentMethodData?.billingDetails == null) {
                    PaymentError.BillingDetailsMissing
                } else null
            }

            is ConfirmParams.SepaDebitParams -> {
                when {
                    params.paymentMethodData?.billingDetails?.name.isNullOrEmpty() -> PaymentError.NameMissing
                    params.paymentMethodData?.billingDetails?.email.isNullOrEmpty() -> PaymentError.EmailMissing
                    params.paymentMethodData?.iban.isNullOrEmpty() -> PaymentError.IBANMissing
                    else -> null
                }
            }

            is ConfirmParams.AuBecsDebitParams -> {
                when {
                    params.paymentMethodData?.bsbNumber.isNullOrEmpty() -> PaymentError.BSBNumberMissing
                    params.paymentMethodData?.accountNumber.isNullOrEmpty() -> PaymentError.AccountNumberMissing
                    else -> null
                }
            }

            is ConfirmParams.BacsDebitParams -> {
                when {
                    params.paymentMethodData?.sortCode.isNullOrEmpty() -> PaymentError.SortCodeMissing
                    params.paymentMethodData?.accountNumber.isNullOrEmpty() -> PaymentError.AccountNumberMissing
                    else -> null
                }
            }

            is ConfirmParams.SofortParams -> {
                if (params.paymentMethodData?.country.isNullOrEmpty()) {
                    PaymentError.CountryMissing
                } else null
            }

            is ConfirmParams.NetBankingParams -> {
                if (params.paymentMethodData?.bank.isNullOrEmpty()) {
                    PaymentError.BankNameMissing
                } else null
            }

            is ConfirmParams.USBankAccountParams -> {
                when {
                    params.paymentMethodData?.billingDetails == null -> PaymentError.BillingDetailsMissing
                    params.paymentMethodData?.linkAccountSessionId == null -> {
                        when {
                            params.paymentMethodData?.accountNumber.isNullOrEmpty() -> PaymentError.AccountNumberMissing
                            params.paymentMethodData?.routingNumber.isNullOrEmpty() -> PaymentError.RoutingNumberMissing
                            else -> null
                        }
                    }
                    else -> null
                }
            }

            is ConfirmParams.GooglePayParams -> {
                if (params.jsonObject == null) {
                    PaymentError.GooglePayJsonMissing
                } else null
            }

            is ConfirmParams.BancontactParams -> {
                if (params.paymentMethodData.billingDetails?.name.isNullOrEmpty()) {
                    PaymentError.NameMissing
                } else null
            }

            is ConfirmParams.EpsDebitParams -> {
                if (params.paymentMethodData?.billingDetails?.name.isNullOrEmpty()) {
                    PaymentError.NameMissing
                } else null
            }

            is ConfirmParams.OxxoParams -> {
                when {
                    params.paymentMethodData?.billingDetails?.name.isNullOrEmpty() -> PaymentError.NameMissing
                    params.paymentMethodData?.billingDetails?.email.isNullOrEmpty() -> PaymentError.EmailMissing
                    else -> null
                }
            }

            is ConfirmParams.AlipayParams -> {
                when {
                    params.paymentMethodData?.billingDetails?.name.isNullOrEmpty() -> PaymentError.NameMissing
                    params.paymentMethodData?.billingDetails?.email.isNullOrEmpty() -> PaymentError.EmailMissing
                    else -> null
                }
            }

            is ConfirmParams.AfterpayClearpayParams -> {
                when {
                    params.paymentMethodData?.billingDetails?.name.isNullOrEmpty() -> PaymentError.NameMissing
                    params.paymentMethodData?.billingDetails?.email.isNullOrEmpty() -> PaymentError.EmailMissing
                    else -> null
                }
            }

            is ConfirmParams.BlikParams -> {
                if (params.paymentMethodData?.blikCode.isNullOrEmpty()) {
                    PaymentError.BlikCodeMissing
                } else null
            }

            is ConfirmParams.WeChatPayParams -> {
                if (params.paymentMethodData?.appId.isNullOrEmpty()) {
                    PaymentError.AppIdMissing
                } else null
            }

            is ConfirmParams.MultiBancoParams -> {
                if (params.paymentMethodData?.billingDetails?.email.isNullOrEmpty()) {
                    PaymentError.EmailMissing
                } else null
            }

            is ConfirmParams.PaymentMethodIdParams -> {
                if (params.paymentMethodData.paymentMethodId.isEmpty()) {
                    PaymentError.PaymentMethodIdMissing
                } else null
            }

            else -> null
        }
    }
}
