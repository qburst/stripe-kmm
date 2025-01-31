package model

/** Sealed class representing different types of intent parameters. */
sealed class IntentParams {

    /**
     * Represents payment intent parameters.
     *
     * @property paymentIntentClientSecret The client secret for the payment intent.
     * @property setupIntentClientSecret The client secret for the setup intent (optional).
     */
    data class PaymentIntentParams(
            val paymentIntentClientSecret: String,
            val setupIntentClientSecret: String? = null,
    //        val intentConfiguration: IntentConfiguration? = null
    ) : IntentParams() {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the payment intent client secret, setup intent client secret,
         * ```
         *         and intent configuration (if any).
         * ```
         */
        fun toDictionary() =
                mapOf(
                        "paymentIntentClientSecret" to paymentIntentClientSecret,
                        "setupIntentClientSecret" to setupIntentClientSecret,
                        //            "intentConfiguration" to intentConfiguration?.toDictionary()
                        )
    }

    /**
     * Represents setup intent parameters.
     *
     * @property setupIntentClientSecret The client secret for the setup intent.
     * @property paymentIntentClientSecret The client secret for the payment intent (optional).
     */
    data class SetupIntentParams(
            val setupIntentClientSecret: String,
            val paymentIntentClientSecret: String? = null,
    //        val intentConfiguration: IntentConfiguration? = null
    ) : IntentParams() {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the setup intent client secret, payment intent client secret,
         * ```
         *         and intent configuration (if any).
         * ```
         */
        fun toDictionary() =
                mapOf(
                        "setupIntentClientSecret" to setupIntentClientSecret,
                        "paymentIntentClientSecret" to paymentIntentClientSecret,
                        //            "intentConfiguration" to intentConfiguration?.toDictionary()
                        )
    }

    /**
     * Represents configuration intent parameters.
     *
     * @property setupIntentClientSecret The client secret for the setup intent (optional).
     * @property paymentIntentClientSecret The client secret for the payment intent (optional).
     */
    data class ConfigurationIntentParams(
            val setupIntentClientSecret: String? = null,
            val paymentIntentClientSecret: String? = null,
    //        val intentConfiguration: IntentConfiguration
    ) : IntentParams() {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the setup intent client secret, payment intent client secret,
         * ```
         *         and intent configuration (if any).
         * ```
         */
        fun toDictionary() =
                mapOf(
                        "setupIntentClientSecret" to setupIntentClientSecret,
                        "paymentIntentClientSecret" to paymentIntentClientSecret,
                        //            "intentConfiguration" to intentConfiguration.toDictionary()
                        )
    }
}

/**
 * Represents the setup parameters for payment methods.
 *
 * @property merchantDisplayName The name of the merchant.
 * @property customerId The customer ID (optional).
 * @property customerEphemeralKeySecret The ephemeral key secret for the customer (optional).
 * @property customFlow Indicates if the custom flow is enabled (default is false).
 * @property style The style for the payment method (default is [Style.AUTOMATIC]).
 * @property returnURL The return URL after the payment is completed (optional).
 * @property billingDetailsCollectionConfiguration Configuration for billing details collection
 * (optional).
 * @property defaultBillingDetails The default billing details (optional).
 * @property defaultShippingDetails The default shipping details (optional).
 * @property allowsDelayedPaymentMethods Indicates if delayed payment methods are allowed (default
 * is false).
 * @property primaryButtonLabel The label for the primary button (optional).
 * @property removeSavedPaymentMethodMessage Message shown when removing a saved payment method
 * (optional).
 * @property preferredNetworks A list of preferred card brands for payment (optional).
 * @property paymentMethodOrder A list of payment methods to be used (optional).
 * @property allowsRemovalOfLastSavedPaymentMethod Indicates if the last saved payment method can be
 * removed (default is true).
 * @property paymentIntentClientSecret The client secret for the payment intent (required).
 * @property amount The amount for the payment (default is "0").
 */
data class SetupParams(
        val merchantDisplayName: String,
        val customerId: String? = null,
        val customerEphemeralKeySecret: String? = null,
        val customFlow: Boolean = false,
        //    val applePay: ApplePayParams? = null,
        //    val googlePay: GooglePayParams? = null,
        val style: Style = Style.AUTOMATIC,
        val returnURL: String? = null,
        val billingDetailsCollectionConfiguration: BillingDetailsCollectionConfiguration? = null,
        val defaultBillingDetails: BillingDetails? = null,
        val defaultShippingDetails: AddressDetails? = null,
        val allowsDelayedPaymentMethods: Boolean = false,
        //    val appearance: AppearanceParams? = null,
        val primaryButtonLabel: String? = null,
        val removeSavedPaymentMethodMessage: String? = null,
        val preferredNetworks: List<CardBrand>? = null,
        val paymentMethodOrder: List<String>? = null,
        val allowsRemovalOfLastSavedPaymentMethod: Boolean = true,
        val paymentIntentClientSecret: String?,
        val amount: String? = "0"
) {
    /**
     * Converts this object to a dictionary.
     *
     * @return A map containing all the setup parameters.
     */
    fun toDictionary() =
            mapOf(
                    "merchantDisplayName" to merchantDisplayName,
                    "customerId" to customerId,
                    "customerEphemeralKeySecret" to customerEphemeralKeySecret,
                    "customFlow" to customFlow,
                    //        "applePay" to applePay?.toDictionary(),
                    //        "googlePay" to googlePay?.toDictionary(),
                    "style" to style.toDictionary(),
                    "returnURL" to returnURL,
                    "billingDetailsCollectionConfiguration" to
                            billingDetailsCollectionConfiguration?.toDictionary(),
                    "defaultBillingDetails" to defaultBillingDetails?.toDictionary(),
                    "defaultShippingDetails" to defaultShippingDetails?.toDictionary(),
                    "allowsDelayedPaymentMethods" to allowsDelayedPaymentMethods,
                    //        "appearance" to appearance?.toDictionary(),
                    "primaryButtonLabel" to primaryButtonLabel,
                    "removeSavedPaymentMethodMessage" to removeSavedPaymentMethodMessage,
                    "preferredNetworks" to preferredNetworks?.map { it.toDictionary() },
                    "paymentMethodOrder" to paymentMethodOrder,
                    "allowsRemovalOfLastSavedPaymentMethod" to
                            allowsRemovalOfLastSavedPaymentMethod,
                    "paymentIntentClientSecret" to paymentIntentClientSecret
            )
}

/** Enum for style types used in payment methods. */
enum class Style {
    ALWAYS_LIGHT,
    ALWAYS_DARK,
    AUTOMATIC;

    /**
     * Converts this enum value to a lowercase string.
     *
     * @return The lowercase string representation of the style.
     */
    fun toDictionary() = name.lowercase()
}

/** Enum representing collection modes for various data types. */
enum class CollectionMode(val value: String) {
    AUTOMATIC("automatic"),
    NEVER("never"),
    ALWAYS("always")
}

/** Enum representing address collection modes. */
enum class AddressCollectionMode(val value: String) {
    AUTOMATIC("automatic"),
    NEVER("never"),
    FULL("full")
}

/**
 * Represents the configuration for collecting billing details.
 *
 * @property name The collection mode for the name (default is [CollectionMode.AUTOMATIC]).
 * @property phone The collection mode for the phone (default is [CollectionMode.AUTOMATIC]).
 * @property email The collection mode for the email (default is [CollectionMode.AUTOMATIC]).
 * @property address The collection mode for the address (default is
 * [AddressCollectionMode.AUTOMATIC]).
 * @property attachDefaultsToPaymentMethod Flag to attach defaults to the payment method (default is
 * false).
 */
data class BillingDetailsCollectionConfiguration(
        val name: CollectionMode = CollectionMode.AUTOMATIC,
        val phone: CollectionMode = CollectionMode.AUTOMATIC,
        val email: CollectionMode = CollectionMode.AUTOMATIC,
        val address: AddressCollectionMode = AddressCollectionMode.AUTOMATIC,
        val attachDefaultsToPaymentMethod: Boolean = false
) {
    /**
     * Converts this object to a dictionary.
     *
     * @return A map containing the collection modes and attach flag.
     */
    fun toDictionary(): Map<String, Any?> =
            mapOf(
                    "name" to name.value,
                    "phone" to phone.value,
                    "email" to email.value,
                    "address" to address.value,
                    "attachDefaultsToPaymentMethod" to attachDefaultsToPaymentMethod
            )
}

/**
 * Represents the options for presenting payment methods.
 *
 * @property timeout The timeout duration for the presentation (optional).
 */
data class PresentOptions(val timeout: Long? = null) {
    /**
     * Converts this object to a dictionary.
     *
     * @return A map containing the timeout duration.
     */
    fun toDictionary() = mapOf("timeout" to timeout)
}

/**
 * Represents a card brand used in payment methods.
 *
 * @property brandName The name of the card brand (e.g., Visa, MasterCard).
 */
data class CardBrand(
        val brandName: String // Example placeholder
) {
    /**
     * Converts this object to a dictionary.
     *
     * @return A map containing the brand name.
     */
    fun toDictionary() = mapOf("brandName" to brandName)
}
