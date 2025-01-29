package com.common.payment

expect suspend fun fetchPaymentIntentClientSecret(amount: String): String
