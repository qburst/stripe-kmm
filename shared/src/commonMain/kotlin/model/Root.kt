package model

/**
 * Represents information about the application.
 *
 * @property name The name of the application (optional).
 * @property version The version of the application (optional).
 * @property partnerId The partner ID associated with the application (optional).
 * @property url The URL associated with the application (optional).
 */
data class AppInfo(
        val name: String? = null,
        val version: String? = null,
        val partnerId: String? = null,
        val url: String? = null
) {
    /**
     * Converts this object to a dictionary.
     *
     * @return A map containing the name, version, partner ID, and URL.
     */
    fun toDictionary(): Map<String, Any?> {
        return mapOf("name" to name, "version" to version, "partnerId" to partnerId, "url" to url)
    }
}

/**
 * Represents initialization parameters for Stripe.
 *
 * @property publishableKey The publishable key for the Stripe account.
 * @property stripeAccountId The Stripe account ID (optional).
 * @property threeDSecureParams The 3D Secure configuration parameters (optional).
 * @property merchantIdentifier The merchant identifier for the payment setup (optional).
 * @property urlScheme The URL scheme for the payment process (optional).
 * @property setReturnUrlSchemeOnAndroid A flag to set the return URL scheme on Android (optional).
 */
data class InitStripeParams(
        val publishableKey: String,
        val stripeAccountId: String? = null,
        val threeDSecureParams: ThreeDSecureConfigurationParams? = null,
        val merchantIdentifier: String? = null,
        val urlScheme: String? = null,
        val setReturnUrlSchemeOnAndroid: Boolean? = null
)

/**
 * Represents initialization parameters for the payment setup.
 *
 * @property publishableKey The publishable key for the Stripe account.
 * @property stripeAccountId The Stripe account ID (optional).
 * @property threeDSecureParams The 3D Secure configuration parameters (optional).
 * @property merchantIdentifier The merchant identifier for the payment setup (optional).
 * @property urlScheme The URL scheme for the payment process (optional).
 * @property setReturnUrlSchemeOnAndroid A flag to set the return URL scheme on Android (optional).
 * @property appInfo The app info, which contains application-specific details (optional).
 * @property androidContext The Android context (optional).
 * @property androidActivity The Android activity (optional).
 */
data class InitialiseParams(
        val publishableKey: String,
        val stripeAccountId: String? = null,
        val threeDSecureParams: ThreeDSecureConfigurationParams? = null,
        val merchantIdentifier: String? = null,
        val urlScheme: String? = null,
        val setReturnUrlSchemeOnAndroid: Boolean? = null,
        val appInfo: AppInfo? = null,
        val androidContext: Any? = null,
        val androidActivity: Any? = null
) {
    /**
     * Converts this object to a dictionary.
     *
     * @return A map containing the publishable key, Stripe account ID, 3D Secure params,
     * ```
     *         merchant identifier, URL scheme, return URL scheme flag, and app info.
     * ```
     */
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
