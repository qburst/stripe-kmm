package com.common.navigation

sealed class Screen {
    object Cart : Screen()
    object Checkout : Screen()
}

