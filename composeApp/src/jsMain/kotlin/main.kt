import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.jetbrains.skiko.wasm.onWasmReady
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow("Test App") {


            MaterialTheme(
                colorScheme = LightColorScheme,
                typography = Typography,
                content = { Button(onClick ={}

                ) {
                    Text("TEST WEB")
                }

                }
            )




        }
    }
}
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6650a4),
    secondary = Color(0xFF6650a4),
    tertiary = Color(0xFF6650a4)
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    )