This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop, Server.

- `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:

  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

- `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

- `/server` is for the Ktor server application.

- `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.

# Stripe KMM Integration

This repository provides an implementation of **Stripe Payment** using **Kotlin Multiplatform Mobile (KMM)**. It supports multiple payment methods, including **iDEAL, Card, UPI, and CashApp**.

## 🚀 Features

- **Initialize Stripe SDK** with a publishable key
- **Create Payment Methods** for different payment options
- **Confirm Payment** and handle next actions
- **Support for multiple payment methods** (Card, iDEAL, UPI, CashApp)

## 📌 Installation

### Shared Module Setup

1. **Add Dependencies**  
   Add the Stripe KMM dependency in your `shared` module's `build.gradle.kts` under `commonMain`:

   ```kotlin
   kotlin {
       sourceSets {
           commonMain {
               dependencies {
                   implementation("io.github.qburst.stripekmm:<version>")
               }
           }
       }
   }
   ```

### iOS Setup

1. **Add Shared Dependency**

   - Ensure the shared module dependency is properly set up in your iOS project
   - The shared module containing the Stripe KMM implementation will be automatically available

2. **Add Swift Package**
   - Open your iOS project in Xcode
   - Go to File → Add Package Dependencies
   - Add `SwiftKMMStripeKit`
   - Click on "Add Package"

## 📖 Usage

### 1️⃣ Initialize Stripe SDK

Before making any payments, initialize the Stripe SDK with your publishable key:

```kotlin
val stripe = ProvideStripeSdk()

val initialiseParams = InitialiseParams(
    publishableKey = "<your_publishable_key>",
    appInfo = AppInfo(
        name = "<your_app_name>",
        version = "<your_app_version>",
        partnerId = "<your_partner_id>",
        url = "<your_app_url>",
    )
)

// Initialize Stripe
CoroutineScope(Dispatchers.Default).launch {
    stripe.initialise(initialiseParams)
}
```

### 2️⃣ Collect Billing Details

Before proceeding with payment, collect user billing details:

```kotlin
val billingDetails = BillingDetails(
    name = "<customer_name>",
    email = "<customer_email>",
    phone = "<customer_phone>"
)
```

### 3️⃣ Choose a Payment Method

The app provides different payment methods. The user selects one from:

- iDEAL
- Card
- UPI
- CashApp

```kotlin
var selectedMethod by remember { mutableStateOf("") }
```

### 4️⃣ Create Payment Method

Once the user provides the necessary details, create the payment method:

```kotlin
CoroutineScope(Dispatchers.Default).launch {
    stripe.createPaymentMethod(
        params = when(selectedMethod) {
            "Ideal" -> {
                CreateParams.IdealParams(
                    paymentMethodData = CreateParams.PaymentMethodDataIdeal(
                        bankName = "<ideal_bank_name>",
                        billingDetails = billingDetails
                    )
                )
            }
            "Card" -> {
                CreateParams.CardParamsWithToken(
                    paymentMethodData = CreateParams.PaymentMethodDataWithToken(
                        token = "<card_token>",
                        billingDetails = billingDetails
                    )
                )
            }
            "Upi" -> {
                CreateParams.UpiParams(
                    paymentMethodData = CreateParams.PaymentMethodDataUpi(
                        vpa = "<upi_vpa>",
                        billingDetails = billingDetails
                    )
                )
            }
            "CashApp" -> {
                CreateParams.CashAppParams(
                    paymentMethodData = CreateParams.PaymentMethodDataCashApp(
                        billingDetails = billingDetails
                    )
                )
            }
            else -> throw IllegalStateException("Invalid payment method selected")
        },
        options = CreateOptions(FutureUsage.OFF_SESSION),
        onSuccess = { result ->
            println("Payment Method Created: $result")
        },
        onError = { error ->
            println("Error: $error")
        }
    )
}
```

### 5️⃣ Confirm Payment

Once a payment method is created, confirm the payment:

```kotlin
CoroutineScope(Dispatchers.Default).launch {
    stripe.confirmPayment(
        paymentIntentClientSecret = "<payment_intent_client_secret>",
        params = ConfirmParams.CardParamsWithToken(
            paymentMethodData = ConfirmParams.PaymentMethodDataWithToken(
                token = "<card_token>",
                billingDetails = billingDetails
            )
        ),
        options = CreateOptions(FutureUsage.OFF_SESSION),
        onSuccess = { result ->
            println("Payment Confirmed: $result")
        },
        onError = { error ->
            println("Error: $error")
        }
    )
}
```

### 6️⃣ Handle Next Actions (If Required)

Sometimes, additional steps are required for authentication (e.g., 3D Secure). In such cases, handle next actions:

```kotlin
CoroutineScope(Dispatchers.Default).launch {
    stripe.handleNextAction(
        paymentIntentClientSecret = "<payment_intent_client_secret>",
        returnURL = "<your_return_url>",
        onSuccess = { result ->
            println("Next Action Handled: $result")
        },
        onError = { error ->
            println("Error: $error")
        }
    )
}
```

## 🛠️ Customization

You can customize the following parameters:

- Billing Details (`BillingDetails(name, email, phone)`)
- Payment Options (`CreateOptions(FutureUsage.OFF_SESSION)`)
- Return URL (`<your_return_url>`)
- Publishable Key (`<your_publishable_key>`)

## 📌 Notes

- Ensure you replace placeholders (`<your_publishable_key>`, `<payment_intent_client_secret>`, `<your_return_url>`) with your actual credentials
- Test using Stripe Test Cards from Stripe Docs
- You must handle 3D Secure authentication (if required)

## 📜 License

This project is licensed under the MIT License.

## 📞 Support

For issues and support, open an issue on the repository or check Stripe Documentation:

[🔗 Stripe Docs](https://stripe.com/docs)
