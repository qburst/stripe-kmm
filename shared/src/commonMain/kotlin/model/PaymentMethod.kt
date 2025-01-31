import model.Address
import model.BillingDetails
import model.FutureUsage
import model.ShippingDetails

// Sealed class to represent the possible types for CreateParams
/** Sealed class representing different types of parameters for creating payment methods. */
sealed class CreateParams {

    /**
     * Represents CardParams with optional token.
     *
     * @property paymentMethodType The type of payment method (default is "Card").
     * @property paymentMethodData The payment method data, which may contain a token.
     */
    data class CardParamsWithToken(
            val paymentMethodType: String = "Card",
            val paymentMethodData: PaymentMethodDataWithToken?
    ) : CreateParams() {

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
     * Represents CardParams with paymentMethodId.
     *
     * @property paymentMethodType The type of payment method (default is "Card").
     * @property paymentMethodData The payment method data, which contains a paymentMethodId.
     */
    data class CardParamsWithPaymentId(
            val paymentMethodType: String = "Card",
            val paymentMethodData: PaymentMethodDataWithPaymentId
    ) : CreateParams() {

        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the payment method type and data.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "paymentMethodType" to paymentMethodType,
                    "paymentMethodData" to paymentMethodData.toDictionary()
            )
        }
    }

    /**
     * Represents IdealParams.
     *
     * @property paymentMethodType The type of payment method (default is "Ideal").
     * @property paymentMethodData The payment method data for Ideal.
     */
    data class IdealParams(
            val paymentMethodType: String = "Ideal",
            val paymentMethodData: PaymentMethodDataIdeal?,
    ) : CreateParams() {

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
     * Represents OxxoParams.
     *
     * @property paymentMethodType The type of payment method (default is "Oxxo").
     * @property paymentMethodData The payment method data for Oxxo.
     */
    data class OxxoParams(
            val paymentMethodType: String = "Oxxo",
            val paymentMethodData: PaymentMethodDataOxxo?
    ) : CreateParams() {

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
     * Represents P24Params.
     *
     * @property paymentMethodType The type of payment method (default is "P24").
     * @property paymentMethodData The payment method data for P24.
     */
    data class P24Params(
            val paymentMethodType: String = "P24",
            val paymentMethodData: PaymentMethodDataP24?
    ) : CreateParams() {

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
     * Represents AlipayParams.
     *
     * @property paymentMethodType The type of payment method (default is "Alipay").
     * @property paymentMethodData The payment method data for Alipay.
     */
    data class AlipayParams(
            val paymentMethodType: String = "Alipay",
            val paymentMethodData: PaymentMethodDataAlipay? = null
    ) : CreateParams() {

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
     * Represents GiropayParams.
     *
     * @property paymentMethodType The type of payment method (default is "Giropay").
     * @property paymentMethodData The payment method data for Giropay.
     */
    data class GiropayParams(
            val paymentMethodType: String = "Giropay",
            val paymentMethodData: PaymentMethodDataGiropay? = null
    ) : CreateParams() {

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
     * Represents SepaDebitParams.
     *
     * @property paymentMethodType The type of payment method (default is "SepaDebit").
     * @property paymentMethodData The payment method data for SepaDebit.
     */
    data class SepaDebitParams(
            val paymentMethodType: String = "SepaDebit",
            val paymentMethodData: PaymentMethodDataSepaDebit?
    ) : CreateParams() {

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
     * Represents EpsDebitParams.
     *
     * @property paymentMethodType The type of payment method (default is "Eps").
     * @property paymentMethodData The payment method data for Eps.
     */
    data class EpsDebitParams(
            val paymentMethodType: String = "Eps",
            val paymentMethodData: PaymentMethodDataEps?,
    ) : CreateParams() {

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
     * Represents AuBecsDebitParams.
     *
     * @property paymentMethodType The type of payment method (default is "AuBecsDebit").
     * @property paymentMethodData The payment method data for AuBecsDebit.
     */
    data class AuBecsDebitParams(
            val paymentMethodType: String = "AuBecsDebit",
            val paymentMethodData: PaymentMethodDataAuBecs?
    ) : CreateParams() {

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
     * Represents BacsDebitParams.
     *
     * @property paymentMethodType The type of payment method (default is "BacsDebit").
     * @property paymentMethodData The payment method data for BacsDebit.
     */
    data class BacsDebitParams(
            val paymentMethodType: String = "BacsDebit",
            val paymentMethodData: PaymentMethodDataBacsDebit?
    ) : CreateParams() {

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
     * Represents SofortParams.
     *
     * @property paymentMethodType The type of payment method (default is "Sofort").
     * @property paymentMethodData The payment method data for Sofort.
     */
    data class SofortParams(
            val paymentMethodType: String = "Sofort",
            val paymentMethodData: PaymentMethodDataSofort?
    ) : CreateParams() {

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
     * Represents UPIParams.
     *
     * @property paymentMethodType The type of payment method (default is "upi").
     * @property paymentMethodData The payment method data for UPI.
     */
    data class UpiParams(
            val paymentMethodType: String = "upi",
            val paymentMethodData: PaymentMethodDataUpi?
    ) : CreateParams() {

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
     * Represents NetBankingParams.
     *
     * @property paymentMethodType The type of payment method (default is "netbanking").
     * @property paymentMethodData The payment method data for NetBanking.
     */
    data class NetBankingParams(
            val paymentMethodType: String = "netbanking",
            val paymentMethodData: PaymentMethodDataNetBanking?
    ) : CreateParams() {

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
     * Represents GrabPayParams.
     *
     * @property paymentMethodType The type of payment method (default is "GrabPay").
     * @property paymentMethodData The payment method data for GrabPay.
     */
    data class GrabPayParams(
            val paymentMethodType: String = "GrabPay",
            val paymentMethodData: PaymentMethodDataGrabPay?
    ) : CreateParams() {

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
     * Represents FPXParams.
     *
     * @property paymentMethodType The type of payment method (default is "Fpx").
     * @property paymentMethodData The payment method data for FPX.
     */
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

    /**
     * Represents GrabPayParams.
     *
     * @property paymentMethodType The type of payment method (default is "GrabPay").
     * @property paymentMethodData The payment method data for GrabPay.
     */
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

    /**
     * Represents KlarnaParams.
     *
     * @property paymentMethodType The type of payment method (default is "Klarna").
     * @property paymentMethodData The payment method data for Klarna.
     */
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

    /**
     * Represents BancontactParams.
     *
     * @property paymentMethodType The type of payment method (default is "Bancontact").
     * @property paymentMethodData The payment method data for Bancontact.
     */
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

    /**
     * Represents USBankAccountParams.
     *
     * @property paymentMethodType The type of payment method (default is "USBankAccount").
     * @property paymentMethodData The payment method data for USBankAccount.
     */
    data class USBankAccountParams(
            val paymentMethodType: String = "USBankAccount", // Constant value
            val paymentMethodData: PaymentMethodDataUSBankAccount? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "paymentMethodType" to paymentMethodType,
                    "paymentMethodData" to paymentMethodData?.toDictionary()
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
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "paymentMethodType" to paymentMethodType,
                    "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    /**
     * Represents BlikParams.
     *
     * @property paymentMethodType The type of payment method (default is "Blik").
     * @property paymentMethodData The payment method data for Blik.
     */
    data class BlikParams(
            val paymentMethodType: String = "Blik", // Constant value
            val paymentMethodData: PaymentMethodDataBlik? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "paymentMethodType" to paymentMethodType,
                    "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    /**
     * Represents WeChatPayParams.
     *
     * @property paymentMethodType The type of payment method (default is "WeChatPay").
     * @property paymentMethodData The payment method data for WeChatPay.
     */
    data class WeChatPayParams(
            val paymentMethodType: String = "WeChatPay", // Constant value
            val paymentMethodData: PaymentMethodDataWeChatPay? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "paymentMethodType" to paymentMethodType,
                    "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    /**
     * Represents AffirmParams.
     *
     * @property paymentMethodType The type of payment method (default is "Affirm").
     * @property paymentMethodData The payment method data for Affirm.
     */
    data class AffirmParams(
            val paymentMethodType: String = "Affirm", // Constant value
            val paymentMethodData: PaymentMethodDataAffirm? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "paymentMethodType" to paymentMethodType,
                    "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    /**
     * Represents CashAppParams.
     *
     * @property paymentMethodType The type of payment method (default is "CashApp").
     * @property paymentMethodData The payment method data for CashApp.
     */
    data class CashAppParams(
            val paymentMethodType: String = "CashApp", // Constant value
            val paymentMethodData: PaymentMethodDataCashApp? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "paymentMethodType" to paymentMethodType,
                    "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    /**
     * Represents MultibancoParams.
     *
     * @property paymentMethodType The type of payment method (default is "Multibanco").
     * @property paymentMethodData The payment method data for Multibanco.
     */
    data class MultiBancoParams(
            val paymentMethodType: String = "Multibanco", // Constant value
            val paymentMethodData: PaymentMethodDataMultiBanco? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "paymentMethodType" to paymentMethodType,
                    "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    /**
     * Represents AlmaParams.
     *
     * @property paymentMethodType The type of payment method (default is "Alma").
     * @property paymentMethodData The payment method data for Alma.
     */
    data class AlmaParams(
            val paymentMethodType: String = "Alma", // Constant value
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
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents SunbitParams.
     *
     * @property paymentMethodType The type of payment method (default is "Sunbit").
     * @property paymentMethodData The payment method data for Sunbit.
     */
    data class SunbitParams(
            val paymentMethodType: String = "Sunbit", // Constant value
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
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents BillieParams.
     *
     * @property paymentMethodType The type of payment method (default is "Billie").
     * @property paymentMethodData The payment method data for Billie.
     */
    data class BillieParams(
            val paymentMethodType: String = "Billie", // Constant value
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
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents SatispayParams.
     *
     * @property paymentMethodType The type of payment method (default is "Satispay").
     * @property paymentMethodData The payment method data for Satispay.
     */
    data class SatispayParams(
            val paymentMethodType: String = "Satispay", // Constant value
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
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents RevolutPayParams.
     *
     * @property paymentMethodType The type of payment method (default is "RevolutPay").
     * @property paymentMethodData The payment method data for RevolutPay.
     */
    data class RevolutPayParams(
            val paymentMethodType: String = "RevolutPay", // Constant value
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
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents MobilePayParams.
     *
     * @property paymentMethodType The type of payment method (default is "MobilePay").
     * @property paymentMethodData The payment method data for MobilePay.
     */
    data class MobilePayParams(
            val paymentMethodType: String = "MobilePay", // Constant value
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
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents AmazonPayParams.
     *
     * @property paymentMethodType The type of payment method (default is "AmazonPay").
     * @property paymentMethodData The payment method data for AmazonPay.
     */
    data class AmazonPayParams(
            val paymentMethodType: String = "AmazonPay", // Constant value
            val paymentMethodData: PaymentMethodDataAmazonPay? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "paymentMethodType" to paymentMethodType,
                    "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }

    /**
     * Represents GooglePayParams.
     *
     * @property paymentMethodType The type of payment method (default is "GooglePay").
     * @property paymentMethodData The payment method data for GooglePay.
     */
    data class GooglePayParams(
            val paymentMethodType: String = "GooglePay", // Constant value
            val jsonObject: Any? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "paymentMethodType" to paymentMethodType,
                    "paymentMethodData" to jsonObject
            )
        }
    }

    /**
     * Represents SwishParams.
     *
     * @property paymentMethodType The type of payment method (default is "swish").
     * @property paymentMethodData The payment method data for Swish.
     */
    data class SwishParams(
            val paymentMethodType: String = "swish", // Constant value
            val paymentMethodData: PaymentMethodDataSwish? = null
    ) : CreateParams() {
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "paymentMethodType" to paymentMethodType,
                    "paymentMethodData" to paymentMethodData?.toDictionary()
            )
        }
    }
    /**
     * Represents payment method data for Swish.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataSwish(
            val billingDetails: BillingDetails? = null,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents payment method data with a token.
     *
     * @property token The token for the payment method (optional).
     * @property billingDetails The billing details associated with the payment method (optional).
     */
    data class PaymentMethodDataWithToken(
            val token: String? = null,
            val billingDetails: BillingDetails? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the token and billing details.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("token" to token, "billingDetails" to billingDetails?.toDictionary())
        }
    }

    /**
     * Represents payment method data with a payment method ID.
     *
     * @property paymentMethodId The unique identifier for the payment method.
     * @property cvc The card verification code (optional).
     * @property billingDetails The billing details associated with the payment method (optional).
     */
    data class PaymentMethodDataWithPaymentId(
            val paymentMethodId: String,
            val cvc: String? = null,
            val billingDetails: BillingDetails? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the payment method ID, CVC, and billing details.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "paymentMethodId" to paymentMethodId,
                    "cvc" to cvc,
                    "billingDetails" to billingDetails?.toDictionary()
            )
        }
    }

    /**
     * Represents payment method data for Ideal.
     *
     * @property bankName The name of the bank (optional).
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataIdeal(
            val bankName: String?,
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the bank name, billing details, and metadata.
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
     * Represents payment method data for Oxxo.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataOxxo(
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents payment method data for P24.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataP24(
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents payment method data for Alipay.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataAlipay(
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents payment method data for Giropay.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     */
    data class PaymentMethodDataGiropay(val billingDetails: BillingDetails?) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary())
        }
    }

    /**
     * Represents payment method data for SEPA Debit.
     *
     * @property iban The International Bank Account Number (IBAN) for the payment method
     * (optional).
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataSepaDebit(
            val iban: String?,
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the IBAN, billing details, and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "iban" to iban,
                    "billingDetails" to billingDetails?.toDictionary(),
                    "metadata" to metadata
            )
        }
    }

    /**
     * Represents payment method data for EPS.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataEps(
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents payment method data for AU BECS Direct Debit.
     *
     * @property formDetails The form details for the payment method (optional).
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataAuBecs(
            val formDetails: FormDetails?,
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the form details, billing details, and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "formDetails" to formDetails?.toDictionary(),
                    "billingDetails" to billingDetails?.toDictionary(),
                    "metadata" to metadata
            )
        }
    }

    /**
     * Represents payment method data for BACS Direct Debit.
     *
     * @property bacsDebit The BACS-specific details for the payment method (optional).
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataBacsDebit(
            val bacsDebit: BacsDetails?,
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the BACS debit details, billing details, and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "bacsDebit" to bacsDebit?.toDictionary(),
                    "billingDetails" to billingDetails?.toDictionary(),
                    "metadata" to metadata
            )
        }
    }

    /**
     * Represents details for a BACS Direct Debit payment.
     *
     * @property accountNumber The account number for the direct debit.
     * @property sortCode The sort code for the bank account.
     */
    data class BacsDetails(val accountNumber: String, val sortCode: String) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the account number and sort code.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("accountNumber" to accountNumber, "sortCode" to sortCode)
        }
    }

    /**
     * Represents payment method data for Sofort.
     *
     * @property country The country code where the payment method is used (optional).
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataSofort(
            val country: String?,
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the country, billing details, and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "country" to country,
                    "billingDetails" to billingDetails?.toDictionary(),
                    "metadata" to metadata
            )
        }
    }

    /**
     * Represents payment method data for UPI (Unified Payments Interface).
     *
     * @property vpa The Virtual Payment Address (VPA) for the payment method (optional).
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
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
     * Represents payment method data for Net Banking.
     *
     * @property bank The name or identifier of the bank (optional).
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataNetBanking(
            val bank: String?,
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the bank, billing details, and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "bank" to bank,
                    "billingDetails" to billingDetails?.toDictionary(),
                    "metadata" to metadata
            )
        }
    }

    /**
     * Represents payment method data for GrabPay.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     */
    data class PaymentMethodDataGrabPay(val billingDetails: BillingDetails? = null) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary())
        }
    }

    /**
     * Represents payment method data for FPX (Financial Process Exchange).
     *
     * @property testOfflineBank A flag indicating whether the bank is offline for testing
     * (optional).
     * @property bankName The name of the bank (optional).
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataFPX(
            val testOfflineBank: Boolean?,
            val bankName: String?,
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the test offline bank flag, bank name, billing details, and
         * metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "testOfflineBank" to testOfflineBank,
                    "bankName" to bankName,
                    "billingDetails" to billingDetails?.toDictionary(),
                    "metadata" to metadata
            )
        }
    }

    /**
     * Represents form details required for payment methods such as AU BECS Direct Debit.
     *
     * @property accountNumber The account number associated with the form.
     * @property bsbNumber The Bank State Branch (BSB) number associated with the form.
     * @property email The email address associated with the account (optional).
     * @property name The name associated with the account (optional).
     */
    data class FormDetails(
            val accountNumber: String,
            val bsbNumber: String,
            val email: String?,
            val name: String?
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the account number, BSB number, email, and name.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "accountNumber" to accountNumber,
                    "bsbNumber" to bsbNumber,
                    "email" to email,
                    "name" to name
            )
        }
    }

    /**
     * Represents payment method data for Klarna.
     *
     * @property billingDetails The billing details associated with the payment method.
     * @property shippingDetails The shipping details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataKlarna(
            val billingDetails: KlarnaBillingDetails, // Custom class to reflect Pick behavior
            val shippingDetails: BillingDetails? = null,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details, shipping details, and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "billingDetails" to billingDetails.toDictionary(),
                    "shippingDetails" to shippingDetails?.toDictionary(),
                    "metadata" to metadata
            )
        }
    }

    /**
     * Represents the billing details specific to Klarna.
     *
     * @property email The email associated with the billing details (required).
     * @property address The address associated with the billing details, represented by a custom
     * KlarnaAddress class.
     * @property phone The phone number associated with the billing details (optional).
     * @property name The name associated with the billing details (optional).
     */
    data class KlarnaBillingDetails(
            val email: String, // Required from BillingDetails
            val address: KlarnaAddress, // Custom address class to reflect country pick
            val phone: String? = null, // Other fields from BillingDetails
            val name: String? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the email, address, phone, and name.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "email" to email,
                    "address" to address.toDictionary(),
                    "phone" to phone,
                    "name" to name
            )
        }
    }

    /**
     * Represents the address details specific to Klarna.
     *
     * @property city The city of the address (optional).
     * @property country The country of the address (optional).
     * @property line1 The first line of the address (optional).
     * @property line2 The second line of the address (optional).
     * @property postalCode The postal code of the address (optional).
     * @property state The state or province of the address (optional).
     */
    data class KlarnaAddress(
            val city: String? = null,
            val country: String? = null,
            val line1: String? = null,
            val line2: String? = null,
            val postalCode: String? = null,
            val state: String? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the address fields (city, country, etc.).
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

    /**
     * Represents payment method data for Afterpay/Clearpay.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property shippingDetails The shipping details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataAfterpayClearpay(
            val billingDetails: BillingDetails?, // Custom class to reflect Pick behavior
            val shippingDetails: ShippingDetails?,
            val metadata: Map<String, String>? = null
    ) : CreateParams() {

        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details, shipping details, and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "billingDetails" to billingDetails?.toDictionary(),
                    "shippingDetails" to shippingDetails?.toDictionary(),
                    "metadata" to metadata
            )
        }
    }

    /**
     * Represents payment method data for Bancontact.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class BancontactDataParams(
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents payment method data for a US bank account.
     *
     * @property billingDetails The billing details associated with the US bank account (required).
     * @property accountNumber The account number for the bank account.
     * @property routingNumber The routing number for the bank account.
     * @property linkAccountSessionId The session ID used for linking the bank account (optional).
     * @property accountHolderType The type of account holder (defaults to
     * [BankAccountHolderType.INDIVIDUAL]).
     * @property accountType The type of the bank account (defaults to [BankAccountType.CHECKING]).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataUSBankAccount(
            val billingDetails: USBillingDetails, // Custom class to enforce `name` as required
            val accountNumber: String,
            val routingNumber: String,
            val linkAccountSessionId: String?,
            val accountHolderType: BankAccountHolderType? =
                    BankAccountHolderType.INDIVIDUAL, // Default to Individual
            val accountType: BankAccountType? = BankAccountType.CHECKING, // Default to Checking
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details, account number, routing number, account
         * holder type,
         * ```
         *         account type, session ID, and metadata.
         * ```
         */
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

    /**
     * Represents payment method data for WeChat Pay.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataWeChatPay(
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents payment method data for Blik.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataBlik(
            val billingDetails: BillingDetails?,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents the billing details for a US bank account. This class enforces the `name` field as
     * a required property.
     *
     * @property name The name of the account holder (required).
     * @property phone The phone number associated with the billing details (optional).
     * @property email The email address associated with the billing details (optional).
     * @property address The address associated with the billing details (optional).
     */
    data class USBillingDetails(
            val name: String, // Required field
            val phone: String? = null,
            val email: String? = null,
            val address: Address? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the name, phone, email, and address.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "name" to name,
                    "phone" to phone,
                    "email" to email,
                    "address" to address?.toDictionary()
            )
        }
    }

    /** Enum representing the type of account holder for a bank account. */
    enum class BankAccountHolderType {
        COMPANY,
        INDIVIDUAL,
        UNKNOWN
    }

    /** Enum representing the type of account holder for a bank account. */
    enum class BankAccountType {
        CHECKING,
        SAVINGS,
        UNKNOWN
    }

    /**
     * Represents payment method data for Affirm.
     *
     * @property shippingDetails The shipping details associated with the payment method (optional).
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataAffirm(
            val shippingDetails: ShippingDetails? = null,
            val billingDetails: BillingDetails? = null,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the shipping details, billing details, and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf(
                    "shippingDetails" to shippingDetails?.toDictionary(),
                    "billingDetails" to billingDetails?.toDictionary(),
                    "metadata" to metadata
            )
        }
    }

    /**
     * Represents payment method data for CashApp.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataCashApp(
            val billingDetails: BillingDetails? = null,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents payment method data for MultiBanco.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataMultiBanco(
            val billingDetails: BillingDetails? = null,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }

    /**
     * Represents payment method data for Amazon Pay.
     *
     * @property billingDetails The billing details associated with the payment method (optional).
     * @property metadata Additional metadata for the payment method (optional).
     */
    data class PaymentMethodDataAmazonPay(
            val billingDetails: BillingDetails? = null,
            val metadata: Map<String, String>? = null
    ) {
        /**
         * Converts this object to a dictionary.
         *
         * @return A map containing the billing details and metadata.
         */
        fun toDictionary(): Map<String, Any?> {
            return mapOf("billingDetails" to billingDetails?.toDictionary(), "metadata" to metadata)
        }
    }
}

/**
 * Represents the options for creating a payment method setup.
 *
 * @property setupFutureUsage The future usage option for the payment method setup (optional).
 */
data class CreateOptions(val setupFutureUsage: FutureUsage? = null) {
    /**
     * Converts this object to a dictionary.
     *
     * @return A map containing the future usage value (if set).
     */
    fun toDictionary(): Map<String, Any?> {
        return mapOf("setupFutureUsage" to setupFutureUsage?.value)
    }
}
