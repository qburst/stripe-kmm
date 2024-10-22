//Platform.wasmJsMain

//@JsFun("showAlert")
//external fun showAlert()

import kotlinx.browser.window
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

//fun addNumbers(): Int {
//    val a = 10
//    val b = 20
//    return a + b
//}
////external fun addNumbers():Int
//class WasmPlatform: Platform {
//
//    override val name: String = "Web with Kotlin/Wasm"
//    override  val data: String = "My testing data"
//
//    override fun getNumber():String{
//        return "my testing function"
//    }
//
//    override fun getPayment(): String {
//
//        return "Result of the addNumbers function:"
//
//    }
//}
actual fun getPlatform(): Platform = WASMPlatform()


class WASMPlatform : Platform {
    override val name: String = "WASM JS"
    override val data: String = "WASM specific data"

    // Implement the required functions
//    override fun getNumber(): String = "WASM Platform Number"
    override fun getPayment(): String = "WASM Payment Info"
}

private val client = HttpClient(Js)

fun fetchApiResponse() {
    MainScope().launch {
        try {
            val response: String = client.get("https://jsonplaceholder.typicode.com/posts/1").toString()
            println("API Response: $response")
            window.alert("API Response: $response")
        } catch (e: Exception) {
            println("Error: ${e.message}")
            window.alert("Failed to fetch API response")
        }
    }
}
//actual fun getPlatform(): Platform = WasmPlatform()