import io.ktor.client.engine.js.Js
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.HttpClientEngineConfig
class JSMainPlatform : Platform {
    override fun selectClient(): HttpClientEngineFactory<HttpClientEngineConfig> {
        return Js
    }
}

actual fun choosePlatform(): Platform = JSMainPlatform()
