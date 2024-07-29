package com.qburst.stripe_kmm

import android.app.Application
import com.stripe.android.PaymentConfiguration

class MyApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

        fun getAppContext() = instance!!.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize the Stripe SDK with your publishable key
        PaymentConfiguration.init(
            applicationContext,
            "your_publishable_key" // Replace with your actual Stripe publishable key
        )
    }
}