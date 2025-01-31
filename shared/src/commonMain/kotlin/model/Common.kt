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


data class AddressDetails(
    val name: String? = null,
    val address: Address? = null,
    val phone: String? = null,
    val isCheckboxSelected: Boolean? = null,

) {
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "address" to address.toString(),
            "phone" to phone,
            "isCheckboxSelected" to isCheckboxSelected
        )
    }
}



//export type AddressDetails = {
//    /** The customer's full name. */
//    name?: string;
//    /** The customer's address. */
//    address?: Address;
//    /** The customer's phone number. */
//    phone?: string;
//    /** Whether or not the checkbox is initally selected. Defaults to false.
//     *  Note: The checkbox is displayed below the other fields when additionalFields.checkboxLabel is set.
//     *  */
//    isCheckboxSelected?: boolean;
//};



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

typealias ShippingDetails = BillingDetails