package model

data class PaymentMethod(
    val id: String
) {
    data class CreateParams(
        val type: String,
        val card: CardParams? = null,
        val billingDetails: BillingDetailsParams? = null,
        // Add other fields as necessary
    ) {
        fun toMap(): Map<String, Any?> {
            val map = mutableMapOf<String, Any?>("type" to type)
            card?.let { map["card"] = it.toMap() }
            billingDetails?.let { map["billing_details"] = it.toMap() }
            return map
        }
    }

    data class CardParams(
        val number: String,
        val expMonth: Int,
        val expYear: Int,
        val cvc: String
    ) {
        fun toMap(): Map<String, Any?> {
            return mapOf(
                "number" to number,
                "exp_month" to expMonth,
                "exp_year" to expYear,
                "cvc" to cvc
            )
        }
    }

    data class BillingDetailsParams(
        val name: String? = null,
        val email: String? = null,
        val phone: String? = null,
        // Add other fields as necessary
    ) {
        fun toMap(): Map<String, Any?> {
            return mapOf(
                "name" to name,
                "email" to email,
                "phone" to phone
            )
        }
    }

    data class CreateOptions(
        val captureMethod: String? = null, // Options for capture method (e.g., 'automatic' or 'manual')
        val statementDescriptor: String? = null, // Custom statement descriptor
        val metadata: Map<String, String>? = null // Additional metadata
    ) {
        fun toMap(): Map<String, Any?> {
            val map = mutableMapOf<String, Any?>()
            captureMethod?.let { map["capture_method"] = it }
            statementDescriptor?.let { map["statement_descriptor"] = it }
            metadata?.let { map["metadata"] = it }
            return map
        }
    }
}

sealed class CreatePaymentMethodResult {
    data class Success(val paymentMethodId: String) : CreatePaymentMethodResult()
    data class Failure(val error: String) : CreatePaymentMethodResult()
}