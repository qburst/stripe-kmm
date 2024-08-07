import di.CoroutineViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repository.StripeRepository


actual class ProvideStripeSdk actual constructor() : KoinComponent, CoroutineViewModel() {

    var publishableKey: String? = null
    var clientSecret: String? = null
    val stripe: StripeRepository by inject()
    actual suspend fun initialise(publishableKey:String, clientSecret:String) {
            stripe.initialise(publishableKey, clientSecret)
    }

}