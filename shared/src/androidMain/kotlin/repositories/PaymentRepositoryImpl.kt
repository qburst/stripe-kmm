package repositories

import CreateParams
import Mockable
import SingletonStripeInitialization
import com.google.gson.Gson
import com.stripe.android.ApiResultCallback
import com.stripe.android.model.PaymentMethod
import kotlinx.coroutines.suspendCancellableCoroutine
import model.CreatePaymentModel
import kotlin.coroutines.resume

@Mockable
class PaymentRepositoryImpl: PaymentRepository {

    override suspend fun createPaymentMethod(
        params: CreateParams,
        idempotencyKey: String?,
        stripeAccountId: String?,
    ): ApiResult = suspendCancellableCoroutine { continuation ->

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
            else -> {
                Throwable("Entity type mismatch.")
                return@suspendCancellableCoroutine
            }
        }



    }
}