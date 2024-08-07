import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
 fun App() {
   val stripe = ProvideStripeSdk()
    val publishableKey = "pk_test_FkQvi0DNueKlNnVwNoJktg2W"
    val clientSecret = "pi_1PkSCWKJ38Q1wp9doyjSYOkp_secret_2KgZsrv5w4kP4csyfiLfA52qO"
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    stripe.initialise(publishableKey,clientSecret)
                }
            }) {
                Text("initiate payment")
            }
            Column(Modifier.padding(15.dp), ) {
                Text("publishableKey: $publishableKey",  fontWeight = FontWeight.Bold)
                Text("clientSecret: $clientSecret", fontWeight = FontWeight.Bold)
            }
        }
    }
}