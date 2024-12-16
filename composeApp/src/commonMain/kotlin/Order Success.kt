import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.common.navigation.Screen


@Composable
fun OrderSuccess(onNavigate: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xF9F8FE))){
        Column(modifier = Modifier.fillMaxSize().padding(25.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Order Placed!", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp))
            Text("Woohoo! Your order has been placed successfully", modifier = Modifier.padding(bottom = 8.dp))
            Button(onClick = onNavigate) { Text("Continue Shopping") }
        }
    }
}