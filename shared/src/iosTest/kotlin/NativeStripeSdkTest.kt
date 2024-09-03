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

class NativeStripeSdkTest : KoinTest {
    private lateinit var mockStripeRepository: StripeRepository
    private lateinit var provideStripeSdk: ProvideStripeSdk

    private val testModule = module {
        single<StripeRepository> { MockStripeRepository() }
    }

    @BeforeTest
    fun setup() {
        startKoin {
            modules(testModule) // Start Koin with the test module
        }
        mockStripeRepository = get() // Retrieve the mock repository
        provideStripeSdk = ProvideStripeSdk() // Initialize the SDK with injected dependencies
    }

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

        println("actual: ${initialiseParams.toDictionary()}")
        // Call the method under test
        provideStripeSdk.initialise(initialiseParams)

    }

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

        // Verify that an exception is thrown when trying to initialise with invalid parameters
        val exception = assertFailsWith<IllegalArgumentException> {
            provideStripeSdk.initialise(invalidInitialiseParams)
        }

        // Check the exception message to ensure it's as expected
        assertEquals("Publishable key cannot be empty.", exception.message)
    }

    @Test
    fun `test createPaymentMethod with valid CardParamsWithToken`() = runBlocking {
        // Arrange: Prepare valid CreateParams and CreateOptions
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

        val options = CreateOptions() // Assume options can be an empty or populated object

        var result: Map<String, Any?>? = null
        var error: Throwable? = null

        // Act: Call createPaymentMethod
        provideStripeSdk.createPaymentMethod(
            params = params,  // Convert params to a dictionary
            options = options, // Convert options to a dictionary
            onSuccess = {
                result = it
                println("Success callback called with result: $result")
            },
            onError = {
                error = it
                println("Error callback called with error: ${it.message}")
            }
        )

        // Assert: Verify that the success callback is called with expected results
        assertNotNull(result, "The result should not be null")
        assertEquals("success", result?.get("status"), "The status should be 'success'")
        assertEquals("generated_payment_method_id", result?.get("paymentMethodId"), "The paymentMethodId should match")

        // Ensure no error occurred
        assertNull(error, "There should be no error")
    }
}
