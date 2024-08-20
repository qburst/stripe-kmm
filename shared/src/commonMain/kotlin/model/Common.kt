package model

data class Address(
    val city: String? = null,
    val country: String? = null,
    val line1: String? = null,
    val line2: String? = null,
    val postalCode: String? = null,
    val state: String?  = null,
) {
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


// Data class for BillingDetails

data class BillingDetails(
    val email: String? = null,
    val phone: String? = null,
    val name: String? = null,
    val address: Address? = null
) {
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
            "email" to email,
            "phone" to phone,
            "name" to name,
            "address" to address?.toDictionary()
        )
    }
}