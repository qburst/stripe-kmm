package model

import com.stripe.android.model.Address
import com.stripe.android.model.PaymentMethod
import com.stripe.android.model.PaymentMethodCreateParams

class CreatePaymentModel {

    fun createCardPaymentParamsWithToken(params: CreateParams.CardParamsWithToken): PaymentMethodCreateParams {
        val card = PaymentMethodCreateParams.Card.create(params.paymentMethodData?.token ?: "")
        val address = Address.Builder()
        address.setLine1(params.paymentMethodData?.billingDetails?.address?.line1)
        address.setLine2(params.paymentMethodData?.billingDetails?.address?.line2)
        address.setCity(params.paymentMethodData?.billingDetails?.address?.city)
        address.setState(params.paymentMethodData?.billingDetails?.address?.state)
        address.setCountry(params.paymentMethodData?.billingDetails?.address?.country)
        address.setPostalCode(params.paymentMethodData?.billingDetails?.address?.postalCode)

        return PaymentMethodCreateParams.create(
            card = card,
            allowRedisplay = null,
            billingDetails = PaymentMethod.BillingDetails(
                name = params.paymentMethodData?.billingDetails?.name,
                phone = params.paymentMethodData?.billingDetails?.phone,
                email = params.paymentMethodData?.billingDetails?.email,
                address = address.build()
            ),
            metadata = null
        )
    }
}