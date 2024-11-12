
external fun showAlert()

class JSMainPlatform: Platform {

    override val name: String = "Web with Kotlin/new JSMain"
    override  val data: String = "My testing data in JSMain"

    override fun printHelloWorld():String{

            return ("Hello, World testing from new module!")

    }

}

actual fun choosePlatform(): Platform = JSMainPlatform()