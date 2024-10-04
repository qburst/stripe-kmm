package model

/**
 * This class is returned as a result of all api calls
 * @param [success]  will always have the JSON string of the result response from Stripe api
 * @param [error]  will have the [Throwable] exception from api or from the input validation class
 */
data class ApiResult (
    val success: Map<String, Any?>?,
    val error: Throwable?
)