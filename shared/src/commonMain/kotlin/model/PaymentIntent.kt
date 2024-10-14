package model

import CreateOptions

sealed class ConfirmParams {


    data class CardParamsWithToken(
        val paymentMethodType: String = "Card",
        val paymentMethodData: PaymentMethodDataWithToken?
    ) : ConfirmParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    data class IdealParams(
        val paymentMethodType: String = "Ideal",
        val paymentMethodData: PaymentMethodDataIdeal?,
    ) : ConfirmParams() {

        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary(),
            )
        }
    }

    // Data class for IdealParams paymentMethodData
    data class PaymentMethodDataIdeal(
        val bankName: String? = null,
        val billingDetails: BillingDetails? = null,
        val mandateData: MandateData? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "bankName" to bankName,
                "billingDetails" to billingDetails?.toDictionary(),
                "mandateData"   to mandateData?.toDictionary()
            )
        }
    }

    data class PaymentMethodDataWithToken(
        val token: String? = null,
        val billingDetails: BillingDetails? = null,
        val mandateData: MandateData? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "token" to token,
                "billingDetails" to billingDetails?.toDictionary(),
                "mandateData"   to mandateData?.toDictionary()
            )
        }
    }

    data class MandateData(
        val customerAcceptance: CustomerAcceptance
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "customerAcceptance" to customerAcceptance.toDictionary()
            )
        }
    }

    // Define the CustomerAcceptance class
    data class CustomerAcceptance(
        val online: OnlineAcceptance
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "online" to online.toDictionary()
            )
        }
    }

    // Define the OnlineAcceptance class
    data class OnlineAcceptance(
        val ipAddress: String, // IP address of the customer
        val userAgent: String // User agent string of the customer's device
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "ipAddress" to ipAddress,
                "userAgent" to userAgent
            )
        }
    }
}


typealias ConfirmOptions = CreateOptions


enum class FutureUsage(val value: String) {
    OFF_SESSION("OffSession"),
    ON_SESSION("OnSession");

    companion object {
        // Function to convert from a string value to an enum
        fun fromString(value: String): FutureUsage? {
            return values().find { it.value == value }
        }
    }

    fun toDictionary(): Map<String, String> {
        return mapOf("value" to value)
    }
}
