import di.Stripe
import io.mockative.any
import io.mockative.coEvery
import kotlinx.coroutines.runBlocking
import mocks.MockStripeRepository
import model.Address
import model.BillingDetails
import model.InitialiseParams
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import repository.StripeRepository
import kotlin.test.*
/**
 * Unit tests for the NativeStripeSdkTest class.
 *
 * This class tests the initialization of the Stripe SDK and the creation of different payment methods
 * (Card, Ideal, and SEPA Debit) using various parameter configurations. It uses MockStripeRepository for mocking
 * the Stripe API calls and Koin for dependency injection.
 */

class NativeStripeSdkTest {

    /**
     * A reference to the ProvideStripeSdk class which provides the Stripe SDK functions for initialization and payment methods.
     */
    private lateinit var provideStripeSdk: ProvideStripeSdk

    /**
     * Sets up the test environment before each test case.
     * This includes initializing the ProvideStripeSdk instance and mocking the Stripe SDK with MockStripeRepository.
     */
    @BeforeTest
    fun setup() {
        provideStripeSdk = ProvideStripeSdk()
        Stripe.setProvider(MockStripeRepository())
    }

    /**
     * Tests the initialization of the Stripe SDK with valid initialization parameters.
     * Ensures that the parameters are correctly passed to the Stripe SDK.
     */
    @Test
    fun testInitialise(): Unit = runBlocking {
        val initialiseParams = InitialiseParams(
            publishableKey = "pk_test_12345",
            stripeAccountId = "acct_12345",
            threeDSecureParams = null,
            merchantIdentifier = "merchant.com.example",
            urlScheme = "com.example.app",
            setReturnUrlSchemeOnAndroid = false,
            appInfo = null
        )
        provideStripeSdk.initialise(initialiseParams)
    }

    /**
     * Tests the initialization of the Stripe SDK with invalid parameters.
     * Verifies that an IllegalArgumentException is thrown when passing invalid initialization parameters.
     */
    @Test
    fun testInitialiseWithInvalidParams(): Unit = runBlocking {
        val invalidInitialiseParams = InitialiseParams(
            publishableKey = "", // Invalid publishable key
            stripeAccountId = "invalid_acct", // Invalid account ID
            threeDSecureParams = null,
            merchantIdentifier = "invalid.merchant.identifier",
            urlScheme = "invalid.url.scheme",
            setReturnUrlSchemeOnAndroid = false,
            appInfo = null
        )

        val exception = assertFailsWith<IllegalArgumentException> {
            provideStripeSdk.initialise(invalidInitialiseParams)
        }
        assertEquals("Publishable key cannot be empty.", exception.message)
    }

    /**
     * Tests the creation of a payment method using valid CardParamsWithToken.
     * Verifies that the success callback is triggered and the expected results are returned.
     */
    @Test
    fun `test createPaymentMethod with valid CardParamsWithToken`() = runBlocking {
        val billingDetails = BillingDetails(
            name = "John Doe",
            email = "johndoe@example.com",
            phone = "1234567890",
            address = Address(
                line1 = "123 Main St",
                line2 = "Apt 4B",
                city = "New York",
                state = "NY",
                postalCode = "10001",
                country = "US"
            )
        )
        val params = CreateParams.CardParamsWithToken(
            paymentMethodData = CreateParams.PaymentMethodDataWithToken(
                token = "valid_token",
                billingDetails = billingDetails
            )
        )

        val options = CreateOptions()
        var result: Map<String, Any?>? = null
        var error: Throwable? = null

        provideStripeSdk.createPaymentMethod(params, options, onSuccess = {
            result = it
        }, onError = {
            error = it
        })

        assertNotNull(result)
        assertEquals("success", result?.get("status"))
        assertEquals("generated_payment_method_id", result?.get("paymentMethodId"))
        assertNull(error)
    }

    /**
     * Tests the creation of a payment method using valid IdealParams.
     * Verifies that the success callback is triggered and the expected results are returned.
     */
    @Test
    fun `test createPaymentMethod with valid IdealParams`() = runBlocking {
        val billingDetails = BillingDetails(
            name = "Jane Doe",
            email = "janedoe@example.com",
            phone = "0987654321",
            address = Address(
                line1 = "456 Elm St",
                line2 = "Suite 101",
                city = "San Francisco",
                state = "CA",
                postalCode = "94103",
                country = "US"
            )
        )
        val params = CreateParams.IdealParams(
            paymentMethodData = CreateParams.PaymentMethodDataIdeal(
                billingDetails = billingDetails,
                bankName = "example_bank"
            )
        )
        val options = CreateOptions()
        var result: Map<String, Any?>? = null
        var error: Throwable? = null

        provideStripeSdk.createPaymentMethod(params, options, onSuccess = {
            result = it
        }, onError = {
            error = it
        })

        assertNotNull(result)
        assertEquals("success", result?.get("status"))
        assertEquals("generated_ideal_payment_method_id", result?.get("paymentMethodId"))
        assertNull(error)
    }

    /**
     * Tests the creation of a payment method using valid SepaDebitParams.
     * Verifies that the success callback is triggered and the expected results are returned.
     */
    @Test
    fun `test createPaymentMethod with valid SepaDebitParams`() = runBlocking {
        val billingDetails = BillingDetails(
            name = "Jane Doe",
            email = "janedoe@example.com",
            phone = "0987654321",
            address = Address(
                line1 = "456 Elm St",
                line2 = "Suite 101",
                city = "San Francisco",
                state = "CA",
                postalCode = "94103",
                country = "US"
            )
        )
        val params = CreateParams.SepaDebitParams(
            paymentMethodData = CreateParams.PaymentMethodDataSepaDebit(
                iban = "DE89370400440532013000",
                billingDetails = billingDetails
            )
        )
        val options = CreateOptions()
        var result: Map<String, Any?>? = null
        var error: Throwable? = null

        provideStripeSdk.createPaymentMethod(params, options, onSuccess = {
            result = it
        }, onError = {
            error = it
        })

        assertNotNull(result)
        assertEquals("generated_sepa_debit_payment_method_id", result?.get("paymentMethodId"))
        assertNull(error)
    }


    /**
     * Tests the handling of the next action for a valid PaymentIntentClientSecret.
     * Verifies that the success callback is triggered with the correct result map and no errors occur.
     *
     * Scenario:
     * - Given a valid `PaymentIntentClientSecret` and `returnURL`, the test expects the `onSuccess`
     *   callback to be invoked with the expected `paymentIntentId`.
     * - The `onError` callback should not be invoked.
     */

    @Test
    fun `test handleNextAction with valid PaymentIntentClientSecret`() = runBlocking {
        val paymentIntentClientSecret = "pi_client_secret_12345"
        val returnURL = "myapp://payment-completed"
        var result: Map<String, Any?>? = null
        var error: Throwable? = null

        provideStripeSdk.handleNextAction(
            paymentIntentClientSecret = paymentIntentClientSecret,
            returnURL = returnURL,
            onSuccess = {
                result = it
            },
            onError = {
                error = it
            }
        )

        assertNotNull(result)
        assertEquals("payment_intent_id_12345", result?.get("paymentIntentId"))
        assertNull(error)
    }

    /**
     * Tests the handling of the next action for a valid SetupIntentClientSecret.
     * Verifies that the success callback is triggered with the correct result map and no errors occur.
     *
     * Scenario:
     * - Given a valid `SetupIntentClientSecret` and `returnURL`, the test expects the `onSuccess`
     *   callback to be invoked with the expected `setupIntentId`.
     * - The `onError` callback should not be invoked.
     */

    @Test
    fun `test handleNextActionForSetup with valid SetupIntentClientSecret`() = runBlocking {
        val setupIntentClientSecret = "si_client_secret_67890"
        val returnURL = "myapp://setup-completed"
        var result: Map<String, Any?>? = null
        var error: Throwable? = null

        provideStripeSdk.handleNextActionForSetup(
            setupIntentClientSecret = setupIntentClientSecret,
            returnURL = returnURL,
            onSuccess = {
                result = it
            },
            onError = {
                error = it
            }
        )

        assertNotNull(result)
        assertEquals("setup_intent_id_67890", result?.get("setupIntentId"))
        assertNull(error)
    }



}
