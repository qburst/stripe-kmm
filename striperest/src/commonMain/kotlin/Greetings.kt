package striperest
import Platform
import choosePlatform
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.promise
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.forms.FormDataContent
import io.ktor.http.Parameters
import io.ktor.serialization.kotlinx.json.*
import kotlinx.atomicfu.TraceBase.None.append
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.promise


class Greetings {
    private val platform = choosePlatform()
    private val client = HttpClient(platform.selectClient()) {
        install(ContentNegotiation) {
            json()
        }
    }

    fun fetchApiResponse(url: String, token: String): dynamic {
    return MainScope().promise {
        try {
            val response: HttpResponse = client.post(url) {
                headers {
                    append("Authorization", "Bearer $token")
                }
                setBody(FormDataContent(Parameters.build {
//                    append("type", "card")
//                    append("card[exp_month]", "5")
//                    append("card[exp_year]", "2026")
//                    append("card[number]", "424242424242")
//                    append("card[cvc]", "314")

                    append("description","testing from code")
                    append("name","Gokul")
                    append("email","gokul@gmail.com")

                }))
            }
            response.bodyAsText() // Return the response body as a string
        } catch (e: Exception) {
            "Error fetching data: ${e.message}"
        }
    }
}



//   public fun test(): String {
//        return "Hello, ${platform.printHelloWorld()}!"
//
//    }
}

