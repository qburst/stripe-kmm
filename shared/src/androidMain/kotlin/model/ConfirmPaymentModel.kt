package model

import com.stripe.android.model.Address
import com.stripe.android.model.PaymentMethod
import com.stripe.android.model.PaymentMethodCreateParams

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
        val address = Address.Builder()
        address.setLine1(paypalParams.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(paypalParams.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(paypalParams.paymentMethodData?.billingDetails?.address?.city)
        address.setState(paypalParams.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(paypalParams.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(paypalParams.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.createPayPal(
            metadata = paypalParams.paymentMethodData?.metadata,
        )
    }
}
