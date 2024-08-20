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

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeStripe("pk_test_FkQvi0DNueKlNnVwNoJktg2W", "pi_1PmuqXKJ38Q1wp9dLgw8eijG_secret_5W09ySjx4NofVyUq9os07fOKj")

        setContent {
            App()
        }
    }

    private fun initializeStripe(publishableKey: String, clientSecret: String) {
        lifecycleScope.launch {
            StripeSdkSingleton.provideStripeSdk.initialise(
                publishableKey = publishableKey,
                clientSecret = clientSecret,
                context = applicationContext,
                activity = this@MainActivity
            )
        }
    }
}
object StripeSdkSingleton {
    val provideStripeSdk = ProvideStripeSdk()
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}