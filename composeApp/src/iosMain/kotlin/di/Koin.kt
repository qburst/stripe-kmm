package di

import org.koin.core.context.startKoin
import repository.StripeRepository

fun initKoin(stripeRepository: StripeRepository) {
    val koinApp = startKoin() {
        modules(
            stripeModule(stripeRepository),
        )
    }.koin
}
