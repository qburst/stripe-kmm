import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.isMock
import io.mockative.mock
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.runTest
import model.Address
import model.BillingDetails
import model.ApiResult
import repositories.PaymentRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CreatePaymentRepoTest {

    @Mock
    private val paymentRepo: PaymentRepository = mock(classOf<PaymentRepository>())

    @Test
    fun isMockEmptyClass() {
        assertTrue(isMock(paymentRepo))
    }

    @Test
    fun testingSuccessResponse() = runTest {
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

        coEvery { paymentRepo.createPaymentMethod(params) }.returnsMany(
            ApiResult(
                success = mapOf("success" to "Success"),
                error = null
            )
        )

        val result = CreatePaymentApi(paymentRepo).createPaymentMethod(params = params)

        assertTrue { result.success != null }
        assertTrue {result.success == mapOf("success" to "Success")}
        assertTrue { result.error == null }
    }

    @Test
    fun testingErrorResponse() = runTest {
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

        coEvery { paymentRepo.createPaymentMethod(params) }.returnsMany(
            ApiResult(
                success = null,
                error = Throwable("No token available")
            )
        )

        val result = CreatePaymentApi(paymentRepo).createPaymentMethod(params = params)

        assertTrue { result.success == null }
        assertTrue { result.error != null }
    }

    @Test
    fun `Test confirm payment onSuccess case`() = runTest {

        // 1. Tell Mockative to invoke the onSuccess callback (the 4th argument)
        coEvery {
            paymentRepo.confirmPayment(
                paymentIntentClientSecret = any(),
                params = any(),
                options = any(),
                onSuccess = any(),
                onError = any()
            )
        }.invokes { args ->
            // args[0] = paymentIntentClientSecret
            // args[1] = params
            // args[2] = options
            // args[3] = onSuccess callback
            // args[4] = onError callback
            val onSuccessCallback = args[3] as (Map<String, Any?>) -> Unit

            // Simulate a success response from Stripe
            onSuccessCallback(mapOf("status" to "Payment success"))
        }

        // 2. Use CompletableDeferred to capture the async callback result
        val successDeferred = CompletableDeferred<Map<String, Any?>>()
        val errorDeferred = CompletableDeferred<Throwable?>()

        // 3. Call the function
        paymentRepo.confirmPayment(
            paymentIntentClientSecret = any(),
            params = any(),
            options = any(),
            onSuccess = { result ->
                successDeferred.complete(result)
            },
            onError = { error ->
                errorDeferred.complete(error)
            }
        )

        // 4. Await the response and assert
        val result = successDeferred.await()
        assertEquals("Payment success", result["status"])
        assertTrue(!errorDeferred.isCompleted) // Error should not be triggered
    }

    @Test
    fun `Test confirm payment onError case`() = runTest {
        // 1. Tell Mockative to invoke the onSuccess callback (the 5th argument)
        coEvery {
            paymentRepo.confirmPayment(
                paymentIntentClientSecret = any(),
                params = any(),
                options = any(),
                onSuccess = any(),
                onError = any()
            )
        }.invokes { args ->
            // args[0] = paymentIntentClientSecret
            // args[1] = params
            // args[2] = options
            // args[3] = onSuccess callback
            // args[4] = onError callback
            val onErrorCallback = args[4] as (Throwable) -> Unit

            // Simulate a success response from Stripe
            onErrorCallback(Throwable("Payment failed"))

            // 2. Use CompletableDeferred to capture the async callback result
            val successDeferred = CompletableDeferred<Map<String, Any?>>()
            val errorDeferred = CompletableDeferred<Throwable?>()

            // 3. Call the function
            paymentRepo.confirmPayment(
                paymentIntentClientSecret = any(),
                params = any(),
                options = any(),
                onSuccess = { result ->
                    successDeferred.complete(result)
                },
                onError = { error ->
                    errorDeferred.complete(error)
                }
            )

            // 4. Await the response and assert
            val result = errorDeferred.await()
            assertEquals(Throwable("Payment failed"), result)
            assertTrue(!successDeferred.isCompleted)
        }
    }

}