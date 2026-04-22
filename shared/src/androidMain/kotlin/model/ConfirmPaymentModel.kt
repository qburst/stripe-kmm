package model

import com.stripe.android.model.Address
import com.stripe.android.model.PaymentMethod
import com.stripe.android.model.PaymentMethodCreateParams
import org.json.JSONObject

class ConfirmPaymentModel {
    fun createCardPaymentParamsWithToken(cardParams: ConfirmParams.CardParamsWithToken): PaymentMethodCreateParams {
        val card = PaymentMethodCreateParams.Card.create(cardParams.paymentMethodData?.token ?: "")
        return PaymentMethodCreateParams.create(
            card = card,
            allowRedisplay = null,
            billingDetails = PaymentMethod.BillingDetails(
                name = cardParams.paymentMethodData?.billingDetails?.name,
                phone = cardParams.paymentMethodData?.billingDetails?.phone,
                email = cardParams.paymentMethodData?.billingDetails?.email,
                address = buildAddress(cardParams.paymentMethodData?.billingDetails)
            ),
            metadata = null
        )
    }

    fun createPaymentWithIdeal(idelParams: ConfirmParams.IdealParams): PaymentMethodCreateParams {
        val ideal = PaymentMethodCreateParams.Ideal(bank = idelParams.paymentMethodData?.bankName)
        return PaymentMethodCreateParams.create(
            ideal = ideal,
            billingDetails = PaymentMethod.BillingDetails(
                name = idelParams.paymentMethodData?.billingDetails?.name,
                phone = idelParams.paymentMethodData?.billingDetails?.phone,
                email = idelParams.paymentMethodData?.billingDetails?.email,
                address = buildAddress(idelParams.paymentMethodData?.billingDetails)
            ),
            metadata = idelParams.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithUpi (upiParams: ConfirmParams.UpiParams): PaymentMethodCreateParams {
        val upiDetails = PaymentMethodCreateParams.Upi(
            vpa = upiParams.paymentMethodData?.vpa ?: ""
        )

        return PaymentMethodCreateParams.create(
            upi = upiDetails,
            billingDetails = PaymentMethod.BillingDetails(
                name = upiParams.paymentMethodData?.billingDetails?.name,
                phone = upiParams.paymentMethodData?.billingDetails?.phone,
                email = upiParams.paymentMethodData?.billingDetails?.email,
                address = buildAddress(upiParams.paymentMethodData?.billingDetails)
            ),
            metadata = upiParams.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithFpx(fpxParams: ConfirmParams.FpxParams): PaymentMethodCreateParams {
        val fpx = PaymentMethodCreateParams.Fpx(bank = fpxParams.paymentMethodData?.bankName)

        return PaymentMethodCreateParams.create(
            fpx = fpx,
            billingDetails = PaymentMethod.BillingDetails(
                name = fpxParams.paymentMethodData?.billingDetails?.name,
                phone = fpxParams.paymentMethodData?.billingDetails?.phone,
                email = fpxParams.paymentMethodData?.billingDetails?.email,
                address = buildAddress(fpxParams.paymentMethodData?.billingDetails)
            ),
            metadata = fpxParams.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithPaypal(paypalParams: ConfirmParams.PayPalParams): PaymentMethodCreateParams {
        return PaymentMethodCreateParams.createPayPal(
            metadata = paypalParams.paymentMethodData?.metadata,
        )
    }

    fun createPaymentWithSepaDebit(params: ConfirmParams.SepaDebitParams): PaymentMethodCreateParams {
        val sepaDebit = PaymentMethodCreateParams.SepaDebit(iban = params.paymentMethodData?.iban)

        return PaymentMethodCreateParams.create(
            sepaDebit = sepaDebit,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithAuBecsDebit(params: ConfirmParams.AuBecsDebitParams): PaymentMethodCreateParams {
        val auBecs = PaymentMethodCreateParams.AuBecsDebit(
            bsbNumber = params.paymentMethodData?.bsbNumber ?: "",
            accountNumber = params.paymentMethodData?.accountNumber ?: ""
        )
        return PaymentMethodCreateParams.create(
            auBecsDebit = auBecs,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithBacsDebit(params: ConfirmParams.BacsDebitParams): PaymentMethodCreateParams {
        val bascDetails = PaymentMethodCreateParams.BacsDebit(
            accountNumber = params.paymentMethodData?.accountNumber ?: "",
            sortCode = params.paymentMethodData?.sortCode ?: ""
        )

        return PaymentMethodCreateParams.create(
            bacsDebit = bascDetails,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithSofort(params: ConfirmParams.SofortParams): PaymentMethodCreateParams {
        val sofortDetails = PaymentMethodCreateParams.Sofort(
            country = params.paymentMethodData?.country ?: "",
        )


        return PaymentMethodCreateParams.create(
            sofort = sofortDetails,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithNetBanking(params: ConfirmParams.NetBankingParams): PaymentMethodCreateParams {
        val netBanking = PaymentMethodCreateParams.Netbanking(
            bank = params.paymentMethodData?.bank ?: ""
        )


        return PaymentMethodCreateParams.create(
            netbanking = netBanking,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithUsBankAccount(params: ConfirmParams.USBankAccountParams): PaymentMethodCreateParams {

        val accountType = when (params.paymentMethodData?.accountType) {
            ConfirmParams.BankAccountType.SAVINGS -> PaymentMethod.USBankAccount.USBankAccountType.SAVINGS
            ConfirmParams.BankAccountType.CHECKING -> PaymentMethod.USBankAccount.USBankAccountType.CHECKING
            else -> PaymentMethod.USBankAccount.USBankAccountType.UNKNOWN
        }

        val accountHolderType = when (params.paymentMethodData?.accountHolderType) {
            ConfirmParams.BankAccountHolderType.INDIVIDUAL -> PaymentMethod.USBankAccount.USBankAccountHolderType.INDIVIDUAL
            ConfirmParams.BankAccountHolderType.COMPANY -> PaymentMethod.USBankAccount.USBankAccountHolderType.COMPANY
            else -> PaymentMethod.USBankAccount.USBankAccountHolderType.UNKNOWN
        }


        val usBankAccount = if (params.paymentMethodData?.linkAccountSessionId != null) {
            PaymentMethodCreateParams.USBankAccount(
                linkAccountSessionId = params.paymentMethodData.linkAccountSessionId,
            )
        } else {
            PaymentMethodCreateParams.USBankAccount(
                accountNumber = params.paymentMethodData?.accountNumber ?: "",
                routingNumber = params.paymentMethodData?.routingNumber ?: "",
                accountType = accountType,
                accountHolderType = accountHolderType
            )
        }


        return PaymentMethodCreateParams.create(
            usBankAccount = usBankAccount,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithCashAppPay(params: ConfirmParams.CashAppParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createCashAppPay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata,
        )

    }

    fun createPaymentWithSwish(params: ConfirmParams.SwishParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createSwish(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata,
        )

    }

    fun createPaymentWithBanContacts(params: ConfirmParams.BancontactParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createBancontact(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData.billingDetails?.name,
                phone = params.paymentMethodData.billingDetails?.phone,
                email = params.paymentMethodData.billingDetails?.email,
                address = buildAddress(params.paymentMethodData.billingDetails)
            ),
            metadata = params.paymentMethodData.metadata,
        )
    }

    fun createPaymentWithEps(params: ConfirmParams.EpsDebitParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createEps(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata,
        )
    }

    fun createPaymentWithOxxo(params: ConfirmParams.OxxoParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createOxxo(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata,
        )
    }

    fun createPaymentWithAlipay(params: ConfirmParams.AlipayParams): PaymentMethodCreateParams {
        return PaymentMethodCreateParams.createAlipay(
            metadata = params.paymentMethodData?.metadata,
        )
    }

    fun createPaymentWithAfterpayClearpay(params: ConfirmParams.AfterpayClearpayParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createAfterpayClearpay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithBlik(params: ConfirmParams.BlikParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createBlik(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun getBlikPaymentCode(params: ConfirmParams.BlikParams) = params.paymentMethodData?.blikCode!!

    fun getWeChatAppId(params: ConfirmParams.WeChatPayParams) = params.paymentMethodData?.appId!!


    fun createPaymentWithWeChatPay(params: ConfirmParams.WeChatPayParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createWeChatPay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithKlarna(params: ConfirmParams.KlarnaParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createKlarna(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithAffirm(params: ConfirmParams.AffirmParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createAffirm(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithAmazonPay(params: ConfirmParams.AmazonPayParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createAmazonPay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithMultiBanco(params: ConfirmParams.MultiBancoParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createMultibanco(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithAlma(params: ConfirmParams.AlmaParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createAlma(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithSunbit(params: ConfirmParams.SunbitParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createSunbit(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithBillie(params: ConfirmParams.BillieParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createBillie(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithSatispay(params: ConfirmParams.SatispayParams): PaymentMethodCreateParams {
        return PaymentMethodCreateParams.createSatispay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithRevolutPay(params: ConfirmParams.RevolutPayParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createRevolutPay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithMobilePay(params: ConfirmParams.MobilePayParams): PaymentMethodCreateParams {

        return PaymentMethodCreateParams.createMobilePay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithGiropay(params: ConfirmParams.GiropayParams): PaymentMethodCreateParams {
        return PaymentMethodCreateParams.createGiropay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    private fun buildAddress(details: BillingDetails?): Address {
        return Address.Builder().apply {
            setLine1(details?.address?.line1)
            setLine2(details?.address?.line2)
            setCity(details?.address?.city)
            setState(details?.address?.state)
            setCountry(details?.address?.country)
            setPostalCode(details?.address?.postalCode)
        }.build()
    }

    fun createPaymentWithGooglePay(params: ConfirmParams.GooglePayParams): PaymentMethodCreateParams {
        return PaymentMethodCreateParams.createFromGooglePay(params.jsonObject as JSONObject)
    }
}
