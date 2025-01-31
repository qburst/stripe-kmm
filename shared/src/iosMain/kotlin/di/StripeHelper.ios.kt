package di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import repository.StripeRepository

/**
 * Initializes Koin with a Stripe repository module.
 *
 * This function sets up Koin dependency injection for the `StripeRepository` instance.
 * It starts the Koin application and loads the provided module. The module injects
 * a singleton instance of the `StripeRepository` which can be accessed via Koin.
 *
 * Note: This function is not currently accessible from the `iosApp`. To enable access
 * from iOS, ensure that this function is properly exposed in your Kotlin Multiplatform
 * code, and dependencies are passed through expected methods for iOS interoperability.
 *
 * @param stripeRepository An instance of the `StripeRepository` to be injected.
 */
fun stripeInitKoin(stripeRepository: StripeRepository) {
    val koinApp = startKoin() {
        modules(
            stripeModule(stripeRepository),
        )
    }.koin
}

/**
 * Defines a Koin module that provides the `StripeRepository`.
 *
 * This module binds the provided `StripeRepository` as a singleton instance within
 * the Koin container, making it available throughout the application where needed.
 *
 * @param stripeRepository The `StripeRepository` to be injected into the Koin container.
 * @return A Koin `Module` that binds the `StripeRepository`.
 */
fun stripeModule(stripeRepository: StripeRepository): Module = module {
    single { stripeRepository }
}

/**
 * Singleton object for Stripe integration.
 *
 * The `Stripe` object provides a mechanism for setting and accessing the `StripeRepository`
 * throughout the application. The `setProvider` function is used to assign the repository,
 * which can then be accessed from anywhere in the code.
 */
object Stripe {
    lateinit var provider: StripeRepository

    /**
     * Sets the Stripe repository provider.
     *
     * This method assigns the provided `StripeRepository` to the `provider` variable,
     * which can then be accessed as a singleton instance across the application.
     *
     * @param stripe An instance of `StripeRepository` to be used as the provider.
     */
    fun setProvider(stripe: StripeRepository) {
        provider = stripe
    }
}
