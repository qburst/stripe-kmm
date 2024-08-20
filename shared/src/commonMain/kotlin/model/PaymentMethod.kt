import model.BillingDetails
import model.FutureUsage

// Sealed class to represent the possible types for CreateParams

sealed class CreateParams {

    // Represents CardParams with optional token
    data class CardParamsWithToken(
        val paymentMethodType: String = "Card",
        val paymentMethodData: PaymentMethodDataWithToken?
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    // Represents CardParams with paymentMethodId
    data class CardParamsWithPaymentId(
        val paymentMethodType: String = "Card",
        val paymentMethodData: PaymentMethodDataWithPaymentId
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    // Represents IdealParams
    data class IdealParams(
        val paymentMethodType: String = "Ideal",
        val paymentMethodData: PaymentMethodDataIdeal?
    ) : CreateParams()

    // Data class for paymentMethodData with token
    data class PaymentMethodDataWithToken(
        val token: String? = null,
        val billingDetails: BillingDetails? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "token" to token,
                "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }

    // Data class for paymentMethodData with paymentMethodId
    data class PaymentMethodDataWithPaymentId(
        val paymentMethodId: String,
        val cvc: String? = null,
        val billingDetails: BillingDetails? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodId" to paymentMethodId,  // Non-nullable, always included
                "cvc" to cvc,  // Nullable, included if not null
                "billingDetails" to billingDetails?.toDictionary()  // Nullable, transformed to a dictionary if not null
            )
        }
    }

    // Data class for IdealParams paymentMethodData
    data class PaymentMethodDataIdeal(
        val bankName: String?,
        val billingDetails: BillingDetails?
    )
}

data class CreateOptions(
    val setupFutureUsage: FutureUsage? = null
) {
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
            "setupFutureUsage" to setupFutureUsage?.value
        )
    }
}
