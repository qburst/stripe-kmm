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
                "paymentMethodData" to paymentMethodData.toDictionary()
            )
        }
    }

    // Represents IdealParams
    data class IdealParams(
        val paymentMethodType: String = "Ideal",
        val paymentMethodData: PaymentMethodDataIdeal?,
    ) : CreateParams() {

        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary(),
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
        val paymentMethodData: PaymentMethodDataEps?,

    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary(),
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

    data class BacsDebitParams(
        val paymentMethodType: String = "BacsDebit",
        val paymentMethodData: PaymentMethodDataBacsDebit?
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

    data class UpiParams(
        val paymentMethodType: String = "upi",
        val paymentMethodData: PaymentMethodDataUpi?
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    data class NetBankingParams(
        val paymentMethodType: String = "netbanking",
        val paymentMethodData: PaymentMethodDataNetBanking?
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

    data class BlikParams(
        val paymentMethodType: String = "Blik",  // Constant value
        val paymentMethodData: PaymentMethodDataBlik? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    data class WeChatPayParams(
        val paymentMethodType: String = "WeChatPay",  // Constant value
        val paymentMethodData: PaymentMethodDataWeChatPay? = null
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

    data class MultiBancoParams(
        val paymentMethodType: String = "Multibanco",  // Constant value
        val paymentMethodData: PaymentMethodDataMultiBanco? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    data class AlmaParams(
        val paymentMethodType: String = "Alma",  // Constant value
        val paymentMethodData: PaymentMethodDataAlma? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }
    data class PaymentMethodDataAlma(
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class SunbitParams(
        val paymentMethodType: String = "Sunbit",  // Constant value
        val paymentMethodData: PaymentMethodDataSunbit? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }
    data class PaymentMethodDataSunbit(
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class BillieParams(
        val paymentMethodType: String = "Billie",  // Constant value
        val paymentMethodData: PaymentMethodDataBillie? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }
    data class PaymentMethodDataBillie(
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class SatispayParams(
        val paymentMethodType: String = "Satispay",  // Constant value
        val paymentMethodData: PaymentMethodDataSatispay? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }
    data class PaymentMethodDataSatispay(
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class RevolutPayParams(
        val paymentMethodType: String = "RevolutPay",  // Constant value
        val paymentMethodData: PaymentMethodDataRevolutPay? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }
    data class PaymentMethodDataRevolutPay(
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class MobilePayParams(
        val paymentMethodType: String = "MobilePay",  // Constant value
        val paymentMethodData: PaymentMethodDataMobilePay? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }
    data class PaymentMethodDataMobilePay(
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class AmazonPayParams(
        val paymentMethodType: String = "AmazonPay",  // Constant value
        val paymentMethodData: PaymentMethodDataAmazonPay? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    data class GooglePayParams(
        val paymentMethodType: String = "GooglePay",  // Constant value
        val jsonObject: Any? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to jsonObject
            )
        }
    }

    data class SwishParams(
        val paymentMethodType: String = "swish",  // Constant value
        val paymentMethodData: PaymentMethodDataSwish? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "paymentMethodType" to paymentMethodType,
                "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    data class PaymentMethodDataSwish(
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
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
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "bankName" to bankName,
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }


    // Data class for OxxoParams paymentMethodData
    data class PaymentMethodDataOxxo(
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class PaymentMethodDataP24(
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class PaymentMethodDataAlipay(
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
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
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "iban" to iban,
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class PaymentMethodDataEps(
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class PaymentMethodDataAuBecs(
        val formDetails: FormDetails?,
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "formDetails" to formDetails?.toDictionary(),
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class PaymentMethodDataBacsDebit(
        val bacsDebit: BacsDetails?,
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "bacsDebit" to bacsDebit?.toDictionary(),
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class BacsDetails(
        val accountNumber: String,
        val sortCode: String
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "accountNumber" to accountNumber,
                "sortCode" to sortCode
            )
        }
    }


    data class PaymentMethodDataSofort(
        val country: String?,
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "country" to country,
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class PaymentMethodDataUpi(
        val vpa: String?,
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "vpa" to vpa,
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class PaymentMethodDataNetBanking(
        val bank: String?,
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "bank" to bank,
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
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
        val testOfflineBank: Boolean?,
        val bankName: String?,
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "testOfflineBank" to testOfflineBank,
                "bankName" to bankName,
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }


    data class FormDetails(
        val accountNumber: String,
        val bsbNumber: String,
        val email: String?,
        val name: String?
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
        val shippingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails.toDictionary(),
                "shippingDetails" to shippingDetails?.toDictionary(),
                "metadata" to metadata
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
        val billingDetails: BillingDetails?, // Custom class to reflect Pick behavior
        val shippingDetails: ShippingDetails?,
        val metadata: Map<String, String>? = null

    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "shippingDetails" to shippingDetails?.toDictionary(),
                "metadata" to metadata
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
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }


    data class PaymentMethodDataUSBankAccount(
        val billingDetails: USBillingDetails,  // Custom class to enforce `name` as required
        val accountNumber: String,
        val routingNumber: String,
        val linkAccountSessionId: String?,
        val accountHolderType: BankAccountHolderType? = BankAccountHolderType.INDIVIDUAL, // Default to Individual
        val accountType: BankAccountType? = BankAccountType.CHECKING,  // Default to Checking
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails.toDictionary(),
                "accountNumber" to accountNumber,
                "routingNumber" to routingNumber,
                "linkAccountSessionId" to linkAccountSessionId,
                "accountHolderType" to accountHolderType?.name,
                "accountType" to accountType?.name,
                "metadata" to metadata
            )
        }
    }


    data class PaymentMethodDataPayPal(
        val billingDetails: BillingDetails,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class PaymentMethodDataWeChatPay(
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class PaymentMethodDataBlik(
        val billingDetails: BillingDetails?,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
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
        INDIVIDUAL,
        UNKNOWN
    }

    enum class BankAccountType {
        CHECKING,
        SAVINGS,
        UNKNOWN
    }

    data class PaymentMethodDataAffirm(
        val shippingDetails: ShippingDetails? = null,
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "shippingDetails" to shippingDetails?.toDictionary(),
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class PaymentMethodDataCashApp(
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class PaymentMethodDataMultiBanco(
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
            )
        }
    }

    data class PaymentMethodDataAmazonPay(
        val billingDetails: BillingDetails? = null,
        val metadata: Map<String, String>? = null
    ) {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                "billingDetails" to billingDetails?.toDictionary(),
                "metadata" to metadata
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
