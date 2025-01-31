
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.common.models.CartItem
import com.common.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.AppInfo
import model.InitialiseParams
import model.PresentOptions
import model.SetupParams
import kotlin.math.roundToInt

@Composable
fun Cart(
     onFailure: (Screen) -> Unit,
     onSuccess: (Screen) -> Unit
) {
    val stripe = ProvideStripeSdk()
    var amount="0"
    val initialiseParams = InitialiseParams(
        publishableKey = "pk_test_FkQvi0DNueKlNnVwNoJktg2W",
        appInfo = AppInfo(
            name = "Stripe App",
            version = "1.2.3",
            partnerId = "new",
            url = "https://qburst.com",
        )
    )

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            stripe.initialise(initialiseParams)
        }
    }

    Scaffold(
        topBar = {
            AppBar("Cart", null)
        }
    ) {


        val cartItems = listOf(
            CartItem(id = 1, name = "Keyboard", price = 899.99, quantity = 2),
            CartItem(id = 2, name = "Mouse", price = 499.99, quantity = 1),
            CartItem(id = 3, name = "Monitor", price = 12499.99, quantity = 3)
        )

        Box(modifier = Modifier.fillMaxSize().background(color = Color(0xF9F8FE))){


        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartItems) { item ->
                    CartItemRow(item)
                }
            }

            val totalPrice = cartItems.sumOf { it.price * it.quantity }
             amount = ((totalPrice * 100).roundToInt() ).toString()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .padding(16.dp)
                    .background(Color(0xFFE0F7FA), shape = RoundedCornerShape(10.dp)) // Island with rounded corners
                    .padding(10.dp), // Inner padding inside the island
                contentAlignment = Alignment.Center
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween, // Spaces out the elements
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Row(){
                        Text("Total: ", modifier = Modifier.padding(8.dp))
                        Text("₹$totalPrice", fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF00796B))

                    }


                    Button(
                        shape = RoundedCornerShape(24.dp),
                        onClick = {
                            var paymentIntentParams = SetupParams(
                                merchantDisplayName = "Qburst",
                                amount=amount,
                                paymentIntentClientSecret = "pi_1QXLRzKJ38Q1wp9dF6c5MrT1_secret_9EDiqf0eNa3rfEyE1U2RDMcQj"
                            )
                            CoroutineScope(Dispatchers.Default).launch {
                                stripe.initPaymentSheet(
                                    params = paymentIntentParams,
                                    onSuccess = { result ->
                                        print(" result = $result")
                                        // Pass the result back to the UI through the onSuccess callback
                                        CoroutineScope(Dispatchers.Default).launch {
                                            stripe.presentPaymentSheet(
                                                options = PresentOptions(),
                                                onSuccess = { result ->
                                                    onSuccess(Screen.OrderSuccess)
                                                    print(" result = $result")
                                                    // Pass the result back to the UI through the onSuccess callback
                                                },
                                                onError = { error ->
                                                    // Pass the error back to the UI through the onError callback
                                                    onFailure(Screen.OrderFailed)
                                                    print(error)
                                                }
                                            )
                                        }
                                    },
                                    onError = { error ->
                                        // Pass the error back to the UI through the onError callback
                                        print(error)
                                    }
                                )
                            }

                    }) {
                    Text("Checkout")
                }}
            }

        }
    }}


}

@Composable
fun CartItemRow(item: CartItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(Color(0xFFE0F7FA), shape = RoundedCornerShape(10.dp)) // Island with rounded corners
            .padding(10.dp), // Inner padding inside the island
    ){

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column (){ Text(item.name, fontSize = 15.sp)
            Text("Qty: ${item.quantity}") }
        Column {
            Text("₹${item.price}")
            Text("x ${item.quantity}")
            val totalItemPrice = (item.price * item.quantity * 100).roundToInt() / 100.0
            Text("$totalItemPrice", fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF8C52F6),
                modifier = Modifier.padding(top = 5.dp))
        }
    }
    }

}