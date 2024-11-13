
import io.ktor.client.engine.js.Js
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.HttpClientEngineConfig
interface Platform {
 fun selectClient(): HttpClientEngineFactory<HttpClientEngineConfig>
}

expect fun choosePlatform(): Platform