package com.qburst.stripe_kmm.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.stripe.Stripe
import com.stripe.model.PaymentIntent
import com.stripe.param.PaymentIntentCreateParams

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/payment-intent") {
            Stripe.apiKey = "sk_test_hPRNV2gZ6gcIV99ndFejwEHT"
            val paymentIntentCreateParams: PaymentIntentCreateParams = PaymentIntentCreateParams.builder().setAmount(2000L).setCurrency("inr")
                .setAutomaticPaymentMethods(PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build()
                ).build()
            val paymentIntent: PaymentIntent = PaymentIntent.create(paymentIntentCreateParams)
            call.respondText(paymentIntent.toString())
        }
    }
}
