package com.qburst.stripe_kmm

import App
import StripAuthentication
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
        lifecycleScope.launch {
            try {
                StripAuthentication().getCustomers(
                    applicationContext,
                    this@MainActivity,
                    "pi_1PjJ9tKJ38Q1wp9d34bgxfya_secret_ERnyOPd9d4g8DRywbiPnDocJ6",
                    "pk_test_FkQvi0DNueKlNnVwNoJktg2W"
                )
            } catch (e: Exception) {
                Log.e("CUSTOMER_DETAILS", e.toString())
                e.printStackTrace()
            }
        }

    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}