import model.Address
import model.BillingDetails
import model.FutureUsage
import model.ShippingDetails

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
    ) : CreateParams() {

        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    // Represents OxxoParams
    data class OxxoParams(
        val paymentMethodType: String = "Oxxo",
        val paymentMethodData: PaymentMethodDataOxxo?
    ) : CreateParams() {

        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    // Represents OxxoParams
    data class P24Params(
        val paymentMethodType: String = "P24",
        val paymentMethodData: PaymentMethodDataP24?
    ) : CreateParams() {

        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    // Represents OxxoParams
    data class AlipayParams(
        val paymentMethodType: String = "Alipay",
        val paymentMethodData: PaymentMethodDataAlipay? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    data class GiropayParams(
        val paymentMethodType: String = "Giropay",
        val paymentMethodData: PaymentMethodDataGiropay? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }


    data class SepaDebitParams(
        val paymentMethodType: String = "SepaDebit",
        val paymentMethodData: PaymentMethodDataSepaDebit?
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }


    data class EpsDebitParams(
        val paymentMethodType: String = "Eps",
        val paymentMethodData: PaymentMethodDataEps?
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    data class AuBecsDebitParams(
        val paymentMethodType: String = "AuBecsDebit",
        val paymentMethodData: PaymentMethodDataAuBecs?
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }


    data class SofortParams(
        val paymentMethodType: String = "Sofort",
        val paymentMethodData: PaymentMethodDataSofort?
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }


    data class GrabPayParams(
        val paymentMethodType: String = "GrabPay",
        val paymentMethodData: PaymentMethodDataGrabPay?
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    data class FPXParams(
        val paymentMethodType: String = "Fpx",
        val paymentMethodData: PaymentMethodDataFPX?
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    data class AfterpayClearpayParams(
        val paymentMethodType: String = "AfterpayClearpay",
        val paymentMethodData: PaymentMethodDataAfterpayClearpay?
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }


    data class KlarnaParams(
        val paymentMethodType: String = "Klarna",
        val paymentMethodData: PaymentMethodDataKlarna?
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }


    data class BancontactParams(
        val paymentMethodType: String = "Bancontact",
        val paymentMethodData: BancontactDataParams
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData.toDictionary()
            )
        }
    }

    data class USBankAccountParams(
        val paymentMethodType: String = "USBankAccount",  // Constant value
        val paymentMethodData: PaymentMethodDataUSBankAccount? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }


    data class PayPalParams(
        val paymentMethodType: String = "PayPal",  // Constant value
        val paymentMethodData: PaymentMethodDataPayPal? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    data class AffirmParams(
        val paymentMethodType: String = "Affirm",  // Constant value
        val paymentMethodData: PaymentMethodDataAffirm? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }


    data class CashAppParams(
        val paymentMethodType: String = "CashApp",  // Constant value
        val paymentMethodData: PaymentMethodDataCashApp? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }


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
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "bankName" to bankName,
                "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }


    // Data class for OxxoParams paymentMethodData
    data class PaymentMethodDataOxxo(
        val billingDetails: BillingDetails?
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }

    data class PaymentMethodDataP24(
        val billingDetails: BillingDetails?
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }

    data class PaymentMethodDataAlipay(
        val billingDetails: BillingDetails?
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }


    data class PaymentMethodDataGiropay(
        val billingDetails: BillingDetails?
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }

    data class PaymentMethodDataSepaDebit(
        val iban: String?,
        val billingDetails: BillingDetails?
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "iban" to iban,
                "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }

    data class PaymentMethodDataEps(
        val billingDetails: BillingDetails?
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }

    data class PaymentMethodDataAuBecs(
        val formDetails: FormDetails?
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "formDetails" to formDetails?.toDictionary()
            )
        }
    }


    data class PaymentMethodDataSofort(
        val country: String?,
        val billingDetails: BillingDetails?
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "country" to country,
                "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }

    data class PaymentMethodDataGrabPay(
        val billingDetails: BillingDetails? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }

    data class PaymentMethodDataFPX(
        val testOfflineBank: Boolean?
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "testOfflineBank" to testOfflineBank
            )
        }
    }


    data class FormDetails(
        val accountNumber: String,
        val bsbNumber: String,
        val email: String,
        val name: String
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "accountNumber" to accountNumber,
                "bsbNumber" to bsbNumber,
                "email" to email,
                "name" to name
            )
        }
    }

    data class PaymentMethodDataKlarna(
        val billingDetails: KlarnaBillingDetails, // Custom class to reflect Pick behavior
        val shippingDetails: BillingDetails? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails.toDictionary(),
                "shippingDetails" to shippingDetails?.toDictionary()
            )
        }
    }


    data class KlarnaBillingDetails(
        val email: String, // Required from BillingDetails
        val address: KlarnaAddress, // Custom address class to reflect country pick
        val phone: String? = null, // Other fields from BillingDetails
        val name: String? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "email" to email,
                "address" to address.toDictionary(),
                "phone" to phone,
                "name" to name
            )
        }
    }


    data class PaymentMethodDataAfterpayClearpay(
        val billingDetails: BillingDetails, // Custom class to reflect Pick behavior
        val shippingDetails: ShippingDetails

    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails.toDictionary(),
                "shippingDetails" to shippingDetails.toDictionary()
            )
        }
    }

    data class KlarnaAddress(
        val city: String? = null,
        val country: String? = null,
        val line1: String? = null,
        val line2: String? = null,
        val postalCode: String? = null,
        val state: String? = null
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


    data class BancontactDataParams(
        val billingDetails: BillingDetails
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails.toDictionary()
            )
        }
    }


    data class PaymentMethodDataUSBankAccount(
        val billingDetails: USBillingDetails,  // Custom class to enforce `name` as required
        val accountNumber: String,
        val routingNumber: String,
        val accountHolderType: BankAccountHolderType? = BankAccountHolderType.INDIVIDUAL, // Default to Individual
        val accountType: BankAccountType? = BankAccountType.CHECKING  // Default to Checking
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails.toDictionary(),
                "accountNumber" to accountNumber,
                "routingNumber" to routingNumber,
                "accountHolderType" to accountHolderType?.name,
                "accountType" to accountType?.name
            )
        }
    }


    data class PaymentMethodDataPayPal(
        val billingDetails: BillingDetails
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails.toDictionary()
            )
        }
    }


    // Custom class to ensure that `name` is required
    data class USBillingDetails(
        val name: String,  // Required field
        val phone: String? = null,
        val email: String? = null,
        val address: Address? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "name" to name,
                "phone" to phone,
                "email" to email,
                "address" to address?.toDictionary()
            )
        }
    }

    enum class BankAccountHolderType {
        COMPANY,
        INDIVIDUAL
    }

    enum class BankAccountType {
        CHECKING,
        SAVINGS
    }

    data class PaymentMethodDataAffirm(
        val shippingDetails: ShippingDetails? = null,
        val billingDetails: BillingDetails? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "shippingDetails" to shippingDetails?.toDictionary(),
                "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }

    data class PaymentMethodDataCashApp(
        val billingDetails: BillingDetails? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }

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
