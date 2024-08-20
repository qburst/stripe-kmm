package model

enum class FutureUsage(val value: String) {
    OFF_SESSION("OffSession"),
    ON_SESSION("OnSession");

    companion object {
        // Function to convert from a string value to an enum
        fun fromString(value: String): FutureUsage? {
            return values().find { it.value == value }
        }
    }

    fun toDictionary(): Map<String, String> {
        return mapOf("value" to value)
    }
}
