package model

/**
 * Sealed class representing different types of payment errors.
 * Provides type-safe error handling for payment operations.
 */
sealed class PaymentError(open val message: String) {
    /**
     * Error when token is missing for card payment
     */
    object TokenMissing : PaymentError("Token is required for card payment")
    
    /**
     * Error when bank name is missing for bank-based payments (Ideal, FPX, NetBanking)
     */
    object BankNameMissing : PaymentError("Bank name is required")
    
    /**
     * Error when VPA (Virtual Payment Address) is missing for UPI payment
     */
    object VPAMissing : PaymentError("VPA is required for UPI payment")
    
    /**
     * Error when IBAN is missing for SEPA Debit payment
     */
    object IBANMissing : PaymentError("IBAN is required for SEPA Debit payment")
    
    /**
     * Error when BSB Number is missing for AU BECS Debit payment
     */
    object BSBNumberMissing : PaymentError("BSB Number is required for AU BECS Debit payment")
    
    /**
     * Error when account number is missing
     */
    object AccountNumberMissing : PaymentError("Account Number is required")
    
    /**
     * Error when sort code is missing for Bacs Debit payment
     */
    object SortCodeMissing : PaymentError("Sort Code is required for Bacs Debit payment")
    
    /**
     * Error when country is missing for Sofort payment
     */
    object CountryMissing : PaymentError("Country is required for Sofort payment")
    
    /**
     * Error when billing details are missing
     */
    object BillingDetailsMissing : PaymentError("Billing details are required")
    
    /**
     * Error when name is missing from billing details
     */
    object NameMissing : PaymentError("Name is required")
    
    /**
     * Error when email is missing from billing details
     */
    object EmailMissing : PaymentError("Email is required")
    
    /**
     * Error when payment method ID is missing
     */
    object PaymentMethodIdMissing : PaymentError("Payment Method ID is required")
    
    /**
     * Error when BLIK code is missing for BLIK payment
     */
    object BlikCodeMissing : PaymentError("BLIK Code is required for BLIK payment")
    
    /**
     * Error when App ID is missing for WeChat Pay
     */
    object AppIdMissing : PaymentError("App ID is required for WeChat Pay")
    
    /**
     * Error when Google Pay JSON data is missing
     */
    object GooglePayJsonMissing : PaymentError("Google Pay JSON data is required")
    
    /**
     * Error when routing number is missing for US Bank Account
     */
    object RoutingNumberMissing : PaymentError("Routing Number is required for US Bank Account")
    
    /**
     * Generic error with custom message
     */
    data class GenericError(override val message: String) : PaymentError(message)
    
    /**
     * Converts the error to a user-friendly string representation
     */
    fun toUserMessage(): String = message
    
    /**
     * Converts the error to a map for API responses
     */
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "error" to this::class.simpleName,
            "message" to message
        )
    }
}