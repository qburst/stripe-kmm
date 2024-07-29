import model.PaymentMethod

// shared/src/commonMain/kotlin/com/example/stripekmm/StripeFunctionality.kt

interface StripeFunctionality {
     fun createPaymentMethod(): Any
}

expect fun getStripeFunctionality(): StripeFunctionality
