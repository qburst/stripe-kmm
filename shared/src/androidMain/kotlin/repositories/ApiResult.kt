package repositories

data class ApiResult (
    val success: Map<String, Any?>?,
    val error: Throwable?
)