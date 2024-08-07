package repository

interface StripeRepository {
    @Throws(Throwable::class)
    fun initialise(publishableKey:String, clientSecret:String)
}