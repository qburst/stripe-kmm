import androidx.compose.runtime.*
import com.common.navigation.Screen

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Cart) }

     fun navigateTo(screen: Screen) {
        currentScreen = screen
    }

    // Render content based on the current screen
    when (currentScreen) {
        is Screen.Checkout -> Checkout { navigateTo(Screen.Cart) }
        is Screen.Cart -> Cart { navigateTo(Screen.OrderFailed) }
        is Screen.OrderFailed -> OrderFailed { navigateTo(Screen.Cart) }
        is Screen.OrderSuccess -> OrderSuccess { navigateTo(Screen.Cart) }
    }
}
