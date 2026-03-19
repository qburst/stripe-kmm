package model

import com.stripe.android.model.Address
import com.stripe.android.model.PaymentMethod
import com.stripe.android.model.PaymentMethodCreateParams
import org.json.JSONObject

class ConfirmPaymentModel {
    fun createCardPaymentParamsWithToken(cardParams: ConfirmParams.CardParamsWithToken): PaymentMethodCreateParams {
        val card = PaymentMethodCreateParams.Card.create(cardParams.paymentMethodData?.token ?: "")
        val address = Address.Builder()
        address.setLine1(cardParams.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(cardParams.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(cardParams.paymentMethodData?.billingDetails?.address?.city)
        address.setState(cardParams.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(cardParams.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(cardParams.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.create(
            card = card,
            allowRedisplay = null,
            billingDetails = PaymentMethod.BillingDetails(
                name = cardParams.paymentMethodData?.billingDetails?.name,
                phone = cardParams.paymentMethodData?.billingDetails?.phone,
                email = cardParams.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = null
        )
    }

    fun createPaymentWithIdeal(idelParams: ConfirmParams.IdealParams): PaymentMethodCreateParams {
        val ideal = PaymentMethodCreateParams.Ideal(bank = idelParams.paymentMethodData?.bankName)
        val address = Address.Builder()
        address.setLine1(idelParams.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(idelParams.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(idelParams.paymentMethodData?.billingDetails?.address?.city)
        address.setState(idelParams.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(idelParams.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(idelParams.paymentMethodData?.billingDetails?.address?.postalCode)
        return PaymentMethodCreateParams.create(
            ideal = ideal,
            billingDetails = PaymentMethod.BillingDetails(
                name = idelParams.paymentMethodData?.billingDetails?.name,
                phone = idelParams.paymentMethodData?.billingDetails?.phone,
                email = idelParams.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = idelParams.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithUpi (upiParams: ConfirmParams.UpiParams): PaymentMethodCreateParams {
        val upiDetails = PaymentMethodCreateParams.Upi(
            vpa = upiParams.paymentMethodData?.vpa ?: ""
        )

        val address = Address.Builder()
        address.setLine1(upiParams.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(upiParams.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(upiParams.paymentMethodData?.billingDetails?.address?.city)
        address.setState(upiParams.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(upiParams.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(upiParams.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.create(
            upi = upiDetails,
            billingDetails = PaymentMethod.BillingDetails(
                name = upiParams.paymentMethodData?.billingDetails?.name,
                phone = upiParams.paymentMethodData?.billingDetails?.phone,
                email = upiParams.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = upiParams.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithFpx(fpxParams: ConfirmParams.FpxParams): PaymentMethodCreateParams {
        val fpx = PaymentMethodCreateParams.Fpx(bank = fpxParams.paymentMethodData?.bankName)
        val address = Address.Builder()
        address.setLine1(fpxParams.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(fpxParams.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(fpxParams.paymentMethodData?.billingDetails?.address?.city)
        address.setState(fpxParams.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(fpxParams.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(fpxParams.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.create(
            fpx = fpx,
            billingDetails = PaymentMethod.BillingDetails(
                name = fpxParams.paymentMethodData?.billingDetails?.name,
                phone = fpxParams.paymentMethodData?.billingDetails?.phone,
                email = fpxParams.paymentMethodData?.billingDetails?.email,
                address = address.build()
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
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.create(
            sepaDebit = sepaDebit,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithAuBecsDebit(params: ConfirmParams.AuBecsDebitParams): PaymentMethodCreateParams {
        val auBecs = PaymentMethodCreateParams.AuBecsDebit(
            bsbNumber = params.paymentMethodData?.bsbNumber ?: "",
            accountNumber = params.paymentMethodData?.accountNumber ?: ""
        )
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)
        return PaymentMethodCreateParams.create(
            auBecsDebit = auBecs,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithBacsDebit(params: ConfirmParams.BacsDebitParams): PaymentMethodCreateParams {
        val bascDetails = PaymentMethodCreateParams.BacsDebit(
            accountNumber = params.paymentMethodData?.accountNumber ?: "",
            sortCode = params.paymentMethodData?.sortCode ?: ""
        )
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.create(
            bacsDebit = bascDetails,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithSofort(params: ConfirmParams.SofortParams): PaymentMethodCreateParams {
        val sofortDetails = PaymentMethodCreateParams.Sofort(
            country = params.paymentMethodData?.country ?: "",
        )

        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.create(
            sofort = sofortDetails,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithNetBanking(params: ConfirmParams.NetBankingParams): PaymentMethodCreateParams {
        val netBanking = PaymentMethodCreateParams.Netbanking(
            bank = params.paymentMethodData?.bank ?: ""
        )

        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.create(
            netbanking = netBanking,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
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

        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.create(
            usBankAccount = usBankAccount,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithCashAppPay(params: ConfirmParams.CashAppParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createCashAppPay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata,
        )

    }

    fun createPaymentWithSwish(params: ConfirmParams.SwishParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createSwish(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata,
        )

    }

    fun createPaymentWithBanContacts(params: ConfirmParams.BancontactParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData.billingDetails?.address?.city)
        address.setState(params.paymentMethodData.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createBancontact(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData.billingDetails?.name,
                phone = params.paymentMethodData.billingDetails?.phone,
                email = params.paymentMethodData.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData.metadata,
        )
    }

    fun createPaymentWithEps(params: ConfirmParams.EpsDebitParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createEps(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata,
        )
    }

    fun createPaymentWithOxxo(params: ConfirmParams.OxxoParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createOxxo(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
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
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createAfterpayClearpay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithBlik(params: ConfirmParams.BlikParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createBlik(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun getBlikPaymentCode(params: ConfirmParams.BlikParams) = params.paymentMethodData?.blikCode!!

    fun createPaymentWithWeChatPay(params: ConfirmParams.WeChatPayParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createWeChatPay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithKlarna(params: ConfirmParams.KlarnaParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createKlarna(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithAffirm(params: ConfirmParams.AffirmParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createAffirm(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithAmazonPay(params: ConfirmParams.AmazonPayParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createAmazonPay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithMultiBanco(params: ConfirmParams.MultiBancoParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createMultibanco(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithAlma(params: ConfirmParams.AlmaParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createAlma(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithSunbit(params: ConfirmParams.SunbitParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createSunbit(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithBillie(params: ConfirmParams.BillieParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createBillie(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithSatispay(params: ConfirmParams.SatispayParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createSatispay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithRevolutPay(params: ConfirmParams.RevolutPayParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createRevolutPay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithMobilePay(params: ConfirmParams.MobilePayParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createMobilePay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithGiropay(params: ConfirmParams.GiropayParams): PaymentMethodCreateParams {
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createGiropay(
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = params.paymentMethodData?.metadata
        )
    }

    fun createPaymentWithGooglePay(params: ConfirmParams.GooglePayParams): PaymentMethodCreateParams {
        return PaymentMethodCreateParams.createFromGooglePay(params.jsonObject as JSONObject)
    }
}
