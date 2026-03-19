package model

import CreateParams
import com.stripe.android.model.Address
import com.stripe.android.model.PaymentMethod
import com.stripe.android.model.PaymentMethodCreateParams
import org.json.JSONObject

/**
 * This class will convert the user input to [PaymentMethodCreateParams] for calling native stripe api
 * @param [CreateParams] It will get the different types of wallet methods as params
 * @param[PaymentMethodCreateParams] will be returned this is the object from stripe sdk.
 */
class CreatePaymentModel {

    fun createCardPaymentParamsWithToken(params: CreateParams.CardParamsWithToken): PaymentMethodCreateParams {
        val card = PaymentMethodCreateParams.Card.create(params.paymentMethodData?.token ?: "")
        return PaymentMethodCreateParams.create(
            card = card,
            allowRedisplay = null,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(details = params.paymentMethodData?.billingDetails)
            ),
            metadata = null
        )
    }

    fun createPaymentWithIdeal(params: CreateParams.IdealParams): PaymentMethodCreateParams {
        val ideal = PaymentMethodCreateParams.Ideal(bank = params.paymentMethodData?.bankName)
        return PaymentMethodCreateParams.create(
            ideal = ideal,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithFpx(params: CreateParams.FPXParams): PaymentMethodCreateParams {
        val fpx = PaymentMethodCreateParams.Fpx(bank = params.paymentMethodData?.bankName)
        return PaymentMethodCreateParams.create(
            fpx = fpx,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithSepaDebit(params: CreateParams.SepaDebitParams): PaymentMethodCreateParams {
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

    fun createPaymentWithAuBecsDebit(params: CreateParams.AuBecsDebitParams): PaymentMethodCreateParams {
        val auBecs = PaymentMethodCreateParams.AuBecsDebit(
            bsbNumber = params.paymentMethodData?.formDetails?.bsbNumber ?: "",
            accountNumber = params.paymentMethodData?.formDetails?.accountNumber ?: ""
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

    fun createPaymentWithBacsDebit(params: CreateParams.BacsDebitParams): PaymentMethodCreateParams {
        val bascDetails = PaymentMethodCreateParams.BacsDebit(
            accountNumber = params.paymentMethodData?.bacsDebit?.accountNumber ?: "",
            sortCode = params.paymentMethodData?.bacsDebit?.sortCode ?: ""
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

    fun createPaymentWithSofort (params: CreateParams.SofortParams): PaymentMethodCreateParams {
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

    fun createPaymentWithUpi (params: CreateParams.UpiParams): PaymentMethodCreateParams {
        val upiDetails = PaymentMethodCreateParams.Upi(
            vpa = params.paymentMethodData?.vpa ?: ""
        )

        return PaymentMethodCreateParams.create(
            upi = upiDetails,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = buildAddress(params.paymentMethodData?.billingDetails)
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithNetBanking(params: CreateParams.NetBankingParams): PaymentMethodCreateParams {
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

    fun createPaymentWithUsBankAccount(params: CreateParams.USBankAccountParams): PaymentMethodCreateParams {

        val accountType = when (params.paymentMethodData?.accountType) {
            CreateParams.BankAccountType.SAVINGS -> PaymentMethod.USBankAccount.USBankAccountType.SAVINGS
            CreateParams.BankAccountType.CHECKING -> PaymentMethod.USBankAccount.USBankAccountType.CHECKING
            else -> PaymentMethod.USBankAccount.USBankAccountType.UNKNOWN
        }

        val accountHolderType = when (params.paymentMethodData?.accountHolderType) {
            CreateParams.BankAccountHolderType.INDIVIDUAL -> PaymentMethod.USBankAccount.USBankAccountHolderType.INDIVIDUAL
            CreateParams.BankAccountHolderType.COMPANY -> PaymentMethod.USBankAccount.USBankAccountHolderType.COMPANY
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

    fun createPaymentWithCashAppPay(params: CreateParams.CashAppParams): PaymentMethodCreateParams {
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

    fun createPaymentWithSwish(params: CreateParams.SwishParams): PaymentMethodCreateParams {
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

    fun createPaymentWithBanContacts(params: CreateParams.BancontactParams): PaymentMethodCreateParams {
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

    fun createPaymentWithEps(params: CreateParams.EpsDebitParams): PaymentMethodCreateParams {
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

    fun createPaymentWithOxxo(params: CreateParams.OxxoParams): PaymentMethodCreateParams {
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

    fun createPaymentWithAlipay(params: CreateParams.AlipayParams): PaymentMethodCreateParams {
        return PaymentMethodCreateParams.createAlipay(
            metadata = params.paymentMethodData?.metadata,
        )
    }

    fun createPaymentWithPaypal(params: CreateParams.PayPalParams): PaymentMethodCreateParams {
        return PaymentMethodCreateParams.createPayPal(
            metadata = params.paymentMethodData?.metadata,
        )
    }

    fun createPaymentWithClearPay(params: CreateParams.AfterpayClearpayParams): PaymentMethodCreateParams {
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

    fun createPaymentWithGooglePay(params: CreateParams.GooglePayParams): PaymentMethodCreateParams {
        return PaymentMethodCreateParams.createFromGooglePay(params.jsonObject as JSONObject)
    }

    fun createPaymentWithBlik(params: CreateParams.BlikParams): PaymentMethodCreateParams {
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

    fun createPaymentWithWeChatPay(params: CreateParams.WeChatPayParams): PaymentMethodCreateParams {
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

    fun createPaymentWithKlarna(params: CreateParams.KlarnaParams): PaymentMethodCreateParams {
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

    fun createPaymentWithAffirm(params: CreateParams.AffirmParams): PaymentMethodCreateParams {
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

    fun createPaymentWithAmazonPay(params: CreateParams.AmazonPayParams): PaymentMethodCreateParams {
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

    fun createPaymentWithMultiBanco(params: CreateParams.MultiBancoParams): PaymentMethodCreateParams {
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

    fun createPaymentWithAlma(params: CreateParams.AlmaParams): PaymentMethodCreateParams {
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

    fun createPaymentWithSunbit(params: CreateParams.SunbitParams): PaymentMethodCreateParams {
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

    fun createPaymentWithBillie(params: CreateParams.BillieParams): PaymentMethodCreateParams {
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

    fun createPaymentWithSatisPay(params: CreateParams.SatispayParams): PaymentMethodCreateParams {
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

    fun createPaymentWithRevolutPay(params: CreateParams.RevolutPayParams): PaymentMethodCreateParams {
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

    fun createPaymentWithMobilePay(params: CreateParams.MobilePayParams): PaymentMethodCreateParams {
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

    private fun buildAddress(details: CreateParams.KlarnaBillingDetails?): Address {
        return Address.Builder().apply {
            setLine1(details?.address?.line1)
            setLine2(details?.address?.line2)
            setCity(details?.address?.city)
            setState(details?.address?.state)
            setCountry(details?.address?.country)
            setPostalCode(details?.address?.postalCode)
        }.build()
    }

    private fun buildAddress(details: CreateParams.USBillingDetails?): Address {
        return Address.Builder().apply {
            setLine1(details?.address?.line1)
            setLine2(details?.address?.line2)
            setCity(details?.address?.city)
            setState(details?.address?.state)
            setCountry(details?.address?.country)
            setPostalCode(details?.address?.postalCode)
        }.build()
    }


}