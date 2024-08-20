import android.nfc.Tag
import android.util.Log
import model.InitialiseParams


actual class ProvideStripeSdk actual constructor()  {
//    params: InitialiseParams
     actual suspend fun initialise(initialiseParams: InitialiseParams) {
        Log.d("TAG","hello from android")
    }

    actual suspend fun createPaymentMethod(
        params: CreateParams,
        options: CreateOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
    }

}
