package di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import repository.StripeRepository

// this koin need be called from ios, for now it's not accessible in iosApp
fun stripeInitKoin(stripeRepository: StripeRepository) {
    val koinApp = startKoin() {
        modules(
            stripeModule(stripeRepository),
        )
    }.koin
}

fun stripeModule(stripeRepository: StripeRepository): Module = module {
    single { stripeRepository }
}

object Stripe {
    lateinit var provider:StripeRepository;

    fun setProvider(stripe:StripeRepository) {
        provider = stripe
    }
}