package com.common.payment

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.formUrlEncode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

actual suspend fun fetchPaymentIntentClientSecret(amount: String): String {
    val client = HttpClient(Darwin) { // Use Darwin engine for iOS
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging)
    }

    try {
        val response: String = client.post("https://api.stripe.com/v1/payment_intents") {
            header(HttpHeaders.Authorization, "Bearer sk_test_hPRNV2gZ6gcIV99ndFejwEHT")
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(
                listOf(
                    "amount" to amount, // Amount in paise (smallest currency unit)
                    "currency" to "inr",
                    "automatic_payment_methods[enabled]" to "true"
                ).formUrlEncode()
            )
        }.body()

        val jsonObject = Json.parseToJsonElement(response).jsonObject
        return jsonObject["client_secret"]?.jsonPrimitive?.content ?: throw Exception("No client_secret found")
    } catch (e: Exception) {
        throw Exception("Error fetching Payment Intent: ${e.message}")
    } finally {
        client.close()
    }
}
