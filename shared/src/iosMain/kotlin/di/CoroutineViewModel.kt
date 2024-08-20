package di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

 abstract class CoroutineViewModel {

     val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

     fun dispose() {
        coroutineScope.cancel()
        onCleared()
    }
    protected  open fun onCleared() {
    }


 }