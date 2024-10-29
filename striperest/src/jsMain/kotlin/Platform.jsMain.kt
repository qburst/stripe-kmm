
external fun showAlert()

class JSMainPlatform: Platform {

    override val name: String = "Web with Kotlin/new JSMain"
    override  val data: String = "My testing data in JSMain"

    override fun getValue():String{
        return "my testing function"
    }

}

actual fun choosePlatform(): Platform = JSMainPlatform()