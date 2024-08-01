package model

data class AppInfo(
    val name: String,
    val version: String,
    val url: String
)


data class InitStripeParams(
    val publishableKey: String,
    val stripeAccountId: String? = null,
    val threeDSecureParams: ThreeDSecureConfigurationParams? = null,
    val merchantIdentifier: String? = null,
    val urlScheme: String? = null,
    val setReturnUrlSchemeOnAndroid: Boolean? = null
)


data class InitialiseParams (
    val publishableKey: String,
    val stripeAccountId: String? = null,
    val threeDSecureParams: ThreeDSecureConfigurationParams? = null,
    val merchantIdentifier: String? = null,
    val urlScheme: String? = null,
    val setReturnUrlSchemeOnAndroid: Boolean? = null,
    val appInfo: AppInfo
)