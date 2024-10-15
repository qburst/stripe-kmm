import model.Address
import model.BillingDetails
import repositories.CreatePaymentValidation
import kotlin.test.Test
import kotlin.test.assertTrue

class CreatePaymentValidationTest {
    @Test
    fun cardParamsWithTokenValidTest1 () {
        val params = CreateParams.CardParamsWithToken(
            paymentMethodData = CreateParams.PaymentMethodDataWithToken(
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun cardParamsWithTokenValidTest2 () {
        val params = CreateParams.CardParamsWithToken(
            paymentMethodData = CreateParams.PaymentMethodDataWithToken(
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun cardParamsWithTokenValidTest3 () {
        val params = CreateParams.CardParamsWithToken(
            paymentMethodData = CreateParams.PaymentMethodDataWithToken(
                token = "tok_1PztKPKJ38Q1wp9d0BfiXZiE",
                billingDetails = null,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun cardParamsWithTokenNullTest () {
        val params = CreateParams.CardParamsWithToken(
            paymentMethodData = CreateParams.PaymentMethodDataWithToken(
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Token is mandatory" }
    }
    @Test
    fun cardParamsWithTokenEmptyTest () {
        val params = CreateParams.CardParamsWithToken(
            paymentMethodData = CreateParams.PaymentMethodDataWithToken(
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Token is mandatory" }
    }
    @Test
    fun cardParamsWithPaymentIdValidTest1 () {
        val params = CreateParams.CardParamsWithPaymentId(
            paymentMethodData = CreateParams.PaymentMethodDataWithPaymentId(
                paymentMethodId = "123456",
                cvc = "123",
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun cardParamsWithPaymentIdValidTest2 () {
        val params = CreateParams.CardParamsWithPaymentId(
            paymentMethodData = CreateParams.PaymentMethodDataWithPaymentId(
                paymentMethodId = "123456",
                cvc = null,
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun cardParamsWithPaymentIdValidTest3 () {
        val params = CreateParams.CardParamsWithPaymentId(
            paymentMethodData = CreateParams.PaymentMethodDataWithPaymentId(
                paymentMethodId = "123456",
                cvc = "",
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun cardParamsWithPaymentIdEmptyTest () {
        val params = CreateParams.CardParamsWithPaymentId(
            paymentMethodData = CreateParams.PaymentMethodDataWithPaymentId(
                paymentMethodId = "",
                cvc = "123",
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Payment method id is mandatory" }
    }
    @Test
    fun cardParamsWithBillingNullTest () {
        val params = CreateParams.CardParamsWithPaymentId(
            paymentMethodData = CreateParams.PaymentMethodDataWithPaymentId(
                paymentMethodId = "",
                cvc = "123",
                billingDetails = null
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Billing details is mandatory" }
    }
    @Test
    fun auBecsDebitParamsValidTest1 () {
        val params = CreateParams.AuBecsDebitParams(
            paymentMethodType = "AuBecsDebit",
            paymentMethodData = CreateParams.PaymentMethodDataAuBecs(
                formDetails = CreateParams.FormDetails(accountNumber = "132456667", bsbNumber="132355889", email = "john@example.com", name="John Doe"),
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun auBecsDebitParamsValidTest2 () {
        val params = CreateParams.AuBecsDebitParams(
            paymentMethodType = "AuBecsDebit",
            paymentMethodData = CreateParams.PaymentMethodDataAuBecs(
                formDetails = CreateParams.FormDetails(accountNumber = "132456667", bsbNumber="132355889", email = "", name=""),
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun auBecsDebitParamsValidTest3 () {
        val params = CreateParams.AuBecsDebitParams(
            paymentMethodType = "AuBecsDebit",
            paymentMethodData = CreateParams.PaymentMethodDataAuBecs(
                formDetails = CreateParams.FormDetails(accountNumber = "132456667", bsbNumber="132355889", email = "john@example.com", name="John Doe"),
                billingDetails = null
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun auBecsDebitParamsValidTest4 () {
        val params = CreateParams.AuBecsDebitParams(
            paymentMethodType = "AuBecsDebit",
            paymentMethodData = CreateParams.PaymentMethodDataAuBecs(
                formDetails = CreateParams.FormDetails(accountNumber = "132456667", bsbNumber="132355889", email = null, name = null),
                billingDetails = null
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun auBecsDebitParamsValidTest5 () {
        val params = CreateParams.AuBecsDebitParams(
            paymentMethodType = "AuBecsDebit",
            paymentMethodData = CreateParams.PaymentMethodDataAuBecs(
                formDetails = CreateParams.FormDetails(accountNumber = "132456667", bsbNumber="132355889", email = null, name = null),
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun auBecsDebitParamsAccNumEmptyTest () {
        val params = CreateParams.AuBecsDebitParams(
            paymentMethodType = "AuBecsDebit",
            paymentMethodData = CreateParams.PaymentMethodDataAuBecs(
                formDetails = CreateParams.FormDetails(accountNumber = "", bsbNumber="132355889", email = "john@example.com", name="John Doe"),
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Account number is mandatory" }
    }
    @Test
    fun auBecsDebitParamsBsbNumEmptyTest () {
        val params = CreateParams.AuBecsDebitParams(
            paymentMethodType = "AuBecsDebit",
            paymentMethodData = CreateParams.PaymentMethodDataAuBecs(
                formDetails = CreateParams.FormDetails(accountNumber = "132456667", bsbNumber="", email = "john@example.com", name="John Doe"),
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "BSB number is mandatory" }
    }
    @Test
    fun bacsDebitParamsValidTest1 () {
        val params = CreateParams.BacsDebitParams(
            paymentMethodType = "BacsDebit",
            paymentMethodData = CreateParams.PaymentMethodDataBacsDebit(
                bacsDebit = CreateParams.BacsDetails(accountNumber = "132456667", sortCode = "1234"),
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun bacsDebitParamsValidTest2 () {
        val params = CreateParams.BacsDebitParams(
            paymentMethodType = "BacsDebit",
            paymentMethodData = CreateParams.PaymentMethodDataBacsDebit(
                bacsDebit = CreateParams.BacsDetails(accountNumber = "132456667", sortCode = "1234"),
                billingDetails = null
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun bacsDebitParamsAccNumEmptyTest () {
        val params = CreateParams.BacsDebitParams(
            paymentMethodType = "BacsDebit",
            paymentMethodData = CreateParams.PaymentMethodDataBacsDebit(
                bacsDebit = CreateParams.BacsDetails(accountNumber = "", sortCode = "1234"),
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Account number is mandatory" }
    }
    @Test
    fun bacsDebitParamsSortCodeEmptyTest () {
        val params = CreateParams.BacsDebitParams(
            paymentMethodType = "BacsDebit",
            paymentMethodData = CreateParams.PaymentMethodDataBacsDebit(
                bacsDebit = CreateParams.BacsDetails(accountNumber = "132456667", sortCode = ""),
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Sort code is mandatory" }
    }
    @Test
    fun sofortParamsValidTest1 () {
        val params = CreateParams.SofortParams(
            paymentMethodType = "Sofort",
            paymentMethodData = CreateParams.PaymentMethodDataSofort(
                country = "India",
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun sofortParamsValidTest2 () {
        val params = CreateParams.SofortParams(
            paymentMethodType = "Sofort",
            paymentMethodData = CreateParams.PaymentMethodDataSofort(
                country = "India",
                billingDetails = null
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun sofortParamsCountryEmptyTest () {
        val params = CreateParams.SofortParams(
            paymentMethodType = "Sofort",
            paymentMethodData = CreateParams.PaymentMethodDataSofort(
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Country is mandatory" }
    }
    @Test
    fun sofortParamsCountryNullTest () {
        val params = CreateParams.SofortParams(
            paymentMethodType = "Sofort",
            paymentMethodData = CreateParams.PaymentMethodDataSofort(
                country = null,
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Country is mandatory" }
    }
    @Test
    fun netBankingParamsValidTest1 () {
        val params = CreateParams.NetBankingParams(
            paymentMethodType = "netbanking",
            paymentMethodData = CreateParams.PaymentMethodDataNetBanking(
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun netBankingParamsValidTest2 () {
        val params = CreateParams.NetBankingParams(
            paymentMethodType = "netbanking",
            paymentMethodData = CreateParams.PaymentMethodDataNetBanking(
                bank = "hdfc",
                billingDetails = null
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun netBankingParamsBankEmptyTest () {
        val params = CreateParams.NetBankingParams(
            paymentMethodType = "netbanking",
            paymentMethodData = CreateParams.PaymentMethodDataNetBanking(
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Bank is mandatory" }
    }
    @Test
    fun netBankingParamsBankNullTest () {
        val params = CreateParams.NetBankingParams(
            paymentMethodType = "netbanking",
            paymentMethodData = CreateParams.PaymentMethodDataNetBanking(
                bank = null,
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Bank is mandatory" }
    }
    @Test
    fun klarnaParamsValidTest1 () {
        val params = CreateParams.KlarnaParams(
            paymentMethodType = "Klarna",
            paymentMethodData = CreateParams.PaymentMethodDataKlarna(
                billingDetails = CreateParams.KlarnaBillingDetails(
                    email = "john@example.com",
                    address = CreateParams.KlarnaAddress(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"),
                    phone = "1234567890",
                    name="John Doe"),
                shippingDetails = BillingDetails(
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun klarnaParamsValidTest2 () {
        val params = CreateParams.KlarnaParams(
            paymentMethodType = "Klarna",
            paymentMethodData = CreateParams.PaymentMethodDataKlarna(
                billingDetails = CreateParams.KlarnaBillingDetails(
                    email = "john@example.com",
                    address = CreateParams.KlarnaAddress(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"),
                    phone = "",
                    name = ""),
                shippingDetails = BillingDetails(
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun klarnaParamsValidTest3 () {
        val params = CreateParams.KlarnaParams(
            paymentMethodType = "Klarna",
            paymentMethodData = CreateParams.PaymentMethodDataKlarna(
                billingDetails = CreateParams.KlarnaBillingDetails(
                    email = "john@example.com",
                    address = CreateParams.KlarnaAddress(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"),
                    phone = null,
                    name = null),
                shippingDetails = BillingDetails(
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun klarnaParamsValidTest4 () {
        val params = CreateParams.KlarnaParams(
            paymentMethodType = "Klarna",
            paymentMethodData = CreateParams.PaymentMethodDataKlarna(
                billingDetails = CreateParams.KlarnaBillingDetails(
                    email = "john@example.com",
                    address = CreateParams.KlarnaAddress(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"),
                    phone = "",
                    name = ""),
                shippingDetails = null
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun klarnaParamsValidTest5 () {
        val params = CreateParams.KlarnaParams(
            paymentMethodType = "Klarna",
            paymentMethodData = CreateParams.PaymentMethodDataKlarna(
                billingDetails = CreateParams.KlarnaBillingDetails(
                    email = "john@example.com",
                    address = CreateParams.KlarnaAddress(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"),
                    phone = null,
                    name = null),
                shippingDetails = null
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun klarnaParamsBillingDetailsEmailEmptyTest () {
        val params = CreateParams.KlarnaParams(
            paymentMethodType = "Klarna",
            paymentMethodData = CreateParams.PaymentMethodDataKlarna(
                billingDetails = CreateParams.KlarnaBillingDetails(
                    email = "",
                    address = CreateParams.KlarnaAddress(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                shippingDetails = BillingDetails(
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
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Email is mandatory" }
    }
    @Test
    fun usBankAccountParamsValidTest1 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.INDIVIDUAL,
                accountType = CreateParams.BankAccountType.CHECKING,
                )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest2 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.INDIVIDUAL,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest3 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.INDIVIDUAL,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest4 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest5 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest6 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest7 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest8 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest9 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest10 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = null,
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.INDIVIDUAL,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest11 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = null,
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest12 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = null,
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest13 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = null,
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest14 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = null,
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest15 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = null,
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest16 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = null,
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest17 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.INDIVIDUAL,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest18 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest19 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest20 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest21 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest22 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest23 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest24 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = null,
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.INDIVIDUAL,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest25 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = null,
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest26 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = null,
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest27 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = null,
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest28 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = null,
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest29 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = null,
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest30 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = null,
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest31 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = null,
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.INDIVIDUAL,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest32 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = null,
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest33 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = null,
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest34 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = null,
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest35 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = null,
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest36 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = null,
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest37 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest38 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.INDIVIDUAL,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest39 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest40 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest41 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest42 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest43 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest44 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "12445",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest45 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = null,
                accountHolderType = CreateParams.BankAccountHolderType.INDIVIDUAL,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest46 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = null,
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest47 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = null,
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest48 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = null,
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest49 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = null,
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest50 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = null,
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest51 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = null,
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest52 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "",
                accountHolderType = CreateParams.BankAccountHolderType.INDIVIDUAL,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest53 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest54 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest55 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "",
                accountHolderType = CreateParams.BankAccountHolderType.COMPANY,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest56 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest57 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.SAVINGS,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest58 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest59 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "",
                    address = null,
                    phone = "",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "123456",
                linkAccountSessionId = "",
                accountHolderType = CreateParams.BankAccountHolderType.INDIVIDUAL,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsValidTest60 () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = null,
                    address = null,
                    phone = null,
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "123456",
                linkAccountSessionId = null,
                accountHolderType = CreateParams.BankAccountHolderType.INDIVIDUAL,
                accountType = CreateParams.BankAccountType.CHECKING,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun usBankAccountParamsNameEmptyTest () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name=""),
                accountNumber = "122423626",
                routingNumber = "13251",
                linkAccountSessionId = "",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Name is mandatory" }
    }
    @Test
    fun usBankAccountParamsAccNumEmptyTest () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "",
                routingNumber = "13251",
                linkAccountSessionId = "",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Account number is mandatory" }
    }
    @Test
    fun usBankAccountParamsRoutingNumEmptyTest () {
        val params = CreateParams.USBankAccountParams(
            paymentMethodType = "USBankAccount",
            paymentMethodData = CreateParams.PaymentMethodDataUSBankAccount(
                billingDetails = CreateParams.USBillingDetails(
                    email = "john@example.com",
                    address = Address(
                        city = "New York",
                        country = "US",
                        line1 = "123 Main St",
                        line2 = "Apt 4B",
                        postalCode = "10001",
                        state = "NY"
                    ),
                    phone = "1234567890",
                    name="John Doe"),
                accountNumber = "122423626",
                routingNumber = "",
                linkAccountSessionId = "",
                accountHolderType = CreateParams.BankAccountHolderType.UNKNOWN,
                accountType = CreateParams.BankAccountType.UNKNOWN,
            )
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Routing number is mandatory" }
    }
    @Test
    fun googlePayParamsValidTest1 () {
        val params = CreateParams.GooglePayParams(
            paymentMethodType = "GooglePay",
            jsonObject = "123245"
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun googlePayParamsValidTest2 () {
        val params = CreateParams.GooglePayParams(
            paymentMethodType = "GooglePay",
            jsonObject = {"123245"}
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun googlePayParamsValidTest3 () {
        val params = CreateParams.GooglePayParams(
            paymentMethodType = "GooglePay",
            jsonObject = 123245
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "success" }
    }
    @Test
    fun googlePayParamsJsonObjStrEmptyTest () {
        val params = CreateParams.GooglePayParams(
            paymentMethodType = "GooglePay",
            jsonObject = ""
        )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Google pay payment data is mandatory" }
    }
    @Test
    fun googlePayParamsJsonObjNullTest () {
        val params = CreateParams.GooglePayParams(
            paymentMethodType = "GooglePay",
            jsonObject = null
            )
        val result = CreatePaymentValidation.validateCreatePaymentParams(params)
        assertTrue { result == "Google pay payment data is mandatory" }
    }
}