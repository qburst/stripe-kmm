//Platform.wasmJsMain

@JsFun("showAlert")
external fun showAlert()

fun addNumbers(): Int {
    val a = 10
    val b = 20
    return a + b
}
//external fun addNumbers():Int
class WasmPlatform: Platform {

    override val name: String = "Web with Kotlin/Wasm"
    override  val data: String = "My testing data"

    override fun getNumber():String{
        return "my testing function"
    }

    override fun getPayment(): String {

        return "Result of the addNumbers function:"

    }
}

actual fun getPlatform(): Platform = WasmPlatform()