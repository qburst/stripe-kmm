package model



sealed class IntentParams {


    data class PaymentIntentParams(
        val paymentIntentClientSecret: String,
        val setupIntentClientSecret: String? = null,
//        val intentConfiguration: IntentConfiguration? = null
    ) : IntentParams() {
         fun toDictionary() = mapOf(
            "paymentIntentClientSecret" to paymentIntentClientSecret,
            "setupIntentClientSecret" to setupIntentClientSecret,
//            "intentConfiguration" to intentConfiguration?.toDictionary()
        )
    }

    data class SetupIntentParams(
        val setupIntentClientSecret: String,
        val paymentIntentClientSecret: String? = null,
//        val intentConfiguration: IntentConfiguration? = null
    ) : IntentParams() {
         fun toDictionary() = mapOf(
            "setupIntentClientSecret" to setupIntentClientSecret,
            "paymentIntentClientSecret" to paymentIntentClientSecret,
//            "intentConfiguration" to intentConfiguration?.toDictionary()
        )
    }

    data class ConfigurationIntentParams(
        val setupIntentClientSecret: String? = null,
        val paymentIntentClientSecret: String? = null,
//        val intentConfiguration: IntentConfiguration
    ) : IntentParams() {
         fun toDictionary() = mapOf(
            "setupIntentClientSecret" to setupIntentClientSecret,
            "paymentIntentClientSecret" to paymentIntentClientSecret,
//            "intentConfiguration" to intentConfiguration.toDictionary()
        )
    }
}

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
    val paymentIntentClientSecret: String? ,
    val amount:String?="0"

) {
    fun toDictionary() = mapOf(
        "merchantDisplayName" to merchantDisplayName,
        "customerId" to customerId,
        "customerEphemeralKeySecret" to customerEphemeralKeySecret,
        "customFlow" to customFlow,
//        "applePay" to applePay?.toDictionary(),
//        "googlePay" to googlePay?.toDictionary(),
        "style" to style.toDictionary(),
        "returnURL" to returnURL,
        "billingDetailsCollectionConfiguration" to billingDetailsCollectionConfiguration?.toDictionary(),
        "defaultBillingDetails" to defaultBillingDetails?.toDictionary(),
        "defaultShippingDetails" to defaultShippingDetails?.toDictionary(),
        "allowsDelayedPaymentMethods" to allowsDelayedPaymentMethods,
//        "appearance" to appearance?.toDictionary(),
        "primaryButtonLabel" to primaryButtonLabel,
        "removeSavedPaymentMethodMessage" to removeSavedPaymentMethodMessage,
        "preferredNetworks" to preferredNetworks?.map { it.toDictionary() },
        "paymentMethodOrder" to paymentMethodOrder,
        "allowsRemovalOfLastSavedPaymentMethod" to allowsRemovalOfLastSavedPaymentMethod,
        "paymentIntentClientSecret" to paymentIntentClientSecret
    )
}

// Enum for style types
enum class Style {
    ALWAYS_LIGHT, ALWAYS_DARK, AUTOMATIC;

    fun toDictionary() = name.lowercase()
}

// Placeholder data classes for associated types with toDictionary methods
//data class ApplePayParams(
//
//) {
//    fun toDictionary(): Map<String, Any?> = mapOf()
//}

//data class GooglePayParams(
//    // Define properties as per your requirements
//) {
//    fun toDictionary(): Map<String, Any?> = mapOf()
//}

enum class CollectionMode(val value: String) {
    AUTOMATIC("automatic"),
    NEVER("never"),
    ALWAYS("always")
}

enum class AddressCollectionMode(val value: String) {
    AUTOMATIC("automatic"),
    NEVER("never"),
    FULL("full")
}

data class BillingDetailsCollectionConfiguration(
    val name: CollectionMode = CollectionMode.AUTOMATIC,
    val phone: CollectionMode = CollectionMode.AUTOMATIC,
    val email: CollectionMode = CollectionMode.AUTOMATIC,
    val address: AddressCollectionMode = AddressCollectionMode.AUTOMATIC,
    val attachDefaultsToPaymentMethod: Boolean = false
) {
    fun toDictionary(): Map<String, Any?> = mapOf(
        "name" to name.value,
        "phone" to phone.value,
        "email" to email.value,
        "address" to address.value,
        "attachDefaultsToPaymentMethod" to attachDefaultsToPaymentMethod
    )
}

data class PresentOptions(
    val timeout: Long? = null
) {
    fun toDictionary() = mapOf(
        "timeout" to timeout
    )

}

data class CardBrand(
    val brandName: String // Example placeholder
) {
    fun toDictionary() = mapOf("brandName" to brandName)
}
