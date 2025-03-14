package com.qburst.stripe_kmm

import App
import ProvideStripeSdk
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import model.AppInfo
import model.InitialiseParams

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stripe = ProvideStripeSdk()

        val initialiseParams = InitialiseParams(
            publishableKey = "pk_test_FkQvi0DNueKlNnVwNoJktg2W",
            androidContext = applicationContext,
            androidActivity = this@MainActivity,
            appInfo = AppInfo(
                name = "Stripe App",
                version = "1.2.3",
                partnerId = "new",
                url = "https://qburst.com",
            )
        )

        lifecycleScope.launch {
            stripe.initialise(initialiseParams)
        }


        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}