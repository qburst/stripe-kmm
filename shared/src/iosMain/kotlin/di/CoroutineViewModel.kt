package di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/**
 * `CoroutineViewModel` is an abstract base class designed to provide a coroutine scope
 * for managing asynchronous tasks in a structured and lifecycle-aware manner. It helps
 * in managing coroutine lifecycles by ensuring that all coroutines launched in this scope
 * are canceled when the view model is disposed.
 *
 * This class is platform-independent and can be extended by platform-specific view models
 * in Kotlin Multiplatform projects.
 */
abstract class CoroutineViewModel {

    /**
     * `coroutineScope` is a `CoroutineScope` bound to the main thread and a supervisor job.
     * The main thread is typically where UI interactions happen, and the supervisor job allows
     * for launching multiple coroutines where failures in one will not cancel others.
     */
    val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    /**
     * `dispose` is called to cancel the coroutine scope and clean up any resources when
     * the view model is no longer needed. It calls the `cancel()` function on the `coroutineScope`
     * and then triggers the `onCleared()` method for additional clean-up logic.
     */
    fun dispose() {
        coroutineScope.cancel()
        onCleared()
    }

    /**
     * `onCleared` is a protected open method that can be overridden by subclasses
     * to handle additional cleanup logic when the view model is disposed.
     * By default, it does nothing, but subclasses may override this method if needed.
     */
    protected open fun onCleared() {
    }
}
