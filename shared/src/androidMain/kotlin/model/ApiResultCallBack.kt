package model

interface ApiResultCallBack<T> {
    fun onSuccess(result: T)
    fun onError(error: Throwable)
}