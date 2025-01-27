package model

/**
 * Represents configuration parameters for 3D Secure authentication.
 *
 * @property timeout The timeout for the 3D Secure process (optional).
 * @property label The label properties for the 3D Secure interface (optional).
 * @property navigationBar The navigation bar properties for the 3D Secure interface (optional).
 * @property textField The text field properties for the 3D Secure interface (optional).
 * @property submitButton The submit button properties for the 3D Secure interface (optional).
 * @property cancelButton The cancel button properties for the 3D Secure interface (optional).
 * @property continueButton The continue button properties for the 3D Secure interface (optional).
 * @property nextButton The next button properties for the 3D Secure interface (optional).
 * @property resendButton The resend button properties for the 3D Secure interface (optional).
 */
data class ThreeDSecureConfigurationParams(
        val timeout: Int? = null,
        val label: LabelProps? = null,
        val navigationBar: NavigationBarProps? = null,
        val textField: TextFieldProps? = null,
        val submitButton: ButtonProps? = null,
        val cancelButton: ButtonProps? = null,
        val continueButton: ButtonProps? = null,
        val nextButton: ButtonProps? = null,
        val resendButton: ButtonProps? = null
) {
    /**
     * Converts this object to a dictionary.
     *
     * @return A map containing the timeout, label, navigation bar, text field, buttons, etc.
     */
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
                "timeout" to timeout,
                "label" to label?.toDictionary(),
                "navigationBar" to navigationBar?.toDictionary(),
                "textField" to textField?.toDictionary(),
                "submitButton" to submitButton?.toDictionary(),
                "cancelButton" to cancelButton?.toDictionary(),
                "continueButton" to continueButton?.toDictionary(),
                "nextButton" to nextButton?.toDictionary(),
                "resendButton" to resendButton?.toDictionary()
        )
    }
}

/**
 * Represents properties for iOS and Android configurations.
 *
 * @property iosProps The iOS-specific properties (optional).
 * @property androidProps The Android-specific properties (optional).
 */
data class Props(val iosProps: IOSProps? = null, val androidProps: AndroidProps? = null)

/** Enum representing the styles for the navigation bar. */
enum class NavigationBarStyle {
    DEFAULT,
    BLACK,
    BLACK_TRANSLUCENT
}

/**
 * Represents the properties for iOS configurations.
 *
 * @property backgroundColor The background color for the iOS configuration (optional).
 * @property footer The footer properties for the iOS configuration (optional).
 */
data class IOSProps(val backgroundColor: String? = null, val footer: FooterProps? = null)

/**
 * Represents the properties for the iOS navigation bar.
 *
 * @property barStyle The style of the navigation bar (optional).
 * @property translucent Indicates whether the navigation bar is translucent (optional).
 * @property barTintColor The tint color for the navigation bar (optional).
 */
data class IOSNavigationBarProps(
        val barStyle: NavigationBarStyle? = null,
        val translucent: Boolean? = null,
        val barTintColor: String? = null
)

/**
 * Represents the properties for Android configurations.
 *
 * @property accentColor The accent color for the Android configuration (optional).
 */
data class AndroidProps(val accentColor: String? = null)

/**
 * Represents the properties for the Android navigation bar.
 *
 * @property statusBarColor The color of the status bar (optional).
 * @property backgroundColor The background color for the navigation bar (optional).
 */
data class AndroidNavigationBarProps(
        val statusBarColor: String? = null,
        val backgroundColor: String? = null
)

/**
 * Represents the properties for the navigation bar in 3D Secure UI.
 *
 * @property barStyle The style of the navigation bar (optional).
 * @property translucent Indicates whether the navigation bar is translucent (optional).
 * @property barTintColor The tint color for the navigation bar (optional).
 * @property statusBarColor The color of the status bar (optional).
 * @property backgroundColor The background color for the navigation bar (optional).
 * @property headerText The header text displayed in the navigation bar (optional).
 * @property buttonText The text displayed on the button in the navigation bar (optional).
 * @property textColor The color of the text in the navigation bar (optional).
 * @property textFontSize The font size of the text in the navigation bar (optional).
 */
data class NavigationBarProps(
        val barStyle: NavigationBarStyle? = null,
        val translucent: Boolean? = null,
        val barTintColor: String? = null,
        val statusBarColor: String? = null,
        val backgroundColor: String? = null,
        val headerText: String? = null,
        val buttonText: String? = null,
        val textColor: String? = null,
        val textFontSize: Int? = null
) {
    /**
     * Converts this object to a dictionary.
     *
     * @return A map containing the properties of the navigation bar.
     */
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
                "barStyle" to barStyle?.name, // Convert enum to string using name
                "translucent" to translucent,
                "barTintColor" to barTintColor,
                "statusBarColor" to statusBarColor,
                "backgroundColor" to backgroundColor,
                "headerText" to headerText,
                "buttonText" to buttonText,
                "textColor" to textColor,
                "textFontSize" to textFontSize
        )
    }
}

/**
 * Represents the properties for the footer in the iOS configuration.
 *
 * @property backgroundColor The background color of the footer (optional).
 * @property chevronColor The color of the chevron icon (optional).
 * @property headingTextColor The color of the heading text (optional).
 * @property textColor The color of the text (optional).
 */
data class FooterProps(
        val backgroundColor: String? = null,
        val chevronColor: String? = null,
        val headingTextColor: String? = null,
        val textColor: String? = null
)

/**
 * Represents the label properties for the 3D Secure UI.
 *
 * @property headingTextColor The color of the heading text (optional).
 * @property textColor The color of the text (optional).
 * @property textFontSize The font size of the text (optional).
 * @property headingFontSize The font size of the heading text (optional).
 */
data class LabelProps(
        val headingTextColor: String? = null,
        val textColor: String? = null,
        val textFontSize: Int? = null,
        val headingFontSize: Int? = null
) {
    /**
     * Converts this object to a dictionary.
     *
     * @return A map containing the label properties.
     */
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
                "headingTextColor" to headingTextColor,
                "textColor" to textColor,
                "textFontSize" to textFontSize,
                "headingFontSize" to headingFontSize
        )
    }
}

/**
 * Represents the text field properties for the 3D Secure UI.
 *
 * @property borderColor The color of the text field's border (optional).
 * @property borderWidth The width of the text field's border (optional).
 * @property borderRadius The radius of the text field's border (optional).
 * @property textColor The color of the text in the text field (optional).
 * @property textFontSize The font size of the text in the text field (optional).
 */
data class TextFieldProps(
        val borderColor: String? = null,
        val borderWidth: Int? = null,
        val borderRadius: Int? = null,
        val textColor: String? = null,
        val textFontSize: Int? = null
) {
    /**
     * Converts this object to a dictionary.
     *
     * @return A map containing the text field properties.
     */
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
                "borderColor" to borderColor,
                "borderWidth" to borderWidth,
                "borderRadius" to borderRadius,
                "textColor" to textColor,
                "textFontSize" to textFontSize
        )
    }
}

/**
 * Represents the button properties for the 3D Secure UI.
 *
 * @property backgroundColor The background color of the button (optional).
 * @property borderRadius The border radius of the button (optional).
 * @property textColor The color of the button's text (optional).
 * @property textFontSize The font size of the button's text (optional).
 */
data class ButtonProps(
        val backgroundColor: String? = null,
        val borderRadius: Int? = null,
        val textColor: String? = null,
        val textFontSize: Int? = null
) {
    /**
     * Converts this object to a dictionary.
     *
     * @return A map containing the button properties.
     */
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
                "backgroundColor" to backgroundColor,
                "borderRadius" to borderRadius,
                "textColor" to textColor,
                "textFontSize" to textFontSize
        )
    }
}
