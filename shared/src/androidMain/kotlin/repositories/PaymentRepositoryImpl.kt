package repositories

import CreateParams
import Mockable
import SingletonStripeInitialization
import com.google.gson.Gson
import com.stripe.android.ApiResultCallback
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.model.PaymentMethod
import com.stripe.android.model.PaymentMethodCreateParams
import com.stripe.android.model.PaymentMethodOptionsParams
import kotlinx.coroutines.suspendCancellableCoroutine
import model.ApiResult
import model.ConfirmOptions
import model.ConfirmParams
import model.ConfirmPaymentModel
import model.CreatePaymentModel
import kotlin.coroutines.resume

/**
 * This class is the implementation for the [PaymentRepository]
 * Logics for the Stripe bridging is provided here.
 */
@Mockable
class PaymentRepositoryImpl: PaymentRepository {

    override suspend fun createPaymentMethod(
        params: CreateParams,
        idempotencyKey: String?,
        stripeAccountId: String?,
    ): ApiResult = suspendCancellableCoroutine { continuation ->

        /**
         * This [CreatePaymentValidation.validateCreatePaymentParams] for the purpose of validating the input.
         * @return [String] Error message if caught with some validation error
         */
        val validationResult = CreatePaymentValidation.validateCreatePaymentParams(params)
        if(validationResult != "success") {
            continuation.resume(
                ApiResult(
                    success = null,
                    error = null
                )
            )
        }

        val apiReturn = object : ApiResultCallback<PaymentMethod> {
            override fun onSuccess(result: PaymentMethod) {
                val gson = Gson()
                continuation.resume(
                    ApiResult(
                        success = mapOf("result" to gson.toJson(result)),
                        error = null
                    )
                )
            }

            override fun onError(e: Exception) {
                continuation.resume(ApiResult(success = null, error = e))
            }
        }

        /**
         * This method will call the respective Stripe api based on the @param[CreateParams] with different wallet systems
         */
        when(params) {
            is CreateParams.CardParamsWithToken -> {
                if(params.paymentMethodData?.token == null) {
                    continuation.resume(ApiResult(success = null, error = Throwable("Error")))
                }
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createCardPaymentParamsWithToken(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.IdealParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithIdeal(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.FPXParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithFpx(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.SepaDebitParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithSepaDebit(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.AuBecsDebitParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithAuBecsDebit(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.BacsDebitParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithBacsDebit(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.SofortParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithSofort(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.UpiParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithUpi(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.NetBankingParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithNetBanking(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.USBankAccountParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithUsBankAccount(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.CashAppParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithCashAppPay(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.SwishParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithSwish(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.BancontactParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithBanContacts(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.EpsDebitParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithEps(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.OxxoParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithOxxo(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.AlipayParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithAlipay(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.PayPalParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithPaypal(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.AfterpayClearpayParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithClearPay(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.GooglePayParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithGooglePay(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.BlikParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithBlik(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.WeChatPayParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithWeChatPay(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.KlarnaParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithKlarna(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.AffirmParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithAffirm(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.AmazonPayParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithAmazonPay(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.MultiBancoParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithMultiBanco(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.AlmaParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithAlma(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.SunbitParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithSunbit(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.BillieParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithBillie(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.SatispayParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithSatisPay(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.RevolutPayParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithRevolutPay(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }
            is CreateParams.MobilePayParams -> {
                SingletonStripeInitialization.StripeInstanse.stripe.createPaymentMethod(
                    paymentMethodCreateParams = CreatePaymentModel().createPaymentWithMobilePay(params),
                    idempotencyKey = idempotencyKey,
                    stripeAccountId = stripeAccountId,
                    callback = apiReturn
                )
            }

            /**
             * @throws [Throwable] if any of the non specified parameter is passed.
             */
            else -> {
                Throwable("Entity type mismatch.")
                return@suspendCancellableCoroutine
            }
        }
    }

    override suspend fun confirmPayment(
        paymentIntentClientSecret: String,
        params: ConfirmParams,
        options: ConfirmOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ){
        try {
            val stripeInstance = SingletonStripeInitialization.StripeInstanse
            stripeInstance.setPaymentResultCallback(object : InitializeStripe.PaymentResult {
                override fun onSuccess(status: Map<String, Any?>) {
                    onSuccess(status)
                }

                override fun onFailure(throwable: Throwable) {
                    onError(throwable)
                }
            })

            val validationResult = ConfirmPaymentValidation.validateCreatePaymentParams(params)

            if (validationResult != "success") {
                onError(Throwable(validationResult))
            }
            else{
                val confirmPaymentIntentParams = when (params) {
                    is ConfirmParams.CardParamsWithToken -> {
                        val cardParams = ConfirmPaymentModel().createCardPaymentParamsWithToken(cardParams = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = cardParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.IdealParams -> {
                        val idealParams = ConfirmPaymentModel().createPaymentWithIdeal(idelParams = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = idealParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.UpiParams -> {
                        val upiParams = ConfirmPaymentModel().createPaymentWithUpi(upiParams = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = upiParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.FpxParams -> {
                        val fpxParams = ConfirmPaymentModel().createPaymentWithFpx(fpxParams = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = fpxParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.PayPalParams -> {
                        val payPalParams = ConfirmPaymentModel().createPaymentWithPaypal(paypalParams = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = payPalParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.SepaDebitParams -> {
                        val sepaParams = ConfirmPaymentModel().createPaymentWithSepaDebit(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = sepaParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.AuBecsDebitParams -> {
                        val auBecsParams = ConfirmPaymentModel().createPaymentWithAuBecsDebit(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = auBecsParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.BacsDebitParams -> {
                        val bacsParams = ConfirmPaymentModel().createPaymentWithBacsDebit(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = bacsParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.SofortParams -> {
                        val sofortParams = ConfirmPaymentModel().createPaymentWithSofort(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = sofortParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.NetBankingParams -> {
                        val netBankingParams = ConfirmPaymentModel().createPaymentWithNetBanking(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = netBankingParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.USBankAccountParams -> {
                        val usBankParams = ConfirmPaymentModel().createPaymentWithUsBankAccount(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = usBankParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.CashAppParams -> {
                        val cashAppParams = ConfirmPaymentModel().createPaymentWithCashAppPay(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = cashAppParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.SwishParams -> {
                        val swishParams = ConfirmPaymentModel().createPaymentWithSwish(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = swishParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.BancontactParams -> {
                        val bancontactParams = ConfirmPaymentModel().createPaymentWithBanContacts(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = bancontactParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.EpsDebitParams -> {
                        val epsParams = ConfirmPaymentModel().createPaymentWithEps(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = epsParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.OxxoParams -> {
                        val oxxoParams = ConfirmPaymentModel().createPaymentWithOxxo(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = oxxoParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.AlipayParams -> {
                        val alipayParams = ConfirmPaymentModel().createPaymentWithAlipay(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = alipayParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.AfterpayClearpayParams -> {
                        val afterpayParams = ConfirmPaymentModel().createPaymentWithAfterpayClearpay(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = afterpayParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.BlikParams -> {
                        val blikParams = ConfirmPaymentModel().createPaymentWithBlik(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = blikParams,
                            clientSecret = paymentIntentClientSecret,
                            paymentMethodOptions = PaymentMethodOptionsParams.Blik(
                                code = ConfirmPaymentModel().getBlikPaymentCode(
                                    params = params
                                )
                            )
                        )
                    }

                    is ConfirmParams.WeChatPayParams -> {
                        val weChatParams = ConfirmPaymentModel().createPaymentWithWeChatPay(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = weChatParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.KlarnaParams -> {
                        val klarnaParams = ConfirmPaymentModel().createPaymentWithKlarna(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = klarnaParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.AffirmParams -> {
                        val affirmParams = ConfirmPaymentModel().createPaymentWithAffirm(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = affirmParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.AmazonPayParams -> {
                        val amazonParams = ConfirmPaymentModel().createPaymentWithAmazonPay(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = amazonParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.MultiBancoParams -> {
                        val multibancoParams = ConfirmPaymentModel().createPaymentWithMultiBanco(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = multibancoParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.AlmaParams -> {
                        val almaParams = ConfirmPaymentModel().createPaymentWithAlma(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = almaParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.SunbitParams -> {
                        val sunbitParams = ConfirmPaymentModel().createPaymentWithSunbit(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = sunbitParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.BillieParams -> {
                        val billieParams = ConfirmPaymentModel().createPaymentWithBillie(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = billieParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.SatispayParams -> {
                        val satispayParams = ConfirmPaymentModel().createPaymentWithSatispay(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = satispayParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.RevolutPayParams -> {
                        val revolutParams = ConfirmPaymentModel().createPaymentWithRevolutPay(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = revolutParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.MobilePayParams -> {
                        val mobileParams = ConfirmPaymentModel().createPaymentWithMobilePay(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = mobileParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.GiropayParams -> {
                        val giropayParams = ConfirmPaymentModel().createPaymentWithGiropay(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = giropayParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }

                    is ConfirmParams.GooglePayParams -> {
                        val googlePayParams = ConfirmPaymentModel().createPaymentWithGooglePay(params = params)
                        ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                            paymentMethodCreateParams = googlePayParams,
                            clientSecret = paymentIntentClientSecret
                        )
                    }
                }

                stripeInstance.confirmPaymentLauncher.confirm(confirmPaymentIntentParams)
            }

        } catch (e: Exception) {
            onError(e)
        }
    }
}