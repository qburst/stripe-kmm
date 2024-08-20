package model

data class AppInfo(
    val name: String? = null,
    val version: String? = null,
    val partnerId: String? = null,
    val url: String? = null
) {
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "version" to version,
            "partnerId" to partnerId,
            "url" to url
        )
    }
}

data class InitStripeParams(
    val publishableKey: String,
    val stripeAccountId: String? = null,
    val threeDSecureParams: ThreeDSecureConfigurationParams? = null,
    val merchantIdentifier: String? = null,
    val urlScheme: String? = null,
    val setReturnUrlSchemeOnAndroid: Boolean? = null
)


data class InitialiseParams(
    val publishableKey: String,
    val stripeAccountId: String? = null,
    val threeDSecureParams: ThreeDSecureConfigurationParams? = null,
    val merchantIdentifier: String? = null,
    val urlScheme: String? = null,
    val setReturnUrlSchemeOnAndroid: Boolean? = null,
    val appInfo: AppInfo? = null
) {
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
            "publishableKey" to publishableKey,
            "stripeAccountId" to stripeAccountId,
            "threeDSecureParams" to threeDSecureParams?.toDictionary(),
            "merchantIdentifier" to merchantIdentifier,
            "urlScheme" to urlScheme,
            "setReturnUrlSchemeOnAndroid" to setReturnUrlSchemeOnAndroid,
            "appInfo" to appInfo?.toDictionary()
        )
    }
}
