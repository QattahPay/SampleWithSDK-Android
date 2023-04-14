## Qattah Pay SDK Sample Application

This is a sample application that demonstrates how to use the Qattah Pay SDK in your own application.

## Requirements

To use the Qattah Pay SDK, you will need:

- A merchant account with Qattah Pay
- A merchant API key from Qattah Pay
- An Android device running Android 4.4 (KitKat) or higher

## Installation

To use the Qattah Pay SDK in your own application, follow these steps:

1. Add the following to your project-level build.gradle file:

```kotlin
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

or add this to your project settings.gradle:
```kotlin
dependencyResolutionManagement {
    ...
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

```

2. Add the following to your app-level build.gradle file:

```kotlin
dependencies {
    implementation 'com.github.QattahPay:qattahpay-android-sdk:2.4@aar'
}

```

## Usage

To use the QattahPay Android SDK Library, you need to initialize it in your application's onCreate() method:

```kotlin
QattahPay.initialize(context, "YOUR_MERCHANT_API_KEY");

```

To make a payment request, you need to create a PaymentRequest object and call the startPayment() method:

```kotlin
            // create a new payment request
            val paymentRequest: PaymentRequest = PaymentRequest.Builder()
                .setAmount(120.00) // amount in Saudi Riyals (SAR)
                .setCurrency(Currency.SAR)
                .setOrderId("ORDER_ID")
                .setDescription("PRODUCT_DESCRIPTION")
                .setCustomerEmail("CUSTOMER_EMAIL")
                .setCustomerMobileNumber("CUSTOMER_MOBILE_NUMBER")
                .isSandbox(true)
                .build()

            // start a new payment session
            QattahPay.startPayment(paymentRequest, paymentCallbacks)

```

And you can handle all callbacks by creating a local object for the Qattah Pay Callback Service in your activity by following below steps

```kotlin
private val paymentCallbacks = object : PaymentCallback {
        override fun onStarted(paymentId: String) {
            // Handle payment creation
            Toast.makeText(this@MainActivity, "Payment ID: $paymentId", Toast.LENGTH_SHORT)
                .show()
        }

        override fun onSuccess(paymentId: String) {
            // Handle payment success
            Toast.makeText(this@MainActivity, "Payment Success", Toast.LENGTH_SHORT).show()
        }

        override fun onError(errorMessage: String) {
            // Handle payment error
            Toast.makeText(
                this@MainActivity,
                "Payment Failed: $errorMessage",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onCancel() {
            // Handle payment cancel
            Toast.makeText(this@MainActivity, "Payment canceled", Toast.LENGTH_LONG).show()
        }
    }
```

## Documentation

For more information on how to use the Qattah Pay SDK, please refer to the official documentation from [this link](https://api-docs.qattahpay.sa/qattah-payment-public-api-documentation/)
