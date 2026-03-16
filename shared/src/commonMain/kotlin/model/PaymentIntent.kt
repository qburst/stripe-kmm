package model

import CreateOptions

/**
 * Sealed class representing the various types of confirmation parameters.
 */
sealed class ConfirmParams {

    /**
     * Data class for payment method type "Card" with token.
     * 
     * @property paymentMethodType Type of payment method (default is "Card").
     * @property paymentMethodData Contains payment method details with a token.
     */
    data class CardParamsWithToken(
        val paymentMethodType: String = "Card",
        val paymentMethodData: PaymentMethodDataWithToken?
    ) : ConfirmParams() {
        
        /**
         * Converts this object to a dictionary.
         * 
         * @return A map containing the payment method type and data.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    /**
     * Data class for payment method type "Ideal".
     * 
     * @property paymentMethodType Type of payment method (default is "Ideal").
     * @property paymentMethodData Contains payment method details for Ideal.
     */
    data class IdealParams(
        val paymentMethodType: String = "Ideal",
        val paymentMethodData: PaymentMethodDataIdeal?,
    ) : ConfirmParams() {

        /**
         * Converts this object to a dictionary.
         * 
         * @return A map containing the payment method type and data.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary(),
            )
        }
    }

    /**
     * Data class representing the payment method details for Ideal.
     * 
     * @property bankName The bank name for the payment method.
     * @property billingDetails The billing details for the payment method.
     * @property metadata Additional metadata data for the payment method.
     */
    data class PaymentMethodDataIdeal(
        val bankName: String? = null,
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        
        /**
         * Converts this object to a dictionary.
         * 
         * @return A map containing the bank name, billing details, and metadata data.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "bankName" to bankName,
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    /**
     * Data class representing the payment method details with a token.
     * 
     * @property token The token for the payment method.
     * @property billingDetails The billing details for the payment method.
     * @property mandateData Additional mandate data for the payment method.
     */
    data class PaymentMethodDataWithToken(
        val token: String? = null,
        val billingDetails: BillingDetails? = null,
        val mandateData: MandateData? = null
    ) {
        
        /**
         * Converts this object to a dictionary.
         * 
         * @return A map containing the token, billing details, and mandate data.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "token" to token,
                "billingDetails" to billingDetails?.toDictionary(),
                "mandateData"   to mandateData?.toDictionary()
            )
        }
    }

    /**
     * Data class representing mandate data for the payment method.
     * 
     * @property customerAcceptance Contains information about the customer's acceptance.
     */
    data class MandateData(
        val customerAcceptance: CustomerAcceptance
    ) {
        
        /**
         * Converts this object to a dictionary.
         * 
         * @return A map containing the customer acceptance data.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "customerAcceptance" to customerAcceptance.toDictionary()
            )
        }
    }

    // Define the CustomerAcceptance class
    /**
     * Data class representing the customer's acceptance of the payment method.
     * 
     * @property online Contains online acceptance information.
     */
    data class CustomerAcceptance(
        val online: OnlineAcceptance
    ) {
        
        /**
         * Converts this object to a dictionary.
         * 
         * @return A map containing the online acceptance data.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "online" to online.toDictionary()
            )
        }
    }

    // Define the OnlineAcceptance class
    /**
     * Data class representing the online acceptance of the customer.
     * 
     * @property ipAddress The IP address of the customer.
     * @property userAgent The user agent string of the customer's device.
     */
    data class OnlineAcceptance(
        val ipAddress: String, // IP address of the customer
        val userAgent: String // User agent string of the customer's device
    ) {
        
        /**
         * Converts this object to a dictionary.
         * 
         * @return A map containing the IP address and user agent.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "ipAddress" to ipAddress,
                "userAgent" to userAgent
            )
        }
    }

    /**
     * Data class for payment method type "Upi".
     *
     * @property paymentMethodType Type of payment method (default is "Upi").
     * @property paymentMethodData Contains payment method details for Upi.
     */
    data class UpiParams(
        val paymentMethodType: String = "Upi",
        val paymentMethodData: PaymentMethodDataUpi?,
    ) : ConfirmParams() {

        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the payment method type and data.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary(),
            )
        }
    }

    data class PaymentMethodDataUpi(
        val vpa: String?,
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the VPA, billing details, and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "vpa" to vpa,
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    /**
     * Data class for payment method type "Fpx".
     *
     * @property paymentMethodType Type of payment method (default is "Fpx").
     * @property paymentMethodData Contains payment method details for Fpx.
     */
    data class FpxParams(
        val paymentMethodType: String = "Fpx",
        val paymentMethodData: PaymentMethodDataFpx?,
    ) : ConfirmParams() {

        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the payment method type and data.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary(),
            )
        }
    }

    data class PaymentMethodDataFpx(
        val bankName: String?,
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the bank and billing details.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "bank" to bankName,
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    /**
     * Represents PayPalParams.
     *
     * @property paymentMethodType The type of payment method (default is "PayPal").
     * @property paymentMethodData The payment method data for PayPal.
     */
    data class PayPalParams(
        val paymentMethodType: String = "PayPal", // Constant value
        val paymentMethodData: PaymentMethodDataPayPal? = null
    ) : ConfirmParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    /**
     * Represents payment method data for PayPal.
     *
     * @property billingDetails The billing details associated with the PayPal account (required).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataPayPal(
        val billingDetails: BillingDetails,
        val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails.toDictionary(), "metadata" to metadata)
        }
    }
}

/**
 * Type alias for CreateOptions.
 */
typealias ConfirmOptions = CreateOptions

/**
 * Enum class representing future usage options.
 * 
 * @property value The string value for the future usage option.
 */
enum class FutureUsage(val value: String) {
    OFF_SESSION("OffSession"),
    ON_SESSION("OnSession");

    companion object {
        /**
         * Converts a string value to a corresponding [FutureUsage] enum.
         * 
         * @param value The string value.
         * @return The corresponding [FutureUsage] enum, or null if not found.
         */
        fun fromString(value: String): FutureUsage? {
            return values().find { it.value == value }
        }
    }

    /**
     * Converts this enum to a dictionary.
     * 
     * @return A map containing the value of the enum.
     */
    fun toDictionary(): Map<String, String> {
        return mapOf("value" to value)
    }
}
