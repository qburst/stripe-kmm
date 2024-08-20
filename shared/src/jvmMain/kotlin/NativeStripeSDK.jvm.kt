import model.InitialiseParams

actual class ProvideStripeSdk  {
    actual suspend fun initialise(initialiseParams: InitialiseParams) {
        TODO("Not yet implemented")
    }

    actual suspend fun createPaymentMethod(
        params: CreateParams,
        options: CreateOptions,
        onSuccess: (Map<String, Any?>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
    }
}
