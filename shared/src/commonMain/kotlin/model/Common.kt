package model

data class Address(
    val city: String? = null,
    val country: String? = null,
    val line1: String? = null,
    val line2: String? = null,
    val postalCode: String? = null,
    val state: String?  = null,
) {
    /**
     * Converts the Address object to a dictionary representation.
     *
     * @return A map containing address fields as key-value pairs.
     */
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
            "city" to city,
            "country" to country,
            "line1" to line1,
            "line2" to line2,
            "postalCode" to postalCode,
            "state" to state
        )
    }
}


data class AddressDetails(
    val name: String? = null,
    val address: Address? = null,
    val phone: String? = null,
    val isCheckboxSelected: Boolean? = null,

) {
    /**
     * Converts the AddressDetails object to a dictionary representation.
     *
     * @return A map containing address details fields as key-value pairs.
     */
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "address" to address?.toDictionary(),
            "phone" to phone,
            "isCheckboxSelected" to isCheckboxSelected
        )
    }
}

/**
 * Data class representing billing details, which can also be used as shipping details.
 */
data class BillingDetails(
    val email: String? = null,
    val phone: String? = null,
    val name: String? = null,
    val address: Address? = null
) {
    /**
     * Converts the BillingDetails object to a dictionary representation.
     *
     * @return A map containing billing details fields as key-value pairs.
     */
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
            "email" to email,
            "phone" to phone,
            "name" to name,
            "address" to address?.toDictionary()
        )
    }
}

/**
 * Alias for BillingDetails to represent shipping details.
 */
typealias ShippingDetails = BillingDetails
