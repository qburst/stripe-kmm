import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.common.models.CartItem
import kotlin.math.roundToInt

@Composable
fun Cart( onCheckout: () -> Unit) {

    val cartItems = listOf(
        CartItem(id = 1, name = "Item 1", price = 10.0, quantity = 2),
        CartItem(id = 2, name = "Item 2", price = 15.5, quantity = 1),
        CartItem(id = 3, name = "Item 3", price = 7.0, quantity = 3)
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Your Cart",
                modifier = Modifier.padding(vertical = 16.dp),
                fontSize = 20.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cartItems) { item ->
                CartItemRow(item)
            }
        }

        val totalPrice = cartItems.sumOf { it.price * it.quantity }
        val roundedTotalPrice = (totalPrice * 100).roundToInt() / 100.0
        Text("Total: $$roundedTotalPrice", modifier = Modifier.padding(8.dp))

        Button(
            onClick = onCheckout,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            Text("Checkout")
        }
    }
}

@Composable
fun CartItemRow(item: CartItem) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(item.name)
        Text("${item.quantity}")
        Text("${item.price}")
        val totalItemPrice = (item.price * item.quantity * 100).roundToInt() / 100.0
        Text("$$totalItemPrice")
    }
}
