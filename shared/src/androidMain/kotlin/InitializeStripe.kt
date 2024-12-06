import android.content.Context
import androidx.activity.ComponentActivity
import com.stripe.android.Stripe
import model.InitialiseParams

class InitializeStripe {
    private lateinit var activity: ComponentActivity
    private lateinit var context: Context
    private lateinit var publishableKey: String
    lateinit var stripe: Stripe


    fun initializeStripe(initialObject: InitialiseParams) {
        if ((initialObject.androidActivity != null) && (initialObject.androidContext != null)) {
            activity = initialObject.androidActivity as ComponentActivity
            context = initialObject.androidContext as Context
            publishableKey = initialObject.publishableKey

            stripe = Stripe(
                context = context,
                publishableKey = publishableKey
            )
        }
    }

}

object SingletonStripeInitialization {
public val StripeInstanse = InitializeStripe()
}