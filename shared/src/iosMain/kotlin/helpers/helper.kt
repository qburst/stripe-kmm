package helpers

fun mapToThrowable(errorMap: Map<String, Any?>): Throwable {
    val errorDetails = errorMap["error"] as? Map<String, Any?> ?: return Exception("Unknown error")

    val code = errorDetails["code"] as? String ?: "UNKNOWN_ERROR"
    val message = errorDetails["message"] as? String ?: "An unknown error occurred"

    return Exception("Stripe Error ($code): $message")
}
