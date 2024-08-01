

//import com.qburst.stripe_kmm.StripeSDK.DemoG1Updater
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ExportObjCClass
import model.InitialiseParams
import platform.Foundation.NSLog

@ExportObjCClass
actual class provideStripeSdk actual constructor() : StripeSdk {

    @OptIn(ExperimentalForeignApi::class)
    override suspend fun initialise() {
//        DemoG1Updater.updatePaymentSheetPresentedWithIsPresented(true)
    }

}