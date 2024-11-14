// src/commonMain/kotlin/com/example/models/CartItem.kt
package com.common.models

data class CartItem(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int
)
