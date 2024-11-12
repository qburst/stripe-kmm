//
//external fun showAlert()
//
//class JSMainPlatform: Platform {
//    override fun printHelloWorld():String{
//
//            return ("Hello, World testing from new module!")
//
//    }
//}
//
//actual fun choosePlatform(): Platform = JSMainPlatform()
//
//

import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.promise

class JSMainPlatform : Platform {
    private val client = HttpClient(Js) {
        install(ContentNegotiation) {
            json()
        }
    }

    override fun printHelloWorld(): String {
        // Simple return to keep the interface
        return "Hello, World testing from new module!"
    }

    fun fetchApiResponse(url: String): dynamic {
        return MainScope().promise {
            try {
                val response: HttpResponse = client.get(url)
                response.bodyAsText()
            } catch (e: Exception) {
                "Error fetching data: ${e.message}"
            }
        }
    }
}

actual fun choosePlatform(): Platform = JSMainPlatform()
