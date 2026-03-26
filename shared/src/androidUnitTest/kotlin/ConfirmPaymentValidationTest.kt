import model.Address
import model.BillingDetails
import model.ConfirmParams
import model.PaymentError
import repositories.ConfirmPaymentValidation
import kotlin.test.Test
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ConfirmPaymentValidationTest {

    // CardParamsWithToken Tests
    @Test
    fun cardParamsWithTokenValidTest1() {
        val params = ConfirmParams.CardParamsWithToken(
            paymentMethodData = ConfirmParams.PaymentMethodDataWithToken(
                token = "tok_1PztKPKJ38Q1wp9d0BfiXZiE",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun cardParamsWithTokenValidTest2() {
        val params = ConfirmParams.CardParamsWithToken(
            paymentMethodData = ConfirmParams.PaymentMethodDataWithToken(
                token = "tok_1PztKPKJ38Q1wp9d0BfiXZiE",
                billingDetails = BillingDetails(
                    email = "",
                    phone = "",
                    name = "",
                    address = Address(
                        city = "",
                        country = "",
                        line1 = "",
                        line2 = "",
                        postalCode = "",
                        state = ""
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun cardParamsWithTokenValidTest3() {
        val params = ConfirmParams.CardParamsWithToken(
            paymentMethodData = ConfirmParams.PaymentMethodDataWithToken(
                token = "tok_1PztKPKJ38Q1wp9d0BfiXZiE",
                billingDetails = null
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun cardParamsWithTokenNullTest() {
        val params = ConfirmParams.CardParamsWithToken(
            paymentMethodData = ConfirmParams.PaymentMethodDataWithToken(
                token = null,
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.TokenMissing)
    }

    @Test
    fun cardParamsWithTokenEmptyTest() {
        val params = ConfirmParams.CardParamsWithToken(
            paymentMethodData = ConfirmParams.PaymentMethodDataWithToken(
                token = "",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.TokenMissing)
    }

    // IdealParams Tests
    @Test
    fun idealParamsValidTest1() {
        val params = ConfirmParams.IdealParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataIdeal(
                bankName = "abn_amro",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun idealParamsValidTest2() {
        val params = ConfirmParams.IdealParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataIdeal(
                bankName = "abn_amro",
                billingDetails = null
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun idealParamsBankNameEmptyTest() {
        val params = ConfirmParams.IdealParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataIdeal(
                bankName = "",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.BankNameMissing)
    }

    @Test
    fun idealParamsBankNameNullTest() {
        val params = ConfirmParams.IdealParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataIdeal(
                bankName = null,
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.BankNameMissing)
    }

    // UpiParams Tests
    @Test
    fun upiParamsValidTest1() {
        val params = ConfirmParams.UpiParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataUpi(
                vpa = "test@upi",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun upiParamsValidTest2() {
        val params = ConfirmParams.UpiParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataUpi(
                vpa = "test@upi",
                billingDetails = null
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun upiParamsVpaEmptyTest() {
        val params = ConfirmParams.UpiParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataUpi(
                vpa = "",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.VPAMissing)
    }

    @Test
    fun upiParamsVpaNullTest() {
        val params = ConfirmParams.UpiParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataUpi(
                vpa = null,
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.VPAMissing)
    }

    // FpxParams Tests
    @Test
    fun fpxParamsValidTest1() {
        val params = ConfirmParams.FpxParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataFpx(
                bankName = "affin_bank",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun fpxParamsValidTest2() {
        val params = ConfirmParams.FpxParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataFpx(
                bankName = "affin_bank",
                billingDetails = null
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun fpxParamsBankNameEmptyTest() {
        val params = ConfirmParams.FpxParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataFpx(
                bankName = "",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.BankNameMissing)
    }

    @Test
    fun fpxParamsBankNameNullTest() {
        val params = ConfirmParams.FpxParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataFpx(
                bankName = null,
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.BankNameMissing)
    }

    // PayPalParams Tests
    @Test
    fun payPalParamsValidTest1() {
        val params = ConfirmParams.PayPalParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataPayPal(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun payPalParamsBillingDetailsNullTest() {
        val params = ConfirmParams.PayPalParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataPayPal(
                billingDetails = null
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.BillingDetailsMissing)
    }

    // SepaDebitParams Tests
    @Test
    fun sepaDebitParamsValidTest1() {
        val params = ConfirmParams.SepaDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataSepaDebit(
                iban = "DE89370400440532013000",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun sepaDebitParamsValidTest2() {
        val params = ConfirmParams.SepaDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataSepaDebit(
                iban = "DE89370400440532013000",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    name = "John Doe"
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun sepaDebitParamsNameEmptyTest() {
        val params = ConfirmParams.SepaDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataSepaDebit(
                iban = "DE89370400440532013000",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.NameMissing)
    }

    @Test
    fun sepaDebitParamsEmailEmptyTest() {
        val params = ConfirmParams.SepaDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataSepaDebit(
                iban = "DE89370400440532013000",
                billingDetails = BillingDetails(
                    email = "",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.EmailMissing)
    }

    @Test
    fun sepaDebitParamsIbanEmptyTest() {
        val params = ConfirmParams.SepaDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataSepaDebit(
                iban = "",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.IBANMissing)
    }

    @Test
    fun sepaDebitParamsIbanNullTest() {
        val params = ConfirmParams.SepaDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataSepaDebit(
                iban = null,
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.IBANMissing)
    }

    // AuBecsDebitParams Tests
    @Test
    fun auBecsDebitParamsValidTest1() {
        val params = ConfirmParams.AuBecsDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataAuBecs(
                bsbNumber = "132355889",
                accountNumber = "132456667",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun auBecsDebitParamsBsbNumberEmptyTest() {
        val params = ConfirmParams.AuBecsDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataAuBecs(
                bsbNumber = "",
                accountNumber = "132456667",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.BSBNumberMissing)
    }

    @Test
    fun auBecsDebitParamsAccountNumberEmptyTest() {
        val params = ConfirmParams.AuBecsDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataAuBecs(
                bsbNumber = "132355889",
                accountNumber = "",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.AccountNumberMissing)
    }

    // BacsDebitParams Tests
    @Test
    fun bacsDebitParamsValidTest1() {
        val params = ConfirmParams.BacsDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataBacsDebit(
                sortCode = "1234",
                accountNumber = "132456667",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun bacsDebitParamsSortCodeEmptyTest() {
        val params = ConfirmParams.BacsDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataBacsDebit(
                sortCode = "",
                accountNumber = "132456667",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.SortCodeMissing)
    }

    @Test
    fun bacsDebitParamsAccountNumberEmptyTest() {
        val params = ConfirmParams.BacsDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataBacsDebit(
                sortCode = "1234",
                accountNumber = "",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.AccountNumberMissing)
    }

    // SofortParams Tests
    @Test
    fun sofortParamsValidTest1() {
        val params = ConfirmParams.SofortParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataSofort(
                country = "DE",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun sofortParamsCountryEmptyTest() {
        val params = ConfirmParams.SofortParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataSofort(
                country = "",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.CountryMissing)
    }

    // NetBankingParams Tests
    @Test
    fun netBankingParamsValidTest1() {
        val params = ConfirmParams.NetBankingParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataNetBanking(
                bank = "hdfc",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun netBankingParamsBankEmptyTest() {
        val params = ConfirmParams.NetBankingParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataNetBanking(
                bank = "",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.BankNameMissing)
    }

    // USBankAccountParams Tests
    @Test
    fun usBankAccountParamsValidTest1() {
        val params = ConfirmParams.USBankAccountParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataUSBankAccount(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                ),
                accountNumber = "122423626",
                routingNumber = "13251"
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun usBankAccountParamsValidTest2() {
        val params = ConfirmParams.USBankAccountParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataUSBankAccount(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                ),
                linkAccountSessionId = "12445"
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun usBankAccountParamsBillingDetailsNullTest() {
        val params = ConfirmParams.USBankAccountParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataUSBankAccount(
                billingDetails = null,
                accountNumber = "122423626",
                routingNumber = "13251"
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.BillingDetailsMissing)
    }

    @Test
    fun usBankAccountParamsAccountNumberEmptyTest() {
        val params = ConfirmParams.USBankAccountParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataUSBankAccount(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                ),
                accountNumber = "",
                routingNumber = "13251"
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.AccountNumberMissing)
    }

    @Test
    fun usBankAccountParamsRoutingNumberEmptyTest() {
        val params = ConfirmParams.USBankAccountParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataUSBankAccount(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                ),
                accountNumber = "122423626",
                routingNumber = ""
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.RoutingNumberMissing)
    }

    // GooglePayParams Tests
    @Test
    fun googlePayParamsValidTest1() {
        val params = ConfirmParams.GooglePayParams(
            paymentMethodType = "GooglePay",
            jsonObject = "123245"
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun googlePayParamsValidTest2() {
        val params = ConfirmParams.GooglePayParams(
            paymentMethodType = "GooglePay",
            jsonObject = 123245
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun googlePayParamsJsonObjectNullTest() {
        val params = ConfirmParams.GooglePayParams(
            paymentMethodType = "GooglePay",
            jsonObject = null
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.GooglePayJsonMissing)
    }

    // BancontactParams Tests
    @Test
    fun bancontactParamsValidTest1() {
        val params = ConfirmParams.BancontactParams(
            paymentMethodData = ConfirmParams.BancontactDataParams(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun bancontactParamsNameEmptyTest() {
        val params = ConfirmParams.BancontactParams(
            paymentMethodData = ConfirmParams.BancontactDataParams(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.NameMissing)
    }

    // EpsDebitParams Tests
    @Test
    fun epsDebitParamsValidTest1() {
        val params = ConfirmParams.EpsDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataEps(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun epsDebitParamsNameEmptyTest() {
        val params = ConfirmParams.EpsDebitParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataEps(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.NameMissing)
    }

    // OxxoParams Tests
    @Test
    fun oxxoParamsValidTest1() {
        val params = ConfirmParams.OxxoParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataOxxo(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun oxxoParamsNameEmptyTest() {
        val params = ConfirmParams.OxxoParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataOxxo(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.NameMissing)
    }

    @Test
    fun oxxoParamsEmailEmptyTest() {
        val params = ConfirmParams.OxxoParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataOxxo(
                billingDetails = BillingDetails(
                    email = "",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.EmailMissing)
    }

    // AlipayParams Tests
    @Test
    fun alipayParamsValidTest1() {
        val params = ConfirmParams.AlipayParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataAlipay(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun alipayParamsNameEmptyTest() {
        val params = ConfirmParams.AlipayParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataAlipay(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.NameMissing)
    }

    @Test
    fun alipayParamsEmailEmptyTest() {
        val params = ConfirmParams.AlipayParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataAlipay(
                billingDetails = BillingDetails(
                    email = "",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.EmailMissing)
    }

    // AfterpayClearpayParams Tests
    @Test
    fun afterpayClearpayParamsValidTest1() {
        val params = ConfirmParams.AfterpayClearpayParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataAfterpayClearpay(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun afterpayClearpayParamsNameEmptyTest() {
        val params = ConfirmParams.AfterpayClearpayParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataAfterpayClearpay(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.NameMissing)
    }

    @Test
    fun afterpayClearpayParamsEmailEmptyTest() {
        val params = ConfirmParams.AfterpayClearpayParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataAfterpayClearpay(
                billingDetails = BillingDetails(
                    email = "",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.EmailMissing)
    }

    // BlikParams Tests
    @Test
    fun blikParamsValidTest1() {
        val params = ConfirmParams.BlikParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataBlik(
                blikCode = "123456",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun blikParamsValidTest2() {
        val params = ConfirmParams.BlikParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataBlik(
                blikCode = "123456",
                billingDetails = null
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun blikParamsCodeEmptyTest() {
        val params = ConfirmParams.BlikParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataBlik(
                blikCode = "",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.BlikCodeMissing)
    }

    // WeChatPayParams Tests
    @Test
    fun weChatPayParamsValidTest1() {
        val params = ConfirmParams.WeChatPayParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataWeChatPay(
                appId = "wx1234567890",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun weChatPayParamsValidTest2() {
        val params = ConfirmParams.WeChatPayParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataWeChatPay(
                appId = "wx1234567890",
                billingDetails = null
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun weChatPayParamsAppIdEmptyTest() {
        val params = ConfirmParams.WeChatPayParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataWeChatPay(
                appId = "",
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.AppIdMissing)
    }

    // MultiBancoParams Tests
    @Test
    fun multiBancoParamsValidTest1() {
        val params = ConfirmParams.MultiBancoParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataMultiBanco(
                billingDetails = BillingDetails(
                    email = "john@example.com",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun multiBancoParamsEmailEmptyTest() {
        val params = ConfirmParams.MultiBancoParams(
            paymentMethodData = ConfirmParams.PaymentMethodDataMultiBanco(
                billingDetails = BillingDetails(
                    email = "",
                    phone = "1234567890",
                    name = "John Doe",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    )
                )
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.EmailMissing)
    }

    // PaymentMethodIdParams Tests
    @Test
    fun paymentMethodIdParamsValidTest1() {
        val params = ConfirmParams.PaymentMethodIdParams(
            paymentMethodData = ConfirmParams.PaymentMethodIdData(
                paymentMethodId = "pm_1234567890"
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertNull(result)
    }

    @Test
    fun paymentMethodIdParamsIdEmptyTest() {
        val params = ConfirmParams.PaymentMethodIdParams(
            paymentMethodData = ConfirmParams.PaymentMethodIdData(
                paymentMethodId = ""
            )
        )
        val result = ConfirmPaymentValidation.validateCreatePaymentParams(params)
        assertTrue(result is PaymentError.PaymentMethodIdMissing)
    }
}