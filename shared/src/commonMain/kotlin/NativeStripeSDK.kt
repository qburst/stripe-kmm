import model.InitialiseParams

expect class ProvideStripeSdk() {
    suspend fun initialise(initialiseParams: InitialiseParams)
    suspend fun createPaymentMethod(
        params: CreateParams,
        options: CreateOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    )
}
