import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.isMock
import io.mockative.mock
import kotlinx.coroutines.test.runTest
import model.Address
import model.BillingDetails
import model.ApiResult
import repositories.PaymentRepository
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertTrue

@Ignore("Need to check reason for test failure..")
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
}