package model


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
data class Props(
    val iosProps: IOSProps? = null,
    val androidProps: AndroidProps? = null
)

enum class NavigationBarStyle {
    DEFAULT, BLACK, BLACK_TRANSLUCENT
}

data class IOSProps(
    val backgroundColor: String? = null,
    val footer: FooterProps? = null
)

data class IOSNavigationBarProps(
    val barStyle: NavigationBarStyle? = null,
    val translucent: Boolean? = null,
    val barTintColor: String? = null
)

data class AndroidProps(
    val accentColor: String? = null
)

data class AndroidNavigationBarProps(
    val statusBarColor: String? = null,
    val backgroundColor: String? = null
)

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
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
            "barStyle" to barStyle?.name,  // Convert enum to string using name
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


data class FooterProps(
    val backgroundColor: String? = null,
    val chevronColor: String? = null,
    val headingTextColor: String? = null,
    val textColor: String? = null
)

data class LabelProps(
    val headingTextColor: String? = null,
    val textColor: String? = null,
    val textFontSize: Int? = null,
    val headingFontSize: Int? = null
) {
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
            "headingTextColor" to headingTextColor,
            "textColor" to textColor,
            "textFontSize" to textFontSize,
            "headingFontSize" to headingFontSize
        )
    }
}

data class TextFieldProps(
    val borderColor: String? = null,
    val borderWidth: Int? = null,
    val borderRadius: Int? = null,
    val textColor: String? = null,
    val textFontSize: Int? = null
) {
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

data class ButtonProps(
    val backgroundColor: String? = null,
    val borderRadius: Int? = null,
    val textColor: String? = null,
    val textFontSize: Int? = null
) {
    fun toDictionary(): Map<String, Any?> {
        return mapOf(
            "backgroundColor" to backgroundColor,
            "borderRadius" to borderRadius,
            "textColor" to textColor,
            "textFontSize" to textFontSize
        )
    }
}
